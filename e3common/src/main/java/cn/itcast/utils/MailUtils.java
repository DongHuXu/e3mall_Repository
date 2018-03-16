package cn.itcast.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	private static String smtp_host = "smtp.126.com"; 
	private static String username = "itcast_ymy@126.com"; 
	//授权码
	private static String password = "asd123456"; 

	private static String from = "itcast_ymy@126.com"; // 使用当前账户
	public static String activeUrl = "http://localhost:8080/bos_fore/CustomerAction_active";

	public static void sendMail(String subject, String content, String to) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtp_host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			Transport transport = session.getTransport();
			transport.connect(smtp_host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败...");
		}
	}

	public static void main(String[] args) {
		//标题
		//内容
		//发送地址
		sendMail("测试邮件", "你好，传智播客", "769039691@qq.com");
	}
}
