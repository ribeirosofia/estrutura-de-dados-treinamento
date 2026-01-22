package com.javagirls.social_media_ed.feed;

import com.javagirls.social_media_ed.commons.UsuarioLogadoComponent;
import com.javagirls.social_media_ed.postagem.Postagem;
import com.javagirls.social_media_ed.postagem.PostagemNo;
import com.javagirls.social_media_ed.postagem.PostagemNoDTO;
import com.javagirls.social_media_ed.postagem.PostagemRepository;
import com.javagirls.social_media_ed.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe Feed.
 *
 * OBJETIVO DIDÁTICO:
 * Esta classe testa uma implementação de Feed que usa lista encadeada.
 * O Feed simula uma timeline onde postagens são adicionadas no início
 * (postagem mais recente é a cabeça da lista).
 *
 * CONCEITOS ENVOLVIDOS:
 * - Lista encadeada simples
 * - Padrão DTO (Data Transfer Object)
 * - Injeção de dependências
 * - Mocking de repositórios
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes da Classe Feed - Sistema de Timeline com Lista Encadeada")
class FeedTest {

    @Mock
    private UsuarioLogadoComponent usuarioLogadoComponent;
    @Mock
    private PostagemRepository postagemRepository;
    private Feed feed;
    private Usuario usuarioTeste;
    private Postagem postagemSalva;

    /**
     * SETUP: Configuração inicial antes de cada teste.
     *
     * OBJETIVO: Garantir que cada teste comece com um estado limpo.
     * Aqui criamos uma nova instância do Feed com os mocks injetados.
     */
    @BeforeEach
    void setUp() {
        // Inicializa o Feed com as dependências mockadas
        //feed = new Feed(usuarioLogadoComponent, postagemRepository);

        usuarioTeste = new Usuario();
        usuarioTeste.setId(1);
        usuarioTeste.setNomeUsuario("Ana Silva");
        usuarioTeste.setEmail("ana@email.com");

        postagemSalva = new Postagem("Mensagem de teste", usuarioTeste);
        postagemSalva.setId(100);
    }

