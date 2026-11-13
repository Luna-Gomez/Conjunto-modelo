
package com.conjunto.lumvet.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionBD {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/conjunto_lumvet_db?sslMode=REQUIRED&connectTimeout=10000";
    private static final String USUARIO = "admin_conjunto_lumvet";
    private static final String PASSWORD = "#LuMVet$15811";
    
    private static Connection conexion;
    
    private ConexionBD(){}
    
    public static Connection getConexion() throws SQLException{
        if(conexion == null || conexion.isClosed() || !conexion.isValid(2)){
            try{
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }catch(SQLException sqle){
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }
        }
        return conexion;
    }
}
