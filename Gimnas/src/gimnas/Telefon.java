package gimnas;

import java.util.regex.Pattern;

public class Telefon {
    private int tel;
    
    public Telefon(){
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Telefon(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Telefon: " + tel;
    }
    public boolean validarTel(String tel){
        Pattern pattern = Pattern.compile("^(\\+34|0034|34)?[67]\\d{8}$");
        return pattern.matcher(tel).matches();
    }
}