package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottoneImage;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class AlgosImageField extends CustomField implements AlgosField {

    //--defaul temporary image
    private Image image;

    private String name;

    private BottoneImage buttonDialogo;

    private IndirizzoPresenter indirizzoPresenter;
    private AlgosPresenterImpl formPresenter;

    public AlgosImageField(BottoneImage buttonDialogo) {
        this.buttonDialogo = buttonDialogo;
    }// end of Spring constructor

    @Override
    protected Component initContent() {
        image = LibResource.getImage("DEU.png");
        image.setWidth("8em");
        image.setHeight("4em");
        return new HorizontalLayout(buttonDialogo, image);
    }// end of method

    @Override
    protected void doSetValue(Object o) {
        int a = 87;
    }

    @Override
    public Object getValue() {
        return null;
    }

//    public void setValue(Object obj) {
//        int a = 87;
//    }

    @Override
    public void doValue(AlgosEntity entityBean) {
        int a = 87;
        String name = "";
        if (entityBean != null) {
            name = entityBean.getId();
        }// end of if cycle
        name = name.toUpperCase() + ".png";

        Image image = LibResource.getImage(name);
        if (image != null) {
            image.setHeight("4em");
            image.setWidth("8em");
//            this.addComponent(image);
        }// end of if cycle

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void saveSon() {

    }

    @Override
    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }

    @Override
    public void setFormPresenter(AlgosPresenterImpl formPresenter) {
        buttonDialogo.setPresenter(formPresenter);
    }// end of method

}// end of class
