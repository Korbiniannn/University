//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // method overriding = Declaring a method in subclass;
        //                     which is already present in parent class.
        //                     done so that a child class can give its own implementation.

        Animal animal = new Animal();
        Dog dog = new Dog();

        dog.speak();
        animal.speak();


    }
}