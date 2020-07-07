package it.infocert.mytest.service;

import it.infocert.mytest.entity.Customer;
import it.infocert.mytest.entity.Customer_;
import it.infocert.mytest.model.CustomerDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(Transactional.TxType.REQUIRED)
public class CustomerService {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    public CustomerDTO get(Long id) {
        statistics();

//        final Cliente cliente = entityManager.find(Cliente.class, uidcli);
//        final var output = new Customer();
//        output.setBusinessName(cliente.getRagioneSociale());
//        output.setEmail(cliente.getEmail());
//        output.setUidCli(cliente.getUidCliente());
        final Customer customer = entityManager.find(Customer.class, id);
        final var output = new CustomerDTO();
        output.setBirth(customer.getBirth());
        output.setId(customer.getId());
        output.setLastUpdate(customer.getLastUpdate());
        output.setName(customer.getName());
        output.setSurname(customer.getSurname());
        return output;
    }

    public List<CustomerDTO> list(String email) {

        statistics();

//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
//        final Root<Cliente> root = criteriaQuery.from(Cliente.class);
//
//        criteriaQuery
//                .where(
//                        criteriaBuilder.like(root.get(Cliente_.email), email + '%')
//                );
//
//        final List<Cliente> articles = entityManager
//                .createQuery(criteriaQuery)
//                .setHint(QueryHints.HINT_CACHEABLE, true)
//                .setHint(QueryHints.HINT_CACHE_REGION, "query-customers")
//                .getResultList();

//        return articles.stream().map(o -> {
//            final var output = new Customer();
//            output.setBusinessName(o.getRagioneSociale());
//            output.setEmail(o.getEmail());
//            output.setUidCli(o.getUidCliente());
//            return output;
//        }).collect(Collectors.toList());

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        final Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery
                .where(
                        criteriaBuilder.like(root.get(Customer_.name), email + '%')
                );

        final List<Customer> customers = entityManager
                .createQuery(criteriaQuery)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setHint(QueryHints.HINT_CACHE_REGION, "query-customers")
                .getResultList();

        return customers.stream().map(customer -> {
            final var output = new CustomerDTO();
            output.setBirth(customer.getBirth());
            output.setId(customer.getId());
            output.setLastUpdate(customer.getLastUpdate());
            output.setName(customer.getName());
            output.setSurname(customer.getSurname());
            return output;
        }).collect(Collectors.toList());

    }

    private void statistics() {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        logger.info("now: {}", date);

        final SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        final Statistics statistics = sessionFactory.getStatistics();
    }

}
