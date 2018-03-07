package it.algos.springtemplates.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.annotation.AIView;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.login.ALogin;
import it.algos.springvaadin.menu.MenuHome;
import it.algos.springvaadin.service.AResourceService;
import it.algos.springvaadin.view.AView;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:52
 */
@Slf4j
@Scope("session")
@SpringView(name = ACost.VIEW_TEMPLATES)
@AIView(roleTypeVisibility = EARoleType.developer)
public class ATemplatesView extends AView {


    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.HOME;


    //    @Autowired
    private AResourceService resource;



    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public ATemplatesView(AResourceService resource) {
        super(null, null);
        this.resource=resource;
    }// end of Spring constructor


    /**
     * Metodo invocato (dalla SpringNavigator di SpringBoot) ogni volta che la view diventa attiva
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Passa il controllo al Presenter
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.start();
    }// end of method


    /**
     * Creazione di una view con splash screen
     */
    public void start() {
        this.removeAllComponents();

        //--componente grafico obbligatorio
        this.bodyLayout.setContent(getImage());
        this.addComponent(bodyLayout);

        this.setExpandRatio(bodyLayout, 1);
    }// end of method


    private Layout getImage() {
        String nomeImmagineConSuffisso = "amb3.jpg";
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSizeFull();
        int larImage = 650;
        int altImage = 400;
        double delta = 1.5;
        int lar = ((Double) (larImage * delta)).intValue();
        int alt = ((Double) (altImage * delta)).intValue();

        Image image = resource.getImage(nomeImmagineConSuffisso);
        image.setWidth(lar, Unit.PIXELS);
        image.setHeight(alt, Unit.PIXELS);

        layout.addComponent(image);
        layout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);

        return layout;
    }// end of method


}// end of class
