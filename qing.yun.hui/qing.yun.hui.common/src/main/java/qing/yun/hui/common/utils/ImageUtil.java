package qing.yun.hui.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 ** @category 生成图片缩略图、截取图片局部内容
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月24日下午4:34:09
 **/
public class ImageUtil {

    private static Logger log = LoggerFactory.getLogger(ImageUtil.class);
    
    /**缩略图的前缀*/
    private static String DEFAULT_PREVFIX = "thumb_";
    
    private static String DEFAULT_CUT_PREVFIX = "cut_";
    
    /**不强制生成宽高缩略图*/
    private static Boolean DEFAULT_FORCE = false;
    
    /**
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    private static boolean thumbnailImage(File imgFile,File targetFile, int w, int h, String prevfix, boolean force){
    	Boolean success=Boolean.FALSE;
        if(imgFile.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return success;
                }
                log.debug("target image's size, width:{}, height:{}.",w,h);
                Image img = ImageIO.read(imgFile);
                
                //如果img为null，则用户上传的不是一张真正的图片.
                if(null==img) {
                	log.error("Sorry, This is not a picture.");
                	return success;
                }
                if(!force){
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            log.debug("change image's height, width:{}, height:{}.",w,h);
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            log.debug("change image's width, width:{}, height:{}.",w,h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                ImageIO.write(bi, suffix, targetFile);//new File(p.substring(0,p.lastIndexOf(File.separator)) + File.separator + prevfix +imgFile.getName())
                success=true;
            } catch (IOException e) {
            	success=false;
            }
        }else{
            log.warn("the image is not exist.");
        }
        return success;
    }
    
    /**
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param targetPath   目标图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static boolean thumbnailImage(String sourcePath,String targetPath, int w, int h, String prevfix, boolean force){
        File sourceFile = new File(sourcePath);
        File targetFile=new File(targetPath);
        return thumbnailImage(sourceFile,targetFile, w, h, prevfix, force);
    }
    
    /**
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param sourcePath    原图片路径
     * @param targetPath    目标图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static boolean thumbnailImage(String sourcePath,String targetPath, int w, int h, boolean force){
        return thumbnailImage(sourcePath, targetPath,w, h, DEFAULT_PREVFIX, force);
    }
    
    /**
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     */
    public static boolean thumbnailImage(String imagePath,String targetPath, int w, int h){
        return thumbnailImage(imagePath, targetPath,w, h, DEFAULT_FORCE);
    }
    
    public static boolean thumbnailImage(File sourceFile,String targetPath, int w, int h){
        File targetFile=new File(targetPath);
        return thumbnailImage(sourceFile,targetFile, w, h, DEFAULT_PREVFIX, DEFAULT_FORCE);
    }
    
    /**
     * <p>Description:  根据原图与裁切size截取局部图片</p>
     * @param srcImg    源图片
     * @param output    图片输出流
     * @param rect      需要截取部分的坐标和大小
     */
    private static boolean cutImage(File srcImg, OutputStream output, Rectangle rect){
    	Boolean success=Boolean.FALSE;
        if(srcImg.exists()){
            FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
            	Image img = ImageIO.read(srcImg);
            	if(null==img){
            		log.error("Sorry, This is not a picture.");
                	return success;
            	}
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if(srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return success;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis,true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);	
                ImageIO.write(bi, suffix, output);
                success=true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fis != null) fis.close();
                    if(iis != null) iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            log.warn("the src image is not exist.");
        }
        return success;
    }
    
    /**
     * <p>Description:  根据原图与裁切size截取局部图片</p>
     * @param srcImg    源图片
     * @param output    图片输出流
     * @param x         需要截取部分的x坐标
     * @param y 		需要截取部分的y坐标
     * @param width     需要截取图片的宽度
     * @param height    需要截取图片的高度
     */
    public static boolean cutImage(File srcImg, OutputStream output, int x, int y, int width, int height){
        return cutImage(srcImg, output, new java.awt.Rectangle(x, y, width, height));
    }
    
    /**
     * <p>Description:     根据原图与裁切size截取局部图片</p>
     * @param srcImg       源图片
     * @param destImgPath  截取后文件的路径
     * @param rect         需要截取部分的坐标和大小
     */
    public static boolean cutImage(File srcImg, String destImgPath, java.awt.Rectangle rect){
        File destImg = new File(destImgPath);
        if(destImg.exists()){
            String p = destImg.getPath();
            try {
                if(!destImg.isDirectory()) p = destImg.getParent();
                if(!p.endsWith(File.separator)) p = p + File.separator;
                return cutImage(srcImg, new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + srcImg.getName()), rect);
            } catch (FileNotFoundException e) {
                log.warn("the dest image is not exist.");
            }
        }else log.warn("the dest image folder is not exist.");
        return false;
    }
    
    /**
     * <p>Description:     根据原图与裁切size截取局部图片</p>
     * @param srcImg       源图片
     * @param destImgPath  截取后文件的路径
     * @param x         需要截取部分的x坐标
     * @param y 		需要截取部分的y坐标
     * @param width     需要截取图片的宽度
     * @param height    需要截取图片的高度
     */
    public static void cutImage(File srcImg, String destImg, int x, int y, int width, int height){
        cutImage(srcImg, destImg, new Rectangle(x, y, width, height));
    }
    
    /**
     * <p>Description:     根据原图与裁切size截取局部图片</p>
     * @param srcImg       源图片
     * @param destImgPath  截取后文件的路径
     * @param x            需要截取部分的x坐标
     * @param y 		      需要截取部分的y坐标
     * @param width        需要截取图片的宽度
     * @param height       需要截取图片的高度
     */
    public static void cutImage(String srcImg, String destImg, int x, int y, int width, int height){
        cutImage(new File(srcImg), destImg, new Rectangle(x, y, width, height));
    }
    
    
    //====================================   给图片添加水印及文字       ====================================
    
    
    
    
    public static void main(String[] args) throws IOException {
    	
    	String imagePath="F:/test/images/test.jpg";	//待处理的图片
    	String waterImagePath="F:/test/images/water.jpg";//待添加的水印图片
    	String text="水印测试";//待添加的文字
    	String outputImagePath="F:/test/images/output3.jpg";//处理完成后的图片出路径
    	
    	File file=new File(imagePath);
    	Image image=ImageIO.read(file);
    	
    	int width=image.getWidth(null);
    	int height=image.getHeight(null);
    	
    	
    	BufferedImage bufferImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D graphics2D= bufferImg.createGraphics();
    	
    	
    	graphics2D.drawString(text, width/2, height/2);
    	
    	graphics2D.dispose();
    	
    	FileOutputStream fos=new FileOutputStream(outputImagePath);
    	
    	ImageIO.write(bufferImg, "jpg", fos);
    	
    	fos.close();
    	
    	System.out.println("over.");
//        thumbnailImage("F:/test/xx.jpg","F:/test/x.jpg", 33, 33,true);
//        cutImage("F:/test/object.jpeg","F:/test", 250, 70, 300, 400);
    	
    	
    	
    }
	
}
