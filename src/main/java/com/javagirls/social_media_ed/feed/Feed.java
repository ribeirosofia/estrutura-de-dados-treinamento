package com.javagirls.social_media_ed.feed;

import com.javagirls.social_media_ed.commons.UsuarioLogadoComponent;
import com.javagirls.social_media_ed.postagem.Postagem;
import com.javagirls.social_media_ed.postagem.PostagemNo;
import com.javagirls.social_media_ed.postagem.PostagemRepository;
import com.javagirls.social_media_ed.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Feed {
    private PostagemNo cabeca;
    private int tamanho;
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

    public PostagemNo adicionarNoInicio(String mensagem, Usuario autor) {
        autor = usuarioLogadoComponent.getUsuarioLogado();
        Postagem novaPostagem = new Postagem(mensagem, autor);

        postagemRepository.save(novaPostagem);

        PostagemNo novoNo = new PostagemNo(novaPostagem);
        if (cabeca != null) {
            novoNo.setProximo(cabeca);
            cabeca.setAnterior(novoNo);
        }
        cabeca = novoNo;
        tamanho++;

        return novoNo;

    }

    public int getTamanho() {
        return tamanho;
    }

    // 4. Obter cabeça da lista
    public PostagemNo getCabeca() {
        return cabeca;
    }


    public List<PostagemNo> getodasPostagens(){
        List<PostagemNo> listaPostagem = new ArrayList<PostagemNo>();

        for(int i = 0; i < tamanho; i++){
            listaPostagem.add(cabeca);
            cabeca = cabeca.getProximo();
        }

        return listaPostagem;
    }
 }