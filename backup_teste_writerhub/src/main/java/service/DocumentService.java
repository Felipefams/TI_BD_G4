package service;

import java.util.Scanner;
import java.util.StringTokenizer;

import com.twitter.chill.Base64.InputStream;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import dao.DocumentDAO;
import dao.VersionDAO;
import model.Document;
import model.User;
import spark.Request;
import spark.Response;


public class DocumentService extends VersionService{
	
	private DocumentDAO documentDAO = new DocumentDAO();
	private VersionDAO versionDAO = new VersionDAO();
	
	private  VersionService versionservice = new VersionService();
	
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_DOC_ID = 1;
	private final int FORM_ORDERBY_DOC_NAME = 2;
	private final int FORM_ORDERBY_DATE_CREATION = 3;
	
	public DocumentService() {
		makeFormLstDoc();
	}
	
	public void makeFormLstDoc() {
		makeFormLstDoc(FORM_INSERT, new Document(), FORM_ORDERBY_DOC_NAME);
	}
	
	public void makeForm(int orderBy) {
		makeFormLstDoc(FORM_INSERT, new Document(), orderBy);
	}
	
	public void makeFormEditor(int type, Document document, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		
		String newEdit = "";
		newEdit += "<div class=\"newPost\">\r\n"
				+ "  <h3>Add New Post</h3>\r\n"
				+ "  <input type=\"text\" placeholder=\"Enter title here\">\r\n"
				+ "  <div class=\"toolbar\">\r\n"
				+ "    <button type=\"button\" data-func=\"bold\"><i class=\"fa fa-bold\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"italic\"><i class=\"fa fa-italic\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"underline\"><i class=\"fa fa-underline\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"justifyleft\"><i class=\"fa fa-align-left\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"justifycenter\"><i class=\"fa fa-align-center\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"justifyright\"><i class=\"fa fa-align-right\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"insertunorderedlist\"><i class=\"fa fa-list-ul\"></i></button>\r\n"
				+ "    <button type=\"button\" data-func=\"insertorderedlist\"><i class=\"fa fa-list-ol\"></i></button>\r\n"
				+ "    <div class=\"customSelect\">\r\n"
				+ "      <select data-func=\"fontname\">\r\n"
				+ "        <optgroup label=\"Serif Fonts\">\r\n"
				+ "          <option value=\"Bree Serif\">Bree Serif</option>\r\n"
				+ "          <option value=\"Georgia\">Georgia</option>\r\n"
				+ "           <option value=\"Palatino Linotype\">Palatino Linotype</option>\r\n"
				+ "          <option value=\"Times New Roman\">Times New Roman</option>\r\n"
				+ "        </optgroup>\r\n"
				+ "        <optgroup label=\"Sans Serif Fonts\">\r\n"
				+ "          <option value=\"Arial\">Arial</option>\r\n"
				+ "          <option value=\"Arial Black\">Arial Black</option>\r\n"
				+ "          <option value=\"Asap\" selected>Asap</option>\r\n"
				+ "          <option value=\"Comic Sans MS\">Comic Sans MS</option>\r\n"
				+ "          <option value=\"Impact\">Impact</option>\r\n"
				+ "          <option value=\"Lucida Sans Unicode\">Lucida Sans Unicode</option>\r\n"
				+ "          <option value=\"Tahoma\">Tahoma</option>\r\n"
				+ "          <option value=\"Trebuchet MS\">Trebuchet MS</option>\r\n"
				+ "          <option value=\"Verdana\">Verdana</option>\r\n"
				+ "        </optgroup>\r\n"
				+ "        <optgroup label=\"Monospace Fonts\">\r\n"
				+ "          <option value=\"Courier New\">Courier New</option>\r\n"
				+ "          <option value=\"Lucida Console\">Lucida Console</option>\r\n"
				+ "        </optgroup>\r\n"
				+ "      </select>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"customSelect\">\r\n"
				+ "      <select data-func=\"formatblock\">\r\n"
				+ "        <option value=\"h1\">Heading 1</option>\r\n"
				+ "        <option value=\"h2\">Heading 2</option>\r\n"
				+ "        <option value=\"h4\">Subtitle</option>\r\n"
				+ "        <option value=\"p\" selected>Paragraph</option>\r\n"
				+ "      </select>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"editor\" contenteditable></div>\r\n"
				+ "  <div class=\"buttons\">\r\n"
				+ "    <!--<button type=\"button\">save draft</button>-->\r\n"
				+ "    <button data-func=\"clear\" type=\"button\">clear</button>\r\n"
				+ "    <button data-func=\"save\" type=\"button\">save</button>\r\n"
				+ "  </div>\r\n"
				+ "</div>\r\n"
				+ "<!-- partial -->\r\n"
				+ "  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>\r\n"
				+ "  <script  src=\"./assets/js/editor.js\"></script>\r\n"
				+ "  <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>";
	
		
	}
	
