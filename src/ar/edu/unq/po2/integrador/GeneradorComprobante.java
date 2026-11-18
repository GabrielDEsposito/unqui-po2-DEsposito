package ar.edu.unq.po2.integrador;

import java.util.EnumSet;
import java.util.Set;

public class GeneradorComprobante implements ObservadorReserva{
	private static final Set<TipoEstado> estadosQueNotifican = EnumSet.of(TipoEstado.FINALIZADA);
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
		if (estadosQueNotifican.contains(nuevo.tipo())) {
			this.crearComprobanteFiscalYVoucher(reserva);
		}
	}
	
	private void crearComprobanteFiscalYVoucher(Reserva reserva) {
		double monto = reserva.costoServicio();
		Voucher voucher = new Voucher(monto);
		
		// Falta terminar de implementar esta parte
	}
}
