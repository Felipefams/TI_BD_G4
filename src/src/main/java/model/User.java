package model;

public class User {
    protected int userID;
    protected String userName;
    protected String email;
    protected String recoveryEmail;
    protected String userPassword;


    public User() {
        userrID = -1;
        userName = "";
        email = "";
        recoveryEmail = "";
        userPassword = "";
    }


    public User(int userID, String userName, String email, String recoveryEmail, String userPassword) {
          setUserID(userID);
          setName(userName);
          setEmail(email);
          setRecoveryEmail(recoveryEmail);
          setUserPassword(userPassword);
    
    } 


    public int getuserID() {     return this.userID;    }
    public String getUserName() {   return this.userName;    }
    public String getEmail() {     return this.email;     }
    public String getRecoveryEmail() {  return this.recoveryEmail;   }
    public String getUserPassword() {     return this.userPassword;    }
    
    
    
    public void setUserID(int userID) {    this.userID = userID;    }
    public void setUserName(String userName) {   this.userName = userName;    }
    public void setEmail(String email) {   this.email = email;    }
    public void setRecoveryEmail(String recoveryEmail) {   this.recoveryEmail  = recoveryEmail;   }
    public void setUserPassword(String userPassword) {    this.userPassword = userPassword;    }


    @Override
    public String toString() {
          return "User ID: " + this.userID + "   UserName: " + this.userName + "   Email: " + this.email + "   Recovery Email: "
              + this.recoveryEmail  + "   Password: " + this.userPasssword;
    }
   

    @Override
    public boolean equals(Object obj) {
            return (this.getuserID() == ((User) obj).getID());
    } 

}
