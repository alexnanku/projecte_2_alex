package gimnas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Activitat {
    
    private String dni_ins;
    private int num_act;

    @Override
    public String toString() {
        return "DNI: " + dni_ins + "\nTotal de reserves: " + num_act + "\n";
    }

    public String getDni_ins() {
        return dni_ins;
    }

    public void setDni_ins(String dni_ins) {
        this.dni_ins = dni_ins;
    }

    public int getNum_act() {
        return num_act;
    }

    public void setNum_act(int num_act) {
        this.num_act = num_act;
    }
    
    public ArrayList<Activitat> getActivitatsPerReserves() throws SQLException {
        ArrayList<Activitat> activitats = new ArrayList<>();
        Connexio con = new Connexio();
        Connection connexio = con.getCon();
 
        String consulta = "SELECT a.*, i.DNI as dni, count(a.NUM_ACTIVITAT) as total FROM ACTIVITATS a, inscriuen i WHERE a.NUM_ACTIVITAT=i.NUM_ACTIVITAT and DIA = CURDATE() group by i.DNI order by i.NUM_ACTIVITAT;";
        
        PreparedStatement ps = connexio.prepareStatement(consulta);
        
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Activitat a1 = new Activitat();
            a1.afegirDadesAct(rs);
            activitats.add(a1);
        }
        return activitats;
    }
    
    private Activitat afegirDadesAct (ResultSet rs) throws SQLException {
        this.dni_ins = rs.getString("dni");
        this.num_act = rs.getInt("total");
        
        return this;
    }
}