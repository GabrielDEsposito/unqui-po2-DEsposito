package ar.edu.unq.po2.integrador;

import java.util.EnumSet;
import java.util.Set;

public class NotificadorMail implements ObservadorReserva{
	private static final Set<TipoEstado> estadosQueNotifican = EnumSet.of(TipoEstado.CONFIRMADA, TipoEstado.EN_CURSO, TipoEstado.FINALIZADA);
	private final MailSender mailSender;

	public NotificadorMail(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
		if (estadosQueNotifican.contains(nuevo.tipo())) {
			mailSender.enviarMail(reserva.getEmailCliente(), "Tu reserva cambió de estado", "El nuevo estado de tu reserva es: " + nuevo.tipo(), null);
		}
	}
}