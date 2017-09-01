package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 07-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
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


//    /**
//     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
//     * (si può usare qualsiasi firma)
//     * Regola il modello-dati specifico nel Service
//     */
//    @PostConstruct
//    protected void inizia() {
//        super.inizia();
//        super.setUsaSeparateFormDialog(true);
//    }// end of method

//    /**
//     * Creazione del form
//     * Pannello a tutto schermo, oppure finestra popup
//     * Ricrea tutto ogni volta che diventa attivo
//     *
//     * @param presenter  di riferimento per gli eventi
//     * @param entityBean istanza da presentare
//     * @param fields     del form da visualizzare
//     */
//    @Override
//    public void restart(AlgosPresenterImpl presenter, AEntity entityBean, List<String> fields) {
//        this.entityBean = entityBean;
//        usaSeparateFormDialog(presenter, fields);
//    }// end of method


}// end of class

