package com.yudi.infoandroid.modelo;

public class RegistroIdeasDiarias {


    public String getFechaDía() {
        return fechaDía;
    }

    public void setFechaDía(String fechaDía) {
        this.fechaDía = fechaDía;
    }

    public String getIdeaProducida() {
        return ideaProducida;
    }

    public void setIdeaProducida(String ideaProducida) {
        this.ideaProducida = ideaProducida;
    }

    public RegistroIdeasDiarias (){


    }

    public RegistroIdeasDiarias(String fechaDía, String ideaProducida) {
        this.fechaDía = fechaDía;
        this.ideaProducida = ideaProducida;
    }

    private String fechaDía;
    private String ideaProducida;
}
