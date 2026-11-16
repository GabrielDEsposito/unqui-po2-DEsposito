package ar.edu.unq.po2.integrador;

public class Fidelizacion implements ObservadorReserva{
	
	public void actualizar(Reserva reserva, EstadoReserva estado) {
		if (estado instanceof EstadoCancelada) {
			this.enviarMensajeCuponDescuento(reserva);
		}
	}
	
	private void enviarMensajeCuponDescuento(Reserva reserva) {
        System.out.println("[Club Viajeros] ¡Hola! Lamentamos la cancelación. Te enviamos un cupón del 5% de descuento para tu próximo viaje.");		
	}
}
