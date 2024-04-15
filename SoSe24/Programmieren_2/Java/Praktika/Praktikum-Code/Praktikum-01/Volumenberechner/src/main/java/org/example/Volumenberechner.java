package org.example;

public class Volumenberechner {
    public static void main(String[] args){

        // used a switch to check for command-line arguments, then to hop into the different methods.
        switch(args.length){
            case 1:
                System.out.println("Kugelvolumen: "+kugel(Double.parseDouble(args[0])));
                break;
            case 2:
                System.out.println("Pyramidenvolumen: "+pyramide(Double.parseDouble(args[0]), Double.parseDouble(args[1])));
                break;
            case 3:
                System.out.println("Quadervolumen: "+quader(Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2])));
                break;
            default:
                System.out.println("Verfügbare Berechnungen: \nKugel: Radius \nPyramide: Grundseite Höhe \nQuader: Länge Breite Höhe");
                break;
        }
    }
    // Method to calculate the volume of the globe
    static double kugel(double r){
        return 4.0/3 * Math.PI * (r*r*r);
    }
    // Method to calculate the volume of the pyramid
    static double pyramide(double g,double h){
        return 1.0/3 * (g*g) * h;
    }
    // Method to calculate the volume of the cuboid
    static double quader(double l, double b, double h){
        return l * b * h;
    }
}