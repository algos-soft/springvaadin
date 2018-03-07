package it.algos.springtemplates.enumeration;


/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 07-mar-2018
 * Time: 07:42
 */
public enum Progetto {

    vaadin("springvaadin", "springvaadin"),
    templates("springvaadin", "springtemplates"),
    test("springvaadin", "springvaadintest"),
    wam("springwam", "springwam"),
    bio("springbio", "springbio");


    private String nameProject;
    private String nameModulo;


    Progetto(String nameProject, String nameModulo) {
        this.setNameProject(nameProject);
        this.setNameModulo(nameModulo);
    }// fine del costruttore

    public String getNameProject() {
        return nameProject;
    }// end of method

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }// end of method

    public String getNameModulo() {
        return nameModulo;
    }// end of method

    public void setNameModulo(String nameModulo) {
        this.nameModulo = nameModulo;
    }// end of method

}// end of enumeration class
