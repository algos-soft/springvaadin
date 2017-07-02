package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;


/**
 * Created by gac on 01/06/17
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
 * Usata dalle applicazioni che implementano AlgosApp.USE_MULTI_COMPANY
 */
@Entity
@Table(name = "company")
@SpringComponent
public class Company extends AlgosModel {

    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    //--sigla di riferimento interna (interna, obbligatoria ed unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Column(length = 20, unique = true)
    @AIColumn(type = AFType.text, header = "Sigla")
    @AIField(type = AFType.text, required = true, width = "8em", caption = "Sigla", help = "Codice interno.", error = "Codice obbligatorio")
    private String sigla;


    //--ragione sociale o descrizione della company (visibile - obbligatoria)
    //--descrizione (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Size(min = 2, max = 50)
    @AIColumn(type = AFType.text, width = 320, header = "Descrizione")
    @AIField(type = AFType.text, required = true, width = "30em", caption = "Descrizione", prompt = "Descrizione", help = "Descrizione della company.", error = "Descrizione obbligatoria")
    private String descrizione;


    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @Email
    @AIColumn(type = AFType.text, width = 270, header = "Mail")
    @AIField(type = AFType.text, width = "20em", caption = "Mail", prompt = "pinco.pallino@mail.com", help = "Indirizzo di posta elettronica.")
    private String email;


    @AIColumn(type = AFType.text, width = 320, header = "Indirizzo")
    @AIField(type = AFType.text, width = "27em", caption = "Indirizzo", help = "Indirizzo postale.")
    private String indirizzo;


    // persona di riferimento (facoltativo)
    @AIColumn(type = AFType.text, width = 170, header = "Contatto")
    @AIField(type = AFType.text, width = "20em", caption = "Riferimento", help = "Persona di riferimento.")
    private String contatto;


    // telefono fisso ufficio (facoltativo)
    @AIColumn(type = AFType.text, width = 140, header = "Tel.")
    @AIField(type = AFType.text, width = "20em", caption = "Telefono", help = "Telefono ufficio.")
    private String telefono;


    // cellulare del contatto (facoltativo)
    @AIColumn(type = AFType.text, width = 140, header = "Cell.")
    @AIField(type = AFType.text, width = "20em", caption = "Cellulare", help = "Cellulare del contatto.")
    private String cellulare;


    //--eventuali note (facoltativo)
    @AIColumn(type = AFType.textarea, width = 200, header = "Note")
    @AIField(type = AFType.textarea, width = "27em", caption = "Note", help = "Eventuali note aggiuntive.")
    private String note = "";


    //--inserimento iniziale (facoltativo)
    @AIColumn(type = AFType.localdate, width = 200, header = "Inizio")
    @AIField(type = AFType.localdate, enabled = false, caption = "Inizio", help = "Data di inserimento della company")
    private LocalDate partenza;


    /**
     * The default constructor only exists for the sake of JPA.
     * You won’t use it directly, so it is designated as protected.
     * Costruttore senza argomenti
     * Obbligatorio per le specifiche JavaBean
     * Da non usare MAI per la creazione diretta di una nuova istanza (si perdono i controlli)
     */
    protected Company() {
    }// end of JavaBean constructor


    /**
     * The default constructor for Spring.
     * Il service (dao, repository) viene iniettato in questo costruttore
     *
     * @param companyService iniettato direttamente da Spring
     */
    @Autowired
    public Company(CompanyService companyService) {
        this(companyService, "", "", "", "", "", "", "", "", (LocalDate) null);
    }// end of Spring constructor


    /**
     * Costruttore completo (indispensabile per il Service di SpringBoot)
     * You won’t use it directly, so it is designated as reserved.
     * Eventuali regolazioni iniziali delle property
     * Eventuale uso del service (fornito)
     * La data di partenza (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param companyService iniettato direttamente da Spring
     * @param sigla       di riferimento interna (obbligatoria ed unica)
     * @param descrizione della company (obbligatoria)
     * @param email       posta elettronica (facoltativo)
     * @param indirizzo   postale (facoltativo)
     * @param contatto    persona di riferimento (facoltativo)
     * @param telefono    fisso ufficio (facoltativo)
     * @param cellulare   del contatto (facoltativo)
     * @param note        eventuali (facoltativo)
     * @param partenza    inserimento iniziale (facoltativo)
     */
    Company(CompanyService companyService, String sigla, String descrizione, String email, String indirizzo, String contatto, String telefono, String cellulare, String note, LocalDate partenza) {
        this.setSigla(sigla);
        this.setDescrizione(descrizione);
        this.setEmail(email);
        this.setIndirizzo(indirizzo);
        this.setContatto(contatto);
        this.setTelefono(telefono);
        this.setCellulare(cellulare);
        this.setNote(note);
        this.setPartenza((partenza != null) ? partenza : LocalDate.now());
    }// end of constructor

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
        return this.getSigla();
    }// end of method


    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getContatto() {
        return contatto;
    }

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getPartenza() {
        return partenza;
    }

    public void setPartenza(LocalDate partenza) {
        this.partenza = partenza;
    }

}// end of entity class



