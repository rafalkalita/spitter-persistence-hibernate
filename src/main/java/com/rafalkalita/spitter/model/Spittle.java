package com.rafalkalita.spitter.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

/**
 * User: rafalkalita
 * Date: 20/11/2013
 * Time: 18:31
 */
@Entity
@Table(name = "spittle")
public class Spittle implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Spitter spitter;
    private String message;

    @DateTimeFormat(pattern="hh:mma MMM d, YYYY")
    private Date whenCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "spitter_id")
    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    @Column(name = "message")
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "whencreated")
    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
