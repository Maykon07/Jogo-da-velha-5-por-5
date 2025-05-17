package client;

import common.JogoInterface;
import javax.swing.*;
import java.rmi.Naming;

// Cliente que conecta ao servidor RMI, registra o jogador
// e inicia a interface
public class ClienteMain {
    public static void main(String[] args) {
        try {
            JogoInterface jogo = (JogoInterface) Naming.lookup("rmi://localhost/Jogo");
             // Registra o jogador no jogo. O servidor deve retornar um ID único para o jogador.
            int jogadorId = jogo.registrarJogador();
            if (jogadorId == 0) {
                JOptionPane.showMessageDialog(null, "Máximo de jogadores atingido.");
                return;
            }
            // Inicia a interface gráfica do tabuleiro do jogo
            // e passa a referência do jogo e o ID do jogador.
            SwingUtilities.invokeLater(() -> new TabuleiroUI(jogo, jogadorId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}