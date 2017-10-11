package it.algos.springvaadin.entity;

import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.field.AFieldType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
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
 *
 * La classe NON può usare la Annotation @Setter (contrariamente alle altre classi di Entity),
 * perché 'oscurerebbe' la gestione automatica della key property ObjectId da parte di Mongo
 * Le properety sono tutte pubbliche (contrariamente alle altre classi di Entity),
 * per essere accessibili visto che mancano i 'setters'
 *
 * La gestione delle property 'dataCreazione' e 'dataModifica' è automatica in AlgosServiceImpl.save()
 */
@Getter
public abstract class AEntity implements Serializable {


    /**
     * key property ObjectId
     * di default gestita direttamente da Mongo
     * può essere usata direttamente per identificare la entity con key 'leggibili'
     */
    @NotEmpty
    public String id;


    /**
     * Eventuali note (facoltativo)
     */
    @AIField(type = AFieldType.note, widthEM = 18)
    public String note;

    /**
     * Data di creazione del record (obbligatoria, non modificabile)
     * Gestita in automatico
     * Field visibile solo al developer
     */
    @NotNull
    @Indexed()
    @AIField(name = "Creazione", type = AFieldType.localdatetime, enabled = false)
    @AIColumn(name = "Creazione", width = 110)
    public LocalDateTime dataCreazione;

    /**
     * Data di modifica del record (obbligatoria, modificabile solo da codice, non da UI)
     * Gestita in automatico
     * Field visibile solo al developer
     */
    @NotNull
    @Indexed()
    @AIField(name = "Modifica", type = AFieldType.localdatetime, enabled = false)
    @AIColumn(name = "Modifica", width = 110)
    public LocalDateTime dataModifica;

}// end of entity abstract class


