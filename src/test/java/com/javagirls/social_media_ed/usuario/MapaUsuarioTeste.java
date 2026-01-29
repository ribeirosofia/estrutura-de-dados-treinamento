package com.javagirls.social_media_ed.usuario;

import com.javagirls.social_media_ed.hashtable.MapaUsuarios;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapaUsuarioTeste {

    @Test
    void deveEncontrarOPedro() {
        MapaUsuarios mapa = new MapaUsuarios();

        Usuario pedro = new Usuario("Pedro", "pedro@email.com", "1234");
        Usuario lucas = new Usuario("Lucas", "lucas@email.com", "1234");

        mapa.cadastrar("@pedro", pedro);
        mapa.cadastrar("@lucas", lucas);

        Usuario resultado = mapa.buscar("@pedro");

        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getNomeUsuario());
    }

    @Test
    void deveLidarComColisao() {
        MapaUsuarios mapa = new MapaUsuarios();

        Usuario pedro = new Usuario("Pedro", "pedro@email.com", "1234");
        Usuario estela = new Usuario("Estela", "estela@email.com", "1234");

        mapa.cadastrar("@pedro", pedro);
        mapa.cadastrar("@estela", estela);

        Usuario resultadoPedro = mapa.buscar("@pedro");
        Usuario resultadoEstela = mapa.buscar("@estela");

        assertNotNull(resultadoPedro);
        assertEquals("Pedro", resultadoPedro.getNomeUsuario());

        assertNotNull(resultadoEstela);
        assertEquals("Estela", resultadoEstela.getNomeUsuario());
    }

}