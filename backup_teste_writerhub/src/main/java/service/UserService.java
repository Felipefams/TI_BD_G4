package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.UserDAO;
import model.Document;
import dao.DocumentDAO;
import dao.VersionDAO;
import model.User;
import spark.Request;
import spark.Response;


public class UserService extends DocumentService{
	
	private UserDAO userDAO = new UserDAO();
	private DocumentDAO documentDAO = new DocumentDAO();
	private VersionDAO versionDAO = new VersionDAO();
	
	private DocumentService documentservice = new DocumentService();
	private VersionService versionservice = new VersionService();

	
	public int countID = 1;
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;

	
    public Object insert(Request request, Response response) {
		
		//Collect Metadata about the new User from the request
		// ...
		//After that insert the new User Object
		// System.out.println(request.body());	
		// System.out.println(request.queryString());
		String userName = request.queryParams("username");
		String email = request.queryParams("email");
		// String recoveryEmail = request.queryParams("email");
		String userPassword = request.queryParams("password");
		
		String resp = "";
		
		User user = new User(countID++, userName, email, email, userPassword);
		System.out.println(user.toString());
		if(userDAO.insert(user)) {
            resp = "User (" + userName + ") criada!";
            response.status(201); // 201 Created
		} else {
			resp = "User (" + userName + ") n�o criada!";
			response.status(404); // 404 Not found
		}
		
		// -- Precisa preencher html 
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object get(Request request, Response response) {
		
		//Method to return the HTTP request with a Specific User
		// ...
		
		int id = Integer.parseInt(request.params(":id"));		
		User user = (User) userDAO.get(id);
		
		if (user != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			//makeForm( user );
        
		} else {
            response.status(404); // 404 Not found
            String resp = "User " + id + " n�o encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		User user = (User) userDAO.get(id);
		
		if (user != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			//makeForm( user );
			
        } else {
            response.status(404); // 404 Not found
            String resp = "User " + id + " n�o encontrado.";
            
            // -- Precisa preencher html 
    		//makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object update(Request request, Response response) {
		//Method to Update the Metadata from a User
		// ...
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
        String resp = "";       

        if (user != null) {
        	user.setEmail(request.queryParams("email"));
        	userDAO.update(user);
        	response.status(200); // success
            resp = "User (ID " + user.getUserID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "User (ID " + user.getUserID() + ") n�o encontrado!";
        }
        
        // -- Precisa preencher html 
        
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object getAll(Request request, Response response) {
		//makeFormSignUp();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	
	
	public Object insertDocument(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		Object formAux = documentservice.insert(user.getUserID(), request, response);
		return formAux;
	}
	
	public Object getDocument(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		Object formAux = documentservice.get(user.getUserID(), request, response);
		return formAux;
	}
	
	public Object getToUpdateDocument(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		Object formAux = documentservice.getToUpdate(user.getUserID(), request, response);
		return formAux;
	}
	
	public Object updateDocument(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		Object formAux = documentservice.update(user.getUserID(), request, response);
		return formAux;
	}
	
	public Object getAllDocument(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		Object formAux = documentservice.getAll(user.getUserID(), request, response);
		return formAux;
	}
	

}
