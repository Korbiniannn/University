//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // inheritance = the process where one class acquires;
        //               the attributes and methods of another.

        Car car = new Car();
        car.go();

        Bicycle bicycle = new Bicycle();
        bicycle.stop();

        System.out.println(car.speed);
        System.out.println(bicycle.speed);

        System.out.println(car.doors);
        System.out.println(bicycle.pedals);

    }
}