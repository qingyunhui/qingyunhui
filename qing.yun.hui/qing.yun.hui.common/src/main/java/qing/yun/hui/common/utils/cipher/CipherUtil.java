package qing.yun.hui.common.utils.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import qing.yun.hui.common.enums.CipherCode;

/***
 ** @Description: Cipher(加密算法) 工具类>>>对称加密
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: 2016年4月9日 下午1:20:08
 ** @version: V1.0
 ***/
public class CipherUtil {

	 public static Cipher encrypt;	//加密对象
	 public static Cipher descrypt;//解密对象
	 static String formation="DES";//DES/CBC/PKCS5Padding>>可选转换的 Cipher对象
	 
	 static {
		 //ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE
		 encryptInit(formation,Cipher.ENCRYPT_MODE,CipherCode.QING_YUN_HUI);
		 descryptInit(formation,Cipher.DECRYPT_MODE,CipherCode.QING_YUN_HUI);
	 }
	 
	 /**
	  * 根据给定的字符转换指定length位的字节数组..
	  * @param myKey
	  * @param length
	  * @return byte[] 转换后的byte[]
	  * */
	 public static byte[] getBytes(String myKey,int length){
		 byte[] bytes=new byte[length];
		 byte[] myKeyBytes=myKey.getBytes();
		 for(int i=0;i<bytes.length && i<myKeyBytes.length;i++){
			 bytes[i]=myKeyBytes[i];
		 }
		 return bytes;
	 }
	 
	 /**
	  * 实例化Cipher
	  * @param transformation
	  * @param opmode
	  * @param cipherCode
	  * */
	 public static void encryptInit(String transformation,int opmode,CipherCode cipherCode){
		 Key key=new SecretKeySpec(getBytes(cipherCode.getKey(),8), transformation);
		 try {
			 encrypt=Cipher.getInstance(transformation);
			 encrypt.init(opmode,key);
		} catch (Exception e) {
			System.err.println(e);
		} 
	 }
	 
	 /**
	  * 实例化Cipher
	  * @param transformation
	  * @param opmode
	  * @param myKey
	  * */
	 public static void descryptInit(String transformation,int opmode,CipherCode cipherCode){
		 Key key=new SecretKeySpec(getBytes(cipherCode.getKey(),8), transformation);
		 try {
			 descrypt=Cipher.getInstance(transformation);
			 descrypt.init(opmode,key);
		} catch (Exception e) {
			System.err.println(e);
		} 
	 }
	 
	 public static void decrypt(String filePath) throws Exception{
		 
		 File file=new File(filePath);
		 String outFilePath="F:/test/source/source-dec.txt";
		 FileInputStream in=new FileInputStream(file);
		 FileOutputStream ou=new FileOutputStream(outFilePath);
		 
		 byte[] byts=new byte[1024];
		 int red=0;
		 while((red=in.read(byts))!=-1){
			 ou.write(descrypt.doFinal(byts, 0, red));
		 }
		 
		 in.close();
		 ou.close();
	 }
	 
	 public static void main(String[] args){
//		 String filePath="F:/test/source/source2.txt";
		 try {
//			 decrypt(filePath);
			 test1();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		 
		/* String source="F:/test/source/source.txt";
		 File file=new File(source);
		 FileOutputStream out=null;
		 FileInputStream in=null;
		 byte[] byts=new byte[1024];
		 String source2="F:/test/source/source2.txt";
		 int bt;
		 try {
			 in=new FileInputStream(file);
			 out=new FileOutputStream(source2);
			 while((bt=in.read(byts))>0){
				 out.write(encrypt.doFinal(byts,0,bt));
			 }
		} catch (Exception e) {
		}finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	 }
	 
	 public static void encryptByFile(String source){
		 FileOutputStream out=null;
		 String str="你大爷的abcd12345what?how are you!";
		 File outFile=new File(source);
		 try {
			 out=new FileOutputStream(outFile);
			 if(outFile.exists()){
				 return;
			 }
			 byte[] encBytes=str.getBytes();
			 out.write(encBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 }
	 
	 /**测试-仅是对字符串进行加解密操作*/
	 public static void test1(){
		 String test="你好啊，美女.";
		 try {
			 byte[] encBytes=encrypt.doFinal(test.getBytes());
			 printMsgln("加密后:",new String(encBytes));
			 System.out.println("--------------------------------------");
			 byte[] desBytes=descrypt.doFinal(encBytes);
			 String desPwd=new String(desBytes);
			 printMsgln("解密后",desPwd);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 }
	 
	/**
	 * 根据给定的字节数组转换成十六进制
	 * @param bytes 待转换的字节数组
	 * @return String 转换后的十六进制..
	 * */
	public static String byteToHex(byte[] bytes) {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			/*Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
			例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
			和0xff相与后，高24比特就会被清0了，结果就对了。*/
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	 public static void printMsgln(String msg,Object data){
		 System.out.println("-----"+msg+"-----\n"+data);
	 };
}
