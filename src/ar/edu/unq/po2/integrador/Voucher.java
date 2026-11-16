package ar.edu.unq.po2.integrador;

public class Voucher {
	    private double monto;
		private String codigoTransaccion;

	    public double getMonto() {
	        return monto;
	    }

		public String getCodigoTransaccion() {
			return codigoTransaccion;
		}

		public Voucher(double monto, String codigoTransaccion) {
        this.monto = monto;
        this.codigoTransaccion = codigoTransaccion;
    }
}
