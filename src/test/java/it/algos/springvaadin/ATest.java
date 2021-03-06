package it.algos.springvaadin;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.entity.user.User;
import it.algos.springvaadin.enumeration.EACompanyRequired;
import it.algos.springvaadin.enumeration.EAFieldAccessibility;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 25-dic-2017
 * Time: 09:54
 */
@Slf4j
public class ATest {

    protected final static String FIELD_NAME_KEY = "id";
    protected final static String FIELD_NAME_ORDINE = "ordine";
    protected final static String FIELD_NAME_CODE = "code";
    protected final static String FIELD_NAME_COMPANY = "company";
    protected final static String FIELD_NAME_NICKNAME = "nickname";
    protected final static String FIELD_NAME_PASSWORD = "password";
    protected final static String FIELD_NAME_ROLE = "role";
    protected final static String FIELD_NAME_ENABLED = "enabled";
    protected final static String FIELD_NAME_NOTE = "note";
    protected final static String FIELD_NAME_CREAZIONE = "creazione";
    protected final static String FIELD_NAME_MODIFICA = "modifica";

    protected final static String NAME_ORDINE = "ordine";
    protected final static String NAME_CODE = "code";
    protected final static String NAME_ROLE = "role";
    protected final static String HEADER_ORDINE = "#";
    protected final static String HEADER_CODE = "Code";
    protected static Field FIELD_ORDINE;
    protected static Field FIELD_CODE;
    protected static Field FIELD_ROLE;
    protected static Class<? extends IAView> ROLE_VIEW_CLASS_LIST = RoleList.class;
    protected static Class<? extends IAView> ROLE_VIEW_CLASS_FORM = RoleForm.class;
    protected static Class<? extends AEntity> ROLE_ENTITY_CLASS = Role.class;
    protected static Class<? extends AEntity> USER_ENTITY_CLASS = User.class;

    protected Field reflectionJavaField;
    protected String previsto = "";
    protected String ottenuto = "";
    protected boolean previstoBooleano;
    protected boolean ottenutoBooleano;
    protected int previstoIntero = 0;
    protected int ottenutoIntero = 0;
    protected List<String> previstoList;
    protected List<String> ottenutoList;
    protected List<Field> previstoFieldList;
    protected List<Field> ottenutoFieldList;
    protected EAFieldType previstoType;
    protected EAFieldType ottenutoType;
    protected EAFieldAccessibility previstaAccessibilità;
    protected EAFieldAccessibility ottenutaAccessibilità;
    protected EACompanyRequired previstoCompany;
    protected EACompanyRequired ottenutoCompany;

}// end of class
