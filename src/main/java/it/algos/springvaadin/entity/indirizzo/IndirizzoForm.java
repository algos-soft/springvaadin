package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.toolbar.FormToolbar;

import java.util.List;

/**
 * Created by gac on 07-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_IND)
public class IndirizzoForm extends AlgosFormImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public IndirizzoForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param entityBean istanza da presentare
     * @param fields     del form da visualizzare
     */
    @Override
    public void restart(AlgosEntity entityBean, List<String> fields) {
        this.entityBean = entityBean;
        usaSeparateFormDialog(fields);
    }// end of method


}// end of class

