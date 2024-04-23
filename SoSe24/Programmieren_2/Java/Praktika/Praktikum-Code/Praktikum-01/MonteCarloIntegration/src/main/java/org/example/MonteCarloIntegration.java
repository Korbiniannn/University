package org.example;


public class MonteCarloIntegration {
    // Main Mehtode um sich dem Integral anzunähern
    public static void main(String[] args) {
        //Random rand = new Random();
        final int MAX_ITERATIONS = 100_000;
        final double MIN_CHANGE = 0.000010;
        double allPoints = 10;
        double pointsUnderCurve = 1;
        double approxInt = 1;
        double approxInt2 = pointsUnderCurve / allPoints;
        // While-Loop wiederholt sich, solange bis die änderung ziwchen dem neuen "approxInt2" und dem alten "approxInt" sehr niedrig ist oder "MAX_ITERATIONS" erreicht wird.
        while (allPoints < MAX_ITERATIONS && Math.abs(approxInt2-approxInt) > MIN_CHANGE) {
            approxInt = pointsUnderCurve / allPoints; // alter Wert von approxInt wird mit dem Wert von approxInt2 ersetzt
            ++allPoints;
            double x = Math.random();
            double y = Math.random();
            if (y <= function(x)){ // if-statement um zu überprüfen, ob der y-Wert unter der Funktionskurve liegt
                ++pointsUnderCurve;
            }
            approxInt2 = pointsUnderCurve / allPoints; // approxInt2 wird der neue Wert zugeordnet
        }
        double last_Change = approxInt2 - approxInt;
        System.out.println("Iteration "+(allPoints-10)+": "+approxInt2+" ("+last_Change+")");
    }
    public static double function(double x) { // Methode für die Funktion

        return Math.sin(Math.PI*x);

    }
}