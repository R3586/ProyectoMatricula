package controller;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.ModelMatricula;
import view.FrmPrincipal;

public class CtrMatricula {
    FrmPrincipal vista;
    ModelMatricula modelo;

    public CtrMatricula(FrmPrincipal vista) {
        this.vista = vista;
        modelo = new ModelMatricula();        
    }
    
    public void buscarAlumno() {
        String codigoAlumno = vista.txt2CodAlumno.getText();
        String nombreAlumno = modelo.obtenerNombresAlumno(codigoAlumno);        
        String apellidoAlumno = modelo.obtenerApellidosAlumno(codigoAlumno);
        String nombreCarrera = modelo.obtenerNombreCarrera(codigoAlumno);
        String nombreEscuela = modelo.obtenerNombreEscuela(codigoAlumno);

        if (nombreAlumno != null && nombreCarrera != null) {
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