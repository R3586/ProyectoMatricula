package test;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class ModeloCurso {
    List<String> cursosPrincipales;
    List<String> años;
    List<String> ciclos;
    List<String> asignaturas;

    public ModeloCurso() {
        cursosPrincipales = new ArrayList<>();
        años = new ArrayList<>();
        ciclos = new ArrayList<>();
        asignaturas = new ArrayList<>();
    }

    public List<String> getCursosPrincipales() {
        return cursosPrincipales;
    }

    public void setCursosPrincipales(List<String> cursosPrincipales) {
        this.cursosPrincipales = cursosPrincipales;
    }

    public List<String> getAños() {
        return años;
    }

    public void setAños(List<String> años) {
        this.años = años;
    }

    public List<String> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<String> ciclos) {
        this.ciclos = ciclos;
    }

    public List<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<String> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;     
    String sql = null;
    
    public void conectar() throws SQLException{       
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/matriculaunfv", "root", "");        
    }
    public List<String> obtenerCursosPrincipales() {
        List<String> cursos = new ArrayList<>();
        try {
            conectar();
            sql = "SELECT nombre FROM carrera";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cursos.add(rs.getString("nombre"));
            }
            rs.close(); ps.close(); cn.close();
        } catch (SQLException e) {
        }
        return cursos;
    }

    public List<String> obtenerAniosPorCurso(String carrera) {        
        List<String> años = new ArrayList<>();
        try {
            conectar();
            sql = "SELECT DISTINCT año FROM cursos JOIN carrera ON cursos.id_carrera = carrera.id_carrera WHERE carrera.nombre = ?"; 
            ps = cn.prepareStatement(sql);
            ps.setString(1, carrera);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                años.add(rs.getString("año"));
            }
            rs.close(); ps.close(); cn.close();
        } catch (SQLException e) {
        }
        return años;
    }

    public List<String> obtenerCiclosPorAnio(String carrera, String años) {
        List<String> ciclos = new ArrayList<>();
        try {
            conectar();
            sql = "SELECT DISTINCT ciclo FROM cursos JOIN carrera ON cursos.id_carrera = carrera.id_carrera WHERE carrera.nombre = ? AND cursos.año = ?"; 
            ps = cn.prepareStatement(sql);
            ps.setString(1, carrera);
            ps.setString(2, años);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ciclos.add(rs.getString("ciclo"));
            }
            rs.close(); ps.close(); cn.close();
        } catch (SQLException e) {
        }
        return ciclos;
    }

    public List<String> obtenerCursosPorCiclo(String carrera, String años, String ciclo) {
        List<String> cursosDisponibles = new ArrayList<>();
        try {
            conectar();
            sql = "SELECT asignatura FROM cursos JOIN carrera ON cursos.id_carrera = carrera.id_carrera WHERE carrera.nombre = ? AND cursos.año = ? AND cursos.ciclo = ?"; 
            ps = cn.prepareStatement(sql);
            ps.setString(1, carrera);
            ps.setString(2, años);
            ps.setString(3, ciclo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cursosDisponibles.add(rs.getString("asignatura"));
            }
            rs.close(); ps.close(); cn.close();
        } catch (SQLException e) {
        }
        return cursosDisponibles;
    }
    
}