package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utilidad.ConexionBD;

public class ModelMatricula_std {
    Connection        cn = null;
    PreparedStatement pt = null;
    ResultSet         rs = null;
    
    public String obtenerNombreCarrera(String codigoAlumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT c.nombre_carrera FROM alumno a " +
                     "JOIN carrera c ON a.id_carrera = c.id_carrera " +
                     "WHERE a.id_alumno = ?");
            pt.setString(1, codigoAlumno);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_carrera");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
    
     public String obtenerNombreEscuela(String codigoAlumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT c.nombre_escuela FROM alumno a " +
                     "JOIN carrera c ON a.id_carrera = c.id_carrera " +
                     "WHERE a.id_alumno = ?");
            pt.setString(1, codigoAlumno);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_escuela");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
    
    public int obtenerIdCarreraPorAlumno(String codigoAlumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_carrera FROM alumno WHERE id_alumno = ?");
            pt.setString(1, codigoAlumno);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_carrera");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return -1;
    }

    public List<Integer> obtenerAños() {
        List<Integer> años = new ArrayList<>();
        try {            
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT DISTINCT año FROM cursos ORDER BY año");
            rs = pt.executeQuery();
            while (rs.next()) {
                años.add(rs.getInt("año"));
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return años;
    }
    
    public List<Integer> obtenerCiclos(int año) {
        List<Integer> ciclos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT DISTINCT ciclo FROM cursos WHERE año = ? ORDER BY ciclo");
            pt.setInt(1, año);
            rs = pt.executeQuery();
            while (rs.next()) {
                ciclos.add(rs.getInt("ciclo"));
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return ciclos;
    }

    public List<String> obtenerAsignaturas(int idCarrera, int año, int ciclo) {
        List<String> asignaturas = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT asignatura FROM cursos WHERE id_carrera = ? AND año = ? AND ciclo = ?");
            pt.setInt(1, idCarrera);
            pt.setInt(2, año);
            pt.setInt(3, ciclo);
            rs = pt.executeQuery();
            while (rs.next()) {
                asignaturas.add(rs.getString("asignatura"));
            }            
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return asignaturas;
    }
}
