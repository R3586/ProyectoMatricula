package Controlador;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.ModelHistorial_std;
import utilidad.Recursos;
import Vista.FrmPrincipal_std;


public class CtrHistorial_std {
    FrmPrincipal_std vista;
    ModelHistorial_std modelo;
    Recursos r;
    
    public CtrHistorial_std(FrmPrincipal_std vista) {
        this.vista = vista;
        modelo = new ModelHistorial_std(); 
        r = new Recursos();
    }
    
    public void cargarAlumno(){
        vista.txt3CodAlumno.setText(r.CodUsuario);
        String codigo = vista.txt3CodAlumno.getText();
        modelo.completarAlumno(codigo);
        vista.txt3Alumno.setText(modelo.alumno);
        vista.txt3Carrera.setText(modelo.carrera);
        vista.txt3Escuela.setText(modelo.escuela);  
        cargarAños();
    }

    public void cargarElementosHistorial() {
        String codigo = vista.txt3CodAlumno.getText();
        modelo.completarAlumno(codigo);
            List<ModelHistorial_std> lista = modelo.obtenerHistorial(codigo); 
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblHistorial.getModel();
            modeloTabla.setRowCount(0);
            for (ModelHistorial_std historial : lista) {
                modeloTabla.addRow(new Object[]{
                    historial.getId_curso(),
                    historial.getAño(),
                    historial.getCiclo(),
                    historial.getCurso(),
                    historial.getEP(),
                    historial.getEF(),
                    historial.getPP(),
                    historial.getSUST(),
                    historial.getPROM()
                });
            }
    }
    
    public void cargarElementosHistorialAño() {
        String codigo = vista.txt3CodAlumno.getText();
        modelo.completarAlumno(codigo);
        String año = (String) vista.cbo3Años.getSelectedItem();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<ModelHistorial_std> lista = modelo.obtenerHistorialAño(codigo, año); 
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblHistorial.getModel();
            modeloTabla.setRowCount(0);
            for (ModelHistorial_std historial : lista) {
                modeloTabla.addRow(new Object[]{
                    historial.getId_curso(),
                    historial.getAño(),
                    historial.getCiclo(),
                    historial.getCurso(),
                    historial.getEP(),
                    historial.getEF(),
                    historial.getPP(),
                    historial.getSUST(),
                    historial.getPROM()
                });
            }
        }
    }
    
    public void cargarElementosHistorialCiclo() {
        String codigo = vista.txt3CodAlumno.getText();
        modelo.completarAlumno(codigo);
        String ciclo = (String) vista.cbo3Ciclos.getSelectedItem();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<ModelHistorial_std> lista = modelo.obtenerHistorialCiclo(codigo, ciclo); 
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblHistorial.getModel();
            modeloTabla.setRowCount(0);
            for (ModelHistorial_std historial : lista) {
                modeloTabla.addRow(new Object[]{
                    historial.getId_curso(),
                    historial.getAño(),
                    historial.getCiclo(),
                    historial.getCurso(),
                    historial.getEP(),
                    historial.getEF(),
                    historial.getPP(),
                    historial.getSUST(),
                    historial.getPROM()
                });
            }
        }
    }
    
    public void cargarAños() {        
        List<Integer> años = modelo.obtenerAños();
        actualizarComboBox(vista.cbo3Años, años);
        cargarCiclos();
    }
    
    public void cargarCiclos() {
        if (vista.cbo3Años.getSelectedItem() != null) {
            int año = Integer.parseInt(vista.cbo3Años.getSelectedItem().toString());
            List<Integer> ciclos = modelo.obtenerCiclos(año);
            actualizarComboBox(vista.cbo3Ciclos, ciclos);
        }
    }

    public void actualizarComboBox(JComboBox<String> cbo, java.util.List<?> items) {
        cbo.removeAllItems();
        for (Object item : items) {
            cbo.addItem(item.toString());
        }
    }
    
}
