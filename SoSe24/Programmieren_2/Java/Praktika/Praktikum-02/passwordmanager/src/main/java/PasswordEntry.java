public class PasswordEntry{
    private String website;
    private String loginName;
    PasswordComplexity passwordComplexity;
    String password;
    PasswordEntry nextEntry = null;

    PasswordEntry(String website, String loginName, PasswordComplexity passwordComplexity){
        this.website = website;
        this.loginName = loginName;
        this.passwordComplexity = passwordComplexity;
        this.password= passwordComplexity.generatePassword();

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


}
