package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import Controlador.CtrPrincipal;
import Controlador.CtrMatricula;
import Controlador.CtrAlumno;
import Controlador.CtrHistorial;
import javax.swing.table.DefaultTableCellRenderer;
import utilidad.Recursos;

public class FrmPrincipal extends JFrame {    
    public JInternalFrame intPanel;
    public JLabel lblBienvenida;
    Recursos r;
    CtrPrincipal ctrmain;
    CtrAlumno objctr1;
    CtrMatricula objctr2;
    CtrHistorial objctr3;
    
    public FrmPrincipal() {        
        setSize(1250, 800);
        setLocation(340,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        r = new Recursos();        
        ctrmain = new CtrPrincipal(this);
        objctr1 = new CtrAlumno(this);
        objctr2 = new CtrMatricula(this);
        objctr3 = new CtrHistorial(this);
        
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
        
        JMenu menuRegistro = new JMenu("Registro");
            JMenuItem miEstudiante = new JMenuItem("Estudiante");
            miEstudiante.setIcon(r.iconoadd);
            menuRegistro.add(miEstudiante);            
            JMenuItem miMatricular = new JMenuItem("Matricula");
            miMatricular.setIcon(r.iconoadd);        
            menuRegistro.add(miMatricular);
        menuBar.add(menuRegistro);
        
        JMenu menuNotas = new JMenu("Boletas");
            JMenuItem miNotas = new JMenuItem("Alumno");
            miNotas.setIcon(r.iconoclean);            
            menuNotas.add(miNotas);            
            JMenuItem miNotasSeccion = new JMenuItem("Sección");
            miNotasSeccion.setIcon(r.iconoclean);            
            menuNotas.add(miNotasSeccion);            
        menuBar.add(menuNotas);    
        
        JMenu menuBusqueda = new JMenu("Revisar");
            JMenuItem miMatricula = new JMenuItem("Matriculas");
            miMatricula.setIcon(r.iconoLupa);
            menuBusqueda.add(miMatricula);            
            JMenuItem miVoucher = new JMenuItem("Vouchers");
            miVoucher.setIcon(r.iconoLupa);
            menuBusqueda.add(miVoucher);            
            JMenuItem miHistorial = new JMenuItem("Boletas");
            miHistorial.setIcon(r.iconoLupa);
            menuBusqueda.add(miHistorial);     
        menuBar.add(menuBusqueda); 
        
        if (!r.admin){
            miEstudiante.setEnabled(false);
            miNotas.setEnabled(false);
            miMatricula.setEnabled(false);
            miVoucher.setEnabled(false);
        }
        
        miPrincipal.addActionListener(e -> MenuPrincipal());
        miAjustes.addActionListener(e -> Ajustes());
        miSalida.addActionListener(e -> ctrmain.confirmarSalida());
        
        miEstudiante.addActionListener(e -> registroAlumnos());
        miMatricular.addActionListener(e -> matriculaRegular());
        
        miNotas.addActionListener(e -> boletaAlumno());
        miNotasSeccion.addActionListener(e -> boletaSeccion());   
        
        miMatricula.addActionListener(e -> revisarMatricula());
        miVoucher.addActionListener(e -> revisarVoucher());
        miHistorial.addActionListener(e -> revisarHistorial());
    }
        
    //------CODIGO DE INTERNAL PANELS-------------    
    public void MenuPrincipal(){        
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame inMenu = new JInternalFrame();
        inMenu.setBounds(-5,-30,1260, 860);
        JPanel paMenu = new JPanel();
        paMenu.setLayout(null);
        paMenu.setBackground(Color.WHITE);
        
        lblBienvenida = new JLabel("Bienvenid@, " + r.ApeUsuario + " " + r.NomUsuario);
        lblBienvenida.setBounds(530,25,600,35);
        lblBienvenida.setFont(r.arialXLB);
        paMenu.add(lblBienvenida);
        
        JSeparator spBienvenida = new JSeparator();
        spBienvenida.setBounds(530,65,570,20);
        spBienvenida.setForeground(r.negro);
        paMenu.add(spBienvenida); 
        
        JLabel lblEstudiantes = new JLabel("Registro de Estudiantes");
        lblEstudiantes.setBounds(530, 100, 300, 25);
        lblEstudiantes.setFont(r.arialL);
        paMenu.add(lblEstudiantes);
        
            JButton btnEstudiante = new JButton("<html> Registro de <br> Alumnos </html>");
            btnEstudiante.setBounds(550, 155, 200, 80);
            r.btnstyle(btnEstudiante);
            btnEstudiante.setIcon(r.iconoadd);
            paMenu.add(btnEstudiante);

            JButton btnMatricular = new JButton("<html> Matricular <br> por Estudiante </html>");
            btnMatricular.setBounds(770, 155, 200, 80);
            r.btnstyle(btnMatricular);
            btnMatricular.setIcon(r.iconoadd);
            paMenu.add(btnMatricular);

        JLabel lblMatriculas = new JLabel("Rendimiento Académico");
        lblMatriculas.setBounds(530, 270, 300, 25);
        lblMatriculas.setFont(r.arialL);
        paMenu.add(lblMatriculas);
        
            JButton btnNotas = new JButton("<html> Boleta <br> por Estudiante </html>");
            btnNotas.setBounds(550, 330, 200, 80);
            r.btnstyle(btnNotas);
            btnNotas.setIcon(r.iconoLupa);
            paMenu.add(btnNotas);

            JButton btnNotasSeccion = new JButton("<html> Boleta <br> por Seccion </html>");
            btnNotasSeccion.setBounds(770, 330, 200, 80);
            r.btnstyle(btnNotasSeccion);   
            btnNotasSeccion.setIcon(r.iconoLupa);
            paMenu.add(btnNotasSeccion);

        JLabel lblRendimiento = new JLabel("Revisión Administrativa");
        lblRendimiento.setBounds(530, 445, 400, 25);
        lblRendimiento.setFont(r.arialL);
        paMenu.add(lblRendimiento);
        
            JButton btnRevMatricula = new JButton("<html> Estado de <br> Matricula </html>");
            btnRevMatricula.setBounds(550, 505, 200, 80);
            r.btnstyle(btnRevMatricula);
            btnRevMatricula.setIcon(r.iconopencil);
            paMenu.add(btnRevMatricula);
        
            JButton btnRevVoucher = new JButton("<html> Estado de <br> Voucher </html>");
            btnRevVoucher.setBounds(770, 505, 200, 80);
            r.btnstyle(btnRevVoucher);
            btnRevVoucher.setIcon(r.iconopencil);
            paMenu.add(btnRevVoucher);

            JButton btnRevRendimiento = new JButton("<html> Rendimiento <br> Académico </html>");
            btnRevRendimiento.setBounds(990, 505, 200, 80);
            r.btnstyle(btnRevRendimiento);   
            btnRevRendimiento.setIcon(r.iconopencil);
            paMenu.add(btnRevRendimiento);
            
           
        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(560, 670, 120, 30);
        paMenu.add(btnCerrar);

        JButton btnManual = new JButton("Manual");
        btnManual.setBounds(1080, 670, 120, 30);
        paMenu.add(btnManual);
               
        JPanel panelBarra = r.panelBarra;
        panelBarra.setBounds(0, 790, 1440, 50);
        paMenu.add(panelBarra);
        
        JPanel unfv_local2 = r.unfv_local2;
        unfv_local2.setBounds(0, 0, 500, 800);
        paMenu.add(unfv_local2);

        JPanel unfv_fiis_logo = r.unfv_fiis_logo;
        unfv_fiis_logo.setBounds(1115, 15, 100, 100);
        paMenu.add(unfv_fiis_logo);  

        JButton btn = new JButton();       
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        paMenu.add(btn);  
        
        if (!r.admin){
            btnEstudiante.setEnabled(false);
            btnNotas.setEnabled(false);
            btnRevMatricula.setEnabled(false);
            btnRevVoucher.setEnabled(false);
        }
        
        btnCerrar.addActionListener(e -> ctrmain.confirmarSalida());  
        
        btnEstudiante.addActionListener(e -> registroAlumnos());        
        btnMatricular.addActionListener(e -> matriculaRegular());
        
        btnNotas.addActionListener(e -> boletaAlumno());
        btnNotasSeccion.addActionListener(e -> boletaSeccion());     
        
        btnRevMatricula.addActionListener(e -> revisarMatricula());
        btnRevVoucher.addActionListener(e -> revisarVoucher());
        btnRevRendimiento.addActionListener(e -> revisarHistorial());
        
        inMenu.add(paMenu);
        inMenu.setVisible(true);
        add(inMenu);        
        intPanel = inMenu;
    } 
    
    public void Ajustes(){
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(30,50,200,400);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
    }
    
    //---PANEL 1: ---       
    public JTextField txt1Codigo, txt1Nombres, txt1Apellidos, txt1Direccion, txt1DNI;
    public JComboBox cbo1Carrera;
    public JTable tblAlumnos;
    public DefaultTableModel modelo1Tabla;
    
    public void registroAlumnos(){           
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Inscripción de Alumnos");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setForeground(r.naranja);
        lblTitulo.setBounds(450, 25, 400, 30);
        pa.add(lblTitulo);
        
        JLabel lblbloque1 = new JLabel("  ---- Rellenar Datos ----");
        lblbloque1.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque1.setForeground(r.naranja);
        lblbloque1.setBounds(80, 80, 400, 30);
        pa.add(lblbloque1);
            
            JLabel lblCodigo = new JLabel("Código:");
            lblCodigo.setBounds(80, 125, 100, 30);
            pa.add(lblCodigo);

            txt1Codigo = new JTextField();
            txt1Codigo.setBounds(175, 125, 175, 30);
            pa.add(txt1Codigo);

            JLabel lblApellidos = new JLabel("Apellidos:");
            lblApellidos.setBounds(80, 175, 100, 30);
            pa.add(lblApellidos);

            txt1Apellidos = new JTextField();
            txt1Apellidos.setBounds(175, 175, 175, 30);
            pa.add(txt1Apellidos);

            JLabel lblNombres = new JLabel("Nombres:");
            lblNombres.setBounds(80, 225, 100, 30);
            pa.add(lblNombres);

            txt1Nombres = new JTextField();
            txt1Nombres.setBounds(175, 225, 175, 30);
            pa.add(txt1Nombres);

            JLabel lblDireccion = new JLabel("Dirección:");
            lblDireccion.setBounds(80, 275, 100, 30);
            pa.add(lblDireccion);

            txt1Direccion = new JTextField();
            txt1Direccion.setBounds(175, 275, 175, 30);
            pa.add(txt1Direccion);

            JLabel lblDNI = new JLabel("DNI:");
            lblDNI.setBounds(80, 325, 100, 30);
            pa.add(lblDNI);

            txt1DNI = new JTextField();
            txt1DNI.setBounds(175, 325, 175, 30);
            pa.add(txt1DNI);

            JLabel lblCarrera = new JLabel("Carrera:");
            lblCarrera.setBounds(80, 375, 100, 30);
            pa.add(lblCarrera);

            cbo1Carrera = new JComboBox();
            cbo1Carrera.setBounds(175, 375, 175, 30);
            pa.add(cbo1Carrera);
        
        JLabel lblbloque2 = new JLabel(" ---- Menú Opciones ----");
        lblbloque2.setFont(new Font("Arial", Font.BOLD, 24));
        lblbloque2.setForeground(r.naranja);
        lblbloque2.setBounds(80, 450, 400, 30);
        pa.add(lblbloque2);       
        
            JButton btnRegistrar = new JButton("Registrar");
            btnRegistrar.setBounds(80, 500, 125, 30);
            btnRegistrar.setIcon(r.iconoadd);
            pa.add(btnRegistrar);

            JButton btnModificar = new JButton("Modificar");
            btnModificar.setBounds(225, 500, 125, 30);
            btnModificar.setIcon(r.iconopencil);
            pa.add(btnModificar);

            JButton btnCompletar = new JButton("Llenar");
            btnCompletar.setBounds(80, 560, 125, 30);
            btnCompletar.setIcon(r.iconoLupa);
            pa.add(btnCompletar);

            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.setBounds(225, 560, 125, 30);
            btnEliminar.setIcon(r.iconodelete);
            pa.add(btnEliminar);
        
        String datos[] = {"Código", "Apellidos", "Nombres", "Carrera", "DNI", "Dirección"};
        modelo1Tabla = new DefaultTableModel(null , datos);
        tblAlumnos = new JTable(modelo1Tabla);
        JScrollPane scrollPane = new JScrollPane(tblAlumnos);
        int[] tblColumnas = {90,150,150,120,80,180};
        for (int i = 0; i < tblColumnas.length; i++) {
            tblAlumnos.getColumnModel().getColumn(i).setPreferredWidth(tblColumnas[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblAlumnos.getColumnCount(); i++) {
            if (i!=1 && i!=2 && i!=5){
                tblAlumnos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        scrollPane.setBounds(410, 80, 740, 580);
        pa.add(scrollPane);
        
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
        btnRegistrar.addActionListener((e) -> objctr1.registrarAlumno());
        btnModificar.addActionListener((e) -> objctr1.modificarAlumno());
        btnCompletar.addActionListener((e) -> objctr1.completarAlumno());
        btnEliminar.addActionListener((e) -> objctr1.eliminarAlumno());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
    
//    ---PANEL 2: ---    
    public  JComboBox cbo2Cursos, cbo2Años, cbo2Ciclos, cbo2Seccion, cbo2Periodo;
    public  JTextField txt2CodAlumno, txt2Alumno, txt2Carrera, txt2Escuela, txt2IdVoucher, txt2MontoVoucher;
    public  JTable tblMatricula;
    public  DefaultTableModel modelo2Tabla;
    
    public void matriculaRegular(){          
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Matrícula de alumnos");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(450, 20, 400, 30);
        pa.add(lblTitulo);
        
        JButton btnBuscar = new JButton("Cargar Datos de Alumno");
        btnBuscar.setBounds(420, 90, 200, 30);
        pa.add(btnBuscar);
        
        JLabel lblCodAlumno = new JLabel("Código Alumno");
        lblCodAlumno.setBounds(80,90,100,30);
        pa.add(lblCodAlumno);     
        
        txt2CodAlumno = new JTextField();
        txt2CodAlumno.setBounds(200, 90, 90, 30);
        pa.add(txt2CodAlumno);
        
        JLabel lblAlumno = new JLabel("Nombre Alumno");
        lblAlumno.setBounds(80,150,100,30);
        pa.add(lblAlumno);
        
        txt2Alumno = new JTextField();
        txt2Alumno.setBounds(200,150,200,30);
        txt2Alumno.setBackground(null);
        txt2Alumno.setBorder(null);
        pa.add(txt2Alumno);
        
        JLabel lblCarrera = new JLabel("Carrera");
        lblCarrera.setBounds(420, 150, 100, 30);
        pa.add(lblCarrera);
        
        txt2Carrera = new JTextField();
        txt2Carrera.setBounds(500, 150, 200, 30);
        txt2Carrera.setBackground(null);
        txt2Carrera.setBorder(null);
        pa.add(txt2Carrera);
        
        JLabel lblEscuela = new JLabel("Escuela");
        lblEscuela.setBounds(720, 150, 100, 30);
        pa.add(lblEscuela);
        
        txt2Escuela = new JTextField();
        txt2Escuela.setBounds(800, 150, 300, 30);
        txt2Escuela.setBackground(null);
        txt2Escuela.setBorder(null);
        pa.add(txt2Escuela);

        JLabel lblAño = new JLabel("Año de Estudio");
        lblAño.setBounds(80, 210, 120, 30);
        pa.add(lblAño);

        cbo2Años = new JComboBox();
        cbo2Años.setBounds(200, 210, 50, 30);
        pa.add(cbo2Años);

        JLabel lblCiclos = new JLabel("Ciclo");
        lblCiclos.setBounds(320, 210, 100, 30);
        pa.add(lblCiclos);

        cbo2Ciclos = new JComboBox();
        cbo2Ciclos.setBounds(400, 210, 50, 30);
        pa.add(cbo2Ciclos);
        
        JLabel lblSeccion = new JLabel("Sección");
        lblSeccion.setBounds(500, 210, 100, 30);
        pa.add(lblSeccion);

        cbo2Seccion = new JComboBox(new String[]{"A", "B", "C"});
        cbo2Seccion.setBounds(600,210,50,30);
        pa.add(cbo2Seccion);

        JLabel lblCursos = new JLabel("Asignatura");
        lblCursos.setBounds(80, 270, 100, 30);
        pa.add(lblCursos);

        cbo2Cursos = new JComboBox();
        cbo2Cursos.setBounds(200, 270, 450, 30);
        pa.add(cbo2Cursos); 
        
        JLabel lblPeriodo = new JLabel("Periodo");
        lblPeriodo.setBounds(720, 90, 100, 30);
        pa.add(lblPeriodo);
        
        cbo2Periodo = new JComboBox();
        cbo2Periodo.setBounds(800,90,90,30);
        pa.add(cbo2Periodo);
                
        JLabel lbl2IdVoucher = new JLabel("ID Voucher");
        lbl2IdVoucher.setBounds(720, 210, 100, 30);
        pa.add(lbl2IdVoucher);
        
        txt2IdVoucher = new JTextField();
        txt2IdVoucher.setBounds(800, 210, 100, 30);
        pa.add(txt2IdVoucher);
        
        JLabel lbl2MontoVoucher = new JLabel("Monto S/.");
        lbl2MontoVoucher.setBounds(940, 210, 70, 30);
        pa.add(lbl2MontoVoucher);
        
        txt2MontoVoucher = new JTextField();
        txt2MontoVoucher.setBounds(1020, 210, 100, 30);
        pa.add(txt2MontoVoucher);

        JButton btnAñadir = new JButton("Añadir Curso");
        btnAñadir.setBounds(720, 270, 150, 30);
        pa.add(btnAñadir);        
        
        JButton btnEliminar = new JButton("Eliminar Curso");
        btnEliminar.setBounds(900, 270, 150, 30);
        btnEliminar.setEnabled(true);
        pa.add(btnEliminar);            

        JButton btnGuardar = new JButton("Guardar Matrícula");
        btnGuardar.setBounds(550, 630, 200, 30);
        pa.add(btnGuardar);      
        
        String datos[]={"Codigo","Asignatura","Periodo","Año","Ciclo","Sec","Cre","Voucher"};
        modelo2Tabla = new DefaultTableModel(null,datos);
        tblMatricula = new JTable(modelo2Tabla);
        JScrollPane scrollPane = new JScrollPane(tblMatricula);
        int[] tblColumnas = {200,500,100,80,80,80,80,150};
        for (int i = 0; i < tblColumnas.length; i++) {
            tblMatricula.getColumnModel().getColumn(i).setPreferredWidth(tblColumnas[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblMatricula.getColumnCount(); i++) {
            if (i!=1){
                tblMatricula.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        scrollPane.setBounds(80, 320, 1060, 300);
        pa.add(scrollPane);
                
        objctr2.cargarAños();
        objctr2.cargarPeriodos();        
        btnBuscar.addActionListener((e) -> {
            objctr2.buscarAlumno();    
            objctr2.cargarAsignaturas();
            objctr2.cargarMatriculaPeriodo();
        });
        cbo2Años.addActionListener(e -> objctr2.cargarCiclos());
        cbo2Ciclos.addActionListener(e -> objctr2.cargarAsignaturas());
        btnAñadir.addActionListener(e -> objctr2.registrarCursoMatricula());
        btnEliminar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Falta e.e"));
        btnGuardar.addActionListener(e -> objctr2.nuevaMatricula());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver); 
                
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));        
    }
    
    //---PANEL 3: ---        
    public JTextField txt3CodAlumno, txt3Alumno, txt3Carrera, txt3Escuela;
    public JComboBox cbo3Años, cbo3Ciclos, cbo3Cursos;
    public JTable tblHistorial;
    public DefaultTableModel modelo3Tabla;
    public JTextField txt3IdCurso, txt3EP, txt3EF, txt3PP, txt3SUST;
    
    public void boletaAlumno(){       
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Registro de Historial Académico");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(400, 20, 500, 40);
        pa.add(lblTitulo);

        JLabel lbl2CodAlumno = new JLabel("Código Alumno");
        lbl2CodAlumno.setBounds(80,90,100,30);
        pa.add(lbl2CodAlumno);     
        
        txt3CodAlumno = new JTextField();
        txt3CodAlumno.setBounds(200, 90, 200, 30);
        pa.add(txt3CodAlumno);
        
        JButton btnBuscar = new JButton("Cargar Datos de Alumno");
        btnBuscar.setBounds(420, 90, 200, 30);
        pa.add(btnBuscar);
        
        JLabel lbl2Alumno = new JLabel("Nombre Alumno");
        lbl2Alumno.setBounds(80,150,100,30);
        pa.add(lbl2Alumno);
        
        txt3Alumno = new JTextField();
        txt3Alumno.setBounds(200,150,200,30);
        txt3Alumno.setBackground(null);
        txt3Alumno.setBorder(null);
        pa.add(txt3Alumno);
                
        JLabel lbl2Carrera = new JLabel("Carrera");
        lbl2Carrera.setBounds(420, 150, 100, 30);
        pa.add(lbl2Carrera);

        txt3Carrera = new JTextField();
        txt3Carrera.setBounds(500, 150, 200, 30);
        txt3Carrera.setBackground(null);
        txt3Carrera.setBorder(null);
        pa.add(txt3Carrera);
        
        JLabel lbl2Escuela = new JLabel("Escuela");
        lbl2Escuela.setBounds(720, 150, 100, 30);
        pa.add(lbl2Escuela);

        txt3Escuela = new JTextField();
        txt3Escuela.setBounds(800, 150, 300, 30);
        txt3Escuela.setBackground(null);
        txt3Escuela.setBorder(null);
        pa.add(txt3Escuela);
        
        
        JLabel lblAño = new JLabel("Año de Estudio");
        lblAño.setBounds(720, 210, 120, 30);
        pa.add(lblAño);

        cbo3Años = new JComboBox();
        cbo3Años.setBounds(840, 210, 50, 30);
        pa.add(cbo3Años);

        JLabel lbl2Ciclos = new JLabel("Ciclo");
        lbl2Ciclos.setBounds(960, 210, 100, 30);
        pa.add(lbl2Ciclos);

        cbo3Ciclos = new JComboBox();
        cbo3Ciclos.setBounds(1020, 210, 50, 30);
        pa.add(cbo3Ciclos);

        JLabel lbl3Cursos = new JLabel("Asignatura");
        lbl3Cursos.setBounds(80, 210, 100, 30);
        pa.add(lbl3Cursos);

        cbo3Cursos = new JComboBox();
        cbo3Cursos.setBounds(200, 210, 450, 30);
        pa.add(cbo3Cursos);     
        
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(900,270,100,30);
        pa.add(btnModificar);
        
                
        JLabel lblcodigo = new JLabel("Codigo");
        lblcodigo.setBounds(80,270,100,30);
        pa.add(lblcodigo);
        
        txt3IdCurso = new JTextField();
        txt3IdCurso.setBounds(200,270,100,30);
        pa.add(txt3IdCurso);
        
        JLabel lblep = new JLabel("EP :");
        lblep.setBounds(350,270,60,30);
        pa.add(lblep);
        JLabel lblef = new JLabel("EF :");
        lblef.setBounds(450,270,60,30);
        pa.add(lblef);
        JLabel lblpp = new JLabel("PP :");
        lblpp.setBounds(550,270,60,30);
        pa.add(lblpp);
        JLabel lblsust = new JLabel("SUST:");
        lblsust.setBounds(650,270,60,30);
        pa.add(lblsust);
        
        
        txt3EP = new JTextField();
        txt3EP.setBounds(400,270,30,30);
        pa.add(txt3EP);
        txt3EF = new JTextField();
        txt3EF.setBounds(500,270,30,30);
        pa.add(txt3EF);            
        txt3PP = new JTextField();
        txt3PP.setBounds(600,270,30,30);
        pa.add(txt3PP);            
        txt3SUST = new JTextField();
        txt3SUST.setBounds(700,270,30,30);
        pa.add(txt3SUST);    
        
        
        
        String datos[] = {"Código", "Año", "Ciclo", "Asignatura", "EP", "EF", "PP", "SUST.", "PROM."};
        modelo3Tabla = new DefaultTableModel(null , datos);
        tblHistorial = new JTable(modelo3Tabla);
        JScrollPane scrollPane = new JScrollPane(tblHistorial);
        int[] tblColumnas = {70, 30, 30, 400, 20, 20, 20, 20, 20};
        for (int i = 0; i < tblColumnas.length; i++) {
            tblHistorial.getColumnModel().getColumn(i).setPreferredWidth(tblColumnas[i]);
        }
        scrollPane.setBounds(80, 320, 1060, 300);
        pa.add(scrollPane);
        tblHistorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblHistorial.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    txt3IdCurso.setText(tblHistorial.getValueAt(row, 0).toString());
                    txt3EP.setText(tblHistorial.getValueAt(row, 4).toString());
                    txt3EF.setText(tblHistorial.getValueAt(row, 5).toString());
                    txt3PP.setText(tblHistorial.getValueAt(row, 6).toString());
                    txt3SUST.setText(tblHistorial.getValueAt(row, 7) != null ? tblHistorial.getValueAt(row, 7).toString() : "");
                }
            }
        });      
        
        btnBuscar.addActionListener(e -> objctr3.cargarElementosHistorial());
        btnModificar.addActionListener(e -> objctr3.modificarAlumno());
        cbo3Años.addActionListener(e -> objctr3.cargarCiclos());
        cbo3Ciclos.addActionListener(e -> objctr3.cargarAsignaturas());
        cbo3Cursos.addActionListener(e -> objctr3.cargarIdAsignatura());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);
        
        JButton btnPDF = new JButton("Descargar PDF");
        btnPDF.setBounds(200,660,150,30);
        pa.add(btnPDF);
        btnPDF.addActionListener(e -> objctr3.generarPDF(tblHistorial, txt3CodAlumno, txt3Alumno));
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));        
    }
    
