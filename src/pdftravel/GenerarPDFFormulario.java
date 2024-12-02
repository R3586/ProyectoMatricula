
package pdftravel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerarPDFFormulario extends JFrame {
    private JTextField txtMatricula;
    private JButton btnGenerarPDF;

    public GenerarPDFFormulario() {
        setTitle("Generar PDF de Matrícula");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblMatricula = new JLabel("Número de Matrícula:");
        lblMatricula.setBounds(30, 30, 150, 25);
        add(lblMatricula);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(180, 30, 150, 25);
        add(txtMatricula);

        btnGenerarPDF = new JButton("Generar PDF");
        btnGenerarPDF.setBounds(130, 100, 150, 30);
        add(btnGenerarPDF);

        // Agregar listener al botón
        btnGenerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarPDF(txtMatricula.getText());
            }
        });
    }

    private void generarPDF(String matricula) {
        try {
            // Llamar al método para generar el PDF
            PDFGenerator.generarPDF(matricula);
            JOptionPane.showMessageDialog(this, "PDF generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GenerarPDFFormulario().setVisible(true);
        });
    }
}