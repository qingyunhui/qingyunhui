package qing.yun.hui.common.utils.sftp;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月9日上午11:09:08
 **/
public class SFTPUtil {
	
		/**
		 * 
		 * ChannelSftp类是JSch实现SFTP核心类，它包含了所有SFTP的方法，如：
			put(...)：      文件上传
			get(...)：      文件下载
			cd(...)：       进入指定目录
			ls(...)：       得到指定目录下的文件列表
			rename(...)：   重命名指定文件或目录
			rm(...)：       删除指定文件
			mkdir(...)：    创建目录
			rmdir(...)：    删除目录
		 * 
		 * 步骤：
			1.根据Jsch创建Session;
			2.设置Session密码、超时时间和属性等；
			3.连接session；
			4.使用Session创建ChannelSftp通道；
			5.接下来就可以使用ChannelSftp进行各种操作了：如文件上传、文件下载；
			6.最后，关系各种资源：如Session、ChannelSftp等；
			其他：还可以设置监听器，监控文件上传和下载的进度；
		 * */

	 	Session session = null;
	    Channel channel = null;

	    private static final Logger LOG = LoggerFactory.getLogger(SFTPUtil.class);
	    
	    /**
	     * <p>根据给定 sftpDetails 获取ChannelSftp </p>
	     * @param sftpDetails 
	     * @param timeout
	     * @return ChannelSftp
	     * */
	    public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {
	        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
	        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
	        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
	        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);

	        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
	        if (port != null && !port.equals("")) {
	            ftpPort = Integer.valueOf(port);
	        }

	        JSch jsch = new JSch(); // 创建JSch对象
	        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
	        LOG.info("Session created.");
	        if (ftpPassword != null) {
	            session.setPassword(ftpPassword); // 设置密码
	        }
	                 
	        Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config); // 为Session对象设置properties
	        session.setTimeout(timeout); // 设置timeout时间
	        session.connect(); // 通过Session建立链接
	        LOG.info("Session connected.");

	        LOG.info("Opening Channel.");
	        channel = session.openChannel("sftp"); // 打开SFTP通道
	        channel.connect(); // 建立SFTP通道的连接
	        LOG.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + channel);
	        return (ChannelSftp) channel;
	    }
	    
	    /**
	     * <p>文件上传</p>
	     * @param sftpDetails
	     * @param src 待上传的文件
	     * @param dst 待输出的文件
	     * @param sftpEnum 传输模式
	     * */
	    public static void uploadFile(Map<String, String> sftpDetails,String src, String dst,SFTPEnum sftpEnum) throws Exception {
    	    SFTPUtil sftpUtil = new SFTPUtil();
    	    ChannelSftp chSftp = sftpUtil.getChannel(sftpDetails, 60000);
    	    // 使用这个方法时，dst可以是目录，当dst是目录时，上传后的目标文件名将与src文件名相同
    	    // ChannelSftp.RESUME：断点续传
    	    chSftp.put(src, dst, new MySftpProgressMonitor(), sftpEnum.getValue()); // 代码段2
    	    // 将本地文件名为src的文件输入流上传到目标服务器，目标文件名为dst。
	        chSftp.quit();
	        sftpUtil.closeChannel();
    	  }
	    
	    /**
	     * <p>获取sftp指定文件流</p>
	     * @param sftpDetail
	     * @param sourceFile 待下载的文件
	     * */
	    public static InputStream getSFTPFileInputStream(Map<String, String> sftpDetails,String sourceFile) throws Exception{
	    	SFTPUtil channel = new SFTPUtil();
	    	ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
	        try {
	        	SftpATTRS attr = chSftp.stat(sourceFile);
	        	long fileSize = attr.getSize();
	        	return chSftp.get(sourceFile, new MySftpProgressMonitor(fileSize)); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            chSftp.quit();
	            channel.closeChannel();
	        }
	        return null;
	    }

	    /**
	     * <p>关闭sftpChannel</p>
	     * */
	    public void closeChannel() throws Exception {
	        if (channel != null) {
	            channel.disconnect();
	        }
	        if (session != null) {
	            session.disconnect();
	        }
	    }
	    
	    public static SFTPUtil getSFTPChannel() {
	        return new SFTPUtil();
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	
	    	/******************文件上传**********************/
	    	
	    	/*Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "112.124.117.151");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "andy");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "qtvb520");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
	        String src="F:/test/test.exe";
	        String dst="/data/sftp/andy/upload/";
	        uploadFile(sftpDetails, src, dst, SFTPEnum.APPEND);*/
	    	
	    	/******************文件下载**********************/
	    	
	        
	    	
		}
}
