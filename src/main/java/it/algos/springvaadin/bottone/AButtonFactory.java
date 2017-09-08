package it.algos.springvaadin.bottone;

import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 15:50
 */
public interface AButtonFactory {

    public AButton crea(TypeButton type, ApplicationListener source);

}// end of interface
