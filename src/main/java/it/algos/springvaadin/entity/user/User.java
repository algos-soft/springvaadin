package it.algos.springvaadin.entity.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.algos.springvaadin.lib.ACost;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.context.annotation.Scope;
import lombok.*;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.enumeration.EAListButton;
import it.algos.springvaadin.enumeration.EACompanyRequired;
import it.algos.springvaadin.enumeration.EAFieldAccessibility;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.entity.AEntity;

/**
 * Created by gac on 11-nov-17
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Document (facoltativo) per avere un nome della collection (DB Mongo) diverso dal nome della Entity
 * Annotated with @Scope (obbligatorio = 'session')
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Annotated with @Builder (Lombok) lets you automatically produce the code required to have your class
 * be instantiable with code such as: Person.builder().name("Adam Savage").city("San Francisco").build();
 * Annotated with @EqualsAndHashCode (facoltativo) per ???
 * Annotated with @Qualifier (obbligatorio) per permettere a Spring di istanziare la sottoclasse specifica
 * Annotated with @AIEntity (facoltativo) per alcuni parametri generali del modulo
 * Annotated with @AIList (facoltativo) per le colonne della Lista e loro visibilità/accessibilità relativa all'utente
 * Annotated with @AIForm (facoltativo) per i fields del Form e loro visibilità/accessibilità relativa all'utente
 * Inserisce SEMPRE la versione di serializzazione che viene poi filtrata per non mostrarla in List e Form
 * Le singole property sono annotate con @AIField (obbligatorio per il tipo di Field) e @AIColumn (facoltativo)
 */
@SpringComponent
@Document(collection = "user")
@Scope("session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Qualifier(ACost.TAG_USE)
@AIEntity()
@AIList(dev = EAListButton.standard, admin = EAListButton.noSearch, user = EAListButton.show)
public class User extends AEntity {

    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * nickname di riferimento (obbligatorio, unico per company)
     */
    @NotEmpty
    @Size(min = 3, max = 20)
    @Indexed()
    @AIField(
            type = EAFieldType.text,
            required = true,
            focus = true,
            name = "NickName",
            widthEM = 12)
    @AIColumn(name = "Nick", width = 300)
    private String nickname;


    /**
     * password (obbligatoria o facoltativa, non unica)
     */
    @Size(min = 3, max = 20)
    @AIField(
            type = EAFieldType.text,
            required = true,
            name = "Password",
            widthEM = 12,
            admin = EAFieldAccessibility.allways,
            user = EAFieldAccessibility.showOnly)
    @AIColumn(name = "Password", width = 200)
    private String password;


    /**
     * buttonUser abilitato (facoltativo, di default true)
     */
    @AIField(type = EAFieldType.checkboxlabel, required = true, admin = EAFieldAccessibility.allways)
    @AIColumn(name = "OK")
    private boolean enabled;


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return nickname;
    }// end of method


}// end of entity class
