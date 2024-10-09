package package1;

import package2.*;

public class A {
    protected String protectedMessage = "This is the protected message";
    public static void main(String[] args) {

        B b = new B();
        //System.out.println(b.privateMessage);

    }
}
