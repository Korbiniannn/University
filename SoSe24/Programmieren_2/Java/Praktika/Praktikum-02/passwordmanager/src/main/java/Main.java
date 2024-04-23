import java.util.Random;

public class Main {
    public static void main(String[] args) {
            Random randint = new Random();
            String allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!?+-;,.:";
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < 4; i++){
                password.append(allowedChars.charAt(randint.nextInt(10)));
            }

        System.out.println(PasswordComplexity.PIN.generatePassword());

    }
}