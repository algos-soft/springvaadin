Vaadin and SpringBoot
Architettura MVP (Model, View, Presenter)
Applicazione finale in JAR, con Tomcat embedded.
Alternativamente si può avere l'uscita in WAR. Modifiche possibili, non ancora provate
Usa Vaadin 8.0.5 e IntelliJ Idea 2017.1.3 e SpringBoot 1.5.3

New Project: soluzione A
1) Selezionare maven; click Next (non selezionare Create from archetype)
    Group: it.algos.nomeBaseProgetto (minuscolo)
    Artifact: nomeBaseProgetto (minuscolo)
2) Copiare da springvaadin lo script templates.scripts.newProject.xml ed inserirlo a livello base del nuovo progetto
3) Aprire il file da AntBuild, inserire il nomeBaseProgetto adeguato (seconda riga, iniziale maiuscola);
    lanciare 'creaProgettoECancellaScript';
    dopo il lancio il file si auto-cancella
4) Eseguire Maven -> Import Changes
5) Aprire Project Structure -> Modules e selezionare come Excluded la cartella 'scripts'
6) Creare in MySql un database col nome (minuscolo) del nuovo nomeBaseProgetto.
    In alternativa, modificare il nome del DB in src.main.resources.application.properties


New Project: soluzione B
1) Selezionare Spring Initializr; cliccare 'Next' lasciando selezionato il radioBottone 'Default'
    Group: it.algos.nomeBaseProgetto (minuscolo)
    Artifact: nomeBaseProgetto (minuscolo)
2) Usa le dependencies:
    - Core: Cache, DevTools
    - Web: Web, Web Services, Vaadin
    - SQL: JPA, MySQL, H2
    - I/O: Mail
3) Copiare da springvaadin lo script templates.scripts.newProject.xml a livello base del nuovo progetto
4) Aprire il file da AntBuild, inserire il nomeBaseProgetto adeguato (seconda riga, iniziale maiuscola);
    lanciare 'creaProgettoSenzaPomECancellaScript';
    dopo il lancio il file si auto-cancella
5) Modificare la Main Class in Edit Configurations... di Idea, indicando quella contenuta nella directory 'application'
6) Aprire Project Structure -> Modules e selezionare come Excluded la cartella 'scripts'
7) Creare in MySql un database col nome (minuscolo) del nuovo nomeBaseProgetto.
    In alternativa, modificare il nome del DB in src.main.resources.application.properties

Vaad8springApplication contiene il metodo 'main' che è il punto di ingresso dell'applicazione Java
In fase di sviluppo si possono avere diverse configurazioni, ognuna delle quali punta un ''main' diverso
Nel JAR finale (runtime) si può avere una sola classe col metodo 'main'.
Nel WAR finale (runtime) occorre (credo) inserire dei servlet di context diversi
Vaad8springApplication ha le Annotation:
- @SpringBootApplication
- @EnableAutoConfiguration
- @ComponentScan("it.algos.springvaadin")
    - Senza questa non vede le Annotation tipo @SpringView delle classi che sono
      in una directory diversa da quella che contiene Vaad8springApplication
Vaad8springApplication non fa praticamente niente se non avere le Annotation citate

SpringVaadinUI è la classe di UI che 'parte' all'inizio dell'applicazione
SpringVaadinUI extends UI e implements ViewDisplay
SpringVaadinUI è identificata dall'Annotation @SpringUI()
- @SpringUI() deve essere utilizzata in una sola classe dell'applicazione che estenda UI

I DataSource setting vanno regolati in resources.application.properties

La porta del Tomcat embedded è di default 8080, ma si può regolare in diversi modi:
- In VM options della configurazione -> -Dserver.port=8090

La struttura generata dal pom di Maven ha una sua tipicità
Vedi: https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
Occorre una directory (a livello base nella cartella java) ‘webapp’
    dove metterci una directory VAADIN (maiuscola) con dentro i ‘themes’
ATTENZIONE: Vaadin/SpringBoot/Maven legge automaticamente come baseDir quella dove c’è la cartella VAADIN.
    Le immagini ed i dati demo, vanno messi qui.
