
package com.conjunto.lumvet.model;

import com.conjunto.lumvet.controller.ControlIngreso;

public class Trabajador extends User implements ControlIngreso{
    
    private String empresa;
    private String cargo;
    
    public Trabajador(){
        super();
    }
    
    public Trabajador(int idUser, String nombre, String tipoDoc, String numDoc, String empresa, String cargo){
        super(idUser, nombre, tipoDoc, numDoc);
        this.empresa = empresa;
        this.cargo = cargo;
    }
    
    public String getEmpresa(){
        return empresa;
    }
    
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }
    
    public String getCargo(){
        return cargo;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    @Override
    public String getTipoUser() {
        return "Trabajador";
    }

    @Override
    public boolean validarIngresoResidencia() {
        return true;
    }
}
