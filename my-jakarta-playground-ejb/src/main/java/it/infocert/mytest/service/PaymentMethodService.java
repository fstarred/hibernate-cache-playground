package it.infocert.mytest.service;

import it.infocert.mytest.entity.PaymentMethod;
import it.infocert.mytest.model.PaymentMethodDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Transactional(Transactional.TxType.REQUIRED)
public class PaymentMethodService {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    public PaymentMethodDTO get(Integer id) {

        statistics();

        final PaymentMethod paymentMethod = entityManager.find(PaymentMethod.class, id);

        final var output = new PaymentMethodDTO();
        output.setType(paymentMethod.getType());
        output.setNumber(paymentMethod.getNumber());
        output.setId(paymentMethod.getId());
        return output;
    }

    private void statistics() {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        logger.info("now: {}", date);

        final SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        final Statistics statistics = sessionFactory.getStatistics();
    }
}
