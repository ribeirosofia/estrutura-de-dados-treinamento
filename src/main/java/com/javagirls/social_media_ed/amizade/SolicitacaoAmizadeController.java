package com.javagirls.social_media_ed.amizade;

import com.javagirls.social_media_ed.commons.UsuarioLogadoComponent;
import com.javagirls.social_media_ed.grafo.ListaAdjacenteGrafo;
import com.javagirls.social_media_ed.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes-amizade")
public class SolicitacaoAmizadeController {

    private final AmizadeService amizadeService;
    private final ListaAdjacenteGrafo grafo;
    private final UsuarioLogadoComponent usuarioLogadoComponent;

    public SolicitacaoAmizadeController(AmizadeService amizadeService, ListaAdjacenteGrafo grafo, UsuarioLogadoComponent usuarioLogadoComponent) {
        this.amizadeService = amizadeService;
        this.grafo = grafo;
        this.usuarioLogadoComponent = usuarioLogadoComponent;
    }

    @PostMapping
    public ResponseEntity<String> criarSolicitacao(
            @RequestBody SolicitacaoDTO solicitacaoDados) {

        SolicitacaoAmizade solicitacao = amizadeService.adicionaAmizade(solicitacaoDados);

        return ResponseEntity.ok("Solicitação criada de "+solicitacao.getIdRemetente()+" para "+solicitacao.getIdDestinatario());
    }

    @GetMapping
    public ResponseEntity<SolicitacaoAmizade[]> listarSolicitacoes() {
        SolicitacaoAmizade[] filaRetornada = amizadeService.listarSolicitacoes();
        return ResponseEntity.ok(filaRetornada);
    }

    @GetMapping("/amigos")
    public ResponseEntity<?> minhasConexoes() {
        Integer usuarioId = usuarioLogadoComponent.getUsuarioLogado().getId();
        return ResponseEntity.ok("");
    }

    @PostMapping("/aceitar")
    public ResponseEntity<String> aceitarSolicitacao(
            @RequestBody SolicitacaoDTO solicitacaoDados) {

        amizadeService.excluirSolicitacao();

        return ResponseEntity.ok("Solicitação aceita de "+solicitacaoDados.idRemetente+" para "+solicitacaoDados.idDestinatario);
    }
}