package model;

import java.sql.*;
import utilily.ConexionBD;

public class ModelLoginPass {
    Connection        cn = null;
    PreparedStatement pt = null;
    
    public boolean agregarContraseña(String usuario, String addcontraseña) {
        try {
            if (addcontraseña.length() < 8) {
                return false;
            }
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("UPDATE alumno SET contraseña = ? WHERE usuario = ?");
            pt.setString(1, addcontraseña);
            pt.setString(2, usuario);
            int result = pt.executeUpdate();
            
            pt.close(); cn.close();
            return result > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
