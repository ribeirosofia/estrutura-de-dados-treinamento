package com.javagirls.social_media_ed.feed;

import com.javagirls.social_media_ed.postagem.*;
import com.javagirls.social_media_ed.usuario.Usuario;
import com.javagirls.social_media_ed.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feed;
    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;

    public FeedController(FeedService feed, PostagemRepository postagemRepository, UsuarioRepository usuarioRepository) {
        this.feed = feed;
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/postagem")
    public ResponseEntity<PostagemNoDTO> adicionarPostagem(@RequestBody RequisicaoPostagem request) {
        PostagemNo novaPostagem = feed.adicionarPostagemNoFeed(request.mensagem());
        return ResponseEntity.ok(novaPostagem.toDTO());
    }

    @PostMapping("/postagem/curtidas")
    public ResponseEntity<?> curtirPostagem(@RequestBody RequisicaoCurtidaPostagem requisicao) {

        Usuario usuario = usuarioRepository.findById(requisicao.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Postagem postagem = postagemRepository.findById(requisicao.idPostagem())
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        String response = "";

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<FeedDTO> getFeed() {
        return ResponseEntity.ok(feed.toDTO());
    }
}