package qing.yun.hui.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import qing.yun.hui.common.constants.SymbolConstant;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Jan 24, 2016 10:31:25 AM
 ** @version: V1.0
 ***/
public class FileTool {
	
	private static List<File> fileList=null;//文件列表集合
	private static String path="F:/test/";
	protected static String[] suffixs=new String[]{".mp4"};//文件后缀..
	
	//random.nextInt(26) + 65;  //生成ascll码在:65-90之间的小写字母...
	//random.nextInt(26) + 97; //生成ascll码在:97-122之间的大写字母...
	
	public static void main(String[] args){
		resetFileNames(fileList,suffixs,true);
	}
	
	static{
		if(null==fileList){
			fileList=new ArrayList<File>();
			init(path);
		}
	}
	
	/**
	 * 初始化加载指定路径下的文件、并存储到fileList中
	 * @param 待加载的path
	 * **/
	protected static void init(String path){
		if(StringUtil.isEmpty(path)){
			printError("path:"+path+",不能为空。");
			return;
		}
		File file=new File(path);
		if(!file.exists()){
			printError("path:"+path+",不存在。");
			return;
		}
		getFiles(fileList,file);
	}
	
	/**
	 * 递归遍历File 中所有file(包括其目录下的所有子目录在内的所有文件..)
	 * @param fileList 用来存储所有文件列表
	 * @param file 待递归的file
	 * **/
	protected static void getFiles(List<File> fileList,File file){
		if(file.isDirectory()){
			for(File curFile:file.listFiles()){
				getFiles(fileList,curFile);
			}
		}else{
			System.out.println("file:"+file.getParent()+":\t"+file.getName());
			fileList.add(file);
		}
	}
	
	/**
	 * @param 文件列表集合
	 * @param suffixs 待追加的文件后缀..
	 * @param reset 标记是否重置文件名（如果为true:则会随机生成文件名）
	 * */
	public static void resetFileNames(List<File> fileList,String[] suffixs,boolean reset){
		if(reset){
			if(null==fileList||fileList.size()<1){
				printError("文件列表为空!");
				return;
			}
			for(File file:fileList){
				String parentPath=file.getParent();
				String newFileName=getRandomStr(6)+getRandomSuffix(suffixs);
				file.renameTo(new File(parentPath+File.separator+newFileName));
			}
		}else{
			for(File file:fileList){
				String parentPath=file.getParent();
				String fileName=file.getName();
				String newFileName=null;
				if(fileName.lastIndexOf(SymbolConstant.DOT)!=-1){
					newFileName=fileName.substring(0, fileName.lastIndexOf(SymbolConstant.DOT));
					newFileName+=getRandomSuffix(suffixs);
					file.renameTo(new File(parentPath+File.separator+newFileName));
				}else{
					newFileName+=fileName+getRandomSuffix(suffixs);
					file.renameTo(new File(parentPath+File.separator+newFileName));
				}
			}
		}
	}
	
	protected static String getRandomSuffix(String[] suffixs){
		Random random=new Random();
		int x=random.nextInt(suffixs.length);
		return suffixs[x];
	}
	
	/**
	 * 随机生成a-zA-z0-9等字样的字符（ 每执行一次count就会生成a-z，A-z，0-9三个随机字符..）
	 * @param 生成字符的次数(3*count)
	 * @return 随机生成后的字符串..
	 * **/
	protected static String getRandomStr(int count){
		Random random=new Random();
		StringBuilder stb=new StringBuilder();
		for(int i=0;i<random.nextInt(2)+count;i++){
			int minChar=random.nextInt(26)+65;//生成ascll码在:65-90之间的小写字母...
			int maxChar=random.nextInt(26)+97;//生成ascll码在:97-122之间的大写字母...
			int number=random.nextInt(10);
			Object[] objs={(char)minChar,(char)maxChar,number};
			StringBuffer sb=new StringBuffer();
			for(int k=0;k<objs.length;k++){
				int m=random.nextInt(objs.length)+1;
				if(m>=objs.length){
					m=objs.length-1;
				}
				sb.append(objs[m]);
			}
			stb.append(sb.toString());
		}
		return stb.toString();
	}
	
	/**
	 * 消息打印
	 * @param msg 要打印的消息
	 * */
	protected static void printError(String msg){
		System.err.println(msg);
	}
}