    /**
     * TESTE 1: Adicionar primeira postagem no feed vazio
     *
     * OBJETIVO: Verificar que o Feed funciona corretamente quando está vazio.
     *
     * CENÁRIO: Adicionar uma postagem em um feed recém-criado (sem postagens).
     *
     * COMPORTAMENTO ESPERADO:
     * - A postagem se torna a "cabeça" (primeiro elemento) da lista
     * - O tamanho do feed aumenta de 0 para 1
     * - Não há postagens anterior ou próxima (null)

    @Test
    @DisplayName("1. Deve adicionar primeira postagem em feed vazio")
    void adicionarPrimeiraPostagem_FeedVazio_PostagemTornaCabeca() {
        String mensagem = "Minha primeira postagem!";
        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);
        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagemSalva);

        PostagemNo resultado = feed.adicionarNoInicio(mensagem);

        assertNotNull(resultado, "A postagem não deve ser nula");
        assertEquals(100, resultado.getId(), "ID da postagem deve ser 100");
        assertEquals("Mensagem de teste", resultado.getMensagem(), "Mensagem deve ser preservada");

        assertEquals(1, feed.getTamanho(), "Tamanho do feed deve ser 1");
        assertSame(feed.getCabeca(), resultado, "A postagem adicionada deve ser a cabeça do feed");

        assertNull(resultado.getAnterior(), "Primeira postagem não deve ter anterior");
        assertNull(resultado.getProximo(), "Primeira postagem não deve ter próximo");
    }

    /**
     * TESTE 2: Adicionar múltiplas postagens
     *
     * OBJETIVO: Demonstrar o comportamento de lista.
     *
     * CENÁRIO: Adicionar três postagens sequencialmente.
     *
     * COMPORTAMENTO ESPERADO:
     * - A última postagem adicionada é a primeira a aparecer (cabeça)
     * - As postagens formam uma lista encadeada com referências corretas
     * - O tamanho aumenta corretamente a cada adição

    @Test
    @DisplayName("2. Deve adicionar múltiplas postagens")
    void adicionarMultiplasPostagens_OrdemLIFO_CorretaEstruturaEncadeada() {
        Postagem postagem1 = new Postagem("Postagem 1", usuarioTeste);
        postagem1.setId(1);
        Postagem postagem2 = new Postagem("Postagem 2", usuarioTeste);
        postagem2.setId(2);
        Postagem postagem3 = new Postagem("Postagem 3", usuarioTeste);
        postagem3.setId(3);

        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);

        when(postagemRepository.save(any(Postagem.class)))
                .thenReturn(postagem1)
                .thenReturn(postagem2)
                .thenReturn(postagem3);

        PostagemNo resultado1 = feed.adicionarNoInicio("Postagem 1");
        PostagemNo resultado2 = feed.adicionarNoInicio("Postagem 2");
        PostagemNo resultado3 = feed.adicionarNoInicio("Postagem 3");

        assertEquals(3, feed.getTamanho(), "Feed deve ter 3 postagens");

        assertSame(resultado3, feed.getCabeca(), "Última postagem adicionada deve ser a cabeça");

        // Verifica encadeamento: 3 → 2 → 1 → null
        assertSame(resultado2, resultado3.getProximo(), "Postagem 3 deve apontar para Postagem 2");
        assertSame(resultado3, resultado2.getAnterior(), "Postagem 2 deve apontar de volta para Postagem 3");

        assertSame(resultado1, resultado2.getProximo(), "Postagem 2 deve apontar para Postagem 1");
        assertSame(resultado2, resultado1.getAnterior(), "Postagem 1 deve apontar de volta para Postagem 2");

        assertNull(resultado1.getProximo(), "Postagem 1 (última) não deve ter próximo");
        assertNull(resultado3.getAnterior(), "Postagem 3 (cabeça) não deve ter anterior");
    }

    /**
     * TESTE 3: Converter postagens para DTOs
     *
     * OBJETIVO: Testar a conversão de objetos de domínio para DTOs de transferência.
     *
     * CENÁRIO: Converter postagens do feed para DTOs para resposta da API.
     *
     * COMPORTAMENTO ESPERADO:
     * - Cada PostagemNo é convertido para PostagemNoDTO
     * - Os DTOs contêm informações simplificadas (sem referências encadeadas complexas)
     * - A ordem é preservada

    @Test
    @DisplayName("3. Deve converter postagens para DTOs simplificados")
    void getPostagensDTO_DeveConverterParaDTOs() {
        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);
        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagemSalva);

        feed.adicionarNoInicio("Mensagem para DTO");

        List<PostagemNoDTO> dtos = feed.getPostagensDTO();

        assertEquals(1, dtos.size(), "Deve retornar um DTO");

        PostagemNoDTO dto = dtos.get(0);
        assertEquals(100, dto.id(), "DTO deve conter o ID correto");
        assertEquals("Mensagem de teste", dto.mensagem(), "DTO deve conter a mensagem");
        assertEquals(1, dto.autorId(), "DTO deve conter ID do autor");
        assertEquals("Ana Silva", dto.autorNome(), "DTO deve conter nome do autor");
    }

    /**
     * TESTE 4: Atualizar contador de curtidas em postagem existente
     *
     * OBJETIVO: Testar a atualização de um atributo específico em uma postagem.
     *
     * CENÁRIO: Simular uma curtida em uma postagem já existente no feed.
     *
     * COMPORTAMENTO ESPERADO:
     * - Encontrar a postagem pelo ID
     * - Atualizar o contador de curtidas
     * - Não afetar outras postagens

    @Test
    @DisplayName("4. Deve atualizar contador de curtidas de postagem existente")
    void atualizarCurtidaNoFeed_PostagemExistente_DeveAtualizarContador() {
        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);

        Postagem postagem1 = new Postagem("Post 1", usuarioTeste);
        postagem1.setId(101);
        Postagem postagem2 = new Postagem("Post 2", usuarioTeste);
        postagem2.setId(102);

        when(postagemRepository.save(any(Postagem.class)))
                .thenReturn(postagem1)
                .thenReturn(postagem2);

        feed.adicionarNoInicio("Post 1");
        feed.adicionarNoInicio("Post 2");

        List<PostagemNo> postagens = feed.getTodasPostagens();
        assertEquals(0, postagens.get(0).getContadorCurtidas(), "Contador inicial deve ser 0");

        feed.atualizarCurtidaNoFeed(101, 15);

        postagens = feed.getTodasPostagens();

        PostagemNo postagemAtualizada = postagens.stream()
                .filter(p -> p.getId().equals(101))
                .findFirst()
                .orElse(null);

        assertNotNull(postagemAtualizada, "Deve encontrar a postagem com ID 101");
        assertEquals(15, postagemAtualizada.getContadorCurtidas(), "Contador deve ser atualizado para 15");

        PostagemNo outraPostagem = postagens.stream()
                .filter(p -> p.getId().equals(102))
                .findFirst()
                .orElse(null);

        assertEquals(0, outraPostagem.getContadorCurtidas(), "Outra postagem não deve ser alterada");
    }

    /**
     * TESTE 5: Tentar atualizar curtidas de postagem inexistente
     *
     * OBJETIVO: Testar o comportamento quando a postagem não é encontrada.
     *
     * CENÁRIO: Tentar atualizar uma postagem que não está no feed.
     *
     * COMPORTAMENTO ESPERADO:
     * - O método não deve lançar exceção
     * - O feed deve permanecer inalterado

    @Test
    @DisplayName("5. Deve lidar com tentativa de atualizar postagem inexistente")
    void atualizarCurtidaNoFeed_PostagemInexistente_NaoDeveLancarExcecao() {
        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);
        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagemSalva);

        feed.adicionarNoInicio("Postagem existente");

        int tamanhoInicial = feed.getTamanho();

        assertDoesNotThrow(() -> {
            feed.atualizarCurtidaNoFeed(999, 10); // ID que não existe
        });

        assertEquals(tamanhoInicial, feed.getTamanho(), "Tamanho do feed não deve mudar");

        PostagemNo postagem = feed.getCabeca();
        assertEquals(0, postagem.getContadorCurtidas(), "Contador não deve ser alterado");
    }

    /**
     * TESTE 6: Integração entre métodos do feed
     *
     * OBJETIVO: Demonstrar o fluxo completo de uso do feed.
     *
     * CENÁRIO: Usuário adiciona postagens, curte, e visualiza feed.
     *
     * COMPORTAMENTO ESPERADO:
     * - Todas as operações funcionam em conjunto
     * - O estado do feed é consistente

    @Test
    @DisplayName("6. Fluxo completo: adicionar, curtir e visualizar feed")
    void fluxoCompleto_AdicionarCurtirVisualizar_EstadoConsistente() {
        when(usuarioLogadoComponent.getUsuarioLogado()).thenReturn(usuarioTeste);

        Postagem postagem1 = new Postagem("Postagem para curtir", usuarioTeste);
        postagem1.setId(50);

        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagem1);

        PostagemNo postagemAdicionada = feed.adicionarNoInicio("Postagem para curtir");
        assertEquals(1, feed.getTamanho(), "Feed deve ter 1 postagem");

        List<PostagemNo> visualizacao = feed.getTodasPostagens();
        assertEquals(1, visualizacao.size(), "Deve ver 1 postagem");
        assertEquals(0, visualizacao.get(0).getContadorCurtidas(), "Inicialmente sem curtidas");

        feed.atualizarCurtidaNoFeed(50, 42);

        visualizacao = feed.getTodasPostagens();
        assertEquals(42, visualizacao.get(0).getContadorCurtidas(), "Contador deve ser atualizado");

        FeedDTO feedDTO = feed.toDTO();
        assertEquals(1, feedDTO.getTamanho(), "DTO reflete tamanho correto");
        assertEquals(42, feedDTO.getPostagens().get(0).contadorCurtidas(), "DTO reflete curtidas atualizadas");
    }*/
}