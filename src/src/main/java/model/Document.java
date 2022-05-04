package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Document {
  protected int documentID;
  protected int UserID;
  protected String DocName;
  protected LocalDateTime creationDate;

  public Document() {
    documentID = -1;
    UserID = -1;
    DocName = "";
    creationDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

  }

  public Document(int documentID, int UserID, String DocName, LocalDateTime creationDate) {

    setDocumentID(documentID);
    setUserID(UserID);
    setDocName(DocName);
    setcreationDate(creationDate);

  }

  public int getDocumentID() {
    return this.documentID;
  }

  public int getUserID() {
    return this.UserID;
  }

  public String getDocName() {
    return this.DocName;
  }

  public  LocalDateTime getCreationDate(){    return this.creationDate;  }

  public void setDocumentID(int documentID) {
    this.documentID = documentID;
  }

  public void setUserID(int UserID) {
    this.UserID = UserID;
  }

  public void setDocName(String DocName) {
    this.DocName = DocName;
  }

  public void setcreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Document ID: " + this.documentID + "   User ID: " + this.UserID + "   Document Name: " + this.DocName
        + "   Date of Creation: "
        + this.creationDate;
  }

  @Override
  public boolean equals(Object obj) {
    return (this.getDocumentID() == ((Document) obj).getDocumentID());
  }

}
