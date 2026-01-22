package com.javagirls.social_media_ed.grafo;

import com.javagirls.social_media_ed.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe ListaAdjacenteGrafo.
 *
 * OBJETIVO DIDÁTICO:
 * Esta classe testa uma implementação de grafo não direcionado usando lista de adjacência.
 * O grafo representa conexões sociais onde usuários são vértices e amizades são arestas.
 *
 * CONCEITOS ENVOLVIDOS:
 * - Grafo não direcionado (se A conecta com B, então B conecta com A)
 * - Lista de adjacência (Map<Integer, Set<Integer>>)
 * - Vértices (usuários) e arestas (conexões)
 * - Thread-safe (ConcurrentHashMap)
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes da Classe ListaAdjacenteGrafo - Grafo de Amizades com Lista de Adjacência")
class ListaAdjacenteGrafoTest {

    private ListaAdjacenteGrafo grafo;
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    /**
     * SETUP: Configuração inicial antes de cada teste.
     *
     * OBJETIVO: Garantir que cada teste comece com um grafo vazio.
     * Aqui criamos uma nova instância do grafo e alguns usuários para testes.
     */
    @BeforeEach
    void setUp() {
        // Inicializa um grafo vazio
        grafo = new ListaAdjacenteGrafo();

        // Cria usuários de teste
        usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNomeUsuario("Ana Silva");
        usuario1.setEmail("ana@email.com");

        usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNomeUsuario("Bruno Santos");
        usuario2.setEmail("bruno@email.com");

        usuario3 = new Usuario();
        usuario3.setId(3);
        usuario3.setNomeUsuario("Carla Oliveira");
        usuario3.setEmail("carla@email.com");
    }

    /**
     * TESTE 1: Adicionar primeiro usuário ao grafo vazio
     *
     * OBJETIVO: Verificar que o grafo adiciona corretamente um usuário quando está vazio.
     *
     * CENÁRIO: Adicionar um usuário a um grafo recém-criado (sem vértices).
     *
     * COMPORTAMENTO ESPERADO:
     * - O usuário deve ser adicionado ao mapa de vértices
     * - Deve ser criado um conjunto vazio de adjacências para esse usuário
     * - O usuário deve ser recuperado pelo ID
     */
    @Test
    @DisplayName("1. Deve adicionar usuário ao grafo vazio")
    void adicionarUsuario_GrafoVazio_UsuarioAdicionado() {
        // Executa
        grafo.adicionarUsuario(usuario1);

        // Verifica
        Optional<Usuario> resultado = grafo.buscarUsuario(usuario1.getId());
        assertTrue(resultado.isPresent(), "O usuário deve estar presente no grafo");
        assertEquals(usuario1, resultado.get(), "O usuário recuperado deve ser o mesmo adicionado");
    }

