package com.pereyra.appFacturacion.dtos;

public class ContextoVersion {

    private boolean nuevaVersionCreada;

    public ContextoVersion() {
        this.nuevaVersionCreada = false;
    }

    public boolean isNuevaVersionCreada() {
        return nuevaVersionCreada;
    }

    public void setNuevaVersionCreada(boolean nuevaVersionCreada) {
        this.nuevaVersionCreada = nuevaVersionCreada;
    }
}
