package service;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import dao.DocumentDAO;
import model.Document;
import spark.Request;
import spark.Response;

public class DocumentoService {
	private DocumentDAO documentoDAO = new DocumentDAO();

	public int countID = 1;
	private String form;

	public Object insert(Request request, Response response) {
		// serializar o json da request como objeto DOCUMENT
		Gson gson = new Gson();
		Document documentoAoSalvar = gson.fromJson(request.body(), Document.class);
		String resp;
		if (documentoDAO.insert(documentoAoSalvar)) {
			resp = "Document (" + documentoAoSalvar.getDocName() + ") criada!";
			response.status(201); // 201 Created
		} else {
			resp = "Document (" + documentoAoSalvar.getDocName() + ") não criado!";
			response.status(404); // 404 Not found
		}
		return resp;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = (Document) documentoDAO.get(id);
		if (document != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "Document " + id + " não encontrado.";
		}

		return form;
	}

	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document documnet = (Document) documentoDAO.get(id);
		if (documnet != null) {
			response.status(200); // success
		} else {
			response.status(404); // 404 Not found
			String resp = "Document " + id + " não encontrado.";
		}
		return form;
	}

	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentoDAO.get(id);
		String resp = "";
		if (document != null) {
			Gson gson = new Gson();
			Document novosDadosDocument = gson.fromJson(request.body(), Document.class);
			document.setDocName(novosDadosDocument.getDocName());
			document.setUserID(novosDadosDocument.getUserID());
			document.setcreationDate(new Date());
			documentoDAO.update(document);
			response.status(200); // success
			resp = "Document (ID " + document.getUserID() + ") atualizado!";
		} else {
			response.status(404); // 404 Not found
			resp = "Document (ID " + document.getUserID() + ") n�o encontrado!";
		}
		return resp;
	}

	public List<Document> getAll(Request request, Response response) {
		return documentoDAO.get();
	}

}
