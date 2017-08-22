package it.algos.springvaadin.entity.stato;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import it.algos.springvaadin.app.AlgosApp;
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
        imageField.setFormPresenter(presenter);
        imageField.setName("bandiera");
        imageField.setCaption("Bandiera");
        listaFields.add(imageField);
        return listaFields;
    }// end of method

    /**
     * Usa tutto lo schermo
     *
     * @param presenter di riferimento per gli eventi
     * @param fields    del form da visualizzare
     */
    protected void usaAllScreenx(AlgosPresenterImpl presenter, List<String> fields) {
        super.usaAllScreen(presenter, fields);

        String name = "AUS.png";
//        byte[] bytes = LibResource.getImgBytes(AlgosApp.IMG_FOLDER_NAME, name);
//
////        String str = DatatypeConverter.printBase64Binary(bytes);
//        String str = Base64.encodeBase64String(bytes);
//        Stato stato = statoService.findByNome("Austria");
//        stato.setNumerico(str);
//        try { // prova ad eseguire il codice
//            statoService.save(stato);
//        } catch (Exception unErrore) { // intercetta l'errore
//            int a = 87;
//        }// fine del blocco try-catch
//
//        Resource res = LibResource.getStreamResource(bytes);
//        Image image = LibResource.getImage(res);
//        image.setHeight("4em");
//        image.setWidth("8em");
//        this.addComponent(image);

        if (entityBean != null && entityBean.getId().equals("ita")) {
            name = "AUS.png";
        }// end of if cycle
        if (entityBean != null && entityBean.getId().equals("aus")) {
            name = "DEU.png";
        }// end of if cycle

        Image imageA = LibResource.getImage(name);
        imageA.setHeight("4em");
        imageA.setWidth("8em");

        this.addComponent(imageA);
    }// end of method


    /**
     * Usa tutto lo schermo
     *
     * @param presenter di riferimento per gli eventi
     * @param fields    del form da visualizzare
     */
    protected void usaAllScreen(AlgosPresenterImpl presenter, List<String> fields) {
        super.usaAllScreen(presenter, fields);

//        creaAustria();
//        Stato stato = statoService.findByNome("Austria");
//        String str = stato.getNumerico();
//
//        byte[] decodedBytes = Base64.decodeBase64(str);
//
//        Resource res = LibResource.getStreamResource(decodedBytes);
//        Image image = LibResource.getImage(res);
//        image.setHeight("4em");
//        image.setWidth("8em");
//        this.addComponent(image);
//

        String name = "";
        if (entityBean != null && entityBean.getId().equals("ita")) {
            name = "AUS.png";
        }// end of if cycle
        if (entityBean != null && entityBean.getId().equals("aut")) {
            name = "DEU.png";
        }// end of if cycle

        Image image = LibResource.getImage(name);
        if (image!=null) {
            image.setHeight("4em");
            image.setWidth("8em");
            this.addComponent(image);
        }// end of if cycle



//        Image imageA = LibResource.getImage("AUS.png");
//        imageA.setHeight("4em");
//        imageA.setWidth("8em");
//
//        this.addComponent(imageA);
    }// end of method


    private void creaAustria() {

        String name = "AUS.png";
        byte[] bytes = LibResource.getImgBytes(AlgosApp.IMG_FOLDER_NAME, name);

        String str = Base64.encodeBase64String(bytes);
        Stato stato = statoService.findByNome("Austria");
        stato.setNumerico(str);
        try { // prova ad eseguire il codice
            statoService.save(stato);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

    }// end of method


}// end of class

