package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.CtrPrincipal_std;
import controller.CtrMatricula_std;
import utilily.Recursos;

public class FrmPrincipal_std extends JFrame {     
    public JLabel lblBienvenida;
    public JInternalFrame intPanel;    
    Recursos r;
    CtrPrincipal_std ctrmain;
    CtrMatricula_std objctr1;
    
    public FrmPrincipal_std() {        
        setSize(1250, 800);
        setLocation(340,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        r = new Recursos();
        ctrmain = new CtrPrincipal_std(this);
        objctr1 = new CtrMatricula_std(this);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menuGeneral = new JMenu("Principal");
            JMenuItem miPrincipal = new JMenuItem("Menu Principal");
            miPrincipal.setIcon(r.iconopencil);
            menuGeneral.add(miPrincipal);
            JMenuItem miAjustes = new JMenuItem("Ajustes");
            miAjustes.setIcon(r.iconoclean);
            menuGeneral.add(miAjustes);                 
            JMenuItem miSalida = new JMenuItem("Cerrar Sesión");
            miSalida.setIcon(r.iconologout);
            menuGeneral.add(miSalida);        
        menuBar.add(menuGeneral);
        JMenu menuOpciones = new JMenu("Opciones");
            JMenuItem miMatricula = new JMenuItem("Matricula");
            miMatricula.setIcon(r.iconoadd);
            menuOpciones.add(miMatricula);            
            JMenuItem miHistorial = new JMenuItem("Historial");
            miHistorial.setIcon(r.iconoadd);
            menuOpciones.add(miHistorial);
        menuBar.add(menuOpciones);
                
        miPrincipal.addActionListener(e -> MenuPrincipal());        
        miAjustes.addActionListener(e -> Ajustes());
        miSalida.addActionListener(e -> ctrmain.confirmarSalida());
        miMatricula.addActionListener(e -> Matricula_std());
        miHistorial.addActionListener(e -> Historial_std());            
    }        
        
    //------CODIGO DE INTERNAL PANELS-------------    
    public void MenuPrincipal(){          
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame inMenu = new JInternalFrame();
        inMenu.setBounds(-5,-30,1260, 860);
        JPanel paMenu = new JPanel();
        paMenu.setLayout(null);
        paMenu.setBackground(Color.WHITE);
        
        lblBienvenida = new JLabel();
        lblBienvenida.setBounds(480,120,350,25);
        lblBienvenida.setFont(r.arialLB);
        paMenu.add(lblBienvenida);
        
        ctrmain.bienvenida();
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(560, 580, 120, 30);
        paMenu.add(btnCerrarSesion);

        JButton btnModificar = new JButton("Manual");
        btnModificar.setBounds(560, 500, 120, 30);
        paMenu.add(btnModificar);

        JButton btnMatricula = new JButton(" Matrícula");
        btnMatricula.setBounds(520, 220, 200, 80);
        r.btnstyle(btnMatricula);
        btnMatricula.setIcon(r.iconopencil);
        paMenu.add(btnMatricula);

        JButton btnHistorial = new JButton(" Rendimiento");
        btnHistorial.setBounds(520, 360, 200, 80);
        r.btnstyle(btnHistorial);
        btnHistorial.setIcon(r.iconoLupa);
        paMenu.add(btnHistorial);
                       
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 255, 255, 200));
        panelLogin.setBounds(420, 80, 400, 560);
        paMenu.add(panelLogin);
        
        JPanel panelBarra = r.panelBarra;
        panelBarra.setBounds(0, 790, 1440, 50);
        paMenu.add(panelBarra);
        
        JPanel unfv_local3 = r.unfv_local3;
        unfv_local3.setBounds(0, 0, 1260, 860);
        paMenu.add(unfv_local3);                
        
        JButton btn = new JButton();       
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        paMenu.add(btn);  

        
        btnCerrarSesion.addActionListener(e -> ctrmain.confirmarSalida());        
        btnMatricula.addActionListener(e -> Matricula_std());
        btnHistorial.addActionListener(e -> Historial_std());
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
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in1 = new JInternalFrame();
        in1.setBounds(-5,-30,1260, 860);
        JPanel pa1 = new JPanel();
        pa1.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Matrícula de alumnos");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa1.add(lblTitulo);
        
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
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa1.add(btnVolver);
        
        objctr1.buscarAlumno();
        objctr1.cargarAños();
        
        in1.add(pa1);
        in1.setVisible(true);
        add(in1);
        intPanel = in1;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
    public void Ajustes(){
        
    }
    //---PANEL 2: ---    
    
