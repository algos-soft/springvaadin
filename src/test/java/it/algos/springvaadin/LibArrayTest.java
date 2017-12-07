package it.algos.springvaadin;

import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.LibArray;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 18-nov-2017
 * Time: 09:48
 */
@Slf4j
public class LibArrayTest {


    private final static String[] stringArray = {"primo", "secondo", "quarto", "quinto", "1Ad", "terzo", "a10"};
    private final static List<String> lista = LibArray.fromString(stringArray);
    private List<String> prevista;
    private List<String> ottenuta;


    @Test
    public void inseriscePrima() {
        String[] stringPrevista = {"primo", "secondo", "terzo", "quarto", "quinto", "1Ad",  "a10"};
        prevista = LibArray.fromString(stringPrevista);

        ottenuta = LibArray.inseriscePrima(lista, "terzo", "quarto");
        assertEquals(prevista, ottenuta);
    }// end of single test

    @Test
    public void inserisceDopo() {
        String[] stringPrevista = {"primo", "secondo", "terzo", "quarto", "quinto", "1Ad",  "a10"};
        prevista = LibArray.fromString(stringPrevista);

        ottenuta = LibArray.inserisceDopo(lista, "terzo", "secondo");
        assertEquals(prevista, ottenuta);
    }// end of single test

}// end of class
