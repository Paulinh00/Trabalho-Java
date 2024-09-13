public class Elemento {
    private String id;
    private String nome;
    private String descricao;
    private String dataHora;

    public Elemento(String id, String nome, String descricao, String dataHora) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        if (nome != null) {
            return "ID: " + id + ", nome: " + nome + ", descricao: " + descricao;
        }
        return "ID: " + id + ", Descrição: " + descricao + ", Data e Hora: " + dataHora;
    }
}
