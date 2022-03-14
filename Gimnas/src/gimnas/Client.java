package gimnas;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private Dni dni;
    private String nom;
    private String cognom;
    private String sexe;
    private CompteBancari ccc;
    private LocalDate datanaixement;
    private Telefon telefon;
    private String email;
    private String usuari;
    private String contrasenya;

    @Override
    public String toString() {
        return "Client " + dni + "\nNom: " + nom + "\nCognom: " + cognom + "\nSexe: " + sexe + "\nIBAN: " + ccc + "\nData de naixement: " + datanaixement + "\nTelefon: " + telefon + "\nEmail: " + email + "\nUsuari: " + usuari + "\nContrasenya: " + contrasenya + "\n";
    }

    public void mostrarClients() throws SQLException {
        Connexio con = new Connexio();
        Connection connexio = con.getCon();

        String consulta = "SELECT * FROM CLIENTS";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("DNI"));
            System.out.println();
        }
    }

    public void consultaClient() throws SQLException {

        Scanner teclat = new Scanner(System.in);

        String dni;
        Dni dniObj = new Dni();

        do {
            System.out.println("Introdueix el DNI de la persona que vols consultar.");
            dni = teclat.next();
        } while (!dniObj.validarDni(dni));

        Client client = consultaClientBD(dni);

        if (client != null) {
            System.out.println();
            System.out.print(client);
        }
    }

    public Dni getDni() {
        return dni;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public CompteBancari getCcc() {
        return ccc;
    }

    public LocalDate getDatanaixement() {
        return datanaixement;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public Client consultaClientBD(String dni) throws SQLException {

        Connexio conGim = new Connexio();
        Connection conClient = conGim.getCon();
        String consulta = "SELECT * FROM CLIENTS WHERE DNI=?";

        PreparedStatement sentence = conClient.prepareStatement(consulta);

        sentence.setString(1, dni);

        ResultSet rs = sentence.executeQuery();

        if (rs.next()) {

            this.dni = new Dni(rs.getString("DNI"));
            this.nom = rs.getString("NOM");
            this.cognom = rs.getString("COGNOM");
            this.ccc = new CompteBancari(rs.getString("IBAN"));
            this.sexe = rs.getString("SEXE");
            this.datanaixement = rs.getDate("DATA_NAIX").toLocalDate();
            this.telefon = new Telefon(rs.getInt("TEL"));
            this.email = rs.getString("EMAIL");
            this.usuari = rs.getString("USUARI");
            this.contrasenya = rs.getString("PASS");

            return this;
        }
        return null;
    }

    public void altaClient() throws SQLException {

        Connexio conGim = new Connexio();
        Connection conClient = conGim.getCon();

        Scanner keyboard = new Scanner(System.in);
        
        String cons = "INSERT INTO CLIENTS (DNI, NOM, COGNOM, USUARI, PASS, IBAN, SEXE, DATA_NAIX, TEL, EMAIL, PUBLICITAT, IMPEDIMENT_FISIC) VALUES (?, ?, ?, ?, MD5(?), ?, ?, ?, ?, ?, ?, ?)";

        String dni;

        Dni dniObj = new Dni();

        do {
            System.out.println("Introdueix el DNI del client que vols donar d'alta.");
            dni = keyboard.next();
        } while (!dniObj.validarDni(dni));

        dniObj.setDni(dni);

        setDni(dniObj);

        if (consultaClientBD(dniObj.getDni()) != null) {
            System.out.println("El DNI ja existeix a la base de dades.");
        } else {

            /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            boolean dataCorrecta;

            do {
                
                System.out.println("Introdueïx la DATA DE NAIXAMENT EN EL FORMAT CORRECTE: DD/MM/YYYY");

                try {
                    this.datanaixement = LocalDate.parse(keyboard.next(), formatter);
                    dataCorrecta = true;
                } catch (Exception e) {
                }
            } while (!dataCorrecta); */
            
            
            String nomClient;
            do{
                System.out.println("Introdueix el nom del client.");
                nomClient = keyboard.next();
            } while (nomClient.length() ==0);
            
            System.out.println("Introdueix el cognom del client.");
            String cognomClient = keyboard.next();
            
            System.out.println("Introdueix el nom d'usuari.");
            String usr = keyboard.next();
           
            System.out.println("Introdueix la contrasenya del usuari.");
            String pas = keyboard.next();
            
            CompteBancari ib = new CompteBancari();
            String iban;
            
            do{
                System.out.println("Introdueix el IBAN del client que vols donar d'alta.");
                iban = keyboard.next();
            } while (!ib.validarCcc(iban));
            
            System.out.println("Introdueix el sexe del client.");
            String sx = keyboard.next();
            
            System.out.println("Introdueix la data de naixement (YYYY-MM-DD).");
            String dt = keyboard.next();
            
            Telefon te = new Telefon();
            String tel;
            do{
                System.out.println("Introdueix el telèfon.");
                tel = keyboard.next();
            } while (!te.validarTel(tel));
            
            Email em = new Email();
            String email;
            
            do{
                System.out.println("Introdueix el email del client.");
                email = keyboard.next();
            } while (!em.validarEmail(email));
            
            System.out.println("Introdueix 0 o 1 si vol rebre publicitat. 1 afirmatiu, 0 negatiu.");
            int publi = keyboard.nextInt();
            
            System.out.println("Introdueix 0 o 1 si el client té un impediment físic. 1 afirmatiu, 0 negatiu.");
            int impe = keyboard.nextInt();
            
            PreparedStatement ps = null;

            try{
                ps = conClient.prepareStatement(cons);
                ps.setString(1, dni);
                ps.setString(2, nomClient);
                ps.setString(3, cognomClient);
                ps.setString(4, usr);
                ps.setString(5, pas);
                ps.setString(6, iban);
                ps.setString(7, sx);
                ps.setString(8, dt);
                ps.setString(9, tel);
                ps.setString(10, email);
                ps.setInt(11, publi);
                ps.setInt(12, impe);
                ps.executeUpdate();
                System.out.println("El usuari " + nomClient + " s'ha creat correctament.");
                
            } catch (SQLException e){
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private void cargarDatosDeClientEnSentencia(PreparedStatement ps) throws SQLException {

        ps.setString(1, this.dni.getDni());
        ps.setString(2, this.nom);
        ps.setString(3, this.cognom);
        ps.setString(4, this.ccc.getCcc());
    }
    
    public void baixaClient() throws SQLException{
        
        Connexio con = new Connexio();
        Connection connexio = con.getCon();
        
        Scanner keyboard = new Scanner(System.in);
        
        String dni2;
        Dni dniObj = new Dni();
        do{
            System.out.println("Indica el DNI del client que vols donar de baixa");
            dni2 = keyboard.next();
        } while(!dniObj.validarDni(dni2));
        
        Statement stmt = connexio.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");

        String del = "DELETE FROM CLIENTS WHERE DNI = ?";
        PreparedStatement delete = connexio.prepareStatement(del);

        delete.setString(1, dni2);
        delete.executeUpdate();
    }
    
    private void cargarDatosDeSentenciaEnClient(ResultSet rs) throws SQLException {

        this.dni = new Dni(rs.getString("DNI"));
        this.nom = rs.getString("NOM");
        this.cognom = rs.getString("COGNOM");
        this.ccc = new CompteBancari(rs.getString("IBAN"));
        this.sexe = rs.getString("SEXE");
        this.datanaixement = rs.getDate("DATA_NAIX").toLocalDate();
        this.telefon = new Telefon(rs.getInt("TEL"));
        this.email = rs.getString("EMAIL");
        this.usuari = rs.getString("USUARI");
        this.contrasenya = rs.getString("PASS");
    }

    public ArrayList<Client> getClientsOrdenatsCognom() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        Connexio con = new Connexio();
        Connection connex = con.getCon();

        String consulta = "SELECT * FROM CLIENTS ORDER BY COGNOM, NOM";

        PreparedStatement ps = connex.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.cargarDatosDeSentenciaEnClient(rs);
            clients.add(c1);
        }
        return clients;
    }

    public ArrayList<Client> getClientsOrdenatsEdat() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        Connexio con = new Connexio();
        Connection connex = con.getCon();

        String consulta = "SELECT * FROM CLIENTS ORDER BY DATA_NAIX DESC";

        PreparedStatement ps = connex.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.cargarDatosDeSentenciaEnClient(rs);
            clients.add(c1);
        }
        return clients;
    }

    public ArrayList<Client> getClientsOrdenatsReserves() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        Connexio con = new Connexio();
        Connection connex = con.getCon();

        String consulta = "SELECT s.* FROM inscriuen i, clients s where i.DNI=s.DNI GROUP BY i.DNI ORDER BY COUNT(i.DNI) DESC;";

        PreparedStatement ps = connex.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.cargarDatosDeSentenciaEnClient(rs);
            clients.add(c1);
        }
        return clients;
    }
}
