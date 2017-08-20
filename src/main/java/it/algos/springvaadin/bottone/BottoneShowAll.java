package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_SHOW_ALL)
public class BottoneShowAll extends Bottone {


    public BottoneShowAll(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.show);
    }// end of @Autowired constructor


//    /**
//     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
//     */
//    @PostConstruct
//    protected void inizia() {
//        super.inizia();
//
////        super.setCaption("Tutto");
////        super.setIcon(VaadinIcons.ALIGN_JUSTIFY);
////        super.setEnabled(true);
////        super.setTipo(TipoBottone.showAll);
//    }// end of method

}// end of class
