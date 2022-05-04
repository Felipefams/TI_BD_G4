package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Version {
    private int versionid;
    private int documentID;
    private LocalDateTime DateOfCreation;
    private String LinkOfAccess;

    public Version() {
        versionid = -1;
        documentID = -1;
        DateOfCreation = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LinkOfAccess = "";
    }

    public Version(int versionid, int documentID, LocalDateTime DateOfCreation, String LinkOfAccess) {
        setVersionID(versionid);
        setdocumentID(documentID);
        setData_of_Creation(DateOfCreation);
        setLinkOfAccess(LinkOfAccess);
    }

    public int getVersionID() {
        return this.versionid;
    }

    public int getdocumentID() {
        return this.documentID;
    }

    public LocalDateTime getData_of_Creation() {
        return this.DateOfCreation;
    }

    public String getLinkOfAccess() {
        return this.LinkOfAccess;
    }

    public void setVersionID(int versionid) {
        this.versionid = versionid;
    }

    public void setdocumentID(int documentID) {
        this.documentID = documentID;
    }

    public void setData_of_Creation(LocalDateTime DateOfCreation) {
        this.DateOfCreation = DateOfCreation;
    }

    public void setLinkOfAccess(String LinkOfAccess) {
        this.LinkOfAccess = LinkOfAccess;
    }

    @Override
    public String toString() {
        return "ID da Versão: " + this.versionid + "   ID do Documento: " + this.documentID + "   Data de Criação: "
                + this.DateOfCreation;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getVersionID() == ((Version) obj).getVersionID());
    }
}
