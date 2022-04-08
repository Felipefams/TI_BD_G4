# Requisitos Funcionais

| Funcionalidades da página de Login | Funcionalidades da página do Usuário | Funcionalidades da página de Edição de Documento |
| :--------------------------------: | :----------------------------------: | :----------------------------------------------: |
|  Função “Criar Usuário (Sign up)”  |       Função “Criar Documento”       |              Função “Salvar Versão”              |
|      Função “Logar (Sign in)”      |       Função “Abrir Documento”       |               Função “Abrir Versão”              |
|       Função “Esqueci Senha”       |      Função “Renomear Documento”     |        Função “Aviso sobre Fechar página”        |


*	# Funcionalidades da página de Login:
	- Função “Criar Usuário (Sign up)”: Uma função que será presente na página inicial que será encarregada de pegar as informações do novo usuário e salvar no banco de dados como uma nova instancia Usuário.
	- Função “Logar (Sign in)”: Uma função que ficará encarregada de pegar os atributos e-mail e senha e comparar com a tabela usuários para verificar se as informações estão corretar e então logar na página do usuário correspondente.
	- Função “Esqueci Senha”: Uma função que irá utilizar do e-mail, nome de usuário e e-mail de recuperação para gerar uma nova senha para o usuário que esqueceu a própria senha, fazendo a necessária mudança na tabela Usuário 

* #	Funcionalidades da página do Usuário:
  -	Função “Criar Documento”: Uma função que será presente na página do Usuário para gerar uma nova instância Documento sendo ligado pela Foreign Key do Usuário que a criou. E então redirecionará ao Editor de documentos, com o documento sem nenhuma versão salva.
  -	Função “Abrir Documento”: Uma função que ficará encarregada de pegar o documento selecionado e abrir o Editor de Documento, ao abrir o editor a versão mais recente do Documento será copiada ao editor para que o usuário possa continuar seu documento, se o documento não possuir versões salvas o editor de documento estará em branco.
  -	Função “Renomear Documento”: Uma função simples que irá pegar o Documento selecionado e modificar seu nome no atributo de nome do documento na tabela de documentos.

* #	Funcionalidades da página de Edição de Documento:
  - Função “Salvar Versão”: Uma função que será encarregada de gerar novas instâncias de versões para um documento. Ao criar uma nova versão, o texto que está no editor de texto será salvado nesta nova versão e tornara-se “Read-only” para evitar modificações em versões passadas.  
  -	Função “Abrir Versão”: Uma função simples que irá pegar a versão selecionada e abrir uma visualização de seu conteúdo, se a versão não for a atual para o documento em questão ela será “Read-only” e não será editável, e se ela for a atual do documento ela será editável e salvável.
  -	Função “Aviso sobre Fechar página”: Se um usuário tentar, propositalmente ou não, fechar a página do site, um pop-up deve aparecer avisando que se ele sair todas as modificações não salvas serão perdidas.
  -	Correção Ortográfica do Editor de Texto: Aplicação de um API de correção ortográfica para que o usuário, ao escrever, automáticamente receba recomendações de para melhorar a escrita ou corrigir erros.

# Requisitos Não-Funcionais

  * Criptografia Unidirecional: Criptografia em que só da para criptografar, não é possível descriptografar com exemplos sendo crypt, MDS, SHA. Utilizaremos isto para salvar a senha do usuário de maneira segura e com menos risco de ser acessado pelo banco de dados. Para está criptografia funcionar, a senha deve ser criptografada na hora de comparação para verificar o login.
