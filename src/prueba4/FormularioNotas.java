/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba4;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioNotas extends JFrame {

    // Declaración de componentes
    private JTextField txtCodigoAlumno, txtEP, txtPP, txtEF, txtSUS;
    private JButton btnBuscarAlumno, btnFiltrar, btnActualizarNotas, btnVolver;
    private JComboBox<String> cboAnio, cboCiclo, cboCarrera;
    private JTable tablaNotas;
    private DefaultTableModel modeloTabla;

    public FormularioNotas() {
        setTitle("Gestión de Notas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: Buscar alumno
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.add(new JLabel("Código Alumno:"));
        txtCodigoAlumno = new JTextField(10);
        btnBuscarAlumno = new JButton("Buscar Alumno");
        panelSuperior.add(txtCodigoAlumno);
        panelSuperior.add(btnBuscarAlumno);

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new FlowLayout());
        panelFiltros.add(new JLabel("Año:"));
        cboAnio = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        panelFiltros.add(cboAnio);
        panelFiltros.add(new JLabel("Ciclo:"));
        cboCiclo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        panelFiltros.add(cboCiclo);
        panelFiltros.add(new JLabel("Carrera:"));
        cboCarrera = new JComboBox<>(new String[]{"Ingeniería de Sistemas", "Ingeniería Industrial", "Ingeniería Agroindustrial", "Ingeniería de Transporte"});
        panelFiltros.add(cboCarrera);
        btnFiltrar = new JButton("Filtrar");
        panelFiltros.add(btnFiltrar);

        // Panel central: Tabla de notas
        modeloTabla = new DefaultTableModel(new String[]{"Curso", "EP", "PP", "EF", "SUS", "Promedio"}, 0);
        tablaNotas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaNotas);

        // Panel inferior: Edición de notas
        JPanel panelEdicion = new JPanel(new FlowLayout());
        panelEdicion.add(new JLabel("EP:"));
        txtEP = new JTextField(5);
        panelEdicion.add(txtEP);
        panelEdicion.add(new JLabel("PP:"));
        txtPP = new JTextField(5);
        panelEdicion.add(txtPP);
        panelEdicion.add(new JLabel("EF:"));
        txtEF = new JTextField(5);
        panelEdicion.add(txtEF);
        panelEdicion.add(new JLabel("SUS:"));
        txtSUS = new JTextField(5);
        panelEdicion.add(txtSUS);

        btnActualizarNotas = new JButton("Actualizar Notas");
        panelEdicion.add(btnActualizarNotas);

        // Botón Volver
        btnVolver = new JButton("Volver");
        JPanel panelVolver = new JPanel(new FlowLayout());
        panelVolver.add(btnVolver);

        // Agregar paneles al formulario
        add(panelSuperior, BorderLayout.NORTH);
        add(panelFiltros, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);
        add(panelEdicion, BorderLayout.WEST);
        add(panelVolver, BorderLayout.SOUTH);

        // Hacer visible el formulario
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormularioNotas::new);
    }
}
