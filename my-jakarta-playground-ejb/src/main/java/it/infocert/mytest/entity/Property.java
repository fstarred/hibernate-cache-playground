package it.infocert.mytest.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "PROPERTY")
@Entity
public class Property implements Serializable {

    @EmbeddedId
    private PropertyPk pk;

    @Column(name = "description")
    private String description;

    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY)
    @JoinColumn(name="article_name", referencedColumnName="name", insertable=false, updatable=false)
    private Article article;

    public PropertyPk getPk() {
        return pk;
    }

    public void setPk(PropertyPk pk) {
        this.pk = pk;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
