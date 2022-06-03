package app;

import static spark.Spark.*;

import model.User;


public class Aplicacao {
	
	public static void main(String[] args) {
		port(5789);
		
		staticFiles.location("/public");
		
		get("/", (request, response) -> {
			var user = new User();
			user.setUserName("teste");
			response.body("Esse E um teste get");
			return user;
		});	
		post("/", (request, response) -> {
			return "Esse E um teste post";
		});
		
		// post("/document/insert", (request, response) -> documentService.insert(request, response));
		// post("/document/:id/version/insert", (request, response) -> versionService.insert(request, response));
		
		// get("/document/list/:orderby", (request, response) -> userService.getAll(request, response));
		// get("/document/:id/version/list/:orderby", (request, response) -> versionService.getAll(request, response));
		
		
		// //  Get and Post Document Attributes
		// get("/document/update/:id", (request, response) -> documentService.getToUpdate(request, response));
		// post("/document/update/:id", (request, response) -> documentService.update(request, response));
		
		// // Get and Post Specific Document Version Attributes 
		// get("/document/:id/update/version/:id", (request, response) -> versionService.getToUpdate(request, response));
		// post("/document/:id/update/version/:id", (request, response) -> versionService.update(request, response));
		
		
		// get("/document/delete/:id", (request, response) -> documentService.delete(request, response));
		// get("/document/:id/version/:id", (request, response) -> versionService.delete(request, response));
			
	}
	
}
