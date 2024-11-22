package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorCurso {
    private ModeloCurso modelo;
    private VistaCurso vista;

    public ControladorCurso(ModeloCurso modelo, VistaCurso vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        cargarCursosPrincipales();

        vista.comboCursos.addActionListener(e -> cargarAnios());
        vista.comboAnios.addActionListener(e -> cargarCiclos());
        vista.comboCiclos.addActionListener(e -> cargarAsignaturas());
        
        vista.btngrabar.addActionListener((e) -> { agregarCursoATabla();});
        
        vista.tablaSeleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int selectedRow = vista.tablaSeleccion.getSelectedRow();
                    if (selectedRow != -1) {
                        vista.setFilaSeleccionada(selectedRow);
                    }
                }
        });
        vista.btnEliminar.addActionListener((e) -> eliminarFilaSeleccionada());
        //vista.comboAsignaturas.addActionListener(e -> agregarCursoATabla());
        
    }

    private void cargarCursosPrincipales() {
        List<String> cursos = modelo.obtenerCursosPrincipales();
        vista.setCursos(cursos);
    }

    private void cargarAnios() {
        String cursoSeleccionado = (String) vista.comboCursos.getSelectedItem();
        if (cursoSeleccionado != null) {
            List<String> anios = modelo.obtenerAniosPorCurso(cursoSeleccionado);
            vista.setAnios(anios);
        }
    }

    private void cargarCiclos() {
        String curso = (String) vista.comboCursos.getSelectedItem();
        String año = (String) vista.comboAnios.getSelectedItem();
        if (curso != null && año != null) {
            List<String> ciclos = modelo.obtenerCiclosPorAnio(curso, año);
            vista.setCiclos(ciclos);
        }
    }

    private void cargarAsignaturas() {
        String curso = (String) vista.comboCursos.getSelectedItem();
        String año = (String) vista.comboAnios.getSelectedItem();
        String ciclo = (String) vista.comboCiclos.getSelectedItem();
        if (curso != null && año != null && ciclo != null) {
            List<String> asignaturas = modelo.obtenerCursosPorCiclo(curso, año, ciclo);
            vista.setAsignaturas(asignaturas);
        }
    }

    private void agregarCursoATabla() {
        String curso = (String) vista.comboCursos.getSelectedItem();
        String año = (String) vista.comboAnios.getSelectedItem();
        String ciclo = (String) vista.comboCiclos.getSelectedItem();
        String asignatura = (String) vista.comboAsignaturas.getSelectedItem();
        
        if (curso != null && año != null && ciclo != null && asignatura != null) {
            vista.agregarCursoATabla(curso, año, ciclo, asignatura);
        }
    }
       private void eliminarFilaSeleccionada() {
        int fila = vista.getFilaSeleccionada();
        if (fila != -1) {
            DefaultTableModel modelo = (DefaultTableModel) vista.tablaSeleccion.getModel();
            modelo.removeRow(fila);
            vista.setFilaSeleccionada(-1);
        }
    }
    public static void main(String[] args) {
        ModeloCurso modelo = new ModeloCurso();
        VistaCurso vista = new VistaCurso();
        new ControladorCurso(modelo, vista);
        
        vista.setVisible(true);
    }
}
