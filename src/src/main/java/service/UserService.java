package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.UserDAO;
import model.User;
import spark.Request;
import spark.Response;


public class UserService {
	
	private UserDAO UserDAO = new UserDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;

	public UserService() {
		makeFormSignUp();
	}
	
	/*
	public void makeFormSignUp() {
		makeFormSignUp(FORM_INSERT, new User());
	}*/
	
	public void makeFormSignUp() {
		String nomeArquivo = "singIn.html";
		form = "";
		
		String signUp = "";
		
		signUp = "";
		signUp += "<div id=\"loginExistente\">\r\n"
				+ "        <div class=\"container\">\r\n"
				+ "\r\n"
				+ "            <div id=\"login-row\" class=\"row justify-content-center align-items-center\">\r\n"
				+ "                <div id=\"login-column\" class=\"col-md-6 border\" style=\"margin-top: 3rem;\">\r\n"
				+ "                    <div id=\"login-box\" class=\"col-md-12\">\r\n"
				+ "\r\n"
				+ "                        <form id=\"login-form\" class=\"form\" method=\"post\" onsubmit=\"loginUser (this)\">\r\n"
				+ "                            <h3 class=\"text-center \">Novo usuário</h3>\r\n"
				+ "\r\n"
				+ "                            <div class=\"form-group\">\r\n"
				+ "                                <label for=\"login\">Usuário</label><br>\r\n"
				+ "                                <input type=\"text\" name=\"txt_login\" id=\"txt_login\"\r\n"
				+ "                                    placeholder=\"Digite seu login de usuário\" class=\"form-control\">\r\n"
				+ "                            </div>\r\n"
				+ "\r\n"
				+ "                            <div class=\"form-group\">\r\n"
				+ "                                <label for=\"nome\">Nome completo</label><br>\r\n"
				+ "                                <input type=\"text\" name=\"txt_nome\" id=\"txt_nome\" placeholder=\"Digite seu sobrenome\"\r\n"
				+ "                                    class=\"form-control\">\r\n"
				+ "                            </div>\r\n"
				+ "\r\n"
				+ "                            <div class=\"form-group\">\r\n"
				+ "                                <label for=\"email\">email</label><br>\r\n"
				+ "                                <input type=\"email\" name=\"txt_email\" id=\"txt_email\" placeholder=\"Digite seu email\"\r\n"
				+ "                                    class=\"form-control\">\r\n"
				+ "                            </div>\r\n"
				+ "\r\n"
				+ "                            <div class=\"form-group\">\r\n"
				+ "                                <label for=\"senha\">Senha</label><br>\r\n"
				+ "                                <input type=\"password\" name=\"txt_senha\" id=\"txt_senha\" placeholder=\"Digite sua senha\"\r\n"
				+ "                                    class=\"form-control\">\r\n"
				+ "                            </div>\r\n"
				+ "\r\n"
				+ "                            <div class=\"form-group\">\r\n"
				+ "                                <label for=\"senha2\">Confirmação de Senha</label><br>\r\n"
				+ "                                <input type=\"password\" name=\"txt_senha2\" id=\"txt_senha2\" placeholder=\"Confirme a senha\"\r\n"
				+ "                                    class=\"form-control\">\r\n"
				+ "                            </div>\r\n"
				+ "\r\n"
				+ "                            <!--<div class=\"form-group\">\r\n"
				+ "                                <label for=\"mbti\">Personalidade MBTI</label><br>\r\n"
				+ "                                <input type=\"text\" name=\"txt_mbti\" onkeydown=\"upperCaseF(this)\" id=\"txt_mbti\"\r\n"
				+ "                                    placeholder=\"Digite sua Personalidade\" class=\"form-control\">\r\n"
				+ "                                <p id=\"teste\"><a href=\"https://www.16personalities.com/br/teste-de-personalidade\"\r\n"
				+ "                                        target=\"_blank\">Ainda não fez o teste?</a></p>\r\n"
				+ "                            </div>-->\r\n"
				+ "\r\n"
				+ "                        </form>\r\n"
				+ "                        <button type=\"button\" class=\"btn btn-dark\" id=\"btn_salvar\">Salvar</button>\r\n"
				+ "                        <a href=\"./login.html\"><button class=\"btn btn-dark float-right\" id=\"butao\">Já tem\r\n"
				+ "                                conta?</button></a>\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "    <script src=\"./assets/js/login.js\"></script>\r\n"
				+ "    <script>\r\n"
				+ "\r\n"
				+ "        // Declara uma função para processar o formulário de login\r\n"
				+ "        function processaFormLogin(event) {\r\n"
				+ "            // Cancela a submissão do formulário para tratar sem fazer refresh da tela\r\n"
				+ "            event.preventDefault();\r\n"
				+ "\r\n"
				+ "            // Obtem os dados de login e senha a partir do formulário de login\r\n"
				+ "            var username = document.getElementById('username').value;\r\n"
				+ "            var password = document.getElementById('password').value;\r\n"
				+ "\r\n"
				+ "            // Valida login e se estiver ok, redireciona para tela inicial da aplicação\r\n"
				+ "            resultadoLogin = loginUser(username, password);\r\n"
				+ "            if (resultadoLogin) {\r\n"
				+ "                window.location.href = 'index.html';\r\n"
				+ "            }\r\n"
				+ "            else { // Se login falhou, avisa ao usuário\r\n"
				+ "                alert('Usuário ou senha incorretos');\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        /*Testes das informações*/\r\n"
				+ "        function salvaLogin(event) {\r\n"
				+ "            // Cancela a submissão do formulário para tratar sem fazer refresh da tela\r\n"
				+ "            event.preventDefault();\r\n"
				+ "\r\n"
				+ "            // Obtem os dados do formulário\r\n"
				+ "            let login = document.getElementById('txt_login').value;\r\n"
				+ "            let nome = document.getElementById('txt_nome').value;\r\n"
				+ "            let email = document.getElementById('txt_email').value;\r\n"
				+ "            let senha = document.getElementById('txt_senha').value;\r\n"
				+ "            let senha2 = document.getElementById('txt_senha2').value;\r\n"
				+ "\r\n"
				+ "            var letMiudas = /[a-z]/g;\r\n"
				+ "            var letMaiuscula = /[A-Z]/g;\r\n"
				+ "            var numeros = /[0-9]/g;\r\n"
				+ "            var mail = /[@] + [.]/g;\r\n"
				+ "\r\n"
				+ "            if ((login.length <= 4) || (login.length > 15)) {\r\n"
				+ "                alert('Digite um login valido.');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if ((nome.length <= 4) || (nome.length > 35)) {\r\n"
				+ "                alert('Digite um nome valido.');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (nome.match(numeros)) {\r\n"
				+ "                alert('Não coloque números no seu nome');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (senha != senha2) {\r\n"
				+ "                alert('As senhas informadas não conferem.');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if ((email.length <= 6) || (email.length > 45)) {\r\n"
				+ "                alert('Coloque um email valido');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (senha.length == 0) {\r\n"
				+ "                alert('Coloque sua senha');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (senha.length < 8) {\r\n"
				+ "                alert('Minimo de 8 caracteres na senha');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (!senha.match(numeros)) {\r\n"
				+ "                alert('Coloque pelo menos 1 número');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (!senha.match(letMaiuscula)) {\r\n"
				+ "                alert('Coloque pelo menos 1 letra maiuscula');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            if (!senha.match(letMiudas)) {\r\n"
				+ "                alert('Coloque pelo menos 1 letra minuscula');\r\n"
				+ "                return\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            resultadoCadastro = cadastroUser(login, email);\r\n"
				+ "            if (resultadoCadastro) {\r\n"
				+ "                alert('Conta existente, Email e/ou login já logados');\r\n"
				+ "                return;\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            // Adiciona o usuário no banco de dados\r\n"
				+ "            addUser(nome, login, senha, email);\r\n"
				+ "            alert('Usuário salvo com sucesso. Proceda com o login');\r\n"
				+ "\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        // Associa a funçao processaFormLogin  formulário adicionado um manipulador do evento submit\r\n"
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
		
		form = form.replaceFirst("<SIGNUP>", login);
		
	}
	
	/*
	public void makeFormLogin(int orderBy) {
		makeFormLogin(FORM_INSERT, new User(), orderBy);
	}*/
	
	public void makeFormLogin() {
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
		
		form = form.replaceFirst("<LOGIN>", login);
		
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
		makeFormLogin();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}
	
	public Object delete(Request request, Response response) {
		//Method to get all Users of a User
		// ...
	}

}
