
package com.conjunto.lumvet.dao;

import java.time.LocalDateTime;

public class HistorialIngresoDTO {
    private LocalDateTime fechaHora;
    private String tipoUser;
    private String nombre;
    private String numDoc;
    private String detalle;
    private LocalDateTime horaSalida;
    private String estado;
    
    public HistorialIngresoDTO(LocalDateTime fechaHora, String tipoUser, String nombre, String numDoc, String detalle){
        this.fechaHora = fechaHora;
        this.tipoUser = tipoUser;
        this.nombre = nombre;
        this.numDoc = numDoc;
        this.detalle = detalle;
    }
    
    public HistorialIngresoDTO(LocalDateTime fechaHora, String tipoUser, String nombre, String numDoc, String detalle, LocalDateTime horaSalida, String estado){
        this(fechaHora, tipoUser, nombre, numDoc, detalle);
        this.horaSalida = horaSalida;
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public String getDetalle() {
        return detalle;
    }
    
    public LocalDateTime getHoraSalida(){
        return horaSalida;
    }
    
    public String getEstado(){
        return estado;
    }
    
}
