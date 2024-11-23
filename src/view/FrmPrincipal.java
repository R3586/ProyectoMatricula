package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import controller.CtrPrincipal;
import controller.CtrMatricula;
import controller.CtrAlumno;
import utilily.Recursos;

public class FrmPrincipal extends JFrame {    
    public JInternalFrame intPanel;
    public JLabel lblBienvenida;
    Recursos r;
    CtrPrincipal ctrmain;
    CtrAlumno objctr1;
    CtrMatricula objctr2;
    
    public FrmPrincipal() {        
        setSize(1250, 800);
        setLocation(340,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        r = new Recursos();        
        ctrmain = new CtrPrincipal(this);
        objctr1 = new CtrAlumno(this);
        objctr2 = new CtrMatricula(this);
        
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
        
        JMenu menuRegistro = new JMenu("Matrícula");
            JMenuItem miMatriculaPostulante = new JMenuItem("Postulante");
            miMatriculaPostulante.setIcon(r.iconoadd);
            menuRegistro.add(miMatriculaPostulante);            
            JMenuItem miMatriculaRegular = new JMenuItem("Regular");
            miMatriculaRegular.setIcon(r.iconoadd);        
            menuRegistro.add(miMatriculaRegular);
        menuBar.add(menuRegistro);
        
        JMenu menuBusqueda = new JMenu("Revisar");
            JMenuItem miMatricula = new JMenuItem("Revisar Matricula");
            miMatricula.setIcon(r.iconoLupa);
            menuBusqueda.add(miMatricula);            
            JMenuItem miVoucher = new JMenuItem("Revisar Voucher");
            miVoucher.setIcon(r.iconoLupa);
            menuBusqueda.add(miVoucher);            
            JMenuItem miHistorial = new JMenuItem("Revisar Historial");
            miHistorial.setIcon(r.iconoLupa);
            menuBusqueda.add(miHistorial);     
        menuBar.add(menuBusqueda); 
        
        JMenu menuOpciones = new JMenu("Opciones");
            JMenuItem miModificar = new JMenuItem("Modificar");
            miModificar.setIcon(r.iconoclean);            
            menuOpciones.add(miModificar);            
            JMenuItem miImprimir = new JMenuItem("Imprimir");
            miImprimir.setIcon(r.iconoclean);            
            menuOpciones.add(miImprimir);            
        menuBar.add(menuOpciones);    
        
        miPrincipal.addActionListener(e -> MenuPrincipal());
        miAjustes.addActionListener(e -> Ajustes());
        miSalida.addActionListener(e -> ctrmain.confirmarSalida() );
        miMatriculaPostulante.addActionListener(e -> RegistroPostulante());
        miMatriculaRegular.addActionListener(e -> MatriculaRegular());
        miMatricula.addActionListener(e -> MatriculaRevisar());
        miVoucher.addActionListener(e -> VoucherRevisar());
        miHistorial.addActionListener(e -> HistoriaRevisar());
        miModificar.addActionListener(e -> ModificarDatos());
        miImprimir.addActionListener(e -> ImprimirDatos());   
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
        lblBienvenida.setBounds(530,30,600,25);
        lblBienvenida.setFont(r.arialXLB);
        paMenu.add(lblBienvenida);
        
        JSeparator spBienvenida = new JSeparator();
        spBienvenida.setBounds(530,60,560,15);
        spBienvenida.setForeground(r.negro);
        paMenu.add(spBienvenida); 
        
        ctrmain.bienvenida();
        
        JLabel lblIngresar = new JLabel("Matricular Estudiantes");
        lblIngresar.setBounds(530, 100, 300, 25);
        lblIngresar.setFont(r.arialL);
        paMenu.add(lblIngresar);

        JLabel lblModificar = new JLabel("Modificar Matrículas");
        lblModificar.setBounds(530, 270, 300, 25);
        lblModificar.setFont(r.arialL);
        paMenu.add(lblModificar);

        JLabel lblBuscar = new JLabel("Buscar Matrículas");
        lblBuscar.setBounds(530, 445, 300, 25);
        lblBuscar.setFont(r.arialL);
        paMenu.add(lblBuscar);

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(560, 670, 120, 30);
        paMenu.add(btnCerrar);

        JButton btnManual = new JButton("Manual");
        btnManual.setBounds(1080, 670, 120, 30);
        paMenu.add(btnManual);

        JButton btnMatPost = new JButton(" Postulante");
        btnMatPost.setBounds(560, 155, 200, 80);
        r.btnstyle(btnMatPost);
        btnMatPost.setIcon(r.iconoadd);
        paMenu.add(btnMatPost);

        JButton btnMatEst = new JButton(" Estudiante");
        btnMatEst.setBounds(780, 155, 200, 80);
        r.btnstyle(btnMatEst);
        btnMatEst.setIcon(r.iconoadd);
        paMenu.add(btnMatEst);
        
        JButton btnRevMat = new JButton(" Matricula");
        btnRevMat.setBounds(560, 330, 200, 80);
        r.btnstyle(btnRevMat);
        btnRevMat.setIcon(r.iconopencil);
        paMenu.add(btnRevMat);

        JButton btnRevVou = new JButton(" Vouchers");
        btnRevVou.setBounds(780, 330, 200, 80);
        r.btnstyle(btnRevVou);
        btnRevVou.setIcon(r.iconopencil);
        paMenu.add(btnRevVou);
        
        JButton btnRevHis = new JButton(" Historial");
        btnRevHis.setBounds(1000, 330, 200, 80);
        r.btnstyle(btnRevHis);   
        btnRevHis.setIcon(r.iconopencil);
        paMenu.add(btnRevHis);
        
        JButton btnModDat = new JButton("Modificar Datos");
        btnModDat.setBounds(560, 505, 200, 80);
        r.btnstyle(btnModDat);
        btnModDat.setIcon(r.iconoLupa);
        paMenu.add(btnModDat);
        
        JButton btnImpDat = new JButton("Imprimir Datos");
        btnImpDat.setBounds(780, 505, 200, 80);
        r.btnstyle(btnImpDat);   
        btnImpDat.setIcon(r.iconoLupa);
        paMenu.add(btnImpDat);
        
        JPanel panelBarra = r.panelBarra;
        panelBarra.setBounds(0, 790, 1440, 50);
        paMenu.add(panelBarra);
        
        JPanel unfv_local2 = r.unfv_local2;
        unfv_local2.setBounds(0, 0, 500, 800);
        paMenu.add(unfv_local2);

        JPanel unfv_fiis_logo = r.unfv_fiis_logo;
        unfv_fiis_logo.setBounds(1340, 20, 64, 64);
        paMenu.add(unfv_fiis_logo);  
        
        JButton btn = new JButton();       
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        paMenu.add(btn);  
        
        btnCerrar.addActionListener(e -> ctrmain.confirmarSalida());        
        btnMatPost.addActionListener(e -> RegistroPostulante());        
        btnMatEst.addActionListener(e -> MatriculaRegular());
        btnRevMat.addActionListener(e -> MatriculaRevisar());
        btnRevVou.addActionListener(e -> VoucherRevisar());
        btnRevHis.addActionListener(e -> HistoriaRevisar());
        btnModDat.addActionListener(e -> ModificarDatos());
        btnImpDat.addActionListener(e -> ImprimirDatos());
        
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
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in1 = new JInternalFrame();
        in1.setBounds(-5,-30,1260, 860);
        JPanel pa1 = new JPanel();
        pa1.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Inscripción de Alumnos");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setForeground(r.naranja);
        lblTitulo.setBounds(450, 25, 400, 30);
        pa1.add(lblTitulo);
        
        JLabel lblbloque1 = new JLabel("  ---- Rellenar Datos ----");
        lblbloque1.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque1.setForeground(r.naranja);
        lblbloque1.setBounds(80, 80, 400, 30);
        pa1.add(lblbloque1);
        
        JLabel lblbloque2 = new JLabel(" ---- Menú Opciones ----");
        lblbloque2.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque2.setForeground(r.naranja);
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
        btn1Registrar.setIcon(r.iconoadd);
        pa1.add(btn1Registrar);
        
        JButton btn1Modificar = new JButton("Modificar");
        btn1Modificar.setBounds(225, 500, 125, 30);
        btn1Modificar.setIcon(r.iconopencil);
        pa1.add(btn1Modificar);
        
        JButton btn1Completar = new JButton("Llenar");
        btn1Completar.setBounds(80, 560, 125, 30);
        btn1Completar.setIcon(r.iconoLupa);
        pa1.add(btn1Completar);
        
        JButton btn1Eliminar = new JButton("Eliminar");
        btn1Eliminar.setBounds(225, 560, 125, 30);
        btn1Eliminar.setIcon(r.iconodelete);
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
          
        objctr1.cargarTablaAlumnos();
        objctr1.cargarCarreras();
        btn1Registrar.addActionListener((e) -> objctr1.registrarAlumno());
        btn1Modificar.addActionListener((e) -> objctr1.modificarAlumno());
        btn1Completar.addActionListener((e) -> objctr1.completarAlumno());
        btn1Eliminar.addActionListener((e) -> objctr1.eliminarAlumno());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa1.add(btnVolver);
        
        in1.add(pa1);
        in1.setVisible(true);
        add(in1);
        intPanel = in1;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
    
    //---PANEL 2: ---    
    public  JComboBox cbo2Cursos;
    public  JComboBox cbo2Años, cbo2Ciclos, cbo2Seccion;
    public  JTable tabla2Matricula;
    public  DefaultTableModel modelo2Tabla;
    public  JTable tblMatricula;
    public  JTextField txt2CodAlumno, txt2Alumno, txt2Carrera, txt2Escuela;   
    
    public void MatriculaRegular(){          
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
                
        objctr2.cargarAños();
        btn2Buscar.addActionListener((e) -> {
            objctr2.buscarAlumno();    
            objctr2.cargarAsignaturas();
        });
        cbo2Años.addActionListener(e -> objctr2.cargarCiclos());
        cbo2Ciclos.addActionListener(e -> objctr2.cargarAsignaturas());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa2.add(btnVolver); 
        
        in2.add(pa2);
        in2.setVisible(true);
        add(in2);
        intPanel = in2;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));        
    }
    public void Ajustes(){
        
    }
    
