package it.algos.springvaadin.event;


import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.field.AField;

/**
 * Created by gac on 03/06/17.
 * Eventi generati dai bottoni
 * Link: http://www.baeldung.com/spring-events
 */
public class AButtonEvent extends AEvent {


    /**
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che ha generato l'evento
     */
    public AButtonEvent(IAListener source) {
        this(EATypeButton.annulla, source, source);
    }// end of constructor


    /**
     * @param type   Obbligatorio specifica del tipo di evento
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che ha generato l'evento
     * @param target Obbligatorio (presenter, form, field, window, dialog,... ) a cui indirizzare l'evento
     */
    public AButtonEvent(EATypeButton type, IAListener source, IAListener target) {
        this(type, source, target, (AEntity) null);
    }// end of constructor


    /**
     * @param type       Obbligatorio specifica del tipo di evento
     * @param source     Obbligatorio (presenter, form, field, window, dialog,... ) che ha generato l'evento
     * @param target     Obbligatorio (presenter, form, field, window, dialog,... ) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     */
    public AButtonEvent(EATypeButton type, IAListener source, IAListener target, AEntity entityBean) {
        this(type, source, target, entityBean, (AField) null);
    }// end of constructor


    /**
     * @param type        Obbligatorio specifica del tipo di evento
     * @param source      Obbligatorio (presenter, form, field, window, dialog,... ) che ha generato l'evento
     * @param target      Obbligatorio (presenter, form, field, window, dialog,... ) a cui indirizzare l'evento
     * @param entityBean  Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     * @param sourceField Opzionale (field) in elaborazione. Ha senso solo per alcuni eventi
     */
    public AButtonEvent(EATypeButton type, IAListener source, IAListener target, AEntity entityBean, AField sourceField) {
        super(type, source, target, entityBean, sourceField);
    }// end of constructor


    public EATypeButton getType() {
        return (EATypeButton) type;
    }// end of method


}// end of class