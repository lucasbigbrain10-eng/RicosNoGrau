package dao;

import com.mycompany.janela_de_investimentos.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AcaoDAO {

    public ArrayList<String> listarAcoes() {
        String sql = "SELECT acao, valor FROM acao";
        ArrayList<String> lista = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String nome = rs.getString("acao");
                double valor = rs.getDouble("valor");
                lista.add(nome + " - R$ " + valor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }
}
