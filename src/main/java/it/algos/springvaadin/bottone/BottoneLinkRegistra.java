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
 * Date: dom, 03-set-2017
 * Time: 08:46
 * Registra una entity nel modulo linkato
 * Esegue alcune regolazioni nel modulo chiamante
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_LINK_REGISTRA)
public class BottoneLinkRegistra extends Bottone {


    public BottoneLinkRegistra(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.linkRegistra);
    }// end of @Autowired constructor


//    /**
//     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
//     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AEvent event)
//     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
//     */
//    protected void fire(Button.ClickEvent clickEvent) {
//        if (source != null) {
//            publisher.publishEvent(new AFieldEvent(TypeField.linkTargetDBRef, source, target, entityBean, fieldParent));
//        }// end of if cycle
//    }// end of if/else cycle

}// end of class

