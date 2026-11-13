
package com.conjunto.lumvet.model;


public abstract class User {
    
    protected int idUser;
    protected String nombre;
    protected String tipoDoc;
    protected String numDoc;
    
    public User(){}
    
    public User(int idUser, String nombre, String tipoDoc, String numDoc){
        this.idUser = idUser;
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }
    
    public int getIdUser(){
        return idUser;
    }
    
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getTipoDoc(){
        return tipoDoc;
    }
    
    public void setTipoDoc(String tipoDoc){
        this.tipoDoc = tipoDoc;
    }
    
    public String getNumDoc(){
        return numDoc;
    }
    
    public void setNumDoc(String numDoc){
        this.numDoc = numDoc;
    }
    
    public abstract String getTipoUser();
}
