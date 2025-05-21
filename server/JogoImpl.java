package server;

import common.JogoInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * Classe que implementa a interface JogoInterface.
 * Gerencia o estado do jogo, incluindo o tabuleiro, os jogadores e as jogadas.
 * Implementa a lógica do jogo, incluindo a verificação de vencedores e o registro de jogadores.
 * Utiliza RMI para permitir que os jogadores interajam com o servidor remotamente.
 */
public class JogoImpl extends UnicastRemoteObject implements JogoInterface {
    private char[][] tabuleiro;// matriz do tabuleiro 5x5
    private int jogadorAtual;  // indica qual jogador deve jogar na vez atual
    private int jogadoresRegistrados; // contador de jogadores conectados

    // Construtor da classe JogoImpl.
    public JogoImpl() throws RemoteException {
        tabuleiro = new char[5][5];
        jogadorAtual = 1;
        jogadoresRegistrados = 0;
        limparTabuleiro();
    }

    // Limpa o tabuleiro colocando '-' em todas as casas
    private void limparTabuleiro() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                tabuleiro[i][j] = '-';
    }

     // Registra um novo jogador e retorna o ID dele (1, 2 ou 3). Máximo de 3.
    public synchronized int registrarJogador() {
        if (jogadoresRegistrados < 3) {
            jogadoresRegistrados++;
            return jogadoresRegistrados;
        }
        return 0;
    }

    // Faz uma jogada no tabuleiro se for válida e for a vez do jogador
    public synchronized boolean fazerJogada(int linha, int coluna, int jogadorId) throws RemoteException {
        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 5 || tabuleiro[linha][coluna] != '-') return false;
        if (jogadorId != jogadorAtual) return false;

        // Define a marca com base no ID do jogador
        char marca = switch (jogadorId) {
            case 1 -> 'X';
            case 2 -> 'O';
            case 3 -> 'Z';
            default -> '?';
        };

        tabuleiro[linha][coluna] = marca;
        jogadorAtual = jogadorAtual % 3 + 1;// muda para o próximo jogador
        return true;
    }

    public synchronized char[][] getTabuleiro() {
        return tabuleiro;
    }

    public synchronized int getJogadorAtual() {
        return jogadorAtual;
    }

    // Verifica se algum jogador venceu: 3 marcas consecutivas na horizontal, vertical ou diagonais
    public synchronized int verificarVencedor() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                char c = tabuleiro[i][j];
                // Verifica linhas
                if (c != '-' && c == tabuleiro[i][j+1] && c == tabuleiro[i][j+2])
                    return getJogadorByChar(c);
                c = tabuleiro[j][i];
                // Verifica colunas
                if (c != '-' && c == tabuleiro[j+1][i] && c == tabuleiro[j+2][i])
                    return getJogadorByChar(c);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = tabuleiro[i][j];
                // Verifica diagonais
                if (c != '-' && c == tabuleiro[i+1][j+1] && c == tabuleiro[i+2][j+2])
                    return getJogadorByChar(c);
                c = tabuleiro[i][j+2];
                // Verifica diagonais inversas
                if (c != '-' && c == tabuleiro[i+1][j+1] && c == tabuleiro[i+2][j])
                    return getJogadorByChar(c);
            }
        }
        return 0; // nenhum vencedor
    }

    // Verifica se houve empate (nenhuma casa vazia e nenhum vencedor)
    public synchronized boolean isEmpate() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (tabuleiro[i][j] == '-') return false;
        return verificarVencedor() == 0;
    }

    // Reinicia o jogo, limpando o tabuleiro e resetando o jogador atual
    public synchronized void reiniciarJogo() {
        limparTabuleiro();
        jogadorAtual = 1;
    }

    // Método auxiliar para converter o caractere da marca em ID do jogador
    private int getJogadorByChar(char c) {
        return switch (c) {
            case 'X' -> 1;
            case 'O' -> 2;
            case 'Z' -> 3;
            default -> 0;
        };
    }
}
