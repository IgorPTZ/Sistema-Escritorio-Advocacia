package sistema.advogados.associados.util;

public class Utilitarios {
	
	public static boolean isNumerico(String texto) {
	    
		if (texto == null) {
	        return false;
	    }
	    try {
	    		
	       Long.parseLong(texto);
	       
	    } catch (NumberFormatException e) {
	        
	    	return false;
	    }
	    return true;
	}
}
