package com.javagirls.social_media_ed.amizade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe FilaSolicitacaoAmizade.
 *
 * OBJETIVO DIDÁTICO:
 * Esta classe testa uma implementação de Fila usando array com tamanho fixo (100 posições).
 * A fila segue o princípio FIFO (First In, First Out - Primeiro a Entrar, Primeiro a Sair).
 *
 * CONCEITOS ENVOLVIDOS:
 * - Fila (estrutura de dados FIFO)
 * - Array estático com tamanho máximo
 * - Manipulação de índices (frente e controle de tamanho)
 * - Optional para retorno seguro quando a fila está vazia
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes da Classe FilaSolicitacaoAmizade - Sistema de Fila com Array")
class FilaSolicitacaoAmizadeTest {

    private FilaSolicitacaoAmizade fila;
    private SolicitacaoDTO solicitacao1;
    private SolicitacaoDTO solicitacao2;

    /**
     * SETUP: Configuração inicial antes de cada teste.
     *
     * OBJETIVO: Garantir que cada teste comece com uma fila vazia.
     * O construtor da classe já inicializa o array com 100 posições e tamanho 0.

    @BeforeEach
    void setUp() {
        fila = new FilaSolicitacaoAmizade();

        solicitacao1 = new SolicitacaoDTO(1, 2, "2024-01-15T10:30:00");
        solicitacao2 = new SolicitacaoDTO(3, 4, "2024-01-15T11:00:00");
    }

    /**
     * TESTE 1: Enfileirar primeira solicitação em fila vazia
     *
     * OBJETIVO: Verificar que a fila funciona corretamente quando está vazia.
     *
     * CENÁRIO: Adicionar uma solicitação em uma fila recém-criada.
     *
     * COMPORTAMENTO ESPERADO:
     * - A solicitação é adicionada na primeira posição (índice 0)
     * - O tamanho da fila aumenta de 0 para 1
     * - A solicitação retornada é igual à enviada

    @Test
    @DisplayName("1. Deve enfileirar primeira solicitação em fila vazia")
    void enfileirarPrimeiraSolicitacao_FilaVazia_AdicionaNaPosicaoZero() {
        // Executa
        SolicitacaoAmizade resultado = fila.enfileirar(solicitacao1);

        // Verifica
        assertNotNull(resultado, "A solicitação não deve ser nula");
        assertEquals(1, resultado.getIdRemetente(), "ID do remetente deve ser 1");
        assertEquals(2, resultado.getIdDestinatario(), "ID do destinatário deve ser 2");

        // Verifica estrutura interna da fila
        SolicitacaoAmizade[] filaInterna = fila.imprimirFila();
        assertNotNull(filaInterna[0], "Primeira posição do array não deve ser nula");
        assertEquals(resultado, filaInterna[0], "A solicitação deve estar na posição 0");
    }

    /**
     * TESTE 2: Desenfileirar de fila vazia
     *
     * OBJETIVO: Verificar o comportamento da fila quando tentamos remover de uma fila vazia.
     *
     * CENÁRIO: Chamar desenfileirar() em uma fila sem elementos.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve retornar Optional.empty() (não lançar exceção)
     * - O tamanho da fila permanece 0

    @Test
    @DisplayName("2. Deve retornar Optional vazio ao desenfileirar de fila vazia")
    void desenfileirar_FilaVazia_RetornaOptionalVazio() {
        // Executa
        Optional<SolicitacaoAmizade> resultado = fila.desenfileirar();

        // Verifica
        assertTrue(resultado.isEmpty(), "Deve retornar Optional vazio para fila vazia");
    }

    /**
     * TESTE 3: Desenfileirar única solicitação da fila
     *
     * OBJETIVO: Verificar o comportamento FIFO com um único elemento.
     *
     * CENÁRIO: Enfileirar uma solicitação e depois desenfileirar.
     *
     * COMPORTAMENTO ESPERADO:
     * - A solicitação desenfileirada deve ser a mesma que foi enfileirada
     * - Após desenfileirar, a fila deve ficar vazia
     * - A posição 0 do array deve ser nula após remoção

    @Test
    @DisplayName("3. Deve desenfileirar única solicitação da fila")
    void desenfileirar_FilaComUmElemento_RetornaSolicitacaoEFilaVazia() {
        // Prepara
        SolicitacaoAmizade solicitacaoEnfileirada = fila.enfileirar(solicitacao1);

        // Executa
        Optional<SolicitacaoAmizade> resultado = fila.desenfileirar();

        // Verifica
        assertTrue(resultado.isPresent(), "Deve retornar Optional com valor");
        assertEquals(solicitacaoEnfileirada, resultado.get(),
                "A solicitação desenfileirada deve ser a mesma que foi enfileirada");

        // Verifica estado interno após remoção
        SolicitacaoAmizade[] filaInterna = fila.imprimirFila();
        assertNull(filaInterna[0], "Posição 0 deve ser nula após desenfileirar");
    }

    /**
     * TESTE 4: Verificar ordem FIFO (First In, First Out)
     *
     * OBJETIVO: Comprovar que a fila segue o princípio FIFO.
     *
     * CENÁRIO: Enfileirar duas solicitações e desenfileirar duas vezes.
     *
     * COMPORTAMENTO ESPERADO:
     * - Primeiro desenfileiramento retorna a PRIMEIRA solicitação enfileirada
     * - Segundo desenfileiramento retorna a SEGUNDA solicitação enfileirada
     * - Após remover ambos, a fila fica vazia

    @Test
    @DisplayName("4. Deve manter ordem FIFO (primeiro a entrar, primeiro a sair)")
    void desenfileirar_DuasSolicitacoes_RespeitaOrdemFIFO() {
        // Prepara
        SolicitacaoAmizade primeira = fila.enfileirar(solicitacao1);
        SolicitacaoAmizade segunda = fila.enfileirar(solicitacao2);

        // Executa e verifica primeiro desenfileiramento
        Optional<SolicitacaoAmizade> resultado1 = fila.desenfileirar();
        assertTrue(resultado1.isPresent());
        assertEquals(primeira, resultado1.get(),
                "Primeira solicitação desenfileirada deve ser a primeira enfileirada");

        // Executa e verifica segundo desenfileiramento
        Optional<SolicitacaoAmizade> resultado2 = fila.desenfileirar();
        assertTrue(resultado2.isPresent());
        assertEquals(segunda, resultado2.get(),
                "Segunda solicitação desenfileirada deve ser a segunda enfileirada");

        // Verifica fila vazia
        Optional<SolicitacaoAmizade> resultado3 = fila.desenfileirar();
        assertTrue(resultado3.isEmpty(), "Fila deve estar vazia após remover todos");
    }

    /**
     * TESTE 5: Verificar deslocamento dos elementos após desenfileirar
     *
     * OBJETIVO: Compreender como a implementação com array realiza o deslocamento.
     *
     * CENÁRIO: Enfileirar 3 elementos, desenfileirar 1, e verificar a reorganização.
     *
     * COMPORTAMENTO ESPERADO:
     * - Após desenfileirar, o elemento do índice 1 vai para o índice 0
     * - Após desenfileirar, o elemento do índice 2 vai para o índice 1
     * - A última posição ocupada fica nula

    @Test
    @DisplayName("5. Deve deslocar elementos após desenfileirar")
    void desenfileirar_ComTresElementos_DeslocaElementos() {
        // Prepara
        SolicitacaoDTO solicitacao3 = new SolicitacaoDTO(5, 6, "2024-01-15T12:00:00");

        SolicitacaoAmizade primeira = fila.enfileirar(solicitacao1);
        SolicitacaoAmizade segunda = fila.enfileirar(solicitacao2);
        SolicitacaoAmizade terceira = fila.enfileirar(solicitacao3);

        // Verifica estado inicial
        SolicitacaoAmizade[] filaAntes = fila.imprimirFila();
        assertEquals(primeira, filaAntes[0], "Antes: posição 0 deve ter primeira");
        assertEquals(segunda, filaAntes[1], "Antes: posição 1 deve ter segunda");
        assertEquals(terceira, filaAntes[2], "Antes: posição 2 deve ter terceira");

        // Executa
        fila.desenfileirar();

        // Verifica estado após desenfileirar
        SolicitacaoAmizade[] filaDepois = fila.imprimirFila();
        assertEquals(segunda, filaDepois[0], "Depois: posição 0 deve ter segunda (deslocada)");
        assertEquals(terceira, filaDepois[1], "Depois: posição 1 deve ter terceira (deslocada)");
        assertNull(filaDepois[2], "Depois: posição 2 deve ser nula (última posição liberada)");
    }

    /**
     * TESTE 6: Verificar comportamento do método imprimirFila
     *
     * OBJETIVO: Entender que imprimirFila retorna a referência ao array interno.
     *
     * CENÁRIO: Enfileirar elementos e verificar que o array retornado é o mesmo.
     *
     * COMPORTAMENTO ESPERADO:
     * - O método retorna o array interno (não uma cópia)
     * - Modificações no array retornado afetam o estado interno (não recomendado)
     * - Posições não utilizadas além do tamanho atual são nulas

    @Test
    @DisplayName("6. Método imprimirFila retorna array interno")
    void imprimirFila_RetornaArrayInterno() {
        // Prepara
        fila.enfileirar(solicitacao1);
        fila.enfileirar(solicitacao2);

        // Executa
        SolicitacaoAmizade[] arrayRetornado = fila.imprimirFila();

        // Verifica
        assertNotNull(arrayRetornado, "Array retornado não deve ser nulo");
        assertEquals(100, arrayRetornado.length, "Array deve ter tamanho máximo 100");

        // Primeiras duas posições ocupadas
        assertNotNull(arrayRetornado[0], "Posição 0 ocupada");
        assertNotNull(arrayRetornado[1], "Posição 1 ocupada");

        // Posições além do tamanho atual são nulas
        assertNull(arrayRetornado[2], "Posição 2 deve ser nula (não utilizada ainda)");
        assertNull(arrayRetornado[99], "Última posição deve ser nula (não utilizada)");
    }*/
}