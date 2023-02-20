package com.ourstocks.jwtapp.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Role;
    }

}
