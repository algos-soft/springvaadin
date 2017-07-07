package it.algos.springvaadin.entity.log;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Layout;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 30/06/17.
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@Lazy
@Qualifier(Cost.TAG_LOG)
@SpringComponent
public class LogForm extends AlgosForm {

    //--il service (dao, repository) viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private LogService logService;

    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Log logModel;

    public Binder<Log> binder;


    @Override
    public void creaFields(Layout layout, boolean newRecord, AlgosModel entityBean, List<String> campiVisibili) {
        binder = new Binder<>(Log.class);
        super.entityBean =  entityBean;
        AbstractField field;
        LinkedHashMap<String, Object> mappa;
        Object value;

        if (super.entityBean == null) {
            super.entityBean = logModel;
        }// end of if cycle

        mappa = logService.getBeanMap(entityBean);
        for (String publicFieldName : campiVisibili) {
            field = LibField.create(Log.class, publicFieldName);
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

        binder.setBean((Log) super.entityBean);
    }// end of method




}// end of class