    //---PANEL 3: ---    
    public JLabel lbl3BuscarId, lbl3BuscarNombre;
    public JTextField txt3IdAlumno, txt3NombreAlumno;
    public JButton btn3Buscar, btn3Imprimir, btn3Limpiar;
    public JTable tabla3Matricula;
    public DefaultTableModel modelo3Tabla;
    
    public void MatriculaRevisar(){       
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in3 = new JInternalFrame();
        in3.setBounds(-5,-30,1260, 860);
        JPanel pa3 = new JPanel();
        pa3.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Buscar e Imprimir Matrícula");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa3.add(lblTitulo);

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
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa3.add(btnVolver);
        
        in3.add(pa3);
        in3.setVisible(true);
        add(in3);
        intPanel = in3;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
    //---PANEL 4: ---  
    public JLabel lbl4BuscarId, lbl4BuscarCiclo;
    public JTextField txt4IdAlumno, txt4Ciclo;
    public JButton btn4Buscar, btn4Verificar, btn4Limpiar;
    public JTable tabla4Vouchers;
    public DefaultTableModel modelo4Tabla;
    public String[] titu4tabla = {"ID Voucher", "Fecha", "ID Curso", "Nombre Curso", "Monto", "Estado de Verificación"};
    
    public void VoucherRevisar(){   
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in4 = new JInternalFrame();
        in4.setBounds(-5,-30,1260, 860);
        JPanel pa4 = new JPanel();
        pa4.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Revisión de Vouchers de Pago");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa4.add(lblTitulo);
              
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
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa4.add(btnVolver); 
        
        in4.add(pa4);
        in4.setVisible(true);
        add(in4);
        intPanel = in4;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));    
    }
    //---PANEL 5: ---  
    public JLabel lbl5BuscarId, lbl5BuscarCiclo;
    public JTextField txt5IdAlumno, txt5Ciclo;
    public JButton btn5Buscar, btn5Limpiar;
    public JTable tabla5Historial;
    public DefaultTableModel modelo5Tabla;
    public String[] titu5tabla = {"ID Alumno", "Ciclo", "Curso", "Nota", "Estado"};
    
    public void HistoriaRevisar(){       
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in5 = new JInternalFrame();
        in5.setBounds(-5,-30,1260, 860);
        JPanel pa5 = new JPanel();
        pa5.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Búsqueda de Historial Académico");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa5.add(lblTitulo);
        
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

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa5.add(btnVolver);
        
        in5.add(pa5);
        in5.setVisible(true);
        add(in5);
        intPanel = in5;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));    
    }
    //---PANEL 6: ---    
    public JLabel lbl6CodAlumno, lbl6Ciclo, lbl6Año, lbl6Carrera, lbl6Cursos;
    public JTextField txt6CodAlumno, txt6Año, txt6Ciclo;
    public JComboBox cbo6Carrera, cbo6Cursos;
    public JButton btn6Modificar, btn6Limpiar;
    public String[] carreras = {"Ingeniería de Sistemas", "Ingeniería de Transporte", "Ingeniería Industrial", "Ingeniería Agroindustrial"};
            
    public void ModificarDatos(){   
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in6 = new JInternalFrame();
        in6.setBounds(-5,-30,1260, 860);
        JPanel pa6 = new JPanel();
        pa6.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Modificar Datos del Alumno");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa6.add(lblTitulo);

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
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa6.add(btnVolver);   
        
        in6.add(pa6);
        in6.setVisible(true);
        add(in6);
        intPanel = in6;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));    
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
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in7 = new JInternalFrame();
        in7.setBounds(-5,-30,1260, 860);
        JPanel pa7 = new JPanel();
        pa7.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Imprimir Datos del Alumno");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(500, 20, 500, 40);
        pa7.add(lblTitulo);

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
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa7.add(btnVolver);  
        
        in7.add(pa7);
        in7.setVisible(true);
        add(in7);
        intPanel = in7;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    } 
}
    