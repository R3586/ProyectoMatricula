package view;

import controller.CtrLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public final class FrmLogin extends JFrame {
    public JButton btnEntrar, btnSalir;
    public JLabel lblUsuario, lblContraseña, lb1Mensaje;
    public JTextField txtUsuario;
    public JPasswordField txtContraseña;
    JPanel panelImagen, panelLogo, panelBarra;
    JSeparator spUsuario, spContraseña;
    CtrLogin login;
    
    public FrmLogin() {
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(980, 580);
        setLayout(null);
        setResizable(true);
        setUndecorated(true);
        Font fuente = new Font("Arial", Font.PLAIN, 18);
        Font fuente2 = new Font("Roboto Light", Font.BOLD, 20);
        
        login = new CtrLogin(this);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));

        lblUsuario = new JLabel("Correo Institucional");
        lblUsuario.setBounds(100, 220, 200, 25);
        lblUsuario.setFont(fuente2);
        add(lblUsuario);      
        
        lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(100, 330, 200, 25);
        lblContraseña.setFont(fuente2);
        add(lblContraseña);
        
        lb1Mensaje = new JLabel("");
        lb1Mensaje.setBounds(100, 420, 280, 25);
        lb1Mensaje.setFont(fuente);
        add(lb1Mensaje);
        
        spUsuario = new JSeparator();
        spUsuario.setBounds(100,290,300,15);
        spUsuario.setForeground(Color.BLACK);
        add(spUsuario);   

        txtUsuario = new JTextField("@unfv.edu.pe");
        txtUsuario.setBounds(100, 250, 300, 40);
        txtUsuario.setForeground(Color.GRAY);
        txtUsuario.setFont(fuente);
        txtUsuario.setBorder(null);
        add(txtUsuario);

        spContraseña = new JSeparator();
        spContraseña.setBounds(100,400,300,15);
        spContraseña.setForeground(Color.BLACK);
        add(spContraseña);   
        
        txtContraseña = new JPasswordField("********");
        txtContraseña.setBounds(100, 360, 300, 40);
        txtContraseña.setForeground(Color.GRAY);
        txtContraseña.setFont(fuente);
        txtContraseña.setBorder(null);
        add(txtContraseña);        
              
        JLabel label = new JLabel("ver 0.1.9");
        label.setBounds(15,520,100,25);
        add(label);
        
        panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_local.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelImagen.setBounds(500, 0, 600, 605);
        add(panelImagen);
        
        panelLogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_logo.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelLogo.setBounds(100, 50, 300, 120);
        add(panelLogo);
        
        panelBarra = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/barra_naranja.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelBarra.setBounds(0, 550, 1280, 80);
        add(panelBarra);
        
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 460, 120, 40);
        btnEntrar.setFont(fuente);
        btnEntrar.setBackground(new Color(255, 128, 0));
        btnEntrar.setForeground(Color.WHITE);
        add(btnEntrar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(280, 460, 120, 40);
        btnSalir.setFont(fuente);
        btnSalir.setBackground(new Color(255, 128, 0));
        btnSalir.setForeground(Color.WHITE);        
        add(btnSalir);
        
        btnEntrar.addActionListener(e -> login.Entrar());
        btnSalir.addActionListener(e -> login.Salir());
        login.marcador(txtUsuario, "@unfv.edu.pe");
        login.marcador(txtContraseña, "********");
//        JButton btnMostrar = new JButton("Mostrar");
//        btnMostrar.setBounds(300,405,100,30);
//        btnMostrar.setForeground(null);
//        btnMostrar.setBackground(null);
//        btnMostrar.setBorder(null);
//        add(btnMostrar);
//        btnMostrar.addActionListener(new ActionListener() {
//            private boolean isPasswordVisible = false;
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isPasswordVisible) {
//                    txtContraseña.setFont(fuente);
//                    txtContraseña.setEchoChar('*');
//                    btnMostrar.setText("Mostrar");
//                } else {
//                    txtContraseña.setFont(fuente);
//                    txtContraseña.setEchoChar((char) 0);
//                    btnMostrar.setText("Ocultar");
//                }
//                isPasswordVisible = !isPasswordVisible;
//                txtContraseña.setFont(fuente);
//            }
//        });        
                
        JButton btn = new JButton();
        btn.setBounds(-20,0,10,10);
        SwingUtilities.invokeLater(() -> btn.requestFocus());
        add(btn);        
    }   
}