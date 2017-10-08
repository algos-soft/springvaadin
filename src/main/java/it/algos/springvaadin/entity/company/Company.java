package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.AEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
@EqualsAndHashCode(callSuper = false)
public class Company extends AEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    //--sigla di riferimento interna (interna, obbligatoria ed unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty(message = "La sigla interna è obbligatoria")
    @Size(min = 2, max = 20)
    @AIField(type = AFieldType.text, widthEM = 8, focus = true, help = "Codice interno")
    @AIColumn(width = 100)
    private String sigla;


    //--ragione sociale o descrizione della company (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty(message = "La descrizione è obbligatoria")
    @Size(min = 2, max = 50)
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 30, help = "Descrizione della company")
    @AIColumn(width = 400)
    private String descrizione;


    //--indirizzo (facoltativo)
    //    @DBRef
    @AIField(type = AFieldType.link, clazz = IndirizzoField.class, help = "Indirizzo")
    @AIColumn(width = 400)
    private Indirizzo indirizzo;

    @Email
    @AIField(type = AFieldType.email)
    private String email;

    // persona di riferimento (facoltativo)
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 20, name = "Riferimento")
    private String contact;

    //--eventuali note (facoltativo)
    @AIField(type = AFieldType.note)
    private String note;

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getSigla();
    }// end of method

}// end of class
