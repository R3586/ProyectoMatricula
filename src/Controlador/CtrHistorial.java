package Controlador;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Modelo.ModelHistorial;
import Vista.FrmPrincipal;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CtrHistorial {
    FrmPrincipal vista;
    ModelHistorial modelo;
    public CtrHistorial(FrmPrincipal vista) {
        this.vista = vista;
        modelo = new ModelHistorial(); 
    }

    public void cargarElementosHistorial() {
        String codigo = vista.txt3CodAlumno.getText();
        modelo.completarAlumno(codigo);
        vista.txt3Alumno.setText(modelo.alumno);
        vista.txt3Carrera.setText(modelo.carrera);
        vista.txt3Escuela.setText(modelo.escuela);
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<ModelHistorial> lista = modelo.obtenerHistorial(codigo); 
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblHistorial.getModel();
            modeloTabla.setRowCount(0);
            for (ModelHistorial historial : lista) {
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
            cargarAños();
        }
    }
    
    public void modificarAlumno() {
        String alumno = vista.txt3CodAlumno.getText(); 
        String curso = vista.txt3IdCurso.getText();
        String ep = vista.txt3EP.getText(); 
        String ef = vista.txt3EF.getText();
        String pp = vista.txt3PP.getText();
        String sust = vista.txt3SUST.getText();
        
        if (alumno.isEmpty() || curso.isEmpty() || ep.isEmpty() || ef.isEmpty() || pp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {            
            if (sust.isEmpty()){
                sust = null;
            }
            ModelHistorial historialModificado = new ModelHistorial(alumno, "", curso, "", "", "", ep , ef , pp, sust,"");
            if (modelo.modificarHistorial(historialModificado)) {
                JOptionPane.showMessageDialog(null, "Datos modificados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarElementosHistorial();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el historial.", "Error", JOptionPane.ERROR_MESSAGE);
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
        cargarAsignaturas();
    }

    public void cargarAsignaturas() {
        int idcarrera = switch (modelo.carrera) {
            case "Ingeniería AgroIndustrial" -> 1;
            case "Ingeniería Industrial" -> 2;
            case "Ingeniería de Sistemas" -> 3;
            default -> 4;
        };
        
        if (vista.cbo3Años.getSelectedItem() != null && vista.cbo3Ciclos.getSelectedItem() != null) {
            int año = Integer.parseInt(vista.cbo3Años.getSelectedItem().toString());
            int ciclo = Integer.parseInt(vista.cbo3Ciclos.getSelectedItem().toString());
            List<String> asignaturas = modelo.obtenerAsignaturas(idcarrera, año, ciclo);
            actualizarComboBox(vista.cbo3Cursos, asignaturas);
        }
        cargarIdAsignatura();
    }
    
    public void cargarIdAsignatura(){
        String asignatura = (String) vista.cbo3Cursos.getSelectedItem();
        vista.txt3IdCurso.setText(modelo.obtenerIdAsignatura(asignatura));
    }
    
    public void actualizarComboBox(JComboBox<String> cbo, java.util.List<?> items) {
        cbo.removeAllItems();
        for (Object item : items) {
            cbo.addItem(item.toString());
        }
    }
    
    public void limpiarCampos() {
        vista.txt3IdCurso.setText("");
        vista.txt3EP.setText("");
        vista.txt3EF.setText("");
        vista.txt3PP.setText("");
        vista.txt3SUST.setText("");
    }   
    
    
    
    
    public void generarPDF(JTable tablaNotas, JTextField txtCodigoAlumno, JTextField txtNombreAlumno) {     
        Document document = new Document();        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, 
                    new FileOutputStream(txtCodigoAlumno.getText() + ".pdf"));
            document.open();

            Paragraph titulo = new Paragraph("Reporte de Notas");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" ")); 

            document.add(new Paragraph("Código del Alumno: " + txtCodigoAlumno.getText()));
            document.add(new Paragraph("Nombre del Alumno: " + txtNombreAlumno.getText()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(tablaNotas.getColumnCount());
            table.setWidthPercentage(100);

//            float[] anchosColumnas = {3f, 2f, 2f, 9f,1f,1f,1f,2f,3f};
            float[] anchosColumnas = {2.8f,1.8f,1.8f,10f,1.1f,1.1f,1.1f,2f,2.5f};
            table.setWidths(anchosColumnas);

            for (int i = 0; i < tablaNotas.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tablaNotas.getColumnName(i)));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(cell);
            }

            for (int i = 0; i < tablaNotas.getRowCount(); i++) {
                for (int j = 0; j < tablaNotas.getColumnCount(); j++) {
                    Object valorCelda = tablaNotas.getValueAt(i, j); 
                    PdfPCell cell = new PdfPCell(new Phrase(
                            valorCelda != null ? valorCelda.toString() : ""
                    ));
                    if (j!=3){
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    }
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    table.addCell(cell);
                }
            }
            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Generado automáticamente por el sistema."));
            document.close();
            System.out.println(writer);
        } catch (DocumentException | FileNotFoundException ex) {System.out.println("Error.");} 
        JOptionPane.showMessageDialog(null, "Archivo PDF creado exitosamente.");
    }    
}
