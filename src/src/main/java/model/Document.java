package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Document {
  protected int documentID;
  protected int userID;
  protected String docName;
  protected LocalDateTime creationDate;

  public Document() {
    documentID = -1;
    userID = -1;
    docName = "";
    creationDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

  }

  public Document(int documentID, int userID, String docName, LocalDateTime creationDate) {

    setDocumentID(documentID);
    setUserID(userID);
    setDocName(docName);
    setcreationDate(creationDate);

  }

  public int getDocumentID() {
    return this.documentID;
  }

  public int getUserID() {
    return this.userID;
  }

  public String getDocName() {
    return this.docName;
  }

  public  LocalDateTime getCreationDate(){    return this.creationDate;  }

  public void setDocumentID(int documentID) {
    this.documentID = documentID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }

  public void setcreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Document ID: " + this.documentID + "   User ID: " + this.userID + "   Document Name: " + this.docName
        + "   Date of Creation: "
        + this.creationDate;
  }

  @Override
  public boolean equals(Object obj) {
    return (this.getDocumentID() == ((Document) obj).getDocumentID());
  }

}
