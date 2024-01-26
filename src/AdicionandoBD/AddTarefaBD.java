package AdicionandoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConexaoBD.ConexaoBD;

public class AddTarefaBD {
	public void adicionarTarefa(String titulo, String data, String descricao) {
		String sql = "INSERT INTO task (task_name, task_date, task_description) VALUES (?, ?, ?)";

		try (Connection connection = ConexaoBD.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, titulo);
			pstmt.setDate(2, java.sql.Date.valueOf(data));
			pstmt.setString(3, descricao);
			pstmt.executeUpdate();
			System.out.println("Dados adicionados com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}