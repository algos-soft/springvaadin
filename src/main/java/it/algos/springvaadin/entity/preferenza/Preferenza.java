package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.entity.AEntity;
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
@AIList(columns = {"sigla"})
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
     * sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     */
    @NotEmpty
    @AIField(type = AFieldType.text, required = true, firstCapital = true, widthEM = 8)
    @AIColumn(width = 100, name = "Code")
    private String code;


    /**
     * tipo di dato memorizzato (obbligatorio)
     */
    @NotNull
    @AIField(type = AFieldType.enumeration, required = true, widthEM = 8)
    @AIColumn(width = 100, name = "Type")
    private PrefType type;


    /**
     * descrizione visibile (obbligatoria)
     */
    @AIField(type = AFieldType.text, firstCapital = true, widthEM = 24, help = "Descrizione della preferenza")
    @AIColumn(width = 350)
    private String descrizione = "";


    /**
     * valore della preferenza (obbligatorio)
     */
    @NotNull
    @AIField(type = AFieldType.image, required = true, widthEM = 8)
    @AIColumn(width = 100, name = "Value")
    private byte[] value;



    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getCode();
    }// end of method


}// end of entity class
