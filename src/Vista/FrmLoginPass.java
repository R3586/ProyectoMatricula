package Vista;

import Controlador.CtrLoginPass;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import utilidad.Recursos;

public class FrmLoginPass extends JFrame {
    public JButton btnMostrar;
    public JTextField txtUsuario;
    public JPasswordField txtContraseña, txtConfirmar;
    
    Recursos r;
    CtrLoginPass ctrlogPass;
    
    public FrmLoginPass() {
        setLocation(650, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(640, 495);
        setLayout(null);
        setResizable(true);
        setUndecorated(false);
        //640, 460
        r = new Recursos();
        ctrlogPass = new CtrLoginPass(this);
//        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
                
        JLabel lblTitulo = new JLabel("Registro Único de");
        lblTitulo.setBounds(60, 20, 260, 28);
        lblTitulo.setForeground(r.naranja);
        lblTitulo.setFont(r.robotoXLB);
        add(lblTitulo);
        
        JLabel lblTitulo1 = new JLabel("Estudiante");
        lblTitulo1.setBounds(105, 52, 260, 28);
        lblTitulo1.setForeground(r.naranja);
        lblTitulo1.setFont(r.robotoXLB);
        add(lblTitulo1);
        
        JLabel lblUsuario = new JLabel("Correo Institucional");
        lblUsuario.setBounds(30, 100, 260, 25);
        lblUsuario.setFont(r.robotoSB);
        add(lblUsuario);              
        
        txtUsuario = new JTextField(r.Usuario);
        txtUsuario.setBounds(30, 135, 260, 30);
        txtUsuario.setFont(r.arialS);
        txtUsuario.setBorder(null);
        txtUsuario.setBackground(r.blanco);
        txtUsuario.setEditable(false);
        add(txtUsuario);
        
        JSeparator spUsuario = new JSeparator();
        spUsuario.setBounds(30,165,260,15);
        spUsuario.setForeground(r.negro);
        add(spUsuario);   

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(30, 185, 260, 25);
        lblContraseña.setFont(r.robotoSB);
        add(lblContraseña);
        
        txtContraseña = new JPasswordField("*******");
        txtContraseña.setBounds(30, 220, 260, 30);
        r.txtstyle1(txtContraseña);
        add(txtContraseña);
        
        JSeparator spContraseña = new JSeparator();
        spContraseña.setBounds(30,250,260,15);
        spContraseña.setForeground(r.negro);
        add(spContraseña);   
        
        JLabel lblConfirmar = new JLabel("Confirmar Contraseña");
        lblConfirmar.setBounds(30, 270, 260, 25);
        lblConfirmar.setFont(r.robotoSB);
        add(lblConfirmar);  
        
        txtConfirmar = new JPasswordField("*******");
        txtConfirmar.setBounds(30, 305, 260, 30);
        r.txtstyle1(txtConfirmar);
        add(txtConfirmar); 
                      
        JSeparator spConfirmar = new JSeparator();
        spConfirmar.setBounds(30,335,260,15);
        spConfirmar.setForeground(r.negro);
        add(spConfirmar); 
        
        JPanel unfv_local = r.unfv_local2;
        unfv_local.setBounds(320,-30,350,560);
        add(unfv_local);       
        
        JPanel panelBarra = r.panelBarra;
        panelBarra.setBounds(0,435,640,30);
        add(panelBarra);
        
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(30, 380, 120, 35);
        r.btnstyle(btnContinuar);
        add(btnContinuar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(170, 380, 120, 35);
        r.btnstyle(btnVolver);
        add(btnVolver);
        
        btnContinuar.addActionListener(e -> ctrlogPass.continuar());
        btnVolver.addActionListener(e -> ctrlogPass.volver());
        ctrlogPass.marcar(txtContraseña, "*******"); 
        ctrlogPass.marcar(txtConfirmar, "*******");
        
        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(232,342,50,25);
        btnMostrar.setForeground(null);
        btnMostrar.setBackground(null);
        btnMostrar.setBorder(null);
        add(btnMostrar);
        
        btnMostrar.addActionListener(e -> ctrlogPass.mostrarOcultar());
        
        JButton btn = new JButton();
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        add(btn);        
    }
}