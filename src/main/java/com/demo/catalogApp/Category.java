package com.demo.catalogApp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;

    @Column()
    @Lob
    @Nationalized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = CategoryAttributeConvertor.class)
    private String attributes;

}
