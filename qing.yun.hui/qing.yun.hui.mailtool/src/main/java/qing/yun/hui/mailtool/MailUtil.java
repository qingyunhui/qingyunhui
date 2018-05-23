package qing.yun.hui.mailtool;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 ** @category 邮件工具类...
 ** @author qing.yunhui
 ** @email: qingyunhui@fentuan360.com
 ** @createTime: 2018年4月25日上午9:26:43
 **/
public class MailUtil {

	private static final String USER_KEY="mail.user";
	private static final String PASSWORD_KEY="mail.password";
	private static final String MAIL_HOST_KEY="mail.host";
	
	private static Logger logger=LoggerFactory.getLogger(MailUtil.class);
	
	/**
	 * @param prop
	 * @param system true(取项目配置文件的邮箱信息)、false(取数据库配置的邮箱信息)
	 * @param subject 主题
	 * @param content 内容
	 * @param sendEmails 接收的邮箱列表 
	 * **/
    public static Boolean sendMail(Properties prop,boolean system, String subject, String content,String...sendEmails){
    	Boolean success=Boolean.FALSE;
    	Transport ts =null;
    	try {
    		if(null==prop){
    			throw new Exception("邮件发送失败，失败原因：Properties cannot be null.");
        	}
            Session session = Session.getInstance(prop);//1、创建session
//            session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            ts = session.getTransport(); //2、通过session得到transport对象
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
//            String host=system?prop.getProperty(MAIL_HOST_KEY).trim():Global.getValue(MAIL_HOST_KEY);
            String host=prop.getProperty(MAIL_HOST_KEY).trim();
//            String user=system?prop.getProperty(USER_KEY).trim():Global.getValue(USER_KEY);
            String user=prop.getProperty(USER_KEY).trim();
//            String password=system?prop.getProperty(PASSWORD_KEY).trim():Global.getValue(PASSWORD_KEY); 
            String password=prop.getProperty(PASSWORD_KEY).trim(); 
            ts.connect(host, user, password);
            MimeMessage message = new MimeMessage(session);//4、创建邮件
            message.setFrom(user); //指明邮件的发件人
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            String[] addresses=sendEmails;
            InternetAddress[] internetAddress = new InternetAddress[addresses.length];
            for(int i=0; i<internetAddress.length; i++){
            	internetAddress[i] = new InternetAddress(addresses[i].trim());
            }
            message.setRecipients(Message.RecipientType.TO, internetAddress);//设置接收者
            message.setSubject(subject);//邮件的标题
            //这里加上了:outlook的外壳，防止被邮箱认为是垃圾邮箱的处理.
            message.setHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setSentDate(new Date());//发送时间  
            message.setContent(content, "text/html;charset=UTF-8");//邮件的文本内容
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            success=Boolean.TRUE;
		} catch (Exception e) {
			success=Boolean.FALSE;
			logger.error("【邮件发送】",e);
		}finally{
			if(null!=ts){
				try {
					ts.close();
				} catch (MessagingException e) {
					return success;
				}
			}
		}
    	return success;
    }
    
    /**
     * 发送邮件
     * @param subject 主题
     * @param content 内容
     * @param sendEmails 接收对象(邮箱)
     * @param system true(取项目配置文件的邮箱信息)、false(取数据库配置的邮箱信息)
     * @return 发送成功标识
     * */
    public static Boolean sendMail(String subject,String content,boolean system,String... sendEmails) {
    	long startTime=System.currentTimeMillis();
    	logger.info("【邮件发送】开始...");
    	Properties prop = new Properties();
    	Boolean success=false;
    	try {
			prop.load(ClassLoader.getSystemResourceAsStream("mail.properties"));
		} catch (Exception e) {
			logger.error("【邮件发送】系统文件不存在、请忽略...",e);
	        prop.setProperty("mail.transport.protocol", "smtp");
	        prop.setProperty("mail.smtp.port", "465"); 
//	        prop.setProperty("mail.smtp.socketFactory.port", "25");
	        prop.setProperty("mail.smtp.auth", "true");
	        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        prop.setProperty("mail.smptp.ssl.enable", "true");
	        prop.setProperty("mail.smtp.starttls.enable", "true");
		}finally {
			try {
				success=sendMail(prop,system,subject,content,sendEmails);
				long endTime=System.currentTimeMillis();
				logger.info("【邮件发送】结束,总耗时:{}毫秒",new Object[]{endTime-startTime});
			} catch (Exception e) {
				sendMail(prop,system,subject,content,sendEmails);//发送失败，再尝试一次.
				logger.error("邮件发送失败，失败原因：",e);
			}
		}
		return success;
    }
    
}
