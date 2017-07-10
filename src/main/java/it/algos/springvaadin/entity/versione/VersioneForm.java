package it.algos.springvaadin.entity.versione;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
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
    public void creaFields(Layout layout, AlgosEntity entity, List<String> fields) {
        binder = new Binder<>(Versione.class);
        AbstractField field;
        LinkedHashMap<String, Object> mappa;
        Object value;

        if (entity == null) {
//            entity= versioneService.reset();
        }// end of if cycle

        for (String publicFieldName : fields) {
            field = LibField.create(Versione.class, publicFieldName);
            if (field != null) {
                layout.addComponent(field);
                binder.bind(field, publicFieldName);
//                if (newRecord) {
//                    value = mappa.get(publicFieldName);
//                    if (value != null) {
//                        field.setValue(value);
//                    }// end of if cycle
//                }// end of if cycle
            }// end of if cycle
        }// end of for cycle

        binder.setBean((Versione) entity);
    }// end of method


}// end of class
