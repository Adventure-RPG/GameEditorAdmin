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

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.Instant;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "\"script\"")
public class Script implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Script.class.getName());

    // Raw attributes
    private Integer id;
    private String name;
    private String fileName;
    private String arguments;
    private Instant creationDate;
    private String creationAuthor;
    private Instant lastModificationDate;
    private String lastModificationAuthor;
    private Integer version;

    @Override
    public String entityClassName() {
        return Script.class.getSimpleName();
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

    public Script id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [name] ------------------------

    @NotEmpty
    @Size(max = 200)
    @Column(name = "name", nullable = false, unique = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Script name(String name) {
        setName(name);
        return this;
    }
    // -- [fileName] ------------------------

    @NotEmpty
    @Size(max = 300)
    @Column(name = "file_name", nullable = false, length = 300)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Script fileName(String fileName) {
        setFileName(fileName);
        return this;
    }
    // -- [arguments] ------------------------

    @Size(max = 500)
    @Column(name = "arguments", length = 500)
    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public Script arguments(String arguments) {
        setArguments(arguments);
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

    public Script creationDate(Instant creationDate) {
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

    public Script creationAuthor(String creationAuthor) {
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

    public Script lastModificationDate(Instant lastModificationDate) {
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

    public Script lastModificationAuthor(String lastModificationAuthor) {
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

    public Script version(Integer version) {
        setVersion(version);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Script withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Script && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getName());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this Script instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("name", getName()) //
                .add("fileName", getFileName()) //
                .add("arguments", getArguments()) //
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