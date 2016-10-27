package qing.yun.hui.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import qing.yun.hui.common.constants.Symbol;

/***
 ** @Description: FileUtil 文件处理工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:19:21 AM
 ** @version: V1.0
 ***/
public class FileUtil {
	
	public static void main(String[] args){
		String path="G:/stop/pleasNoOpen/success/ok/success/";
		List<File> listFiles=FileUtil.getFilesByPath(path);
		String[] suffixs={".xml",".txt",".htm"};
		int count=FileUtil.resetFilesName(listFiles, suffixs);
		System.out.println("受影响的行数为:"+count+"条记录");
		
		//The.Dream.Of.Red.Mansions.E06.720p.HDTV.x264-NGB.mkv
		//天蝎.Scorpion.S01E03.中英字幕.HDTVrip.624X352.mp4
		//[迅雷下载www.2tu.cc]华胥引之绝爱之城24.mp4
		//盗墓笔记.The.Lost.Tomb.Season.1.E01.HD1080P.X264.AAC.Mandarin.CHS.Mp4Ba.mp4
		//花千骨.未删减.Ep01.2015.HD720P.X264.AAC.Mandarin.CHS.mp4
		
		/*String path="E:/迅雷下载/芈月传";
		List<File> listFiles=FileUtil.getFilesByPath(path);
		for(File file:listFiles){*///[迅雷下载www.2tu.cc]大舜HDTV14.mp4
			//file.renameTo(new File(modifyFileName(path, file.getPath(), "之城","华胥引之绝爱之城")));
			//【电影天堂-www.dy2018.com】楚汉传奇[HD版]01.rmvb
			//file.renameTo(new File(modifyFileName(path, file.getName(), "[HD版]","楚汉传奇",".rmvb")));
			//[迅雷下载www.xiamp4.com]芈Y传07.HDTV
			//file.renameTo(new File(modifyFileName(path, file.getName(), "芈Y传","芈月传",".mkv")));
			
			//[迅雷下载www.xiamp4.com]M月传58.HDTV
			/*String newFileName=modifyFileName(path, file.getName(), "芈月传月传传","芈月传",".mp4");
			if(StringUtil.isEmpty(newFileName)){
				continue;
			}
			file.renameTo(new File(newFileName));*/
		//}
	}
	/***
	 * E:/medio/TheDreamOfRedMansions/The.Dream.Of.Red.Mansions.E39.720p.HDTV.x264-NGB.mkv
	 * 改成类似这样:E:/medio/TheDreamOfRedMansions/newName39.mkv
	 * @param path 要操作的文件路径
	 * @param fileName 要操作的文件文件名
	 * @param findStr 要查找的字符串
	 * @param newName 定义新的名称
	 * @param defaultSuffix 默认后缀
	 * **/
	public static String modifyFileName(String path,String fileName,String findStr,String newName,String defaultSuffix){
		StringBuffer sb=new StringBuffer();
		String character=getCharacter(fileName, findStr);
		if(StringUtil.isEmpty(character)){
			return null;
		}
		String fileSuffix=getFileSuffix(fileName, defaultSuffix);
		sb.append(path).append(File.separatorChar).append(newName).append(character).append(fileSuffix);
		return sb.toString();
	}
	
	/**
	 * 盗墓笔记.The.Lost.Tomb.Season.1.E01.HD1080P.X264.AAC.Mandarin.CHS.Mp4Ba.mp4  调用getNumber(str,1.E)方法后返回的值为:01
	 * @param str 要操作的字符串
	 * @param findStr 要查找的字符
	 * @return 处理后的字符串
	 * **/
	public static String getCharacter(String str,String findStr){
		int beginIndex=str.indexOf(findStr);
		if(beginIndex==-1){
			return null;
		}
		str=str.substring(beginIndex+findStr.length());
		return str.substring(0,str.indexOf("."));
	}
	
