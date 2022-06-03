package service;

import java.util.List;

import com.google.gson.Gson;

import dao.UserDAO;
import model.User;
import spark.Request;
import spark.Response;

public class UserService {
	private UserDAO userDAO = new UserDAO();

	public int countID = 1;
	private String form;

	public boolean autenticar(String login, String senha) {
		return userDAO.autenticar(login, senha);
	}

	public Object insert(Request request, Response response) {
		// serializar o json da request como objeto USER
		Gson gson = new Gson();
		User usuarioASalvar = gson.fromJson(request.body(), User.class);
		String resp;
		if (userDAO.insert(usuarioASalvar)) {
			resp = "User (" + usuarioASalvar.getUserName() + ") criada!";
			response.status(201); // 201 Created
		} else {
			resp = "User (" + usuarioASalvar.getUserName() + ") não criada!";
			response.status(404); // 404 Not found
		}
		return resp;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = (User) userDAO.get(id);
		if (user != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "User " + id + " não encontrado.";
		}

		return form;
	}

	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = (User) userDAO.get(id);
		if (user != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "User " + id + " n�o encontrado.";
		}
		return form;
	}

	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
		String resp = "";
		if (user != null) {
			Gson gson = new Gson();
			User novosDadosUsuario = gson.fromJson(request.body(), User.class);
			user.setEmail(novosDadosUsuario.getEmail());
			userDAO.update(user);
			response.status(200); // success
			resp = "User (ID " + user.getUserID() + ") atualizado!";
		} else {
			response.status(404); // 404 Not found
			resp = "User (ID " + user.getUserID() + ") n�o encontrado!";
		}
		return resp;
	}

	public List<User> getAll(Request request, Response response) {
		return userDAO.getAll();
	}

}
