package de.jreker.kafka.consumer;

public class Customer {
    private int ID;
    private String LastName;
    private String FirstName;
    private String Telephone;
    private String EMail;

    public Customer(String name, String sirname, String telephone, String eMail, int id) {
        LastName = name;
        FirstName = sirname;
        Telephone = telephone;
        EMail = eMail;
        ID = id;
    }
    public Customer() {}
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", EMail='" + EMail + '\'' +
                '}';
    }
}
