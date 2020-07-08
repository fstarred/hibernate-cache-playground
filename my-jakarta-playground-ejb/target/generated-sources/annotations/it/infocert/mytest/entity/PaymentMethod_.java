package it.infocert.mytest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentMethod.class)
public abstract class PaymentMethod_ {

	public static volatile SingularAttribute<PaymentMethod, String> number;
	public static volatile SingularAttribute<PaymentMethod, Integer> id;
	public static volatile SingularAttribute<PaymentMethod, String> type;
	public static volatile SingularAttribute<PaymentMethod, Customer> customer;

	public static final String NUMBER = "number";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String CUSTOMER = "customer";

}

