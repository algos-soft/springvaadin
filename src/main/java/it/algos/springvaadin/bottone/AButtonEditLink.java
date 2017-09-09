package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 07:42
 * Apertura di un collegamento (target) in un altro modulo
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_LINK)
public class AButtonEditLink extends AButton {

    private AButtonType typeLink = AButtonType.editLinkDBRef;


    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public AButtonEditLink() {
        this(null);
    }// end of Spring constructor


    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param applicationEventPublisher iniettata da Spring
     */
    public AButtonEditLink(ApplicationEventPublisher applicationEventPublisher) {
        super();
        super.setType(AButtonType.editLink);
    }// end of Spring constructor

    protected void addListener() {
//super.addListener();
    }// end of method

    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AEvent event)
     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            publisher.publishEvent(new AButtonEvent(typeLink, source, target, entityBean, fieldParent));
        }// end of if cycle
    }// end of if/else cycle


    public void setTypeLink(AButtonType typeLink) {
        this.typeLink = typeLink;
    }// end of method


}// end of class

