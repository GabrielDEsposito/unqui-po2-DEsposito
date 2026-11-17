package ar.edu.unq.po2.integrador;

public interface ReporteVisitor {
    void visitarServicio(ServicioIndividual servicio);
    void visitarPaquete(PaqueteTuristico paquete);
    void visitarReserva(Reserva reserva);
}
