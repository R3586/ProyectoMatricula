    package utilidad;

import Controlador.CtrLogin;
import java.awt.*;
import javax.swing.*;

public class Recursos {    
    public String CodUsuario =  CtrLogin.CodUsuario, 
                NomUsuario =  CtrLogin.NomUsuario,
                ApeUsuario =  CtrLogin.ApeUsuario,
                Usuario =  CtrLogin.Usuario;
    
    public boolean admin = CtrLogin.admin;
    
    public ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/icons/buscar.png")),
            iconoadd = new ImageIcon(getClass().getResource("/icons/add.PNG")),
            iconoclean = new ImageIcon(getClass().getResource("/icons/clean.png")),
            iconodelete = new ImageIcon(getClass().getResource("/icons/delete.png")),
            iconologout = new ImageIcon(getClass().getResource("/icons/logout.png")),
            iconopencil = new ImageIcon(getClass().getResource("/icons/pencil.png"));
    
    public Font arialSS = new Font("Arial", Font.PLAIN, 16),
            arialS = new Font("Arial", Font.PLAIN, 18),
            arialL = new Font("Arial", Font.PLAIN, 24),
            arialLB = new Font("Arial", Font.BOLD, 24),
            arialXLB = new Font("Arial", Font.BOLD, 30),
            robotoSB = new Font("Roboto Light", Font.BOLD, 18),
            robotoMB = new Font("Roboto Light", Font.BOLD, 20),
            robotoLB = new Font("Roboto Light", Font.BOLD, 22),
            robotoXLB = new Font("Roboto Light", Font.BOLD, 24); 
    
    public Color naranja = new Color(255, 128, 0),
            negro = Color.BLACK,
            gris = Color.LIGHT_GRAY,
            blanco = Color.WHITE,
            rojo = Color.RED;
    
    public JPanel panelBarra = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/barra_naranja.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        },  unfv_logo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_logo.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        },  unfv_fiis_logo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_logo.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        },  unfv_local = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_local.PNG")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }, unfv_local2 = new JPanel() {
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_local_2.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }, unfv_local3 = new JPanel() {
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = new ImageIcon(getClass().getResource("/image/unfv_fiis_local_3.png")).getImage();
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);                
            }
        };
    
    public JButton btnstyle(JButton btn){
        btn.setFont(arialS);
        btn.setBackground(naranja);
        btn.setForeground(blanco);
        btn.setIconTextGap(15);
        return btn;
    }
    public JTextField txtstyle(JTextField txt){        
        txt.setForeground(gris);
        txt.setFont(arialS);
        txt.setBorder(null);
        return txt;
    }
    //Login_std
    public JTextField txtstyle1(JTextField txt){        
        txt.setForeground(gris);
        txt.setFont(arialSS);
        txt.setBorder(null);
        return txt;
    }
}
