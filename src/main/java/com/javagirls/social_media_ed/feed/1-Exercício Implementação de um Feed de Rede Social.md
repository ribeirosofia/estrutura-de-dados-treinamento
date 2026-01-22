# Exercício: Implementação de um Feed de Rede Social usando Linked List

## **🎯 Objetivo do Exercício**

Implementar um sistema de feed (timeline) para uma rede social utilizando a estrutura de dados **Linked List (Lista Encadeada)**. O feed deve permitir adicionar novas postagens no início da lista, mantendo as postagens mais recentes sempre no topo.

## **📋 Contexto do Problema**

Em uma rede social chamada **JavaGirls Social**, os usuários postam mensagens que aparecem em um feed. Cada postagem é representada por um nó (`PostagemNo`) em uma lista encadeada, onde:

- Cada nó contém os dados da postagem
- As postagens são organizadas em ordem cronológica reversa (mais recentes primeiro)
- A estrutura deve ser eficiente para adição de novas postagens

## **🔧 Requisitos Técnicos**

### **1. Estrutura de Dados**

Implemente uma **lista duplamente encadeada** com as seguintes características:

- Cada nó (`PostagemNo`) deve conter:
    - ID da postagem
    - Mensagem do post
    - ID e nome do autor
    - Contador de curtidas
    - Data de criação
    - Referências para o nó anterior e próximo na lista

### **2. Funcionalidades Obrigatórias**

### **A. Classe `Feed`**

```java
public class Feed {
    private PostagemNo cabeca;    // Primeiro nó da lista
    private int tamanho;          // Número total de postagens

    // Métodos a implementar:

    // 1. Adicionar postagem no início do feed
    public PostagemNo adicionarNoInicio(String mensagem, Usuario autor) {
        // TODO: Implementar
        // - Criar nova postagem
        // - Criar novo nó
        // - Inserir no início da lista
        // - Atualizar tamanho
        // - Retornar o novo nó
    }

    // 2. Obter todas as postagens (do mais recente ao mais antigo)
    public List<PostagemNo> getTodasPostagens() {
        // TODO: Implementar
        // - Percorrer a lista a partir da cabeça
        // - Coletar todos os nós em uma lista
    }

    // 3. Obter tamanho do feed
    public int getTamanho() {
        return tamanho;
    }

    // 4. Obter cabeça da lista
    public PostagemNo getCabeca() {
        return cabeca;
    }
}

```

### **B. Classe `PostagemNo`**

```java
public class PostagemNo {
    // Atributos
    private Integer id;
    private String mensagem;
    private Integer autorId;
    private String autorNome;
    private Integer contadorCurtidas;
    private LocalDateTime dataCriacao;
    private PostagemNo anterior;  // Referência ao nó anterior
    private PostagemNo proximo;   // Referência ao próximo nó

    // TODO: Implementar construtores, getters e setters
}

```

### **3. Regras de Implementação**

### **Regra 1: Adição no Início**

- Quando uma nova postagem é criada, ela deve ser inserida no **início** da lista
- A nova postagem se torna a nova `cabeca` da lista
- O nó anterior da antiga cabeça deve apontar para a nova postagem
- O tamanho do feed deve ser atualizado

### **Regra 2: Estrutura da Linked List**

- Implemente uma lista **duplamente encadeada**
- Cada nó deve manter referências para o nó anterior e próximo
- A última postagem deve ter `proximo = null`
- A primeira postagem deve ter `anterior = null`

### **Regra 3: Percorrendo a Lista**

- O método `getTodasPostagens()` deve percorrer a lista começando pela `cabeca`

## **💡 Dicas para Implementação**

### **1. Diagrama da Estrutura**

```
Feed Vazio:
cabeca → null
tamanho = 0

Após adicionar "Post 1":
cabeca → [Post 1] → null
          anterior: null
          proximo: null
tamanho = 1

Após adicionar "Post 2":
cabeca → [Post 2] → [Post 1] → null
          anterior: null     anterior: Post 2
          proximo: Post 1    proximo: null
tamanho = 2

```

## **🔍 Conceitos Avaliados**

1. **Estrutura de Dados**: Compreensão de lista encadeada
2. **Manipulação de Referências**: Ponteiros anterior/próximo
3. **Complexidade Algorítmica**:
    - Adição no início: O(1)
    - Listagem completa: O(n)
4. **Padrões de Projeto**: Separar dados (PostagemNo) da lógica (Feed)

---

**Boa implementação!** Lembre-se que em uma linked list, cada nó é como um vagão de trem: sabe quem está na frente e quem está atrás, mas não conhece o trem inteiro.