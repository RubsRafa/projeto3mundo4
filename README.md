# 📱 Lista de Tarefas x Sensores

Este é um aplicativo Android (Android Studio) simples desenvolvido com foco em **acessibilidade**, permitindo que usuários adicionem e removam tarefas por meio de **comandos de voz** e **síntese de fala** (Text-to-Speech).

O [relatório de práticas](https://github.com/user-attachments/files/20024727/Projeto.Nivel.3.full.pdf) foi confeccionado em formato PDF e produzido em conjunto com o desenvolvimento do projeto.

---

## 🔧 Funcionalidades

- 🗣️ Adição de tarefas (simulada).
- 📋 Visualização de lista de tarefas.
- 🧹 Remoção de tarefas com confirmação.
- 🔊 Leitura em voz alta de todas as tarefas com um botão.

---

## 🛠️ Tecnologias Utilizadas

- Android Studio

---

## ▶️ Como Usar

1. **Adicionar tarefa:**
   - Toque no botão **"Adicionar"**.
   - O app diz "Escutando", e aguarda a simulação da fala.
   - A tarefa é adicionada automaticamente à lista com feedback de voz.

2. **Remover tarefa:**
   - Toque em um item da lista.
   - O app pergunta em voz alta "Deseja remover o item X?" e exibe uma caixa de confirmação.
   - Ao confirmar, o item é removido e a remoção é anunciada por voz.

3. **Ler tarefas:**
   - Toque no botão "Ler tudo" para ouvir todas as tarefas listadas.

---

## 📂 Estrutura Principal

- `MainActivity.java`: lógica principal do app.
- `AudioHelper.java`: utilitário para verificar disponibilidade de áudio.
- `activity_main.xml`: layout da tela principal.

---

## 📹 Resultados

https://github.com/user-attachments/assets/a2a8b319-cdd2-4a42-aeeb-678b9cf8633a

https://github.com/user-attachments/assets/6f8be6c7-6349-4c0a-a0e5-88c95b620a65

