package dao;

import model.Version;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class VersionDAO extends DAO {	
	public VersionDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Version Version) {
		boolean status = false;
		try {
			String sql = "INSERT INTO Version (descricao, preco, quantidade, datafabricacao, datavalidade) "
		               + "VALUES ('" + Version.getDescricao() + "', "
		               + Version.getPreco() + ", " + Version.getQuantidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(Version.getDataFabricacao()));
			st.setDate(2, Date.valueOf(Version.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Version get(int id) {
		Version Version = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Version WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 Version = new Version(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	                				   rs.getInt("quantidade"), 
	        			               rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			               rs.getDate("datavalidade").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Version;
	}
	
	
	public List<Version> get() {
		return get("");
	}

	
	public List<Version> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Version> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Version> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Version> get(String orderBy) {
		List<Version> Versions = new ArrayList<Version>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Version" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Version p = new Version(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"),
	        			                rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			                rs.getDate("datavalidade").toLocalDate());
	            Versions.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Versions;
	}
	
	
	public boolean update(Version Version) {
		boolean status = false;
		try {  
			String sql = "UPDATE Version SET descricao = '" + Version.getDescricao() + "', "
					   + "preco = " + Version.getPreco() + ", " 
					   + "quantidade = " + Version.getQuantidade() + ","
					   + "datafabricacao = ?, " 
					   + "datavalidade = ? WHERE id = " + Version.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(Version.getDataFabricacao()));
			st.setDate(2, Date.valueOf(Version.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Version WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}