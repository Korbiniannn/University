package org.example;

public class Main {
    public static void main (String[] args) {
        Human human1 = new Human("Rick", 65, 80);
        Human human2 = new Human("Morty", 16, 70);
        human2.eat();
        human1.drink();
        human2.bicycle();
        //System.out.println(human1.name + " and " + human2.name);

    }
}