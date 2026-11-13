
package com.conjunto.lumvet.model;

import com.conjunto.lumvet.controller.ControlIngreso;

public class Proveedor extends User implements ControlIngreso{
    
    private String empresa;
    
    public Proveedor(){
        super();
    }
    
    public Proveedor(int idUser, String nombre, String tipoDoc, String numDoc, String empresa){
        super(idUser, nombre, tipoDoc, numDoc);
        this.empresa = empresa;
    }
    
    public String getEmpresa(){
        return empresa;
    }
    
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    @Override
    public String getTipoUser() {
        return "Proveedor";
    }

    @Override
    public boolean validarIngresoResidencia() {
        return true;
    }
}
