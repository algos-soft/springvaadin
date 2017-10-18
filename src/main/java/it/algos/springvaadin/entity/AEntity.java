package it.algos.springvaadin.entity;

import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.login.ARoleType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 18:30
 * Le sottoclassi concrete sono di tipo JavaBean
 * 1) la sottoclasse deve avere un costruttore senza argomenti (fornito in automatico da Lombok)
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolen al posto di get)
 * 3) la sottoclasse deve implementare l'annotation Serializable (lo fa in questa classe)
 * 4) la sottoclasse non deve contenere nessun metodo per la gestione degli eventi
 * <p>
 * Annotated with @Getter (Lombok) for automatic use of Getter
 * Sottoclassi annotated (obbligatorio) with @SpringComponent
 * Sottoclassi annotated (facoltativo) with @Data (Lombok) for automatic use of Getter and Setter
 * Sottoclassi annotated (facoltativo) with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Sottoclassi annotated (facoltativo) with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * <p>
 * La classe NON può usare la Annotation @Setter (contrariamente alle altre classi di Entity),
 * perché 'oscurerebbe' la gestione automatica della key property ObjectId da parte di Mongo
 * Le properety sono tutte pubbliche (contrariamente alle altre classi di Entity),
 * per essere accessibili visto che mancano i 'setters'
 * <p>
 * La gestione delle property 'dataCreazione' e 'dataModifica' è automatica in AlgosServiceImpl.save()
 */
@Getter
public abstract class AEntity implements Serializable {


    /**
     * key property ObjectId
     * di default gestita direttamente da Mongo
     * può essere usata direttamente per identificare la entity con key 'leggibili'
     * NON va usato @NotEmpty, perchè altrimenti binder.validate().isOk() va in errore
     * Ci pensa Mongo a riempire il valore
     */
    @AIField(name = "Key", roleTypeVisibility = ARoleType.developer)
    @AIColumn(roleTypeVisibility = ARoleType.nobody)
    public String id;


    /**
     * Eventuali note (facoltativo)
     */
    @AIField(type = AFieldType.textarea, widthEM = 24)
    @AIColumn(roleTypeVisibility = ARoleType.nobody)
    public String note;

    /**
     * Data di nuovo del record (obbligatoria, non modificabile)
     * Gestita in automatico
     * Field visibile solo al developer
     */
    @NotNull
    @AIField(name = "Data di nuovo della scheda", type = AFieldType.localdatetime, enabled = false, roleTypeVisibility = ARoleType.developer)
    @AIColumn(roleTypeVisibility = ARoleType.nobody)
    public LocalDateTime dataCreazione;

    /**
     * Data di edit del record (obbligatoria, modificabile solo da codice, non da UI)
     * Gestita in automatico
     * Field visibile solo al developer
     */
    @NotNull
    @AIField(name = "Data di edit della scheda", type = AFieldType.localdatetime, enabled = false, roleTypeVisibility = ARoleType.developer)
    @AIColumn(roleTypeVisibility = ARoleType.nobody)
    public LocalDateTime dataModifica;

}// end of entity abstract class


