package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Document {
   protected int ID;
   protected int UserID;
   protected String DocName;
   protected LocalDateTime DateCreation;

   
   public Document(){
     ID = -1;
     UserID = -1;
     DocName = "";
     DataCreation = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

   }


   public Document(int ID,int UserID,String DocName,LocalDateTime DateCreation){

     setID(ID);
     setUserID(UserID);
     setDocName(DocName);
     setDateCreation(DateCreation);

   }



   public  int getID(){   return this.ID;   }
   public  int getUserID(){   return this.UserID;   }
   public  String getDocName(){   return this.DocName;   }
   public  LocalDateTime getDateCreation(){    return this.DateCreation  } 



   public static void setID(int ID){   this.ID = ID;    }
   public static void setUserID(int UserID){   this.UserID = UserID;   }
   public static void setDocName(String DocName){   this.DocName = DocName;   }
   public static void setDateCreation(LocalDateTime DateCreation){  this.DateCreation = DateCreation;  }


   @Override
    public String toString() {
        return "Document ID: " + this.ID + "   User ID: " + this.UserID + "   Document Name: " + this.DocName + "   Date of Creation: "
            + this.DateCreation;
    }
    

    @Override
      public boolean equals(Object obj) {
          return (this.getID() == ((Document) obj).getID());
    } 

}
