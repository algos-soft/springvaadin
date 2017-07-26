package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Qualifier(Cost.TAG_BOT_CREATE)
@SpringComponent
@Scope("prototype")
public class BottoneCreate extends Bottone {

    public BottoneCreate(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.setCaption("Nuovo");
        super.setIcon(VaadinIcons.PLUS);
        super.setEnabled(true);
        super.tipo = TipoBottone.create;

        super.inizia();
    }// end of method

}// end of class
