package ar.edu.unq.po2.integrador;

import java.util.EnumSet;
import java.util.Set;

public class Fidelizacion implements ObservadorReserva{
	private static final Set<TipoEstado> estadosQueNotifican = EnumSet.of(TipoEstado.CANCELADA);
	
	private void enviarMensajeCuponDescuento(Reserva reserva) {
        System.out.println("[Club Viajeros] ¡Hola! Lamentamos la cancelación. Te enviamos un cupón del 5% de descuento para tu próximo viaje.");		
	}
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
		if (estadosQueNotifican.contains(nuevo.tipo())) {
			this.enviarMensajeCuponDescuento(reserva);
		}
	}
}
