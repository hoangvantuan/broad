package trainning.broad.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import trainning.broad.helpers.Constants;

public class Mails {

	HtmlEmail email = new HtmlEmail();

	public Mails() {
		email.setHostName(Constants.EMAIL_HOST_NAME);
		email.setSmtpPort(Constants.SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(Constants.FROM_EMAIL, Constants.EMAIL_PASSWORD));
		email.setSSLOnConnect(true);
	}

	public void sendEmail(String from, String to, String subject, String content) throws EmailException {

		try {
			email.setFrom(from);
			email.setSubject(subject);
			email.setHtmlMsg(content);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			throw e;
		}

	}
}