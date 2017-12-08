package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.enumeration.EAFirstChar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 13:45
 * Classe di Libreria
 * Gestione e formattazione di stringhe di testo
 */
@SpringComponent
@Scope("singleton")
public class ATextService {


    /**
     * Null-safe, short-circuit evaluation.
     *
     * @param stringa in ingresso da controllare
     *
     * @return vero se la stringa è vuota o nulla
     */
    public boolean isEmpty(final String stringa) {
        return stringa == null || stringa.trim().isEmpty();
    }// end of method


    /**
     * Null-safe, short-circuit evaluation.
     *
     * @param stringa in ingresso da controllare
     *
     * @return vero se la stringa esiste è non è vuota
     */
    public boolean isValid(final String stringa) {
        return !isEmpty(stringa);
    }// end of method


    /**
     * Controlla che sia una stringa e che sia valida.
     *
     * @param obj in ingresso da controllare
     *
     * @return vero se la stringa esiste è non è vuota
     */
    public boolean isValid(final Object obj) {
        if (obj instanceof String) {
            return !isEmpty((String) obj);
        } else {
            return false;
        }// end of if/else cycle
    }// end of method


    /**
     * Forza il primo carattere della stringa secondo il flag
     * <p>
     * Se la stringa è nulla, ritorna un nullo
     * Se la stringa è vuota, ritorna una stringa vuota
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     * @param flag    maiuscolo o minuscolo
     *
     * @return uscita string in uscita
     */
    private String primoCarattere(final String testoIn, EAFirstChar flag) {
        String testoOut = testoIn.trim();
        String primo;
        String rimanente;

        if (this.isValid(testoOut)) {
            primo = testoOut.substring(0, 1);
            switch (flag) {
                case maiuscolo:
                    primo = primo.toUpperCase();
                    break;
                case minuscolo:
                    primo = primo.toLowerCase();
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
            rimanente = testoOut.substring(1);
            testoOut = primo + rimanente;
        }// fine del blocco if

        return testoOut.trim();
    }// end of method

    /**
     * Forza il primo carattere della stringa al carattere maiuscolo
     * <p>
     * Se la stringa è nulla, ritorna un nullo
     * Se la stringa è vuota, ritorna una stringa vuota
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     *
     * @return test formattato in uscita
     */
    public String primaMaiuscola(final String testoIn) {
        return primoCarattere(testoIn, EAFirstChar.maiuscolo);
    }// end of method

    /**
     * Forza il primo carattere della stringa al carattere minuscolo
     * <p>
     * Se la stringa è nulla, ritorna un nullo
     * Se la stringa è vuota, ritorna una stringa vuota
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     *
     * @return test formattato in uscita
     */
    public String primaMinuscola(final String testoIn) {
        return primoCarattere(testoIn, EAFirstChar.minuscolo);
    }// end of method


    /**
     * Elimina dal testo il tagIniziale, se esiste
     * <p>
     * Esegue solo se il testo è valido
     * Se tagIniziale è vuoto, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn     ingresso
     * @param tagIniziale da eliminare
     *
     * @return test ridotto in uscita
     */
    public String levaTesta(final String testoIn, String tagIniziale) {
        String testoOut = testoIn.trim();

        if (this.isValid(testoOut) && this.isValid(tagIniziale)) {
            tagIniziale = tagIniziale.trim();
            if (testoOut.startsWith(tagIniziale)) {
                testoOut = testoOut.substring(tagIniziale.length());
            }// fine del blocco if
        }// fine del blocco if

        return testoOut.trim();
    }// end of method

    /**
     * Elimina dal testo il tagFinale, se esiste
     * <p>
     * Esegue solo se il testo è valido
     * Se tagIniziale è vuoto, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn   ingresso
     * @param tagFinale da eliminare
     *
     * @return test ridotto in uscita
     */
    public String levaCoda(final String testoIn, String tagFinale) {
        String testoOut = testoIn.trim();

        if (this.isValid(testoOut) && this.isValid(tagFinale)) {
            tagFinale = tagFinale.trim();
            if (testoOut.endsWith(tagFinale)) {
                testoOut = testoOut.substring(0, testoOut.length() - tagFinale.length());
            }// fine del blocco if
        }// fine del blocco if

        return testoOut.trim();
    }// end of method


    /**
     * Sostituisce nel testo tutte le occorrenze di oldTag con newTag.
     * Esegue solo se il testo è valido
     * Esegue solo se il oldTag è valido
     * newTag può essere vuoto (per cancellare le occorrenze di oldTag)
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso da elaborare
     * @param oldTag  da sostituire
     * @param newTag  da inserire
     *
     * @return testo modificato
     */
    public String sostituisce(final String testoIn, String oldTag, String newTag) {
        String testoOut = "";
        String prima = "";
        String rimane = testoIn;
        int pos = 0;

        if (this.isValid(testoIn) && this.isValid(oldTag)) {
            if (rimane.contains(oldTag)) {
                pos = rimane.indexOf(oldTag);

                while (pos != -1) {
                    pos = rimane.indexOf(oldTag);
                    if (pos != -1) {
                        prima += rimane.substring(0, pos);
                        prima += newTag;
                        pos += oldTag.length();
                        rimane = rimane.substring(pos);
                    }// fine del blocco if
                }// fine di while

                testoOut = prima + rimane;
            }// fine del blocco if
        }// fine del blocco if

        return testoOut.trim();
    }// end of  method

}// end of class
