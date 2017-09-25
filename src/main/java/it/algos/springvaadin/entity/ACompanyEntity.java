package it.algos.springvaadin.entity;

import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.field.AFieldType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 25-set-2017
 * Time: 07:21
 * Le sottoclassi concrete sono di tipo JavaBean
 * 1) la sottoclasse deve avere un costruttore senza argomenti
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolen al posto di get)
 * 3) la sottoclasse deve implementare l'annotation Serializable (lo fa in questa classe)
 * 4) la sottoclasse non deve contenere nessun metodo per la gestione degli eventi
 * <p>
 * Annotated with @Getter (Lombok) for automatic use of Getter
 * Sottoclasse annotated with @SpringComponent (obbligatorio)
 * Sottoclasse annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Sottoclasse annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Sottoclasse annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 */
@Getter
public abstract class ACompanyEntity extends AEntity{

    /**
     * riferimento alla company (obbligatorio per le sottoclassi che usano questa classe)
     */
    @NotEmpty
    @AIField(type = AFieldType.combo, clazz = Company.class)
    private Company company;

}// end of class
