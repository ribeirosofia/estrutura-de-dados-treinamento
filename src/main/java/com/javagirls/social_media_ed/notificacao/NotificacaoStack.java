package com.javagirls.social_media_ed.notificacao;

public class NotificacaoStack {
    NotificacaoNode maisRecente;

    public void adicionaNotificacao(NotificacaoNode novaNotificacao){
     novaNotificacao.anterior = this.maisRecente;
     this.maisRecente = novaNotificacao;
    }

    public void removeNotificacao(){
        if(maisRecente == null){
            System.out.println("Nenhuma notificação para remover");
            return;
        }
        this.maisRecente = maisRecente.anterior;
        System.out.println("Notificação removida, mais recente agora: " + maisRecente);
    }

}
