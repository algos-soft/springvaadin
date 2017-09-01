package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.model.AEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by gac on 01/06/17
 * <p>
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 */
@SpringComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Company extends AEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    //--sigla di riferimento interna (interna, obbligatoria ed unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty(message = "La sigla interna è obbligatoria")
    @Size(min = 2, max = 20)
    @AIField(type = AFType.text, widthEM = 8, help = "Codice interno")
    @AIColumn(width = 80)
    private String sigla;


    //--ragione sociale o descrizione della company (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty(message = "La descrizione è obbligatoria")
    @Size(min = 2, max = 50)
    @AIField(type = AFType.text, firstCapital = true, widthEM = 30, help = "Descrizione della company")
    @AIColumn(width = 320)
    private String descrizione;


    //--indirizzo (facoltativo)
    @AIField(type = AFType.link, clazz = IndirizzoField.class, help = "Indirizzo")
    @AIColumn(width = 400)
    private AEntity indirizzo;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getSigla();
    }// end of method

}// end of class
