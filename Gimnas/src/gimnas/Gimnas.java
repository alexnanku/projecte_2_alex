package gimnas;
import com.sun.jdi.connect.spi.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gimnas {
    
    private String nom;
    private String CIF;
    private int telefon;

    boolean exit;
    boolean exit2;
    Scanner keyboard = new Scanner(System.in);
    
    private Client client = new Client();
    private Activitat activitat = new Activitat(); 

    ArrayList<Client> clients;
    ArrayList<Activitat> activitats;
    
    public Gimnas(){
        this.clients = new ArrayList();
        this.activitats = new ArrayList();
    }

    public void gestionarGimnas() throws SQLException {
    
        do {
            System.out.println("GESTOR D'INVENTARI");
            System.out.println("1. Gestió client");
            System.out.println("2. Visaualitzar clients per criteri");
            System.out.println("3. Visualitzar activitats del dia");
            System.out.println("4. Sortir");
            System.out.println("\nTria una opcio");

            // Declarem que el int opció es pugui introduir per consola mitjançant la funció
            // nextInt.
            int opcio = keyboard.nextInt();

            switch (opcio) {
                case 1:
                    do {
                        System.out.println("Gestió Client");
                        System.out.println("1. Consulta Client");
                        System.out.println("2. Alta client");
                        System.out.println("3. Mostrar el DNI de tots els clients");
                        System.out.println("4. Enrere");
                        System.out.println("\nTria una opcio");

                        int opcio2 = keyboard.nextInt();

                        switch (opcio2) {
                            case 1:
                                client.consultaClient();
                                break;
                            case 2:
                                client.altaClient();
                                break;
                            case 3:
                                client.mostrarClients();
                                break;
                            case 4:
                                exit2 = true;
                                break;
                            default:
                                System.out.println("La opció sel·leccionada no és vàl·lida");
                        }
                    } while (!exit2);
                    break;
                case 2:
                    do {
                        
                        System.out.println("1. Visualitzar clients per cognom");
                        System.out.println("2. Visualitzar clients per edat");
                        System.out.println("3. Visualitzar clients per reserves");
                        System.out.println("4. Enrere");
                        System.out.println("\nTria una opcio");

                        int opcio2 = keyboard.nextInt();

                        switch (opcio2) {
                            case 1:
                                this.clients = client.getClientsOrdenatsCognom();
                                visualitzarClients();
                                break;
                            case 2:
                                this.clients = client.getClientsOrdenatsEdat();
                                visualitzarClients();
                                break;
                            case 3:
                                this.clients = client.getClientsOrdenatsReserves();
                                visualitzarClients();
                                break;
                            case 4:
                                exit2 = true;
                                break;
                            default:
                                System.out.println("La opció sel·leccionada no és vàl·lida");
                        }
                    } while (!exit2);
                    break;
                case 3:
                    activitat.visualitzarActivitats();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("La opció sel·leccionada no és vàl·lida");
            }

            System.out.println("\nOpció: " + opcio);

        } while (!exit);
    }

    public Gimnas(String nom, String cIF, int telefon) {
        this.nom = nom;
        CIF = cIF;
        this.telefon = telefon;
    }
    
    private void visualitzarClients() {
        for(Client cli: this.clients){
            System.out.println(cli);
        }
    }
}