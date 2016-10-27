package qing.yun.hui.common.utils.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import qing.yun.hui.common.constants.Symbol;
import qing.yun.hui.common.enums.CipherCode;
import qing.yun.hui.common.enums.CipherDigest;
import qing.yun.hui.common.enums.ReturnType;
import qing.yun.hui.common.utils.MD5Util;
import qing.yun.hui.common.utils.StringUtil;

/***
 ** @Description: RandomAccessFile类加密解密处理  
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: 2016年4月12日 下午4:47:15
 ** @version: V1.0
 ***/
public class RandomAccessFileUtil {

	static String filePath="F:/test/source/test.mp4";
	public static void main(String[] args){
		System.err.println(MD5Util.getMD5Encryption(CipherCode.QING_YUN_FEI.getKey()));
		try{
			boolean ensucess=encrypt(CipherCode.QING_YUN_FEI,filePath);	//加密
			if(ensucess){
				System.out.println("加密成功.");
			}
			boolean desucess=decrypt(CipherCode.QING_YUN_FEI,filePath);//解密
			if(desucess){
				System.out.println("解密成功.");
			}
		}catch(Exception e){
			System.err.println(e.fillInStackTrace());
		}
	}
	
	/**
	 * 文件加密
	 * @param cipherCode 密钥
	 * @param filePath 待加密的文件
	 * @param tmpSuffix 临时后缀
	 * @return 成功or失败
	 */
	static boolean encrypt(CipherCode cipherCode,String filePath) {
		ReturnType type=exists(filePath);
		if(type.getValue()!=ReturnType.IS_OK.getValue()){
			System.err.println(type.getName());
			return false;//目标为空 or 目标不存在 
		}
		RandomAccessFile randomAcsFile=null;
		try{
			randomAcsFile=new RandomAccessFile(filePath,"rw");
			CipherDigest digest=encrypted(randomAcsFile);
			if(CipherDigest.UN_ENCRYPT.getValue()!=digest.getValue()){
				System.err.println(digest.getName());
				return false;//这里已经确定已经加密过了..
			}
			beginEncrypt(randomAcsFile,cipherCode,filePath);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally{
			try{
				randomAcsFile.close();
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * 文件解密
	 * @param cipherCode 密钥
	 * @param filePath 待解密的文件
	 * @return 成功 or 失败
	 * **/
	static boolean decrypt(CipherCode cipherCode,String filePath) {
		boolean success=true;
		ReturnType type=exists(filePath);
		if(type.getValue()!=ReturnType.IS_OK.getValue()){
			System.err.println(type.getName());
			return success=false;//目标为空 or 目标不存在 
		}
		String md5Key=MD5Util.getMD5Encryption(cipherCode.getKey());
		int keyLength=md5Key.length();
		RandomAccessFile randomAcsFile=null;
		RandomAccessFile tmpRandomAcsFile=null;
		File tmpFile=new File(getNewFilePath(filePath,".tmp"));
		File file=new File(filePath);
		try{
			randomAcsFile=new RandomAccessFile(filePath,"rw");
			CipherDigest digest=encrypted(randomAcsFile);
			if(CipherDigest.UN_ENCRYPT.getValue()==digest.getValue()){
				//没有加密，就不须要再进行解密了..
				System.err.println(CipherDigest.UN_ENCRYPT.getName());
				return success=false;
			}
			long fileLength=randomAcsFile.length();
			if(fileLength<keyLength){
				System.err.println("解密失败!原因是文件长度还没有密钥长度长.");
				return success=false;
			}
			StringBuffer sb=new StringBuffer();
			for(int i=keyLength;i>0;i--){
				randomAcsFile.seek(fileLength-i);
				sb.append((char)randomAcsFile.read());//拿到密钥
			}
			//判断解密的密钥是否匹配.  
			if(!md5Key.equals(sb.toString())){
				System.err.println("密码不匹配。");
				return success=false;
			}
			//开始解密  
			tmpRandomAcsFile=new RandomAccessFile(tmpFile,"rw");
			System.out.println("\n开始解密.....\n");
			for(long i=0;i<fileLength-keyLength;i++){
				randomAcsFile.seek(i);
				tmpRandomAcsFile.seek(i);
				tmpRandomAcsFile.write((char)randomAcsFile.read());
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally{
			try{
				randomAcsFile.close();
				if(null!=tmpRandomAcsFile){
					tmpRandomAcsFile.close();
				}
				if(success){
					file.delete();
					tmpFile.renameTo(file);
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * 开始加密  
	 * @param randomAcsFile 要操作的文件
	 * @param cipherCode 密钥
	 * @param filePath 加密文件的路径
	 * @return 成功 or 失败
	 * */
	static boolean beginEncrypt(RandomAccessFile randomAcsFile,CipherCode cipherCode,String filePath){
		 InputStream is=null;
		 try {
			 is = new FileInputStream(filePath);
			 randomAcsFile.seek(randomAcsFile.length());
			 randomAcsFile.writeBytes(MD5Util.getMD5Encryption(cipherCode.getKey()));//TODO 这里对md5加密后的十六进制数据在进行了byte的转换.所以解密时..
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	  * 根据给定的字符(filePath)为其追加后缀(appenSuffix[.enc]),如果给定字符没有后缀则默认appenSuffix作为后缀.
	  * @param filePath 待处理的字符或文件名路径
	  * @param tmpSuffix 待追加的后缀名
	  * @return 返回新的文件名
	  * */
	 public static String getNewFilePath(String filePath,String tmpSuffix){
		 StringBuffer sb=new StringBuffer();
		 if(filePath.indexOf(Symbol.DOT)!=-1){
			 String prefix=filePath.substring(0, filePath.lastIndexOf("."));//得到前缀
			 String suffix=filePath.substring(filePath.lastIndexOf("."),filePath.length());//得到后缀
			 if(filePath.indexOf(tmpSuffix)!=-1){//如果文件名已经存在待追加的后缀，则不进行处理..
				 sb.append(prefix).append(suffix);
			 }else{
				 sb.append(prefix).append(tmpSuffix).append(suffix);
			 }
		 }else{
			 sb.append(filePath).append(tmpSuffix);
		 }
		 return sb.toString();
	 }
	
	/**
	 * 判断是否已经加密
	 * @param randomAcsFile
	 * @param key
	 * @return CipherDigest
	 * */
	static CipherDigest encrypted(RandomAccessFile randomAcsFile) throws Exception{
		long fileSize=randomAcsFile.length();
		//a.判断是否已经加密过  ,a1.如果已经加密则给予提示、a2.如果未加密则进行加密
		boolean encrypt=false;
		for(CipherCode code:CipherCode.values()){
			String md5Key=MD5Util.getMD5Encryption(code.getKey());
			StringBuffer sbf1=new StringBuffer();
			if(fileSize>=md5Key.length()){
				for(long i=md5Key.length();i>0;i--){
					randomAcsFile.seek(fileSize-i);
					sbf1.append((char)randomAcsFile.read());
				}
				if(md5Key.equals(sbf1.toString())){
					encrypt=true;
					break;
				}
			}
		}
		if(!encrypt){ 
			return CipherDigest.UN_ENCRYPT;
		}
		return CipherDigest.ENCRYPTED;
	}

	/**
	 * 判断文件是否存在
	 * @param filePath  文件路径
	 * @return 返回类型:对应枚举
	 */
	static ReturnType exists(String filePath) {
		if (StringUtil.isEmpty(filePath)) {return ReturnType.IS_EMPTY;}
		File file = new File(filePath);
		if (!file.exists()) {return ReturnType.UN_EXIST;}
		return ReturnType.IS_OK;
	}
}
