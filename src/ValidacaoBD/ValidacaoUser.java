package ValidacaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ConexaoBD.ConexaoBD;

public class ValidacaoUser {
	
	public boolean Validacao(String nomeUser, long numeroUser) {
		String sql = "SELECT user_name, user_number FROM user_name JOIN user_number ON user_name.user_id = user_number.user_id WHERE user_name = ? AND user_number = ?";

		try (Connection connection = ConexaoBD.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomeUser);
			pstmt.setLong(2, numeroUser);
			
			try (ResultSet resultSet = pstmt.executeQuery()) {
				return resultSet.next();
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
