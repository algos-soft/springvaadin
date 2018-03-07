package it.algos.springtemplates.enumeration;


import java.nio.charset.Charset;
import java.util.Map;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 06-mar-2018
 * Time: 19:46
 */
public enum Token {

    lowerProject("LOWERPROJECT"),
    lowerModulo("LOWERMODULO"),
    packageName("PACKAGE"),
    importCost("IMPORT_COST"),
    user("USER"),
    today("TODAY"),
    tag("TAG"),
    entity("ENTITY"),
    entitySuperclass("ENTITYSUPERCLASS");

    private String tokenTag;

    private static String DELIMITER = "@";

    Token(String tokenTag) {
        this.setTokenTag(tokenTag);
    }// fine del costruttore

    public String replace(String textReplacing, String currentTag) {
        return textReplacing.replaceAll(tokenTag, currentTag);
    }// end of method

    public String getTokenTag() {
        return tokenTag;
    }// end of method

    public void setTokenTag(String tokenTag) {
        this.tokenTag = tokenTag;
    }// end of method

    public static String replaceAll(String textReplacing, Map<Token, String> valueMap) {

        for (Token token : values()) {
            if (valueMap.containsKey(token)) {
                textReplacing = textReplacing.replaceAll(DELIMITER + token.tokenTag + DELIMITER, valueMap.get(token));
            }// end of if cycle
        }// end of for cycle

        return textReplacing;
    }// end of static method

}// end of enumeration class
