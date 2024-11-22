package prueba3;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CtrInscripcionAlumno {
    private final VistaInscripcionAlumno vista;
    private final Alumno modelo;

    public CtrInscripcionAlumno(VistaInscripcionAlumno vista) {
        this.vista = vista;
        this.modelo = new Alumno(); 
        
        vista.btnRegistrar.addActionListener(e -> registrarAlumno());
        vista.btnModificar.addActionListener((e) -> modificarAlumno());
        vista.btnEliminar.addActionListener((e) -> eliminarAlumno());
        cargarTablaAlumnos();
        cargarCarreras();
        vista.tblAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = vista.tblAlumnos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    vista.txtCodigo.setText(vista.tblAlumnos.getValueAt(row, 0).toString());
                    vista.txtNombres.setText(vista.tblAlumnos.getValueAt(row, 1).toString());
                    vista.txtApellidos.setText(vista.tblAlumnos.getValueAt(row, 2).toString());
                    vista.txtCorreo.setText(vista.tblAlumnos.getValueAt(row, 3).toString());
                    vista.txtDireccion.setText(vista.tblAlumnos.getValueAt(row, 4).toString());
                    vista.cmbCarrera.setSelectedItem(vista.tblAlumnos.getValueAt(row, 5).toString());
                }
            }
        });
    }

    private void cargarTablaAlumnos() {
        List<Alumno> listaAlumnos = modelo.obtenerListaAlumnos(); 
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.tblAlumnos.getModel();
        modeloTabla.setRowCount(0);
        for (Alumno alumno : listaAlumnos) {
            modeloTabla.addRow(new Object[]{
                alumno.getCodigo(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getDireccion(),
                alumno.getCorreo(),
                alumno.getCarrera()
            });
        }
    }

    private void cargarCarreras() {
        vista.cmbCarrera.setModel(new DefaultComboBoxModel<>(modelo.obtenerCarreras().toArray(new String[0])));
    }

    private void registrarAlumno() {
        String codigo = vista.txtCodigo.getText();
        String nombres = vista.txtNombres.getText();
        String apellidos = vista.txtApellidos.getText();
        String direccion = vista.txtDireccion.getText();
        String correo = vista.txtCorreo.getText();
        String carrera = (String) vista.cmbCarrera.getSelectedItem();

        if (codigo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || correo.isEmpty() || carrera == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno nuevoAlumno = new Alumno(codigo, nombres, apellidos, direccion, correo, carrera);
        if (modelo.registrarAlumno(nuevoAlumno)) {
            JOptionPane.showMessageDialog(null, "Alumno registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTablaAlumnos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarAlumno() {
        String codigo = vista.txtCodigo.getText(); // Obtener el código del alumno
    String nombres = vista.txtNombres.getText(); // Obtener los demás datos
    String apellidos = vista.txtApellidos.getText();
    String direccion = vista.txtDireccion.getText();
    String correo = vista.txtCorreo.getText();
    String carrera = (String) vista.cmbCarrera.getSelectedItem(); // Obtener la carrera seleccionada
    
    // Validar que todos los campos estén completos
    if (codigo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || correo.isEmpty() || carrera == null) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear el objeto Alumno con los datos proporcionados
    Alumno alumnoModificado = new Alumno(codigo, nombres, apellidos, direccion, correo, carrera);
    
    // Llamar al modelo para modificar los datos del alumno
    if (modelo.modificarAlumno(alumnoModificado)) {
        JOptionPane.showMessageDialog(null, "Alumno modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        cargarTablaAlumnos();  // Recargar la tabla de alumnos
        limpiarCampos();  // Limpiar los campos de la vista
    } else {
        JOptionPane.showMessageDialog(null, "Error al modificar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

   private void eliminarAlumno() {
        String codigo = vista.txtCodigo.getText(); 
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumnoAEliminar = new Alumno(codigo, "", "", "", "", "");

        if (modelo.eliminarAlumno(alumnoAEliminar)) {
            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTablaAlumnos();  // Recargar la tabla de alumnos
            limpiarCampos();  // Limpiar los campos de la vista
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void limpiarCampos() {
        vista.txtCodigo.setText("");
        vista.txtNombres.setText("");
        vista.txtApellidos.setText("");
        vista.txtDireccion.setText("");
        vista.txtCorreo.setText("");
        vista.cmbCarrera.setSelectedIndex(0);
    }
}