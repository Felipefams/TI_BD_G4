package dao;

import model.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
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

	public boolean insert(Document document) {
		boolean status = false;
		try {
			String sql = "INSERT INTO document (documentID, userID, docName, creationDate) "
					+ "VALUES ('" + document.getDocumentID() + "', "
					+ document.getUserID() + ", " + document.getDocName()
					+ ", " + document.getCreationDate() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(document.getCreationDate()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Document get(int id) {
		Document document = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM document WHERE documentID=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				document = new Document(rs.getInt("documentID"),
						rs.getInt("userID"), 
						rs.getString("docName"),
						rs.getTimestamp("creationDate").toLocalDateTime());
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

	private List<Document> get(String orderBy) {
		List<Document> documents = new ArrayList<Document>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM document" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Document p = new Document(rs.getInt("documentID"),
				rs.getInt("userID"), 
				rs.getString("docName"),
				rs.getTimestamp("creationDate").toLocalDateTime());
				documents.add(p);
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
			String sql = "UPDATE document SET documentID = '" + document.getDocumentID() + "', "
					+ "userID = " + document.getUserID() + ", "
					+ "docName = " + document.getDocName() + ","
					+ "creationDate = "+ document.getCreationDate() + "WHERE documentID = " + document.getDocumentID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(document.getCreationDate()));
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
			st.executeUpdate("DELETE FROM document WHERE documentID = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}
