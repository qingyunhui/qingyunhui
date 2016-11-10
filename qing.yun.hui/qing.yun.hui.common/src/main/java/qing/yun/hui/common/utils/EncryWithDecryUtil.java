package qing.yun.hui.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/***
 ** @category 适用于文件加解密(比如:文件、视频等等...)
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月10日下午11:33:54
 **/
public class EncryWithDecryUtil {
	
	/** 加密工具 */
	private static Cipher encryptCipher = null;

	/** 解密工具 */
	private static Cipher decryptCipher = null;
	
	/**加密前的明文key*/
	private static String key="admin";

	static{
		try {
			initialize_encryptKey(key);
			initalize_dencryptkey(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**初始化加密key*/
    private static void initialize_encryptKey(String keyValue) throws Exception{
        Key key = getKey(keyValue.getBytes());
		encryptCipher = Cipher.getInstance("DES");//DES/CBC/PKCS5Padding
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);//ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE
	}

    /**初始化解密key*/
    private static void initalize_dencryptkey(String keyValue) throws Exception {
        Key key = getKey(keyValue.getBytes());
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * @param arrBTmp 构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private static Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	/**
	 * 加密字节数组
	 * @param arrB  需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字节数组
	 * @param arrB  需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 文件file进行加密并保存目标文件destFile中
	 * @param file   要加密的文件 如c:/test/srcFile.txt
	 * @param destFile  加密后存放的文件名 如c:/加密后文件.txt
	 */
	public static void encrypt(String sourceFileName, String diminationFileName) throws Exception {
		InputStream is = new FileInputStream(sourceFileName);
		OutputStream out = new FileOutputStream(diminationFileName);
		CipherInputStream cis = new CipherInputStream(is, encryptCipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
	}
	
	public static void main(String[] args){
		//String sourceFileName="F:/test.mp4";
		String diminationFileName="F:/encrypt.mp4";
		String tmp="F:/test/jiemi.mp4";
		try {
			//encrypt(sourceFileName, diminationFileName);
			decrypt(diminationFileName, tmp);
		} catch (Exception e) {
		}
	}

	/**
	 * 文件采用DES算法解密文件
	 * @param sourceFileName  已加密的文件 如c:/加密后文件.txt * 
	 * @param diminationFileName 解密后存放的文件名 如c:/test/解密后文件.txt
	 */
	public static void decrypt(String sourceFileName, String diminationFileName) throws Exception {
		InputStream is = new FileInputStream(sourceFileName);
		OutputStream out = new FileOutputStream(diminationFileName);
		CipherOutputStream cos = new CipherOutputStream(out, decryptCipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			cos.write(buffer, 0, r);
		}
		cos.close();
		out.close();
		is.close();
	}
}
