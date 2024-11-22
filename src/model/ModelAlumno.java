package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilily.ConexionBD;


public class ModelAlumno {
    private String codigo;
    private String apellidos;
    private String nombres;
    private String carrera;
    private String DNI;
    private String direccion;
    private String correo;

    public ModelAlumno(String codigo, String apellidos, String nombres, String carrera, String DNI, String direccion, String correo) {
        this.codigo = codigo;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.carrera = carrera;
        this.DNI = DNI;
        this.direccion = direccion;
        this.correo = correo;
    }

    public ModelAlumno() {
        this.codigo = "";
        this.apellidos = "";
        this.nombres = "";
        this.carrera = "";
        this.DNI = "";
        this.direccion = "";
        this.correo = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    Connection        cn = null;
    PreparedStatement pt = null;
    Statement         st = null;
    ResultSet         rs = null;
    
    public boolean registrarAlumno(ModelAlumno alumno) {
        String consulta = "INSERT INTO alumno (apellidos, nombres, id_carrera, DNI, direccion, usuario, id_alumno) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return actualizarLista(consulta, alumno);
    }
        
    public boolean modificarAlumno(ModelAlumno alumno) {
        String consulta = "UPDATE alumno SET apellidos = ?, nombres = ?, id_carrera = ?, DNI = ?, direccion = ?, usuario = ? WHERE id_alumno = ?";
        return actualizarLista(consulta, alumno);
    }

    private boolean actualizarLista(String consulta, ModelAlumno alumno) {
        try (Connection con = ConexionBD.getConexionBD();
            PreparedStatement ps = con.prepareStatement(consulta)) {    
            ps.setString(1, alumno.getApellidos());        
            ps.setString(2, alumno.getNombres());
            ps.setInt(3, obtenerIdCarrera(alumno.getCarrera()));
            ps.setString(4, alumno.getDNI());
            ps.setString(5, alumno.getDireccion());
            ps.setString(6, alumno.getCorreo());
            ps.setString(7, alumno.getCodigo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public ModelAlumno completarAlumno(String codigo) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_alumno, nombres, apellidos, DNI, direccion FROM alumno WHERE id_alumno = ?");
            pt.setString(1, codigo);
            rs = pt.executeQuery();

            if (rs.next()) {
                String lnombres = rs.getString("nombres");
                String lapellidos = rs.getString("apellidos");
                String lDNI = rs.getString("DNI");
                String ldireccion = rs.getString("direccion");

                return new ModelAlumno(codigo, lapellidos, lnombres, "", lDNI, ldireccion, "");
            }
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return null;
    }
    
    public boolean eliminarAlumno(ModelAlumno alumno) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM alumno WHERE id_alumno = ?");
            pt.setString(1, alumno.getCodigo());
            
            int result = pt.executeUpdate();
            pt.close(); cn.close();
            return result > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
        
    public List<ModelAlumno> obtenerListaAlumnos() {
        List<ModelAlumno> alumnos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            st = cn.createStatement();
            rs = st.executeQuery(
                "SELECT a.id_alumno, a.apellidos, a.nombres, a.DNI, a.direccion, a.usuario, c.abrev_carrera AS carrera " +
                "FROM alumno a " +
                "JOIN carrera c ON a.id_carrera = c.id_carrera");
            while (rs.next()) {
                alumnos.add(new ModelAlumno(
                        rs.getString("id_alumno"),
                        rs.getString("apellidos"),
                        rs.getString("nombres"),
                        rs.getString("carrera"),
                        rs.getString("DNI"),
                        rs.getString("direccion"),
                        rs.getString("usuario")
                ));
            }
            rs.close(); st.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
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
        } catch (SQLException e) {System.out.println(e);}
        return carreras;
    }

    public int obtenerIdCarrera(String nombreCarrera) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT id_carrera FROM carrera WHERE nombre_carrera = ?");
            pt.setString(1, nombreCarrera);
            rs = pt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_carrera");
                }
            
            rs.close(); pt.close(); cn.close();
        } catch (SQLException e) {System.out.println(e);}
        return -1;
    }
}