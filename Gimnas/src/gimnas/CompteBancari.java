package gimnas;

import java.math.BigInteger;

public class CompteBancari {
    private String ccc;
    
    public static final int IBANNUMBER_MIN_SIZE = 15;
    public static final int IBANNUMBER_MAX_SIZE = 34;
    public static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");

    @Override
    public String toString() {
        return "CompteBancari: " + ccc;
    }

    public String getCcc() {
        return ccc;
    }

    public CompteBancari(String ccc) {
        this.ccc = ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
    
    public boolean validarCcc(String ccc) {
        String newAccountNumber = ccc.trim();
 
        // Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid. We could also check
        // for specific length according to country, but for now we won't
        if (newAccountNumber.length() < IBANNUMBER_MIN_SIZE || newAccountNumber.length() > IBANNUMBER_MAX_SIZE) {
            return false;
        }
 
        // Move the four initial characters to the end of the string.
        newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);
 
        // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0;i < newAccountNumber.length();i++) {
            numericAccountNumber.append(Character.getNumericValue(newAccountNumber.charAt(i)));
        }
 
        // Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
        BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
        return ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;
    }
}
