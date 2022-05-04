package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Version {
    private int versionid;
    private int documentID;
    private LocalDateTime CreationDate;
    private String AccessLink;

    public Version() {
        versionid = -1;
        documentID = -1;
        CreationDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        AccessLink = "";
    }

    public Version(int versionid, int documentID, LocalDateTime CreationDate, String AccessLink) {
        setVersionID(versionid);
        setdocumentID(documentID);
        setData_of_Creation(CreationDate);
        setAccessLink(AccessLink);
    }

    public int getVersionID() {
        return this.versionid;
    }

    public int getdocumentID() {
        return this.documentID;
    }

    public LocalDateTime getData_of_Creation() {
        return this.CreationDate;
    }

    public String getAccessLink() {
        return this.AccessLink;
    }

    public void setVersionID(int versionid) {
        this.versionid = versionid;
    }

    public void setdocumentID(int documentID) {
        this.documentID = documentID;
    }

    public void setData_of_Creation(LocalDateTime CreationDate) {
        this.CreationDate = CreationDate;
    }

    public void setAccessLink(String AccessLink) {
        this.AccessLink = AccessLink;
    }

    @Override
    public String toString() {
        return "ID da Versão: " + this.versionid + "   ID do Documento: " + this.documentID + "   Data de Criação: "
                + this.CreationDate;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getVersionID() == ((Version) obj).getVersionID());
    }
}
