package qing.yun.hui.common.utils.decrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;
 
public class Test {
	
    static String publicKey;	//公钥
    static String privateKey;	//私钥
    static String pubKeyPath="F:/test/publicKey.txt";//公钥文件存储路径
    static String privateKeyPath="F:/test/source/privateKey.txt";//私钥文件存储放路径
    static String waitEncFilePath="F:/test/source/test.txt";//待加密文件的存储路径
    static String encFilePath="F:/test/source/enc.txt";//加密后的文件存储路径
    static String decFilePath="F:/test/source/dec.txt";//解密后的文件存储路径
    public Test() throws Exception {
        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        publicKey = RSAUtils.getPublicKey(keyMap);
        privateKey = RSAUtils.getPrivateKey(keyMap);
        // 保存密钥，名字分别为publicKey。txt 和privateKey。txt;
        PrintWriter pw1 = new PrintWriter(new FileOutputStream(pubKeyPath));
        PrintWriter pw2 = new PrintWriter(new FileOutputStream(privateKeyPath));
        pw1.print(publicKey);
        pw2.print(privateKey);
        pw1.close();
        pw2.close();
 
        // 从保存的目录读取刚才的保存的公钥，
        String pubkey = readFile(pubKeyPath);// 读取的公钥内容；
        String waitEncData = readFile(waitEncFilePath); // 需要公钥加密的文件的内容(如F:/test/test.mp4)
        byte[] waitEncDataBytes = RSAUtils.encryptByPublicKey(waitEncData.getBytes(),pubkey);
        //将加密数据base64后写入文件 ， 加密后的文件保存在
        writeFile(encFilePath, Base64Utils.encode(waitEncDataBytes).getBytes("UTF-8"));
        
        
 
        String prikey = readFile(privateKeyPath);// 从保存的目录读取刚才的保存的私钥，
        String encData = readFile(encFilePath);// 刚才加密的文件的内容;
        byte[] encDataBytes = Base64Utils.decode(encData);
        byte[] decDataBytes = RSAUtils.decryptByPrivateKey(encDataBytes, prikey);
        // 解密后后的文件保存在F:/test/dec.mp4
        writeFile(decFilePath, decDataBytes);
    }
 
    private static String readFile(String filePath) throws Exception {
        File inFile = new File(filePath);
        long fileLen = inFile.length();
        Reader reader = new FileReader(inFile);
        char[] content = new char[(int) fileLen];
        reader.read(content);
        System.out.println("读取到的内容为：" + new String(content));
        reader.close();
        return new String(content);
    }
 
    private static void writeFile(String filePath, byte[] content)throws Exception {
        System.out.println("待写入文件的内容为：" + new String(content));
        File outFile = new File(filePath);
        OutputStream out = new FileOutputStream(outFile);
        out.write(content);
        if (out != null) out.close();
    }
 
    public static void main(String[] args) throws Exception {
        new Test();
    }
}