package it.algos.springvaadin.event;

import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 18:00
 * Eventi specifici del Framework
 * Link: http://www.baeldung.com/spring-events
 */
public abstract class AEvent extends ApplicationEvent {


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    private ApplicationListener target;


    //--Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
    private AEntity entityBean;


    //--Opzionale (field) che ha generato l'evento. Ha senso solo per alcuni eventi
    private AField field;


    /**
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AEvent(ApplicationListener source) {
        this(source, (ApplicationListener) null, (AEntity) null, (AField) null);
    }// end of constructor


    /**
     * @param source     Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     * @param target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     * @param field      Opzionale (field) che ha generato l'evento. Ha senso solo per alcuni eventi
     */
    public AEvent(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField field) {
        super(source);
        this.target = target;
        this.entityBean = entityBean;
        this.field = field;
    }// end of constructor


    public Object getType() {
        return null;
    }// end of method


    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    @Override
    public ApplicationListener getSource() {
        return (ApplicationListener) super.getSource();
    }// end of method


    public ApplicationListener getTarget() {
        return target;
    }// end of method


    public AEntity getEntityBean() {
        return entityBean;
    }// end of method


    public AField getField() {
        return field;
    }// end of method

}// end of abstract class