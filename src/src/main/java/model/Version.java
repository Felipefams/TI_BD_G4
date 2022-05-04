package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Version {
    private int id;
    private int DocumentId;
    private LocalDateTime DateOfCreation;
    private String LinkOfAccess;

    public Version() {
       id = -1;
       DocumentId = -1;
       DateOfCreation = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
       LinkOfAccess = "";
    }

    public Version(int id,int DocumentId,LocalDateTime DateOfCreation,String LinkOfAccess) {
        setId(id);
        setDocumentId(DocumentId);
        setData_of_Creation(DateOfCreation);
        setLinkOfAccess(LinkOfAccess);
     }


    public int getId(){ return this.id; }
    public int getDocumentId(){ return this.DocumentId; }
    public LocalDateTime getData_of_Creation(){ return this.DateOfCreation; }
    public String getLinkOfAccess(){ return this.LinkOfAccess; }


    public void setId(int id){ this.id = id; }
    public void setDocumentId(int DocumentId){ this.DocumentId = DocumentId; }
    public void setData_of_Creation(LocalDateTime DateOfCreation){ this.DateOfCreation = DateOfCreation; }
    public void setLinkOfAccess(String LinkOfAccess){ this.LinkOfAccess = LinkOfAccess; }
    

    @Override
	public String toString() {
		return "ID da Versão: " + this.id + "   ID do Documento: " + this.DocumentId + "   Data de Criação: " + this.DateOfCreation ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Version) obj).getId());
	}	
}
