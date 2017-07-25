package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AlgosEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by gac on 30/06/17
 * <p>
 * Classe di tipo JavaBean
 * 1) la classe deve avere un costruttore senza argomenti
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolena al posto di get)
 * 3) la classe deve implementare l'interfaccia Serializable (la fa nella superclasse)
 * 4) la classe non deve contenere nessun metodo per la gestione degli eventi
 * <p>
 * Annotated with @Entity, indicating that it is a JPA entity.
 * If there isn't a @Table annotation, this entity will be mapped to a table named as the class
 * Estende la Entity astratta AlgosModel che contiene la key property ID
 * <p>
 * Tipicamente usata da un admin per verificare i logs
 * Prevede la differenziazione per Company. Se l'applicazione non usa le company, la property è sempre nulla.
 */
@Entity
@Service
@Data
@Table(name = Cost.TAG_LOG)
@Qualifier(Cost.TAG_LOG)
public class Log extends AlgosEntity {

    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    @AIField(type = AFType.combo, clazz = CompanyService.class,  name = "Company")
    private Company company;


    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @AIField(type = AFType.enumeration, clazz = Livello.class)
    private Livello livello;


    //--codifica di gruppo per identificare la tipologia del log (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Column(length = 20)
    @AIField(type = AFType.text, required = true,  help = "Tipologia della versione.")
    private String titolo;


    //--descrizione (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Size(min = 2, max = 50)
    @AIField(type = AFType.text, required = true, widthEM = 30,  help = "Descrizione della versione.")
    @AIColumn( width = 500)
    private String descrizione;


    //--momento in cui si effettua la registrazione del log (obbligatoria, non unica)
    //--inserita automaticamente
    @NotNull
    @AIField(type = AFType.localdatetime, enabled = false,  help = "Data di inserimento della versione")
    private LocalDateTime modifica;


    /**
     * The default constructor only exists for the sake of JPA.
     * You won’t use it directly, so it is designated as protected.
     * Costruttore senza argomenti
     * Obbligatorio per le specifiche JavaBean
     * Da non usare MAI per la creazione diretta di una nuova istanza (si perdono i controlli)
     */
    protected Log() {
        this( (Company) null, (Livello) null, "", "", (LocalDateTime) null);
    }// end of JavaBean constructor


    /**
     * Costruttore completo (indispensabile per il Service di SpringBoot)
     * You won’t use it directly, so it is designated as reserved.
     * Eventuali regolazioni iniziali delle property
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param company     di riferimento (puo essere nullo)
     * @param livello     di presentazione (obbligatorio, non unico)
     * @param titolo      codifica di gruppo per identificare la tipologia del log (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     */
    Log( Company company, Livello livello, String titolo, String descrizione, LocalDateTime modifica) {
        this.setCompany(LibParams.useMultiCompany() && company != null ? company : null);
        this.setLivello(livello != null ? livello : Livello.info);
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setModifica((modifica != null) ? modifica : LocalDateTime.now());
    }// end of general constructor


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getTitolo() + "-" + getDescrizione();
    }// end of method


}// end of class