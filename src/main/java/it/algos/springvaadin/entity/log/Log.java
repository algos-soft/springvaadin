package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyServiceOld;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
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
@Table(name = Cost.TAG_LOG)
@Qualifier(Cost.TAG_LOG)
public class Log extends AlgosEntity {

    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    @AIField(type = AFType.combo, clazz = CompanyServiceOld.class, width = "12em", caption = "Company")
    private Company company;


    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @AIColumn(type = AFType.enumeration, width = 200, header = "Livello")
    @AIField(type = AFType.enumeration, clazz = Livello.class, width = "12em", caption = "Livello")
    private Livello livello;


    //--codifica di gruppo per identificare la tipologia del log (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Column(length = 20)
    @AIColumn(type = AFType.text, width = 200, header = "Titolo")
    @AIField(type = AFType.text, required = true, width = "12em", caption = "Titolo", prompt = "Titolo", help = "Tipologia della versione.", error = "Titolo obbligatorio")
    private String titolo;


    //--descrizione (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Size(min = 2, max = 50)
    @AIColumn(type = AFType.text, width = 500, header = "Descrizione")
    @AIField(type = AFType.text, required = true, width = "30em", caption = "Descrizione", prompt = "Descrizione", help = "Descrizione della versione.", error = "Descrizione obbligatoria")
    private String descrizione;


    //--momento in cui si effettua la registrazione del log (obbligatoria, non unica)
    //--inserita automaticamente
    @NotNull
    @AIColumn(type = AFType.localdatetime, width = 200, header = "Modifica")
    @AIField(type = AFType.localdatetime, enabled = false, caption = "Modifica", help = "Data di inserimento della versione")
    private LocalDateTime modifica;


    /**
     * The default constructor only exists for the sake of JPA.
     * You won’t use it directly, so it is designated as protected.
     * Costruttore senza argomenti
     * Obbligatorio per le specifiche JavaBean
     * Da non usare MAI per la creazione diretta di una nuova istanza (si perdono i controlli)
     */
    protected Log() {
    }// end of JavaBean constructor


    /**
     * The default constructor for Spring.
     * Il service (dao, repository) viene iniettato in questo costruttore
     *
     * @param logService iniettato direttamente da Spring
     */
    @Autowired
    public Log(LogServiceOld logService) {
        this(logService, (Company) null, (Livello) null, "", "", (LocalDateTime) null);
    }// end of Spring constructor


    /**
     * Costruttore completo (indispensabile per il Service di SpringBoot)
     * You won’t use it directly, so it is designated as reserved.
     * Eventuali regolazioni iniziali delle property
     * Eventuale uso del service (fornito)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param logService  iniettato direttamente da Spring
     * @param company     di riferimento (puo essere nullo)
     * @param livello     di presentazione (obbligatorio, non unico)
     * @param titolo      codifica di gruppo per identificare la tipologia del log (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     */
    Log(LogServiceOld logService, Company company, Livello livello, String titolo, String descrizione, LocalDateTime modifica) {
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


    public Company getCompany() {
        return company;
    }// end of getter method

    public void setCompany(Company company) {
        this.company = company;
    }// end of setter method

    public Livello getLivello() {
        return livello;
    }// end of getter method

    public void setLivello(Livello livello) {
        this.livello = livello;
    }// end of setter method

    public String getTitolo() {
        return titolo;
    }// end of getter method

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }// end of setter method

    public String getDescrizione() {
        return descrizione;
    }// end of getter method

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }// end of setter method

    public LocalDateTime getModifica() {
        return modifica;
    }// end of getter method

    public void setModifica(LocalDateTime modifica) {
        this.modifica = modifica;
    }// end of setter method

}// end of class