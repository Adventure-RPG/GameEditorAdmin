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

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Gender.class)
public abstract class Gender_ {

    // Raw attributes
    public static volatile SingularAttribute<Gender, Integer> id;
    public static volatile SingularAttribute<Gender, String> name;
    public static volatile SingularAttribute<Gender, Integer> strengthmin;
    public static volatile SingularAttribute<Gender, Integer> strengthmax;
    public static volatile SingularAttribute<Gender, Integer> agilitymin;
    public static volatile SingularAttribute<Gender, Integer> agilitymax;
    public static volatile SingularAttribute<Gender, Integer> endurancemin;
    public static volatile SingularAttribute<Gender, Integer> endurancemax;
    public static volatile SingularAttribute<Gender, Integer> intelligencemin;
    public static volatile SingularAttribute<Gender, Integer> intelligencemax;
    public static volatile SingularAttribute<Gender, Integer> charismamin;
    public static volatile SingularAttribute<Gender, Integer> charismamax;
    public static volatile SingularAttribute<Gender, Integer> perceptionmin;
    public static volatile SingularAttribute<Gender, Integer> perceptionmax;
    public static volatile SingularAttribute<Gender, Integer> luckmin;
    public static volatile SingularAttribute<Gender, Integer> luckmax;
    public static volatile SingularAttribute<Gender, Integer> heightmin;
    public static volatile SingularAttribute<Gender, Integer> heightmax;
    public static volatile SingularAttribute<Gender, Integer> weightmin;
    public static volatile SingularAttribute<Gender, Integer> weightmax;
    public static volatile SingularAttribute<Gender, Instant> creationDate;
    public static volatile SingularAttribute<Gender, String> creationAuthor;
    public static volatile SingularAttribute<Gender, Instant> lastModificationDate;
    public static volatile SingularAttribute<Gender, String> lastModificationAuthor;
    public static volatile SingularAttribute<Gender, Integer> version;

    // Many to many
    public static volatile ListAttribute<Gender, RaceOption> theRacesOptions;
}