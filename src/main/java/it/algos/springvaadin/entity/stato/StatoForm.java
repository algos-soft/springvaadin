package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AImageField;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.toolbar.AlgosToolbar;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_STA)
public class StatoForm extends AlgosFormImpl {

    @Autowired
    private StatoService statoService;

    @Autowired
    private AImageField imageField;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public StatoForm(@Qualifier(Cost.BAR_FORM) AlgosToolbar toolbar,
                     @Qualifier(Cost.BAR_LINK) AlgosToolbar toolbarLink) {
        super(toolbar, toolbarLink);
    }// end of Spring constructor


    /**
     * Eventuali regolazioni specifiche per i fields
     */
    @Override
    protected void fixFields() {
        AField field = getField("bandiera");

        if (field != null && field instanceof AImageField) {
            field.setEntityBean(entityBean);
            field.setSource(null);
        }// end of if cycle

    }// end of method

    /**
     * Trasferisce i valori dai campi dell'annotation alla entityBean
     * Esegue la (eventuale) validazione dei dati
     * Esegue la (eventuale) trasformazione dei dati
     *
     * @return la entityBean del Form
     */
    @Override
    public AEntity commit() {
        byte[] imgBytes = ((Stato) entityBean).getBandiera();
        super.commit();
        ((Stato) entityBean).setBandiera(imgBytes);

        return entityBean;
    }// end of method


}// end of class

