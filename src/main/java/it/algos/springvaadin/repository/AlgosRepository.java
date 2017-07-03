package it.algos.springvaadin.repository;

import com.vaadin.spring.annotation.SpringComponent;

import java.io.Serializable;

/**
 * Created by gac on 02/07/17
 * <p>
 * Interfaccia generica di collegamento tra i Service ed il DataBase
 * La logica di selezione, controllo e manipolazione dei dati risiede nei Service
 * Nelle Repository c'è l'implementazione specifica di un collegamento al DB
 * Questa interfaccia stabilisce il 'contratto' tra i Service ed il DB
 * Riporta tutti i metodi sicuramente presenti (con la stessa firma) nelle varie implementazioni di collegamento
 * Le interfaccie (con le loro implementazioni) possono essere di vari tipi:
 * - Spring Boot JDBC usa JdbcTemplate (basso livello, architettura più semplice, uso più macchinoso)
 * - Spring Boot JPA usa le Repository (alto livello, architettura automatica molto più complessa, uso facile ed elegante)
 * Possono essere scambiate facilmente lasciando inalterati i Service
 *
 * @see https://spring.io/guides/gs/relational-data-access/
 * @see https://spring.io/guides/gs/accessing-data-jpa/
 * @see https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa
 * @see http://www.concretepage.com/spring/spring-auto-detection-with-component-service-repository-and-controller-stereotype-annotation-example-using-componentscan-and-component-scan
 * @see http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html
 * @see https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-one-configuration/
 * @see http://docs.spring.io/spring-data/jpa/docs/1.8.x/reference/html/#repositories.create-instances
 * @see https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/
 */
@SpringComponent
public interface AlgosRepository {

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param serializable must not be {@literal null}.
     *
     * @return true if an entity with the given id exists, {@literal false} otherwise
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public boolean exists(Serializable serializable);


    /**
     * Returns the number of entity available.
     *
     * @return the number of entity
     */
    public long count();


    /**
     * Returns all instances of the type.
     *
     * @return all entity
     */
    public Iterable findAll();


    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param iterable
     *
     * @return
     */
    public Iterable findAll(Iterable iterable);


    /**
     * Retrieves an entity by its id.
     *
     * @param serializable must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal null} if none found
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public Object findOne(Serializable serializable);


    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     *
     * @return the saved entity
     */
    public Object save(Object entity);


    /**
     * Saves all given entity.
     *
     * @param entities
     *
     * @return the saved entity
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public Iterable save(Iterable entities);


    /**
     * Deletes the entity with the given id.
     *
     * @param serializable must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    public void delete(Serializable serializable);


    /**
     * Deletes a given entity.
     *
     * @param entity
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public void delete(Object entity);


    /**
     * Deletes the given entity.
     *
     * @param entities
     *
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    public void delete(Iterable entities);


    /**
     * Deletes all entity managed by the repository.
     */
    public void deleteAll();


}// end of interface
