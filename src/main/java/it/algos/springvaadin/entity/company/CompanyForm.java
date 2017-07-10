package it.algos.springvaadin.entity.company;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Layout;
import it.algos.springvaadin.entity.log.Log;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 25/06/17
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@SpringComponent
@Qualifier(Cost.TAG_COMP)
public class CompanyForm extends AlgosFormImpl {


    public Binder<Company> binder;


    /**
     * Costruttore @Autowired (nella superclasse
     */
    public CompanyForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     */
    protected String fixCaption(AlgosEntity entity) {
        super.captionCreate = "Nuova company";
        super.captionEdit = "Modifica company";

        return super.fixCaption(entity);
    }// end of method

//    @Override
//    public void creaFields(Layout layout, boolean newRecord, AlgosModel entityBean, List<String> campiVisibili) {
//        binder = new Binder<>(Company.class);
//        super.entityBean = (Company) entityBean;
//        AbstractField field;
//        LinkedHashMap<String, Object> mappa;
//        Object value;
//
//        if (super.entityBean == null) {
//            super.entityBean = companyModel;
//        }// end of if cycle
//
//        mappa = companyService.getBeanMap(entityBean);
//        for (String publicFieldName : campiVisibili) {
//            field = LibField.create(Company.class, publicFieldName);
//            if (field != null) {
//                layout.addComponent(field);
//                binder.bind(field, publicFieldName);
//                if (newRecord) {
//                    value = mappa.get(publicFieldName);
//                    if (value != null) {
//                        field.setValue(value);
//                    }// end of if cycle
//                }// end of if cycle
//            }// end of if cycle
//        }// end of for cycle
//
//        binder.setBean((Company)super.entityBean);
//    }// end of method

}// end of class
