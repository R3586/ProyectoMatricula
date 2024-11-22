package view;

import controller.CtrLogin;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ModelLogin;

public class FrmPrincipal_std extends JFrame {    
    //DECLARACION DE ICONOS
    ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/icons/buscar.png"));
    ImageIcon iconoadd = new ImageIcon(getClass().getResource("/icons/add.PNG"));
    ImageIcon iconoclean = new ImageIcon(getClass().getResource("/icons/clean.png"));
    ImageIcon iconodelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    ImageIcon iconologout = new ImageIcon(getClass().getResource("/icons/logout.png"));
    ImageIcon iconopencil = new ImageIcon(getClass().getResource("/icons/pencil.png"));
    
    //VARIABLES DE INTERNAL PANELS
    public JInternalFrame inMenu, in1, in2;
    public JPanel paMenu, pa1, pa2;
    
    //VARIABLES PUBLICAS
    Font fuente = new Font("Arial", Font.PLAIN, 24);
    Font titulo = new Font("Arial", Font.BOLD, 24);
    Color fondo = new Color(255, 135, 0);
    Color letra = Color.WHITE;
    public JInternalFrame intPanel;
    public JButton btnVolver;
    
    //VARIABLES DEL CONSTRUCTOR
    public JMenuBar menuBar;
    public JMenu menuGeneral, menuOpciones;
    public JMenuItem miPrincipal, miSalida, miMatricula, miHistorial;
    
    public FrmPrincipal_std() {        
        setSize(1250, 800);
        setLocation(340,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        menuGeneral = new JMenu("Principal");
            miPrincipal = new JMenuItem("Menu Principal");
            miPrincipal.setIcon(iconopencil);
            miPrincipal.addActionListener((e) -> {
                MenuPrincipal();
            });
            menuGeneral.add(miPrincipal);
            
            miSalida = new JMenuItem("Cerrar Sesión");
            miSalida.setIcon(iconologout);
            miSalida.addActionListener((e) -> {
                confirmarSalida();
            });
            menuGeneral.add(miSalida);        
        menuOpciones = new JMenu("Opciones");
            miMatricula = new JMenuItem("Matricula");
            miMatricula.setIcon(iconoadd);
            miMatricula.addActionListener((e) -> {
                Matricula_std();
            });
            menuOpciones.add(miMatricula);
            
            miHistorial = new JMenuItem("Historial");
            miHistorial.setIcon(iconoadd);
            miHistorial.addActionListener((e) -> {
                Historial_std();
            });            
            menuOpciones.add(miHistorial);
        menuBar.add(menuGeneral);
        menuBar.add(menuOpciones);
        
        MenuPrincipal();
    }        

    public void volver(){          
        intPanel.dispose();
        intPanel = null;
        MenuPrincipal();
    }
    
    public void cerrarIntPanel(){
        if(intPanel != null){
            intPanel.dispose();
            intPanel = null;
        }
    }    
    
    public void confirmarSalida(){
        int salida = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(salida == JOptionPane.YES_OPTION){
            this.dispose();
            FrmLogin vista = new FrmLogin();
            vista.setVisible(true);
        }
    }
        
    //------CODIGO DE INTERNAL PANELS-------------    
    private void MenuPrincipal(){          
        cerrarIntPanel();
        inMenu = new JInternalFrame();
        inMenu.setBounds(-5,-30,1260, 860);
        paMenu = new JPanel();
        paMenu.setLayout(null);
        paMenu.setBackground(Color.WHITE);
        
        JLabel lblInsert = new JLabel("¡Bienvenid@ estudiante!");
        lblInsert.setBounds(480, 120, 300, 25);
        lblInsert.setFont(titulo);
        paMenu.add(lblInsert);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(560, 580, 120, 30);
        btnCerrarSesion.addActionListener((e) -> {
            confirmarSalida();
        });
        paMenu.add(btnCerrarSesion);

        JButton btnModificar = new JButton("Manual");
        btnModificar.setBounds(560, 500, 120, 30);
        paMenu.add(btnModificar);

        JButton btnMatricula = new JButton(" Matrícula");
        btnMatricula.setBounds(520, 220, 200, 80);
        btnMatricula.setBackground(fondo);
        btnMatricula.setForeground(letra);
        btnMatricula.setIcon(iconopencil);
        btnMatricula.addActionListener((e) -> {
           Matricula_std();
        });
        paMenu.add(btnMatricula);

        JButton btnHistorial = new JButton(" Historial Académico");
        btnHistorial.setBounds(520, 360, 200, 80);
        btnHistorial.setBackground(fondo);
        btnHistorial.setForeground(letra);      
        btnHistorial.setIcon(iconoLupa);
        btnHistorial.addActionListener((e) -> {
           Historial_std();
        });
        paMenu.add(btnHistorial);
               
        
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 255, 255, 200));
        panelLogin.setBounds(420, 80, 400, 560);
        paMenu.add(panelLogin);
        
