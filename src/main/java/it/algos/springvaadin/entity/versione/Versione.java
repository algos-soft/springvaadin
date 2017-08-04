package it.algos.springvaadin.entity.versione;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by gac on 01/06/17
 * <p>
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AlgosEntity che contiene la key property ObjectId
 * <p>
 * Tipicamente usata dal developer per gestire le versioni, patch e release dell'applicazione
 * Non prevede la differenziazione per Company
 */
@SpringComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Versione extends AlgosEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * ordine di creazione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * inserito automaticamente
     * se si cancella una entity, rimane il 'buco' del numero
     */
    @NotNull
    @AIField(type = AFType.integer, enabled = false, widthEM = 3, help = "Ordine di creazione. Unico e normalmente progressivo.")
    @AIColumn(name = "#", width = 80)
    private int ordine;


    /**
     * codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
     */
    @NotEmpty(message = "Il titolo è obbligatorio")
    @Size(min = 2, max = 20)
    @AIField(type = AFType.text, help = "Tipologia della versione.")
    @AIColumn()
    private String titolo;


    /**
     * descrizione (obbligatoria, non unica)
     * non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
     */
    @NotEmpty(message = "La descrizione è obbligatoria")
    @Size(min = 4, max = 200)
    @AIField(type = AFType.text, widthEM = 30, help = "Descrizione della versione.")
    @AIColumn(width = 500)
    private String descrizione;


    /**
     * momento in cui si effettua la modifica della versione (obbligatoria, non unica, non modificabile)
     * inserita automaticamente
     */
    @NotNull
    @AIField(type = AFType.localdatetime, enabled = false, help = "Data di inserimento della versione.")
    @AIColumn
    private LocalDateTime modifica;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getTitolo() + "-" + getDescrizione();
    }// end of method


}// end of entity class
