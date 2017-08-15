package it.algos.springvaadin.entity.stato;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import it.algos.springvaadintest.bootstrap.StatoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_STA)
public class StatoPresenter extends AlgosPresenterImpl {

    @Autowired
    private StatoData statodata;


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     * Regola il modello-dati specifico
     */
    public StatoPresenter(@Qualifier(Cost.TAG_STA) AlgosView view, @Qualifier(Cost.TAG_STA) AlgosService service) {
        super(view, service);
        super.entityClass = Stato.class;
    }// end of Spring constructor


    @Override
    public void importa() {
        chiedeConfermaPrimaDiImportare();
    }// end of method

    /**
     * Presenta un dialogo di conferma prima della importazione effettiva
     */
    public void chiedeConfermaPrimaDiImportare() {
        String message;

        message = "Sei sicuro di voler ricreare tutti gli stati? ";
        ConfirmDialog dialog = new ConfirmDialog("Creazione", message, new ConfirmDialog.Listener() {
            @Override
            public void onClose(ConfirmDialog dialog, boolean confirmed) {
                if (confirmed) {
                    creazione();
                }// end of if cycle
            }// end of inner method
        });// end of anonymous inner class
        dialog.getCancelButton().setIcon(VaadinIcons.ARROW_BACKWARD);
        dialog.getConfirmButton().setIcon(VaadinIcons.CLOSE);
        dialog.setConfirmButtonText("Importa");

        if (LibParams.usaBottoniColorati()) {
            dialog.getCancelButton().addStyleName("buttonGreen");
            dialog.getConfirmButton().addStyleName("buttonRed");
        }// end of if cycle

        dialog.show(LibVaadin.getUI());
    }// end of method

    /**
     * Creazione dei dati
     */
    public void creazione() {
        statodata.creaStati();
        super.presentaLista();
    }// end of method

}// end of class
