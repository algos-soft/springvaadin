package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
 * Usata dalle applicazioni che hanno il flag AlgosApp.USE_MULTI_COMPANY=true
 */
@Entity
@Service
@Table(name = Cost.TAG_COMP)
@Qualifier(Cost.TAG_COMP)
public class Company extends AlgosEntity {

    //--versione della classe per la serializzazione
    private final static long serialVersionUID = 1L;


    //--sigla di riferimento interna (interna, obbligatoria ed unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Column(length = 20, unique = true)
    @AIField(type = AFType.text, required = true, widthEM = 8, help = "Codice interno.")
    @AIColumn(width = 80)
    private String sigla;


    //--ragione sociale o descrizione della company (visibile - obbligatoria)
    //--descrizione (obbligatoria, non unica)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @NotEmpty
    @Size(min = 2, max = 50)
    @AIField(type = AFType.text, required = true, widthEM = 30, help = "Descrizione della company.")
    @AIColumn(width = 320)
    private String descrizione;

    //--posta elettronica (facoltativo)
    //--non va inizializzato con una stringa vuota, perché da Vaadin 8 in poi lo fa automaticamente
    @Email
    @AIField(type = AFType.email, widthEM = 20, name = "Mail", help = "Indirizzo di posta elettronica.")
    @AIColumn(width = 270)
    private String email;

    //--indirizzo postale (facoltativo)
    @AIField(type = AFType.text, widthEM = 27,  help = "Indirizzo postale.")
    @AIColumn(width = 320)
    private String indirizzo;


    //--persona di riferimento (facoltativo)
    @AIField(type = AFType.text, widthEM = 20, name = "Riferimento", help = "Persona di riferimento.")
    @AIColumn(name = "Contatto", width = 170)
    private String contatto;


    //--telefono fisso ufficio (facoltativo)
    @AIField(type = AFType.text, widthEM = 20,  help = "Telefono ufficio.")
    @AIColumn(name = "Tel.", width = 140)
    private String telefono;


    //--cellulare del contatto (facoltativo)
    @AIField(type = AFType.text, widthEM = 20,  help = "Cellulare del contatto.")
    @AIColumn(name = "Cell.", width = 140)
    private String cellulare;


    //--eventuali note (facoltativo)
    @AIField(type = AFType.textarea, widthEM = 27, help = "Eventuali note aggiuntive.")
    private String note = "";


    //--inserimento iniziale (facoltativo)
    @AIField(type = AFType.localdate, enabled = false, name = "Inizio", help = "Data di inserimento della company")
    private LocalDate partenza;


    /**
     * The default constructor only exists for the sake of JPA.
     * You won’t use it directly, so it is designated as protected.
     * Costruttore senza argomenti
     * Obbligatorio per le specifiche JavaBean
     * Da non usare MAI per la creazione diretta di una nuova istanza (si perdono i controlli)
     */
    protected Company() {
        this( "", "", "", "", "", "", "", "", (LocalDate) null);
    }// end of JavaBean constructor



    /**
     * Costruttore completo (indispensabile per il Service di SpringBoot)
     * You won’t use it directly, so it is designated as reserved.
     * Eventuali regolazioni iniziali delle property
     * La data di partenza (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param sigla          di riferimento interna (obbligatoria ed unica)
     * @param descrizione    della company (obbligatoria)
     * @param email          posta elettronica (facoltativo)
     * @param indirizzo      postale (facoltativo)
     * @param contatto       persona di riferimento (facoltativo)
     * @param telefono       fisso ufficio (facoltativo)
     * @param cellulare      del contatto (facoltativo)
     * @param note           eventuali (facoltativo)
     * @param partenza       inserimento iniziale (facoltativo)
     */
    Company( String sigla, String descrizione, String email, String indirizzo, String contatto, String telefono, String cellulare, String note, LocalDate partenza) {
        this.setSigla(sigla);
        this.setDescrizione(descrizione);
        this.setEmail(email);
        this.setIndirizzo(indirizzo);
        this.setContatto(contatto);
        this.setTelefono(telefono);
        this.setCellulare(cellulare);
        this.setNote(note);
        this.setPartenza((partenza != null) ? partenza : LocalDate.now());
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
        return this.getSigla();
    }// end of method


    public String getSigla() {
        return sigla;
    }// end of getter method

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }// end of setter method

    public String getDescrizione() {
        return descrizione;
    }// end of getter method

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }// end of setter method

    public String getEmail() {
        return email;
    }// end of getter method

    public void setEmail(String email) {
        this.email = email;
    }// end of setter method

    public String getIndirizzo() {
        return indirizzo;
    }// end of getter method

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }// end of setter method

    public String getContatto() {
        return contatto;
    }// end of getter method

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }// end of setter method

    public String getTelefono() {
        return telefono;
    }// end of getter method

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }// end of setter method

    public String getCellulare() {
        return cellulare;
    }// end of getter method

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }// end of setter method

    public String getNote() {
        return note;
    }// end of getter method

    public void setNote(String note) {
        this.note = note;
    }// end of setter method

    public LocalDate getPartenza() {
        return partenza;
    }// end of getter method

    public void setPartenza(LocalDate partenza) {
        this.partenza = partenza;
    }// end of setter method


}// end of entity class



