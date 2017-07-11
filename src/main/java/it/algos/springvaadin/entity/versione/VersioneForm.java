package it.algos.springvaadin.entity.versione;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.field.AlgosDateTimeField;
import it.algos.springvaadin.field.AlgosIntegerField;
import it.algos.springvaadin.field.AlgosTextField;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.FormToolbar;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 13/06/17
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersioneForm extends AlgosFormImpl {


    public Binder<Versione> binder;

    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public VersioneForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     */
    protected String fixCaption(AlgosEntity entity) {
        super.captionCreate = "Nuova versione";
        super.captionEdit = "Modifica versione";

        return super.fixCaption(entity);
    }// end of method


//    /**
//     * Esegue il 'rollback'
//     * Revert (ripristina) button pressed in form
//     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
//     */
//    @Override
//    public void revertEntity() {
//        binder.readBean((Versione) entity);
//    }// end of method
//
//
//    /**
//     * Esegue il 'commit'.
//     * Trasferisce i valori dai campi alla entityBean
//     * Esegue la validazione dei dati
//     * Esegue la trasformazione dei dati
//     * Chiude la (eventuale) finestra utilizzata
//     *
//     * @return la entity del Form
//     */
//    @Override
//    public AlgosEntity writeBean() {
//
//        try { // prova ad eseguire il codice
//            binder.writeBean((Versione) entity);
//        } catch (Exception unErrore) { // intercetta l'errore
//            int errore = 87;
//        }// fine del blocco try-catch
//
//        closeWindow();
//
//        return entity;
//    }// end of method

}// end of class
