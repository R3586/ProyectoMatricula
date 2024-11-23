package controller;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ModelAlumno;
import view.FrmPrincipal;

public class CtrAlumno {
    FrmPrincipal vista;
    ModelAlumno modelo;

    public CtrAlumno(FrmPrincipal vista) {
        this.vista = vista;
        this.modelo = new ModelAlumno(); 
    }

    public void cargarTablaAlumnos() {
        List<ModelAlumno> listaAlumnos = modelo.obtenerListaAlumnos(); 
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblAlumnos.getModel();
        modeloTabla.setRowCount(0);
        for (ModelAlumno alumno : listaAlumnos) {
            modeloTabla.addRow(new Object[]{
                alumno.getCodigo(),
                alumno.getApellidos(),
                alumno.getNombres(),
                alumno.getCarrera(),
                alumno.getDNI(),
                alumno.getDireccion(),
            });
        }
    }

    public void cargarCarreras() {        
        for (String carrera : modelo.obtenerCarreras()) {
            vista.cbo1Carrera.addItem(carrera);
        }
    }

    public void registrarAlumno() {
        String codigo = vista.txt1Codigo.getText();
        String apellidos = vista.txt1Apellidos.getText();
        String nombres = vista.txt1Nombres.getText();
        String carrera = (String) vista.cbo1Carrera.getSelectedItem();
        String DNI = vista.txt1DNI.getText();
        String direccion = vista.txt1Direccion.getText();
        String usuario = vista.txt1Codigo.getText()+"@unfv.edu.pe";

        if (codigo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || DNI.isEmpty() || direccion.isEmpty() || carrera == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelAlumno nuevoAlumno = new ModelAlumno(codigo, apellidos, nombres, carrera , DNI , direccion, usuario);
        if (modelo.registrarAlumno(nuevoAlumno)) {
            JOptionPane.showMessageDialog(null, "Alumno registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTablaAlumnos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modificarAlumno() {
        String codigo = vista.txt1Codigo.getText(); 
        String apellidos = vista.txt1Apellidos.getText();
        String nombres = vista.txt1Nombres.getText(); 
        String direccion = vista.txt1Direccion.getText();
        String DNI = vista.txt1DNI.getText();
        String carrera = (String) vista.cbo1Carrera.getSelectedItem();
        
        if (codigo.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || direccion.isEmpty() || DNI.isEmpty() || carrera == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelAlumno alumnoModificado = new ModelAlumno(codigo, apellidos, nombres, carrera , DNI , direccion, "");

        if (modelo.modificarAlumno(alumnoModificado)) {
            JOptionPane.showMessageDialog(null, "Alumno modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTablaAlumnos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void completarAlumno() {
        String codigo = vista.txt1Codigo.getText();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un código para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelAlumno alumno = modelo.completarAlumno(codigo);
        if (alumno != null) {
            vista.txt1Nombres.setText(alumno.getNombres());
            vista.txt1Apellidos.setText(alumno.getApellidos());
            vista.txt1DNI.setText(alumno.getCorreo());
            vista.txt1Direccion.setText(alumno.getDireccion());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarAlumno() {
        String codigo = vista.txt1Codigo.getText(); 
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelAlumno alumnoAEliminar = new ModelAlumno(codigo, "", "", "", "", "","");

        if (modelo.eliminarAlumno(alumnoAEliminar)) {
            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTablaAlumnos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void limpiarCampos() {
        vista.txt1Codigo.setText("");
        vista.txt1Nombres.setText("");
        vista.txt1Apellidos.setText("");
        vista.txt1Direccion.setText("");
        vista.txt1DNI.setText("");
        vista.cbo1Carrera.setSelectedIndex(0);
    }
    
}
