package ar.edu.unq.po2.integrador;

public interface MailSender {
	void enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto);
}
