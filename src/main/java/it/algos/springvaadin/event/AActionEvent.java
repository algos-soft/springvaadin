package it.algos.springvaadin.event;


import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 04/06/17.
 * Eventi generati da una azione (nella Grid, ad esempio)
 * Link: http://www.baeldung.com/spring-events
 */
public class AActionEvent extends AEvent {


    //--Obbligatorio specifica del tipo di evento
    private TypeAction type;


    /**
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AActionEvent(ApplicationListener source) {
        this(TypeAction.click, source);
    }// end of constructor


    /**
     * @param type   Obbligatorio specifica del tipo di evento
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AActionEvent(TypeAction type, ApplicationListener source) {
        this(type, source, (ApplicationListener) null, (AEntity) null);
    }// end of constructor


    /**
     * @param type       Obbligatorio specifica del tipo di evento
     * @param source     Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     * @param target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     */
    public AActionEvent(TypeAction type, ApplicationListener source, ApplicationListener target, AEntity entityBean) {
        super(type,source, target, entityBean);
    }// end of constructor


    public TypeAction getType() {
        return type;
    }// end of method


}// end of class