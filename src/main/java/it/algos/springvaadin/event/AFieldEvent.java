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


    //--Obbligatorio specifica del tipo di evento
    //--Valore modificato oppure link verso un target diverso dal field
    private TypeField type;


    /**
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AFieldEvent(ApplicationListener source) {
        this(TypeField.valueChanged, source);
    }// end of constructor


    /**
     * @param type   Obbligatorio specifica del tipo di evento
     * @param source Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     */
    public AFieldEvent(TypeField type, ApplicationListener source) {
        this(type, source, (ApplicationListener) null, (AEntity) null, (AField) null);
    }// end of constructor


    /**
     * @param type       Obbligatorio specifica del tipo di evento
     * @param source     Obbligatorio (presenter, form, field, window, dialog,... ) che che ha generato l'evento
     * @param target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * @param entityBean Opzionale (entityBean) in elaborazione. Ha senso solo per alcuni eventi
     * @param field      Opzionale (field) che ha generato l'evento. Ha senso solo per alcuni eventi
     */
    public AFieldEvent(TypeField type, ApplicationListener source, ApplicationListener target, AEntity entityBean, AField field) {
        super(source, target, entityBean, field);
        this.type = type;
    }// end of constructor


    public TypeField getType() {
        return type;
    }// end of method


}// end of class
