package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_IMPORT)
public class BottoneImport extends Bottone {


    public BottoneImport(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.importa);
    }// end of @Autowired constructor

//    /**
//     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
//     */
//    @PostConstruct
//    protected void inizia() {
//        super.inizia();
//
////        super.setCaption("Import");
////        super.setIcon(VaadinIcons.EXTERNAL_BROWSER);
////        super.setEnabled(true);
////        super.setTipo(TipoBottone.importa);
//    }// end of method

}// end of class
