package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.entity.role.RoleData;
import it.algos.springvaadin.entity.user.UserData;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 18-dic-2017
 * Time: 16:01
 * Classe di Libreria
 * Controlla e costruisce i dati iniziali di alcune collections
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class ADataService {


    /**
     * Inietta da Spring come 'singleton'
     */
    @Autowired
    public RoleData role;


    /**
     * Inietta da Spring come 'singleton'
     */
    @Autowired
    public UserData user;


    /**
     * Inizializzazione dei dati standard di alcune collections sul DB
     */
    public void inizia() {
        this.role.findOrCrea();
        this.user.findOrCrea();
    }// end of method



}// end of class
