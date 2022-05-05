package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.VersionDAO;
import model.Version;
import spark.Request;
import spark.Response;


public class VersionService {
	
	private VersionDAO VersionDAO = new VersionDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	public VersionService() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Version(), FORM_ORDERBY_DESCRICAO);
	}
	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Version(), orderBy);
	}
	
	public void makeForm(int type, Version Version, int orderBy) {
		String nomeArquivo = "form.html";
	
	//Implement Method to create the component
		
	}
	
	public Object insert(Request request, Response response) {
		//Collect Metadata about the new Version from the request
		// ...
		//After that insert the new Version Object
	}
	
	public Object get(Request request, Response response) {
		//Method to return the HTTP request with a Specific Version
		// ...
	}
	
	public Object getToUpdate(Request request, Response response) {
		//Method to get a Version for Update in above method
		// ...
	}
	
	public Object update(Request request, Response response) {
		//Method to Update the Metadata from a Version
		// ...
	}
	
	public Object getAll(Request request, Response response) {
		//Method to get all Versions of a User
		// ...
	}
	
	public Object delete(Request request, Response response) {
		//Method to get all Versions of a User
		// ...
	}

}
