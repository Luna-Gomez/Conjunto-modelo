
package com.conjunto.lumvet.model;

import com.conjunto.lumvet.controller.ControlIngreso;

public class Visitante extends User implements ControlIngreso {
    
    private Residente residenteVisitado;
    
    public Visitante(){
        super();
    }

    public Visitante(int idUser, String nombre, String tipoDoc, String numDoc, Residente residente) {
        super(idUser, nombre, tipoDoc, numDoc);
        this.residenteVisitado = residente;
    }
  
    public Residente getResidenteVisitado() {
        return residenteVisitado;
    }

    public void setResidenteVisitado(Residente residente) {
        this.residenteVisitado = residente;
    }
    
    @Override
    public String getTipoUser(){
        return "Visitante";
    }

    @Override
    public boolean validarIngresoResidencia() {
        return residenteVisitado != null;
    }
}
