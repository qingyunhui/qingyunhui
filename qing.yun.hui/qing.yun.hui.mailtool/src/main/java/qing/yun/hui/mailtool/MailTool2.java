package qing.yun.hui.mailtool;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2018年4月24日下午6:22:02
 **/
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2018-04-24.
 */
public class MailTool2 {

//    // 发送邮件的账号
    public static String ownEmailAccount = "mx-robot@fentuan360.com";
    // 发送邮件的密码------》授权码
    public static String ownEmailPassword = "Mx654321";


    // 发送邮件的账号
//    public static String ownEmailAccount = "xiaoqiming@fentuan360.com";
//    // 发送邮件的密码------》授权码
//    public static String ownEmailPassword = "Xiaoqm2006";


    public void sendMail(Properties prop,String content,String[] receiveMailAccount) throws Exception {
        // 创建对象回话跟服务器交互
        Session session = Session.getInstance(prop);
        // 会话采用debug模式
        session.setDebug(true);
        // 创建邮件对象
        Message message = createSimpleMail(session,content,receiveMailAccount);
        Transport trans = session.getTransport();
        // 链接邮件服务器
        trans.connect(ownEmailAccount, ownEmailPassword);
        // 发送信息
        trans.sendMessage(message, message.getAllRecipients());
        // 关闭链接
        trans.close();
    }

    /**
     * @Title: createSimpleMail
     * @Description: 创建邮件对象
     * @author: chengpeng
     * @param @param session
     * @param @return
     * @param @throws Exception    设定文件
     * @return Message    返回类型
     * @throws
     */
    public static Message createSimpleMail(Session session,String content ,String [] receiveMailAccount) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 设置发送邮件地址,param1 代表发送地址 param2 代表发送的名称(任意的) param3 代表名称编码方式
        message.setFrom(new InternetAddress(ownEmailAccount, "send", "utf-8"));

        for(int i=0;i<receiveMailAccount.length;i++){
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount[i], receiveMailAccount[i], "utf-8"));
        }



        // 设置邮件主题
        message.setSubject("告警邮箱");
        // 设置邮件内容
        message.setContent(content, "text/html;charset=utf-8");

        // 设置发送时间
        message.setSentDate(new Date());
        // 保存上面的编辑内容
        message.saveChanges();
        // 将上面创建的对象写入本地
        OutputStream out = new FileOutputStream("MyEmail.eml");
        message.writeTo(out);
        out.flush();
        out.close();
        return message;

    }



    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        // 设置邮件传输采用的协议smtp
        prop.setProperty("mail.transport.protocol", "smtp");
        // 设置发送人邮件服务器的smtp地址
        // 发送服务器邮箱smtp服务器地址
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        // 设置验证机制
        prop.setProperty("mail.smtp.auth", "true");

        MailTool2 mailTool = new MailTool2();

        String content = "邮件发送内容";
        String [] receiveMailAccount = new String[]{"mx-robot@fentuan360.com","15195974036@139.com"};
        mailTool.sendMail(prop,content,receiveMailAccount);
    }

}

