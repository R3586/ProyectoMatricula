package Modelo;

import java.sql.*;
import utilidad.ConexionBD;

public class ModelLogin {       
    String CodUsuario, NomUsuario, ApeUsuario, Usuario;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    Connection        cn = null;
    PreparedStatement pt = null;
    ResultSet         rs = null;   

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
            pt = cn.prepareStatement("SELECT id_admin, nombres, apellidos, usuario, contraseña FROM administrativo WHERE usuario = ? AND contraseña = ?");
            pt.setString(1, usuario);
            pt.setString(2, contraseña);
            rs = pt.executeQuery();
            if (rs.next()) {
                setCodUsuario(rs.getString("id_admin"));
                setNomUsuario(rs.getString("nombres"));
                setApeUsuario(rs.getString("apellidos"));
                setUsuario(rs.getString("usuario"));
                rs.close(); pt.close(); cn.close();
                return "admin";
            } 
            rs.close(); pt.close();
            pt = cn.prepareStatement("SELECT id_docente, nombres, apellidos, usuario, contraseña FROM docente WHERE usuario = ? AND contraseña = ?");
            pt.setString(1, usuario);
            pt.setString(2, contraseña);
            rs = pt.executeQuery();
            if (rs.next()) {
                setCodUsuario(rs.getString("id_docente"));
                setNomUsuario(rs.getString("nombres"));
                setApeUsuario(rs.getString("apellidos"));
                setUsuario(rs.getString("usuario"));
                rs.close(); pt.close(); cn.close();
                return "docente";
            } 
            rs.close(); pt.close();
            pt = cn.prepareStatement("SELECT id_alumno, nombres, apellidos, usuario, contraseña FROM alumno WHERE usuario = ? AND contraseña = ?");
            pt.setString(1, usuario);
            pt.setString(2, contraseña);
            rs = pt.executeQuery();
            if (rs.next()) {
                setCodUsuario(rs.getString("id_alumno"));
                setNomUsuario(rs.getString("nombres"));
                setApeUsuario(rs.getString("apellidos"));
                setUsuario(rs.getString("usuario"));
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
                setUsuario(rs.getString("usuario"));
                rs.close(); pt.close(); cn.close();
                return "alumno-noPass";
            }
            rs.close(); pt.close(); cn.close();        
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
}