package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Qualifier(Cost.TAG_BOT_SHOW_ALL)
@SpringComponent
@Scope("prototype")
public class BottoneShowAll extends Bottone {

    public BottoneShowAll(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.setCaption("Tutto");
        super.setIcon(VaadinIcons.ALIGN_JUSTIFY);
        super.setEnabled(true);
        super.tipo = TipoBottone.showAll;

        super.inizia();
    }// end of method

}// end of class
