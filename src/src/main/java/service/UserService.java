package service;

import java.util.Scanner;


public class UserService {
	
	private UserDAO UserDAO = new UserDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	public UserService() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new User(), FORM_ORDERBY_DESCRICAO);
	}
	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new User(), orderBy);
	}
	
	public void makeForm(int type, User User, int orderBy) {
		String nomeArquivo = "form.html";
	
	//Implement Method to create the component
		
	}
	
	public Object insert(Request request, Response response) {
		//Collect Metadata about the new User from the request
		// ...
		//After that insert the new User Object
	}
	
	public Object get(Request request, Response response) {
		//Method to return the HTTP request with a Specific User
		// ...
	}
	
	public Object getToUpdate(Request request, Response response) {
		//Method to get a User for Update in above method
		// ...
	}
	
	public Object update(Request request, Response response) {
		//Method to Update the Metadata from a User
		// ...
	}
	
	public Object getAll(Request request, Response response) {
		//Method to get all Users of a User
		// ...
	}
	
	public Object delete(Request request, Response response) {
		//Method to get all Users of a User
		// ...
	}

}
