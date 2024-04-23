package org.example;
import java.util.Random;
import java.util.Scanner;

public class Einmaleins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viele Aufgabe möchten Sie rechnen? (keine negativen Zahlen)");
        int x = Integer.parseInt(scanner.nextLine());
        int count = x; // count um zu überprüfen wie viele Runden man richtig geantwortet hat
        while(x <= 0){
            System.out.println("Please enter a positive number!");
            x = Integer.parseInt(scanner.nextLine());
        }
        for(int i = 0; i < x; i++) { // Wiederholt sich x-mal, erzeugt immer wieder zufällige Aufgaben
            Random random = new Random();
            int randint = random.nextInt(10)+1;
            int randint2 = random.nextInt(10)+1;
            System.out.println("Was ist "+randint+" * "+randint2+"?");
            int y = Integer.parseInt(scanner.nextLine()); // Eingabe der Antwort
            if(y == randint * randint2) { // Prüfung der Richtigkeit
                System.out.println("Richtig!");
            }
            else{
                --count;
                System.out.println("Leider nicht richtig!");

            }
        }
        int percentage = count * 100 / x; // zur berechnung wie viel Prozent man richtig gelöst hat
        // if-Statements für verschiedene Ausgaben bei unterschiedlicher Erfolgsquote
        if (percentage >99) {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nFabelhaft!");
        }
        else if (percentage >90) {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nAusgezeichnet!");
        }
        else if (percentage >75) {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nSehr gut!");
        }
        else if (percentage >50) {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nMehr als die Hälfte richtig, weiter so");
        }
        else if (percentage >25) {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nDas kannst du besser!");
        }
        else {
            System.out.println(count+" von "+x+" Aufgaben korrekt! ("+percentage+"%)\nNächstes mal machst du´s bestimmt besser!");
        }
    }
}