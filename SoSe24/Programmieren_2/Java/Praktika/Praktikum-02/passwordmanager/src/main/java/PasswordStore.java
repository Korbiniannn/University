public class PasswordStore {
    int size;
    PasswordEntry firstEntry;

    public PasswordStore() {
        this.size = 0;
        this.firstEntry = null;
    }

    public int getSize() {
        return size;
    }

    public PasswordEntry getFirstEntry() {
        return firstEntry;
    }

    public void setFirstEntry(PasswordEntry firstEntry){
        this.firstEntry = firstEntry;
    }

    public boolean contains(PasswordEntry entry){
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

    public boolean add(PasswordEntry entry){
        if (entry == null){
            throw new IllegalArgumentException("entry cannot be null");
        }
        if(contains(entry)){
            return false;
        }
        else{
            entry.setNextEntry(firstEntry);
            setFirstEntry(entry);
            size++;
            return true;
        }
    }

    public void printPasswordStore(){
        PasswordEntry current = firstEntry;
        for (int i = 0; i < size; i++) {
            System.out.println(current.toString());
            current = current.getNextEntry();
        }
    }

}
