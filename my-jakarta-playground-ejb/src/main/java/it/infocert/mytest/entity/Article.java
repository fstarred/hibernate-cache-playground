package it.infocert.mytest.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "entity-articles")
@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;

    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "nested-article-properties")
    @OneToMany(targetEntity = Property.class, fetch = FetchType.LAZY, mappedBy = Property_.ARTICLE)
    private List<Property> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
