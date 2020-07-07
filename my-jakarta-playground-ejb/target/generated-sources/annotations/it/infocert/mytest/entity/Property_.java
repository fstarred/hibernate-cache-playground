package it.infocert.mytest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Property.class)
public abstract class Property_ {

	public static volatile SingularAttribute<Property, String> description;
	public static volatile SingularAttribute<Property, PropertyPk> pk;
	public static volatile SingularAttribute<Property, Article> article;

	public static final String DESCRIPTION = "description";
	public static final String PK = "pk";
	public static final String ARTICLE = "article";

}

