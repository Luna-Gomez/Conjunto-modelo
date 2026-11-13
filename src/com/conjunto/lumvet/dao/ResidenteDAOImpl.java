
package com.conjunto.lumvet.dao;

import com.conjunto.lumvet.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidenteDAOImpl implements IResidenteDAO{

    private Residente construirResidente (ResultSet rs) throws SQLException{
        Residente r = new Residente();
        r.setIdUser(rs.getInt("idUser"));
        r.setNombre(rs.getString("nombre"));
        r.setTipoDoc(rs.getString("tipoDoc"));
        r.setNumDoc(rs.getString("numDoc"));
        r.setNumCasa(rs.getInt("numCasa"));
        r.setNumBloque(rs.getInt("numBloque"));
        return r;
    }
    
    @Override
    public void registrar(Residente r) throws SQLException {
        String sql = "INSERT INTO residente (nombre, tipoDoc, numDoc, numBloque, numCasa) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getTipoDoc());
            ps.setString(3, r.getNumDoc());
            ps.setInt(4, r.getNumBloque());
            ps.setInt(5, r.getNumCasa());
            
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()) r.setIdUser(rs.getInt(1));
            }
        }
    }

    @Override
    public Residente buscarPorNumDoc(String numDoc) throws SQLException {
        String sql = "SELECT * FROM residente WHERE numDoc = ?";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)){
            ps.setString(1, numDoc);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next() ? construirResidente(rs):null;
            }
        }
    }

    @Override
    public List<Residente> listarTodos() throws SQLException {
        List<Residente> lista = new ArrayList<>();
        String sql ="SELECT * FROM residente";
        try(Statement st = ConexionBD.getConexion().createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while(rs.next()) lista.add(construirResidente(rs));
        }
        return lista;
    }
    
    
}
