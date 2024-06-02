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

    public static int[][] generateBingoCard(){
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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
       //int[][] bingoCard = generateBingoCard();
        int[][] bingoCard = BINGO_EXAMPLE;
        checkBingoCard(bingoCard);
       while(!bingo(bingoCard)) {
           //checkBingoCard(bingoCard);
           printBingoCard(bingoCard);
           System.out.println("\nGezogene Zahl: ");
           int number = Integer.parseInt(scanner.nextLine());
           fillBingoCard(bingoCard, number);
           if (fillBingoCard(bingoCard, number)){
               System.out.println("Treffer!!");
           }else{
               System.out.println("Kein Treffer!!");
           }
           fillBingoCard(bingoCard, number);
       }
        System.out.println("BINGO! BINGO! BINGO! BINGO! BINGO! BINGO! BINGO!");
       printBingoCard(bingoCard);


    }

    public static void printBingoCard(int[][] card){
        char[] header = new char[] { 'B', 'I', 'N', 'G', 'O' };
        for(int x = 0; x < 5; x++){
            System.out.print(" "+header[x]+" ");
        }
        for(int i = 0; i < 5; i++){
            System.out.print("\n");
            for(int j = 0; j < 5; j++){
                if(card[i][j] >= 10){
                    System.out.print(card[i][j]+" ");
                }
                else if (card[i][j] == 0) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" " + card[i][j] + " ");
                }
            }
        }
    }

    public static boolean containsDuplicates(int[][] card){
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

    public static void checkBingoCard(int[][] card) {
        if (card == null) {
            throw new IllegalArgumentException("Bingo!-Karte darf nicht null sein!");
        }
        if (card.length != 5) {
            throw new IllegalArgumentException("Die Bingo!-Karte muss ein zweidimensionaler quadratischer Array der Dimension 5x5 sein!");
        }
        for (int[] rows : card) {
            if (rows == null || rows.length != 5) {
                throw new IllegalArgumentException("Jede Reihe darf nicht null sein oder eine andere länge als 5 haben!");
            }
        }
        for(int i = 0; i < 5; i++){
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
        if (card[2][2] != 0) {
            throw new IllegalArgumentException("Der Eintrag in der Mitte muss gestrichen sein!");
        }
        if (containsDuplicates(card)) {
            throw new IllegalArgumentException("Die Bingo!-Karte darf keine Duplikate enthalten!");
        }
    }

    public static boolean fillBingoCard(int[][] bingoCard, int number){
        if (count == 0){
            checkBingoCard(bingoCard);
        }
        count++;
        if(1>number || number>75){
            throw new IllegalArgumentException("Number darf nicht kleiner gleich 0 sein oder größer als 75 sein!");
            //return false;
        }
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

    public static boolean bingo(int[][] bingoCard){
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
