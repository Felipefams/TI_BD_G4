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

	public boolean insert(Document Document) {
		boolean status = false;
		try {
			String sql = "INSERT INTO Document (descricao, preco, quantidade, datafabricacao, datavalidade) "
					+ "VALUES ('" + Document.getDescricao() + "', "
					+ Document.getPreco() + ", " + Document.getQuantidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(Document.getDataFabricacao()));
			st.setDate(2, Date.valueOf(Document.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Document get(int id) {
		Document Document = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Document WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Document = new Document(rs.getInt("id"), rs.getString("descricao"), (float) rs.getDouble("preco"),
						rs.getInt("quantidade"),
						rs.getTimestamp("datafabricacao").toLocalDateTime(),
						rs.getDate("datavalidade").toLocalDate());
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Document;
	}

	public List<Document> get() {
		return get("");
	}

	public List<Document> getOrderByID() {
		return get("id");
	}

	public List<Document> getOrderByDescricao() {
		return get("descricao");
	}

	public List<Document> getOrderByPreco() {
		return get("preco");
	}

	private List<Document> get(String orderBy) {
		List<Document> Documents = new ArrayList<Document>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Document" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Document p = new Document(rs.getInt("id"), rs.getString("descricao"), (float) rs.getDouble("preco"),
						rs.getInt("quantidade"),
						rs.getTimestamp("datafabricacao").toLocalDateTime(),
						rs.getDate("datavalidade").toLocalDate());
				Documents.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Documents;
	}

	public boolean update(Document Document) {
		boolean status = false;
		try {
			String sql = "UPDATE Document SET descricao = '" + Document.getDescricao() + "', "
					+ "preco = " + Document.getPreco() + ", "
					+ "quantidade = " + Document.getQuantidade() + ","
					+ "datafabricacao = ?, "
					+ "datavalidade = ? WHERE id = " + Document.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(Document.getDataFabricacao()));
			st.setDate(2, Date.valueOf(Document.getDataValidade()));
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
			st.executeUpdate("DELETE FROM Document WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}
