import java.sql.SQLOutput;
import java.util.Scanner;

public class Caesar{

  public final static String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public final static String EXAMPLE_KEY = "HAMNOGPQFEISTLJVWDXKCYZRBU";

  public static String applyCaesar(String text, String key, String clear){
    int n = text.length();
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < n; i++){
      for (int j = 0; j < clear.length(); j++){
        if (text.charAt(i) == key.charAt(j)){
          result.append(clear.charAt(j));
        }
      }
      if(!key.contains(String.valueOf(text.charAt(i)))){
        result.append(text.charAt(i));
      }
    }
    return result.toString();
  }

  public static void printStatistics(String text){
    int l = text.length();
    int numLetters = 0;
    int[] count = new int[26];
    for(int i = 0; i < l; i++){
      if(ABC.contains(String.valueOf(text.charAt(i)))){
        numLetters++;
        count[ABC.indexOf(text.charAt(i))]++;
      }
    }
    for(int i = 0; i < 26; i++){
      double percentage = count[i]/(double)numLetters*100;
      System.out.println(ABC.charAt(i)+": "+percentage+"%");
    }


  }

  public static void main(String[] args){
    String message = "PROGRAMMIEREN IN JAVA MACHT NOCH MEHR SPASS ALS IN PYTHON ODER C";

    String encrypted = applyCaesar(message, EXAMPLE_KEY, ABC);
    String decrypted = applyCaesar(encrypted, ABC, EXAMPLE_KEY);

    System.out.printf("Message: %s%n", message);
    System.out.printf("Encrypted: %s%n", encrypted);
    System.out.printf("Decrypted: %s%n", decrypted);

    printStatistics(encrypted);
  }

  public final static String EXAM_HINTS_ENCRYPTED = "WJ EG ULT SPDWFWT RW VLFKLNLG JÜFFLG FEL GWT HAPBLGUL NEGZLEFL VLDQNKLG:\r\n"+
" - UEL SPDWFWT ATELGKELTK FEQN DG ULG CTDSKESD!\r\n"+
" - VLDTVLEKLG FEL DPPL CTDSKESWJFDWHBDVLG FLPVFKFKÄGUEB. GWT FA OLTFKLNLG FEL UEL PÖFWGBLG WGU SÖGGLG DWQN EG ULT SPDWFWT FLPVFKFKÄGUEB DWH UEL PÖFWGB SAJJLG.\r\n"+
" - FLNLG FEL ULG PLEFKWGBFGDQNZLEF DPF LEGL BLPLBLGNLEK LEGL TÜQSJLPUWGB RW ENTLJ ZEFFLGFFKDGU RW VLSA JJLG. ZLGG FEL LEGJDP LEG CTDSKESWJ GEQNK VLFKLNLG, UDGG GWKRLG FEL UEL BLPLBLGNLEK WJ FEQN KELHLT EGUEL JDKLTEL LEGRWDTVLEKLG.\r\n"+
" - FLNLG FEL FEQN DPKL SPDWFWTLG DG WGU TLQNGLG FEL UELFL UWTQN. UEL SPDWFWTLG EG CTABTDJJELTLG 2 FEGU EJJLT ÄNGPEQN DWHBLVDWK. FEL UÜTHLG FEQN FABDT UEL DPKLG SPDWFWTLG JEK ENTLG PÖFWGBLG JEK EG UEL CTÜH WGB GLNJLG.\r\n"+
" - CTAVELTLG FEL UEL VLEFCELPL DWF ULT OATPLFWGB FLPVLT DWF, OLTÄGULTG FEL FEL, JDQNLG FEL FEL SDCWKK WGU TLCDTELTLG FEL FEL. FA VLSAJJLG FEL LEG BLHÜNP HÜT UEL CTABTDJJELTFCTDQNL.\r\n"+
"- ZLGG UDF DPPLF GEQNKF NEPHK, UDGG HEGULG FEL NELT UEL DWHBDVLG ULT GÄQNFKLG CTABTDJJJELTLG 2 SPDWFWT FDJK PÖFWGBLG: NKKCF://VEK.PY/CTAB2-SPDWFWT-FAFL24";







}
