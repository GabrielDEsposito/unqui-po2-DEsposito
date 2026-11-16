package ar.edu.unq.po2.integrador;

public class GeneradorComprobante implements ObservadorReserva{
	
	@Override
	public void actualizar(Reserva reserva, EstadoReserva estado) {
		if (estado instanceof EstadoFinalizada) {
			this.crearComprobanteFiscalYVoucher(reserva);
		}
	}
	
	private void crearComprobanteFiscalYVoucher(Reserva reserva) {
		double monto = reserva.costoServicio();
		Voucher voucher = new Voucher(monto);
		
		// Falta terminar de implementar esta parte
	}
}
