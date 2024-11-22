package prueba2;
public class MatriculaApp {
    public static void main(String[] args) {
        MatriculaModelo modelo = new MatriculaModelo();
        MatriculaVista vista = new MatriculaVista();
        new MatriculaControlador(modelo, vista);

        vista.setVisible(true);
    }
}
