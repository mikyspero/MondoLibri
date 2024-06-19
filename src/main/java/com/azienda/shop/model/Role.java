package com.azienda.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public enum Role {
    ADMIN(0),
    USER(1);

    private final int index;

    Role(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
