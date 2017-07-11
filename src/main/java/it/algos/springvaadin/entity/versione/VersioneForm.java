package it.algos.springvaadin.entity.versione;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.field.AlgosIntegerField;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibField;
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
        LinkedHashMap<String, Object> mappa;
        Object value;

        if (entity == null) {
//            entity= versioneService.reset();
        }// end of if cycle

//        for (String publicFieldName : fields) {
//            field = LibField.create(Versione.class, publicFieldName);
//            if (field != null) {
//                layout.addComponent(field);
////                binder.bind(field, publicFieldName);
//
////                binder.forField(field)
////                        .withValidator(val -> val!=null, "Non può essere nullo")
////                        .bind(publicFieldName);
//
////                if (newRecord) {
////                    value = mappa.get(publicFieldName);
////                    if (value != null) {
////                        field.setValue(value);
////                    }// end of if cycle
////                }// end of if cycle
//            }// end of if cycle
//        }// end of for cycle
        field = LibField.create(Versione.class, "titolo");
        layout.addComponent(field);
        binder.forField(field)
                .withValidator(val -> ((String)val).length()!=3, "Non può essere nullo")
                .bind("titolo");

        binder.setBean((Versione) entity);
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
