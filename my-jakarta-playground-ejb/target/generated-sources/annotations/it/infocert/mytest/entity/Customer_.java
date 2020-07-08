package it.infocert.mytest.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> surname;
	public static volatile SingularAttribute<Customer, Date> lastUpdate;
	public static volatile ListAttribute<Customer, PaymentMethod> paymentMethods;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Date> birth;
	public static volatile SingularAttribute<Customer, Long> id;

	public static final String SURNAME = "surname";
	public static final String LAST_UPDATE = "lastUpdate";
	public static final String PAYMENT_METHODS = "paymentMethods";
	public static final String NAME = "name";
	public static final String BIRTH = "birth";
	public static final String ID = "id";

}

