package server;

import common.JogoInterface;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/*
 * Classe principal do servidor.
 * Inicia o servidor RMI e registra a implementação do jogo.
 * Permite que os clientes se conectem e joguem remotamente.
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            JogoImpl jogo = new JogoImpl();
            Naming.rebind("Jogo", jogo);// cria instância do jogo
            System.out.println("Servidor RMI iniciado.");// registra o jogo no RMI registry
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}