package it.algos.springvaadin.field;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottoneImage;
import it.algos.springvaadin.dialog.Edit2Dialog;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.bson.BSONObject;
import org.bson.BasicBSONDecoder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

import java.util.Base64;

@SpringComponent
@Scope("prototype")
public class AlgosImageField extends CustomField implements AlgosField {

    //--defaul temporary image
    private Image image;

    private Layout placeholderImage = new HorizontalLayout();
    private String name;

    private BottoneImage buttonImage;

    private IndirizzoPresenter indirizzoPresenter;
    private AlgosPresenterImpl formPresenter;

    /**
     */
    protected AlgosEntity entityBean;


    public AlgosImageField(BottoneImage buttonImage) {
        this.buttonImage = buttonImage;
    }// end of Spring constructor

    @Override
    protected Component initContent() {
//        image = LibResource.getImage("DEU.png");
        placeholderImage.setWidth("8em");
        placeholderImage.setHeight("4em");
        return new HorizontalLayout(buttonImage, placeholderImage);
    }// end of method

    @Override
    protected void doSetValue(Object o) {
        int a = 87;
    }// end of method

    @Override
    public Object getValue() {
        String name = entityBean.getId().toUpperCase() + ".png";
        byte[] imgByte = LibResource.getImgBytes(name);

        return imgByte;
    }// end of method

    @Override
    public void setValue(Object obj) {
//        String binaryData=(String)obj;
//        byte[] imageByte = binaryData.getBytes();
//        Image imageTmp2 = LibResource.getImage(imageByte);

        byte[] imageByte = null;
        if (obj instanceof byte[]) {
            imageByte = (byte[]) obj;
        } else {
            return;
        }// end of if/else cycle


        int a = 87;
        Image imageTmp=null;
        if (imageByte != null && imageByte.length > 24) {
             imageTmp = LibResource.getImage(imageByte);
        }// end of if cycle


//        String name = "";
//        name = ((String) obj).toUpperCase() + ".png";
//        Image imageTmp = LibResource.getImage(name);


        if (imageTmp != null) {
            image = imageTmp;
            image.setWidth("8em");
            image.setHeight("4em");
        } else {
            image = null;
        }// end of if/else cycle

        placeholderImage.removeAllComponents();
        if (image != null) {
            placeholderImage.addComponent(image);
        }// end of if cycle

    }// end of method

    @Override
    public void doValue(AlgosEntity entityBean) {
//        String name = "";
//        if (entityBean != null) {
//            name = entityBean.getId();
//        }// end of if cycle
//        name = name.toUpperCase() + ".png";
//
//        Image imageTmp = LibResource.getImage(name);
//        if (imageTmp != null) {
//            image=imageTmp;
//        }// end of if cycle
//
        int a = 87;
    }// end of method

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
    public void setSource(ApplicationListener<AlgosSpringEvent> formSource) {
        buttonImage.setSource(formSource);
        buttonImage.setEntityBean(entityBean);
    }// end of method

    public void setEntityBean(AlgosEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method

}// end of class
