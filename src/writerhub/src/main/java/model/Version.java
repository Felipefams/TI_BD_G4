package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Version {
    private int id;
    private int Document_id;
    private LocalDateTime Data_of_creation;
    private String Link_of_Access;

    public Version() {
       id = -1;
       Document_id = -1;
       Data_of_creation = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
       Link_of_Access = "";
    }

    public Version(int id,int Document_id,LocalDateTime Data_of_creation,String Link_of_Access) {
        setId(id);
        setDocumentId(Document_id);
        setData_of_Creation(Data_of_creation);
        setLink_of_Access(Link_of_Access);
     }


    public int getId(){ return this.id; }
    public int getDocumentId(){ return this.Document_id; }
    public LocalDateTime getData_of_Creation(){ return this.Data_of_creation; }
    public String getLink_of_Access(){ return this.Link_of_Access; }


    public void setId(int id){ this.id = id; }
    public void setDocumentId(int Document_id){ this.Document_id = Document_id; }
    public void setData_of_Creation(LocalDateTime Data_of_creation){ this.Data_of_creation = Data_of_creation; }
    public void setLink_of_Access(String Link_of_Access){ this.Link_of_Access = Link_of_Access; }
    

    @Override
	public String toString() {
		return "ID da Versão: " + this.id + "   ID do Documento: " + this.Document_id + "   Data de Criação: " + this.Data_of_creation ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Version) obj).getId());
	}	
}
