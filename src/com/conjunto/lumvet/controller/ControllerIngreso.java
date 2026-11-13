
package com.conjunto.lumvet.controller;

import com.conjunto.lumvet.dao.IHistorialIngresoDAO;
import com.conjunto.lumvet.dao.HistorialIngresoDAOImpl;
import com.conjunto.lumvet.dao.IResidenteDAO;
import com.conjunto.lumvet.dao.ResidenteDAOImpl;
import com.conjunto.lumvet.model.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ControllerIngreso {
    private final IResidenteDAO residenteDAO = new ResidenteDAOImpl();
    private final IHistorialIngresoDAO historialDAO = new HistorialIngresoDAOImpl();
    
    public boolean ingresoResidente(String numDoc) throws SQLException{
        Residente residente = residenteDAO.buscarPorNumDoc(numDoc);
        if(residente == null){
            return false;
        }
        registrarEnHistorial(residente);
        return true;
    }
    
    public boolean ingresoProveedor(String nombre, String tipoDoc, String numDoc, String empresa) throws SQLException{
        Proveedor proveedor = new Proveedor(0, nombre, tipoDoc, numDoc, empresa);
        return validarYRegistrar(proveedor);
    }
    
    public boolean ingresoTrabajador(String nombre, String tipoDoc, String numDoc, String empresa,String cargo) throws SQLException{
        Trabajador trabajador = new Trabajador(0, nombre, tipoDoc, numDoc, empresa, cargo);
        return validarYRegistrar(trabajador);
    }
    
    public boolean ingresoVisitante(String nombre, String tipoDoc, String numDoc, String numDocResidenteVisitar) throws SQLException{
        Residente residenteVisitar = residenteDAO.buscarPorNumDoc(numDocResidenteVisitar);
        if(residenteVisitar == null){
            return false;
        }
        Visitante visitante = new Visitante(0, nombre, tipoDoc, numDoc, residenteVisitar);
        return validarYRegistrar(visitante);
    }
    
    private boolean validarYRegistrar(User user) throws SQLException{
        if(user instanceof ControlIngreso controlIngreso && !controlIngreso.validarIngresoResidencia()){
            return false;
        }
        registrarEnHistorial(user);
        return true;
    }
    
    private void registrarEnHistorial(User user) throws SQLException{
        Login login = new Login();
        login.setFechaHora(LocalDateTime.now());
        login.setTipoUsuario(user.getTipoUser());
        login.setNumDoc(user.getNumDoc());
        
        historialDAO.registrarIngreso(user, login);
    }
}
