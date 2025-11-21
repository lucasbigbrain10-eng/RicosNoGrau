package com.mycompany.janela_de_investimentos;

import com.mycompany.janela_de_investimentos.ConnectionFactory;
import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.conectar();
        if (con != null) {
            System.out.println("Conectado com sucesso!");
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
