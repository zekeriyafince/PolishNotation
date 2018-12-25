
package polishnotation;

/**
 *
 * @author Zekeriya
 */
public class ClientPolish {
    private String input;
    final PolishSingleton pSingleton ;
    public ClientPolish(String input) {
        this.input = input;
        pSingleton = new PolishSingleton(input);
        
    }
    
    public void IfadeHesapla() {
        
        
        
        if (GirdiKontrol(input) && GirdiIslemMantikDurumu(input)) {
            System.out.println("baslanildi");
            System.out.println("Sonuc:" + pSingleton.MatPrefixHesap(input));

        } else {
            System.out.println("! HATA \nYapilmak istenen Aritmetik işlemi kontrol ediniz....");
        }
    }
    
    private boolean GirdiKontrol(String input) {
        //Klavyeden girilen aritmetik işlemin 0-9 aralığında ve
        //4 işlem içermesi gerekir.
        input += " ";
        if (input.contains("-") || input.contains("+") || input.contains("/") || input.contains("*")) {
            if (input.charAt(0) == '-' || input.charAt(0) == '+' || input.charAt(0) == '/' || input.charAt(0) == '*') {
                input = input.replace(" ", "");
                if (input.matches(".*[0-9]*")) //[0-9] //karakter
                {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean GirdiIslemMantikDurumu(String input) {
        //Aritmetik işlem yapabilme icin 
        //(operand(işlem) sayisi+1 ) == opcode(sayilar) Sayisi
        
        int operandSayisi = 0;
        int opcodeSayisi = 0;
        String[] diziInput = input.split(" ");
        for (String harf : diziInput) {
            if (harf.equals("*") || harf.equals("/") || harf.equals("+") || harf.equals("-")) {
                operandSayisi++;
            } else {
                opcodeSayisi++;
            }

        }
        if (operandSayisi + 1 == opcodeSayisi) {
            return true;
        }
        return false;
    }
}
