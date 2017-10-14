package it.algos.springvaadin.entity.persona;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.login.ARoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by gac on 11-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 */
@SpringComponent
@Document(collection = Cost.TAG_PER)
@AIEntity(roleTypeVisibility = ARoleType.developer, company = ACompanyRequired.nonUsata)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Persona extends AEntity {


    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    //--nome (facoltativo)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @AIField(type = AFieldType.text, required = true, focus = true, firstCapital = true)
    @AIColumn()
    private String nome;


    //--cognome (facoltativo)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @AIField(type = AFieldType.text, required = true, firstCapital = true)
    @AIColumn()
    private String cognome;


    //--indirizzo (facoltativo)
    @AIField(type = AFieldType.link, clazz = IndirizzoPresenter.class, help = "Indirizzo")
    @AIColumn(width = 400, name = "Indirizzo")
    private Indirizzo indirizzo;


    @Email
    @AIField(type = AFieldType.email, widthEM = 24)
    private String email;

    //--telefono (facoltativo)
    @AIField(type = AFieldType.text)
    @AIColumn()
    private String telefono;

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getNome() + " " + getCognome();
    }// end of method


}// end of entity class
