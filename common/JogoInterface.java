package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface remota que define os métodos disponíveis para os clientes via RMI
public interface JogoInterface extends Remote {
    boolean fazerJogada(int linha, int coluna, int jogadorId) throws RemoteException;
    char[][] getTabuleiro() throws RemoteException;
    int getJogadorAtual() throws RemoteException;
    int verificarVencedor() throws RemoteException;
    int registrarJogador() throws RemoteException;
    boolean isEmpate() throws RemoteException;
    void reiniciarJogo() throws RemoteException;
}