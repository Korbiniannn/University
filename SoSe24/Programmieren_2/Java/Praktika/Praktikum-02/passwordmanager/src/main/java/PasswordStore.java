/**
 * @author korbinian ziegltrum
 * @version 1.0.
 * Diese Klasse verwaltet alle Einträge in der Liste.
 */
public class PasswordStore {
    int size;
    PasswordEntry firstEntry;

    /**
     * Default Konstruktor der einen leeren PasswordStore erstellt.
     */
    public PasswordStore() { // default Konstruktor
        this.size = 0;
        this.firstEntry = null;
    }

    /**
     * Gibt die Anzahl der Einträge in der Liste zurück.
     *
     * @return Die Anzahl der Einträge.
     */
    public int getSize(){ // Getter für size
        return size;
    }

    /**
     * Gibt den neusten Eintrag der Liste zurück.
     *
     * @return Der neuste Eintrag.
     */
    public PasswordEntry getFirstEntry(){ //Getter für firstEntry
        return firstEntry;
    }

    /**
     * Setzt den neusten Eintrag.
     *
     * @param firstEntry Der Eintrag, der als neustes Element in die Liste hinzugefügt wird
     */
    public void setFirstEntry(PasswordEntry firstEntry){ // Setter für firstEntry
        this.firstEntry = firstEntry;
    }

    /**
     * Überprüft, ob ein Eintrag schon in der Liste ist.
     *
     * @param entry Der zu überprüfende Eintrag
     * @return true, wenn der Eintrag schon in der Liste ist sons false
     * @throws IllegalArgumentException wenn der zu überprüfende Eintrag "null" ist
     */
    public boolean contains(PasswordEntry entry){ // Methode um zu überprüfen, ob ein Eintrag schon in der liste ist
        PasswordEntry current = firstEntry;
        if (entry == null){
            throw new IllegalArgumentException("entry cannot be null");
        }
        while(current != null){
            if(current.equals(entry)){
                return true;
            }
            current = current.getNextEntry();
        }
        return false;
    }

    /**
     * Fügt einen Eintag der noch nicht in der Liste ist zur Liste als "firstEntry" hinzu
     * und setzt das alte erste Element auf "nextEntry".
     *
     * @param entry Der zu überprüfende Eintrag.
     * @return true, wenn der zu überprüfende Eintrag hinzugefügt wird sonst false.
     * @throws IllegalArgumentException wenn der zu überprüfende Eintrag "null" ist.
     */
    public boolean add(PasswordEntry entry){ // Methode um einen Eintrag der noch nicht in der Liste ist in die Liste einzufügen
        if (entry == null){ // filter für "null" Eintrag
            throw new IllegalArgumentException("entry cannot be null");
        }
        if(contains(entry)){ // filter für Einträge, die schon in der Liste sind (ruft contains-Methode auf)
            return false;
        }
        else{
            entry.setNextEntry(firstEntry); // setzt den alten "firstEntry" auf nextEntry
            setFirstEntry(entry); // setzt den neuen Eintrag auf "firstEntry"
            size++; // erhöht size um einen Eintrag
            return true;
        }
    }

    /**
     * Printed alle Einträge mit der passenden Stelle in der Liste.
     */
    public void printPasswordStore(){ // printed alle Einträge mit der passenden Stelle in der Liste
        PasswordEntry current = firstEntry;
        for (int i = 0; i < size; i++) {
            System.out.println(i+". "+current.toString());
            current = current.getNextEntry();
        }
    }

    /**
     * Printed alle Einträge zu einer bestimmten Website.
     *
     * @param website Die Website, deren Einträge ausgegeben werden sollen.
     * @throws IllegalArgumentException wenn die Website "null" ist.
     */
    public void printPasswordStore(String website){// printed alle Einträge zu einer bestimmten Website
        if(website == null){
            throw new IllegalArgumentException("website cannot be null");
        }
        PasswordEntry current = firstEntry;
        for (int i = 0; i < size; i++) {
            if (current.getWebsite().equals(website)) {
                System.out.println(i + ". " +current);
            }
            current = current.getNextEntry();
        }
    }

    /**
     * Gibt einen Eintrag an einem bestimmten Index zurückzugeben
     *
     * @param index Index des zurückzugebenden Eintrags.
     * @return Den Eintrag an der Stelle Index.
     * @throws IllegalArgumentException wenn index größer als die Länge der Liste ist oder index kleiner als 0 ist.
     */
    public PasswordEntry get(int index) { // Methode um einen Eintrag an einem bestimmten Index zurückzugeben
        PasswordEntry current = firstEntry;
        int i = 0;
        if (index > size || index < 0) {
            throw new IllegalArgumentException("index not in list!");
        }
        while(index != i){
            i++;
            current = current.getNextEntry();
        }
        return current;
    }
}

