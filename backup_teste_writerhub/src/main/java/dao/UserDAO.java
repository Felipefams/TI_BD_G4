package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends DAO {	

	public UserDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(User user) {
		cleanTable();//apaga um usuario toda vez que insere 
		boolean status = false;
		try {
			String sql = "INSERT INTO public.user (userID, userName, email, recoveryEmail, userPassword) "
		               + "VALUES (" + user.getUserID() + ", '"
		               + user.getUserName() + "', '" + user.getEmail() + "', '" + user.getRecoveryEmail() + "', '" + user.getUserPassword()+ "');";
			// System.out.println(user.toString());
			PreparedStatement st = conexao.prepareStatement(sql);
		    // st.setTimestamp(1, Timestamp.valueOf(user.getDataFabricacao()));
			// st.setDate(2, Date.valueOf(user.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) { 
			//funfando 
			System.out.println("ta dando bosta na linha 42 do userDAO");
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean cleanTable(){
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				st.executeUpdate("DELETE * FROM public.user");
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
	}
	
	public User get(int id) {
		User user = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.user WHERE userID="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 user = new User(rs.getInt("userID"), rs.getString("userName"),  
	                				   rs.getString("email"), 
	        			               rs.getString("recoveryEmail"),
	        			               rs.getString("userPassword"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
	}
	
	public List<User> get() {
		return get("");
	}
	public List<User> getOrderByUserID() {
		return get("userID");		
	}
	public List<User> getOrderByUserName() {
		return get("userName");		
	}
	public List<User> getOrderByEmail() {
		return get("email");		
	}
	public List<User> getOrderByRecoveryEmail(){
		return get("recoveryEmail");
	}
	public List<User> getOrderByUserPassword(){
		return get("userPassword");
	}
	
	private List<User> get(String orderBy) {
		List<User> users = new ArrayList<User>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.user" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	User p = new User(rs.getInt("userID"), rs.getString("userName"),  
	                				   rs.getString("email"), 
	        			               rs.getString("recoveryEmail"),
	        			               rs.getString("userPassword"));
	            users.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return users;
	}
	
	public boolean update(User user) {
		boolean status = false;
		try {  
			String sql = "UPDATE public.user SET userID = '" + user.getUserID() + "', "
					   + "userName = " + user.getUserName() + ", " 
					   + "email = " + user.getEmail() + ","
					   + "recoveryEmail = ?, " 
					   + "userPassword = " + user.getUserPassword() + " " 
					   + "WHERE userID = " + user.getUserID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean autenticar(String login, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.user WHERE email LIKE '" + login + "' AND userPassword LIKE '" + senha  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}
}