import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivo {

    public static Pilha lerHistorico(String caminhoArquivo) {
        Pilha historicoPilha = new Pilha();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().startsWith("new Elemento")) {
                    String conteudo = linha.substring(linha.indexOf('(') + 1, linha.indexOf(')'));
                    String[] partes = conteudo.split(",\\s*");
                    String id = partes[0].replace("\"", "");
                    String descricao = partes[1].replace("\"", "");
                    String dataHora = partes[2].replace("\"", "");
                    historicoPilha.push(new Elemento(id, null, descricao, dataHora)); // Nome é null para o histórico
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de histórico: " + e.getMessage());
        }
        return historicoPilha;
    }

    public static Fila lerFilaAtendimento(String caminhoArquivo) {
        Fila filaAtendimento = new Fila();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().startsWith("new Elemento")) {
                    String conteudo = linha.substring(linha.indexOf('(') + 1, linha.indexOf(')'));
                    String[] partes = conteudo.split(",\\s*");
                    String id = partes[0].replace("\"", "");
                    String nome = partes[1].replace("\"", "");
                    String descricao = partes[2].replace("\"", "");
                    filaAtendimento.enfileirar(new Elemento(id, nome, descricao, null)); // Data e hora é null para a fila
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de fila de atendimento: " + e.getMessage());
        }
        return filaAtendimento;
    }

    public static void main(String[] args) {
        // Carregar o histórico (Pilha)
        Pilha historico = lerHistorico("src/historico.txt");
        System.out.println("Histórico de Solicitações:");
        historico.exibirPilha();

        // Carregar a fila de atendimento (Fila)
        Fila filaAtendimento = lerFilaAtendimento("src/filadeatendimento.txt");
        System.out.println("\nFila de Atendimento:");
        filaAtendimento.exibirFila();
    }
}
