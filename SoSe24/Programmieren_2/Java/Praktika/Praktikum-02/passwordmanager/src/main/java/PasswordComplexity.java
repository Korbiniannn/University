import java.util.Random;

/**
 * @author korbinian ziegltrum
 * @version 1.0.
 *
 * Enumeration, um verschiedene Komplexitätsstufen zu definieren.
 *Jedes Element definiert die gewünschten Zeichen sowie die gewünschte Länge.
 */
// Hilfsklasse um Passwörter mit verschiedenen Stärken zu erzeugen
public enum PasswordComplexity {
    // die verschiedenen Typen der Passwort-komplexität
    PIN("0", 4),
    SIMPLE("aA", 5),
    MEDIUM("aA0", 8),
    COMPLEX("aA0!", 10),
    SUPER_COMPLEX("aA0!", 16);

    private final String chars;
    private final int length;

    /**
     * Konstruktor der Enum-Klasse.
     *
     * @param chars Die Zeichen, die im Passwort vorkommen dürfen.
     * @param length Die Länge des Passworts.
     */
    PasswordComplexity(String chars, int length){ // Konstruktor der Enum-Klasse
        this.chars = chars;
        this.length = length;
    }

    /**
     * Gibt einen String zurück, der die Zeichen beinhaltet, aus denen das Passwort bestehen darf.
     *
     * @return String, der die Zeichen enthält, aus denen das Passwort zusammengesetzt wird.
     */
    public String getChars(){ // Getter für die Zeichen
        return chars;
    }

    /**
     * Gibt die Länge des Passworts zurück.
     *
     * @return Integer, der die länge des Passworts angibt.
     */
    public int getLength(){ // Getter für die Länge
        return length;
    }

    /**
     * Generiert ein zufälliges Passwort, dass aus den zuvor definierten "chars" besteht mit der Länge "length".
     *
     * @return Das Passwort, das zufällig generiert wurde
     */
    public String generatePassword(){ // generiert zufällig ein Passwort mit der länge "length" und den passenden "chars"
        Random randint = new Random();
        String allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!?+-;,.:"; // String mit allen erlaubten Characters
        StringBuilder password = new StringBuilder(); // StringBuilder um an den String etwas anzuhängen
        for (int i = 0; i < length; i++){ // loop um die passenden Zeichen passend zur Passwortstärke and den Passwortstring anzuhängen
            switch (this){ // Ein Switch um die verschiedenen Passwortstärken zu unterscheiden
                case PIN:
                    password.append(allowedChars.charAt(randint.nextInt(10)));
                    break;
                case SIMPLE:
                    password.append(allowedChars.charAt(randint.nextInt(10,allowedChars.length()-8)));
                    break;
                case MEDIUM:
                    password.append(allowedChars.charAt(randint.nextInt(allowedChars.length()-8)));
                    break;
                case COMPLEX: case SUPER_COMPLEX:
                    password.append(allowedChars.charAt(randint.nextInt(allowedChars.length())));
                    break;
            }
        }
        return password.toString();
    }
}
