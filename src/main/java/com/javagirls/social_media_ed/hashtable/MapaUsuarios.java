package com.javagirls.social_media_ed.hashtable;

import com.javagirls.social_media_ed.usuario.Usuario;

import java.util.HashMap;
import java.util.LinkedList;

public class MapaUsuarios {
    HashMap<String, Usuario> hashMap = new HashMap<>();
    private LinkedList<Par>[] tabela;
    int capacidade = 10;

    public MapaUsuarios() {
        this.tabela = new LinkedList[10];

        for(int i = 0; i < capacidade; i++){
            tabela[i] = new LinkedList<>();
        }
        hashMap.entrySet();
    }

    private int calcularIndice(String username){

        int soma = 0;
        for(int i = 0; i < username.length(); i++){
            int caractere = username.charAt(i);
            soma = soma + (int) caractere;
        }
        return soma % capacidade;
    }
    public void cadastrar(String chave, Usuario usuario){
        Par par = new Par(chave, usuario);
        int indice = calcularIndice(chave);

        tabela[indice].add(par);
    }

    public Usuario buscar(String username){
        int indice = calcularIndice(username);
       LinkedList<Par>  lista =  tabela[indice];

       for (Par par : lista ){
           if(par.getChave().equalsIgnoreCase(username)){
               return par.getValor();
           }
       }
       return null;
    }
}
