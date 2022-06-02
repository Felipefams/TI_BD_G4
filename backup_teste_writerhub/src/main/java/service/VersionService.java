package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.VersionDAO;
import model.Document;
import model.Version;
import spark.Request;
import spark.Response;


public class VersionService {
	
	private VersionDAO versionDAO = new VersionDAO();
	
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
	

    public Object insert(int documentID,Request request, Response response) {
		
		//Collect Metadata about the new User from the request
		// ...
		//After that insert the new User Object
		
		String accessLink = request.queryParams("access-link");
		LocalDateTime creationDate = LocalDateTime.parse(request.queryParams("creation-date"));
		
		
		String resp = "";
		
		Version version = new Version(-1, documentID,creationDate, accessLink);
		
		if(versionDAO.insert(version) == true) {
            resp = "Version criada!";
            response.status(201); // 201 Created
		} else {
			resp = "Version n�o criada!";
			response.status(404); // 404 Not found
		}
		
		// -- Precisa preencher html 
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object get(int documentID,Request request, Response response) {
		
		//Method to return the HTTP request with a Specific User
		// ...
		
		int id = Integer.parseInt(request.params(":id"));		
		Version version = (Version) versionDAO.get(id);
		
		if (version != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			makeForm(version.getVersionID());
        
		} else {
            response.status(404); // 404 Not found
            String resp = "Version " + id + " n�o encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object getToUpdate(int documentID,Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Version version = (Version) versionDAO.get(id);
		
		if (version != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			makeForm(version.getVersionID());
			
        } else {
            response.status(404); // 404 Not found
            String resp = "Version " + id + " n�o encontrado.";
            
            // -- Precisa preencher html 
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object update(int userID,Request request, Response response) {
		//Method to Update the Metadata from a User
		// ...
		int id = Integer.parseInt(request.params(":id"));
		Version version = versionDAO.get(id);
        String resp = "";       

        if (version != null) {
        	version.setAccessLink(request.queryParams("access-link"));
        	versionDAO.update(version);
        	response.status(200); // success
            resp = "Version (ID " + version.getVersionID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Version (ID " + version.getVersionID() + ") n�o encontrado!";
        }
        
        // -- Precisa preencher html 
        
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object getAll(int documentID,Request request, Response response) {
		makeForm();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	
	public Object delete(int documentID,Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Version version = versionDAO.get(id);
        String resp = "";       

        if (version != null) {
            versionDAO.delete(id);
            response.status(200); // success
            resp = "Version (" + id + ") exclu�do!";
        } else {
            response.status(404); // 404 Not found
            resp = "Version (" + id + ") n�o encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}


}
