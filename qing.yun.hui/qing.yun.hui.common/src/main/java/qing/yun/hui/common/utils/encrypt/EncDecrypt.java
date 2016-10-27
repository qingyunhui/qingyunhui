package qing.yun.hui.common.utils.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/***
 ** @category 加解密
 ** @author qing.yunhui
 ** @date 2016年4月15日 上午9:45:26
 ***/
public class EncDecrypt {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		 //**************************加密******************************
        
        //Cipher：提供加密的类，"AES" 表示加密使用的算法
        Cipher cipher = Cipher.getInstance("AES");
         
        //SecretKey密码生成器，用于生成一个密钥
        SecretKey key = KeyGenerator.getInstance("AES").generateKey();
         
        //用密钥初始化此 Cipher，   Cipher.ENCRYPT_MODE 加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
         
        //对"aaa"执行加密的操作,返回值为加密后的结果
        byte[] result = cipher.doFinal("aaa".getBytes());
         
        System.out.println(new String(result));
         
        //**************************解密******************************
         
        //解密的时候，要用原来加密的密钥
        cipher.init(Cipher.DECRYPT_MODE, key);
         
         //解密，得到加密前的字符串
         result = cipher.doFinal(result);
          
         System.out.println(new String(result));
	}
	
}
