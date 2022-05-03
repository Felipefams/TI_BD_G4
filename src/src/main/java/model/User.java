package model;

public class User {
    protected int ID;
    protected String Name;
    protected String Email;
    protected String RecoveryEmail;
    protected String UserPassword;


    public Produto() {
        ID = -1;
        Name = "";
        Email = "";
        RecoveryEmail = "";
        UserPassword = "";
    }


    public Produto(int ID, String Name, String Email, String RecoveryEmail, String UserPassword) {
          setID(ID);
          setName(Name);
          setEmail(Email);
          setRecoveryEmail(RecoveryEmail);
          setUserPassword(UserPassword);
    
    } 


    public int getID() {     return this.ID;    }
    public String getName() {   return this.Name;    }
    public String getEmail() {     return this.Email;     }
    public String getRecoveryEmail() {  return this.RecoveryEmail;   }
    public String getUserPassword() {     return this.UserPassword;    }
    
    
    
    public void setID(int ID) {    this.ID = ID;    }
    public void setName(String Name) {   this.Name = Name;    }
    public void setEmail(String Email) {   this.Email = Email;    }
    public void setRecoveryEmail(String RecoveryEmail) {   this.RecoveryEmail  = RecoveryEmail;   }
    public void setUserPassword(String UserPassword) {    this.UserPassword = UserPassword;    }


    @Override
    public String toString() {
          return "User ID: " + this.ID + "   UserName: " + this.Name + "   Email: " + this.email + "   Recovery Email: "
              + this.RecoveryEmail  + "   Password: " + this.UserPasssword;
    }
   

    @Override
    public boolean equals(Object obj) {
            return (this.getID() == ((User) obj).getID());
    } 

}
