package it.algos.springvaadin.entities.versione;

import com.vaadin.ui.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gac on 01/06/17.
 * .
 */
@Repository
public interface IVersioneOld {

//    public void setTitolo(String value);

    interface VersioneViewListener {
        void buttonClick(char operation);
    }// end of inner interface

    //    public void addListener(VersioneViewListener listener);
    public void setList(List lista, List<String> colonneVisibili);

    public void setForm(Versione bean, List<String> campiVisibili);

    public void setComponent(Component comp);

}// end of interface
