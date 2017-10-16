package it.algos.springvaadin.entity;

import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.field.AFieldType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;

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
 *
 * Le Entity possono estendere AEntity direttamente, oppure estendere questa classe per usare la property company
 * Se il flag AlgosApp.USE_MULTI_COMPANY=false, anche se la Entity estende questa classe,
 * il valore della property è sempre nullo
 * Se il flag AlgosApp.USE_MULTI_COMPANY=true, e la Entity estende questa classe,
 * la property può essere obbligatoria (es:Turni, Fatture, Utenti) oppure facoltativa (es:Preferenze)
 * secondo l'Annotation della classe @AIEntity(companyNotNull=true) oppure @AIEntity(companyNotNull=false)
 */
@Getter
@Setter
public abstract class ACompanyEntity extends AEntity {


    /**
     * Riferimento alla company (per le sottoclassi che usano questa classe)
     * - Nullo se il flag AlgosApp.USE_MULTI_COMPANY=false
     * - Facoltativo od obbligatorio a seconda della sottoclasse, se il flag AlgosApp.USE_MULTI_COMPANY=true
     */
    @AIField(type = AFieldType.combo, clazz = Company.class)
    @AIColumn(name = "Company", width = 110)
    private Company company;


//    /**
//     * Sigla di codifica interna specifica per ogni company (obbligatoria, non unica in generale ma unica all'interno della company)
//     * Visibile solo per admin e developer
//     * Not null se il flag AlgosApp.USE_MULTI_COMPANY=true
//     */
//    @Column(length = 20)
//    @Indexed()
//    @AIField(type = AFieldType.text, required = true, widthEM = 9)
//    @AIColumn(width = 120)
//    private String code;
//
//
//    /**
//     * Sigla di codifica interna (obbligatoria, unica in generale indipendentemente dalla company)
//     * Calcolata -> codeCompanyUnico = company.companyCode + xxx.code (20+20=40);
//     * Visibile solo per admin e developer
//     * Not null e unica se il flag AlgosApp.USE_MULTI_COMPANY=true
//     */
//    @Column(length = 40)
//    @Indexed()
//    @AIField(type = AFieldType.text, required = true, enabled = false, widthEM = 15)
//    @AIColumn(width = 200)
//    private String codeCompanyUnico;

}// end of class
