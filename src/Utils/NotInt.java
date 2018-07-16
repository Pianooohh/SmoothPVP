package Utils;

public class NotInt {
	
	public boolean isInt(String s) {
        try {
        	Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
