package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by gac on 01/06/17
 * <p>
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AlgosEntity che contiene la key property ObjectId
 */
@SpringComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends AlgosEntity {


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
    private Indirizzo indirizzo;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getSigla();
    }// end of method

}// end of class
