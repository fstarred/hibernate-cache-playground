package it.infocert.mytest.service;

import it.infocert.mytest.entity.Article;
import it.infocert.mytest.entity.Article_;
import it.infocert.mytest.model.ArticleDTO;
import it.infocert.mytest.model.PropertyDTO;
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
public class ArticleService {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    public ArticleDTO get(String id) {

        statistics();

        final Article article = entityManager.find(Article.class, id);

        final var output = new ArticleDTO();
        output.setDescription(article.getDescription());
        output.setName(article.getName());
        output.setType(article.getType());
        output.setProperties(article.getProperties().stream().map(o -> {
            final var property = new PropertyDTO();
            property.setName(o.getPk().getName());
            property.setDescription(o.getDescription());
            return property;
        }).collect(Collectors.toList()));
        return output;

    }

    public List<ArticleDTO> list(String type) {

        statistics();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        final Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery
                .where(
                        criteriaBuilder.equal(root.get(Article_.type), type)
                );

        final List<Article> articles = entityManager
                .createQuery(criteriaQuery)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setHint(QueryHints.HINT_CACHE_REGION, "query-articles")
                .getResultList();

        return articles.stream().map(article -> {
            final var output = new ArticleDTO();
            output.setDescription(article.getDescription());
            output.setName(article.getName());
            output.setType(article.getType());
            output.setProperties(article.getProperties().stream().map(o -> {
                final var property = new PropertyDTO();
                property.setName(o.getPk().getName());
                property.setDescription(o.getDescription());
                return property;
            }).collect(Collectors.toList()));
            return output;
        }).collect(Collectors.toList());

    }

//    public ArticleDTO get(String id) {
//
//        statistics();
//
//        final Articolo articolo = entityManager.find(Articolo.class, id);
//        final var output = new ArticleDTO();
//        output.setName(articolo.getIdArticolo());
//        output.setType(articolo.getProdotto());
//        output.setDescription(articolo.getDescrizioneBreve());
//
//        logger.info("retrieving article services...");
//
//        output.setProperties(articolo.getServizios().stream().map(o -> {
//            final var service = new PropertyDTO();
//            service.setName(o.getPk().getIdServizio());
//            return service;
//        }).collect(Collectors.toList()));
//        return output;
//    }
//
//    public List<ArticleDTO> list(String type) {
//
//        statistics();
//
//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<Articolo> criteriaQuery = criteriaBuilder.createQuery(Articolo.class);
//        final Root<Articolo> root = criteriaQuery.from(Articolo.class);
//
//        criteriaQuery
//                .where(
//                        criteriaBuilder.equal(root.get(Articolo_.prodotto), type)
//                );
//
//        final List<Articolo> articles = entityManager
//                .createQuery(criteriaQuery)
//                .setHint(QueryHints.HINT_CACHEABLE, true)
//                .setHint(QueryHints.HINT_CACHE_REGION, "query-articles")
//                .getResultList();
//
//        return articles.stream().map(o -> {
//            final var output = new ArticleDTO();
//            output.setType(o.getTipologia());
//            output.setName(o.getIdArticolo());
//            output.setDescription(o.getDescrizioneBreve());
//            return output;
//        }).collect(Collectors.toList());
//
//    }

    private void statistics() {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        logger.info("now: {}", date);

        final SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        final Statistics statistics = sessionFactory.getStatistics();
    }

}
