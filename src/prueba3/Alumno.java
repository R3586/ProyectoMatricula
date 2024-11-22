package prueba3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utilily.ConexionBD;

public class Alumno {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private String carrera;

    public Alumno(String codigo, String nombres, String apellidos, String direccion, String correo, String carrera) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.carrera = carrera;
    }

    public Alumno() {
        this.codigo = "";
        this.nombres = "";
        this.apellidos = "";
        this.direccion = "";
        this.correo = "";
        this.carrera = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    Connection        cn = null;
    PreparedStatement pt = null;
    Statement         st = null;
    ResultSet         rs = null;
    
    public boolean registrarAlumno(Alumno alumno) {
        String consulta = "INSERT INTO alumno (nombres, apellidos, correo, direccion, id_carrera, id_alumno) VALUES (?, ?, ?, ?, ?, ?)";
        return actualizarLista(consulta, alumno);
    }
        
    public boolean modificarAlumno(Alumno alumno) {
        String consulta = "UPDATE alumno SET nombres = ?, apellidos = ?, direccion = ?, correo = ?, id_carrera = ? WHERE id_alumno = ?";
        return actualizarLista(consulta, alumno);
    }

    private boolean actualizarLista(String consulta, Alumno alumno) {
        try (Connection con = ConexionBD.getConexionBD();
            PreparedStatement ps = con.prepareStatement(consulta)) {
            
            ps.setString(1, alumno.getNombres());
            ps.setString(2, alumno.getApellidos());
            ps.setString(3, alumno.getDireccion());
            ps.setString(4, alumno.getCorreo());
            ps.setInt(5, obtenerIdCarrera(alumno.getCarrera()));
            ps.setString(6, alumno.getCodigo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean eliminarAlumno(Alumno alumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM alumno WHERE id_alumno = ?");
            pt.setString(1, alumno.getCodigo());            
            int result = pt.executeUpdate();
            pt.close(); cn.close();
            return result > 0;
        } catch (SQLException e) {
            return false;
        }
    }
        
    public List<Alumno> obtenerListaAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            st = cn.createStatement();
            rs = st.executeQuery(
                "SELECT a.id_alumno, a.nombres, a.apellidos, a.correo, a.direccion, c.nombre_carrera AS carrera " +
                "FROM alumno a " +
                "JOIN carrera c ON a.id_carrera = c.id_carrera");
            while (rs.next()) {
                alumnos.add(new Alumno(
                        rs.getString("id_alumno"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getString("carrera")
                ));
            }
            rs.close(); st.close(); cn.close();
        } catch (SQLException e) {}
        return alumnos;
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
        } catch (SQLException e) {}
        return carreras;
    }

    private int obtenerIdCarrera(String nombreCarrera) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_carrera FROM carrera WHERE nombre_carrera = ?");
            pt.setString(1, nombreCarrera);
            rs = pt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_carrera");
            }            
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {}
        return -1;
    }
}