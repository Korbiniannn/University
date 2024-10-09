import java.util.Random;
import java.util.Scanner;

public class Bingo {
    static int count = 0;
    final static int[][] BINGO_EXAMPLE = new int[][]
            {
                    { 4, 27, 32, 55, 73 },
                    { 15, 25, 41, 58, 75},
                    { 8, 26, 0, 59, 70 },
                    { 7, 22, 33, 54, 63 },
                    { 13, 17, 43, 48, 67}
            };

    public static int[][] generateBingoCard(){ // Zusatzaufgabe um eine zufällige Bingo-Karte zu erstellen
        Random randint = new Random();
        int[][] bingoCard = new int[5][5];
        int min_wiederholung = 0;
        while(containsDuplicates(bingoCard) || min_wiederholung < 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == 2 && j == 2) {
                        continue;
                    }
                    if (j == 0) {
                        bingoCard[i][j] = randint.nextInt(1, 15);
                    }
                    if (j == 1) {
                        bingoCard[i][j] = randint.nextInt(16, 30);
                    }
                    if (j == 2) {
                        bingoCard[i][j] = randint.nextInt(31, 45);
                    }
                    if (j == 3) {
                        bingoCard[i][j] = randint.nextInt(46, 60);
                    }
                    if (j == 4) {
                        bingoCard[i][j] = randint.nextInt(61, 75);
                    }
                }
            }
            min_wiederholung++;
        }
        return bingoCard;
    }

    public static void main(String[] args){ // Main-Methode um alles zu kombinieren
        Scanner scanner = new Scanner(System.in); // Scanner für den User input
       //int[][] bingoCard = generateBingoCard();
        int[][] bingoCard = BINGO_EXAMPLE; // lokale Variable bingoCard initialisiert mit BINGO_EXAMPLE
        checkBingoCard(bingoCard); // prüft die bingoCard
       while(!bingo(bingoCard)) { // Schleife wiederholt sich, solange bis ein Bingo kommt
           //checkBingoCard(bingoCard);
           printBingoCard(bingoCard);
           System.out.println("\nGezogene Zahl: "); // Frägt den User nach der gezogenen Zahl
           int number = Integer.parseInt(scanner.nextLine()); // User input
           if(1>number || number>75){// prüft ob, die eingegebene Zahl gültig ist
               throw new IllegalArgumentException("Number darf nicht kleiner gleich 0 sein oder größer als 75 sein!");
           }
           if (fillBingoCard(bingoCard, number)){
               System.out.println("Treffer!!");
           }else{
               System.out.println("Kein Treffer!!");
           }
           fillBingoCard(bingoCard, number); // streicht die nummer, falls treffer
       }
        System.out.println("BINGO! BINGO! BINGO! BINGO! BINGO! BINGO! BINGO!");
       printBingoCard(bingoCard);


    }

    public static void printBingoCard(int[][] card){ // printed die Bingo Karte in einem bestimmten format
        char[] header = new char[] { 'B', 'I', 'N', 'G', 'O' }; // Header
        for(int x = 0; x < 5; x++){
            System.out.print(" "+header[x]+" "); // printed alle Chars aus header mit bestimmtem Abstand
        }
        for(int i = 0; i < 5; i++){ // printed alle Integer aus der Bingo Karte
            System.out.print("\n");
            for(int j = 0; j < 5; j++){
                if(card[i][j] >= 10){ // filtert alle zweistelligen Zahlen heraus, um diese mit passendem Abstand anzugeben
                    System.out.print(card[i][j]+" ");
                }
                else if (card[i][j] == 0) { // wenn eine 0 im Array steht, soll dort eine leere fläche sein (gestrichen)
                    System.out.print("   ");
                }
                else {// filtert alle einstelligen Zahlen heraus, um diese mit passendem Abstand anzugeben
                    System.out.print(" " + card[i][j] + " ");
                }
            }
        }
    }

    public static boolean containsDuplicates(int[][] card){ // läuft für jede Zahl das ganze Array durch, um nach Duplikaten zu suchen
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                int current = card[i][j];

                for(int k = 0; k < 5; k++){
                    for(int l = 0; l < 5; l++){
                        if ((i != k || j != l) && (current == card[k][l]) && (card[k][l] != 0)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void checkBingoCard(int[][] card) { // prüft nach allen Kriterien, ob eine Bingo Karte gültig ist
        if (card == null) { //prüft, ob die Karte gleich null ist
            throw new IllegalArgumentException("Bingo!-Karte darf nicht null sein!");
        }
        if (card.length != 5) { // prüft, ob die Karte eine andere Reihenanzahl als 5 hat
            throw new IllegalArgumentException("Die Bingo!-Karte muss ein zweidimensionaler quadratischer Array der Dimension 5x5 sein!");
        }
        for (int[] rows : card) { // prüft, ob eine der Reihen mehr oder weniger als 5 Spalten hat
            if (rows == null || rows.length != 5) {
                throw new IllegalArgumentException("Jede Reihe darf nicht null sein oder eine andere länge als 5 haben!");
            }
        }
        for(int i = 0; i < 5; i++){ //prüft die zulässigen Integer für die Spalten
            if (card[i][0]< 1 || card[i][0] > 15) {
                throw new IllegalArgumentException("Die 1.Spalte darf nur Zahlen zwischen 1 und 15 (inklusive) enthalten");
            }
            if (card[i][1] < 16 || card[i][1] > 30) {
                throw new IllegalArgumentException("Die 2.Spalte darf nur Zahlen zwischen 16 und 30 (inklusive) enthalten");
            }
            if(i != 2) {
                if (card[i][2] < 31 || card[i][2] > 45) {
                    throw new IllegalArgumentException("Die 3.Spalte darf nur Zahlen zwischen 31 und 45 (inklusive) enthalten");
                }
            }
            if (card[i][3] < 46 || card[i][3] > 60) {
                throw new IllegalArgumentException("Die 4.Spalte darf nur Zahlen zwischen 46 und 60 (inklusive) enthalten");
            }
            if (card[i][4] < 61 || card[i][4] > 75) {
                throw new IllegalArgumentException("Die 5.Spalte darf nur Zahlen zwischen 61 und 75 (inklusive) enthalten");
            }
        }
        if (card[2][2] != 0) { // prüt, ob die mitte gleich 0 ist
            throw new IllegalArgumentException("Der Eintrag in der Mitte muss gestrichen sein!");
        }
        if (containsDuplicates(card)) { // prüft nach Duplikaten
            throw new IllegalArgumentException("Die Bingo!-Karte darf keine Duplikate enthalten!");
        }
    }

    public static boolean fillBingoCard(int[][] bingoCard, int number){ // streicht die Nummern auf der Karte, falls richtig
        if (count == 0){ // Gültigkeit der Karte wird einmal am Anfang geprüft
            checkBingoCard(bingoCard);
        }
        count++;
        if(1>number || number>75){ // Gültigkeit der gezogenen Nummer wird geprüft
            throw new IllegalArgumentException("Number darf nicht kleiner gleich 0 sein oder größer als 75 sein!");
            //return false;
        }
        // streicht die Nummer auf der Karte, falls vorhanden
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(number == bingoCard[i][j]){
                    bingoCard[i][j] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bingo(int[][] bingoCard){ // prüft, ob es einen Bingo gibt
        for(int[] row : bingoCard){ // Prüft die Reihen nach einem Bingo
            int row_value = 0;
            for(int value : row){
                if (value >0){
                    row_value++;
                }
            }
            if(row_value == 0){
                return true;
            }
        }
        for(int i = 0; i < 5; i++){ // Prüft die Spalten nach einem Bingo
            int column_value = 0;
            for(int j = 0; j < 5; j++){
                if (bingoCard[j][i] > 0) {
                    column_value++;
                }
            }
            if(column_value == 0){
                return true;
            }
        }
        if((bingoCard[0][0] + bingoCard[1][1] + bingoCard[2][2] + bingoCard[3][3] + bingoCard[4][4]) == 0){ // Prüft quer von links oben nach rechts unten nach einem Bingo
            return true;
        }
        if((bingoCard[0][4] + bingoCard[1][3] + bingoCard[2][2] + bingoCard[3][1] + bingoCard[4][0]) == 0){ // Prüft quer von rechts oben nach links unten nach einem Bingo
            return true;
        }
        return false;
    }
}
