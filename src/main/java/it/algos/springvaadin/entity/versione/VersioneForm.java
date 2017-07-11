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


    @Override
    public void creaFields(Layout layout, List<String> fields) {
        binder = new Binder<>(Versione.class);
        AbstractField field;
        AbstractValidator validator = null;
        Object value = null;

        for (String publicFieldName : fields) {
            field = LibField.create(Versione.class, publicFieldName);
            validator = LibField.creaValidator(Versione.class, publicFieldName);
            if (field != null) {
                layout.addComponent(field);

                if (field.isEnabled()) {
                    if (validator != null) {
                        binder.forField(field)
                                .withValidator(validator)
                                .bind(publicFieldName);
                    } else {
                        binder.forField(field)
                                .bind(publicFieldName);
                    }// end of if/else cycle
                } else {
                    value = LibReflection.getValue(entity, publicFieldName);
                    field.setValue(value);
                }// end of if/else cycle

            }// end of if cycle
        }// end of for cycle

        binder.readBean((Versione) entity);
    }// end of method


    /**
     * Esegue il 'rollback'
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    @Override
    public void revertEntity() {
        binder.readBean((Versione) entity);
    }// end of method


    /**
     * Esegue il 'commit'.
     * Trasferisce i valori dai campi alla entityBean
     * Esegue la validazione dei dati
     * Esegue la trasformazione dei dati
     * Chiude la (eventuale) finestra utilizzata
     *
     * @return la entity del Form
     */
    @Override
    public AlgosEntity writeBean() {
//        Object a = this.getComponent(0);
//        Label label = (Label) a;
//        String testo = label.getValue();
//
//        Object aa = this.getComponent(1);
//        AlgosIntegerField fieldInt = (AlgosIntegerField) aa;
//        int intval = fieldInt.getValue();
//
//        Object b = this.getComponent(2);
//        AlgosTextField field2 = (AlgosTextField) b;
//        String testo2 = field2.getValue();
//
//        Object c = this.getComponent(3);
//        AlgosTextField field3 = (AlgosTextField) c;
//        String testo3 = field3.getValue();
//
//        Object d = this.getComponent(4);
//        AlgosDateTimeField time = (AlgosDateTimeField) d;
//        Object valtime = time.getValue();


        try { // prova ad eseguire il codice
            binder.writeBean((Versione) entity);
        } catch (Exception unErrore) { // intercetta l'errore
            int errore = 87;
        }// fine del blocco try-catch

        closeWindow();

        return entity;
    }// end of method

    public void creaFields2(Layout layout, AlgosEntity entity, List<String> fields) {
        Binder<Versione> binder = new Binder<>();
        TextField titleField = new TextField();
        AlgosIntegerField intField = new AlgosIntegerField();

        // Start by defining the Field instance to use
//        binder.forField(titleField)
//                // Finalize by doing the actual binding to the Person class
//                .bind(
//                        // Callback that loads the title from a person instance
//                        Versione::getTitolo,
//                        // Callback that saves the title in a person instance
//                        Versione::setTitolo);

//        binder.forField(intField)
//                // Finalize by doing the actual binding to the Person class
//                .bind(
//                        // Callback that loads the title from a person instance
//                        Versione::getOrdine,
//                        // Callback that saves the title in a person instance
//                        Versione::setOrdine);
        binder.forField(titleField)
                .withValidator(val -> val.length() == 4, "")
                .bind("titolo");

        binder.forField(intField).bind("ordine");
    }// end of method

}// end of class
