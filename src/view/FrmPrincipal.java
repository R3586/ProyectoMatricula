package view;

import controller.CtrMatricula;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.CtrAlumno;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmPrincipal extends JFrame {    
    //DECLARACION DE ICONOS
    ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/icons/buscar.png"));
    ImageIcon iconoadd = new ImageIcon(getClass().getResource("/icons/add.PNG"));
    ImageIcon iconoclean = new ImageIcon(getClass().getResource("/icons/clean.png"));
    ImageIcon iconodelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    ImageIcon iconologout = new ImageIcon(getClass().getResource("/icons/logout.png"));
    ImageIcon iconopencil = new ImageIcon(getClass().getResource("/icons/pencil.png"));
    
    //VARIABLES DE INTERNAL PANELS
    public JInternalFrame inMenu, in1, in2, in3, in4, in5, in6, in7;
    public JPanel paMenu, pa1, pa11, pa2, pa3, pa4, pa5, pa6, pa7;
    
    //VARIABLES PUBLICAS
    Font fuente = new Font("Arial", Font.PLAIN, 24);
    Font titulo = new Font("Arial", Font.BOLD, 24);
    Color fondo = new Color(255, 135, 0);
    Color letra = Color.WHITE;
    public JInternalFrame intPanel;
    public JButton btnVolver;
    
    //VARIABLES DEL CONSTRUCTOR
    public JMenuBar menuBar;
    public JMenu menuGeneral, menuRegistro, menuBusqueda, menuOpciones;
    public JMenuItem miPrincipal, miSalida,
            miMatriculaPostulante, miMatriculaRegular,
            miMatricula, miVoucher, miHistorial,
            miModificar, miImprimir;
    
    public CtrAlumno objctr1;
    public CtrMatricula objctr2;
    
    public FrmPrincipal() {        
        setSize(1250, 800);
        setLocation(340,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        objctr1 = new CtrAlumno(this);
        objctr2 = new CtrMatricula(this);
        
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
        menuRegistro = new JMenu("Matrícula");
            miMatriculaPostulante = new JMenuItem("Postulante");
            miMatriculaPostulante.setIcon(iconoadd);
            miMatriculaPostulante.addActionListener((e) -> {
                RegistroPostulante();
            });
            menuRegistro.add(miMatriculaPostulante);
            
            miMatriculaRegular = new JMenuItem("Regular");
            miMatriculaRegular.setIcon(iconoadd);
            miMatriculaRegular.addActionListener((e) -> {
                MatriculaRegular();
            });            
            menuRegistro.add(miMatriculaRegular);
        menuBusqueda = new JMenu("Revisar");
            miMatricula = new JMenuItem("Revisar Matricula");
            miMatricula.setIcon(iconoLupa);
            miMatricula.addActionListener((e) -> {
                MatriculaRevisar();
            });
            menuBusqueda.add(miMatricula);
            
            miVoucher = new JMenuItem("Revisar Voucher");
            miVoucher.setIcon(iconoLupa);
            miVoucher.addActionListener((e) -> {
                VoucherRevisar();
            });
            menuBusqueda.add(miVoucher);
            
            miHistorial = new JMenuItem("Revisar Historial");
            miHistorial.setIcon(iconoLupa);
            miHistorial.addActionListener((e) -> {
                HistoriaRevisar();
            });
            menuBusqueda.add(miHistorial);      
        menuOpciones = new JMenu("Opciones");
            miModificar = new JMenuItem("Modificar");
            miModificar.setIcon(iconoclean);
            miModificar.addActionListener((e) -> {
                ModificarDatos();
            });
            menuOpciones.add(miModificar);
            
            miImprimir = new JMenuItem("Imprimir");
            miImprimir.setIcon(iconoclean);
            miImprimir.addActionListener((e) -> {
                ImprimirDatos();
            });               
            menuOpciones.add(miImprimir);
            
        menuBar.add(menuGeneral);
        menuBar.add(menuRegistro);
        menuBar.add(menuBusqueda);
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
        
        JLabel lblInsert = new JLabel("Matricular Estudiantes");
        lblInsert.setBounds(530, 30, 300, 25);
        lblInsert.setFont(fuente);
        paMenu.add(lblInsert);

        JLabel lblModif = new JLabel("Modificar Matrículas");
        lblModif.setBounds(530, 200, 300, 25);
        lblModif.setFont(fuente);
        paMenu.add(lblModif);

        JLabel lblSearch = new JLabel("Buscar Matrículas");
        lblSearch.setBounds(530, 370, 300, 25);
        lblSearch.setFont(fuente);
        paMenu.add(lblSearch);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(560, 560, 120, 30);
        btnCerrarSesion.addActionListener((e) -> {
            confirmarSalida();
        });
        paMenu.add(btnCerrarSesion);

        JButton btnModificar = new JButton("Manual");
        btnModificar.setBounds(810, 560, 120, 30);
        paMenu.add(btnModificar);

        JButton btnMatPost = new JButton(" Postulante");
        btnMatPost.setBounds(560, 85, 200, 80);
        btnMatPost.setBackground(fondo);
        btnMatPost.setForeground(letra);
        btnMatPost.setIcon(iconoadd);
        btnMatPost.addActionListener((e) -> {
           RegistroPostulante();
        });
        paMenu.add(btnMatPost);

        JButton btnMatEst = new JButton(" Estudiante Regular");
        btnMatEst.setBounds(780, 85, 200, 80);
        btnMatEst.setBackground(fondo);
        btnMatEst.setForeground(letra);      
        btnMatEst.setIcon(iconoadd);
        btnMatEst.addActionListener((e) -> {
           MatriculaRegular();
        });
        paMenu.add(btnMatEst);
        
        JButton btnRevMat = new JButton(" Matricula");
        btnRevMat.setBounds(560, 260, 200, 80);
        btnRevMat.setBackground(fondo);
        btnRevMat.setForeground(letra);
        btnRevMat.setIcon(iconopencil);
        btnRevMat.addActionListener((e) -> {
           MatriculaRevisar();
        });
        paMenu.add(btnRevMat);

        JButton btnRevVou = new JButton(" Vouchers");
        btnRevVou.setBounds(780, 260, 200, 80);
        btnRevVou.setBackground(fondo);
        btnRevVou.setForeground(letra);      
        btnRevVou.setIcon(iconopencil);
        btnRevVou.addActionListener((e) -> {
            VoucherRevisar();
        });
        paMenu.add(btnRevVou);
        
        JButton btnRevHis = new JButton(" Historial");
        btnRevHis.setBounds(1000, 260, 200, 80);
        btnRevHis.setBackground(fondo);
        btnRevHis.setForeground(letra);      
        btnRevHis.setIcon(iconopencil);
        btnRevHis.addActionListener((e) -> {
            HistoriaRevisar();
        });
        paMenu.add(btnRevHis);
        
        JButton btnModDat = new JButton("Modificar Datos");
        btnModDat.setBounds(560, 435, 200, 80);
        btnModDat.setBackground(fondo);
        btnModDat.setForeground(letra);      
        btnModDat.setIcon(iconoLupa);
        btnModDat.addActionListener((e) -> {
            ModificarDatos();
        });
        paMenu.add(btnModDat);
        
        JButton btnImpDat = new JButton("Imprimir Datos");
        btnImpDat.setBounds(780, 435, 200, 80);
        btnImpDat.setBackground(fondo);
        btnImpDat.setForeground(letra);      
        btnImpDat.setIcon(iconoLupa);
        btnImpDat.addActionListener((e) -> {
            ImprimirDatos();
        });
        paMenu.add(btnImpDat);
        
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
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_local_2.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelImagen.setBounds(0, 0, 500, 800);
        paMenu.add(panelImagen);

        JPanel panelLogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_logo.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelLogo.setBounds(1340, 20, 64, 64);
        paMenu.add(panelLogo);  
        
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
    public JTextField txt1Codigo, txt1Nombres, txt1Apellidos, txt1Direccion, txt1DNI;
    public JComboBox<String> cbo1Carrera;
    public JTable tblAlumnos;
    public DefaultTableModel modelo1Tabla;
    
    public void RegistroPostulante(){           
        cerrarIntPanel();
        in1 = new JInternalFrame();
        in1.setBounds(-5,-30,1260, 860);
        pa1 = new JPanel();
        pa1.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Inscripción de Alumnos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setForeground(fondo);
        lblTitulo.setBounds(450, 25, 400, 30);
        pa1.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa1.add(btnVolver);
        
        JLabel lblbloque1 = new JLabel("  ---- Rellenar Datos ----");
        lblbloque1.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque1.setForeground(fondo);
        lblbloque1.setBounds(80, 80, 400, 30);
        pa1.add(lblbloque1);
        
        JLabel lblbloque2 = new JLabel(" ---- Menú Opciones ----");
        lblbloque2.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque2.setForeground(fondo);
        lblbloque2.setBounds(80, 450, 400, 30);
        pa1.add(lblbloque2);
        
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(80, 125, 100, 30);
        pa1.add(lblCodigo);

        txt1Codigo = new JTextField();
        txt1Codigo.setBounds(175, 125, 175, 30);
        pa1.add(txt1Codigo);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(80, 175, 100, 30);
        pa1.add(lblApellidos);

        txt1Apellidos = new JTextField();
        txt1Apellidos.setBounds(175, 175, 175, 30);
        pa1.add(txt1Apellidos);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(80, 225, 100, 30);
        pa1.add(lblNombres);

        txt1Nombres = new JTextField();
        txt1Nombres.setBounds(175, 225, 175, 30);
        pa1.add(txt1Nombres);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(80, 275, 100, 30);
        pa1.add(lblDireccion);

        txt1Direccion = new JTextField();
        txt1Direccion.setBounds(175, 275, 175, 30);
        pa1.add(txt1Direccion);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(80, 325, 100, 30);
        pa1.add(lblDNI);

        txt1DNI = new JTextField();
        txt1DNI.setBounds(175, 325, 175, 30);
        pa1.add(txt1DNI);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(80, 375, 100, 30);
        pa1.add(lblCarrera);

        cbo1Carrera = new JComboBox<>();
        cbo1Carrera.setBounds(175, 375, 175, 30);
        pa1.add(cbo1Carrera);

        JButton btn1Registrar = new JButton("Registrar");
        btn1Registrar.setBounds(80, 500, 125, 30);
        btn1Registrar.setIcon(iconoadd);
        pa1.add(btn1Registrar);
        
        JButton btn1Modificar = new JButton("Modificar");
        btn1Modificar.setBounds(225, 500, 125, 30);
        btn1Modificar.setIcon(iconopencil);
        pa1.add(btn1Modificar);
        
        JButton btn1Completar = new JButton("Llenar");
        btn1Completar.setBounds(80, 560, 125, 30);
        btn1Completar.setIcon(iconoLupa);
        pa1.add(btn1Completar);
        
        JButton btn1Eliminar = new JButton("Eliminar");
        btn1Eliminar.setBounds(225, 560, 125, 30);
        btn1Eliminar.setIcon(iconodelete);
        pa1.add(btn1Eliminar);
        
        String datos[] = {"Código", "Apellidos", "Nombres", "Carrera", "DNI", "Dirección"};
        modelo1Tabla = new DefaultTableModel(null , datos);
        tblAlumnos = new JTable(modelo1Tabla);
        JScrollPane scrollPane = new JScrollPane(tblAlumnos);
        scrollPane.setBounds(410, 80, 740, 580);
        pa1.add(scrollPane);
        
        tblAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblAlumnos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    txt1Codigo.setText(tblAlumnos.getValueAt(row, 0).toString());
                    txt1Apellidos.setText(tblAlumnos.getValueAt(row, 1).toString());
                    txt1Nombres.setText(tblAlumnos.getValueAt(row, 2).toString());
                    txt1DNI.setText(tblAlumnos.getValueAt(row, 4).toString());
                    txt1Direccion.setText(tblAlumnos.getValueAt(row, 5).toString());
                }
            }
        });      
        
        btnVolver.addActionListener(e ->volver());        
        objctr1.cargarTablaAlumnos();
        objctr1.cargarCarreras();
        btn1Registrar.addActionListener((e) -> objctr1.registrarAlumno());
        btn1Modificar.addActionListener((e) -> objctr1.modificarAlumno());
        btn1Completar.addActionListener((e) -> objctr1.completarAlumno());
        btn1Eliminar.addActionListener((e) -> objctr1.eliminarAlumno());
        
        in1.add(pa1);
        in1.setVisible(true);
        add(in1);
        intPanel = in1;
    }
    
    //---PANEL 2: ---    
    public  JComboBox cbo2Cursos;
    public  JComboBox cbo2Años, cbo2Ciclos, cbo2Seccion;
    public  JTable tabla2Matricula;
    public  DefaultTableModel modelo2Tabla;
    public  JTable tblMatricula;
    public  JTextField txt2CodAlumno, txt2Alumno, txt2Carrera, txt2Escuela;   
    
    public void MatriculaRegular(){          
        cerrarIntPanel();
        in2 = new JInternalFrame();
        in2.setBounds(-5,-30,1260, 860);
        pa2 = new JPanel();
        pa2.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Matrícula de alumnos");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa2.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa2.add(btnVolver);
        
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
                
        btnVolver.addActionListener(e ->volver());        
        objctr2.cargarAños();
        btn2Buscar.addActionListener((e) -> {
            objctr2.buscarAlumno();    
            objctr2.cargarAsignaturas();
        });
        cbo2Años.addActionListener(e -> objctr2.cargarCiclos());
        cbo2Ciclos.addActionListener(e -> objctr2.cargarAsignaturas());
        
        in2.add(pa2);
        in2.setVisible(true);
        add(in2);
        intPanel = in2;
    }
    
   
    
    //---PANEL 3: ---    
    public JLabel lbl3BuscarId, lbl3BuscarNombre;
    public JTextField txt3IdAlumno, txt3NombreAlumno;
    public JButton btn3Buscar, btn3Imprimir, btn3Limpiar;
    public JTable tabla3Matricula;
    public DefaultTableModel modelo3Tabla;
    
    public void MatriculaRevisar(){       
        cerrarIntPanel();
        in3 = new JInternalFrame();
        in3.setBounds(-5,-30,1260, 860);
        pa3 = new JPanel();
        pa3.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Buscar e Imprimir Matrícula");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa3.add(btnVolver);

        lbl3BuscarId = new JLabel("Buscar por ID de Alumno:");
        lbl3BuscarId.setBounds(100, 100, 200, 30);
        pa3.add(lbl3BuscarId);

        txt3IdAlumno = new JTextField();
        txt3IdAlumno.setBounds(300, 100, 200, 30);
        pa3.add(txt3IdAlumno);

        lbl3BuscarNombre = new JLabel("Buscar por Nombre de Alumno:");
        lbl3BuscarNombre.setBounds(100, 150, 220, 30);
        pa3.add(lbl3BuscarNombre);

        txt3NombreAlumno = new JTextField();
        txt3NombreAlumno.setBounds(300, 150, 200, 30);
        pa3.add(txt3NombreAlumno);

        btn3Buscar = new JButton("Buscar");
        btn3Buscar.setBounds(550, 125, 100, 30);
        pa3.add(btn3Buscar);
        
        String[] datos = {"ID Curso", "Nombre Curso", "Ciclo", "Créditos", "Estado"};
        modelo3Tabla = new DefaultTableModel(null,datos);
        tabla3Matricula = new JTable(modelo3Tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla3Matricula);
        scrollTabla.setBounds(100, 250, 1200, 400);
        pa3.add(scrollTabla);

        btn3Imprimir = new JButton("Imprimir");
        btn3Imprimir.setBounds(500, 700, 150, 40);
        pa3.add(btn3Imprimir);

        btn3Limpiar = new JButton("Limpiar");
        btn3Limpiar.setBounds(700, 700, 150, 40);
        pa3.add(btn3Limpiar);
        
        in3.add(pa3);
        in3.setVisible(true);
        add(in3);
        intPanel = in3;
    }
    //---PANEL 4: ---  
    public JLabel lbl4BuscarId, lbl4BuscarCiclo;
    public JTextField txt4IdAlumno, txt4Ciclo;
    public JButton btn4Buscar, btn4Verificar, btn4Limpiar;
    public JTable tabla4Vouchers;
    public DefaultTableModel modelo4Tabla;
    public String[] titu4tabla = {"ID Voucher", "Fecha", "ID Curso", "Nombre Curso", "Monto", "Estado de Verificación"};
    
    public void VoucherRevisar(){       
        cerrarIntPanel();
        in4 = new JInternalFrame();
        in4.setBounds(-5,-30,1260, 860);
        pa4 = new JPanel();
        pa4.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Revisión de Vouchers de Pago");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa3.add(btnVolver);   
        
        lbl4BuscarId = new JLabel("Buscar por ID de Alumno:");
        lbl4BuscarId.setBounds(100, 100, 200, 30);
        pa4.add(lbl4BuscarId);

        txt4IdAlumno = new JTextField();
        txt4IdAlumno.setBounds(300, 100, 200, 30);
        pa4.add(txt4IdAlumno);

        lbl4BuscarCiclo = new JLabel("Buscar por Ciclo Académico:");
        lbl4BuscarCiclo.setBounds(100, 150, 200, 30);
        pa4.add(lbl4BuscarCiclo);

        txt4Ciclo = new JTextField();
        txt4Ciclo.setBounds(300, 150, 200, 30);
        pa4.add(txt4Ciclo);

        btn4Buscar = new JButton("Buscar");
        btn4Buscar.setBounds(550, 125, 100, 30);
        pa4.add(btn4Buscar);
        
        modelo4Tabla = new DefaultTableModel(null,titu4tabla);
        tabla4Vouchers = new JTable(modelo4Tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla4Vouchers);
        scrollTabla.setBounds(100, 250, 1200, 400);
        pa4.add(scrollTabla);

        btn4Verificar = new JButton("Verificar");
        btn4Verificar.setBounds(500, 700, 150, 40);
        pa4.add(btn4Verificar);

        btn4Limpiar = new JButton("Limpiar");
        btn4Limpiar.setBounds(700, 700, 150, 40);
        pa4.add(btn4Limpiar);
        
        
        in4.add(pa4);
        in4.setVisible(true);
        add(in4);
        intPanel = in4;
    }
    //---PANEL 5: ---  
    public JLabel lbl5BuscarId, lbl5BuscarCiclo;
    public JTextField txt5IdAlumno, txt5Ciclo;
    public JButton btn5Buscar, btn5Limpiar;
    public JTable tabla5Historial;
    public DefaultTableModel modelo5Tabla;
    public String[] titu5tabla = {"ID Alumno", "Ciclo", "Curso", "Nota", "Estado"};
    
    public void HistoriaRevisar(){       
        cerrarIntPanel();
        in5 = new JInternalFrame();
        in5.setBounds(-5,-30,1260, 860);
        pa5 = new JPanel();
        pa5.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Búsqueda de Historial Académico");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa3.add(btnVolver);   

        lbl5BuscarId = new JLabel("Buscar por ID de Alumno:");
        lbl5BuscarId.setBounds(100, 100, 200, 30);
        pa5.add(lbl5BuscarId);

        txt5IdAlumno = new JTextField();
        txt5IdAlumno.setBounds(300, 100, 200, 30);
        pa5.add(txt5IdAlumno);

        lbl5BuscarCiclo = new JLabel("Buscar por Ciclo Académico:");
        lbl5BuscarCiclo.setBounds(100, 150, 200, 30);
        pa5.add(lbl5BuscarCiclo);

        txt5Ciclo = new JTextField();
        txt5Ciclo.setBounds(300, 150, 200, 30);
        pa5.add(txt5Ciclo);

        btn5Buscar = new JButton("Buscar");
        btn5Buscar.setBounds(550, 125, 100, 30);
        pa5.add(btn5Buscar);

        modelo5Tabla = new DefaultTableModel(null, titu5tabla);
        tabla5Historial = new JTable(modelo5Tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla5Historial);
        scrollTabla.setBounds(100, 250, 1200, 400);
        pa5.add(scrollTabla);

        btn5Limpiar = new JButton("Limpiar");
        btn5Limpiar.setBounds(500, 700, 150, 40);
        pa5.add(btn5Limpiar);

        
        in5.add(pa5);
        in5.setVisible(true);
        add(in5);
        intPanel = in5;
    }
    //---PANEL 6: ---    
    public JLabel lbl6CodAlumno, lbl6Ciclo, lbl6Año, lbl6Carrera, lbl6Cursos;
    public JTextField txt6CodAlumno, txt6Año, txt6Ciclo;
    public JComboBox cbo6Carrera, cbo6Cursos;
    public JButton btn6Modificar, btn6Limpiar;
    public String[] carreras = {"Ingeniería de Sistemas", "Ingeniería de Transporte", "Ingeniería Industrial", "Ingeniería Agroindustrial"};
            
    public void ModificarDatos(){       
        cerrarIntPanel();
        in6 = new JInternalFrame();
        in6.setBounds(-5,-30,1260, 860);
        pa6 = new JPanel();
        pa6.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Modificar Datos del Alumno");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa3.add(btnVolver);   

        lbl6CodAlumno = new JLabel("Código de Alumno:");
        lbl6CodAlumno.setBounds(100, 100, 200, 30);
        pa6.add(lbl6CodAlumno);

        txt6CodAlumno = new JTextField();
        txt6CodAlumno.setBounds(300, 100, 200, 30);
        pa6.add(txt6CodAlumno);

        lbl6Ciclo = new JLabel("Ciclo Académico:");
        lbl6Ciclo.setBounds(100, 150, 200, 30);
        pa6.add(lbl6Ciclo);

        txt6Ciclo = new JTextField();
        txt6Ciclo.setBounds(300, 150, 200, 30);
        pa6.add(txt6Ciclo);

        lbl6Año = new JLabel("Año:");
        lbl6Año.setBounds(100, 200, 200, 30);
        pa6.add(lbl6Año);

        txt6Año = new JTextField();
        txt6Año.setBounds(300, 200, 200, 30);
        pa6.add(txt6Año);

        lbl6Carrera = new JLabel("Carrera:");
        lbl6Carrera.setBounds(100, 250, 200, 30);
        pa6.add(lbl6Carrera);

        cbo6Carrera = new JComboBox<>(carreras);
        cbo6Carrera.setBounds(300, 250, 200, 30);
        pa6.add(cbo6Carrera);

        lbl6Cursos = new JLabel("Cursos:");
        lbl6Cursos.setBounds(100, 300, 200, 30);
        pa6.add(lbl6Cursos);

        cbo6Cursos = new JComboBox<>();
        cbo6Cursos.setBounds(300, 300, 200, 30);
        pa6.add(cbo6Cursos);

        btn6Modificar = new JButton("Modificar");
        btn6Modificar.setBounds(500, 400, 150, 40);
        pa6.add(btn6Modificar);

        btn6Limpiar = new JButton("Limpiar");
        btn6Limpiar.setBounds(700, 400, 150, 40);
        pa6.add(btn6Limpiar);
        
        in6.add(pa6);
        in6.setVisible(true);
        add(in6);
        intPanel = in6;
    }
    //---PANEL 7: ---    
    public JLabel lbl7CodAlumno, lbl7SelectRegistro;
    public JTextField txt7CodAlumno;
    public JComboBox cbo7TipoRegistro;
    public JButton btn7Imprimir, btn7Limpiar;
    public JTable tabla7Datos;
    public DefaultTableModel modelo7Tabla;
    public String[] titu7tabla = {"Campo", "Valor"};
    public String[] tiposRegistro = {"Vouchers", "Historial Académico", "Matrícula"};
    
    public void ImprimirDatos(){       
        cerrarIntPanel();
        in7 = new JInternalFrame();
        in7.setBounds(-5,-30,1260, 860);
        pa7 = new JPanel();
        pa7.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Imprimir Datos del Alumno");
        lblTitulo.setFont(titulo);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        btnVolver.addActionListener((e) -> {
            volver();
        });
        pa3.add(btnVolver);   

        lbl7CodAlumno = new JLabel("Código de Alumno:");
        lbl7CodAlumno.setBounds(100, 100, 200, 30);
        pa7.add(lbl7CodAlumno);

        txt7CodAlumno = new JTextField();
        txt7CodAlumno.setBounds(300, 100, 200, 30);
        pa7.add(txt7CodAlumno);

        lbl7SelectRegistro = new JLabel("Seleccionar Registro:");
        lbl7SelectRegistro.setBounds(100, 150, 200, 30);
        pa7.add(lbl7SelectRegistro);

        cbo7TipoRegistro = new JComboBox<>(tiposRegistro);
        cbo7TipoRegistro.setBounds(300, 150, 200, 30);
        pa7.add(cbo7TipoRegistro);

        btn7Imprimir = new JButton("Imprimir");
        btn7Imprimir.setBounds(550, 100, 100, 30);
        pa7.add(btn7Imprimir);

        modelo7Tabla = new DefaultTableModel(null, titu7tabla);
        tabla7Datos = new JTable(modelo7Tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla7Datos);
        scrollTabla.setBounds(100, 250, 1200, 400);
        pa7.add(scrollTabla);

        btn7Limpiar = new JButton("Limpiar");
        btn7Limpiar.setBounds(500, 700, 150, 40);
        pa7.add(btn7Limpiar);
        
        
        in7.add(pa7);
        in7.setVisible(true);
        add(in7);
        intPanel = in7;
    }
 
}
    