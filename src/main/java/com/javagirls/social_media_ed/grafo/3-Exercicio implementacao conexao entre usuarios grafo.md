# **3. Exercício: Implementação de um Grafo de Amizades usando Lista de Adjacências**

## **Objetivo do Exercício**

Implementar um sistema de grafo (graph) para gerenciar relações de amizade em uma rede social utilizando a estrutura de dados **Grafo Não Direcionado** implementada com lista de adjacências. O grafo deve permitir adicionar usuários (vértices) e conexões de amizade (arestas), além de fornecer funcionalidades para consultar amigos e navegar pelas conexões.

## **Contexto do Problema**

Na rede social **JavaGirls Social**, as relações de amizade entre usuários formam uma rede complexa onde cada usuário é um vértice e cada amizade é uma aresta não-direcionada. Este grafo precisa ser eficiente para:

- Adicionar novos usuários à rede
- Estabelecer conexões de amizade entre usuários existentes
- Consultar a lista de amigos de um usuário específico
- Navegar pela rede de relações sociais

## **Requisitos Técnicos**

### **1. Estrutura de Dados**

Implemente um **grafo não direcionado** usando lista de adjacências com as seguintes características:

- Mapa `vertices` para armazenar usuários (vértices) por ID
- Mapa `adjacencias` para armazenar as conexões (arestas) como conjuntos de IDs
- Contador `totalArestas` para rastrear o número total de conexões

### **2. Funcionalidades Obrigatórias**

### **A. Classe `ListaAdjacenteGrafo`**

```java
public class ListaAdjacenteGrafo implements Grafo {
    private final Map<Integer, Usuario> vertices;
    private final Map<Integer, Set<Integer>> adjacencias;
    private int totalArestas;

    // Métodos a implementar:

    // 1. Adicionar um usuário (vértice) ao grafo
    public void adicionarUsuario(Usuario usuario) {
        // TODO: Implementar
        // - Validar que o usuário não é nulo
        // - Verificar se o usuário já existe no grafo
        // - Adicionar ao mapa de vértices
        // - Criar conjunto vazio para as adjacências deste vértice
    }

    // 2. Buscar um usuário pelo ID
    public Optional<Usuario> buscarUsuario(int id) {
        // TODO: Implementar
        // - Buscar o usuário no mapa de vértices
        // - Retornar Optional contendo o usuário, se encontrado
        // - Retornar Optional.empty() se não encontrado
    }

    // 3. Adicionar uma conexão de amizade (aresta) entre dois usuários
    public void adicionarConexao(int idUsuario1, int idUsuario2) {
        // TODO: Implementar
        // - Verificar se ambos os usuários existem no grafo
        // - Adicionar cada usuário à lista de adjacências do outro
        // - Incrementar o contador total de arestas
        // - A conexão é bidirecional (grafo não direcionado)
    }

    // 4. Obter todas as conexões (amigos) de um usuário
    public List<Usuario> getConexoesUsuario(int idUsuario) {
        // TODO: Implementar
        // - Verificar se o usuário existe no grafo
        // - Obter os IDs dos amigos a partir do mapa de adjacências
        // - Converter IDs para objetos Usuario
        // - Retornar lista de usuários (amigos)
    }

    // 5. Verificar se dois usuários são amigos (conectados)
    public boolean saoAmigos(int idUsuario1, int idUsuario2) {
        // TODO: Implementar
        // - Verificar se ambos os usuários existem
        // - Checar se idUsuario2 está na lista de adjacências de idUsuario1
    }

    // 6. Remover uma conexão de amizade
    public void removerConexao(int idUsuario1, int idUsuario2) {
        // TODO: Implementar
        // - Verificar se ambos os usuários existem
        // - Remover cada usuário da lista de adjacências do outro
        // - Decrementar o contador total de arestas
    }

    // 7. Obter número total de vértices (usuários)
    public int getTotalVertices() {
        return vertices.size();
    }

    // 8. Obter número total de arestas (conexões)
    public int getTotalArestas() {
        return totalArestas;
    }
}

```

### **B. Interface `Grafo`**

```java
public interface Grafo {
    void adicionarUsuario(Usuario usuario);
    Optional<Usuario> buscarUsuario(int id);
    void adicionarConexao(int idUsuario1, int idUsuario2);
    List<Usuario> getConexoesUsuario(int idUsuario);
    boolean saoAmigos(int idUsuario1, int idUsuario2);
    void removerConexao(int idUsuario1, int idUsuario2);
    int getTotalVertices();
    int getTotalArestas();
}

```

### **C. Classe `Usuario`**

```java
public class Usuario {
    // Atributos
    private Integer id;
    private String nomeUsuario;
    private String email;

    // TODO: Implementar construtores, getters e setters
}

```

### **3. Regras de Implementação**

### **Regra 1: Grafo Não Direcionado**

- Todas as conexões (arestas) são bidirecionais
- Se o usuário A é amigo do usuário B, então B também é amigo de A
- Cada conexão deve ser representada nas listas de adjacências de ambos os usuários

### **Regra 2: Evitar Duplicatas**

- Não deve ser possível adicionar o mesmo usuário duas vezes (mesmo ID)
- Não deve ser possível adicionar a mesma conexão duas vezes (amizade duplicada)
- As listas de adjacências devem usar conjuntos (Set) para evitar duplicatas

### **Regra 3: Validações**

- Validar parâmetros nulos no método `adicionarUsuario`
- Verificar existência dos usuários antes de adicionar conexões
- Retornar listas vazias ou Optionals vazios quando apropriado

### **Regra 4: Complexidade**

- As operações principais devem ser eficientes (O(1) para adição de vértices e arestas, O(grau) para consulta de vizinhos)

## **Conceitos Avaliados**

1. **Estrutura de Dados**: Compreensão de grafos não direcionados e listas de adjacências
2. **Manipulação de Mapas e Conjuntos**: Uso eficiente de HashMap e HashSet
3. **Complexidade Algorítmica**:
    - Adição de vértice: O(1)
    - Adição de aresta: O(1)
    - Consulta de vizinhos: O(grau do vértice)
4. **Tratamento de Estados Especiais**: Vértices inexistentes, conexões duplicadas
5. **Padrões de Projeto**: Uso de interfaces, Optionals para valores nulos, separação de responsabilidades

---

**Boa implementação!** Lembre-se que em um grafo de amizades, cada pessoa é como uma estrela conectada a outras, formando uma constelação de relações sociais onde todos estão potencialmente conectados através de amigos de amigos.