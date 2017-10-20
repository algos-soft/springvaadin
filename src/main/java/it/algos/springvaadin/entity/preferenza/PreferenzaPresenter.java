package it.algos.springvaadin.entity.preferenza;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.AComboField;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AJSonField;
import it.algos.springvaadin.field.ALinkField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.search.AlgosSearch;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Slf4j
@Qualifier(Cost.TAG_PRE)
public class PreferenzaPresenter extends AlgosPresenterImpl {

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     * Regola il modello-dati specifico
     */
    public PreferenzaPresenter(@Qualifier(Cost.TAG_PRE) AlgosView view, @Qualifier(Cost.TAG_PRE) AlgosService service, AlgosSearch search) {
        super(view, service, search);
        super.entityClass = Preferenza.class;
    }// end of Spring constructor

    @Override
    public void fieldModificato(ApplicationListener source, AEntity entityBean, AField sourceField) {
        AJSonField fieldValue = null;
        AComboField fieldType = null;
        String typeTxt = null;
        Object obj = null;
        PrefType type = null;

        try { // prova ad eseguire il codice
            fieldValue = (AJSonField) getField("value");
            fieldType = (AComboField) getField("type");
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch

        if (fieldType != null) {
            obj = fieldType.getValue();
        }// end of if cycle

        if (obj != null && obj instanceof PrefType) {
            fieldValue.refreshFromComboField((PrefType) obj);
        }// end of if cycle

    }// end of method

}// end of class
