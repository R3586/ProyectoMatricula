
package test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaCurso extends JFrame {
    public JComboBox<String> comboCursos;
    public JComboBox<String> comboAnios;
    public JComboBox<String> comboCiclos;
    public JComboBox<String> comboAsignaturas;
    public JTable tablaSeleccion;
    public JLabel lblCursos, lblAños, lblCiclos, lblAsignaturas;
    public JScrollPane scrollTabla;
    public JButton btngrabar, btnEliminar;
    public String titutabla[]={"Carrera", "Año", "Ciclo", "Asignatura"};    
    int filaSeleccionada = -1;

    public VistaCurso() {
        setLayout(null);
        setTitle("PRUEBA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(400,200);

        btngrabar = new JButton("Añadir");
        btngrabar.setBounds(30,400,80,25);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(150,400,80,25);   
        lblCursos = new JLabel("Carrera:");
        lblCursos.setBounds(30, 20, 80, 25);
        lblAños = new JLabel("Año:");
        lblAños.setBounds(30, 60, 80, 25);
        lblCiclos = new JLabel("Ciclo:");
        lblCiclos.setBounds(30, 100, 80, 25);
        lblAsignaturas = new JLabel("Asignaturas:");
        lblAsignaturas.setBounds(30, 140, 80, 25);
        comboCursos = new JComboBox<>();
        comboCursos.setBounds(120, 20, 200, 25);
        comboAnios = new JComboBox<>();
        comboAnios.setBounds(120, 60, 200, 25);
        comboCiclos = new JComboBox<>();
        comboCiclos.setBounds(120, 100, 200, 25);
        comboAsignaturas = new JComboBox<>();
        comboAsignaturas.setBounds(120, 140, 400, 25);
        tablaSeleccion = new JTable(new DefaultTableModel(null,titutabla));
        scrollTabla = new JScrollPane(tablaSeleccion);     
        scrollTabla.setBounds(30, 180, 600, 200);
        
        tablaSeleccion.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaSeleccion.getColumnModel().getColumn(1).setPreferredWidth(50);  
        tablaSeleccion.getColumnModel().getColumn(2).setPreferredWidth(50); 
        tablaSeleccion.getColumnModel().getColumn(3).setPreferredWidth(400);

        add(lblCursos); add(comboCursos); add(btngrabar); add(btnEliminar);
        add(lblAños); add(comboAnios);
        add(lblCiclos); add(comboCiclos);
        add(lblAsignaturas); add(comboAsignaturas);
        add(scrollTabla);      
    }

    public void setCursos(List<String> cursos) {
        cargarComboBox(comboCursos, cursos); 
    }
    public void setAnios(List<String> anios) { 
        cargarComboBox(comboAnios, anios); 
    }
    public void setCiclos(List<String> ciclos) {
        cargarComboBox(comboCiclos, ciclos); 
    }
    public void setAsignaturas(List<String> asignaturas) {
        cargarComboBox(comboAsignaturas, asignaturas); 
    }

    public void cargarComboBox(JComboBox<String> comboBox, List<String> items) {
        comboBox.removeAllItems();
        for (String item : items) {
            comboBox.addItem(item);
        }
    }
    
    public void setFilaSeleccionada(int fila) {
        this.filaSeleccionada = fila;
    }

    public int getFilaSeleccionada() {
        return this.filaSeleccionada;
    }
 
    public void agregarCursoATabla(String curso, String año, String ciclo, String asignatura) {
        DefaultTableModel model = (DefaultTableModel) tablaSeleccion.getModel();
        model.addRow(new Object[]{curso, año, ciclo, asignatura});
    }

}
