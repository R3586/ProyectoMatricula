package utilily;
import java.sql.*;

public class ConexionBD {    
    public  static Connection  getConexionBD()
   { 
       Connection cn=null;   
       try {           
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/matriculaunfv","root","");           
            System.out.println("Conexion Exitosa.");            
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Conexion Fallida.");
        }   
       return cn;       
   }    
    
    public static void main(String[] args) {
        getConexionBD();
    } 

}
