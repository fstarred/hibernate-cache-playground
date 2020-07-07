package it.infocert.mytest.service;

import it.infocert.mytest.entity.Property;
import it.infocert.mytest.entity.PropertyPk;
import it.infocert.mytest.model.PropertyDTO;
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
public class PropertyService {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

//    public PropertyDTO get(String articleName, String id, String operation) {
//
//        statistics();
//
//        final var pk = new ArticoloServizioPk();
//        pk.setIdArticolo(articleName);
//        pk.setIdServizio(id);
//        pk.setNomeOperazione(operation);
//
//        final ArticoloServizio articoloServizio = entityManager.find(ArticoloServizio.class, pk);
//        final var output = new PropertyDTO();
//        output.setName(articoloServizio.getPk().getIdServizio());
//
//        return output;
//    }

    public PropertyDTO get(String articleName, String id) {

        statistics();

        final var pk = new PropertyPk();
        pk.setArticleName(articleName);
        pk.setName(id);

        final Property property = entityManager.find(Property.class, pk);

        final var output = new PropertyDTO();
        output.setDescription(property.getDescription());
        output.setName(property.getPk().getName());
        return output;
    }

    private void statistics() {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        logger.info("now: {}", date);

        final SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        final Statistics statistics = sessionFactory.getStatistics();
    }

}
