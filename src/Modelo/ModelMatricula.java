package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utilidad.ConexionBD;

public class ModelMatricula {
    Connection        cn = null;
    PreparedStatement pt = null;
    Statement         st = null;
    ResultSet         rs = null;
    String codalumno,carrera, año, ciclo, asignatura, codcurso, seccion, idvoucher, monto, creditos, periodo;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public ModelMatricula(String codalumno, String año, String ciclo, String asignatura, String codcurso, String seccion, String idvoucher, String creditos, String periodo) {
        this.codalumno = codalumno;
        this.año = año;
        this.ciclo = ciclo;
        this.asignatura = asignatura;
        this.codcurso = codcurso;
        this.seccion = seccion;
        this.idvoucher = idvoucher;
        this.creditos = creditos;
        this.periodo = periodo;
    }
    
    public ModelMatricula(){
        this.codalumno = "";
        this.año = "";
        this.ciclo = "";
        this.asignatura = "";
        this.codcurso = "";
        this.seccion = "";
        this.idvoucher = "";
        this.creditos = "";
    }
    
    public String getCodalumno() {
        return codalumno;
    }

    public void setCodalumno(String codalumno) {
        this.codalumno = codalumno;
    }
    
    public String getMonto() {
        return monto;
    }

    public void setMontovoucher(String monto) {
        this.monto = monto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCodcurso() {
        return codcurso;
    }

    public void setCodcurso(String codcurso) {
        this.codcurso = codcurso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getIdvoucher() {
        return idvoucher;
    }

    public void setIdvoucher(String idvoucher) {
        this.idvoucher = idvoucher;
    }
    
    public String obtenerNombresAlumno(String codigoAlumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT nombres FROM alumno WHERE id_alumno = ?");
            pt.setString(1, codigoAlumno);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombres");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
    
    public String obtenerApellidosAlumno(String codigoAlumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT apellidos FROM alumno WHERE id_alumno = ?");
            pt.setString(1, codigoAlumno);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getString("apellidos");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }

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
    
    public List<String> obtenerPeriodo() {
        List<String> periodos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT periodo FROM periodo");
            rs = pt.executeQuery();
            while (rs.next()) {
                periodos.add(rs.getString("periodo"));
            }            
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return periodos;
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
    
    public String obtenerIdPeriodo(String periodo) {
        String idPeriodo;
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_periodo FROM periodo WHERE periodo = ?");
            pt.setString(1, periodo);
            rs = pt.executeQuery();
            if (rs.next()) {
                idPeriodo = rs.getString("id_periodo");                
                return idPeriodo;
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
    
    public boolean registrarVoucherCurso(String voucher, String alumno,String id_curso,String monto) {
        String query = "INSERT INTO voucher_pago (id_voucher, id_alumno, id_curso, monto) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexionBD();
            PreparedStatement ps = con.prepareStatement(query)) {    
            ps.setString(1, voucher);
            ps.setString(2,alumno);
            ps.setString(3,id_curso);    
            ps.setString(4,monto);   

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {System.out.println(e);}
        return false;
    }
    
    public boolean registrarCursoMatricula(String alumno, String id_curso, String seccion, String voucher, String periodo) {
        String query = "INSERT INTO matricula (id_alumno, id_curso, id_seccion, id_voucher, id_periodo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexionBD();
            PreparedStatement ps = con.prepareStatement(query)) {    
            ps.setString(1,alumno);
            ps.setString(2,id_curso);
            ps.setString(3,seccion);    
            ps.setString(4,voucher);    
            ps.setString(5,periodo);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {System.out.println(e);}
        return false;
    }
    
    public boolean registrarHistorial(String alumno, String id_curso, String seccion, String periodo) {
        String query = "INSERT INTO historial_academico (id_alumno, id_curso, id_seccion, id_periodo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexionBD();
            PreparedStatement ps = con.prepareStatement(query)) {    
            ps.setString(1, alumno);   
            ps.setString(2, id_curso);  
            ps.setString(3, seccion);
            ps.setString(4, periodo);   

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {System.out.println(e);}
        return false;
    }
    
    public List<ModelMatricula> obtenerMatricula(String codigo, String periodo) {
        List<ModelMatricula> matricula = new ArrayList<>();
        String query =  """
                        SELECT DISTINCT m.id_curso, c.asignatura, p.periodo, c.año, c.ciclo, s. nombre_seccion, c.creditos, m.id_voucher
                        FROM matricula m 
                        INNER JOIN periodo p ON m.id_periodo = p.id_periodo
                        INNER JOIN cursos c ON m.id_curso = c.id_curso
                        INNER JOIN seccion s ON m.id_seccion  = s.id_seccion
                        INNER JOIN voucher_pago v ON m.id_voucher = v.id_voucher
                        WHERE m.id_alumno = ? AND p.periodo = ?
                        """;

        try (Connection cn = ConexionBD.getConexionBD();
            PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, codigo);
            ps.setString(2, periodo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    matricula.add(new ModelMatricula(
                            rs.getString("año"),
                            rs.getString("año"),  
                            rs.getString("ciclo"),
                            rs.getString("asignatura"),
                            rs.getString("id_curso"),
                            rs.getString("nombre_seccion"),
                            rs.getString("id_voucher"),
                            rs.getString("creditos"),
                            rs.getString("periodo")
                    ));                    
                }
            }
        } catch (SQLException e) {System.out.println(e);}
        return matricula;
    }
}
