package com.javagirls.social_media_ed.feed;

import com.javagirls.social_media_ed.postagem.PostagemNo;

public class FeedService {

    private final Feed feed;

    public FeedService(Feed feed) {
        this.feed = feed;
    }

    public PostagemNo adicionarPostagemNoFeed(String mensagem) {
        return null;
    }

    public void atualizarCurtidaNoFeed(Integer id, Integer contadorCurtidas) {
    }

    public FeedDTO toDTO() {
        return feed.toDTO();
    }
}
