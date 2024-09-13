public class Node {
    private Elemento elemento;
    private Node anterior;
    private Node proximo;

    public Node() {
        this.anterior = null;
        this.elemento = null;
        this.proximo = null;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }
}
