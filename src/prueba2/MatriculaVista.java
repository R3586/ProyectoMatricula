package prueba2;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class MatriculaVista extends JFrame {
    public JComboBox<String> comboAño;
    public JComboBox<String> comboCiclo;
    public JComboBox<String> comboCarrera;
    public JComboBox<String> comboCurso;
    public JComboBox<String> comboSeccion;
    public JTextField txtVoucher;
    public JButton btnInsertarTabla;
    public JButton btnSubirMatricula;
    public JTable tablaMatricula;
    public DefaultTableModel modeloTabla;

    public MatriculaVista() {
        setTitle("Formulario de Matrícula");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con el formulario
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2));
        add(panelFormulario, BorderLayout.NORTH);

        // Componentes del formulario
        panelFormulario.add(new JLabel("Año:"));
        comboAño = new JComboBox<>(new String[]{"2023", "2024"});
        panelFormulario.add(comboAño);

        panelFormulario.add(new JLabel("Ciclo:"));
        comboCiclo = new JComboBox<>(new String[]{"1", "2"});
        panelFormulario.add(comboCiclo);

        panelFormulario.add(new JLabel("Carrera:"));
        comboCarrera = new JComboBox<>();
        panelFormulario.add(comboCarrera);

        panelFormulario.add(new JLabel("Curso:"));
        comboCurso = new JComboBox<>();
        panelFormulario.add(comboCurso);

        panelFormulario.add(new JLabel("Sección:"));
        comboSeccion = new JComboBox<>();
        panelFormulario.add(comboSeccion);

        panelFormulario.add(new JLabel("ID Voucher:"));
        txtVoucher = new JTextField();
        panelFormulario.add(txtVoucher);

        btnInsertarTabla = new JButton("Insertar en Tabla");
        panelFormulario.add(btnInsertarTabla);

        btnSubirMatricula = new JButton("Subir Matrícula");
        panelFormulario.add(btnSubirMatricula);

        // Tabla para visualizar la matrícula
        modeloTabla = new DefaultTableModel(new String[]{"Año", "Ciclo", "Carrera", "Curso", "Sección", "ID Voucher"}, 0);
        tablaMatricula = new JTable(modeloTabla);
        add(new JScrollPane(tablaMatricula), BorderLayout.CENTER);
    }
}
