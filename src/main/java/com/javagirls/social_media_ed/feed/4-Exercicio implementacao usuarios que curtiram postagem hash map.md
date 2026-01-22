# **4. Exercício: Sistema de Indexação de Curtidas com HashMap**

# **Objetivo do Exercício**

Implementar um sistema de indexação de curtidas utilizando a estrutura de dados **HashMap** para otimizar consultas sobre quais usuários curtiram cada postagem. Considerando que a classe `Postagem` já possui um `Set<Usuario>` para armazenar as curtidas, você deverá criar uma estrutura paralela (Map) que permita consultas rápidas e eficientes sem precisar percorrer todas as postagens.

## **Contexto do Problema**

Na rede social **JavaGirls Social**, cada postagem já possui um conjunto (`Set<Usuario>`) que armazena os usuários que a curtiram. No entanto, para consultas frequentes como:

- "Quais usuários curtiram a postagem X?"
- "O usuário Y já curtiu esta postagem?"
- "Quantas curtidas cada postagem recebeu?"

Percorrer o conjunto de todas as postagens e seus respectivos conjuntos de curtidas pode ser ineficiente quando há muitas postagens. Sua tarefa é implementar um **Sistema de Indexação de Curtidas** que utilize um `HashMap` para mapear rapidamente cada postagem aos usuários que a curtiram.

## **Requisitos Técnicos**

### **1. Estrutura de Dados**

Implemente um **sistema de indexação** usando `HashMap` que complementa a estrutura existente da classe `Postagem`:

- Um mapa `Map<Integer, Set<Integer>>` onde a chave é o ID da postagem e o valor é um conjunto de IDs dos usuários que a curtiram
- Este mapa deve ser mantido sincronizado com o atributo `curtidas` de cada `Postagem`
- O sistema deve permitir consultas O(1) para operações comuns

### **2. Regras de Implementação**

### **Regra 1: Sincronização com a Postagem**

- Toda vez que uma curtida for adicionada ou removida da `Postagem`, o índice deve ser atualizado

### **Regra 2: Consistência de Dados**

- O índice deve refletir exatamente o estado atual das curtidas das postagens

### **Regra 3: Performance**

- Todas as operações no índice devem ser O(1) em média
- O uso de `HashMap` e `HashSet` garante acesso rápido aos dados
- Evitar operações que exijam percorrer todo o índice

### **Regra 4: Tratamento de Casos Especiais**

- Postagens sem curtidas não devem ocupar espaço no índice (opcional)
- O índice deve lidar com postagens removidas (limpar entradas correspondentes)
- Métodos devem retornar valores padrão (conjuntos vazios, zero) para elementos não encontrados

## **Dicas para Implementação**

### **1. Diagrama da Estrutura**

```
Classe Postagem:
- id: 1
- curtidas: Set<Usuario> [Usuário{id:101}, Usuário{id:102}]

Índice (IndexadorCurtidas):
Map<Integer, Set<Integer>>
{
    1: {101, 102},    // Postagem 1 curtida pelos usuários 101 e 102
    2: {101, 103},    // Postagem 2 curtida pelos usuários 101 e 103
    3: {102}          // Postagem 3 curtida pelo usuário 102
}

```

## **Conceitos Avaliados**

1. **Estrutura de Dados**: Compreensão e aplicação de HashMap e HashSet
2. **Indexação de Dados**: Criação de estruturas auxiliares para otimizar consultas
3. **Sincronização de Dados**: Manter múltiplas estruturas de dados consistentes
4. **Complexidade Algorítmica**: Garantir operações eficientes (O(1))
5. **Padrões de Projeto**: Serviço, injeção de dependências, separação de responsabilidades
6. **APIs REST**: Criação de endpoints otimizados usando o índice

---

**Boa implementação!**