package ar.edu.unq.po2.integrador;

public class TransferenciaBancariaDirecta extends MetodoDePago {
    private int cbu;
    private String alias;
    private ITransferenciaBancariaDirecta apiTransferencia;

    @Override
    protected void validarDatos() {
        apiTransferencia.validarCbuYAlias(cbu, alias);
    }

    @Override
    protected void reservarFondos(double monto) {
        
    }

    @Override
    protected void ejecutarTransaccion(double monto) {
        apiTransferencia.transferenciaDirectaOProgramada(monto);
    }

    @Override
    protected void notificarResultado() {
        
    }
}