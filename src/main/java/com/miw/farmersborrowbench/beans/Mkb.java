package com.miw.farmersborrowbench.beans;

import com.miw.farmersborrowbench.beans.entity.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Mkb extends User {

    @Enumerated(EnumType.STRING)
    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
