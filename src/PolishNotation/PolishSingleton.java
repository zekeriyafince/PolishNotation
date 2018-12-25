
package polishnotation;

import java.util.Stack;

/**
 *
 * @author Zekeriya
 */
public class PolishSingleton {
    protected String inputDeger ;

    public PolishSingleton() {
    }
    
    
    public PolishSingleton(String inputDeger) {
        this.inputDeger = inputDeger;
    }
    
    
    static boolean OperandMi(String harf) {
        //İşlem gerektiren durumu bulmak için kontrol edilirken kullan
        if (harf.equals("*") || harf.equals("/") || harf.equals("+") || harf.equals("-")) {
            return true;
        }
        return false;

    }

    static String InputGuncelle(String input, int result, String oldDeger) {
        //Bir artimetik işlem yapıldığında sonuc yazılarak guncelleniyor
        input = input.replace(oldDeger, Integer.toString(result));
        return input;
    }

    

    static int MatPrefixHesap(String input) {
        input += " ";
        String strGeciciInput = input;
        //geciciInput = input.replace(" ", "");
        Stack stackYapisi = new Stack();
        String opcodeTemp = "";
        int result = 0;
        int geciciInputIndis = 0;
        int sonrakiBosluk = 0; // ilk bosluk 0 indisle baslar
        String singleTekKarakter = ""; //2 bosluk arasındaki deger atamak icin
        String islemeKatilan = "";
        String[] katarIslem = new String[3]; //2 opcode ve operand icin
        int indexKatarIslem = 0;
        
        while (geciciInputIndis < strGeciciInput.length()) {
            //alttaki 2 satir ile 2 bosluk arası degeri alma
            sonrakiBosluk = strGeciciInput.indexOf(" ", sonrakiBosluk + 1);
            singleTekKarakter = strGeciciInput.substring(geciciInputIndis, sonrakiBosluk);

            if (OperandMi(singleTekKarakter)) {
                //Karakter operand ise direkt stack'e at islem yapma
                stackYapisi.push(singleTekKarakter);
            } else { 
                //opcode gelmesi durumunda
                stackYapisi.push(singleTekKarakter);

                //Opcode sonraki karakter'e bakılacak ve geciciTekTekKarakter atanacak
                String tempSingleTekKarakter = "";
                int geciciBosluk = strGeciciInput.indexOf(" ", sonrakiBosluk + 1);

                if (geciciBosluk != -1) {
                    tempSingleTekKarakter = strGeciciInput.substring(sonrakiBosluk + 1, geciciBosluk);
                }

                //geciciInput un sonunda geciciBosluk=-1 olacak
                //geciciTekTekKarakter opcode ise çalısacak
                //İlk opcode oldugu icin opcodeTemp null olacak
                if (geciciBosluk == -1 || !OperandMi(tempSingleTekKarakter) || !opcodeTemp.isEmpty()) {
                    opcodeTemp = singleTekKarakter;
                    katarIslem[indexKatarIslem] = singleTekKarakter;

                    if (!katarIslem[0].isEmpty() && indexKatarIslem == 1) { //2 tane opcode varsa(1 2)
                        int number1, number2;
                        String operand;
                        number1 = Integer.parseInt((String) stackYapisi.pop()); //2
                        number2 = Integer.parseInt((String) stackYapisi.pop()); //1
                        operand = (String) stackYapisi.pop(); //+
                        islemeKatilan = operand + " " + String.valueOf(number2) + " " + String.valueOf(number1);
                        if (operand.equals("+")) {
                            result = number1 + number2; //3

                        } else if (operand.equals("-")) {

                            result = number1 - number2;

                        } else if (operand.equals("/")) {

                            result = number1 / number2;

                        } else if (operand.equals("*")) {

                            result = number1 * number2;

                        }
                        //artimetik islem oldu ve geciciInput guncellendi
                        strGeciciInput = InputGuncelle(strGeciciInput, result, islemeKatilan);
                        System.out.println("Guncellendi : " + strGeciciInput);
                        geciciInputIndis = -1;
                        opcodeTemp = "";
                        islemeKatilan = "";
                        sonrakiBosluk = 0;
                        singleTekKarakter = "";
                        stackYapisi.removeAllElements();
                        indexKatarIslem = -1;
                    }
                    indexKatarIslem++;
                }

            }

            if (geciciInputIndis != -1) {
                //geciciInputIndis'i degerdeki karakterin uzunluğu +1 kadar ilerle
                //yani bosluku da gecerek diger karaktere ulas
                //Birden fazla basamak degeri icin tekTekKarakter.length() kullanildi
                geciciInputIndis += singleTekKarakter.length() + 1;
            } else {
                geciciInputIndis++;
            }
        }
        return result;
    }
}
