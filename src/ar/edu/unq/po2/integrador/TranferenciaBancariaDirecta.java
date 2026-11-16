package ar.edu.unq.po2.integrador;

public class TransferenciaBancariaDirecta extends MetodoDePago {
    private String cbu;
    private String alias;
    private ITransferenciaBancariaDirecta TranferenciaBancariaDirecta;
    private List<ComprobanteCBU> comprobantes = new ArrayList<>();

    @Override
    protected void validarDatos() {
        if (!TranferenciaBancariaDirecta.validarCbuYAlias(cbu, alias)) {
            throw new IllegalStateException("Datos de CBU o alias inválidos");
        }
    }

    @Override
    protected void reservarFondos(double monto) {
        //no se requiere reservar fondos para transferencia bancaria directa
    }

    @Override
    protected void ejecutarTransaccion(double monto) {
        TranferenciaBancariaDirecta.transferenciaDirectaOProgramada(monto);
    }

    @Override
    protected void notificarResultado(double monto) {
        ComprobanteCBU comprobante = new ComprobanteCBU(cbu, alias, this.getCodigoTransaccion());
        this.comprobantes.add(new ComprobanteCBU(cbu, alias, this.getCodigoTransaccion()));
    }

    public List<ComprobanteCBU> getComprobantes() {
        return comprobantes;
    }
}