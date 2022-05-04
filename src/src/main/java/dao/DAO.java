package dao;


import java.sql.*;
/*Isso aqui ta pronto, nao precisa mexer nao, so na hora de fazer a conexao*/
public class DAO{
    protected Connection conexao;


    public DAO(){ conexao = null;  }


    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "writerhub";
        int port = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + port +"/" + mydatabase;
        String username = " ";
        String password = " ";
        boolean status = false;


        try {
            Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conex�o efetuada com o postgres!");

        }catch (ClassNotFoundException e) { 
			System.err.println("Conex�o N�O efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- " + e.getMessage());
		}
        return status;
    }

    public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}  
}