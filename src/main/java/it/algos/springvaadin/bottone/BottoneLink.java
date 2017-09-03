package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.event.TypeField;
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
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_LINK)
public class BottoneLink extends Bottone {



    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param applicationEventPublisher iniettata da Spring
     */
    public BottoneLink(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(TypeButton.editLink);
    }// end of Spring constructor


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AEvent event)
     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            applicationEventPublisher.publishEvent(new AFieldEvent(TypeField.linkTarget, source, target, entityBean, fieldParent));
        }// end of if cycle
    }// end of if/else cycle



}// end of class

