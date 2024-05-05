import java.util.Objects;

public class PasswordEntry{
    String website;
    String loginName;
    PasswordComplexity passwordComplexity;
    String password;
    public PasswordEntry nextEntry = null;


    public PasswordEntry(String website, String loginName, PasswordComplexity passwordComplexity){
        if (website == null || website.isBlank()) {
            throw new IllegalArgumentException("Website can't be null nor only whitespaces!");
        }
        else{
            this.website = website;
        }
        if (passwordComplexity == null) {
            throw new IllegalArgumentException("PasswordComplexity can't be null!");
        }else {
            this.passwordComplexity = passwordComplexity;
        }
        this.loginName = loginName;
        this.password= passwordComplexity.generatePassword();

    }

    public PasswordEntry(String website, PasswordComplexity passwordComplexity){
        this(website, null, passwordComplexity);
    }

    public PasswordEntry(PasswordEntry other){
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

    public String getWebsite(){
        return website;
    }

    public String getLoginName(){
        return loginName;
    }

    public PasswordComplexity getPasswordComplexity(){
        return passwordComplexity;
    }

    public String getPassword(){
        return password;
    }

    public PasswordEntry getNextEntry(){
        return nextEntry;
    }

    public void setNextEntry(PasswordEntry nextEntry){
        this.nextEntry = nextEntry;
    }

    public void regeneratePassword(){
        this.password = passwordComplexity.generatePassword();
    }

    @Override
    public String toString(){
        return this.website +" L:"+this.loginName+" P:"+this.password+"("+this.passwordComplexity+")";
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null){
            return false;
        }
        if (getClass() != other.getClass()){
            return false;
        }
        PasswordEntry otherPasswordEntry = (PasswordEntry) other;
        return Objects.equals(this.website, otherPasswordEntry.website)&&
                Objects.equals(this.loginName, otherPasswordEntry.loginName)&&
                Objects.equals(this.passwordComplexity, otherPasswordEntry.passwordComplexity);
    }
}
