package edu.hebut.technote.Mailutils;
import android.util.Log;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class QQMailSender {

    private static final String SMTP_HOST_NAME = "smtp.qq.com";
    private static final String SMTP_PORT = "465"; // SSL端口
    private static final String MAIL_TRANSPORT_PROTOCOL = "smtps";

    /**
     * 发送邮件
     *
     * @param from      发送者的QQ邮箱地址
     * @param password  应用专用授权码（非登录密码）
     * @param to        收件人邮箱地址
     * @param subject   邮件主题
     * @param content   邮件正文文本
     */
    public static void sendEmail(String from, String password, String to, String subject, String content) {
        try {
            // 设置系统属性
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.port", SMTP_PORT);

            // 获取Session对象
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, password);
                        }
                    });

            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // 设置邮件正文
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(content);
                // 如果没有附件，直接设置纯文本正文
                message.setText(content);
            // 发送邮件
            Transport.send(message);

            Log.d("QQMailSender", "邮件已成功发送到：" + to);

        } catch (Exception e) {
            Log.e("QQMailSender", "发送邮件时发生错误", e);
        }
    }
}