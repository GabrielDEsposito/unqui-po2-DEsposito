package ar.edu.unq.po2.integrador;

public interface ITransferenciaBancariaDirecta {

    boolean validarCbuYAlias(String cbu, String alias);
    
    void transferenciaDirectaOProgramada(double monto);
}