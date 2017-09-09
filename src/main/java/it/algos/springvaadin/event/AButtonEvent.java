package it.algos.springvaadin.event;


import com.vaadin.ui.Window;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 03/06/17.
 * Eventi generati dai bottoni
 * Link: http://www.baeldung.com/spring-events
 */
public class AButtonEvent extends AEvent {


    //--Obbligatorio specifica del tipo di evento
    private AButtonType type;


    //--opzionale
    private Window parentDialog;


    /**
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AButtonEvent(ApplicationListener source) {
        this(AButtonType.annulla, source);
    }// end of constructor


    /**
     * @param type   Obbligatorio specifica del tipo di evento
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AButtonEvent(AButtonType type, ApplicationListener source) {
        this(type, source, (ApplicationListener) null, (AEntity) null, (AField) null);
    }// end of constructor


    /**
     * @param type       Obbligatorio specifica del tipo di evento
     * @param source     Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     * @param target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     * @param field      Opzionale (field) che ha generato l'evento. Ha senso solo per alcuni eventi
     */
    public AButtonEvent(AButtonType type, ApplicationListener source, ApplicationListener target, AEntity entityBean, AField field) {
        super(source, target, entityBean, field);
        this.type = type;
    }// end of constructor


    public AButtonType getType() {
        return type;
    }// end of method


    public Window getParentDialog() {
        return parentDialog;
    }// end of method


    public void setParentDialog(Window parentDialog) {
        this.parentDialog = parentDialog;
    }// end of method


}// end of class