package qing.yun.hui.mailtool;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import qing.yun.hui.common.utils.RSAUtil;

 
public class MailTool {
	
	private static String USER_KEY="user";
	private static String PASSWORD_KEY="password";
	private static String MAIL_HOST_KEY="mail.host";
	
	//公钥key
	protected static final String _publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWbYw374WwDCe+RmMFrFXYmiWz9W0AVKvCaVANsEKxXWPUDIgMQPMKeQB/0Flryt0RFlFcfaVCljbVhgwRDEEAe8WYABxXHiEfhoRhWUoVSx5Vu50fOnk4mZUM2Wo2jnBDR/ZU/wZh9YpbTYImy2cNGh5kqNk/ZRm4EI0yq1lgUwIDAQAB";
    
	//私钥key
	protected static final String _privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZtjDfvhbAMJ75GYwWsVdiaJbP1bQBUq8JpUA2wQrFdY9QMiAxA8wp5AH/QWWvK3REWUVx9pUKWNtWGDBEMQQB7xZgAHFceIR+GhGFZShVLHlW7nR86eTiZlQzZajaOcENH9lT/BmH1iltNgibLZw0aHmSo2T9lGbgQjTKrWWBTAgMBAAECgYBGfl/Pdre3oHqfeGy/I9u2/cXdZ44FMaisGp4hTBB0/cbigFzhIS7EIaBSRVkiYpbmgwbtyRPA+JQJzB+rfYqX7Qb42t4AaeKiZUMnqGh73gJ/746DqCl9iMg78Vt3fCVzRd41WJmb2nR+C/ftjZOIybL8TPzDh5BVgEZdYwH9AQJBAOcKJvyaQYQQWj8YmzwC4/xnZFg/yVZW5iwGe363Et3gp+gdMvH/OWDI2ekYclsSSSlnEE7MYcSBVXgeakWTdjECQQCmrewY20e/yIQhKi62QAWTR4fxjEEYxYRa+H1E5iVLF+n3m9LXFtvcThI54qgwknY1s+DsA0JIvXxlp2rDAKnDAkEAtC7nvuLyq3MxXrnq/v9mHIfp6yU7+TjmY3lewLE4Zo/5gv2aqL6aWg/qWm61aK83HbICNuIQcXdIV9iomsBpUQJAU095Q89OpIBf8oe4A2YqozvMqiIVY4FOL34mduql10vjqNyc3N9TE2F+YKp2sJh0N1Fqae0TE3KKm/C7Py60qwJABss3wYwjsMjWkl6CGP5h+Jm7pIjM0AhWu5Dm+779twjhGuxPmXqpkOmzagsAPqWShJAgINeuPDfNJgFfzsUZHg==";

	
	public static void main(String[] args) {
		String subject="测试";
		String content="预警";
		String[] sendEmails=new String[]{"280672161@qq.com","qingyunhui@fentuan360.com"};
		try {
			sendMail(subject, content, sendEmails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * @param prop【N】  可以为null，如果为null，则会new 一个Properties.
	 * @param subject【y】 主题
	 * @param content【y】 内容
	 * @param sendEmails 接收的邮箱列表 >>如果为null则默认发送系统默认的。
	 * **/
    public static Boolean sendTextMail(Properties prop, String subject, String content,String[] sendEmails)  {
    	Boolean success=Boolean.FALSE;
    	Transport ts =null;
    	try {
    		if(null==prop){
        		prop = new Properties();
        		prop.load(Main.class.getResourceAsStream("/mail.properties"));
        	}
            Session session = Session.getInstance(prop);//1、创建session
            session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            ts = session.getTransport(); //2、通过session得到transport对象
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            String password=getDecryptionPassword(_privateKey, prop.getProperty(PASSWORD_KEY).trim());
            ts.connect(prop.getProperty(MAIL_HOST_KEY).trim(), prop.getProperty(USER_KEY).trim(), password);
            MimeMessage message = new MimeMessage(session);//4、创建邮件
            message.setFrom(prop.getProperty(USER_KEY).trim()); //指明邮件的发件人
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
     * */
    public static Boolean sendMail(String subject,String content,String[] sendEmails) throws Exception {
    	Properties prop = new Properties();
    	Boolean success=false;
        prop.load(Main.class.getResourceAsStream("/mail.properties"));
		try {
			success=sendTextMail(prop,subject,content,sendEmails);
		} catch (Exception e) {
			sendTextMail(prop,subject,content,sendEmails);
			throw new Exception("邮件发送失败，失败原因："+e);
		}
		return success;
    }
    
    /**
     * <p>获取解密后的密码</p>
     * @param privateKey 私钥
     * @param encryptionPassword 加密后的密码
     * @return 解密后的密码
     * */
    private static String getDecryptionPassword(String privateKey,String encryptionPassword){
    	try {
    		return RSAUtil.decryptionByPrivateKey(privateKey, encryptionPassword);
		} catch (Exception e) {
			return encryptionPassword;
		}
    }
    
    protected String beginSendMail(String userTheme,String userAddressee,String userMain){
		InternetAddress[] popAddressList = null;
		String smtpServer = "smtp.163.com";
		String SmtpAddress = "289341714@qq.com";
		String addresslist = userAddressee;//收件人的地址;
		String subject = userTheme;
		String Type = "text/html";
		String messageText = userMain;
		List<String> arrArchievList = new ArrayList<String>();// 附件
		try {
			/*JSONArray jsonArray = JSONArray.fromObject(jsonData);
			if (jsonArray == null || jsonArray.size() < 0) {
				return MAIN;
			}
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				attachmentList = jsonObject.get("attachmentList").toString();
				arrArchievList.add(attachmentList);//取出每一个附件.
			}*/
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpServer);// 存储发送邮件服务器的信息
			props.put("mail.smtp.auth", "true");// 同时通过验证
			props.put("mail.transport.protocol", "smtp");
			Session mailSession = Session.getInstance(props);// 根据属性新建一个邮件会话
			mailSession.setDebug(false);
			javax.mail.Message msg = new MimeMessage(mailSession);
			// 设定发件人的地址
			msg.setFrom(new InternetAddress(SmtpAddress));
			// 设定收信人的地址
			popAddressList = InternetAddress.parse(addresslist, false);
			//抄送或者密送//////
			msg.setRecipients(javax.mail.Message.RecipientType.TO, popAddressList);

			// 设定信中的主题
			msg.setSubject(subject);

			// 设定送信的时间
			msg.setSentDate(new Date());

			// 如果有附件，先将由件内容部分存起来
			if (arrArchievList != null && arrArchievList.size() > 0) {
				// 1.保存内容
				MimeMultipart mp = new MimeMultipart();
				MimeBodyPart mailContentPart = new MimeBodyPart();
				mailContentPart.setContent(messageText, Type + ";charset=GBK");
				msg.setContent(messageText, Type + ";charset=GBK");
				// 这句很重要，千万不要忘了(标识发送的内容包含附件.)
				mp.setSubType("related");
				mp.addBodyPart(mailContentPart);
				// 2.保存多个附件
				for (int index = 0; index < arrArchievList.size(); index++) {
					File file = new File((arrArchievList.toArray())[index].toString());
					MimeBodyPart mailArchieve = new MimeBodyPart();
					// FileDataSource做为附件数据源.
					FileDataSource dataSource = new FileDataSource(file);
					// 然后将附件数据源传入:DataHandler中.
					mailArchieve.setDataHandler(new DataHandler(dataSource));
					mailArchieve.setFileName(MimeUtility.encodeText(dataSource.getName(), "GBK", "B"));
					mp.addBodyPart(mailArchieve);
				}
				// 3.最后集成内容和附件，一起发送
				msg.setContent(mp);
			} else {
				msg.setContent(messageText, Type + ";charset=GBK");
			}
			// 发送邮件
			Transport transport;
			msg.saveChanges();// 存储邮件信息
			transport = mailSession.getTransport("smtp");
			// 以smtp方式登录邮箱
			// username填写你的发送邮件的用户名如bluewens,userpwd填写你的密码,如获88888888，即transport.connect("smtp.163.com","bluewens","88888888");
			transport.connect("smtp.exmail.qq.com", "289341714@qq.com","increment");
			transport.sendMessage(msg, msg.getAllRecipients());// 发送邮件,其中第二个参数是所有
			// 已设好的收件人地址
			props.put("pop.163.com", "false");
			transport.close();
			System.out.println("邮件已发送成功!");
		} catch (Exception e) {
		}
		return "beginSendMail";
	}
}
