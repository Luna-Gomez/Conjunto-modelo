
package com.conjunto.lumvet.model;

import com.conjunto.lumvet.dao.IResidenteDAO;
import com.conjunto.lumvet.dao.ResidenteDAOImpl;
import java.sql.SQLException;

public class Registro {
    
    private IResidenteDAO residenteDAO = new ResidenteDAOImpl();
    
    public boolean registrar (User user) throws SQLException{
        if(user instanceof Residente residente){
            residenteDAO.registrar(residente);
            return true;
        }
        return false;
    }
}