	public void makeFormLstDoc(int tipo, Document document, int orderBy){
		String nomeArquivo = "src/main/resources/public/form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String lstDoc = "";
		if(tipo != FORM_INSERT) {
			lstDoc += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/produto/list/1\">Novo Produto</a></b></font></td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t</table>";
			lstDoc += "\t<br>";			
		}
		String action = "/user/login/documents/list/main/1";
	
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String name, buttonLabel;
			LocalDateTime data;

			if (tipo == FORM_INSERT){
				// action += "";
				name = "Inserir Produto";
				data = document.getCreationDate();
				buttonLabel = "Inserir";
			} else {
				// action += "update/" + document.getDocumentID();
				name = "Atualizar Produto (ID " + document.getDocumentID() + ")";
				data = document.getCreationDate();
				buttonLabel = "Atualizar";
			}

			lstDoc += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			lstDoc += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td>&nbsp;Descri��o: <input class=\"input--register\" type=\"text\" name=\"data\" value=\""+ data +"\"></td>";
			lstDoc += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ document.getDocName() +"\"></td>";
			lstDoc += "\t\t\t<td>Quantidade: <input class=\"input--register\" type=\"text\" name=\"ID\" value=\""+ document.getDocumentID() +"\"></td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td>&nbsp;Data de fabrica��o: <input class=\"input--register\" type=\"text\" name=\"dataCriacao\" value=\""+ document.getCreationDate().toString() + "\"></td>";
			lstDoc += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t</table>";
			lstDoc += "\t</form>";

		} else if (tipo == FORM_DETAIL){
			lstDoc += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar documento (ID " + document.getDocumentID() + ")</b></font></td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td>&nbsp;Descri��o: "+ document.getDocName() +"</td>";
			lstDoc += "\t\t\t<td>Quantidade: "+ document.getDocumentID() +"</td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t\t<tr>";
			lstDoc += "\t\t\t<td>&nbsp;Data de fabrica��o: "+ document.getCreationDate().toString() + "</td>";
			lstDoc += "\t\t\t<td>&nbsp;</td>";
			lstDoc += "\t\t</tr>";
			lstDoc += "\t</table>";

		}else {
			System.out.println("ERRO! Tipo n�o identificado " + tipo);
		}
		form = form.replaceFirst("<UM-PRODUTO>", lstDoc);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");

		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Rela��o de Produtos</b></font></td></tr>\n" +
			"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
			"\n<tr>\n" + 
			"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DOC_ID + "\"><b>ID</b></a></td>\n" +
			"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DOC_NAME + "\"><b>Descri��o</b></a></td>\n" +
			"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DATE_CREATION + "\"><b>Pre�o</b></a></td>\n" +
			"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
			"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
			"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
			"</tr>\n";


		List<Document> documentos;
		if (orderBy == FORM_ORDERBY_DOC_ID) {               documentos = documentDAO.getOrderByDocumentID();
		} else if (orderBy == FORM_ORDERBY_DOC_NAME) {		documentos = documentDAO.getOrderByDocName();
		} else if (orderBy == FORM_ORDERBY_DATE_CREATION) {	documentos = documentDAO.getOrderByCreationDate();
		} else {											documentos = documentDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Document d : documentos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + d.getDocumentID() + "</td>\n" +
            		  "\t<td>" + d.getDocName() + "</td>\n" +
            		  "\t<td>" + d.getCreationDate() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/document/" + d.getDocumentID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/document/update/" + d.getDocumentID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + d.getDocumentID() + "', '" + d.getDocName() + "', '" + d.getCreationDate() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-DOC>", list);
		try(FileWriter fw = new FileWriter(nomeArquivo)){
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(form);
			bw.close();
		} catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}
	}
	
    public Object insert(int userID,Request request, Response response) {
		
		//Collect Metadata about the new User from the request
		// ...
		//After that insert the new User Object
		
		String docName = request.queryParams("doc-name");
		LocalDateTime creationDate = LocalDateTime.parse(request.queryParams("creation-date"));
		
		String resp = "";
		
		Document document = new Document(-1, userID,docName, creationDate);
		
		if(documentDAO.insert(document) == true) {
            resp = "Document (" + docName + ") criada!";
            response.status(201); // 201 Created
		} else {
			resp = "Document (" + docName + ") não criada!";
			response.status(404); // 404 Not found
		}
		
		// -- Precisa preencher html 
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object get(int userID,Request request, Response response) {
		
		//Method to return the HTTP request with a Specific User
		// ...
		
		int id = Integer.parseInt(request.params(":id"));		
		Document document = (Document) documentDAO.get(id);
		
		if (document != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			makeForm(document.getDocumentID());
        
		} else {
            response.status(404); // 404 Not found
            String resp = "User " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object getToUpdate(int userID,Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Document document = (Document) documentDAO.get(id);
		
		if (document != null) {
			response.status(200); // success
			
			// -- Precisa preencher html 
			makeForm(document.getDocumentID());
			
        } else {
            response.status(404); // 404 Not found
            String resp = "Document " + id + " não encontrado.";
            
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
		Document document = documentDAO.get(id);
        String resp = "";       

        if (document != null) {
        	document.setDocName(request.queryParams("doc-name"));
        	documentDAO.update(document);
        	response.status(200); // success
            resp = "Document (ID " + document.getDocumentID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Document (ID " + document.getDocumentID() + ") não encontrado!";
        }
        
        // -- Precisa preencher html 
        
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object getAll(int userID,Request request, Response response) {
		makeFormLstDoc();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Document document = documentDAO.get(id);
        String resp = "";       

        if (document != null) {
            documentDAO.delete(id);
            response.status(200); // success
            resp = "Document (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Document (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	
	public Object insertVersion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentDAO.get(id);
		Object formAux = versionservice.insert(document.getDocumentID(), request, response);
		return formAux;
	}
	
	public Object getVersion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentDAO.get(id);
		Object formAux = versionservice.get(document.getDocumentID(), request, response);
		return formAux;
	}
	
	public Object getToUpdateVersion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentDAO.get(id);
		Object formAux = versionservice.getToUpdate(document.getDocumentID(), request, response);
		return formAux;
	}
	
	public Object updateVersion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentDAO.get(id);
		Object formAux = versionservice.getToUpdate(document.getDocumentID(), request, response);
		return formAux;
	}
	
	public Object getAllVersion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Document document = documentDAO.get(id);
		Object formAux = versionservice.getAll(document.getDocumentID(), request, response);
		return formAux;
	}
	

}
