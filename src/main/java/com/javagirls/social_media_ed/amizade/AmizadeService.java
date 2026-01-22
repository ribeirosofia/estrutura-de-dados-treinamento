package com.javagirls.social_media_ed.amizade;

import org.springframework.stereotype.Service;

@Service
public class AmizadeService {

    private final FilaSolicitacaoAmizade fila;

    public AmizadeService(FilaSolicitacaoAmizade fila) {
        this.fila = fila;
    }

    public SolicitacaoAmizade adicionaAmizade(SolicitacaoDTO solicitacaoDados) {
        return null;
    }

    public SolicitacaoAmizade[] listarSolicitacoes() {
        return null;
    }

    public void excluirSolicitacao() {

    }
}
