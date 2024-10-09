import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasse um die Passwörter zu Managen
 */

public class PasswordManager {
  /**
   * Fügt beispiel Einträge hinzu (zum Testen)
   *
   * @param store Objekt der Klasse PasswordStore
   */
  private static void addExampleEntries(PasswordStore store){
    PasswordEntry e1 = new PasswordEntry("reddit.com","extremelurker", PasswordComplexity.PIN);
    PasswordEntry e2 = new PasswordEntry("github.com", "java-programmer", PasswordComplexity.SIMPLE);
    PasswordEntry e3 = new PasswordEntry("moodle.haw-landshut.de", "s-cauer", PasswordComplexity.COMPLEX);
    store.add(e1);
    store.add(e2);
    store.add(e3);
  }

  /**
   * Fügt neuen Eintrag zur Liste hinzu, wenn er noch nicht vorhanden ist.
   *
   * @param store Objekt der Klasse PasswordStore
   * @param website Website-URL
   * @param loginName Login Name
   * @param complexity Passwortstärke
   */
  private static void addPasswordEntry(PasswordStore store, String website, String loginName, PasswordComplexity complexity){

    PasswordEntry entry = new PasswordEntry(website, loginName, complexity);

    if (store.add(entry)){
      System.out.printf("New entry added: %s%n", entry);
    } else {
      System.out.println("Entry already exists");
    }

  }

  /**
   * Ruft die "printPasswordStore" Methode aus PasswordStore auf um alle Einträge wiederzugeben.
   *
   * @param store Objekt der Klasse PasswordStore
   */
  private static void printPasswordStore(PasswordStore store){
    store.printPasswordStore();
  }

  /**
   * Ruft die überladene "printPasswordStore(website)" Methode aus PasswordStore auf um alle Einträge
   * zu einer Website wiederzugeben.
   *
   * @param store Objekt der Klasse PasswordStore.
   * @param website Website-URL
   */
  private static void printPasswordStoreForWebsite(PasswordStore store, String website){
    store.printPasswordStore(website);
  }

  /**
   * Ruft die "regeneratePassword" Methode auf um ein neues Passwort zu erstellen
   * für einen Eintrag an einem bestimmten Index, der durch die "get(index)" Methode gegeben wird.
   *
   * @param store Objekt der Klasse PasswordStore
   * @param index Index des gewünschten Eintrags
   */
  private static void regeneratePassword(PasswordStore store, int index){
    store.get(index).regeneratePassword();
  }

  private static void removeEntry(PasswordStore store, int index){
    // store.remove(index);
  }


  // ##########################################################################
  // ########### DON'T CHANGE ANYTHING BELOW THIS LINE ########################
  // ##########################################################################

  private static void printPasswordComplexities(){
    for (PasswordComplexity complexity : PasswordComplexity.values()){
      System.out.printf(" %d: %s - %s Length: %d%n",
          complexity.ordinal(), complexity.name(), complexity.getChars(), complexity.getLength());
    }
  }


  private static void readNewEntry(Scanner scanner, PasswordStore store){

    String website = "";

    while (website.isBlank()){
      System.out.print("Website: ");
      website = scanner.next();
    }

    String loginName = "";

    System.out.print("Login name: ");
    loginName = scanner.next();


    PasswordComplexity selectedComplexity = null;
    do{
      System.out.println("Choose a password complexity: ");
      printPasswordComplexities();
      System.out.print("Selection: ");
      try {
        int selection = scanner.nextInt();

        if (selection >= 0 && selection < PasswordComplexity.values().length){
          selectedComplexity = PasswordComplexity.values()[selection];
        } else {
          System.out.println("Invalid selection");
        }

      } catch (InputMismatchException e){
        System.out.println("Invalid input");
        scanner.nextLine();
      }

    } while (selectedComplexity == null);

    addPasswordEntry(store, website, loginName, selectedComplexity);


  }

  /**
   *
   * @param scanner Um einen Index entgegenzunehmen
   * @param store Objekt der Klasse PasswordStore
   * @return den Index
   */
  private static int readIndex(Scanner scanner, PasswordStore store){

    boolean valid = false;
    int index = 0;

    if (store.getSize() == 0) {
      System.out.println("Nothing to remove");
      return 0;
    }


    do {
      System.out.print("Index (-1 to abort): ");
      try {
        index = scanner.nextInt();

        if (index >= 0 && index < store.getSize())
          valid = true;
        else if (index == -1)
          return -1;
        else
          System.out.println("Invalid index.");


      } catch (InputMismatchException e){
        System.out.println("Invalid input.");
        scanner.nextLine();

      }

    } while (!valid);

    return index;

  }

  /**
   *
   * @param args Kommandozeilenparameter
   */
  public static void main(String[] args) {
    PasswordStore store = new PasswordStore();

    addExampleEntries(store);

    Scanner scanner = new Scanner(System.in);

    String input = null;

    do {

      System.out.println("(N)ew entry, (L)ist entries, re(G)enerate password, (F)ind entry, (R)emove entry, (Q)uit ");
      input = scanner.next().toUpperCase();

      switch (input){
        case "N":
          readNewEntry(scanner, store);
          break;

        case "L":
          printPasswordStore(store);
          break;

        case "G":
          int index = readIndex(scanner, store);
          if (index >= 0)
            regeneratePassword(store, index);
          break;

        case "F":
          System.out.print("Website: ");
          String website = scanner.next();
          printPasswordStoreForWebsite(store, website);
          break;

        case "R":
          index = readIndex(scanner, store);
          if (index >= 0)
            removeEntry(store, index);
          break;

        case "Q":
          System.out.println("Bye!");
          break;

        default:
          System.out.println("Ungültige Eingabe!");

      }



    } while (!input.equals("Q"));

    scanner.close();




  }

}
