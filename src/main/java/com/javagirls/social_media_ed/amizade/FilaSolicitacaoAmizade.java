package com.javagirls.social_media_ed.amizade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilaSolicitacaoAmizade {
    private static final int TAMANHO_MAXIMO = 100;
    private SolicitacaoAmizade[] fila = new SolicitacaoAmizade[TAMANHO_MAXIMO];
    private int tamanhoAtual = 0;

    public SolicitacaoAmizade enfileirar(SolicitacaoDTO solicitacaoDados) {

        SolicitacaoAmizade novaSolicitacao = solicitacaoDados.toSolicitacaoAmizade();
        if(this.tamanhoAtual < TAMANHO_MAXIMO) {
            this.fila[tamanhoAtual] = novaSolicitacao;
            tamanhoAtual++;
        }
        return novaSolicitacao;

        // TODO: Implementar
        // - Converter DTO para SolicitacaoAmizade
        // - Verificar se há espaço na fila
        // - Adicionar no final do array
        // - Atualizar tamanhoAtual
        // - Retornar a solicitação enfileirada
    }

    // 2. Remover e retornar a solicitação mais antiga (desenfileirar)
//    public Optional<SolicitacaoAmizade> desenfileirar() {
//        // TODO: Implementar
//        // - Verificar se a fila está vazia
//        // - Remover a primeira solicitação (índice 0)
//        // - Deslocar todos os elementos restantes uma posição para frente
//        // - Atualizar tamanhoAtual
//        // - Retornar a solicitação removida (Optional)
//    }

    // 3. Verificar se a fila está vazia
//    public boolean estaVazia() {
//        // TODO: Implementar
//        // - Retornar true se tamanhoAtual == 0
//    }

    // 4. Verificar se a fila está cheia
//    public boolean estaCheia() {
//        // TODO: Implementar
//        // - Retornar true se tamanhoAtual == TAMANHO_MAXIMO
//    }

//     5. Obter tamanho atual da fila
//    public int getTamanhoAtual() {
//        return tamanhoAtual;
//    }

    // 6. Visualizar todas as solicitações na fila
//    public SolicitacaoAmizade[] imprimirFila() {
//        // TODO: Implementar
//        // - Retornar o array com as solicitações atuais
//        // - Considerar que posições vazias podem ser null
//        //}
}