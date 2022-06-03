package app;

import static spark.Spark.*;

import model.User;
import service.DocumentService;
//import service.DocumentService;
import service.UserService;
//import service.VersionService;

public class Aplicacao {

	private static UserService userService = new UserService();
	private static DocumentService documentService = new DocumentService();
	// private static DocumentService documentService = new DocumentService();
	// private static VersionService versionService = new VersionService();

	// Talvez colocar outras entidades aqui depois

	public static void main(String[] args) {
		port(2789);

		staticFiles.location("/public");
		// post("/user/login", (request, response) -> userService.insert(request, response));
		post("/user/login", (request, response) -> {
			//por algum motivo esse redirect tem que ficar antes do post
			response.redirect("/user/login/documents");
			userService.insert(request, response);
			return null;
		});
		get("/user/login/documents", (request, response) -> {
			response.redirect("/login.html");	
			// documentService.getAll(1, request, response);
			return null;
		});//"teste");

		post("/user/login/documents/list", (request, response) -> {
			response.redirect("/user/login/documents/list/main");
			return null;
		});

		get("/user/login/documents/list/main", (request, response) -> {
			response.redirect("/form.html");
			return null;
		});

		post("/user/login/documents/list/main/1", (request, response) -> {
			documentService.insert(1, request, response);
			return null;
		});		

	}

}
