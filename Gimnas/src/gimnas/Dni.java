
package gimnas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dni {
    private String dni;
    private int num;
    private char c;

    @Override
    public String toString() {
        return "Dni: " + dni;
    }

    public Dni(String dni) {
        this.dni = dni;
    }

    public Dni(){
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean validarDni (String dni){
        
        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

        Matcher mat = patron.matcher(dni);
        
        if(!mat.matches()){
           System.out.println("El DNI introduït es incorrecte, introdueïx un DNI vàl·lid.");
           System.out.println();
           mat = patron.matcher(dni);
        } else{
            System.out.println("El DNI " + dni + " és vàl·lid.");
        }
        return true;
    }
}
