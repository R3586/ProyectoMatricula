package prueba3;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VistaInscripcionAlumno extends JFrame {
    public JTextField txtCodigo, txtNombres, txtApellidos, txtDireccion, txtCorreo;
    public JComboBox<String> cmbCarrera;
    public JButton btnRegistrar, btnModificar, btnEliminar;
    public JTable tblAlumnos;
    public DefaultTableModel modeloTabla;

    public VistaInscripcionAlumno() {
        setLayout(null);
        setSize(1250, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblTitulo = new JLabel("Inscripción de Alumnos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(30, 20, 400, 30);
        add(lblTitulo);
        
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 70, 100, 30);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(150, 70, 200, 30);
        add(txtCodigo);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(30, 120, 100, 30);
        add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(150, 120, 200, 30);
        add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(30, 170, 100, 30);
        add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 170, 200, 30);
        add(txtApellidos);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(30, 220, 100, 30);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(150, 220, 200, 30);
        add(txtDireccion);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 270, 100, 30);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 270, 200, 30);
        add(txtCorreo);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(30, 320, 100, 30);
        add(lblCarrera);

        cmbCarrera = new JComboBox<>();
        cmbCarrera.setBounds(150, 320, 200, 30);
        add(cmbCarrera);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 370, 150, 40);
        add(btnRegistrar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(150, 420, 150, 40);
        add(btnModificar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(150, 470, 150, 40);
        add(btnEliminar);
        
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombres", "Apellidos", "Correo", "Dirección", "Carrera"}, 0);
        tblAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tblAlumnos);
        scrollPane.setBounds(400, 70, 700, 340);
        add(scrollPane);
        
    }
}