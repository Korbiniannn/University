public class Main {
    public static void main(String[] args) {

        // super = keyword refers to the superclass (parent) of an object
        //         very similar to the "this" keyword.

        Hero hero1 = new Hero("Batman",42,"money");
        Hero hero2 = new Hero("Superman", 43, "everything");

        System.out.println(hero1.name);
        System.out.println(hero1.age);
        System.out.println(hero1.power);
        System.out.println(hero2);
    }
}