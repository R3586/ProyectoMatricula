package prueba3;

import java.sql.*;
import javax.swing.JFrame;
import utilily.ConexionBD;

public class Main {
    public static void main(String[] args) {
        VistaInscripcionAlumno vista = new VistaInscripcionAlumno();
        
        // Crear el controlador, que manejará la lógica
        new CtrInscripcionAlumno(vista);
        
        // Configurar la vista para que sea visible
        vista.setVisible(true);
    }
}