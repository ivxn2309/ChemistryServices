package core;

public class NameResolver {
    public static final int OXIDOS_BASICOS = 0;
    public static final int OXIDOS_ACIDOS = 1;
    public static final int HIDRUROS_METALICOS = 2;
    public static final int HIDRUROS_NO_METALICOS = 3;
    public static final int ACIDOS_HIDRACIDOS = 4;
    public static final int SAL_BINARIA = 5;
    public static final int PEROXIDOS = 6;
    
    private int type;

    public NameResolver(int type) {
        this.type = type;
    }
    
    /**
     * Notese que la cadena debe separar cada una de las partes del compupuesto por puntos ":"
     * y debe incluirse todos los datos incluyendo los implicitos, por ejemplo para representar
     * el agua se tendria que mandar la siguiente cadena "1:Hidrogeno:2:1:Oxigeno:1", esta cadena 
     * incluye los coeficientes de cada elemento, esto para que pueda ser reutilizado el mismo 
     * formato para otras operaciones.
     * @param compuesto Es una cadena que representa un compuesto 
     * @return El nombre del compuesto
     */
    public String resolveName(String compuesto) {
        String [] separated = compuesto.split(":");
        //El minimo de componentes de la cadena son seis para compuestos binarios
        if(separated.length < 6) return "Formato inválido";
        //Forzosamente son tres componentes por cada elemento 
        if(separated.length % 3 != 0) return "Formato inválido";
        
        System.out.println("String Received: " + compuesto);
        
        //Recuperacion de datos
        int sizeA = Integer.parseInt(separated[0]) * Integer.parseInt(separated[2]);
        int sizeB = Integer.parseInt(separated[3]) * Integer.parseInt(separated[5]);
        String elemA = separated[1];
        String elemB = separated[4];
        
        switch(type) {
            case OXIDOS_BASICOS: return resolveOxido(sizeB, sizeA, elemA);
            case PEROXIDOS: return resolvePeroxido(sizeB, sizeA, elemA);
            case OXIDOS_ACIDOS: return resolveOxido(sizeB, sizeA, elemA);
            case HIDRUROS_METALICOS: return resolveStockHidruro(sizeB, elemA);
            case HIDRUROS_NO_METALICOS: return resolveHidruro(sizeB, sizeA, elemA);
            case ACIDOS_HIDRACIDOS: return resolveHidracidos(elemB);
            case SAL_BINARIA: return resolveSalBinaria(sizeB, elemA, elemB);
        }
        
        return "Undefined";
    }
    
    private String resolveOxido(int oxis, int elems, String elementB) {
        String oxiSuffix = getPreffix(oxis);
        String elemSuffix = getPreffix(elems);
        String tipo = "óxido";
        if(oxiSuffix.length() < 1) tipo = "Óxido";
        if(elemSuffix.length() > 0) elementB = elementB.toLowerCase();
        return "<html>" + oxiSuffix + tipo + " de <br>" + elemSuffix + elementB + "</html>";
    }
    
    private String resolvePeroxido(int oxis, int elems, String elementB) {
        String oxiSuffix = getPreffix(oxis);
        String elemSuffix = getPreffix(elems);
        String tipo = "peróxido";
        if(oxiSuffix.length() < 1) tipo = "Peróxido";
        if(elemSuffix.length() > 0) elementB = elementB.toLowerCase();
        return "<html>" + oxiSuffix + tipo + " de <br>" + elemSuffix + elementB + "</html>";
    }
    
    private String resolveStockHidruro(int hidros, String elementB) {
        return "<html>Hidruro de <br>" + elementB + " (" + numberToRoman(hidros) + ")</html>";
    }
    
    private String resolveHidruro(int hidros, int elems, String elementB) {
        String hidroPreffix = getPreffix(hidros);
        String elemPreffix = getPreffix(elems);
        String tipo = "hidruro";
        if(hidroPreffix.length() < 1) tipo = "Hidruro";
        if(elemPreffix.length() > 0) elementB = elementB.toLowerCase();
        return "<html>" + hidroPreffix + tipo + " de <br>" + elemPreffix + elementB + "</html>";
    }
    
    private String resolveHidracidos (String elementB) {
        //La excepcion del azufre
        if(elementB.equals("Azufre"))
            return "Ácido Sulfhídrico";
        
        //Los acentos son removidos para formar la palabra compuesta
        elementB = removeTilde(elementB);
        //Para los demas casos, se busca la ultima consonante y se añade el sufijo
        int idx = elementB.length() - 1;
        for(; idx > 0; idx--) {
            boolean vowel = isVowel(elementB.charAt(idx));
            if(!vowel)
                break;
        }
        return "Ácido " + elementB.substring(0, ++idx) + "hídrico";
    }
    
    private String resolveSalBinaria (int size, String elementA, String elementB) {
        //Las excepciones
        if(elementB.equals("Azufre"))
            elementB = "Sulf";
        if(elementB.equals("Fósforo"))
            elementB = "Fosf";
        if(elementB.equals("Nitrógeno"))
            elementB = "Nitr";
        
        //Los acentos son removidos para formar la palabra compuesta
        elementB = removeTilde(elementB);
        //Para los demas casos, se busca la ultima consonante y se añade el sufijo
        int idx = elementB.length() - 1;
        for(; idx > 0; idx--) {
            boolean vowel = isVowel(elementB.charAt(idx));
            if(!vowel)
                break;
        }
        
        elementB = elementB.substring(0, ++idx);
        return elementB + "uro de " + elementA + " (" + numberToRoman(size) + ")";
    }
    
    private String removeTilde(String string) {
        string = string.replace("á", "a");
        string = string.replace("é", "e");
        string = string.replace("í", "i");
        string = string.replace("ó", "o");
        string = string.replace("ú", "u");
        
        string = string.replace("Á", "A");
        string = string.replace("É", "E");
        string = string.replace("Í", "I");
        string = string.replace("Ó", "O");
        string = string.replace("Ú", "U");
        return string;
    }
    
    private boolean isVowel (char letter) {
        if(letter == 'a' || letter == 'A') return true;
        if(letter == 'e' || letter == 'E') return true;
        if(letter == 'i' || letter == 'I') return true;
        if(letter == 'o' || letter == 'O') return true;
        if(letter == 'u' || letter == 'U') return true;
        return false;
    }
    
    private String getPreffix(int size){
        switch(size) {
            case 2: return "Di";
            case 3: return "Tri";
            case 4: return "Tetra";
            case 5: return "Penta";
            case 6: return "Hexa";
            case 7: return "Hepta";
        }
        return "";
    }
    
    private String toCapitalCase(String string) {
        char [] array = string.toCharArray();
        array[0] = string.toUpperCase().charAt(0);
        return new String(array);
    }
    
    private String numberToRoman(int num){
        switch(num){
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
            case 11: return "XI";
            case 12: return "XII";
            case 13: return "XIII";
            case 14: return "XIV";
            case 15: return "XV";
            case 16: return "XVI";
            case 17: return "XVII";
            case 18: return "XVIII";
            case 19: return "XIX";
            case 20: return "XX";
        }
        return "-";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
