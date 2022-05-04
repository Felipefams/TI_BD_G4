package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Version {
    private int documentID;
    private int versionID;
    private LocalDateTime creationDate;
    private String accessLink;

    public Version() {
        versionID = -1;
        documentID = -1;
        creationDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        accessLink = "";
    }

    public Version(int versionID, int documentID, LocalDateTime CreationDate, String AccessLink) {
        setVersionID(versionID);
        setdocumentID(documentID);
        setCreationDate(CreationDate);
        setAccessLink(AccessLink);
    }

    public int getVersionID() {
        return this.versionID;
    }

    public int getdocumentID() {
        return this.documentID;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public String getAccessLink() {
        return this.accessLink;
    }

    public void setVersionID(int versionid) {
        this.versionID = versionid;
    }

    public void setdocumentID(int documentID) {
        this.documentID = documentID;
    }

    public void setCreationDate(LocalDateTime CreationDate) {
        this.creationDate = CreationDate;
    }

    public void setAccessLink(String AccessLink) {
        this.accessLink = AccessLink;
    }

    @Override
    public String toString() {
        return "ID da Versão: " + this.versionID + "   ID do Documento: " + this.documentID + "   Data de Criação: "
                + this.creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getVersionID() == ((Version) obj).getVersionID());
    }
}
