package com.sbab.home.assignment.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Businformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    private String busnumber;

    @NotNull
    @NotEmpty
    private String directioncode;

    @NotNull
    @NotEmpty
    private String busstopnumber;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getBusnumber() {
        return busnumber;
    }


    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }


    public String getDirectioncode() {
        return directioncode;
    }


    public void setDirectioncode(String directioncode) {
        this.directioncode = directioncode;
    }


    public String getBusstopnumber() {
        return busstopnumber;
    }


    public void setBusstopnumber(String busstopnumber) {
        this.busstopnumber = busstopnumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Businformation)) {
            return false;
        }
        Businformation that = (Businformation) o;
        return getId() == that.getId() &&
               Objects.equals(getBusnumber(), that.getBusnumber()) &&
               Objects.equals(getDirectioncode(), that.getDirectioncode()) &&
               Objects.equals(getBusstopnumber(), that.getBusstopnumber());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBusnumber(), getDirectioncode(), getBusstopnumber());
    }


    @Override
    public String toString() {
        return "Businformation{" +
               "id=" + id +
               ", busnumber='" + busnumber + '\'' +
               ", directioncode='" + directioncode + '\'' +
               ", busstopnumber='" + busstopnumber + '\'' +
               '}';
    }
}

