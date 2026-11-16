package ar.edu.unq.po2.integrador;

public class NotificadorMail implements ObservadorReserva{
	private final MailSender mailSender;

	public NotificadorMail(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
		if (nuevo instanceof EstadoConfirmada || nuevo instanceof EstadoEnCurso || nuevo instanceof EstadoFinalizada) {
			mailSender.enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto);
		}
	}
}