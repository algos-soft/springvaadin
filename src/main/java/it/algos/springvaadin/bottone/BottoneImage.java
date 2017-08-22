package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

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


}// end of class