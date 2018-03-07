package it.algos.springtemplates.enumeration;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 06-mar-2018
 * Time: 09:38
 */
public enum Componente {

    entity("Entity", "EntityCompany", ""),
    list("List", "List", "List"),
    form("Form", "Form", "Form"),
    presenter("Presenter", "Presenter", "Presenter"),
    repository("Repository", "RepositoryCompany", "Repository"),
    service("Service", "ServiceCompany", "Service");

    private String source;
    private String sourceCompany;
    private String suffix;


    Componente(String source, String sourceCompany, String suffix) {
        this.setSource(source);
        this.setSourceCompany(sourceCompany);
        this.setSuffix(suffix);
    }// fine del costruttore


    public String getSourceName() {
        String txt = ".txt";
        return getSource() + txt;
    }// end of method


    public String getSourceNameCompany() {
        String txt = ".txt";
        return getSourceCompany() + txt;
    }// end of method


    public String getJavaClassName() {
        String java = ".java";
        return getSuffix() + java;
    }// end of method


    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }// end of method

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceCompany() {
        return sourceCompany;
    }

    public void setSourceCompany(String sourceCompany) {
        this.sourceCompany = sourceCompany;
    }

    public String getSuffix() {
        return suffix;
    }
}// end of enumeration
