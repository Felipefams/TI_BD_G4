package service;

import java.time.LocalDateTime;
import dao.VersionDAO;
import model.Version;
import spark.Request;
import spark.Response;

public class VersionService {

	private VersionDAO versionDAO = new VersionDAO();

	private String form;

	public VersionService() {
	}

	public Object insert(int documentID, Request request, Response response) {
		String accessLink = request.queryParams("accessLink");
		LocalDateTime creationDate = LocalDateTime.parse(request.queryParams("creationDate"));

		String resp = "";

		Version version = new Version(-1, documentID, creationDate, accessLink);

		if (versionDAO.insert(version) == true) {
			resp = "Version criada!";
			response.status(201); // 201 Created
		} else {
			resp = "Version n�o criada!";
			response.status(404); // 404 Not found
		}

		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
				"<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

	public Object get(int documentID, Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Version version = (Version) versionDAO.get(id);

		if (version != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "Version " + id + " n�o encontrado.";
			form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
					"<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
		}

		return form;
	}

	public Object getToUpdate(int documentID, Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Version version = (Version) versionDAO.get(id);
		if (version != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "Version " + id + " n�o encontrado.";
			form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
					"<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
		}
		return form;
	}

	public Object update(int userID, Request request, Response response) {
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

		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
				"<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

	public Object getAll(int documentID, Request request, Response response) {
		response.header("Content-Type", "text/html");
		response.header("Content-Encoding", "UTF-8");
		return form;
	}

	public Object delete(int documentID, Request request, Response response) {
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
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
				"<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
	}

}
