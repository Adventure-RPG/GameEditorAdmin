/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.mycompany.myapp.domain;

import java.time.Instant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Characteristic.class)
public abstract class Characteristic_ {

    // Raw attributes
    public static volatile SingularAttribute<Characteristic, Integer> id;
    public static volatile SingularAttribute<Characteristic, Integer> strength;
    public static volatile SingularAttribute<Characteristic, Integer> agility;
    public static volatile SingularAttribute<Characteristic, Integer> endurance;
    public static volatile SingularAttribute<Characteristic, Integer> intelligence;
    public static volatile SingularAttribute<Characteristic, Integer> charisma;
    public static volatile SingularAttribute<Characteristic, Integer> perception;
    public static volatile SingularAttribute<Characteristic, Integer> luck;
    public static volatile SingularAttribute<Characteristic, Integer> pointsAvailable;
    public static volatile SingularAttribute<Characteristic, Integer> skillAvailable;
    public static volatile SingularAttribute<Characteristic, Instant> creationDate;
    public static volatile SingularAttribute<Characteristic, String> creationAuthor;
    public static volatile SingularAttribute<Characteristic, Instant> lastModificationDate;
    public static volatile SingularAttribute<Characteristic, String> lastModificationAuthor;
    public static volatile SingularAttribute<Characteristic, Integer> version;

    // One to one
    public static volatile SingularAttribute<Characteristic, Character> characteristics;
}