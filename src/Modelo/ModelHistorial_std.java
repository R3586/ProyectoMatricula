package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utilidad.ConexionBD;

public class ModelHistorial_std {
    Connection        cn = null;
    PreparedStatement pt = null;
    ResultSet         rs = null;
    Statement         st = null;
    String id_alumno, id_carrera, id_curso, año, ciclo, curso , EP, EF, PP, SUST, PROM;

    public ModelHistorial_std(String id_alumno, String id_carrera, String id_curso, String año, String ciclo, String curso, String EP, String EF, String PP, String SUST, String PROM) {
        this.id_alumno = id_alumno;
        this.id_carrera = id_carrera;
        this.id_curso = id_curso;
        this.año = año;
        this.ciclo = ciclo;
        this.curso = curso;
        this.EP = EP;
        this.EF = EF;
        this.PP = PP;
        this.SUST = SUST;
        this.PROM = PROM;
    }

    public ModelHistorial_std() {
        this.id_alumno = "";
        this.id_carrera = "";
        this.id_curso = "";
        this.año = "";
        this.ciclo = "";
        this.curso = "";
        this.EP = "";
        this.EF = "";
        this.PP = "";
        this.SUST = "";
        this.PROM = "";
    }
    
    public String getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(String id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEP() {
        return EP;
    }

    public void setEP(String EP) {
        this.EP = EP;
    }

    public String getEF() {
        return EF;
    }

    public void setEF(String EF) {
        this.EF = EF;
    }

    public String getPP() {
        return PP;
    }

    public void setPP(String PP) {
        this.PP = PP;
    }

    public String getSUST() {
        return SUST;
    }

    public void setSUST(String SUST) {
        this.SUST = SUST;
    }

    public String getPROM() {
        return PROM;
    }

    public void setPROM(String PROM) {
        this.PROM = PROM;
    }
            
    public List<ModelHistorial_std> obtenerHistorial(String codigo) {
        List<ModelHistorial_std> historial = new ArrayList<>();
        String query =  """
                        SELECT DISTINCT h.id_curso, c.año, c.ciclo, c.asignatura, h.ep, h.ef, h.pp, h.sust, ROUND(h.nota_promedio) AS prom 
                        FROM historial_academico h 
                        INNER JOIN cursos c ON h.id_curso = c.id_curso 
                        WHERE h.id_alumno = ?
                        ORDER BY c.ciclo, c.asignatura;
                        """;

        try (Connection cn = ConexionBD.getConexionBD();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historial.add(new ModelHistorial_std(
                            rs.getString("pp"),  
                            rs.getString("pp"),
                            rs.getString("id_curso"),
                            rs.getString("año"),
                            rs.getString("ciclo"),
                            rs.getString("asignatura"),
                            rs.getString("ep"),
                            rs.getString("ef"),
                            rs.getString("pp"),
                            rs.getString("sust"),
                            rs.getString("prom")
                    ));
                }
            }
        } catch (SQLException e) {System.out.println(e);}
        return historial;
    }
    
    public List<ModelHistorial_std> obtenerHistorialAño(String codigo, String año) {
        List<ModelHistorial_std> historial = new ArrayList<>();
        String query =  """
                        SELECT DISTINCT h.id_curso, c.año, c.ciclo, c.asignatura, h.ep, h.ef, h.pp, h.sust, ROUND(h.nota_promedio) AS prom 
                        FROM historial_academico h 
                        INNER JOIN cursos c ON h.id_curso = c.id_curso 
                        WHERE h.id_alumno = ? AND c.año = ?
                        ORDER BY c.ciclo, c.asignatura;
                        """;

        try (Connection cn = ConexionBD.getConexionBD();
            PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, codigo);
            ps.setString(2, año);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historial.add(new ModelHistorial_std(
                            rs.getString("pp"),  
                            rs.getString("pp"),
                            rs.getString("id_curso"),
                            rs.getString("año"),
                            rs.getString("ciclo"),
                            rs.getString("asignatura"),
                            rs.getString("ep"),
                            rs.getString("ef"),
                            rs.getString("pp"),
                            rs.getString("sust"),
                            rs.getString("prom")
                    ));
                }
            }
        } catch (SQLException e) {System.out.println(e);}
        return historial;
    }
    
    public List<ModelHistorial_std> obtenerHistorialCiclo(String codigo, String ciclo) {
        List<ModelHistorial_std> historial = new ArrayList<>();
        String query =  """
                        SELECT DISTINCT h.id_curso, c.año, c.ciclo, c.asignatura, h.ep, h.ef, h.pp, h.sust, ROUND(h.nota_promedio) AS prom 
                        FROM historial_academico h 
                        INNER JOIN cursos c ON h.id_curso = c.id_curso 
                        WHERE h.id_alumno = ? AND c.ciclo = ?
                        ORDER BY c.ciclo, c.asignatura;
                        """;

        try (Connection cn = ConexionBD.getConexionBD();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, codigo);
            ps.setString(2, ciclo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historial.add(new ModelHistorial_std(
                            rs.getString("pp"),  
                            rs.getString("pp"),
                            rs.getString("id_curso"),
                            rs.getString("año"),
                            rs.getString("ciclo"),
                            rs.getString("asignatura"),
                            rs.getString("ep"),
                            rs.getString("ef"),
                            rs.getString("pp"),
                            rs.getString("sust"),
                            rs.getString("prom")
                    ));
                }
            }
        } catch (SQLException e) {System.out.println(e);}
        return historial;
    }
    
    public String alumno, carrera, escuela;
    public void completarAlumno(String codigo) {
        String query =  """
                        SELECT a.apellidos, a.nombres, c.nombre_carrera, c.nombre_escuela
                        FROM alumno a 
                        JOIN carrera c ON a.id_carrera = c.id_carrera
                        WHERE a.id_alumno = ?
                        """;        
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement(query);
            pt.setString(1, codigo);
            rs = pt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nombres");
                String ape = rs.getString("apellidos");
                alumno = ape + ", " + nom;
                carrera = rs.getString("nombre_carrera");
                escuela = rs.getString("nombre_escuela");
                
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
    }
    
    public List<String> obtenerCarreras() {
        List<String> carreras = new ArrayList<>();
        try {            
            cn = ConexionBD.getConexionBD();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT nombre_carrera FROM carrera");
            while (rs.next()) {
                carreras.add(rs.getString("nombre_carrera"));
            }
            rs.close(); st.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return carreras;
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
    
    public String obtenerIdAsignatura(String asignatura) {
        String idAsignatura;
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_curso FROM cursos WHERE asignatura = ?");
            pt.setString(1, asignatura);
            rs = pt.executeQuery();
            if (rs.next()) {
                idAsignatura = rs.getString("id_curso");                
                return idAsignatura;
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
}