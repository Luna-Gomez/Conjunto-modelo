
package com.conjunto.lumvet.model;

public class Residente extends User {
    
    private int numBloque;
    private int numCasa;
    
    public Residente(){
        super();
    }
    
    public Residente(int idUser, String nombre, String tipoDoc, String numDoc, int numBloque, int numCasa) {
        super(idUser, nombre, tipoDoc, numDoc);
        this.numBloque = numBloque;
        this.numCasa = numCasa;
    }

    public int getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(int numBloque) {
        this.numBloque = numBloque;
    }

    public int getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(int numCasa) {
        this.numCasa = numCasa;
    }

    @Override
    public String getTipoUser(){
        return "Residente";
    }
}
