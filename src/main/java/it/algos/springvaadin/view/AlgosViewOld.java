package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneForm;
import it.algos.springvaadin.entity.versione.VersioneList;
import it.algos.springvaadin.entity.versione.VersionePresenter;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by gac on 01/06/17.
 * Le sottoclassi concrete di questa classe astratta devono utilizzare @SpringView(name = xxx.VIEW_NAME)
 * SpringNavigator 'legge' tutte le classi che usano @SpringView e mantiene uno SpringViewProvider,
 * utilizzato da getNavigator().navigateTo(address)
 * <p>
 * Nelle sottoclassi vanno usati (nell'ordine)
 * -@SpringComponent
 * -@UIScope oppure ViewScope
 * -@SpringView(name = xxxView.VIEW_NAME)
 * <p>
 * Se si usa @UIScope, the same instance will be used by all views of the UI
 * Se si usa @ViewScope, a new instance will be created for every view instance created
 * <p>
 * Il metodo postConstructor viene chiamato una volta sola
 * Il metodo enter(viewChangeEvent) viene chiamato ogni volta che si richiama la view
 */
public abstract class AlgosViewOld extends VerticalLayout implements View {


    //--il presenter specifico viene iniettato nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosPresenter presenter;


    //--la lista specifica viene iniettata nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosList list;


    //--il form specifico viene iniettato nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosForm form;


    public AlgosViewOld() {
    }// fine del metodo costruttore

//    /**
//     * Presenter specifico, iniettato in questa classe
//     * Lista specifica, iniettata in questa classe
//     * Form specifico, iniettato in questa classe
//     * Vengono iniettati qui per avere le classi specifiche.
//     * Nella superclasse vengono gestite le properties generiche.
//     */
//    @Autowired//@todo funziona anche levando @Autowired :-) Non capisco
//    public AlgosView(AlgosPresenter presenter) {
//        this.presenter = presenter;
//        this.list = list;
//        this.form = form;
//    }// fine del metodo costruttore Autowired

    /**
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve  quindi iniettarsi il riferimento al gestore principale (Presenter)
     */
    @Autowired//@todo funziona anche levando @Autowired :-) Non capisco
    public AlgosViewOld(AlgosPresenter presenter) {
        this.presenter = presenter;

        //--riferimento incrociato
//        presenter.setView(this);
    }// fine del metodo costruttore Autowired

    /**
     * Metodo invocato subito DOPO il costruttore
     * <p>
     * Performing the initialization in a constructor is not suggested
     * as the state of the UI is not properly set up when the constructor is invoked.
     * <p>
     * Ci possono essere diversi metodi con @PostConstruct e firme diverse e funzionano tutti,
     * ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    protected void inizia() {
        this.setMargin(false);
        this.setSpacing(false);

        if (AlgosApp.USE_DEBUG) {
            this.addStyleName("rosso");
        }// fine del blocco if
    }// end of method


//    /**
//     * Metodo invocato (da SpringBoot) ogni volta che la view diventa attiva
//     * Passa il controllo alla classe AlgosPresenter che gestisce la business logic
//     * <p>
//     * Il metodo postConstructor viene chiamato una volta sola dallo SpringNavigator
//     * Il metodo enter(viewChangeEvent) viene chiamato ogni volta che si richiama la view dallo SpringNavigator
//     */
//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        presenter.enter();
//    }// end of method


    /**
     * Metodo invocato da AlgosPresenter
     * <p>
     * Regola la lista (che usa una Grid)
     * Visualizza la lista
     *
     * @param items           da visualizzare nella Grid
     * @param colonneVisibili e ordinate della lista
     */
    public void setList(Class<? extends AlgosModel> clazz, List items, List<String> colonneVisibili) {
        removeAllComponents();
        list.inizia(clazz, items, colonneVisibili);
        addComponent(list);
    }// end of method


    /**
     * Metodo invocato da AlgosPresenter
     * <p>
     * Regola il form
     * Visualizza il form
     *
     * @param campiVisibili e ordinati del form
     */
    public void setForm(Class<? extends AlgosModel> entityBean, AlgosService service, List<String> campiVisibili) {
        removeAllComponents();
        form.iniziaCreate(service, campiVisibili);
        addComponent(form);
    }// end of method


    /**
     * Metodo invocato da AlgosPresenter
     * <p>
     * Regola il form
     * Visualizza il form
     *
     * @param campiVisibili e ordinati del form
     */
    public void setForm(AlgosModel entityBean, AlgosService service, List<String> campiVisibili) {
        removeAllComponents();
        form.iniziaEdit(entityBean, service, campiVisibili);
        addComponent(form);
    }// end of method


    /**
     * /**
     * Gestito da xxxPresenter
     * Mostra una scheda
     */
    public void setForm(Versione bean, List<String> campiVisibili) {
//        removeAllComponents();
//
//        VerticalLayout layout = new VerticalLayout();
//
//        //@todo da realizzare con Property e non String
////        for (String label : campiVisibili) {
////            layout.addComponent(new TextField(label, bean.getTitolo()));
////        }// end of for cycle
//
//        if (bean != null) {
//            fieldTitolo = new TextField("Titolo", bean.getTitolo());
//            fieldDescrizione = new TextField("Descrizione", bean.getDescrizione());
//            fieldTime = new AlgosDateField("Data", LibTime.asLocalDate(bean.getModifica()));
//            fieldTime.setDateFormat("d-MMM-yyyy");
//        } else {
//            fieldTitolo = new TextField("Titolo");
//            fieldDescrizione = new TextField("Descrizione");
//        }// end of if/else cycle
//
//        if (bean != null) {
//            addComponent(fieldTitolo);
//            addComponent(fieldDescrizione);
//            addComponent(fieldTime);
//        } else {
//            addComponent(fieldTitolo);
//            addComponent(fieldDescrizione);
//        }// end of if/else cycle
//
////        layout.addComponent(new TextField("Ordine", String.valueOf(bean.getOrdine())));
//        addComponent(layout);
//        addComponent(formToolbar);
    }// end of method


//    /**
//     * Sincronizza alcuni bottoni della lista
//     * Rimana al metodo della Lista
//     */
//    public void sincBottoniLista(boolean enabled) {
//        //@todo da sviluppare nella superclasse
////        if (lista!=null) {
////            lista.getToolbar().buttonEdit.setEnabled(enabled);
////            lista.getToolbar().buttonDelete.setEnabled(enabled);
////        }
//        //        listToolbar.buttonEdit.setEnabled(enabled);
////        listToolbar.buttonDelete.setEnabled(enabled);
////        }// end of if cycle
//        int a = 87;
//    }// end of method


    public AlgosPresenter getPresenter() {
        return presenter;
    }// end of method

    /**
     * Selezione della griglia
     * Rimana al metodo della Lista
     */
    public int numRigheSelezionate() {
        return list.numRigheSelezionate();
    }// end of method

    /**
     * Selezione della griglia
     * Rimana al metodo della Lista
     */
    public boolean unaRigaSelezionata() {
        return list.unaRigaSelezionata();
    }// end of method


    public void setComponent(Component comp) {
        addComponent(comp);
    }


    public AlgosList getList() {
        return list;
    }

    public AlgosForm getForm() {
        return form;
    }

    public void setDisplay(double value) {

    }

}// end of class
