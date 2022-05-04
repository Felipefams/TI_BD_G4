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
	
	public boolean insert(Version version) {
		//done
		boolean status = false;
		try {
			String sql = "INSERT INTO Version (documentID, versionID, creationDate, accessLink) "
		               + "VALUES ('" + version.getdocumentID() + "', "
		               + version.getVersionID() + ", " + version.getCreationDate() + ");";//", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(version.getCreationDate()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Version get(int id) {
		Version v = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Version WHERE versionID="+id;
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	 v = new Version(rs.getInt("documentID"), rs.getInt("versionID"),
						 		 rs.getTimestamp("creationDate").toLocalDateTime(),
	        			         rs.getString("accessLink"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return v;
	}
	
	
	public List<Version> get() {
		return get("");
	}

	/*	
	public List<Version> getOrderByID() {
		return get("id");		
	}
	public List<Version> getOrderByDescricao() {
		return get("descricao");		
	}
	public List<Version> getOrderByPreco() {
		return get("preco");		
	}*/
	
	
	private List<Version> get(String orderBy) {
		List<Version> Versions = new ArrayList<Version>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Version" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
				Version v = new Version(rs.getInt("documentID"), rs.getInt("versionID"),
						 		 rs.getTimestamp("CreationDate").toLocalDateTime(),
	        			         rs.getString("AccessLink"));
				/*
				pedaco do max pra debug
	        	Version p = new Version(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"),
	        			                rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			                rs.getDate("datavalidade").toLocalDate());
				*/
	            Versions.add(v);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Versions;
	}
	
	
	public boolean update(Version version) {
		boolean status = false;
		try {  
			String sql = "UPDATE Version SET documentID = '" + version.getdocumentID() + "', "
					   + "versionID = " + version.getVersionID() + ", " 
					   + "creationDate = " + version.getCreationDate() + ","
					   + "accessLink = " + version.getAccessLink();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(version.getCreationDate()));
			// st.setDate(2, Date.valueOf(version.getDataValidade()));
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
			st.executeUpdate("DELETE FROM Version WHERE versionID = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
