package gimnas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Activitat {
    
    private String nom_act;

    public String getNom_act() {
        return nom_act;
    }

    public void setNom_act(String nom_act) {
        this.nom_act = nom_act;
    }

    @Override
    public String toString() {
        return "Activitats: " + nom_act + "\n";
    }

    Activitat visualitzarActivitats() throws SQLException {
        Connexio con = new Connexio();
        Connection connexio = con.getCon();

        String consulta = "SELECT * FROM ACTIVITATS ";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("NOM_ACT"));
            return this;
        }
        return null;
    }
}
