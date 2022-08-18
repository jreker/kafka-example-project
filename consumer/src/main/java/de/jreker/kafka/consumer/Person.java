package de.jreker.kafka.consumer;


public class Person {
    
    private String Name;
    private String Sirname;
    private String Telephone;
    private String EMail;

    public Person(String name, String sirname, String telephone, String eMail) {
        Name = name;
        Sirname = sirname;
        Telephone = telephone;
        EMail = eMail;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    
    public String getSirname() {
        return Sirname;
    }
    public void setSirname(String sirname) {
        Sirname = sirname;
    }
    
    public String getTelephone() {
        return Telephone;
    }
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
    
    public String getEMail() {
        return EMail;
    }
    public void setEMail(String eMail) {
        EMail = eMail;
    }
}
