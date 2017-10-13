package it.algos.springvaadin.exception;

import it.algos.springvaadin.entity.AEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 13-ott-2017
 * Time: 16:50
 */
public class NullCodeCompanyException extends CompanyException {

    private final static String MESSAGE_INI = "Esiste già un codeCompanyUnico con code=";
    private final static String MESSAGE_END = " per questa company";

    /**
     * Constructor for NullCompanyException.
     */
    public NullCodeCompanyException(String code) {
        super(MESSAGE_INI + code + MESSAGE_END);
    }// fine del costruttore

}// end of class
