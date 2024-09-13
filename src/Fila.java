public class Fila {
    private Node frente;
    private Node tras;

    public Fila() {
        this.frente = null;
        this.tras = null;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public void enfileirar(Elemento elemento) {
        Node novoNode = new Node();
        novoNode.setElemento(elemento);

        if (isEmpty()) {
            frente = novoNode;
            tras = novoNode;
        } else {
            tras.setProximo(novoNode);
            novoNode.setAnterior(tras);
            tras = novoNode;
        }
    }

    public Elemento desenfileirar() throws Exception {
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }
        Elemento elemento = frente.getElemento();
        frente = frente.getProximo();

        if (frente == null) {
            tras = null;
        } else {
            frente.setAnterior(null);
        }
        return elemento;
    }

    // Método para obter o nó da frente da fila
    public Node getFrente() {
        return frente;
    }

    public void exibirFila() {
        Node atual = frente;
        if (isEmpty()) {
            System.out.println("Fila vazia.");
        } else {
            while (atual != null) {
                System.out.println(atual.getElemento());
                atual = atual.getProximo();
            }
        }
    }
}
