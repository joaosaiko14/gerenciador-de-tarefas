package AdicionandoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConexaoBD.ConexaoBD;

public class AddUsuarioBD {

	public void adcNumAndUser(String nome, Long numero) {
		
		String sqlPK = "INSERT INTO user_name (user_name) VALUES (?)";

		try (Connection connection = ConexaoBD.getConnection();
				PreparedStatement stmtInsertReferenciada = connection.prepareStatement(sqlPK,
						Statement.RETURN_GENERATED_KEYS)) {
			stmtInsertReferenciada.setString(1, nome);
			int linhasAfetadas = stmtInsertReferenciada.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet generateKeys = stmtInsertReferenciada.getGeneratedKeys();
				if (generateKeys.next()) {
					int chavePrimaria = generateKeys.getInt(1);

					String sqlFK = "INSERT INTO user_number (user_number, user_id) VALUES (?, ?)";

					try (PreparedStatement stmtInsertEstrangeira = connection.prepareStatement(sqlFK)) {
						stmtInsertEstrangeira.setLong(1, numero);
						stmtInsertEstrangeira.setInt(2, chavePrimaria);

						stmtInsertEstrangeira.executeUpdate();

						System.out.println("Numero adicionados com sucesso!");
					}
				}
			}
			System.out.println("Nome adicionados com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
