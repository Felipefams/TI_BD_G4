package service;

import java.util.Scanner;


public class DocumentService {
	
	private DocumentDAO documentDAO = new DocumentDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	public DocumentService() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Document(), FORM_ORDERBY_DESCRICAO);
	}
	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Document(), orderBy);
	}
	
	public void makeForm(int type, Document document, int orderBy) {
		String nomeArquivo = "form.html";
	
	//Implement Method to create the component
		
	}
	
	public Object insert(Request request, Response response) {
		//Collect Metadata about the new Document from the request
		// ...
		//After that insert the new Document Object
	}
	
	public Object get(Request request, Response response) {
		//Method to return the HTTP request with a Specific Document
		// ...
	}
	
	public Object getToUpdate(Request request, Response response) {
		//Method to get a Document for Update in above method
		// ...
	}
	
	public Object update(Request request, Response response) {
		//Method to Update the Metadata from a Document
		// ...
	}
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	
	public Object delete(Request request, Response response) {
		//Method to get all documents of a User
		// ...
	}

}
