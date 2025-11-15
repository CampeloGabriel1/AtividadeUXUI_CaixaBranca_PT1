package login;

public class App {
    public static void main(String[] args) {
        
        User user = new User();
        
        System.out.println("Tentando conectar ao BD...");
        java.sql.Connection conn = user.conectarBD();
        
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso.");
        } else {
            System.err.println("Erro de Conexão. Verifique o driver MySQL e as credenciais no User.java.");
            return;
        }
        
        // --- 3. Teste do Método verificarUsuario() ---
        
        String testeLogin = "seu_login_aqui"; 
        String testeSenha = "sua_senha_aqui";
        
        boolean logado = user.verificarUsuario(testeLogin, testeSenha);
        
        if (logado) {
            System.out.println("\nUsuário autenticado com sucesso!");
            System.out.println("Nome do Usuário: " + user.nome);
        } else {
            System.out.println("\nFalha na autenticação. Usuário ou senha incorretos.");
        }
    }
}