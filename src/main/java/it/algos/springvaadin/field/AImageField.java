package it.algos.springvaadin.field;

import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottoneImage;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 29-ago-2017
 * Time: 08:11
 */
public class AImageField extends AField {

    private Layout placeholderImage = new HorizontalLayout();
    private BottoneImage buttonImage = new BottoneImage(null);


    public AImageField(AlgosPresenterImpl presenter) {
        super(presenter);
    }// end of constructor

    @Override
    public Component initContent() {


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

}// end of class

