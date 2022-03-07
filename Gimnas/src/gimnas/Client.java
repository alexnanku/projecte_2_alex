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
    private int telefon;
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

    public int getTelefon() {
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
            this.telefon = rs.getInt("TEL");
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
                dataCorrecta = true;
                System.out.println("Introdueïx la DATA DE NAIXAMENT EN EL FORMAT CORRECTE: DD/MM/YYYY");

                try {
                    this.datanaixement = LocalDate.parse(keyboard.next(), formatter);
                } catch (Exception e) {

                }
            } while (!dataCorrecta); */

            String nomclient;
            System.out.println("Introdueix el nom del client que vols inserir: ");
            nomclient = keyboard.next();

            String cognomclient;
            System.out.println("Introdueix el nom del client que vols inserir: ");
            cognomclient = keyboard.next();
            String cons = "INSERT INTO CLIENTS (DNI, NOM, COGNOM) VALUES ?, ?, ?";
            PreparedStatement ps = conClient.prepareStatement(cons);

            ps.setString(1, dni);
            ps.setString(2, nomclient);
            ps.setString(3, cognomclient);
            
            ResultSet rs = ps.executeQuery();
        }
    }

    private void cargarDatosDeClientEnSentencia(PreparedStatement ps) throws SQLException {

        ps.setString(1, this.dni.getDni());
        ps.setString(2, this.nom);
        ps.setString(3, this.cognom);
        ps.setString(4, this.ccc.getCcc());
    }

    private void cargarDatosDeSentenciaEnClient(ResultSet rs) throws SQLException {

        this.dni = new Dni(rs.getString("DNI"));
        this.nom = rs.getString("NOM");
        this.cognom = rs.getString("COGNOM");
        this.ccc = new CompteBancari(rs.getString("IBAN"));
        this.sexe = rs.getString("SEXE");
        this.datanaixement = rs.getDate("DATA_NAIX").toLocalDate();
        this.telefon = rs.getInt("TEL");
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

        String consulta = "SELECT * FROM CLIENTS ORDER BY DATA_NAIX ASC";

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

        String consulta = "SELECT s.NOM, s.COGNOM, count(i.DNI) FROM inscriuen i, clients s where i.DNI=s.DNI GROUP BY i.DNI;";

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
