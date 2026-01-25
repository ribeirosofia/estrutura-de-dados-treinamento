package com.javagirls.social_media_ed.hashtable;

import com.javagirls.social_media_ed.usuario.Usuario;

public class Par {

    private Usuario valor;
    private String chave;

    public Par(String chave, Usuario valor){
        this.chave = chave;
        this.valor = valor;
    }

    public String getChave(){
        return chave;
    }

    public Usuario getValor(){
        return valor;
    }
}
