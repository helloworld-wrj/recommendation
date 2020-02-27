package or.wr.bookrecommendationsystem.impl;

public interface MailService {

    void sendHtmlMail(String to, String subject, String content);
}
