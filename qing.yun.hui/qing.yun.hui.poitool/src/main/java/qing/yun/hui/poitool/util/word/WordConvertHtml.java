package qing.yun.hui.poitool.util.word;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import qing.yun.hui.poitool.vo.SysAttachment;
import qing.yun.hui.poitool.vo.SysUser;

/***
 ** @Description: word转html
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:50:43 PM
 ** @version: V1.0
 ***/
public class WordConvertHtml {
	
	/**
	 * word 到 html 的转换
	 * @param multipartFile or file 要转换的文件
	 * @param sysUser
	 * @param path 本地存储的路径。 
	 * @return Map<String,Object> 存储的是word中的附件和转换后的html内容。
	 * **/
	public static Map<String,Object> importWordToHtml(MultipartFile multipartFile,SysUser sysUser, String path) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		if(multipartFile.getSize()<1){
			return map;
		}
        HWPFDocument wordDocument = new HWPFDocument (multipartFile.getInputStream());
        final  List<Filed> uuidList=new ArrayList<Filed>();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
        wordToHtmlConverter.setPicturesManager (new PicturesManager() {
            public String savePicture (byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
            	return resetImageSrc(uuidList,pictureType);
            }
        });
        wordToHtmlConverter.processDocument (wordDocument);
        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        List<SysAttachment> sysAttachmentList=getAttachmentList(sysUser, pics, uuidList, path);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource (htmlDocument);
        StreamResult streamResult = new StreamResult (outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty (OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty (OutputKeys.INDENT, "yes");
        serializer.setOutputProperty (OutputKeys.METHOD, "html");
        serializer.transform (domSource, streamResult);
        outStream.close();
        String content = new String (outStream.toByteArray() );
        //String title=htmlDocument.getElementsByTagName("title").item(0).getTextContent();
        map.put("content", content);
        map.put("sysAttachmentList", sysAttachmentList);
        return map;
	}
	
	/**
	 * 如果给定path 在硬盘上不存在 则创建之。
	 * @param path 磁盘文件路径
	 * */
	public static void mkdirs(String path){
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	/**
	 * 重置图片src地址且记录uuid
	 * @param suggestedName
	 * @param uuidList
	 * **/
	public static  String resetImageSrc(List<Filed> uuidList,PictureType pictureType){
		String uuid=UUID.randomUUID().toString();
		String basePath=getBasePath();
		String suggestedName=basePath+"download.json?id="+uuid;
		Filed filed=new Filed(uuid,pictureType);
		filed.setUuid(uuid);
		uuidList.add(filed);
		return suggestedName;
	}
	
	/**
	 * 如果word中存在图片，则会把图片落地到本地指定目录下，且把图片封装到附件对象中。
	 * @param sysUser
	 * @param pictureList
	 * @param uuidList
	 * @param path
	 * @return List<SysAttachment>
	 * **/
	public static List<SysAttachment> getAttachmentList(SysUser sysUser,List<Picture> pictureList,List<Filed> uuidList,String path){
		List<SysAttachment> attachmentList=new ArrayList<SysAttachment>();
		if (pictureList != null && pictureList.size()>0) {
        	if(!path.endsWith(System.getProperty("file.separator"))){
    			path = path + File.separatorChar;
    		}
        	mkdirs(path);
            for (int i = 0; i < pictureList.size(); i++) {
            	PictureType pictureType=uuidList.get(i).getPictureType();
                //重命名上传后的文件名  
                String reMyFileName = uuidList.get(i).getUuid() + "."+pictureType.getExtension();  
                //定义上传路径  
                String physicalPath = path + reMyFileName;  
                SysAttachment sysAttachment = new SysAttachment();
                sysAttachment.setId(uuidList.get(i).getUuid());
                sysAttachment.setCreater(sysUser.getName());
                sysAttachment.setCreaterId(sysUser.getAccountId());
                sysAttachment.setOriginName(uuidList.get(i).getUuid()+"."+pictureType.getExtension());
                sysAttachment.setPhysicalPath(physicalPath);
                sysAttachment.setSize((long) pictureList.get(i).getSize());
                sysAttachment.setSuffix("."+pictureType.getExtension());
                sysAttachment.setType(pictureType.getMime());
                sysAttachment.setUrl("download.json?id="+uuidList.get(i).getUuid());
                Picture pic = (Picture) pictureList.get (i);
                attachmentList.add(sysAttachment);
                try {
                    pic.writeImageContent (new FileOutputStream (path + uuidList.get(i).getUuid()+"."+uuidList.get(i).getPictureType().getExtension()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return attachmentList;
	}
	
	/**
	 * 获得项目路径
	 * */
	public static String getBasePath(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		return basePath;
	}
	
	static class Filed{
		
		public Filed(){};
		
		public Filed(String uuid,String imgType){
			this.uuid=uuid;
			this.imgType=imgType;
		}
		
		public Filed(String uuid,PictureType pictureType){
			this.uuid=uuid;
			this.pictureType=pictureType;
		}
		
		private String uuid;
		
		private String imgType;
		
		private PictureType pictureType;

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getImgType() {
			return imgType;
		}

		public void setImgType(String imgType) {
			this.imgType = imgType;
		}
		public PictureType getPictureType() {
			return pictureType;
		}
		public void setPictureType(PictureType pictureType) {
			this.pictureType = pictureType;
		}
	}

}
