package ar.edu.unq.po2.integrador;

public interface ITransferenciaBancariaDirecta {

    boolean validarCbuYAlias(int cbu, String alias);
    
    void transferenciaDirectaOProgramada(double monto);
}