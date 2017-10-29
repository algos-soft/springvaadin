package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.field.FieldAccessibility;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.list.ListButton;
import it.algos.springvaadin.login.ARoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AEntity che contiene la key property ObjectId
 */
@SpringComponent
@Document(collection = Cost.TAG_PRE)
@AIEntity(roleTypeVisibility = ARoleType.admin, company = ACompanyRequired.facoltativa)
@AIList(columns = {"ordine", "code", "type", "value"}, dev = ListButton.standard, admin = ListButton.edit)
@AIForm()
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Preferenza extends ACompanyEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * ordine (facoltativo, modificabile)
     * inserito automaticamente
     */
    @Indexed()
    @AIField(type = AFieldType.integer, widthEM = 3, help = "Ordine di versione. Normalmente progressivo", admin = FieldAccessibility.showOnly)
    @AIColumn(name = "#", width = 50)
    private int ordine;


    /**
     * sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     */
    @NotEmpty
    @AIField(type = AFieldType.text, required = true, focus = true, admin = FieldAccessibility.showOnly)
    @AIColumn(width = 200, name = "Code")
    private String code;


    /**
     * tipo di dato memorizzato (obbligatorio)
     */
    @NotNull
    @AIField(type = AFieldType.enumeration, required = true, widthEM = 8, name = "Tipo di dato", dev = FieldAccessibility.allways, admin = FieldAccessibility.showOnly)
    private PrefType type;


    /**
     * tipo di utente che può visualizzare/modificare le preferenze (obbligatorio)
     */
    @NotNull
    @AIField(type = AFieldType.enumeration, clazz = ARoleType.class, required = true, widthEM = 8, name = "Livello di visibilità", admin = FieldAccessibility.showOnly)
    @AIColumn(width = 110, name = "Level")
    private ARoleType livello;


    /**
     * descrizione visibile (obbligatoria)
     */
    @NotEmpty
    @AIField(type = AFieldType.textarea, firstCapital = true, help = "Descrizione della preferenza", admin = FieldAccessibility.showOnly)
    @AIColumn(roleTypeVisibility = ARoleType.nobody, width = 600)
    private String descrizione;


    /**
     * valore della preferenza (obbligatorio)
     */
    @NotNull
    @AIField(type = AFieldType.json, required = true, admin = FieldAccessibility.allways)
    @AIColumn(roleTypeVisibility = ARoleType.nobody, width = 200, name = "Value")
    private byte[] value;



    /**
     * effetti (obbligatorio, di default 1)
     */
    @NotNull
    @AIField(type = AFieldType.radio, required = true, name = "Attivazione della preferenza", widthEM = 24, admin = FieldAccessibility.showOnly)
    @AIColumn(roleTypeVisibility = ARoleType.nobody)
    private PrefEffect attivazione;

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getCode();
    }// end of method


}// end of entity class
