package com.mycompany.janela_de_investimentos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/rng?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "pedrin123@00//.wA";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}
