import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class Main extends JFrame {
    private JTextArea historicoTextArea;
    private JTextArea filaTextArea;
    private JTextField idField, nomeField, descricaoField, dataHoraField;
    private JButton addHistoricoButton, removeHistoricoButton, addFilaButton, removeFilaButton;
    private Pilha historico;
    private Fila filaAtendimento;

    public Main() {
        // Inicialização das estruturas
        historico = LeitorDeArquivo.lerHistorico("src/historico.txt");
        filaAtendimento = LeitorDeArquivo.lerFilaAtendimento("src/filadeatendimento.txt");

        setTitle("Sistema de Atendimento");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        historicoTextArea = new JTextArea(10, 50);
        filaTextArea = new JTextArea(10, 50);
        historicoTextArea.setEditable(false);
        filaTextArea.setEditable(false);


        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new GridLayout(1, 2));
        textAreaPanel.add(new JScrollPane(historicoTextArea));
        textAreaPanel.add(new JScrollPane(filaTextArea));
        add(textAreaPanel, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        inputPanel.add(descricaoField);
        inputPanel.add(new JLabel("Data e Hora:"));
        dataHoraField = new JTextField();
        inputPanel.add(dataHoraField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        addHistoricoButton = new JButton("Adicionar ao Histórico");
        removeHistoricoButton = new JButton("Remover do Histórico");
        addFilaButton = new JButton("Adicionar à Fila");
        removeFilaButton = new JButton("Remover da Fila");

        buttonPanel.add(addHistoricoButton);
        buttonPanel.add(removeHistoricoButton);
        buttonPanel.add(addFilaButton);
        buttonPanel.add(removeFilaButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addHistoricoButton.addActionListener(e -> adicionarAoHistorico());
        removeHistoricoButton.addActionListener(e -> removerDoHistorico());
        addFilaButton.addActionListener(e -> adicionarAFila());
        removeFilaButton.addActionListener(e -> removerDaFila());

        atualizarExibicaoHistorico();
        atualizarExibicaoFila();
    }

    private void adicionarAoHistorico() {
        String id = idField.getText();
        String descricao = descricaoField.getText();
        String dataHora = dataHoraField.getText();
        if (!id.isEmpty() && !descricao.isEmpty() && !dataHora.isEmpty()) {
            historico.push(new Elemento(id, null, descricao, dataHora));
            atualizarExibicaoHistorico();
            salvarHistorico();
        } else {
            JOptionPane.showMessageDialog(this, "Preencha os campos ID, Descrição e Data e Hora.");
        }
    }

    private void removerDoHistorico() {
        try {
            historico.pop();
            atualizarExibicaoHistorico();
            salvarHistorico();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Histórico vazio.");
        }
    }

    private void adicionarAFila() {
        String id = idField.getText();
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        if (!id.isEmpty() && !nome.isEmpty() && !descricao.isEmpty()) {
            filaAtendimento.enfileirar(new Elemento(id, nome, descricao, null));
            atualizarExibicaoFila();
            salvarFila();
        } else {
            JOptionPane.showMessageDialog(this, "Preencha os campos ID, Nome e Descrição.");
        }
    }

    private void removerDaFila() {
        try {
            filaAtendimento.desenfileirar();
            atualizarExibicaoFila();
            salvarFila();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fila vazia.");
        }
    }

    private void atualizarExibicaoHistorico() {
        historicoTextArea.setText("");
        Node atual = historico.getTopo();
        while (atual != null) {
            historicoTextArea.append(atual.getElemento().toString() + "\n");
            atual = atual.getProximo();
        }
    }

    private void atualizarExibicaoFila() {
        filaTextArea.setText("");
        Node atual = filaAtendimento.getFrente();
        while (atual != null) {
            filaTextArea.append(atual.getElemento().toString() + "\n");
            atual = atual.getProximo();
        }
    }

    private void salvarHistorico() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/historico.txt"))) {
            Node atual = historico.getTopo();
            while (atual != null) {
                Elemento e = atual.getElemento();
                writer.write("new Elemento(\"" + e.getId() + "\", \"" + e.getDescricao() + "\", \"" + e.getDataHora() + "\");\n");
                atual = atual.getProximo();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o histórico.");
        }
    }

    private void salvarFila() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/filadeatendimento.txt"))) {
            Node atual = filaAtendimento.getFrente();
            while (atual != null) {
                Elemento e = atual.getElemento();
                writer.write("new Elemento(\"" + e.getId() + "\", \"" + e.getNome() + "\", \"" + e.getDescricao() + "\");\n");
                atual = atual.getProximo();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a fila de atendimento.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
