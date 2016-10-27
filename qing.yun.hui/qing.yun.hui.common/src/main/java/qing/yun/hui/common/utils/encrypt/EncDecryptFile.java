package qing.yun.hui.common.utils.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/***
 ** @category 功能说明
 ** @author qing.yunhui
 ** @date 2016年4月15日 上午10:17:32
 ***/
public class EncDecryptFile {
	
	static Cipher cipher;
	static SecretKey key;
	public static void decryptFile(String filePath){
		String outPath="F:/test/source/demo/source-dec.txt";
		File file=new File(filePath);
		try {
			FileInputStream in=new FileInputStream(file);
			FileOutputStream ou=new FileOutputStream(outPath);
			key=KeyGenerator.getInstance("AES").generateKey();
			cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] byts=new byte[1024];
			int b=0;
			while((b=in.read(byts))!=-1){
				ou.write(cipher.doFinal(byts,0,b));
			}
			in.close();
			ou.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void encryptFile(String filePath){
		String outPath="F:/test/source/demo/source-enc.txt";
		File file=new File(filePath);
		try {
			FileInputStream in=new FileInputStream(file);
			FileOutputStream ou=new FileOutputStream(outPath);
			key=KeyGenerator.getInstance("AES").generateKey();
			cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byts=new byte[1024];
			int b=0;
			while((b=in.read(byts))!=-1){
				ou.write(cipher.doFinal(byts,0,b));
			}
			in.close();
			ou.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		//String filePath="F:/test/source/demo/source.txt";
		//encryptFile(filePath);
		
		String filePath2="F:/test/source/demo/source-enc.txt";
		decryptFile(filePath2);
	}
}
