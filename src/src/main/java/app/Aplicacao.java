package app;

import static spark.Spark.*;

import com.google.gson.Gson;

import model.Login;
import model.User;
import service.DocumentoService;
import service.UserService;

public class Aplicacao {

	private static UserService userService = new UserService();

	private static DocumentoService documentService = new DocumentoService();

	public static void main(String[] args) {
		port(2727);

		// staticFiles.location("/public");

		get("/hello", (req, res) -> "Hello World");

		// recuperar todos os usuarios
		post("/log-in", (request, response) -> {
			try{
				Gson gson = new Gson();
				Login login = gson.fromJson(request.body(), Login.class);
				if (userService.autenticar(login.getEmail(),login.getUserPassword())){
					response.status(200); // http ok
					return "ok";
				}
				response.status(500);
				return "ok";
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		// recuperar todos os usuarios
		get("/users/", (request, response) -> {
			return userService.getAll(request, response);
		});

		// recuperar usuario por id
		get("/users/:id", (request, response) -> {
			try{
				System.out.println(request.params(":id"));
				return userService.get(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		// salvar usuario
		post("/users/", (request, response) -> {
			try{
				System.out.println(request.body());
				return userService.insert(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		// alterar usuario
		put("/user/:id", (request, response) -> {
			try{
				System.out.println(request.params(":id"));
				System.out.println(request.body());
				return userService.update(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		//------------------------------ DOCUMENT

		// recuperar todos os usuarios
		get("/documents/", (request, response) -> {
			return documentService.getAll(request, response);
		});

		// recuperar usuario por id
		get("/documents/:id", (request, response) -> {
			try{
				System.out.println(request.params(":id"));
				return documentService.get(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		// salvar usuario
		post("/documents/", (request, response) -> {
			try{
				System.out.println(request.body());
				return documentService.insert(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});

		// alterar usuario
		put("/document/:id", (request, response) -> {
			try{
				System.out.println(request.params(":id"));
				System.out.println(request.body());
				return documentService.update(request, response);
			} catch (Exception e) {
				response.status(500);
				return e.getMessage();
			}
		});
	}

}
