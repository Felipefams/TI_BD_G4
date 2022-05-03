/*
esse tipo SERIAL eh um tipo especifico do postgres
equivalente ao ID. ele ja seta o unique e NOT NULL
automaticamente
*/
CREATE TABLE usuario(
	user_id serial PRIMARY KEY,
	user_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	passhas varchar(200) NOT NULL
);

CREATE TABLE documento(
	user_id serial PRIMARY KEY,
	doc_id serial PRIMARY KEY,
	doc_name varchar(50),
	CONSTRAINT fk_usuario 
		FOREIGN KEY (user_id) 
			REFERENCES usuario(user_id)
			ON DELETE SET NULL 
);

CREATE TABLE versao(
	doc_id serial PRIMARY KEY, 
	num_version serial PRIMARY KEY,
	doc_link varchar(300),
	CONSTRAINT fk_doc_id,
		FOREIGN KEY (doc_id)
			REFERENCES usuario(user_id)
			ON DELET SET NULL
);
