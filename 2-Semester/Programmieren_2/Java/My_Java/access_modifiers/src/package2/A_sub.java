package package2;

import package1.A;

public class A_sub extends A {
    public static void main(String[] args) {

        A_sub a = new A_sub();
        System.out.println(a.protectedMessage);
    }
}
