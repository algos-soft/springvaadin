package it.algos.springvaadin.entities.versione;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.field.AlgosDateField;
import it.algos.springvaadin.field.AlgosTextField;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.lib.LibTime;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 13/06/17
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@Lazy
@SpringComponent
public class VersioneForm extends AlgosForm {

    //--il service (dao, repository) viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private VersioneService versioneService;


    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Versione versioneModel;


    public Binder<Versione> binder;


    @Override
    public void creaFields(Layout layout, boolean newRecord, AlgosModel entityBean, List<String> campiVisibili) {
        binder = new Binder<>(Versione.class);
        AbstractField field;
        LinkedHashMap<String, Object> mappa;
        Object value;

        if (entityBean == null) {
            entityBean= versioneService.reset();
        }// end of if cycle

        mappa = versioneService.getBeanMap(entityBean);
        for (String publicFieldName : campiVisibili) {
            field = LibField.create(Versione.class, publicFieldName);
            if (field != null) {
                layout.addComponent(field);
                binder.bind(field, publicFieldName);
                if (newRecord) {
                    value = mappa.get(publicFieldName);
                    if (value != null) {
                        field.setValue(value);
                    }// end of if cycle
                }// end of if cycle
            }// end of if cycle
        }// end of for cycle

        binder.setBean((Versione) entityBean);
        super.entityBean =  entityBean;
    }// end of method


}// end of class
