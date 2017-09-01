package it.algos.springvaadin.entity.indirizzo;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.annotation.AIForm;
import it.algos.springvaadin.annotation.AIList;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosEntity;
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
 * Estende la Entity astratta AlgosEntity che contiene la key property ObjectId
 * <p>
 * Usato da altre 'collection' (ex moduli)
 */
@SpringComponent
@Document(collection = Cost.TAG_IND)
@AIList()
@AIForm(showsID = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Indirizzo extends AlgosEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * indirizzo: via, nome e numero (obbligatoria, non unica)
     */
    @NotEmpty(message = "L'indirizzo è obbligatorio")
    @Size(min = 2, max = 40)
    @AIField(type = AFType.text, widthEM = 20, help = "Via, nome e numero")
    @AIColumn(width = 300)
    private String indirizzo = "";


    /**
     * località (obbligatoria, non unica)
     */
    @NotEmpty(message = "La località è obbligatoria")
    @Size(min = 4, max = 40)
    @AIField(type = AFType.text, firstCapital = true, widthEM = 20, help = "Città, comune, paese")
    @AIColumn(width = 300)
    private String localita = "";


    /**
     * codice di avviamento postale (obbligatoria, non unica)
     * non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
     */
    @Size(min = 5, max = 5, message = "CAP deve essere di 5 numeri")
    @AIField(type = AFType.text, widthEM = 5, help = "Codice postale")
    @AIColumn(width = 90)
    private String cap = "";


    /**
     * stato (obbligatoria, non unica)
     * riferimento dinamico
     */
    @DBRef
    @NotEmpty(message = "Lo stato è obbligatorio")
    @Size(min = 3, max = 20)
    @AIField(type = AFType.combo, clazz = Stato.class)
    @AIColumn(width = 140)
    private Stato stato ;


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
        value += spazio + stato;

        return value;
    }// end of method


}// end of entity class
