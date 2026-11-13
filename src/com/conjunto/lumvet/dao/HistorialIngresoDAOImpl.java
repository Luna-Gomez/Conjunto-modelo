/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conjunto.lumvet.dao;

import com.conjunto.lumvet.dao.HistorialIngresoDTO;
import com.conjunto.lumvet.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialIngresoDAOImpl implements IHistorialIngresoDAO{
    
    //Para simplificar el código creamos una variable que contenga la sentencia SQL para los SELECT con JOINS para obtener todos los valores o consultarlos añadiendole Where a la sentencia
    private static final String SQL_BASE = "SELECT hi.*, " +
                "r1.numCasa AS casaResidente, r1.numBloque AS bloqueResidente," + 
                "r2.numCasa AS casaVisitado, r2.numBloque AS bloqueVisitado " + 
                "FROM historial_ingreso hi " + 
                "LEFT JOIN residente r1 ON hi.idResidente = r1.idUser " + 
                "LEFT JOIN residente r2 ON hi.idResidenteVisitado = r2.idUser ";
    
    //Función para construir la respuesta según el tipo de Usuario que ingresa, de igual forma se usa para simplificar el código que puede ser repetitivo y ayuda a que el código sea legible
    private HistorialIngresoDTO construirDTO(ResultSet rs) throws SQLException{
        String tipoUser = rs.getString("tipoUsuario");
        String detalle = switch (tipoUser){
                    case "Residente" -> "Casa " + rs.getInt("casaResidente") + ", Bloque " + rs.getInt("bloqueResidente");
                    case "Proveedor" -> "Empresa: " + rs.getString("empresa");
                    case "Trabajador" -> rs.getString("cargo") + " - " + rs.getString("empresa");
                    case "Visitante" -> "Visitó casa " + rs.getInt("casaVisitado") + ", Bloque" + rs.getInt("bloqueVisitado");
                    default -> "";
        };

        Timestamp salida = rs.getTimestamp("horaSalida");
        
        return new HistorialIngresoDTO(
            rs.getTimestamp("fechaHora").toLocalDateTime(),
            tipoUser,
            rs.getString("nombre"),
            rs.getString("numDoc"),
            detalle,
            salida != null ? salida.toLocalDateTime() : null,
            rs.getString("estado")
        );
        
    }

    @Override
    public void registrarIngreso(User user, Login login) throws SQLException {
        String sql = "INSERT INTO historial_ingreso " + "(fechaHora, tipoUsuario, nombre, tipoDoc, numDoc, idResidente, empresa, cargo, idResidenteVisitado) " + "VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)){
            ps.setTimestamp(1, Timestamp.valueOf(login.getFechaHora()));
            ps.setString(2, user.getTipoUser());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getTipoDoc());
            ps.setString(5, user.getNumDoc());
            
            if(user instanceof Residente residente){
                ps.setInt(6, residente.getIdUser());
            }else {
                ps.setNull(6, Types.INTEGER);
            }
            
            if( user instanceof Proveedor provedor){
                ps.setString(7, provedor.getEmpresa());
            } else if (user instanceof Trabajador trabajador){
                ps.setString(7, trabajador.getEmpresa());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }
            
            if(user instanceof Trabajador trabajador){
                ps.setString(8, trabajador.getCargo());
            }else {
                ps.setNull(8, Types.VARCHAR);
            }
            
            if(user instanceof Visitante visitante && visitante.getResidenteVisitado() != null){
                ps.setInt(9, visitante.getResidenteVisitado().getIdUser());
            } else {
                ps.setNull(9, Types.INTEGER);
            }
            
            ps.executeUpdate();
        }
    }

    @Override
    public List<HistorialIngresoDTO> obtenerHistorial() throws SQLException {
        List<HistorialIngresoDTO> historialIngreso = new ArrayList<>();
        String sql = SQL_BASE + "ORDER BY hi.fechaHora DESC"; //Añadimos el ordenar por fecha hora del más reciente al primer registro.
        
        try (Statement st = ConexionBD.getConexion().createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                historialIngreso.add(construirDTO(rs));
            }
        }
        
        return historialIngreso;
    }
    
    
    @Override
    public List<HistorialIngresoDTO> buscarPorDocumento(String numDoc) throws SQLException{
        List<HistorialIngresoDTO> historialIngreso = new ArrayList<>();
        String sql = SQL_BASE + "WHERE hi.numDoc = ? ORDER BY hi.fechaHora DESC";
        
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)){
            ps.setString(1, numDoc);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    historialIngreso.add(construirDTO(rs));
                }
            }
        }
        
        return historialIngreso;
    }

    //Cerrar solo el ingreso más reciente de esa persona con ese documento.
    @Override
    public boolean registrarSalida(String numDoc) throws SQLException {
        String sql = "UPDATE historial_ingreso SET horaSalida = ?, estado = 'Salió'" + "WHERE numDoc = ? AND estado = 'Dentro' ORDER BY fechaHora DESC LIMIT 1";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)){
            ps.setTimestamp(1, Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.setString(2, numDoc);
            return ps.executeUpdate() > 0;
        }
    }
}
