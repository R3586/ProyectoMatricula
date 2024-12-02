package pdftravel;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PDFGenerator {

    public static void generarPDF(String matricula) throws Exception {
        // Ruta donde se guardará el PDF
        String filePath = "matricula_" + matricula + ".pdf";

        // Conectar a la base de datos y obtener la información de la matrícula
        String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
        String user = "tu_usuario";
        String password = "tu_password";
        String query = "SELECT * FROM matricula WHERE numero = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                // Datos de la matrícula
//                String numero = rs.getString("numero");
//                String estudiante = rs.getString("estudiante"); // Suponiendo que tienes este campo
//                String curso = rs.getString("curso");
//
//                // Crear el PDF
//                try (PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
//                     Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer))) {
//
//                    // Agregar contenido al PDF
//                    document.add(new Paragraph("Información de Matrícula"));
//                    document.add(new Paragraph("Número de Matrícula: " + numero));
//                    document.add(new Paragraph("Estudiante: " + estudiante));
//                    document.add(new Paragraph("Curso: " + curso));
//                }
//
//                System.out.println("PDF generado en: " + filePath);
//            } else {
//                throw new Exception("No se encontró la matrícula.");
//            }
        }
    }
}
