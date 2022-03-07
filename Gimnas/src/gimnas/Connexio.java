package gimnas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {
    
    private final String server = "jdbc:mysql://192.168.1.200/";
    private final String password = "Dam1";
    private final String user = "projecte";
    private final String bd = "gim";
    private Connection con;

    public Connexio() {

        try {
            con = DriverManager.getConnection(server + bd, user, password);
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Hi ha hagut un error inesperat.");
        }
    }

    public void Desconexio(){
        try {
            con.close();
            System.out.println("Desconnexió realitzada correctament.");
        } catch (SQLException e) {
        }
    }

    public Connection getCon() {
        return con;
    }
}