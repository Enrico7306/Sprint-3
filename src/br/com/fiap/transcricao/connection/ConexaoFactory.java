package br.com.fiap.transcricao.connection;

/**
 * Classe de conexão com o banco de dados.
 *
 * O sistema guarda os dados em memória (nas classes Repository), então
 * esta classe representa o ponto único onde ficam usuário e senha do
 * banco e onde a conexão é "aberta" — é por aqui que, se um dia o
 * projeto passar a usar um banco de dados real (JDBC), a troca seria
 * feita, sem precisar mexer nos repositories nem no service.
 */
public class ConexaoFactory {

    private static final String USUARIO = "sa";
    private static final String SENHA = "fiap2026";

    private static boolean conectado = false;

    public static boolean conectar() {
        if (!conectado) {
            System.out.println("Conectando ao banco de dados com o usuário '" + USUARIO + "'...");
            conectado = true;
        }
        return conectado;
    }

    public static boolean isConectado() {
        return conectado;
    }
}