    //---PANEL 4: ---  
    public JTextField txt4CodAlumno, txt4Alumno, txt4Carrera, txt4Escuela;
    public JComboBox cbo4Años, cbo4Ciclos, cbo4Cursos;
    public JTable tblHistorialSec;
    public DefaultTableModel modelo4Tabla;
    public JTextField txt4IdCurso, txtt4P, txt4EF, txt4PP, txt4SUST;
    
    public void boletaSeccion(){
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Registro de Historial Académico");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(400, 20, 500, 40);
        pa.add(lblTitulo);

        JLabel lbl2CodAlumno = new JLabel("Código Alumno");
        lbl2CodAlumno.setBounds(80,90,100,30);
        pa.add(lbl2CodAlumno);     
        
        txt3CodAlumno = new JTextField();
        txt3CodAlumno.setBounds(200, 90, 200, 30);
        pa.add(txt3CodAlumno);
        
        JButton btnBuscar = new JButton("Cargar Datos de Alumno");
        btnBuscar.setBounds(420, 90, 200, 30);
        pa.add(btnBuscar);
        
        JLabel lbl2Alumno = new JLabel("Nombre Alumno");
        lbl2Alumno.setBounds(80,150,100,30);
        pa.add(lbl2Alumno);
        
        txt3Alumno = new JTextField();
        txt3Alumno.setBounds(200,150,200,30);
        txt3Alumno.setBackground(null);
        txt3Alumno.setBorder(null);
        pa.add(txt3Alumno);
                
        JLabel lbl2Carrera = new JLabel("Carrera");
        lbl2Carrera.setBounds(420, 150, 100, 30);
        pa.add(lbl2Carrera);

        txt3Carrera = new JTextField();
        txt3Carrera.setBounds(500, 150, 200, 30);
        txt3Carrera.setBackground(null);
        txt3Carrera.setBorder(null);
        pa.add(txt3Carrera);
        
        JLabel lbl2Escuela = new JLabel("Escuela");
        lbl2Escuela.setBounds(720, 150, 100, 30);
        pa.add(lbl2Escuela);

        txt3Escuela = new JTextField();
        txt3Escuela.setBounds(800, 150, 300, 30);
        txt3Escuela.setBackground(null);
        txt3Escuela.setBorder(null);
        pa.add(txt3Escuela);
        
        
        JLabel lblAño = new JLabel("Año de Estudio");
        lblAño.setBounds(720, 210, 120, 30);
        pa.add(lblAño);

        cbo3Años = new JComboBox();
        cbo3Años.setBounds(840, 210, 50, 30);
        pa.add(cbo3Años);

        JLabel lbl2Ciclos = new JLabel("Ciclo");
        lbl2Ciclos.setBounds(960, 210, 100, 30);
        pa.add(lbl2Ciclos);

        cbo3Ciclos = new JComboBox();
        cbo3Ciclos.setBounds(1020, 210, 50, 30);
        pa.add(cbo3Ciclos);

        JLabel lbl3Cursos = new JLabel("Asignatura");
        lbl3Cursos.setBounds(80, 210, 100, 30);
        pa.add(lbl3Cursos);

        cbo3Cursos = new JComboBox();
        cbo3Cursos.setBounds(200, 210, 450, 30);
        pa.add(cbo3Cursos);     
        
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(900,270,100,30);
        pa.add(btnModificar);
        
                
        JLabel lblcodigo = new JLabel("Codigo");
        lblcodigo.setBounds(80,270,100,30);
        pa.add(lblcodigo);
        
        txt3IdCurso = new JTextField();
        txt3IdCurso.setBounds(200,270,100,30);
        pa.add(txt3IdCurso);
        
        JLabel lblep = new JLabel("EP :");
        lblep.setBounds(350,270,60,30);
        pa.add(lblep);
        JLabel lblef = new JLabel("EF :");
        lblef.setBounds(450,270,60,30);
        pa.add(lblef);
        JLabel lblpp = new JLabel("PP :");
        lblpp.setBounds(550,270,60,30);
        pa.add(lblpp);
        JLabel lblsust = new JLabel("SUST:");
        lblsust.setBounds(650,270,60,30);
        pa.add(lblsust);
        
        
        txt3EP = new JTextField();
        txt3EP.setBounds(400,270,30,30);
        pa.add(txt3EP);
        txt3EF = new JTextField();
        txt3EF.setBounds(500,270,30,30);
        pa.add(txt3EF);            
        txt3PP = new JTextField();
        txt3PP.setBounds(600,270,30,30);
        pa.add(txt3PP);            
        txt3SUST = new JTextField();
        txt3SUST.setBounds(700,270,30,30);
        pa.add(txt3SUST);    
        
        
        
        String datos[] = {"Código", "Año", "Ciclo", "Asignatura", "EP", "EF", "PP", "SUST.", "PROM."};
        modelo3Tabla = new DefaultTableModel(null , datos);
        tblHistorial = new JTable(modelo3Tabla);
        JScrollPane scrollPane = new JScrollPane(tblHistorial);
        int[] tblColumnas = {70, 30, 30, 400, 20, 20, 20, 20, 20};
        for (int i = 0; i < tblColumnas.length; i++) {
            tblHistorial.getColumnModel().getColumn(i).setPreferredWidth(tblColumnas[i]);
        }
        scrollPane.setBounds(80, 320, 1060, 300);
        pa.add(scrollPane);
        tblHistorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblHistorial.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    txt3IdCurso.setText(tblHistorial.getValueAt(row, 0).toString());
                    txt3EP.setText(tblHistorial.getValueAt(row, 4).toString());
                    txt3EF.setText(tblHistorial.getValueAt(row, 5).toString());
                    txt3PP.setText(tblHistorial.getValueAt(row, 6).toString());
                    txt3SUST.setText(tblHistorial.getValueAt(row, 7) != null ? tblHistorial.getValueAt(row, 7).toString() : "");
                }
            }
        });      
        
        btnBuscar.addActionListener(e -> objctr3.cargarElementosHistorial());
        btnModificar.addActionListener(e -> objctr3.modificarAlumno());
        cbo3Años.addActionListener(e -> objctr3.cargarCiclos());
        cbo3Ciclos.addActionListener(e -> objctr3.cargarAsignaturas());
        cbo3Cursos.addActionListener(e -> objctr3.cargarIdAsignatura());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);  
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
        
    }
    
    //---PANEL 5: ---  
   public void revisarMatricula(){   
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Revisión de Matrícula");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(400, 20, 500, 40);
        pa.add(lblTitulo);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);  
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel)); 
    }   
    //---PANEL 6: ---
     
    public void revisarVoucher(){   
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Revisión de Voucher de Pago");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(400, 20, 500, 40);
        pa.add(lblTitulo);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    }
    //---PANEL 7: ---        
    public void revisarHistorial(){    
        ctrmain.cerrarIntPanel(intPanel);
        JInternalFrame in = new JInternalFrame();
        in.setBounds(-5,-30,1260, 860);
        JPanel pa = new JPanel();
        pa.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Revisión de Historial Académico");
        lblTitulo.setFont(r.arialXLB);
        lblTitulo.setBounds(400, 20, 500, 40);
        pa.add(lblTitulo);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 660, 100, 30);
        pa.add(btnVolver);  
        
        in.add(pa);
        in.setVisible(true);
        add(in);
        intPanel = in;
        btnVolver.addActionListener(e -> ctrmain.volver(intPanel));
    } 
}
    

/*String datos[]={"Periodo","Codigo","Turno","Sección","Asignatura","Año","Ciclo","Créd.","Voucher"};
        modelo2Tabla = new DefaultTableModel(null,datos);
        tblMatricula = new JTable(modelo2Tabla);
        JScrollPane scrollPane = new JScrollPane(tblMatricula);
        int[] tblColumnas = {200,500,100,80,80,80,80,150};
        for (int i = 0; i < tblColumnas.length; i++) {
            tblMatricula.getColumnModel().getColumn(i).setPreferredWidth(tblColumnas[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblMatricula.getColumnCount(); i++) {
            tblMatricula.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
*/