public class Pilha {
    private Node topo;

    public Pilha() {
        this.topo = null;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public void push(Elemento elemento) {
        Node novoNode = new Node();
        novoNode.setElemento(elemento);
        novoNode.setProximo(topo);
        topo = novoNode;
    }

    public Elemento pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Pilha vazia.");
        }
        Elemento elemento = topo.getElemento();
        topo = topo.getProximo();
        return elemento;
    }

    // Método para obter o nó no topo da pilha
    public Node getTopo() {
        return topo;
    }

    public void exibirPilha() {
        Node atual = topo;
        if (isEmpty()) {
            System.out.println("Pilha vazia.");
        } else {
            while (atual != null) {
                System.out.println(atual.getElemento());
                atual = atual.getProximo();
            }
        }
    }
}
