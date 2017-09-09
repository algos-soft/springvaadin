package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 * <p>
 * Gli stati vengono classificati secondo la norma ISO 3166
 *
 * @https://it.wikipedia.org/wiki/ISO_3166
 */
@SpringComponent
@Document(collection = Cost.TAG_STA)
@AIList(showsID = true, widthID = 80, columns = {"ordine", "nome", "alfaDue", "alfaTre", "numerico"})
@AIForm(showsID = true, widthIDEM = 4)
@AISearch(fields = {"nome", "alfaDue"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Stato extends AEntity {


    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    /**
     * ordine di creazione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * inserito automaticamente
     * se si cancella una entity, rimane il 'buco' del numero
     */
    @NotNull
    @Indexed()
    @AIField(type = AFieldType.integer, enabled = false, help = "Ordine di creazione. Unico e normalmente progressivo")
    @AIColumn(name = "#", width = 70)
    private int ordine;


    //--nome (obbligatoria, unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Indexed(unique = true)
    @Size(min = 4)
    @AIField(type = AFieldType.text, required = true, focus = true, firstCapital = true)
    @AIColumn(width = 250)
    private String nome;


    //--codice alfabetico di 2 cifre (facoltativo, unico)
    //-- 249 codici assegnati
    @NotEmpty
    @Indexed(unique = true)
    @Size(min = 2, max = 2)
    @AIField(type = AFieldType.text, widthEM = 6, allUpper = true, onlyLetter = true)
    @AIColumn(width = 100)
    private String alfaDue;


    //--codice alfabetico di 3 cifre (facoltativo, unico)
    //-- 249 codici assegnati
    @NotEmpty
    @Indexed(unique = true)
    @Size(min = 3, max = 3)
    @AIField(type = AFieldType.text, widthEM = 6, allUpper = true, onlyLetter = true)
    @AIColumn(width = 100)
    private String alfaTre;


    //--codice numerico di 3 cifre (facoltativo, vuoto oppure unico)
    //-- 249 codici assegnati
    @Indexed(unique = false)
    @Size(min = 3, max = 3)
    @AIField(type = AFieldType.text, widthEM = 6, onlyNumber = true)
    @AIColumn(width = 100)
    private String numerico;


    //--immagine bandiera (facoltativo, unico)
    //-- 249 codici assegnati
    @Indexed(unique = false)
    @AIField(type = AFieldType.image, widthEM = 8)
    @AIColumn(width = 100)
    private byte[] bandiera;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getNome();
    }// end of method


}// end of entity class
