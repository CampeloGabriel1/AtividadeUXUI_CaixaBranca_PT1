package login;

public class App {
    public static void main(String[] args) {
        
        // 1. Instanciar a classe User
        User user = new User();
        
        // 2. Tentar a conexão (Obrigatório, pois verificarUsuario() chama conectarBD())
        System.out.println("Tentando conectar ao BD...");
        java.sql.Connection conn = user.conectarBD();
        
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso.");
        } else {
            System.err.println("Erro de Conexão. Verifique o driver MySQL e as credenciais no User.java.");
            return; // Interrompe se a conexão falhar
        }
        
        // --- 3. Teste do Método verificarUsuario() ---
        
        String testeLogin = "seu_login_aqui"; // Substitua por um login existente no seu DB
        String testeSenha = "sua_senha_aqui"; // Substitua pela senha correspondente
        
        // Executar o método
        boolean logado = user.verificarUsuario(testeLogin, testeSenha);
        
        // Verificar o resultado
        if (logado) {
            System.out.println("\nUsuário autenticado com sucesso!");
            System.out.println("Nome do Usuário: " + user.nome);
        } else {
            System.out.println("\nFalha na autenticação. Usuário ou senha incorretos.");
        }
    }
}