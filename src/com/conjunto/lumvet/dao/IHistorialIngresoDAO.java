
package com.conjunto.lumvet.dao;

import com.conjunto.lumvet.model.Login;
import com.conjunto.lumvet.model.User;
import java.sql.SQLException;
import java.util.List;

public interface IHistorialIngresoDAO {
    void registrarIngreso(User user, Login login) throws SQLException;
    List<HistorialIngresoDTO> obtenerHistorial() throws SQLException;
    List<HistorialIngresoDTO> buscarPorDocumento(String numDoc) throws SQLException;
    boolean registrarSalida(String numDoc) throws SQLException;
}
