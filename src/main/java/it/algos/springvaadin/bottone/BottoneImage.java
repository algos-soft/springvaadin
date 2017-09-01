package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.event.EventType;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Bottone affiancato al field dell'immagine, di norma a sinistra
 * Apre un dialogo di dettaglio o modifica del field immagine
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_IMAGE)
public class BottoneImage extends Bottone {


    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param applicationEventPublisher iniettata da Spring
     */
    public BottoneImage(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.image);
    }// end of Spring constructor


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AEvent event)
     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            applicationEventPublisher.publishEvent(new AFieldEvent(EventType.linkTarget, source, target, entityBean, null));
        }// end of if cycle
    }// end of if/else cycle


}// end of class