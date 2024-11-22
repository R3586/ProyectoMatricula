package model;

import java.sql.*;
import utilily.ConexionBD;

public class ModelLogin {       
    Connection        cn = null;
    PreparedStatement pt = null;
    ResultSet         rs = null;   
    
    public String validarLogin(String usuario, String contraseña) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM alumno WHERE usuario = ? AND contraseña = ? ");
            pt.setString(1, usuario);
            pt.setString(2, contraseña);
            rs = pt.executeQuery();
            if (rs.next()) {
                return "alumno";
            } else{
                rs.close(); pt.close();    
                pt = cn.prepareStatement("SELECT * FROM consejero WHERE usuario = ? AND contraseña = ?");
                pt.setString(1, usuario);
                pt.setString(2, contraseña);
                rs = pt.executeQuery();
                if (rs.next()) {
                    return "docente";
                }
            }
            rs.close(); pt.close();  cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
}