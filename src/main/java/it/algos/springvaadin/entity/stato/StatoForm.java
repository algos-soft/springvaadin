package it.algos.springvaadin.entity.stato;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.field.AlgosImageField;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.toolbar.FormToolbar;

import javax.xml.bind.DatatypeConverter;
import java.util.List;

import static com.sun.tools.doclint.Entity.ge;
import static it.algos.springvaadin.lib.LibResource.getStreamResource;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_STA)
public class StatoForm extends AlgosFormImpl {

    @Autowired
    private StatoService statoService;

    @Autowired
    private AlgosImageField imageField;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public StatoForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor

    /**
     * Crea i campi
     *
     * @param presenter  di riferimento per gli eventi
     * @param fieldsName del form da visualizzare
     *
     * @return lista di fields
     */
    @Override
    protected List<AlgosField> creaFields(AlgosPresenterImpl presenter, List<String> fieldsName) {
        List<AlgosField> listaFields = super.creaFields(presenter, fieldsName);
        imageField.setEntityBean(entityBean);
        imageField.setSource(presenter);
        imageField.setName("bandiera");
        imageField.setCaption("Bandiera");
        listaFields.add(imageField);
        return listaFields;
    }// end of method


    /**
     * Trasferisce i valori dai campi dell'interfaccia alla entityBean
     * Esegue la (eventuale) validazione dei dati
     * Esegue la (eventuale) trasformazione dei dati
     *
     * @return la entityBean del Form
     */
    @Override
    public AlgosEntity commit() {
        byte[] imgBytes = ((Stato) entityBean).getBandiera();
        super.commit();
        ((Stato) entityBean).setBandiera(imgBytes);

        return entityBean;
    }// end of method


}// end of class