	/**
	 * 根据给定的文件名,取其后缀(.xx),如果fileName为null或为空则取默认的后缀(defaultSuffix);
	 * @param fileName 要操作的文件名 
	 * @param defaultSuffix 默认后缀
	 * @return 截取后的后缀名
	 * **/
	public static String getFileSuffix(String fileName,String defaultSuffix){
		if(StringUtil.isEmpty(fileName)){
			return defaultSuffix;
		}
		String suffix=fileName.substring(fileName.lastIndexOf("."));
		return suffix;
	}
	
	
	
	/****
	 * 文件列表
	 * ****/
	private static  List<File> listFile=new ArrayList<File>();
	
	/***根据给定的文件后缀数组,随机得到其中某一个后缀。
	 * @param tagetSuffixs 文件后缀数组
	 * @return 文件后缀数组中的随机一个后缀。
	 * ***/
	public static String getRandomSuffix(String[] tagetSuffixs){
		Random random=new Random();
		return tagetSuffixs[random.nextInt(tagetSuffixs.length)];
	}
	
	/****
	 * 根据给定path递归该path下所有文件如果有子文件会把子文件夹下的文件一并找出来;
	 * @param path 磁盘或文件夹路径
	 * @return List<File> 返回指定文件夹下所有文件;
	 * *****/
	public static List<File> getFilesByPath(String path){
		if(StringUtil.isEmpty(path)){
			System.err.println("path:不能为空!");
			return null;
		}
		File file=new File(path);//file.isFile(如果是文件[E:/test/test.txt]返回true:否则返回false)
		if(!file.exists()){
			System.err.println("path:该文件夹不存或输入有误!");
			return null;
		}
		if(file.isDirectory()){
			for(File tmpFile:file.listFiles()){
				getFilesByPath(tmpFile.getPath());
			}
		}else{
			listFile.add(file);
		}
		return listFile;
	}
	
	/****
	 * 根据给定的sourceSuffixs文件后缀列表从listFile文件列表中找出符合条件的文件。
	 * @param listFile 文件列表
	 * @param sourceSuffixs 文件后缀名列表
	 * @return List<File> 返回符合条件的文件列表。 
	 * ***/
	public static List<File> getContainSuffixsFiles(List<File> listFile,String[] sourceSuffixs){
		if(null==sourceSuffixs|| sourceSuffixs.length<1){
			System.err.println("木有过滤。原因是给定的文件后缀列表为空。");
			return listFile;
		}
		if(null==listFile ||listFile.size()<1){
			System.err.println("文件列表木有文件。");
			return listFile;
		}
		List<File> curListFiles=new ArrayList<File>();
		for(File file:listFile){
			for(String suffix:sourceSuffixs){
				if(file.getPath().endsWith(suffix.trim())){
					curListFiles.add(file);
					continue;
				}
			}
		}
		return curListFiles;
	}
	
	/***
	 * 更改文件后缀;根据给定的suffixs后缀列表从listFiles文件列表中查找符合条件的文件并更改之。
	 * @param listFiles 待更改后缀名的文件列表。
	 * @param suffixs  后缀列表。
	 * @return 受影响的条数。
	 * ***/
	public static int resetFilesName(List<File> listFiles,String[] suffixs){
		if(null==suffixs || suffixs.length<1){
			return 0;
		}
		if(null==listFiles || listFiles.size()<1){
			return 0;
		}
		int count=0;
		for(File file:listFiles){
			StringBuffer sb=new StringBuffer();
			String path=file.getPath();
			sb.append(handelPath(path)).append(getRandomSuffix(suffixs));
			file.renameTo(new File(sb.toString()));
			count++;
		}
		return count;
	}
	
	/**
	 * 判断path 是滞包含点(.)如果包含,则从path下标0到最后一个.(点)的下标开始截取,如果不包含,最返回原path;
	 * @param path 要截取的字符串
	 * @return 截取后的字符串;
	 * **/
	public static String handelPath(String path){
		boolean contains=path.contains(".");
		if(contains){
			return path.substring(0,path.lastIndexOf("."));
		}
		return path;
	}
	
