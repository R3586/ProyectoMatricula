package prueba2;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MatriculaModelo {

    private Connection conexion;
    private Map<String, Map<String, Integer>> cuposPorSeccion;

    public MatriculaModelo() {
        // Simulación de cupos disponibles por sección
        cuposPorSeccion = new HashMap<>();
        cuposPorSeccion.put("Curso1", Map.of("A", 5, "B", 10, "C", 0)); // Ejemplo: Curso1 con cupos
        cuposPorSeccion.put("Curso2", Map.of("A", 2, "B", 1, "C", 3));
        try {
            // Configura tu conexión a la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculaunfv", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int obtenerCuposRestantes(String curso, String seccion) {
        if (cuposPorSeccion.containsKey(curso) && cuposPorSeccion.get(curso).containsKey(seccion)) {
            return cuposPorSeccion.get(curso).get(seccion);
        }
        return 0;
    }
    // Obtener lista de carreras
    public ArrayList<String> obtenerCarreras() {
        ArrayList<String> carreras = new ArrayList<>();
        try {
            String query = "SELECT nombre_carrera FROM carrera";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                carreras.add(rs.getString("nombre_carrera"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carreras;
    }

    // Obtener lista de cursos por carrera
    public ArrayList<String> obtenerCursos(String carrera) {
        ArrayList<String> cursos = new ArrayList<>();
        try {
            String query = "SELECT asignatura FROM cursos WHERE id_carrera = (SELECT id_carrera FROM carrera WHERE nombre_carrera = ?)";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, carrera);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                cursos.add(rs.getString("asignatura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    // Obtener lista de secciones por curso
    public ArrayList<String> obtenerSecciones(String curso) {
        ArrayList<String> secciones = new ArrayList<>();
        try {
            String query = "SELECT nombre_seccion FROM seccion WHERE id_curso = (SELECT id_curso FROM cursos WHERE asignatura = ?)";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, curso);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                secciones.add(rs.getString("nombre_seccion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return secciones;
    }

    // Validar cupos disponibles
    public boolean validarCupos(String seccion) {
        try {
            String query = "SELECT cupo_maximo - (SELECT COUNT(*) FROM matricula WHERE id_seccion = (SELECT id_seccion FROM seccion WHERE nombre_seccion = ?)) AS cupos_disponibles FROM seccion WHERE nombre_seccion = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, seccion);
            pstmt.setString(2, seccion);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("cupos_disponibles") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Agregar matrícula
    public boolean agregarMatricula(int idAlumno, String seccion) {
        try {
            if (!validarCupos(seccion)) {
                throw new Exception("No hay cupos disponibles en esta sección.");
            }
            String query = "INSERT INTO matricula (id_alumno, id_seccion, estado) VALUES (?, (SELECT id_seccion FROM seccion WHERE nombre_seccion = ?), 'activo')";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, idAlumno);
            pstmt.setString(2, seccion);
            return pstmt.executeUpdate() > 0;
//        } catch (SQLException | Exception e) {
//            e.printStackTrace();
        } catch(Exception e){
            
        }
        return false;
    }
}
