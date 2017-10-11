package it.algos.springvaadin.form;

import com.vaadin.data.ValidationResult;
import com.vaadin.ui.Component;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.entity.AEntity;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by gac on 10/07/17
 * Presenta i dati di una singola entityBean, sotto forma di un Form
 * Conosce solo la entityBean ed i fields che gli vengono passati dal presenter nel metodo restart
 * Responsabile di presentare i dati e lanciare gli eventi necessari
 * Si inietta (nella classe concreta) la FormToolbar/LinkToolbar con i bottoni che generano gli eventi
 */
public interface AlgosForm {

    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param source                presenter di riferimento da cui vengono generati gli eventi
     * @param entityBean            istanza da elaborare
     * @param reflectFields         campi del form da visualizzare
     * @param usaSeparateFormDialog barra alternativa di bottoni per gestire il ritorno ad altro modulo
     */
    public void restart(ApplicationListener source, AEntity entityBean, List<Field> reflectFields, boolean usaSeparateFormDialog);

    /**
     * Creazione del form di un altro modulo/collezione
     * Solo finestra popup
     *
     * @param source             presenter di riferimento per i componenti da cui vengono generati gli eventi
     * @param sourceField        di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     * @param entityBean         istanza da elaborare
     * @param reflectFields      campi del form da visualizzare
     * @param usaBottoneRegistra utilizzo del ButtonRegistra, che registra subito
     *                           oppure ButtonAccetta, che demanda la registrazione alla scheda chiamante
     */
    public void restartLink(ApplicationListener source, AField sourceField, AEntity entityBean, List<Field> reflectFields, AButtonType type);

    /**
     * Esegue il 'rollback' del Form
     * Revert (ripristina) button pressed in form
     * Usa la entityBean già presente nel form, ripristinando i valori iniziali
     * Rimane nel form SENZA registrare
     */
    public void revert();


    /**
     * Checks all current validation errors
     * Se ce ne sono, rimane nel form SENZA registrare
     *
     * @return ci sono errori in almeno una delle property della entity
     */
    public boolean entityHasError();


    /**
     * Checks if the entity has no current validation errors at all
     * Se la entity è OK, può essere registrata
     *
     * @return tutte le property della entity sono valide
     */
    public boolean entityIsOk();


    /**
     * All fields errors
     *
     * @return errors
     */
    public List<ValidationResult> getEntityErrors();


    /**
     * Trasferisce i valori dai campi dell'annotation alla entityBean
     * Esegue la (eventuale) validazione dei dati
     * Esegue la (eventuale) trasformazione dei dati
     *
     * @return la entityBean del Form
     */
    public AEntity commit();


    /**
     * Chiude la (eventuale) finestra utilizzata nel Form
     */
    public void closeWindow();


    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent();


    /**
     * Restituisce la entity utilizzata
     *
     * @return la entityBean del Form
     */
    public AEntity getEntityBean();


    /**
     * Abilita o disabilita lo specifico bottone della Toolbar
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param status abilitare o disabilitare
     */
    public void enableButton(AButtonType type, boolean status);


    /**
     * Inserisce nei bottoni Registra o Accetta il Field che va notificato
     *
     * @param parentField che ha richiesto questo form
     */
    public void setParentField(AField parentField);


    /**
     * Registra eventuali dipendenze di un field del Form
     */
    public void saveSons();

}// end of interface
