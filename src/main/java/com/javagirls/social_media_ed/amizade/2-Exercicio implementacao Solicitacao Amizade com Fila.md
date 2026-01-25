# **Exercício: Implementação de uma Fila de Solicitações de Amizade usando Array**

# **Objetivo do Exercício**

Implementar um sistema de fila (queue) para gerenciar solicitações de amizade em uma rede social utilizando a estrutura de dados **Fila (Queue)** implementada com array. A fila deve permitir adicionar novas solicitações no final e processar as mais antigas primeiro (FIFO - First In, First Out).

## **Contexto do Problema**

Na rede social **JavaGirls Social**, os usuários enviam solicitações de amizade uns para os outros. Essas solicitações precisam ser armazenadas e processadas na ordem de chegada, seguindo o princípio FIFO. Cada solicitação é representada por um objeto `SolicitacaoAmizade` armazenado em um array, onde:

- O array tem tamanho fixo definido por `TAMANHO_MAXIMO`
- As operações devem ser eficientes para adição e remoção
- A estrutura deve controlar o tamanho atual da fila

## **Requisitos Técnicos**

### **1. Estrutura de Dados**

Implemente uma **fila (queue)** usando um array de tamanho fixo com as seguintes características:

- Array `fila` do tipo `SolicitacaoAmizade[]` com tamanho máximo definido
- Contador `tamanhoAtual` para rastrear o número de elementos na fila
- A fila deve seguir o princípio FIFO (First In, First Out)

### **2. Funcionalidades Obrigatórias**

### **A. Classe `FilaSolicitacaoAmizade`**

```java
import com.javagirls.social_media_ed.amizade.SolicitacaoAmizade;
import com.javagirls.social_media_ed.amizade.SolicitacaoDTO;

public class FilaSolicitacaoAmizade {
   private static final int TAMANHO_MAXIMO = 100;
   private SolicitacaoAmizade[] fila;
   private int tamanhoAtual;

   // Métodos a implementar:

   // 1. Adicionar solicitação ao final da fila (enfileirar)
   public SolicitacaoAmizade enfileirar(SolicitacaoDTO solicitacaoDados) {

      SolicitacaoAmizade novaSolicitacao = solicitacaoDados;
      if(fila != null){
          throw RuntimeException();
      }
      fila[tamanhoAtual]

      // TODO: Implementar
      // - Converter DTO para SolicitacaoAmizade
      // - Verificar se há espaço na fila
      // - Adicionar no final do array
      // - Atualizar tamanhoAtual
      // - Retornar a solicitação enfileirada
   }

   // 2. Remover e retornar a solicitação mais antiga (desenfileirar)
   public Optional<SolicitacaoAmizade> desenfileirar() {
      // TODO: Implementar
      // - Verificar se a fila está vazia
      // - Remover a primeira solicitação (índice 0)
      // - Deslocar todos os elementos restantes uma posição para frente
      // - Atualizar tamanhoAtual
      // - Retornar a solicitação removida (Optional)
   }

   // 3. Verificar se a fila está vazia
   public boolean estaVazia() {
      // TODO: Implementar
      // - Retornar true se tamanhoAtual == 0
   }

   // 4. Verificar se a fila está cheia
   public boolean estaCheia() {
      // TODO: Implementar
      // - Retornar true se tamanhoAtual == TAMANHO_MAXIMO
   }

   // 5. Obter tamanho atual da fila
   public int getTamanhoAtual() {
      return tamanhoAtual;
   }

   // 6. Visualizar todas as solicitações na fila
   public SolicitacaoAmizade[] imprimirFila() {
      // TODO: Implementar
      // - Retornar o array com as solicitações atuais
      // - Considerar que posições vazias podem ser null
   }
}

```

### **B. Classe `SolicitacaoAmizade`**

```java
public class SolicitacaoAmizade {
    // Atributos
    private Integer id;
    private Integer idRemetente;
    private Integer idDestinatario;
    private LocalDateTime dataSolicitacao;
    private String status; // "PENDENTE", "ACEITA", "REJEITADA"

    // TODO: Implementar construtores, getters e setters
}

```

### **C. Classe `SolicitacaoDTO`**

```java
public class SolicitaçãoDTO {
    // Atributos
    private Integer idRemetente;
    private Integer idDestinatario;

    // Método para converter DTO em entidade
    public SolicitacaoAmizade toSolicitacaoAmizade() {
        // TODO: Implementar
        // - Criar nova SolicitacaoAmizade com os dados do DTO
        // - Gerar ID único (pode ser sequencial)
        // - Definir data atual e status inicial como "PENDENTE"
    }
}

```

### **3. Regras de Implementação**

### **Regra 1: Adição no Final (Enfileirar)**

- Quando uma nova solicitação é criada, ela deve ser adicionada no **final** da fila

### **Regra 2: Remoção do Início (Desenfileirar)**

- A solicitação removida deve ser a mais antiga
- Após remover o primeiro elemento, todos os elementos restantes devem ser deslocados uma posição para frente

### **Regra 3: Controle de Capacidade**

- A fila não pode exceder o `TAMANHO_MAXIMO`
- O método `enfileirar` deve lançar uma exceção se a fila estiver cheia
- O método `desenfileirar` deve retornar `Optional.empty()` se a fila estiver vazia

## **Conceitos Avaliados**

1. **Estrutura de Dados**: Compreensão de fila (queue) e operações FIFO
2. **Manipulação de Arrays**: Deslocamento de elementos, controle de índices
3. **Complexidade Algorítmica**:
    - Enfileirar: O(1)
    - Desenfileirar: O(n) devido ao deslocamento
4. **Tratamento de Estados Especiais**: Fila vazia, fila cheia
5. **Padrões de Projeto**: DTO (Data Transfer Object), Optional para valores nulos

---

**Boa implementação!** Lembre-se que em uma fila, o primeiro a entrar é o primeiro a sair, assim como em uma fila de banco onde a primeira pessoa a chegar é a primeira a ser atendida.