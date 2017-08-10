package it.algos.springvaadin.entity.indirizzo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosEntity;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by gac on 01/06/17
 * <p>
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 * Estende la Entity astratta AlgosEntity che contiene la key property ObjectId
 * <p>
 * Usato da altre 'collection' (ex moduli)
 */
@SpringComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indirizzo extends AlgosEntity {


    /**
     * versione della classe per la serializzazione
     */
    private final static long serialVersionUID = 1L;


    /**
     * indirizzo: via, nome e numero (obbligatoria, non unica)
     */
    @NotEmpty(message = "L'indirizzo è obbligatorio")
    @Size(min = 2, max = 40)
    @AIField(type = AFType.text, widthEM = 20, help = "Via, nome e numero")
    @AIColumn(width = 300)
    private String indirizzo = "";


    /**
     * località (obbligatoria, non unica)
     */
    @NotEmpty(message = "La località è obbligatoria")
    @Size(min = 4, max = 40)
    @AIField(type = AFType.text, firstCapital = true, widthEM = 20, help = "Città, comune, paese")
    @AIColumn(width = 300)
    private String localita = "";


    /**
     * codice di avviamento postale (obbligatoria, non unica)
     * non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
     */
    @Size(min = 5, max = 5, message = "CAP deve essere di 5 numeri")
    @AIField(type = AFType.text, widthEM = 5, help = "Codice postale")
    @AIColumn(width = 90)
    private String cap = "";


    /**
     * stato (obbligatoria, non unica)
     */
    @NotEmpty(message = "Lo stato è obbligatorio")
    @Size(min = 3, max = 20)
    @AIField(type = AFType.text, firstCapital = true, widthEM = 10)
    @AIColumn(width = 140)
    private String stato = "";


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String value = "";
        String spazio = " ";
        String sep = " - ";

        value += indirizzo;
        value += (LibText.isValid(cap) ? sep + cap : sep);
        value += spazio + localita;
        value += spazio + stato;

        return value;
    }// end of method


}// end of entity class
