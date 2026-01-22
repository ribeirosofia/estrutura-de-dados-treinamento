package com.javagirls.social_media_ed.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javagirls.social_media_ed.commons.UsuarioLogadoComponent;
import com.javagirls.social_media_ed.postagem.Postagem;
import com.javagirls.social_media_ed.postagem.PostagemNo;
import com.javagirls.social_media_ed.postagem.PostagemNoDTO;
import com.javagirls.social_media_ed.postagem.PostagemRepository;
import com.javagirls.social_media_ed.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Feed {
    private final UsuarioLogadoComponent usuarioLogadoComponent;
    private final PostagemRepository postagemRepository;

    public Feed(UsuarioLogadoComponent usuarioLogadoComponent, PostagemRepository postagemRepository) {
        this.usuarioLogadoComponent = usuarioLogadoComponent;
        this.postagemRepository = postagemRepository;
    }

    public FeedDTO toDTO() {
        return new FeedDTO(Collections.emptyList(), 0);
    }

    public void carregarDoBanco() {
        List<Postagem> todasPostagensBanco = postagemRepository.findAllByOrderByDataCriacaoDesc();

        // Limpar lista atual


        // Adicionar do mais recente para o mais antigo

    }
}