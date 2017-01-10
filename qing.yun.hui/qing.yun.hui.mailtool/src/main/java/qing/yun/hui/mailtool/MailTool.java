package qing.yun.hui.mailtool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
public class MailTool {
	
	private static String USER_KEY="user";
	private static String PASSWORD_KEY="password";
	private static String MAIL_HOST_KEY="mail.host";
	private static String ADDRESSES_KEY="addresses";
    
	/**
	 * @param prop  可以为null，如果为null，则会new 一个Properties.
	 * @param subject 主题
	 * @param content 内容
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
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(prop.getProperty(MAIL_HOST_KEY).trim(), prop.getProperty(USER_KEY).trim(), prop.getProperty(PASSWORD_KEY).trim());
            //4、创建邮件
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发件人
            message.setFrom(prop.getProperty(USER_KEY).trim());
            
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            String[] addresses=null;
            if(null!=sendEmails && sendEmails.length>0){
            	addresses = sendEmails;
            }else{
            	addresses = prop.getProperty(ADDRESSES_KEY).trim().split(",");
            }
            InternetAddress[] internetAddress = new InternetAddress[addresses.length];
            for(int i=0; i<internetAddress.length; i++){
            	internetAddress[i] = new InternetAddress(addresses[i].trim());
            }
            message.setRecipients(Message.RecipientType.TO, internetAddress);
            //邮件的标题
            message.setSubject(subject);
            //邮件的文本内容
            message.setContent(content, "text/html;charset=UTF-8");
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
    
    public static String dateToString(Date dateDate, String formatString) {
		String dateString = "";
		if(dateDate != null && formatString != "" && formatString != null) {
    		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
    		dateString = formatter.format(dateDate);
		}
		return dateString;
	}
    
    public static String content(String toUser, String throughAddress) {
    	StringBuffer sb=new StringBuffer();
    	sb.append("<div>尊敬的用户:</div><br/>");
    	sb.append("<div>" + toUser + ",您好！<div/>");
    	sb.append("<div>您于" + dateToString(new Date(),"YYYY-MM-dd HH:mm:ss")+ "提交找回密码请求,请点击下面的链接修改用户 " + toUser + " 的密码:</div>");
    	sb.append("<div><a href='www.baidu.com" +  "' style='color:red;font-size:20px;'>" + throughAddress + "</a></div>");
    	sb.append("<div >如果您无法点击这个链接,请将此链接复制到浏览器地址栏后访问！</div><br/>");
    	sb.append("<div >为了保证您账号的安全性,该链接有效期为30分钟,并且点击一次后失效!</div><br/>");
    	sb.append("<div >设置并牢记密码保护问题将更好地保障您的账号安全。</div><br/>");
    	sb.append("<div >如果您误收到此电子邮件,则可能是其他用户在尝试账号设置时的误操作,如果您并未发起该请求,则无需进行任何操作,并可以放心地忽略此电子邮件。</div><br/>");
    	sb.append("<div >若担心账号安全,建议您立即登陆,进入''管理系统',密码修改中修改密码。</div><br/>");
    	sb.append("<div >感谢您使用龙飞颜料商行预测试版系统!</div><br/>");
    	sb.append("<div style='text-align:center' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 浙江省乐清市虹桥镇龙飞颜料商行</div><br/>");
    	sb.append("<div style='text-align:center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ dateToString(new Date(),"YYYY-MM-dd HH:mm:ss") + "</div><br/>");
    	sb.append("<div>此邮件为自动发送,请忽回复!</div><br/>");
		return sb.toString();
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
}
