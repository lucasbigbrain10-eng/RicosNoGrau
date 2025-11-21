package dao;

import com.mycompany.janela_de_investimentos.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransacaoDAO {

    public void registrar(int idUsuario, int idAcao, int quantidade, String tipo) {
        String sql = "INSERT INTO transacao (id_usuario, id_acao, quantidade, tipo) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idAcao);
            stmt.setInt(3, quantidade);
            stmt.setString(4, tipo);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

