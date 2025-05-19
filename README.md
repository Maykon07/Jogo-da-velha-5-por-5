
# 🕹️ Jogo da Velha 5x5 com 3 Jogadores (Java RMI)

Este projeto implementa um **Jogo da Velha** de **5x5 casas**, com suporte a **3 jogadores remotos**, utilizando **Java RMI (Remote Method Invocation)** como base da comunicação distribuída.

## 📌 Objetivo

- Aplicar os conceitos de **sistemas distribuídos** com RMI.
- Criar um jogo interativo com comunicação cliente-servidor.
- Desenvolver uma interface gráfica simples para cada jogador.

## 📂 Estrutura do Projeto

```
Jogo-da-velha-5-por-5/
  ├── common/            # Interface remota
  │   └── JogoInterface.java
  ├── server/            # Lógica do jogo e servidor RMI
  │   ├── JogoImpl.java
  │   └── ServerMain.java
  ├── client/            # Cliente com interface gráfica (Swing)
  │   ├── ClienteMain.java
  │   └── TabuleiroUI.java
```

## 🧠 Como Funciona

- O servidor armazena o **estado global do jogo** e valida as jogadas.
- Cada cliente se conecta via RMI e registra seu jogador (X, O, Z).
- A interface Swing exibe o tabuleiro e permite jogar **clicando nas casas**.
- O jogo reconhece vitória (3 em sequência) e empate (tabuleiro cheio).
- Ao final, o jogo é automaticamente reiniciado.

## ▶️ Como Executar

### 1. Compilando o projeto

Abra o terminal na pasta `Jogo-da-velha-5-por-5/` e compile:

```bash
javac common/*.java server/*.java client/*.java
```

### 2. Iniciando o RMI Registry

No mesmo diretório, abra um terminal separado e execute:

```bash
start rmiregistry
```

> ⚠️ Mantenha este terminal aberto.

### 3. Iniciando o servidor

```bash
java server.ServerMain
```

### 4. Abrindo os jogadores

Abra 3 terminais e execute em cada um:

```bash
java client.ClienteMain
```

Cada um representará um jogador diferente (Jogador 1, 2 e 3).

## 🏁 Regras do Jogo

- Jogadores:  
  - Jogador 1 → `X`  
  - Jogador 2 → `O`  
  - Jogador 3 → `Z`

- Vence quem alinhar **3 símbolos iguais** na horizontal, vertical ou diagonal.
- Se todas as casas forem ocupadas sem vencedor → **empate**.
- Todos os jogadores recebem a mensagem de fim e o jogo reinicia.

## ✅ Requisitos

- Java JDK 11+ instalado
- Sistema operacional Windows, Linux ou Mac
- Interface pode ser testada localmente, com múltiplas janelas

## 🛠️ Melhorias Implementadas

- Interface gráfica com clique nas casas.
- Verificação automática de vitória e empate.
- Todos os jogadores são notificados do resultado.
- Reinício automático da partida após fim de jogo.
- Mensagens de “aguarde sua vez” e jogada inválida.

## 📚 Conceitos Envolvidos

- Comunicação remota via **Java RMI**
- Conceitos de **sincronização** com `synchronized`
- **Programação orientada a objetos**
- Interface com **Swing**

## 👨‍💻 Autoria

Projeto acadêmico para a disciplina **Sistemas Distribuídos**.
### Desenvolvido por Maykon de Souza Santos
