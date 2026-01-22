package com.javagirls.social_media_ed.amizade;

public class SolicitacaoDTO {

    public Integer idRemetente;
    public Integer idDestinatario;
    public String mensagem;

    public SolicitacaoDTO(Integer idRemetente, Integer idDestinatario, String mensagem) {
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.mensagem = mensagem;
    }

    public SolicitacaoAmizade toSolicitacaoAmizade(){
        return new SolicitacaoAmizade(
                idRemetente = this.idRemetente,
                idDestinatario = this.idDestinatario,
                mensagem = this.mensagem
        );
    }
}
