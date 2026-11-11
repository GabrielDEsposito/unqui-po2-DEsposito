package ar.edu.unq.po2.integrador;

public class NotificadorMail implements ObservadorReserva{
	private final MailSender mailSender;

	public NotificadorMail(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva estado) {
		if (estado instanceof EstadoConfirmada || estado instanceof EstadoEnCurso || estado instanceof EstadoFinalizada) {
			mailSender.enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto);
		}
	}
}