package gimnas;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GimnasApp {
    
    public static void main(String[] args) throws SQLException {
        
       Gimnas g = new Gimnas(null, null, 0);
       Connexio con = new Connexio();
       con.getCon();
       
       g.gestionarGimnas();
    }
}
