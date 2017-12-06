package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:53
 */
/**
 * Created by gac on 09/07/17
 * Presenta i dati di una lista di entities, usando una Grid
 * Conosce solo la entityClass, gli items e le columns che gli vengono passati dal presenter nel metodo restart
 * Responsabile di presentare i dati e lanciare gli eventi necessari
 * Si inietta (nella classe concreta) la AlgosGrid che contiene le azioni che generano gli eventi
 * Si inietta (nella classe concreta) la ListToolbar che contiene i bottoni che generano gli eventi
 * I bottoni e le azioni sono ''prototype'', cioè ne viene generato uno per ogni xxxPresenter -> xxxView -> xxxList
 * La AlgosGrid è ''prototype'', cioè ne viene generata una diversa per ogni xxxPresenter -> xxxView -> xxxList
 */
public interface IAlgosList {


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param presenter   di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    public void restart(AlgosPresenterImpl presenter, Class<? extends AEntity> entityClazz, List<Field> columns, List items);


//    /**
//     * Righe selezionate nella Grid
//     *
//     * @return numero di righe selezionate
//     */
//    public int numRigheSelezionate();
//
//
//    /**
//     * Una riga selezionata nella grid
//     *
//     * @return true se è selezionata una ed una sola riga nella Grid
//     */
//    public boolean isUnaRigaSelezionata();
//
//
//    /**
//     * Abilita o disabilita lo specifico bottone della Toolbar
//     *
//     * @param type   del bottone, secondo la Enumeration AButtonType
//     * @param status abilitare o disabilitare
//     */
//    public void enableButton(AButtonType type, boolean status);
//
//
//
//    /**
//     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
//     * Lista nulla, se nessuna riga è selezionata
//     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
//     * Lista di uno o più elementi, se è Grid.SelectionMode.MULTI
//     *
//     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
//     */
//    public List<AEntity> getEntityBeans();
//
//
//    /**
//     * Elemento selezionato nella Grid
//     *
//     * @return entityBean
//     */
//    public AEntity getEntityBean();


}// end of interface
