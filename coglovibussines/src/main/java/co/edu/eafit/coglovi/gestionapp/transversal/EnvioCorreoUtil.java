package co.edu.eafit.coglovi.gestionapp.transversal;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

public class EnvioCorreoUtil {

	private String correoOrigen;
	private String mensaje;
	private String[] correoDestino;
	private Properties mailProperty;
	private String correoPass;
	private String subject;
	private boolean isSSL;
	private byte[] file;
	private String fileName;
	private String typeFile;
	private String host;
	private String port;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCorreoOrigen() {
		return correoOrigen;
	}

	public void setCorreoOrigen(String correoOrigen) {
		this.correoOrigen = correoOrigen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String[] getCorreoDestino() {
		return correoDestino;
	}

	public void setCorreoDestino(String[] correoDestino) {
		this.correoDestino = correoDestino;
	}

	public Properties getMailProperty() {
		return mailProperty;
	}

	public void setMailProperty(Properties mailProperty) {
		this.mailProperty = mailProperty;
	}

	public String getCorreoPass() {
		return correoPass;
	}

	public void setCorreoPass(String correoPass) {
		this.correoPass = correoPass;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isSSL() {
		return isSSL;
	}

	public void setSSL(boolean isSSL) {
		this.isSSL = isSSL;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	public void enviar() throws MessagingException {
		mailProperty = new Properties();
		if (isSSL) {
			mailProperty.setProperty("mail.smtps.host", host);
			mailProperty.setProperty("mail.smtps.starttls.enable", "true");
			mailProperty.setProperty("mail.smtps.port", port);
			mailProperty.setProperty("mail.smtps.auth", "true");
		} else {
			mailProperty.setProperty("mail.smtp.host", host);
			mailProperty.setProperty("mail.smtp.starttls.enable", "true");
			mailProperty.setProperty("mail.smtp.port", port);
			mailProperty.setProperty("mail.smtp.auth", "true");
		}

		Session mailSesion = Session.getDefaultInstance(mailProperty, null);
		Message msg = new MimeMessage(mailSesion);
		msg.setFrom(new InternetAddress(correoOrigen));
		msg.setSubject(subject);
		msg.setSentDate(new java.util.Date());
		msg.setContent(mensaje, "text/html");
		if (file != null) {
			msg.setFileName(fileName);
			DataSource dataSource = new ByteArrayDataSource(file, typeFile);
			msg.setDataHandler(new DataHandler(dataSource));
		}

		InternetAddress address[] = new InternetAddress[correoDestino.length];
		for (int i = 0; i != correoDestino.length; i++) {
			address[i] = new InternetAddress(correoDestino[i]);
		}

		msg.setRecipients(Message.RecipientType.TO, address);

		Transport t = mailSesion.getTransport(isSSL ? "smtps" : "smtp");
		t.connect(correoOrigen, correoPass);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();

	}

}
