package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibColumn;
import it.algos.springvaadin.lib.LibDate;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.renderer.ByteStringRenderer;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.ListToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PRE)
@Slf4j
public class PreferenzaList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public PreferenzaList(@Qualifier(Cost.TAG_PRE) AlgosService service, AlgosGrid grid, ListToolbar toolbar) {
        super(service, grid, toolbar);
    }// end of Spring constructor

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     * Può essere sovrascritto per un'intestazione (caption) della grid
     */
    @Override
    protected void inizializza(String className, List items) {
        if (LibSession.isDeveloper()) {
            super.inizializza(className, items);
            caption += "</br>Lista visibile solo all'admin che vede SOLO le schede della sua company";
            caption += "</br>Usa la company (se AlgosApp.USE_MULTI_COMPANY=true) che è facoltativa";
            caption += "</br>Solo il developer vede queste note";
        } else {
            super.caption = "Preferenze di funzionamento del programma";
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la finestra diventa attiva
     *
     * @param source      di riferimento per gli eventi
     * @param entityClass del modello dati
     * @param items       da visualizzare nella grid
     * @param columns     da visualizzare nella grid
     */
    @Override
    public void restart(AlgosPresenterImpl source, Class<? extends AEntity> entityClass, List items, List<String> columns) {
        super.restart(source, entityClass, items, columns);

        Grid.Column colonna = grid.addColumn(
                preferenza -> {
                    PrefType type = ((Preferenza) preferenza).getType();
                    byte[] bytes = (byte[]) ((Preferenza) preferenza).getValue();
                    Object value = null;

                    switch (type) {
                        case string:
                        case bool:
                        case integer:
                        case email:
                            try { // prova ad eseguire il codice
                                value = type.bytesToObject(bytes);
                            } catch (Exception unErrore) { // intercetta l'errore
                                log.error(unErrore.toString());
                            }// fine del blocco try-catch
                            break;
                        case date:
                            try { // prova ad eseguire il codice
                                value = type.bytesToObject(bytes);
                                value = LibDate.getShort((LocalDateTime)value);
                            } catch (Exception unErrore) { // intercetta l'errore
                                log.error(unErrore.toString());
                            }// fine del blocco try-catch
                            break;
                        default:
                            log.warn("Switch - caso non definito");
                            break;
                    } // end of switch statement
                    return value;
                });//end of lambda expressions
        colonna.setCaption("Value");
        float lar = grid.getWidth();
        grid.setWidth(lar + LibColumn.WIDTH_BYTE, Unit.PIXELS);
    }// end of method

}// end of class