        JPanel panelBarra = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/barra_naranja.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelBarra.setBounds(0, 790, 1440, 50);
        paMenu.add(panelBarra);
        
        JPanel panelImagen = new JPanel() {
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_local_3.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);                
            }
        };
        panelImagen.setBounds(0, 0, 1260, 860);
        paMenu.add(panelImagen);
                
        
        JButton btn = new JButton();       
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        paMenu.add(btn);  

        inMenu.add(paMenu);
        inMenu.setVisible(true);
        add(inMenu);        
        intPanel = inMenu;
    } 

    //---PANEL 1: ---       
    public  JComboBox cbo2Cursos;
    public  JComboBox cbo2Años, cbo2Ciclos, cbo2Seccion;
    public  JTable tabla2Matricula;
    public  DefaultTableModel modelo2Tabla;
    public  JTable tblMatricula;
    public  JTextField txt2CodAlumno, txt2Alumno, txt2Carrera, txt2Escuela;   
    
    public void  Matricula_std(){           
        cerrarIntPanel();
        in1 = new JInternalFrame();
        in1.setBounds(-5,-30,1260, 860);
        pa1 = new JPanel();
        pa1.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Matrícula de alumnos");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa1.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa1.add(btnVolver);
        
        JButton btn2Buscar = new JButton("Buscar Alumno");
        btn2Buscar.setBounds(420, 70, 150, 30);
        pa1.add(btn2Buscar);

        JLabel lbl2CodAlumno = new JLabel("Código Alumno");
        lbl2CodAlumno.setBounds(80,70,100,30);
        pa1.add(lbl2CodAlumno);     
        
        txt2CodAlumno = new JTextField();
        txt2CodAlumno.setBounds(200, 70, 200, 30);
        pa1.add(txt2CodAlumno);
        
        JLabel lbl2Alumno = new JLabel("Nombre Alumno");
        lbl2Alumno.setBounds(80,120,100,30);
        pa1.add(lbl2Alumno);
        
        txt2Alumno = new JTextField();
        txt2Alumno.setBounds(200,120,200,30);
        txt2Alumno.setBackground(null);
        txt2Alumno.setBorder(null);
        pa1.add(txt2Alumno);
                
        JLabel lbl2Carrera = new JLabel("Carrera");
        lbl2Carrera.setBounds(420, 120, 100, 30);
        pa1.add(lbl2Carrera);

        txt2Carrera = new JTextField();
        txt2Carrera.setBounds(500, 120, 200, 30);
        txt2Carrera.setBackground(null);
        txt2Carrera.setBorder(null);
        pa1.add(txt2Carrera);
        
        JLabel lbl2Escuela = new JLabel("Escuela");
        lbl2Escuela.setBounds(720, 120, 100, 30);
        pa1.add(lbl2Escuela);

        txt2Escuela = new JTextField();
        txt2Escuela.setBounds(800, 120, 300, 30);
        txt2Escuela.setBackground(null);
        txt2Escuela.setBorder(null);
        pa1.add(txt2Escuela);

        JLabel lbl2Año = new JLabel("Año de Estudio");
        lbl2Año.setBounds(80, 220, 120, 30);
        pa1.add(lbl2Año);

        cbo2Años = new JComboBox<>();
        cbo2Años.setBounds(200, 220, 50, 30);
        pa1.add(cbo2Años);

        JLabel lbl2Ciclos = new JLabel("Ciclo");
        lbl2Ciclos.setBounds(320, 220, 100, 30);
        pa1.add(lbl2Ciclos);

        cbo2Ciclos = new JComboBox<>();
        cbo2Ciclos.setBounds(400, 220, 50, 30);
        pa1.add(cbo2Ciclos);
        
        JLabel lbl2Seccion = new JLabel("Sección");
        lbl2Seccion.setBounds(500, 220, 100, 30);
        pa1.add(lbl2Seccion);

        cbo2Seccion = new JComboBox<>();
        cbo2Seccion.setBounds(600, 220, 50, 30);
        pa1.add(cbo2Seccion);

        JLabel lbl2Cursos = new JLabel("Asignatura");
        lbl2Cursos.setBounds(80, 270, 100, 30);
        pa1.add(lbl2Cursos);

        cbo2Cursos = new JComboBox<>();
        cbo2Cursos.setBounds(200, 270, 450, 30);
        pa1.add(cbo2Cursos);       
                
        JLabel lbl2IdVoucher = new JLabel("ID Voucher");
        lbl2IdVoucher.setBounds(740, 220, 100, 30);
        pa1.add(lbl2IdVoucher);
        
        JTextField txt2IdVoucher = new JTextField();
        txt2IdVoucher.setBounds(820, 220, 100, 30);
        pa1.add(txt2IdVoucher);
        
        JLabel lbl2MontoVoucher = new JLabel("Monto S/.");
        lbl2MontoVoucher.setBounds(960, 220, 70, 30);
        pa1.add(lbl2MontoVoucher);
        
        JTextField txt2MontoVoucher = new JTextField();
        txt2MontoVoucher.setBounds(1040, 220, 100, 30);
        pa1.add(txt2MontoVoucher);

        JButton btn2AñadirCurso = new JButton("Añadir");
        btn2AñadirCurso.setBounds(700, 270, 100, 30);
        pa1.add(btn2AñadirCurso);        
        
        JButton btn2EliminarCurso = new JButton("Eliminar Curso");
        btn2EliminarCurso.setBounds(840, 270, 150, 30);
        btn2EliminarCurso.setEnabled(true);
        pa1.add(btn2EliminarCurso);            

        JButton btn2GuardarMatricula = new JButton("Guardar Matrícula");
        btn2GuardarMatricula.setBounds(500, 590, 200, 30);
        btn2GuardarMatricula.setEnabled(false);
        pa1.add(btn2GuardarMatricula);      
        
        JButton btn2Nuevo = new JButton("Nuevo");
        btn2Nuevo.setBounds(350, 590, 100, 30);
        pa1.add(btn2Nuevo);
        
        String datos[]={"Carrera","Año","Ciclo","Asignatura"};
        modelo2Tabla = new DefaultTableModel(null,datos);
        tblMatricula = new JTable(modelo2Tabla);
        JScrollPane scrollPane = new JScrollPane(tblMatricula);
        scrollPane.setBounds(80, 320, 1060, 250);
        pa1.add(scrollPane);
        
        btn2AñadirCurso.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "En proceso");
        });
        
        in1.add(pa1);
        in1.setVisible(true);
        add(in1);
        intPanel = in1;
    }
    
    //---PANEL 2: ---    
    
    public void Historial_std(){          
        cerrarIntPanel();
        in2 = new JInternalFrame();
        in2.setBounds(-5,-30,1260, 860);
        pa2 = new JPanel();
        pa2.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Historial Académico");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa2.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa2.add(btnVolver);
        
        
        in2.add(pa2);
        in2.setVisible(true);
        add(in2);
        intPanel = in2;
    }
}
    