	/**
	 * 重置文件名;把listFiles文件列表中的文件包含sourceSuffixs后缀的文件改成以tagetSuffixs为后缀的文件。
	 * 如果sourceSuffixs为null或为空那么listFiles所有文件的后缀将以tagetSuffixs为后缀。
	 * @param listFiles 待更名的文件列表
	 * @param sourceSuffixs 待还更改的源文件后缀
	 * @param tagetSuffixs 新文件的后缀
	 * ***/
	public static void resetFilesNameByPath(List<File> listFiles,String[] sourceSuffixs,String[] tagetSuffixs){
		if(null==listFiles || listFiles.size()<1){
			System.err.println("木有更改成功!原因是:给定的文件列表为空。");
			return;
		}
		if(null==tagetSuffixs || tagetSuffixs.length<1){
			System.err.println("木有更改成功!原因是:给定的新文件后缀为空。");
			return;
		}
		int count=0;
		if(null==sourceSuffixs || sourceSuffixs.length<1){
			System.err.println("源文件后缀为空，系统默认以tagetSuffixs为后缀。");
			count=resetFilesName(listFiles, tagetSuffixs);
		}else{
			List<File> containListFiles=getContainSuffixsFiles(listFiles, sourceSuffixs);
			count=resetFilesName(containListFiles, tagetSuffixs);
		}
		System.out.println("受影响:"+count+"条。");
	}
	
	/***
	 * 给原有的文件名追加后缀名,如果原有文件已经包含后缀名、将会舍弃,系统会用给定的suffixs作为后缀。
	 * @param listFiles 待修改的文件列表。
	 * @param suffixs 待追加的后缀名列表
	 * ****/
	public static void appendFileSuffixs(List<File> listFiles,String[] suffixs){
		int count=0;
		if(null==listFiles || listFiles.size()<1){
			System.err.println("给定的文件列表为空,修改失败。");
			return;
		}
		if(null==suffixs || suffixs.length<1){
			System.err.println("给定的后缀名列表为空,修改失败。");
			return;
		}
		for(File file:listFiles){
			int countDot=file.getPath().lastIndexOf(Symbol.DOT);
			if(countDot!=-1){
				file.renameTo(new File(file.getPath().substring(0,file.getPath().lastIndexOf(Symbol.DOT))+getRandomSuffix(suffixs)));
				count++;
			}else{
				file.renameTo(new File(file.getPath()+getRandomSuffix(suffixs)));
				count++;
			}
		}
		System.out.println("修改成功了:"+count+"条数据。");
	}
	
	/**
	 * 根据给定的文件路径、删除指定文件
	 * @param path 文件绝对路径...
	 * **/
	public static void deleteFile(String path){
		if(StringUtil.isEmpty(path)){
			return;
		}
		File file=new File(path);
		if(!file.exists()){
			return;
		}
		file.delete();
	}
	
	/**
	  * 根据给定的字符(filePath)为其追加后缀(appenSuffix[.enc]),如果给定字符没有后缀则默认appenSuffix作为后缀.
	  * @param filePath 待处理的字符或文件名路径
	  * @param appenSuffix 待追加的后缀名
	  * @param defaultSuffix 默认后缀名(只有当filePath没有后缀的情况下才会触发)
	  * */
	 public static String getNewFilePath(String filePath,String appendSuffix,String defaultSuffix){
		 StringBuffer sb=new StringBuffer();
		 if(filePath.indexOf(".")!=-1){
			 String prefix=filePath.substring(0, filePath.lastIndexOf("."));//得到前缀
			 String suffix=filePath.substring(filePath.lastIndexOf("."),filePath.length());//得到后缀
			 if(filePath.indexOf(appendSuffix)!=-1){//如果文件名已经存在待追加的后缀，则不进行处理..
				 sb.append(prefix).append(suffix);
			 }else{
				 sb.append(prefix).append(appendSuffix).append(suffix);
			 }
		 }else{
			 sb.append(filePath).append(appendSuffix).append(defaultSuffix);
		 }
		 return sb.toString();
	 }
}
