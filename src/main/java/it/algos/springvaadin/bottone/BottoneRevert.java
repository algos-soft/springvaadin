package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Qualifier(Cost.TAG_BOT_REVERT)
@SpringComponent
@Scope("prototype")
public class BottoneRevert extends Bottone {

    public BottoneRevert(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.setCaption("Ripristina");
        super.setIcon(VaadinIcons.REFRESH);
        super.setEnabled(false);
        super.tipo = TipoBottone.revert;

        super.inizia();
    }// end of method

}// end of class
