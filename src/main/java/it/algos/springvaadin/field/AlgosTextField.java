package it.algos.springvaadin.field;

import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class AlgosTextField extends TextField {


    /**
     * Constructs an empty <code>TextField</code> with no caption.
     */
    public AlgosTextField() {
        super();
    }// end of constructor


    /**
     * Constructs an empty <code>TextField</code> with given caption.
     *
     * @param caption the caption <code>String</code> for the editor.
     */
    public AlgosTextField(String caption) {
        this(caption, "");
    }// end of constructor


    /**
     * Constructs a new <code>TextField</code> with the given caption and
     * initial text contents. The editor constructed this way will not be bound
     * to a Property unless
     * {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)}
     * is called to bind it.
     *
     * @param caption the caption <code>String</code> for the editor.
     * @param value   the initial text content of the editor.
     */
    public AlgosTextField(String caption, String value) {
        super(caption, value);
        init();
    }// end of constructor

    private void init() {
        initField();
        setWidth("180px");
    }// end of method

    public void initField() {
    }// end of method


}// end of field class
