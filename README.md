# hibernate-cache-playground
hibernate cache application under wildfly

## cache configuration

```xml
<property name="hibernate.cache.infinispan.entity.expiration.max_idle" value="30000"/>
<property name="hibernate.cache.infinispan.query.expiration.max_idle" value="5000"/>
<property name="hibernate.cache.infinispan.collection.expiration.max_idle" value="5000"/>

<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.entity-articles.expiration.max_idle" value="20000"/>
<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.entity-customers.expiration.max_idle" value="25000"/>
<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.entity-payment-method.expiration.max_idle" value="15000"/>

<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.query-articles.expiration.max_idle" value="10000"/>
<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.query-customers.expiration.max_idle" value="15000"/>

<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.nested-article-properties.expiration.max_idle" value="15000"/>

<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.entity-customers.eviction.max_entries" value="3"/>

<property name="hibernate.cache.infinispan.my-jakarta-playground.ear/my-jakarta-playground-ejb.jar#primary.entity-payment-method.expiration.life_span" value="10000"/>
```

## entities summary

```java
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "entity-articles")
@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "nested-article-properties")
    @OneToMany(targetEntity = Property.class, fetch = FetchType.LAZY, mappedBy = Property_.ARTICLE)
    private List<Property> properties;

}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "PROPERTY")
@Entity
public class Property implements Serializable {
}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entity-customers")
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = PaymentMethod.class, mappedBy = PaymentMethod_.CUSTOMER)
    private List<PaymentMethod> paymentMethods;

}

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entity-payment-method")
@Table(name = "CUST_PAYMENT_METHOD")
@Entity
public class PaymentMethod implements Serializable {
}
```

## test results

### CUSTOMERS API

#### CACHE DURATION

| API	        			| CUSTOMER      | PAYMENT-METHOD  |
| --------------------- 	|:-------------:| :------------:  |
| /customers/{id}   		| 25 secs		| 5 secs 		  |
| /customers		   		| 15 secs		| 5 secs 		  |

#### CACHE CONFIGURATION AFFECTED

| API	        			| CUSTOMER      							| PAYMENT-METHOD  								|
| --------------------- 	|:---------------------------------------:	| :------------------------------------------:  |
| /customers/{id}   		| entity-customers.expiration.max_idle		| collection.expiration.max_idle				|
| /customers		   		| query-customers.expiration.max_idle		| collection.expiration.max_idle				|

### ARTICLES API

#### CACHE DURATION

| API	        						| ARTICLE       | PROPERTY		  |
| --------------------- 				|:-------------:| :------------:  |
| /articles/{id}   						| 20 secs		| 15 secs 		  |
| /articles?type	   					| 10 secs		| 15 secs 		  |
| /articles/{id}/properties/{property}	| -				| 30 secs 		  |

#### CACHE CONFIGURATION AFFECTED

| API	        						| ARTICLE       							| PROPERTY		  									|
| --------------------- 				|:---------------------------------------:	| :----------------------------------------------:	|
| /articles/{id}   						| entity-articles.expiration.max_idle		| nested-article-properties.expiration.max_idle 	|
| /articles?type	   					| query-articles.expiration.max_idle		| nested-article-properties.expiration.max_idle  	|
| /articles/{id}/properties/{property}	| -											| entity.expiration.max_idle				        |


### PAYMENT-METHOD API

#### CACHE DURATION

| API	        						| PAYMENT METHOD    |
| --------------------- 				|:----------------:	|
| /payment-methods/{id}   				| 15 secs			|


#### CACHE CONFIGURATION AFFECTED

| API	        						| PAYMENT METHOD    								|
| --------------------- 				|:------------------------------------------------: |
| /payment-methods/{id}   				| entity-payment-method.expiration.max_idle			|
