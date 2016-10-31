package trainning.broad.helpers;

import java.util.Properties;

import javax.mail.Authenticator;

public class Mail {

	private Properties props = new Properties();
	private Authenticator auth = new Authenticator() {
	};

	public Mail() {

		this.props.put("mail.smtp.host", "smtp.gmail.com");
		this.props.put("mail.smtp.port", "587");
		this.props.put("mail.transport.protocol", "smtp");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.debug", "true");
	}
}
