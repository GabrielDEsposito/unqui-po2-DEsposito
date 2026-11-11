package ar.edu.unq.po2.integrador;

public interface ItarjetaInternacional {

	public void	validarDatosTarjeta(int numeroTarjeta, String cvv, String fechaVencimiento) {
        try {
            if (numeroTarjeta <= 0 || cvv == null || fechaVencimiento == null) {
                throw new IllegalArgumentException("Datos de tarjeta inválidos.");
            }
            // Aquí podrías agregar más validaciones según sea necesario
        } catch (IllegalArgumentException e) {
            System.out.println("Error al validar la tarjeta: " + e.getMessage());
        }
    }

    public preAutorizacionDelBanco() {
        
    }
}
