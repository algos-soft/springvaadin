package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AlgosEntity che contiene la key property ObjectId
 * <p>
 * Gli stati vengono classificati secondo la norma ISO 3166
 */
@SpringComponent
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stato extends AlgosEntity {


    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    /**
     * ordine di creazione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * inserito automaticamente
     * se si cancella una entity, rimane il 'buco' del numero
     */
    @NotNull
    @Indexed(unique = true)
    @AIField(type = AFType.integer, enabled = false, widthEM = 3, help = "Ordine di creazione. Unico e normalmente progressivo")
    @AIColumn(name = "#", width = 70)
    private int ordine;


    //--nome (obbligatoria, unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Indexed(unique = true)
    @AIField(type = AFType.text, required = true, focus = true, widthEM = 16)
    @AIColumn(width = 250)
    private String nome;


    //--codice alfabetico di 2 cifre (facoltativo, unico)
    //-- 249 codici assegnati
    @AIField(type = AFType.text, widthEM = 4)
    @AIColumn(width = 100)
    private String alfaDue;

    //--codice alfabetico di 3 cifre (facoltativo, unico)
    //-- 249 codici assegnati
    @AIField(type = AFType.text, widthEM = 4)
    @AIColumn(width = 100)
    private String alfaTre;

    //--codice numerico di 3 cifre (facoltativo, unico)
    //-- 249 codici assegnati
    @AIField(type = AFType.text, widthEM = 4)
    @AIColumn(width = 100)
    private String numerico;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
//        return getNome() + " (" + getAlfaDue()+")";
        return getNome();
    }// end of method


}// end of entity class
