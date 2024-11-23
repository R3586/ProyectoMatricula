package model;

import java.sql.*;
import utilily.ConexionBD;

public class ModelLogin {       
    Connection        cn = null;
    PreparedStatement pt = null;
    ResultSet         rs = null;   
    String CodUsuario, NomUsuario, ApeUsuario;

    public String getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(String CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String NomUsuario) {
        this.NomUsuario = NomUsuario;
    }

    public String getApeUsuario() {
        return ApeUsuario;
    }

    public void setApeUsuario(String ApeUsuario) {
        this.ApeUsuario = ApeUsuario;
    }
    
    public String validarLogin(String usuario, String contraseña) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_consejero, nombres, apellidos, usuario, contraseña FROM consejero WHERE usuario = ? AND contraseña = ?");
            pt.setString(1, usuario);
            pt.setString(2, contraseña);
            rs = pt.executeQuery();
            if (rs.next()) {
                setCodUsuario(rs.getString("id_consejero"));
                setNomUsuario(rs.getString("nombres"));
                setApeUsuario(rs.getString("apellidos"));
                rs.close(); pt.close(); cn.close();
                return "consejero";
            } else {
                rs.close(); pt.close();
                pt = cn.prepareStatement("SELECT id_alumno, nombres, apellidos, usuario, contraseña FROM alumno WHERE usuario = ? AND contraseña = ?");
                pt.setString(1, usuario);
                pt.setString(2, contraseña);
                rs = pt.executeQuery();
                if (rs.next()) {
                    setCodUsuario(rs.getString("id_alumno"));
                    setNomUsuario(rs.getString("nombres"));
                    setApeUsuario(rs.getString("apellidos"));
                    rs.close(); pt.close(); cn.close();
                    return "alumno";
                } 
                rs.close(); pt.close();
                pt = cn.prepareStatement("SELECT id_alumno, nombres, apellidos, usuario, contraseña FROM alumno WHERE usuario = ? AND contraseña IS NULL");
                pt.setString(1, usuario);
                rs = pt.executeQuery();
                if (rs.next()) {
                    setCodUsuario(rs.getString("id_alumno"));
                    setNomUsuario(rs.getString("nombres"));
                    setApeUsuario(rs.getString("apellidos"));
                    rs.close(); pt.close(); cn.close();
                    return "alumno-noPass";
                }
                rs.close(); pt.close(); cn.close();
            }
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
}