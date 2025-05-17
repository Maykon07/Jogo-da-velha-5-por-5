package client;

import common.JogoInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Interface gráfica com Swing.
* Cria uma janela com um tabuleiro 5x5 e botões para cada célula.
* Cada botão representa uma célula do tabuleiro e exibe o símbolo do jogador atual.
* O jogador pode clicar em um botão para fazer uma jogada.
* O tabuleiro é atualizado a cada segundo para refletir o estado atual do jogo.
* Quando um jogador vence, uma mensagem é exibida.
* O jogador atual é verificado antes de permitir a jogada.
 */
public class TabuleiroUI extends JFrame {
    private JButton[][] botoes = new JButton[5][5];
    private JogoInterface jogo;
    private int jogadorId;
    private JLabel statusLabel;

    /**
     * Construtor da classe TabuleiroUI.
     * Inicializa a interface gráfica do tabuleiro do jogo.
     */
    public TabuleiroUI(JogoInterface jogo, int jogadorId) {
        this.jogo = jogo;
        this.jogadorId = jogadorId;
        setTitle("Jogador " + jogadorId);
        setSize(420, 460);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final int x = i, y = j;
                botoes[i][j] = new JButton("-");
                botoes[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
                botoes[i][j].addActionListener(e -> fazerJogada(x, y));
                gridPanel.add(botoes[i][j]);
            }
        }

        statusLabel = new JLabel("Seu ID: " + jogadorId, SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        atualizarTabuleiro();
        Timer timer = new Timer(1000, e -> atualizarTabuleiro());
        timer.start();
        setVisible(true);
    }

    /**
     * Método chamado quando um botão é clicado.
     * Verifica se o jogador atual é o jogador que está jogando.
     * Se sim, faz a jogada e atualiza o tabuleiro.
     * Se houver um vencedor, exibe uma mensagem.
     */
    private void fazerJogada(int linha, int coluna) {
        try {
            if (jogo.getJogadorAtual() != jogadorId) {
                JOptionPane.showMessageDialog(this, "Aguarde sua vez.");
                return;
            }
            if (!jogo.fazerJogada(linha, coluna, jogadorId)) {
                JOptionPane.showMessageDialog(this, "Jogada inválida.");
                return;
            }
            atualizarTabuleiro();

            int vencedor = jogo.verificarVencedor();
            if (vencedor != 0) {
                JOptionPane.showMessageDialog(this, "Jogador " + vencedor + " venceu!");
                jogo.reiniciarJogo();
                atualizarTabuleiro();
            } else if (jogo.isEmpate()) {
                JOptionPane.showMessageDialog(this, "Empate! Ninguém venceu.");
                jogo.reiniciarJogo();
                atualizarTabuleiro();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Atualiza o tabuleiro com o estado atual do jogo.
     * Obtém o tabuleiro do objeto JogoInterface e atualiza os botões.
     */
    private void atualizarTabuleiro() {
        try {
            char[][] tab = jogo.getTabuleiro();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    botoes[i][j].setText(String.valueOf(tab[i][j]));
                }
            }
            statusLabel.setText("Jogador " + jogadorId + " - Sua vez: " + (jogo.getJogadorAtual() == jogadorId));
    
            int vencedor = jogo.verificarVencedor();
            if (vencedor != 0) {
                JOptionPane.showMessageDialog(this, "Jogador " + vencedor + " venceu!");
                jogo.reiniciarJogo();
            } else if (jogo.isEmpate()) {
                JOptionPane.showMessageDialog(this, "Empate! Ninguém venceu.");
                jogo.reiniciarJogo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
} 
