/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.mycompany.myapp.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.mycompany.myapp.audit.AuditContextHolder;

@Entity
@Table(name = "\"character\"")
public class Character implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Character.class.getName());

    // Raw attributes
    private Integer id;
    private String username;
    private Instant creationDate;
    private String creationAuthor;
    private Instant lastModificationDate;
    private String lastModificationAuthor;
    private Integer version;

    // Many to one
    private Race race;

    // One to one
    private Characteristic charc;

    // Many to many
    private List<Skill> theSkills = new ArrayList<Skill>();

    @Override
    public String entityClassName() {
        return Character.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "id", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Character id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [username] ------------------------

    @NotEmpty
    @Size(max = 200)
    @Column(name = "username", nullable = false, unique = true, length = 200)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Character username(String username) {
        setUsername(username);
        return this;
    }
    // -- [creationDate] ------------------------

    @Column(name = "creation_date", length = 29)
    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Character creationDate(Instant creationDate) {
        setCreationDate(creationDate);
        return this;
    }
    // -- [creationAuthor] ------------------------

    @Column(name = "creation_author", length = 200)
    public String getCreationAuthor() {
        return creationAuthor;
    }

    public void setCreationAuthor(String creationAuthor) {
        this.creationAuthor = creationAuthor;
    }

    public Character creationAuthor(String creationAuthor) {
        setCreationAuthor(creationAuthor);
        return this;
    }
    // -- [lastModificationDate] ------------------------

    @Column(name = "last_modification_date", length = 29)
    public Instant getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Instant lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Character lastModificationDate(Instant lastModificationDate) {
        setLastModificationDate(lastModificationDate);
        return this;
    }
    // -- [lastModificationAuthor] ------------------------

    @Column(name = "last_modification_author", length = 200)
    public String getLastModificationAuthor() {
        return lastModificationAuthor;
    }

    public void setLastModificationAuthor(String lastModificationAuthor) {
        this.lastModificationAuthor = lastModificationAuthor;
    }

    public Character lastModificationAuthor(String lastModificationAuthor) {
        setLastModificationAuthor(lastModificationAuthor);
        return this;
    }
    // -- [version] ------------------------

    @Column(name = "version", precision = 10)
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Character version(Integer version) {
        setVersion(version);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Character.race ==> Race.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "race_id", nullable = false)
    @ManyToOne
    public Race getRace() {
        return race;
    }

    /**
     * Set the {@link #race} without adding this Character instance on the passed {@link #race}
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by {@link Race}
     */
    public void setRace(Race race) {
        this.race = race;
    }

    public Character race(Race race) {
        setRace(race);
        return this;
    }

    // -----------------------------------------------------------------
    // One to one
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Owner side of one-to-one relation: Character.charcId ==> Characteristic.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @JoinColumn(name = "charc_id", nullable = false, unique = true)
    @OneToOne
    public Characteristic getCharc() {
        return charc;
    }

    public void setCharc(Characteristic charc) {
        this.charc = charc;
    }

    public Character charc(Characteristic charc) {
        setCharc(charc);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to Many
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // many-to-many: character ==> theSkills
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

    /**
     * Returns the {@link #theSkills} list.
     */
    @JoinTable(name = "character_skill", joinColumns = @JoinColumn(name = "char_id") , inverseJoinColumns = @JoinColumn(name = "skill_id") )
    @ManyToMany
    public List<Skill> getTheSkills() {
        return theSkills;
    }

    /**
     * Set the {@link #theSkills} list.
     *
     * @param theSkills the list of Skill
     */
    public void setTheSkills(List<Skill> theSkills) {
        this.theSkills = theSkills;
    }

    /**
     * Helper method to add the passed {@link Skill} to the {@link #theSkills} list.
     */
    public boolean addAnSkill(Skill anSkill) {
        return getTheSkills().add(anSkill);
    }

    /**
     * Helper method to remove the passed {@link Skill} from the {@link #theSkills} list.
     */
    public boolean removeAnSkill(Skill anSkill) {
        return getTheSkills().remove(anSkill);
    }

    /**
     * Helper method to determine if the passed {@link Skill} is present in the {@link #theSkills} list.
     */
    public boolean containsAnSkill(Skill anSkill) {
        return getTheSkills() != null && getTheSkills().contains(anSkill);
    }

    /**
     * Apply the default values.
     */
    public Character withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Character && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getUsername());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this Character instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("username", getUsername()) //
                .add("creationDate", getCreationDate()) //
                .add("creationAuthor", getCreationAuthor()) //
                .add("lastModificationDate", getLastModificationDate()) //
                .add("lastModificationAuthor", getLastModificationAuthor()) //
                .add("version", getVersion()) //
                .toString();
    }

    @PrePersist
    protected void prePersist() {
        if (AuditContextHolder.audit()) {
            setCreationAuthor(AuditContextHolder.username());
            setCreationDate(Instant.now());

        }
    }

    @PreUpdate
    protected void preUpdate() {
        if (AuditContextHolder.audit()) {
            setLastModificationAuthor(AuditContextHolder.username());
            setLastModificationDate(Instant.now());
        }
    }
}