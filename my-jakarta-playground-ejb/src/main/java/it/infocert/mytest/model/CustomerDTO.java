package it.infocert.mytest.model;

import java.util.Date;
import java.util.List;

public class CustomerDTO {

    private Long id;

    private String name;

    private String surname;

    private Date birth;

    private Date lastUpdate;

    private List<PaymentMethodDTO> paymentMethods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<PaymentMethodDTO> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodDTO> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
