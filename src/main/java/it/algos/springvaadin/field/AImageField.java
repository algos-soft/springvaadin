package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottonType;
import it.algos.springvaadin.bottone.BottoneImage;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 29-ago-2017
 * Time: 08:11
 */
@SpringComponent
public class AImageField extends AField {

    private Layout placeholderImage = new HorizontalLayout();
    private BottoneImage buttonImage = new BottoneImage(null);
    private ApplicationEventPublisher applicationEventPublisher;

//    public AImageField() {
//        super(null);
//    }// end of constructor
//
//    public AImageField(AlgosPresenterImpl presenter) {
//        super(presenter);
//        this.setCaption("");
//    }// end of constructor

    @Override
    public Component initContent() {
        buttonImage.setApplicationEventPublisher(applicationEventPublisher);
        buttonImage.setType(BottonType.editLink);
//        buttonImage.setSource(formSource);
        buttonImage.inizia();

        if (placeholderImage != null && buttonImage != null) {
            placeholderImage.setWidth("8em");
            placeholderImage.setHeight("4em");
            addListener();
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

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }// end of method

    @Override
    public void setSource(ApplicationListener formSource) {
        buttonImage.setSource(formSource);
    }// end of method


}// end of class

