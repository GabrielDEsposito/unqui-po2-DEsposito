package ar.edu.unq.po2.integrador;

public class TransferenciaBancariaDirecta extends MetodoDePago {
    private int cbu;
    private String alias;
    private ITransferenciaBancariaDirecta TranferenciaBancariaDirecta;

    @Override
    protected void validarDatos() {
        TranferenciaBancariaDirecta.validarCbuYAlias(cbu, alias);
    }

    @Override
    protected void reservarFondos(double monto) {
        
    }

    @Override
    protected void ejecutarTransaccion(double monto) {
        TranferenciaBancariaDirecta.transferenciaDirectaOProgramada(monto);
    }

    @Override
    protected void notificarResultado() {
        
    }
}