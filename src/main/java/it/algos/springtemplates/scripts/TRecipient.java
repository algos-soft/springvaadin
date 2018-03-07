package it.algos.springtemplates.scripts;

import com.vaadin.ui.Window;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 07-mar-2018
 * Time: 08:07
 */
public interface TRecipient {
    public void gotInput(String inputText, Window dialogWindow);
}// end of interface
