package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottonType;
import it.algos.springvaadin.bottone.BottoneImage;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 29-ago-2017
 * Time: 08:11
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_IMAGE)
public class AImageField extends AField {

    private Layout placeholderImage = new HorizontalLayout();

    @Autowired
    private BottoneImage buttonImage;


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
    }// end of method


    public void setWidth(String width) {
        if (placeholderImage != null) {
            placeholderImage.setWidth(width);
            placeholderImage.setHeight("4em");
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        if (placeholderImage != null && buttonImage != null) {
            return new HorizontalLayout(buttonImage, placeholderImage);
        } else {
            return null;
        }// end of if/else cycle
    }// end of method

    @Override
    public byte[] getValue() {
//        String name = entityBean.getId().toUpperCase() + ".png";
//        byte[] imgByte = LibResource.getImgBytes(name);
//
//        return imgByte;
        return null;
    }// end of method

    @Override
    protected void doSetValue(Object value) {
        byte[] imgByte = null;
        Image image = null;
        placeholderImage.removeAllComponents();

        if (value != null && value instanceof byte[]) {
            imgByte = (byte[]) value;
        }// end of if cycle

        if (imgByte != null && imgByte.length > 0) {
            image = LibResource.getImage(imgByte);
        }// end of if cycle

        if (image != null) {
            image.setWidth("8em");
            image.setHeight("4em");
            placeholderImage.addComponent(image);
        }// end of if cycle

    }// end of method


    @Override
    public void setSource(AlgosPresenterImpl source) {
        if (buttonImage != null) {
            if (source!=null) {
                buttonImage.setSource(source);
            }// end of if cycle

            if (entityBean!=null) {
                buttonImage.setEntityBean(entityBean);
            }// end of if cycle
        }// end of if cycle
    }// end of method


}// end of class

