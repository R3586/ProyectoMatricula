package Controlador;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import utilidad.Recursos;
import Vista.FrmLogin;
import Vista.FrmPrincipal_std;

public class CtrPrincipal_std {
    FrmPrincipal_std vista;
    Recursos r;
    
    public CtrPrincipal_std(FrmPrincipal_std vista) {
        this.vista = vista;
        r = new Recursos();
    }
    
    public void bienvenida(){
        vista.lblBienvenida.setText("Hola!, " + r.ApeUsuario +".");
    }
    
    public JInternalFrame volver(JInternalFrame internal){          
        internal.dispose();
        vista.MenuPrincipal();
        return internal;
    }
    
    public JInternalFrame cerrarIntPanel(JInternalFrame internal){
        if(internal != null){
            internal.dispose();
        }
        return internal;
    }    
    
    public void confirmarSalida(){
        int salida = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(salida == JOptionPane.YES_OPTION){
            vista.dispose();
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
        }
    }
}
