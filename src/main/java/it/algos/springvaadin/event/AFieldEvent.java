package it.algos.springvaadin.event;

import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 18:00
 * Eventi generati da un Field (campo) di un Form
 */
public class AFieldEvent extends AEvent {


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    private ApplicationListener target;


    //--Opzionale (entityBean) in elaborazione
    private AEntity entityBean;


    //--Opzionale (field) che ha generato l'evento
    private AField field;


    /**
     * @param source (obbligatorio) presenter che gestisce l'evento
     */
    public AFieldEvent(ApplicationListener source) {
        this(source, (ApplicationListener) null, (AEntity) null, (AField) null);
    }// end of constructor

    /**
     * @param source     Obbligatorio presenter che gestisce l'evento
     * @param target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione
     * @param field      Opzionale (field) che ha generato l'evento
     */
    public AFieldEvent(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField field) {
        super(source);
        this.target = target;
        this.entityBean = entityBean;
        this.field = field;
    }// end of constructor


    public ApplicationListener getTarget() {
        return target;
    }// end of method

    public AEntity getEntityBean() {
        return entityBean;
    }// end of method

    public AField getField() {
        return field;
    }// end of method

}// end of class