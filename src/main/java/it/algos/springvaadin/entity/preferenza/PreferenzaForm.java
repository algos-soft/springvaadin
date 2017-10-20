package it.algos.springvaadin.entity.preferenza;

import com.vaadin.data.Binder;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.HasValue;
import com.vaadin.data.Validator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.entity.log.LogType;
import it.algos.springvaadin.field.AComboField;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AImageField;
import it.algos.springvaadin.field.AJSonField;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.FormToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PRE)
@Slf4j
public class PreferenzaForm extends AlgosFormImpl {

    @Autowired
    private LogService logger;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public PreferenzaForm(@Qualifier(Cost.BAR_FORM) AToolbar toolbar,
                          @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        super(toolbar, toolbarLink);
    }// end of Spring constructor


    /**
     * Eventuali regolazioni specifiche per i fields
     */
    @Override
    protected void fixFields() {
        String fieldName = "type";
        AField field = null;

        if (entityBean != null && entityBean instanceof Preferenza) {
            try { // prova ad eseguire il codice
                field = getField(fieldName);
                if (entityBean.id == null) {
                    //--valore di default per una nuova scheda
                    field.setValue(PrefType.string);
                } else {
                    //--disabilita il campo in modifica scheda
                    field.setEnabled(false);
                }// end of if/else cycle
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
        }// end of if cycle
    }// end of method


    /**
     * Checks if the entity has no current validation errors at all
     * Se la entity è OK, può essere registrata
     *
     * @return tutte le property della entity sono valide
     */
    @Override
    public boolean entityIsOk() {
        boolean entityValida = false;

        try { // prova ad eseguire il codice




//            while(var2.hasNext()) {
//                Binder.BindingImpl<?, ?, ?> binding = (Binder.BindingImpl)var2.next();
//                results.add(binding.doValidation());
//            }


//            Stream<HasValue<?>> stream = binder.getFields();
//            Optional alfa = stream.findFirst();
//           Binder.Binding bind = (Binder.Binding)binder.getBinding("value");
//            bind.doValidation();
//            Iterator var2 = bind.iterator();



            entityValida = binder != null && binder.validate().isOk();
        } catch (Exception unErrore) { // intercetta l'errore
            Notification.show("Accetta", "Scheda non valida", Notification.Type.WARNING_MESSAGE);
        }// fine del blocco try-catch

        return entityValida;
    }// end of method


}// end of class

