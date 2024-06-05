import java.sql.SQLOutput;
import java.util.Scanner;

public class Caesar{

  public final static String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public final static String EXAMPLE_KEY = "HAMNOGPQFEISTLJVWDXKCYZRBU";

  public static String applyCaesar(String text, String key, String clear){ //
    StringBuilder result = new StringBuilder();// StringBuilder um die einzelnen Strings aneinander zu ketten
    for(int i = 0; i < text.length(); i++){ // Loop um jeden Character im String text durchzugehen
      for (int j = 0; j < key.length(); j++){ // Loop betrachtet jedes zeichen im String key für jedes Zeichen im String text
        if (text.charAt(i) == key.charAt(j)){ //prüft ob, da Zeichen im String text am Index i gleich dem Zeichen im String key am Index j ist
          result.append(clear.charAt(j)); // Falls ja, dann wird das Zeichen im String clear am Index j zum Ergebnis hinzugefügt
        }
      }
      if(!key.contains(String.valueOf(text.charAt(i)))){ // Falls String key das Zeichen aus String text nicht enthält
        result.append(text.charAt(i)); // soll einfach das Zeichen aus String text zum Ergebnis hinzufügt werden
      }
    }
    return result.toString(); // ruft methode toString auf dem Ergebnis auf
  }

  public static void printStatistics(String text){ // Methode um die Wahrscheinlichkeiten jedes Zeichens wiederzugeben
    int numLetters = 0; // Zahl aller Zeichen im String text zusammen
    int[] count = new int[26]; // Counter für die einzelnen Zeichen
    for(int i = 0; i < text.length(); i++){ // loop läuft jedes Zeichen im String text durch
      if(ABC.contains(String.valueOf(text.charAt(i)))){ // If-Statement um Sonderzeichen und Leerzeichen zu filtern
        numLetters++; // erhöht die Gesamtzahl aller Zeichen
        count[ABC.indexOf(text.charAt(i))]++; // Erhöht den Wert um 1 an der Stelle vom Zeichen im Alphabet
      }
    }
    for(int i = 0; i < 26; i++){ // läuft jede Stelle des Arrays count durch
      double percentage = count[i]/(double)numLetters*100; // Erstellt den Prozentwert für das Zeichen an Stelle i
      System.out.printf("%c: %.2f%%%n",ABC.charAt(i),percentage); // Printed das Zeichen mit der passenden prozentzahl
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
