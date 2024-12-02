package Controlador;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Modelo.ModelMatricula_std;
import utilidad.Recursos;
import Vista.FrmPrincipal_std;

public class CtrMatricula_std {
    FrmPrincipal_std vista;
    ModelMatricula_std modelo;
    Recursos r;
    public CtrMatricula_std(FrmPrincipal_std vista) {
        this.vista = vista;
        modelo = new ModelMatricula_std(); 
        r = new Recursos();
    }
    
    public void buscarAlumno() {
        String codigoAlumno = r.CodUsuario;
        String nombreAlumno = r.NomUsuario;     
        String apellidoAlumno = r.ApeUsuario;
        String nombreCarrera = modelo.obtenerNombreCarrera(codigoAlumno);
        String nombreEscuela = modelo.obtenerNombreEscuela(codigoAlumno);

        if (nombreAlumno != null && nombreCarrera != null) {
            vista.txt2CodAlumno.setText(codigoAlumno);
            vista.txt2Alumno.setText(apellidoAlumno + ", " + nombreAlumno);
            vista.txt2Carrera.setText(nombreCarrera);
            vista.txt2Escuela.setText(nombreEscuela);
            vista.cbo2Seccion.addItem("A");
            vista.cbo2Seccion.addItem("B");
            vista.cbo2Seccion.addItem("C");
        } else {
            JOptionPane.showMessageDialog(null, "Alumno no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarAños() {        
        List<Integer> años = modelo.obtenerAños();
        actualizarComboBox(vista.cbo2Años, años);
        cargarCiclos();
        cargarAsignaturas();
    }
    
    public void cargarCiclos() {
        if (vista.cbo2Años.getSelectedItem() != null) {
            int año = Integer.parseInt(vista.cbo2Años.getSelectedItem().toString());
            List<Integer> ciclos = modelo.obtenerCiclos(año);
            actualizarComboBox(vista.cbo2Ciclos, ciclos);
        }
        cargarAsignaturas();
    }

    public void cargarAsignaturas() {
        String codigoAlumno = vista.txt2CodAlumno.getText();
        int idCarrera = modelo.obtenerIdCarreraPorAlumno(codigoAlumno);

        if (vista.cbo2Años.getSelectedItem() != null && vista.cbo2Ciclos.getSelectedItem() != null) {
            int año = Integer.parseInt(vista.cbo2Años.getSelectedItem().toString());
            int ciclo = Integer.parseInt(vista.cbo2Ciclos.getSelectedItem().toString());
            List<String> asignaturas = modelo.obtenerAsignaturas(idCarrera, año, ciclo);
            actualizarComboBox(vista.cbo2Cursos, asignaturas);
        }
    }
     public void actualizarComboBox(JComboBox<String> cbo, java.util.List<?> items) {
        cbo.removeAllItems();
        for (Object item : items) {
            cbo.addItem(item.toString());
        }
    }
}