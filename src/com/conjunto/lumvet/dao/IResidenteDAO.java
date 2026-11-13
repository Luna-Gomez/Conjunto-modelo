
package com.conjunto.lumvet.dao;

import com.conjunto.lumvet.model.Residente;
import java.sql.SQLException;
import java.util.List;

public interface IResidenteDAO {
    void registrar(Residente r) throws SQLException;
    Residente buscarPorNumDoc(String numDoc) throws SQLException;
    List<Residente> listarTodos() throws SQLException;
}
