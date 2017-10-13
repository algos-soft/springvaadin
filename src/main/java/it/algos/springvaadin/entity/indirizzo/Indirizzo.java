package it.algos.springvaadin.entity.indirizzo;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.entity.AEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

/**
 * Created by gac on 01/06/17
 * <p>
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 * <p>
 * Usato da altre 'collection' (ex moduli)
 */
@SpringComponent
@Document(collection = Cost.TAG_IND)
@AIEntity(company = ACompanyRequired.nonUsata)
@AIForm()
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Indirizzo extends AEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * indirizzo: via, nome e numero (obbligatoria, non unica)
     */
    @NotEmpty()
    @Size(min = 2, max = 40)
    @AIField(type = AFieldType.text, widthEM = 20, focus = true, help = "Via, nome e numero")
    @AIColumn(width = 300)
    private String indirizzo = "";


    /**
     * località (obbligatoria, non unica)
     */
    @NotEmpty()
    @Size(min = 4, max = 40)
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 20, help = "Città, comune, paese")
    @AIColumn(width = 300)
    private String localita = "";


    /**
     * codice di avviamento postale (obbligatoria, non unica)
     * non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
     */
    @Size(min = 5, max = 5, message = "CAP deve essere di 5 numeri")
    @AIField(type = AFieldType.text, widthEM = 5, help = "Codice postale")
    @AIColumn(width = 90)
    private String cap = "";


    /**
     * stato (obbligatoria, non unica)
     * riferimento dinamico
     */
    @DBRef
    @NotEmpty()
    @AIField(type = AFieldType.combo, clazz = Stato.class)
    @AIColumn(width = 140)
    private Stato stato;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String value = "";
        String spazio = " ";
        String sep = " - ";

        value += indirizzo;
        value += (LibText.isValid(cap) ? sep + cap : sep);
        value += spazio + localita;
        value += spazio + (stato != null ? stato : "");

        return value;
    }// end of method


}// end of entity class
