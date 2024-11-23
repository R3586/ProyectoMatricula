package view;

import controller.CtrLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import utilily.Recursos;

public final class FrmLogin extends JFrame {
    public JLabel lb1Mensaje;
    public JButton btnMostrar;
    public JTextField txtUsuario;
    public JPasswordField txtContraseña;
    
    Recursos r;
    CtrLogin ctrlog;
    
    public FrmLogin() {
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(980, 580);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        
        r = new Recursos();
        ctrlog = new CtrLogin(this);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
    
        lb1Mensaje = new JLabel();
        lb1Mensaje.setBounds(100, 420, 280, 25);
        lb1Mensaje.setFont(r.arialS);
        add(lb1Mensaje);
        
        JLabel lblUsuario = new JLabel("Correo Institucional");
        lblUsuario.setBounds(100, 220, 200, 25);
        lblUsuario.setFont(r.robotoMB);
        add(lblUsuario);      

        txtUsuario = new JTextField("@unfv.edu.pe");
        txtUsuario.setBounds(100, 250, 300, 40);
        r.txtstyle(txtUsuario);
        add(txtUsuario);
        
        JSeparator spUsuario = new JSeparator();
        spUsuario.setBounds(100,290,300,15);
        spUsuario.setForeground(Color.BLACK);
        add(spUsuario); 
        
        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(100, 330, 200, 25);
        lblContraseña.setFont(r.robotoMB);
        add(lblContraseña);
        
        txtContraseña = new JPasswordField("********");
        txtContraseña.setBounds(100, 360, 300, 40);
        r.txtstyle(txtContraseña);
        add(txtContraseña);  
        
        JSeparator spContraseña = new JSeparator();
        spContraseña.setBounds(100,400,300,15);
        spContraseña.setForeground(Color.BLACK);
        add(spContraseña);
        
        JLabel label = new JLabel("ver 0.2.5");
        label.setBounds(15,520,100,25);
        add(label);
        
        JPanel unfv_local = r.unfv_local;
        unfv_local.setBounds(500, 0, 600, 605);
        add(unfv_local);
        
        JPanel unfv_logo = r.unfv_logo;
        unfv_logo.setBounds(100, 50, 300, 120);
        add(unfv_logo);
        
        JPanel panelBarra = r.panelBarra;
        panelBarra.setBounds(0, 550, 1280, 80);
        add(panelBarra);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 460, 120, 40);
        r.btnstyle(btnEntrar);
        add(btnEntrar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(280, 460, 120, 40);
        r.btnstyle(btnSalir);   
        add(btnSalir);
        
        btnEntrar.addActionListener(e -> ctrlog.entrar());
        btnSalir.addActionListener(e -> ctrlog.salir());
        ctrlog.marcar(txtUsuario, "@unfv.edu.pe");
        ctrlog.marcar(txtContraseña, "********");   
//(100, 420, 280, 25);
        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(345,410,50,25);
        btnMostrar.setForeground(null);
        btnMostrar.setBackground(null);
        btnMostrar.setBorder(null);
        add(btnMostrar);
        
        btnMostrar.addActionListener(e -> ctrlog.mostrarOcultar()); 

        JButton btn = new JButton();
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        add(btn);        
    }   
}           