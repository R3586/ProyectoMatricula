package prueba2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatriculaControlador {
    private MatriculaModelo modelo;
    private MatriculaVista vista;

    public MatriculaControlador(MatriculaModelo modelo, MatriculaVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Cargar carreras en el ComboBox
        for (String carrera : modelo.obtenerCarreras()) {
            vista.comboCarrera.addItem(carrera);
        }

        // Listeners
        vista.comboCarrera.addActionListener(e -> actualizarCursos());
        vista.comboCurso.addActionListener(e -> actualizarSecciones());
        vista.btnInsertarTabla.addActionListener(e -> insertarEnTabla());
        vista.btnSubirMatricula.addActionListener(e -> subirMatricula());
    }

    private void actualizarCursos() {
        String carreraSeleccionada = (String) vista.comboCarrera.getSelectedItem();
        vista.comboCurso.removeAllItems();
        for (String curso : modelo.obtenerCursos(carreraSeleccionada)) {
            vista.comboCurso.addItem(curso);
        }
    }

    private void actualizarSecciones() {
        String cursoSeleccionado = (String) vista.comboCurso.getSelectedItem();
        vista.comboSeccion.removeAllItems();

        // Consultar las secciones disponibles (A, B, C) y los cupos
        for (String seccion : new String[]{"A", "B", "C"}) {
            int cuposRestantes = modelo.obtenerCuposRestantes(cursoSeleccionado, seccion);
            if (cuposRestantes > 0) {
                vista.comboSeccion.addItem(seccion + " (Cupos: " + cuposRestantes + ")");
            }
        }

        if (vista.comboSeccion.getItemCount() == 0) {
            JOptionPane.showMessageDialog(vista, "No hay cupos disponibles en ninguna sección para este curso.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void insertarEnTabla() {
        // Validar ID Voucher
        String idVoucher = vista.txtVoucher.getText().trim();
        if (idVoucher.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese el ID del voucher.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que se haya seleccionado una sección
        if (vista.comboSeccion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una sección con cupos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener datos del formulario
        String año = (String) vista.comboAño.getSelectedItem();
        String ciclo = (String) vista.comboCiclo.getSelectedItem();
        String carrera = (String) vista.comboCarrera.getSelectedItem();
        String curso = (String) vista.comboCurso.getSelectedItem();
        String seccion = ((String) vista.comboSeccion.getSelectedItem()).split(" ")[0]; // Solo obtener la letra de la sección

        // Insertar datos en la tabla
        vista.modeloTabla.addRow(new Object[]{año, ciclo, carrera, curso, seccion, idVoucher});
        JOptionPane.showMessageDialog(vista, "Matrícula añadida a la tabla.");
    }

    private void subirMatricula() {
        // Subir los datos de la tabla a la base de datos
        int filas = vista.modeloTabla.getRowCount();

        if (filas == 0) {
            JOptionPane.showMessageDialog(vista, "No hay datos en la tabla para subir.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = true;
        for (int i = 0; i < filas; i++) {
            try {
                String seccion = (String) vista.modeloTabla.getValueAt(i, 4);
                String idVoucher = (String) vista.modeloTabla.getValueAt(i, 5);

                // Validar cupos y realizar matrícula
                if (!modelo.validarCupos(seccion)) {
                    throw new Exception("No hay cupos disponibles en la sección " + seccion);
                }
                modelo.agregarMatricula(1, seccion); // Ejemplo: id_alumno = 1
            } catch (Exception e) {
                exito = false;
                JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (exito) {
            JOptionPane.showMessageDialog(vista, "Todas las matrículas se han subido correctamente.");
            vista.modeloTabla.setRowCount(0); // Limpiar la tabla
        }
    }
}   