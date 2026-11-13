
package com.conjunto.lumvet.model;

import java.time.LocalDateTime;

public class Login {
    
    private LocalDateTime fechaHora;
    private String tipoUsuario;
    private String numDoc;
    
    public Login(){}
    
    public Login(LocalDateTime fechaHora, String tipoUsuario, String numDoc){
        this.fechaHora = fechaHora;
        this.tipoUsuario = tipoUsuario;
        this.numDoc = numDoc;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
    
    public void ingreso(){
        this.fechaHora = LocalDateTime.now();
    }
}
