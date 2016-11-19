package qing.yun.hui.common.utils;
/***
 ** @category RSA加密、解密、非对称(适应于加密的数据字节少于128字节的数据加密)
 ** @author qing.yunhui
 ** @date 2016年4月8日 下午2:33:19
 ***/
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtil {
	
	protected static final String ALGORITHM_RSA = "RSA";
	protected static String publicKey = null;
	protected static String privateKey = null;
	
	
	
	
	public static void main(String[] args) throws Exception {
		// 生成公钥和私钥
//		generatorKeyPair();
		String source = "500";
        System.out.println("加密前的数据：\r\n" + source);
        System.out.println("--------------------------公钥加密，私钥解密------------------------------");
        // 公钥加密
        String _publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWbYw374WwDCe+RmMFrFXYmiWz9W0AVKvCaVANsEKxXWPUDIgMQPMKeQB/0Flryt0RFlFcfaVCljbVhgwRDEEAe8WYABxXHiEfhoRhWUoVSx5Vu50fOnk4mZUM2Wo2jnBDR/ZU/wZh9YpbTYImy2cNGh5kqNk/ZRm4EI0yq1lgUwIDAQAB";
        String _privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZtjDfvhbAMJ75GYwWsVdiaJbP1bQBUq8JpUA2wQrFdY9QMiAxA8wp5AH/QWWvK3REWUVx9pUKWNtWGDBEMQQB7xZgAHFceIR+GhGFZShVLHlW7nR86eTiZlQzZajaOcENH9lT/BmH1iltNgibLZw0aHmSo2T9lGbgQjTKrWWBTAgMBAAECgYBGfl/Pdre3oHqfeGy/I9u2/cXdZ44FMaisGp4hTBB0/cbigFzhIS7EIaBSRVkiYpbmgwbtyRPA+JQJzB+rfYqX7Qb42t4AaeKiZUMnqGh73gJ/746DqCl9iMg78Vt3fCVzRd41WJmb2nR+C/ftjZOIybL8TPzDh5BVgEZdYwH9AQJBAOcKJvyaQYQQWj8YmzwC4/xnZFg/yVZW5iwGe363Et3gp+gdMvH/OWDI2ekYclsSSSlnEE7MYcSBVXgeakWTdjECQQCmrewY20e/yIQhKi62QAWTR4fxjEEYxYRa+H1E5iVLF+n3m9LXFtvcThI54qgwknY1s+DsA0JIvXxlp2rDAKnDAkEAtC7nvuLyq3MxXrnq/v9mHIfp6yU7+TjmY3lewLE4Zo/5gv2aqL6aWg/qWm61aK83HbICNuIQcXdIV9iomsBpUQJAU095Q89OpIBf8oe4A2YqozvMqiIVY4FOL34mduql10vjqNyc3N9TE2F+YKp2sJh0N1Fqae0TE3KKm/C7Py60qwJABss3wYwjsMjWkl6CGP5h+Jm7pIjM0AhWu5Dm+779twjhGuxPmXqpkOmzagsAPqWShJAgINeuPDfNJgFfzsUZHg==";
		String target = encryptionByPublicKey(_publicKey,source);
		System.out.println("加密后的数据:"+target);
        // 私钥解密
		String _target= decryptionByPrivateKey(_privateKey,target);
		System.out.println("解密后的数据:"+_target);
	}
	
	/**
	 * 生成密钥对
	 * @throws Exception
	 */
	protected static void generatorKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        byte[] keyBs = rsaPublicKey.getEncoded();
        publicKey = encodeBase64(keyBs);
        System.out.println("生成的公钥：\r\n" + publicKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyBs = rsaPrivateKey.getEncoded();
        privateKey = encodeBase64(keyBs);
        System.out.println("生成的私钥：\r\n" + privateKey);
	}
	
	/**
	 * 获取公钥
	 * @param publicKey 公钥
	 * @return base64加密后的公钥
	 * @throws Exception
	 */
	protected static PublicKey getPublicKey(String publicKey) throws Exception {
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(publicKeySpec);
	}
	
	/**
	 * 获取私钥
	 * @param privateKey 私钥
	 * @return base64解密后的私钥
	 * @throws Exception
	 */
	protected static PrivateKey getPrivateKey(String privateKey) throws Exception {
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
	}
	
	/**
	 * <p>公钥加密，须要注意的是RSA加密只支付最大加密字节长度为117而解密最大字节只支持128长度</p>
	 * <p>如果加解密的字节超过支持的长度，可以分块进行加解密。</p>
	 * @param publicKey 公钥
	 * @param source 待加密的数据源
	 * @return String 加密后的数据
	 * */
	public static String encryptionByPublicKey(String publicKey,String source) throws Exception{
		PublicKey _publicKey = getPublicKey(publicKey);
        Cipher cipher = Cipher.getInstance(_publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, _publicKey);
        byte[] bytes=source.getBytes("UTF-8");
        cipher.update(bytes);
        String target = encodeBase64(cipher.doFinal());
		return target;
	}
	

	/**
	 * 私钥解密
	 * <p>公钥加密，须要注意的是RSA加密只支付最大加密字节长度为117而解密最大字节只支持128长度</p>
	 * <p>如果加解密的字节超过支持的长度，可以分块进行加解密。</p>
	 * @param privateKey 私钥
	 * @param target 待解密的数据源
	 * @throws Exception
	 */
	public static String decryptionByPrivateKey(String privateKey,String target) throws Exception {
		PrivateKey _privateKey = getPrivateKey(privateKey);
        Cipher cipher = Cipher.getInstance(_privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, _privateKey);
        cipher.update(decodeBase64(target));
        String source = new String(cipher.doFinal(), "UTF-8");
        return source;
	}
	
	/**
	 * base64编码
	 * @param source
	 * @return
	 * @throws Exception
	 */
	protected static String encodeBase64(byte[] source) throws Exception{
		return new String(Base64.encodeBase64(source), "UTF-8");
	}
	
	/**
	 * Base64解码
	 * @param target
	 * @return
	 * @throws Exception
	 */
	protected static byte[] decodeBase64(String target) throws Exception{
		return Base64.decodeBase64(target.getBytes("UTF-8"));
	}
}
