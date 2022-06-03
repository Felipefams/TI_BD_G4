package dao;

import model.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO extends DAO {
	public DocumentDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public String nextVal(){
		try {
			String sql = "select nextval('seq_document') as teste;";
			System.out.println(sql);
			PreparedStatement st = conexao.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			String ans = null;
			if(rs.next()){
				ans = rs.getString("teste");
			}
			st.close();
			return ans; //resposta;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean insert(Document document) {
		boolean status = false;
		try {
			String nextVal = nextVal();
			if (nextVal == null) {
				throw new Exception("Invalid sequence");
			}
			String sql = "INSERT INTO public.document (documentID, userID, docName, creationDate) "
					+ "VALUES ('" + nextVal + "', "
					+ document.getUserID() + ", " + document.getDocName()
					+ ", " + document.getCreationDate() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public Document get(int id) {
		Document document = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.document WHERE documentID=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				document = new Document(
						rs.getInt("documentID"),
						rs.getInt("userID"),
						rs.getString("docName"),
						rs.getDate("creationDate"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return document;
	}

	public List<Document> get() {
		return get("");
	}

	public List<Document> getOrderByDocumentID() {
		return get("documentID");
	}

	public List<Document> getOrderByUserID() {
		return get("userID");
	}

	public List<Document> getOrderByDocName() {
		return get("docName");
	}

	public List<Document> getOrderByCreationDate() {
		return get("creationDate");
	}

	private List<Document> get(String orderBy) {
		List<Document> documents = new ArrayList<Document>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.document" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Document document = new Document(
					rs.getInt("documentID"),
					rs.getInt("userID"),
					rs.getString("docName"),
					rs.getDate("creationDate"));
				documents.add(document);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return documents;
	}

	public boolean update(Document document) {
		boolean status = false;
		try {
			String sql = "UPDATE public.document SET documentID = '" + document.getDocumentID() + "', "
					+ "userID = " + document.getUserID() + ", "
					+ "docName = " + document.getDocName() + ","
					+ "creationDate = " + document.getCreationDate() + "WHERE documentID = " + document.getDocumentID();
			PreparedStatement st = conexao.prepareStatement(sql);
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
			st.executeUpdate("DELETE FROM public.document WHERE documentID = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}