Remember to also map the path “/VAADIN/” to the Vaadin servlet for serving static resources

Un Navigator esplicito non serve. SpringBoot usa SpringNavigator che 'legge' tutte le classi con @SpringView
    e mantiene uno SpringViewProvider utilizzato da getNavigator().navigateTo(address)
Le varie view sono automaticamente disponibili nel codice del programma per navigare da una all'altra
Per consentire all'utente lo spostamente occorre comunque creare una MenuBar coi nomi delle View
    (letti da una property statica della classe) e creare il comando getNavigator().navigateTo(viewName);

Per ogni classe che si vuole iniettare, occorre usare @SpringComponent nella classe e @Autowired per la property

Le classi di tipo 'bean' devono usare @Component

In SpringBoot, Pay attention to the order of annotations

Se si mappa con @Autowired una property che richiama un Interfaccia ed esiste una (ed una sola)
implementazione di quella interfaccia, la classe concreta viene creata.
Se c'è più di un'implementazione dell'interfaccia richiesta, si ottiene: 'expected single matching bean'

Gli eventi vengono gestiti da @EventListener (che mi sembra uno sviluppo di @Observes)

    The model is an interface defining the data to be displayed or otherwise acted upon in the user interface.
    The view is a passive interface that displays data (the model) and routes user commands (events) to the presenter to act upon that data.
    The presenter acts upon the model and the view. It retrieves data from repositories (the model), and formats it for display in the view.

    When a user triggers an event method of the view, it does nothing
    but invoke a method of the presenter that has no parameters and no return value.
    The presenter then retrieves data from the view through methods defined by the view interface.
    Finally, the presenter operates on the model and updates the view with the results of the operation

Spring Data is not compatible with the architecture of JPAContainer.

Leggi: https://www.voxxed.com/blog/2017/03/21-improvements-vaadin-8/

Costanti dell'applicazione
1) Costanti globali dell'applicazione. Non modificabili (final static).
    . PRIMA della chiamata del browser
    . Costanti per leggere/scrivere sempre uguale nelle mappe, negli attributi, nei cookies, nelle property
    . Stanno nella classe Cost.
2) Costanti globali dell'applicazione. Non modificabili (final static).
    . Regolate in fase di costruzione del framework.
    . Application is coming up and is ready to server requests
    . PRIMA della chiamata del browser
    . Stanno in AlgosApp.
3) Costanti globali dell'applicazione. Business logic. Modificabili (static).
    . Application received the server requests
    . DOPO la chiamata del browser
    . Fixed in AlgosSpringBoot.afterPropertiesSet()
    . Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
4) Costanti globali dell'interfaccia. Modificabili (static).
     * Regolate nel metodo @PostConstruct di AlgosUI.inizia()
     * Pussono essere modificate in @PostConstruct.inizia() della sottoclasse concreta xxxUI

==Spring Data==
Spring Boot JDBC usa JdbcTemplate (basso livello, architettura più semplice, uso più macchinoso)
Spring Boot JPA usa le Repository (alto livello, architettura automatica molto più complessa, uso banale ed elegante)
Per il momento uso JDBC per passare poi a JPA.
Metto nei Service solo la definizione dei dati e la logica di manipolazione
Separo nelle Repository l'implementazione JDBC, così per passare a JPA non devo cambiare nulla dei Service

==Scope==
 * – We have some type of scope:
 * + singleton: only one instance is created (default scope)
 * + prototype: new instance is created everytime prototype bean is referenced.
 * + request: one instance for a single HTTP request.
 * + session: one instance for an HTTP Session
 * + globalSession: one instance for a global HTTP Session. Typically only valid when used in a Portlet context.
 * + application: Scopes a single bean definition to the lifecycle of a ServletContext
 * + (Only valid in the context of a web-aware Spring ApplicationContext).
// @see http://javasampleapproach.com/spring-framework/spring-bean-scope-using-annotation-singleton-prototype-request-session-global-session-application