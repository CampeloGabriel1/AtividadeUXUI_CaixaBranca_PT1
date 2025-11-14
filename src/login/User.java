package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException; // Necessário para tratamento correto

public class User {
    
    // As variáveis nome e result agora são da instância
    public String nome = "";
    public boolean result = false;

    // 1. Método conectarBD() corrigido minimamente para retornar conexão ou null
    public Connection conectarBD(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // Corrigida a string do driver
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) { 
            // Apenas imprime o erro, como no original, mas de forma legível
            System.err.println("Erro de conexão: " + e.getMessage());
        }
        return conn; // Retorna null em caso de falha, mantendo o risco de NP
    }

    // 2. Método verificarUsuario() com correções estruturais e de fechamento de recursos
    public boolean verificarUsuario(String login, String senha) {
        // Redefine o estado para garantir que cada chamada seja nova
        this.result = false;
        this.nome = "";
        
        Connection conn = conectarBD();
        Statement st = null;
        ResultSet rs = null;

        // [CORRIGIDO: Tratamento de NullPointer - sai se a conexão falhar]
        if (conn == null) {
            return false;
        }

        // Concatenação SQL insegura (mantida conforme o original para evitar segurança)
        String sql = "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "';";
        
        try {
            // Recursos declarados fora para fechar no finally
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            if (rs.next()) {
                this.result = true;
                // [CORRIGIDO: Leitura de nome SOMENTE após rs.next() para evitar exceção]
                this.nome = rs.getString("nome"); 
            }
        } catch (Exception e) {
            // Tratamento de exceção simples, como no original
            System.err.println("Erro na verificação: " + e.getMessage());
        } finally {
            // [CORRIGIDO: Implementação do fechamento de recursos]
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException closeException) {
                System.err.println("Erro ao fechar recursos: " + closeException.getMessage());
            }
        }
        
        return this.result; 
    }
}