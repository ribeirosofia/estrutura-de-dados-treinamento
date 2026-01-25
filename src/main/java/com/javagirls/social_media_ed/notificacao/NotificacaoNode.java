package com.javagirls.social_media_ed.notificacao;

public class NotificacaoNode {
    public String mensagem;
    public NotificacaoNode anterior;

    @Override
 public String toString(){
     return "NotificacaoNode{mensagem=' "+ mensagem + "' }";
 }


    public NotificacaoNode getAnterior() {
        return anterior;
    }
}
