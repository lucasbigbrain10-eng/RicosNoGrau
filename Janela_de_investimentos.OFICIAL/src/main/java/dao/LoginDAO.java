package dao;

import com.mycompany.janela_de_investimentos.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean autenticar(String nome, String senha) {
        String sql = "SELECT * FROM login WHERE nome = ? AND senha = ?";
        
        try (Connection con = ConnectionFactory.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // se encontrou usuário
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
