package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.entity.persona.PersonaPresenter;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.FieldAccessibility;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.login.ARoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
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
 */
@SpringComponent
@Document(collection = Cost.TAG_COMP)
@AIEntity(roleTypeVisibility = ARoleType.admin, company = ACompanyRequired.nonUsata)
@AIList(columns = {"sigla", "descrizione", "contatto"})
@AIForm()
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Company extends AEntity {

    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    @NotEmpty(message = "La sigla interna è obbligatoria")
//    @Indexed(unique = true)
    @Size(min = 2, max = 20)
    @AIField(type = AFieldType.text, widthEM = 8, focus = true, help = "Codice interno", admin = FieldAccessibility.showOnly)
    @AIColumn(width = 100)
    private String sigla;


    /**
     * ragione sociale o descrizione della company (obbligatoria, non unica)
     */
    @NotEmpty(message = "La descrizione è obbligatoria")
    @Size(min = 2, max = 50)
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 24, help = "Descrizione della company")
    @AIColumn(width = 350)
    private String descrizione;


    /**
     * persona di riferimento (facoltativo)
     * riferimento statico SENZA @DBRef
     */
    @AIField(type = AFieldType.link, clazz = PersonaPresenter.class, help = "Riferimento")
    @AIColumn(width = 250, name = "Riferimento")
    private Persona contatto;


    /**
     * posta elettronica (facoltativo)
     */
    @Email
    @AIField(type = AFieldType.email, widthEM = 24)
    @AIColumn(width = 350, name = "Mail")
    private String email;


    /**
     * indirizzo (facoltativo)
     * riferimento statico SENZA @DBRef
     */
    @AIField(type = AFieldType.link, clazz = IndirizzoPresenter.class, help = "Indirizzo")
    @AIColumn(width = 350, name = "Indirizzo")
    private Indirizzo indirizzo;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getSigla();
    }// end of method

}// end of class
