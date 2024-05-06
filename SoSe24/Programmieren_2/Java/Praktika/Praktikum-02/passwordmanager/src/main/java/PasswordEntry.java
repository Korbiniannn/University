import java.util.Objects;

/**
 * @author korbinian ziegltrum
 * @version 1.0.
 *
 * Methode um einen Eintrag darzustellen, der die Website-URL, den LoginNamen,
 * die Passwortstärke und das Passwort beinhaltet.
 */

public class PasswordEntry{
    private final String website;
    private final String loginName;
    private final PasswordComplexity passwordComplexity;
    public String password;
    PasswordEntry nextEntry = null;

    /**
     * Konstruiert einen Eintrag mit der Website-URL, dem Login Namen und der Passwortstärke.
     *
     * @param website Die Website-URL
     * @param loginName Der Login Name
     * @param passwordComplexity Die Passwortstärke
     * @throws IllegalArgumentException wenn die Website "null" oder "blank" ist oder wenn die Passwortstärke "null" ist.
     */
    public PasswordEntry(String website, String loginName, PasswordComplexity passwordComplexity){ // Konstruktor für PasswortEntry
        if (website == null || website.isBlank()) { // If-Statement um sicherzustellen, dass Website weder "null" noch "Blank" ist
            throw new IllegalArgumentException("Website can't be null nor only whitespaces!");
        }
        else{
            this.website = website;
        }
        if (passwordComplexity == null) { // If-Statement um sicherzustellen, dass passwordComplexity nicht "null" ist
            throw new IllegalArgumentException("PasswordComplexity can't be null!");
        }else {
            this.passwordComplexity = passwordComplexity;
        }
        this.loginName = loginName;
        this.password= passwordComplexity.generatePassword();

    }

    /**
     * Konstruktor, der alle Argumente des ersten aufnimmt bis auf loginName
     *
     * @param website Die Website-URL
     * @param passwordComplexity Die Passwortstärke
     */
    public PasswordEntry(String website, PasswordComplexity passwordComplexity){ // Konstruktor, der alle Argumente des ersten aufnimmt bis auf loginName
        this(website, null, passwordComplexity);
    }

    /**
     * Konstruktor, der ein PasswordEntry-Objekt kopieren soll.
     *
     * @param other Das Objekt, das kopiert werden soll.
     * @throws IllegalArgumentException wenn Other "null" ist.
     */
    public PasswordEntry(PasswordEntry other){ // Copier-Konstruktor
        if (other == null) {
            throw new IllegalArgumentException("Other can't be null!");
        }
        else{
            this.website = other.website;
            this.loginName = other.loginName;
            this.passwordComplexity = other.passwordComplexity;
            this.password = other.password;
            this.nextEntry = other.nextEntry;
        }
    }

    /**
     * Gibt die Website-URL zurück.
     *
     * @return Die Website-URL.
     */
    public String getWebsite(){ // Getter für Website
        return website;
    }

    /**
     * Gibt den login Namen zurück.
     *
     * @return Den login Namen.
     */
    public String getLoginName(){ // Getter für LoginName
        return loginName;
    }

    /**
     * Gibt die Passwortstärke zurück.
     *
     * @return Die Passwortstärke.
     */
    public PasswordComplexity getPasswordComplexity(){ // Getter für PasswordComplexity
        return passwordComplexity;
    }

    /**
     * Gibt das Passwort zurück.
     *
     * @return Das Passwort.
     */
    public String getPassword(){ // Getter für Password
        return password;
    }

    /**
     * Gibt das nächste Objekt der Liste zurück.
     *
     * @return Das nächte Objekt der Liste.
     */
    public PasswordEntry getNextEntry(){ // Getter für NextEntry
        return nextEntry;
    }

    /**
     * Setzt den nächsten Eintrag der Liste.
     *
     * @param nextEntry Der Eintrag der gesetzt werden soll.
     */
    public void setNextEntry(PasswordEntry nextEntry){ // Setter für NextEntry
        this.nextEntry = nextEntry;
    }

    /**
     * Generiert ein neues Passwort.
     */
    public void regeneratePassword(){ // Methode um das Passwort neu zu generieren
        this.password = passwordComplexity.generatePassword();
    }

    /**
     * Gibt einen Eintrag als String in formatiertem Format zurück.
     *
     * @return Den Eintrag als String.
     */
    @Override
    public String toString(){ // Overridden toString Methode um jeden Eintrag nach bestimmtem Schema zurückzugeben
        return this.website +" L:"+this.loginName+" P:"+this.password+" ("+this.passwordComplexity+")";
    }

    /**
     * Methode um zu überprüfen, ob zwei Objekte gleich sind.
     *
     * @param other Das Objekt, das überprüft wird.
     * @return true, falls die Objekte gleich sind sonst false.
     */
    @Override
    public boolean equals(Object other){ // Overridden equals Methode um zu prüfen, ob zwei Objekte gleich sind
        if (this == other){
            return true;
        }
        if (other == null){ // filter für "null" Einträge
            return false;
        }
        if (getClass() != other.getClass()){ // filter für Objekte unterschiedlicher Klassen
            return false;
        }
        PasswordEntry otherPasswordEntry = (PasswordEntry) other;
        return Objects.equals(website, otherPasswordEntry.website)&&
                Objects.equals(loginName, otherPasswordEntry.loginName)&&
                Objects.equals(passwordComplexity, otherPasswordEntry.passwordComplexity);
    }
}
