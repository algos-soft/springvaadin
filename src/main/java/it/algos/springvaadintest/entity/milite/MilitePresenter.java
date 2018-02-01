package it.algos.springvaadintest.entity.milite;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.presenter.APresenter;
import it.algos.springvaadin.service.IAService;
import it.algos.springvaadin.list.IAList;
import it.algos.springvaadin.form.IAForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.lib.ACost;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: 2018-01-31_15:21:40
 * Estende la Entity astratta APresenter che gestisce la business logic
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Scope (obbligatorio = 'session')
 * Annotated with @Qualifier (obbligatorio) per permettere a Spring di istanziare la sottoclasse specifica
 * Annotated with @AIScript (facoltativo) per controllare la ri-creazione di questo file nello script del framework
 * Costruttore con dei link @Autowired di tipo @Lazy per evitare un loop nella injection
 */
@SpringComponent
@Scope("session")
@Qualifier(ACost.TAG_MIL)
@AIScript(sovrascrivibile = true)
public class MilitePresenter extends APresenter {

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     * Regola il modello-dati specifico
     */
    public MilitePresenter(
                @Lazy @Qualifier(ACost.TAG_MIL) IAService service,
                @Lazy @Qualifier(ACost.TAG_MIL) IAList list,
                @Lazy @Qualifier(ACost.TAG_MIL) IAForm form) {
        super(service, list, form);
        super.entityClass = Milite.class;
     }// end of Spring constructor


}// end of class
