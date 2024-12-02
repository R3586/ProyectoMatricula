package Controlador;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.ModelMatricula;
import Vista.FrmPrincipal;

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
    
    public void cargarPeriodos() {
        List<String> periodos = modelo.obtenerPeriodo();
        actualizarComboBox(vista.cbo2Periodo, periodos);
        vista.cbo2Periodo.setSelectedIndex(1);
    }

    public void actualizarComboBox(JComboBox<String> cbo, java.util.List<?> items) {
        cbo.removeAllItems();
        for (Object item : items) {
            cbo.addItem(item.toString());
        }
    }    
    
    public void registrarCursoMatricula(){
        String alumno = vista.txt2CodAlumno.getText(); 
        String id_curso = modelo.obtenerIdAsignatura(vista.cbo2Cursos.getSelectedItem().toString());        
        String seccion = switch (vista.cbo2Seccion.getSelectedItem().toString()) {
            case "A" -> "1";
            case "B" -> "2";
            default -> "3";
        };        
        String voucher = vista.txt2IdVoucher.getText();
        String periodo = modelo.obtenerIdPeriodo(vista.cbo2Periodo.getSelectedItem().toString());
        String monto = vista.txt2MontoVoucher.getText();        
      
        if (alumno.isEmpty() || id_curso.isEmpty() || voucher.isEmpty() || monto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if(modelo.registrarVoucherCurso(voucher, alumno, id_curso, monto) &&
                modelo.registrarCursoMatricula(alumno,id_curso,seccion,voucher,periodo) &&
                modelo.registrarHistorial(alumno,id_curso,seccion,periodo)) {
                    cargarMatriculaPeriodo();
                    JOptionPane.showMessageDialog(null, "Alumno registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al matricular el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    
    public void cargarMatriculaPeriodo() {
        String codigo = vista.txt2CodAlumno.getText();
        String periodo = vista.cbo2Periodo.getSelectedItem().toString();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<ModelMatricula> lista = modelo.obtenerMatricula(codigo, periodo); 
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblMatricula.getModel();
            modeloTabla.setRowCount(0);
            for (ModelMatricula matricula : lista) {
                modeloTabla.addRow(new Object[]{
                    matricula.getCodcurso(),
                    " " + matricula.getAsignatura(),
                    matricula.getPeriodo(),
                    matricula.getAño(),
                    matricula.getCiclo(),
                    matricula.getSeccion(),
                    matricula.getCreditos(),
                    matricula.getIdvoucher()
                });
            }
        }
    }
    
    public void nuevaMatricula(){     
        vista.cbo2Periodo.setSelectedIndex(0);
        vista.cbo2Años.setSelectedIndex(0);
        vista.cbo2Ciclos.setSelectedIndex(0);
        vista.cbo2Seccion.setSelectedIndex(0);
        vista.txt2IdVoucher.setText("");
        vista.txt2MontoVoucher.setText("");
        vista.cbo2Cursos.removeAllItems();
        cargarMatriculaPeriodo();
        vista.txt2CodAlumno.setText("");
        vista.txt2Alumno.setText("");
        vista.txt2Carrera.setText("");
        vista.txt2Escuela.setText("");
        JOptionPane.showMessageDialog(null, "Matricula finalizada. Seleccione otro alumno.");
    }
}