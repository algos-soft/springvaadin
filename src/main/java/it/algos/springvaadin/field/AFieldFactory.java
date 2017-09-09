package it.algos.springvaadin.field;

import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 30-ago-2017
 * Time: 11:14
 */
public interface AFieldFactory {

    public AField crea(AFType type, String publicFieldName, AlgosPresenterImpl presenter,Object[] items);

}// end of interface
