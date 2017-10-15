package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.login.ARoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by gac on 30-set-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 */
@SpringComponent
@Document(collection = Cost.TAG_LOG)
@AIEntity(roleTypeVisibility = ARoleType.admin, company = ACompanyRequired.obbligatoriaSenzaCodeUnico)
@AIList(columns = {"livello","gruppo", "descrizione", "evento"})
@AIForm()
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Log extends ACompanyEntity {

    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * gruppo/titolo/evento (obbligatorio)
     */
    @NotEmpty(message = "Liovello del log è obbligatorio")
    @Indexed()
    @AIField(type = AFieldType.enumeration, clazz = LogLevel.class, required = true, widthEM = 10)
    @AIColumn(width = 140)
    private String livello;


    /**
     * gruppo/titolo/evento (obbligatorio)
     */
    @NotEmpty(message = "La tipologia del log è obbligatoria")
    @Indexed()
    @AIField(type = AFieldType.enumeration, clazz = LogType.class, required = true, widthEM = 10)
    @AIColumn(width = 140)
    private String gruppo;


    /**
     * descrizione (obbligatoria)
     */
    @NotEmpty(message = "La descrizione è obbligatoria")
    @Indexed()
    @Size(min = 2, max = 100)
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 24, help = "Messaggio del log")
    @AIColumn(width = 350)
    private String descrizione;


    /**
     * Data dell'evento (obbligatoria, non modificabile)
     * Gestita in automatico
     * Field visibile solo al admin
     */
    @NotNull
    @Indexed()
    @AIField(name = "Data dell'evento di log", type = AFieldType.localdatetime, enabled = false, roleTypeVisibility = ARoleType.admin)
    @AIColumn(name = "Data", roleTypeVisibility = ARoleType.admin)
    public LocalDateTime evento;


//    /**
//     * @return a string representation of the object.
//     */
//    @Override
//    public String toString() {
//        return getSigla();
//    }// end of method


}// end of entity class