    /**
     * TESTE 2: Tentar adicionar usuário nulo
     *
     * OBJETIVO: Verificar que o grafo rejeita usuários nulos com exceção apropriada.
     *
     * CENÁRIO: Chamar adicionarUsuario com null.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve lançar IllegalArgumentException com mensagem específica
     */
    @Test
    @DisplayName("2. Deve lançar exceção ao adicionar usuário nulo")
    void adicionarUsuario_UsuarioNulo_LancaExcecao() {
        // Executa e verifica
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.adicionarUsuario(null);
        });

        assertEquals("Usuário não pode ser nulo", exception.getMessage());
    }

    /**
     * TESTE 3: Adicionar usuário duplicado (mesmo ID)
     *
     * OBJETIVO: Verificar que o grafo não duplica usuários com o mesmo ID.
     *
     * CENÁRIO: Tentar adicionar um usuário com ID que já existe no grafo.
     *
     * COMPORTAMENTO ESPERADO:
     * - Não deve lançar exceção (comportamento atual)
     * - Deve manter o usuário original
     * - Não deve criar novo conjunto de adjacências
     */
    @Test
    @DisplayName("3. Não deve duplicar usuário com mesmo ID")
    void adicionarUsuario_UsuarioDuplicado_MantemOriginal() {
        // Prepara
        grafo.adicionarUsuario(usuario1);

        // Cria outro usuário com mesmo ID
        Usuario usuarioDuplicado = new Usuario();
        usuarioDuplicado.setId(1);
        usuarioDuplicado.setNomeUsuario("Ana Outra");

        // Executa
        grafo.adicionarUsuario(usuarioDuplicado);

        // Verifica que ainda retorna o usuário original
        Optional<Usuario> resultado = grafo.buscarUsuario(1);
        assertTrue(resultado.isPresent());
        assertEquals("Ana Silva", resultado.get().getNomeUsuario(), "Deve manter o usuário original");
    }

    /**
     * TESTE 4: Buscar usuário que não existe
     *
     * OBJETIVO: Verificar o comportamento quando se busca um usuário inexistente.
     *
     * CENÁRIO: Buscar um ID que nunca foi adicionado ao grafo.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve retornar Optional.empty()
     */
    @Test
    @DisplayName("4. Deve retornar Optional vazio ao buscar usuário inexistente")
    void buscarUsuario_UsuarioInexistente_RetornaOptionalVazio() {
        // Executa
        Optional<Usuario> resultado = grafo.buscarUsuario(999);

        // Verifica
        assertTrue(resultado.isEmpty(), "Deve retornar Optional vazio para usuário inexistente");
    }

    /**
     * TESTE 5: Adicionar conexão entre dois usuários existentes
     *
     * OBJETIVO: Verificar que o grafo cria arestas (conexões) bidirecionais corretamente.
     *
     * CENÁRIO: Adicionar uma conexão entre dois usuários que estão no grafo.
     *
     * COMPORTAMENTO ESPERADO:
     * - Ambos os usuários devem aparecer nas listas de adjacência um do outro
     * - A conexão deve ser bidirecional (grafo não direcionado)
     */
    @Test
    @DisplayName("5. Deve adicionar conexão bidirecional entre usuários")
    void adicionarConexao_DoisUsuariosExistentes_ConexaoBidirecional() {
        // Prepara
        grafo.adicionarUsuario(usuario1);
        grafo.adicionarUsuario(usuario2);

        // Executa
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());

        // Verifica
        List<Usuario> conexoesUsuario1 = grafo.getConexoesUsuario(usuario1.getId());
        List<Usuario> conexoesUsuario2 = grafo.getConexoesUsuario(usuario2.getId());

        assertAll(
                () -> assertEquals(1, conexoesUsuario1.size(), "Usuário1 deve ter 1 conexão"),
                () -> assertTrue(conexoesUsuario1.contains(usuario2), "Usuário1 deve estar conectado com Usuário2"),
                () -> assertEquals(1, conexoesUsuario2.size(), "Usuário2 deve ter 1 conexão"),
                () -> assertTrue(conexoesUsuario2.contains(usuario1), "Usuário2 deve estar conectado com Usuário1")
        );
    }

    /**
     * TESTE 6: Tentar adicionar conexão com usuário inexistente
     *
     * OBJETIVO: Verificar que o grafo não cria conexões com vértices inexistentes.
     *
     * CENÁRIO: Chamar adicionarConexao com um ID de usuário que não está no grafo.
     *
     * COMPORTAMENTO ESPERADO:
     * - Não deve lançar exceção (comportamento atual)
     * - Não deve alterar as adjacências dos usuários existentes
     */
    @Test
    @DisplayName("6. Não deve adicionar conexão se um dos usuários não existe")
    void adicionarConexao_UmUsuarioInexistente_NaoAdicionaConexao() {
        // Prepara: adiciona apenas o usuario1
        grafo.adicionarUsuario(usuario1);

        // Executa: tenta adicionar conexão com usuario2 (que não foi adicionado)
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());

        // Verifica que usuario1 não tem conexões
        List<Usuario> conexoesUsuario1 = grafo.getConexoesUsuario(usuario1.getId());
        assertTrue(conexoesUsuario1.isEmpty(), "Usuário1 não deve ter conexões");
    }

    /**
     * TESTE 7: Obter conexões de usuário sem conexões
     *
     * OBJETIVO: Verificar o retorno para usuário sem amizades.
     *
     * CENÁRIO: Buscar conexões de um usuário que foi adicionado mas não tem conexões.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve retornar lista vazia (não nula)
     */
    @Test
    @DisplayName("7. Deve retornar lista vazia para usuário sem conexões")
    void getConexoesUsuario_UsuarioSemConexoes_RetornaListaVazia() {
        // Prepara
        grafo.adicionarUsuario(usuario1);

        // Executa
        List<Usuario> conexoes = grafo.getConexoesUsuario(usuario1.getId());

        // Verifica
        assertNotNull(conexoes, "Lista não deve ser nula");
        assertTrue(conexoes.isEmpty(), "Lista deve estar vazia para usuário sem conexões");
    }

    /**
     * TESTE 8: Obter conexões de usuário que não existe
     *
     * OBJETIVO: Verificar o comportamento quando se busca conexões de vértice inexistente.
     *
     * CENÁRIO: Chamar getConexoesUsuario com ID que nunca foi adicionado.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve retornar lista vazia (Collections.emptyList())
     */
    @Test
    @DisplayName("8. Deve retornar lista vazia ao buscar conexões de usuário inexistente")
    void getConexoesUsuario_UsuarioInexistente_RetornaListaVazia() {
        // Executa
        List<Usuario> conexoes = grafo.getConexoesUsuario(999);

        // Verifica
        assertNotNull(conexoes, "Lista não deve ser nula");
        assertTrue(conexoes.isEmpty(), "Deve retornar lista vazia para usuário inexistente");
    }

    /**
     * TESTE 9: Grafo com múltiplas conexões (estrela)
     *
     * OBJETIVO: Verificar que um usuário pode ter múltiplas conexões.
     *
     * CENÁRIO: Criar configuração onde um usuário central conecta com vários outros.
     *
     * COMPORTAMENTO ESPERADO:
     * - O usuário central deve aparecer nas listas de todos os conectados
     * - Cada usuário periférico deve ter apenas o central como conexão
     */
    @Test
    @DisplayName("9. Deve gerenciar múltiplas conexões (configuração em estrela)")
    void adicionarConexao_MultiplasConexoes_ConfiguracaoEstrela() {
        // Prepara
        grafo.adicionarUsuario(usuario1);  // Centro
        grafo.adicionarUsuario(usuario2);  // Periferia
        grafo.adicionarUsuario(usuario3);  // Periferia

        // Executa: usuario1 conecta com usuario2 e usuario3
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());
        grafo.adicionarConexao(usuario1.getId(), usuario3.getId());

        // Verifica
        List<Usuario> conexoesCentro = grafo.getConexoesUsuario(usuario1.getId());
        List<Usuario> conexoesPerif1 = grafo.getConexoesUsuario(usuario2.getId());
        List<Usuario> conexoesPerif2 = grafo.getConexoesUsuario(usuario3.getId());

        assertAll(
                () -> assertEquals(2, conexoesCentro.size(), "Centro deve ter 2 conexões"),
                () -> assertTrue(conexoesCentro.contains(usuario2), "Centro conectado com Periferia1"),
                () -> assertTrue(conexoesCentro.contains(usuario3), "Centro conectado com Periferia2"),

                () -> assertEquals(1, conexoesPerif1.size(), "Periferia1 deve ter 1 conexão"),
                () -> assertTrue(conexoesPerif1.contains(usuario1), "Periferia1 conectado apenas com Centro"),

                () -> assertEquals(1, conexoesPerif2.size(), "Periferia2 deve ter 1 conexão"),
                () -> assertTrue(conexoesPerif2.contains(usuario1), "Periferia2 conectado apenas com Centro")
        );
    }

    /**
     * TESTE 10: Conexão duplicada (idempotência)
     *
     * OBJETIVO: Verificar que adicionar a mesma conexão múltiplas vezes não duplica.
     *
     * CENÁRIO: Chamar adicionarConexao com os mesmos IDs várias vezes.
     *
     * COMPORTAMENTO ESPERADO:
     * - Deve manter apenas uma conexão (Set não permite duplicatas)
     * - Não deve lançar exceção
     */
    @Test
    @DisplayName("10. Não deve duplicar conexão ao adicionar a mesma aresta múltiplas vezes")
    void adicionarConexao_MesmaConexaoDuasVezes_NaoDuplica() {
        // Prepara
        grafo.adicionarUsuario(usuario1);
        grafo.adicionarUsuario(usuario2);

        // Executa: adiciona a mesma conexão duas vezes
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());

        // Verifica que ainda há apenas uma conexão
        List<Usuario> conexoesUsuario1 = grafo.getConexoesUsuario(usuario1.getId());
        assertEquals(1, conexoesUsuario1.size(), "Deve haver apenas 1 conexão (não duplicada)");
    }

    /**
     * TESTE 11: Conexão reflexiva (usuário consigo mesmo)
     *
     * OBJETIVO: Verificar se o grafo permite self-loops (laços).
     *
     * CENÁRIO: Tentar conectar um usuário com ele mesmo.
     *
     * COMPORTAMENTO ESPERADO:
     * - O código atual permite, mas em grafos sociais isso não faz sentido
     * - Pode ser um caso de borda a ser tratado
     */
    @Test
    @DisplayName("11. Pode adicionar conexão de usuário consigo mesmo (self-loop)")
    void adicionarConexao_MesmoUsuario_AdicionaSelfLoop() {
        // Prepara
        grafo.adicionarUsuario(usuario1);

        // Executa
        grafo.adicionarConexao(usuario1.getId(), usuario1.getId());

        // Verifica
        List<Usuario> conexoes = grafo.getConexoesUsuario(usuario1.getId());
        assertEquals(1, conexoes.size(), "Usuário deve ter 1 conexão consigo mesmo");
        assertTrue(conexoes.contains(usuario1), "A conexão é com o próprio usuário");
    }

    /**
     * TESTE 12: Grafo completo (todos conectados com todos)
     *
     * OBJETIVO: Verificar o comportamento do grafo com configuração completa.
     *
     * CENÁRIO: Criar 3 usuários e conectar todos entre si.
     *
     * COMPORTAMENTO ESPERADO:
     * - Cada usuário deve ter 2 conexões (todos os outros)
     * - Total de arestas: n*(n-1)/2 = 3 arestas (contadas 6 vezes, mas 3 únicas)
     */
    @Test
    @DisplayName("12. Deve criar grafo completo (todos conectados com todos)")
    void adicionarConexao_GrafoCompleto_TodosConectados() {
        // Prepara
        grafo.adicionarUsuario(usuario1);
        grafo.adicionarUsuario(usuario2);
        grafo.adicionarUsuario(usuario3);

        // Executa: cria todas as conexões possíveis
        grafo.adicionarConexao(usuario1.getId(), usuario2.getId());
        grafo.adicionarConexao(usuario1.getId(), usuario3.getId());
        grafo.adicionarConexao(usuario2.getId(), usuario3.getId());

        // Verifica
        List<Usuario> conexoes1 = grafo.getConexoesUsuario(usuario1.getId());
        List<Usuario> conexoes2 = grafo.getConexoesUsuario(usuario2.getId());
        List<Usuario> conexoes3 = grafo.getConexoesUsuario(usuario3.getId());

        assertAll(
                () -> assertEquals(2, conexoes1.size(), "Usuario1 deve ter 2 conexões"),
                () -> assertTrue(conexoes1.contains(usuario2), "Usuario1 conectado com Usuario2"),
                () -> assertTrue(conexoes1.contains(usuario3), "Usuario1 conectado com Usuario3"),

                () -> assertEquals(2, conexoes2.size(), "Usuario2 deve ter 2 conexões"),
                () -> assertTrue(conexoes2.contains(usuario1), "Usuario2 conectado com Usuario1"),
                () -> assertTrue(conexoes2.contains(usuario3), "Usuario2 conectado com Usuario3"),

                () -> assertEquals(2, conexoes3.size(), "Usuario3 deve ter 2 conexões"),
                () -> assertTrue(conexoes3.contains(usuario1), "Usuario3 conectado com Usuario1"),
                () -> assertTrue(conexoes3.contains(usuario2), "Usuario3 conectado com Usuario2")
        );
    }
}