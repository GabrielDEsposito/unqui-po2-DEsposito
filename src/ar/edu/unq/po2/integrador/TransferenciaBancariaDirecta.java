package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class TransferenciaBancariaDirecta extends MetodoPago {
    private String cbu;
    private String alias;
    private ITransferenciaBancariaDirecta TranferenciaBancariaDirecta;
    private List<ComprobanteCBU> comprobantes = new ArrayList<>();

    @Override
    protected void ValidarDatos() {
        if (!TranferenciaBancariaDirecta.validarCbuYAlias(cbu, alias)) {
            throw new IllegalStateException("Datos de CBU o alias inválidos");
        }
    }

    @Override
    protected void ReservarFondos(double monto) {
        //no se requiere reservar fondos para transferencia bancaria directa
    }

    @Override
    protected void EjecutarTransacion(double monto) {
        TranferenciaBancariaDirecta.transferenciaDirectaOProgramada(monto);
    }

    @Override
    protected void NotificarResultado(double monto) {
        ComprobanteCBU comprobante = new ComprobanteCBU(cbu, alias, this.getCodigoTransaccion());
        this.comprobantes.add(comprobante);
    }

    public List<ComprobanteCBU> getComprobantes() {
        return comprobantes;
    }
}