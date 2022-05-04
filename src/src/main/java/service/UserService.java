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
	
		//by Constantino
		String nomeArquivo = "login.html";
		form = "";
		
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String login = "";
		
		login += "\t<div id=\"login\">";
		login += "\t<div class=\"container\">";
		login += "\t<div id=\"login-row\" class=\"row justify-content-center align-items-center\">";
		login += "\t<div id=\"login-column\" class=\"col-md-6\">";
		login += "\t<div id=\"login-box\" class=\"col-md-12\">";
		login += "\t<div id=\"login-box\">";
		login += "\t<form id=\"login-form\" class=\"form\" method=\"post\" onsubmit=\"loginUser (this)\">";
		login += "\t<h3 class=\"text-center\">Login</h3>";
		login += "\t<div class=\"form-group\">";
		login += "\t<label for=\"username\">Usuário</label><br>";
		login += "\t<input type=\"text\" name=\"username\" id=\"username\" class=\"form-control\" autocomplete=\"off\">";
		login += "\t</div>";
		login += "\t<div class=\"form-group\">";
		login += "\t<label for=\"password\">Senha</label><br>";
		login += "\t<input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\">";
		login += "\t</div>";
		login += "\t<div class=\"form-group text-right\">";
		login += "\t<input class=\"btn btn-dark\" type=\"submit\" name=\"submit\" value=\"Entrar\">";
		login += "\t</div>";
		login += "\t</form>";
		login += "\t<p><a href=\"novoLogin.html\"><button class=\"btn btn-dark\">Nova conta?</button></a></p>";
		login += "\t</div>";
		login += "\t</div>";
		login += "\t</div>";
		login += "\t</div>";
		login += "\t</div>";
		login += "\t</div>";
		login += "\t<script src=\"./TI_BD_G4/src/src/main/resources/public/assets/js/login.js\"></script>";
		login += "\t<script>";
		login += "\tfunction processaFormLogin(event) {";
		login += "\tevent.preventDefault();";
		login += "\tvar username = document.getElementById('username').value;";
		login += "\tvar password = document.getElementById('password').value;";
		login += "\tif (resultadoLogin) {";
		login += "\twindow.location.href = 'main.html';}";
		login += "\telse { // Se login falhou, avisa ao usuário";
		login += "\talert('Usuário ou senha incorretos');\r\n"
				+ "            }\r\n"
				+ "        }";
		login += "        // Associa a funçao processaFormLogin  formulário adicionado um manipulador do evento submit\r\n"
				+ "        document.getElementById('login-form').addEventListener('submit', processaFormLogin);\r\n"
				+ "\r\n"
				+ "        // Associar salvamento ao botao\r\n"
				+ "        document.getElementById('btn_salvar').addEventListener('click', salvaLogin);\r\n"
				+ "\r\n"
				+ "    </script>\r\n"
				+ "\r\n"
				+ "    <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"\r\n"
				+ "        integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>\r\n"
				+ "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"\r\n"
				+ "        integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>\r\n"
				+ "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"\r\n"
				+ "        integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>";
		
		form = form.replaceFirst("<login>", login);
		
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