    public void Historial_std(){  
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in2 = new JInternalFrame();
        in2.setBounds(-5,-30,1260, 860);
        JPanel pa2 = new JPanel();
        pa2.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Matrícula de alumnos");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa2.add(lblTitulo);
        
        JButton btn2Buscar = new JButton("Cargar Datos de Alumno");
        btn2Buscar.setBounds(420, 70, 200, 30);
        pa2.add(btn2Buscar);

        JLabel lbl2CodAlumno = new JLabel("Código Alumno");
        lbl2CodAlumno.setBounds(80,70,100,30);
        pa2.add(lbl2CodAlumno);     
        
        txt2CodAlumno = new JTextField();
        txt2CodAlumno.setBounds(200, 70, 200, 30);
        pa2.add(txt2CodAlumno);
        
        JLabel lbl2Alumno = new JLabel("Nombre Alumno");
        lbl2Alumno.setBounds(80,120,100,30);
        pa2.add(lbl2Alumno);
        
        txt2Alumno = new JTextField();
        txt2Alumno.setBounds(200,120,200,30);
        txt2Alumno.setBackground(null);
        txt2Alumno.setBorder(null);
        pa2.add(txt2Alumno);
                
        JLabel lbl2Carrera = new JLabel("Carrera");
        lbl2Carrera.setBounds(420, 120, 100, 30);
        pa2.add(lbl2Carrera);

        txt2Carrera = new JTextField();
        txt2Carrera.setBounds(500, 120, 200, 30);
        txt2Carrera.setBackground(null);
        txt2Carrera.setBorder(null);
        pa2.add(txt2Carrera);
        
        JLabel lbl2Escuela = new JLabel("Escuela");
        lbl2Escuela.setBounds(720, 120, 100, 30);
        pa2.add(lbl2Escuela);

        txt2Escuela = new JTextField();
        txt2Escuela.setBounds(800, 120, 300, 30);
        txt2Escuela.setBackground(null);
        txt2Escuela.setBorder(null);
        pa2.add(txt2Escuela);

        JLabel lbl2Año = new JLabel("Año de Estudio");
        lbl2Año.setBounds(80, 220, 120, 30);
        pa2.add(lbl2Año);

        cbo2Años = new JComboBox<>();
        cbo2Años.setBounds(200, 220, 50, 30);
        pa2.add(cbo2Años);

        JLabel lbl2Ciclos = new JLabel("Ciclo");
        lbl2Ciclos.setBounds(320, 220, 100, 30);
        pa2.add(lbl2Ciclos);

        cbo2Ciclos = new JComboBox<>();
        cbo2Ciclos.setBounds(400, 220, 50, 30);
        pa2.add(cbo2Ciclos);
        
        JLabel lbl2Seccion = new JLabel("Sección");
        lbl2Seccion.setBounds(500, 220, 100, 30);
        pa2.add(lbl2Seccion);

        cbo2Seccion = new JComboBox<>();
        cbo2Seccion.setBounds(600, 220, 50, 30);
        pa2.add(cbo2Seccion);

        JLabel lbl2Cursos = new JLabel("Asignatura");
        lbl2Cursos.setBounds(80, 270, 100, 30);
        pa2.add(lbl2Cursos);

        cbo2Cursos = new JComboBox<>();
        cbo2Cursos.setBounds(200, 270, 450, 30);
        pa2.add(cbo2Cursos);       
                
        JLabel lbl2IdVoucher = new JLabel("ID Voucher");
        lbl2IdVoucher.setBounds(740, 220, 100, 30);
        pa2.add(lbl2IdVoucher);
        
        JTextField txt2IdVoucher = new JTextField();
        txt2IdVoucher.setBounds(820, 220, 100, 30);
        pa2.add(txt2IdVoucher);
        
        JLabel lbl2MontoVoucher = new JLabel("Monto S/.");
        lbl2MontoVoucher.setBounds(960, 220, 70, 30);
        pa2.add(lbl2MontoVoucher);
        
        JTextField txt2MontoVoucher = new JTextField();
        txt2MontoVoucher.setBounds(1040, 220, 100, 30);
        pa2.add(txt2MontoVoucher);

        JButton btn2AñadirCurso = new JButton("Añadir");
        btn2AñadirCurso.setBounds(700, 270, 100, 30);
        pa2.add(btn2AñadirCurso);        
        
        JButton btn2EliminarCurso = new JButton("Eliminar Curso");
        btn2EliminarCurso.setBounds(840, 270, 150, 30);
        btn2EliminarCurso.setEnabled(true);
        pa2.add(btn2EliminarCurso);            

        JButton btn2GuardarMatricula = new JButton("Guardar Matrícula");
        btn2GuardarMatricula.setBounds(500, 590, 200, 30);
        btn2GuardarMatricula.setEnabled(false);
        pa2.add(btn2GuardarMatricula);      
        
        JButton btn2Nuevo = new JButton("Nuevo");
        btn2Nuevo.setBounds(350, 590, 100, 30);
        pa2.add(btn2Nuevo);
        
        String datos[]={"Carrera","Año","Ciclo","Asignatura"};
        modelo2Tabla = new DefaultTableModel(null,datos);
        tblMatricula = new JTable(modelo2Tabla);
        JScrollPane scrollPane = new JScrollPane(tblMatricula);
        scrollPane.setBounds(80, 320, 1060, 250);
        pa2.add(scrollPane);
                  
        objctr1.cargarAños();
        btn2Buscar.addActionListener((e) -> {
            objctr1.buscarAlumno();    
            objctr1.cargarAsignaturas();
        });
        cbo2Años.addActionListener(e -> objctr1.cargarCiclos());
        cbo2Ciclos.addActionListener(e -> objctr1.cargarAsignaturas());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa2.add(btnVolver);
      
        in2.add(pa2);
        in2.setVisible(true);
        add(in2);
        intPanel = in2;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
}
    