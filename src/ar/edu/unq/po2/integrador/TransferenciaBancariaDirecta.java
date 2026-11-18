package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class TransferenciaBancariaDirecta extends MetodoPago {
    private String cbu;
    private String alias;
    private ITransferenciaBancariaDirecta TranferenciaBancariaDirecta;
    private List<ComprobanteCBU> comprobantes = new ArrayList<>();

    
    public TransferenciaBancariaDirecta(String cbu, String alias,ITransferenciaBancariaDirecta TranferenciaBancariaDirecta) {
    	this.cbu = cbu;
    	this.alias = alias;
    	this.TranferenciaBancariaDirecta = TranferenciaBancariaDirecta;
    	
    }
    
    
    @Override
	public void ValidarDatos() {
        if (!TranferenciaBancariaDirecta.validarCbuYAlias(cbu, alias)) {
            throw new IllegalStateException("Datos de CBU o alias inválidos");
        }
    }

    @Override
	public void ReservarFondos(double monto) {
        //no se requiere reservar fondos para transferencia bancaria directa
    }

    @Override
	public void EjecutarTransaccion(double monto) {
        TranferenciaBancariaDirecta.transferenciaDirectaOProgramada(monto);
    }

    @Override
	public void NotificarResultado(double monto) {
        ComprobanteCBU comprobante = new ComprobanteCBU(cbu, alias, this.getCodigoTransaccion());
        this.comprobantes.add(comprobante);
    }

    public List<ComprobanteCBU> getComprobantes() {
        return comprobantes;
    }
}