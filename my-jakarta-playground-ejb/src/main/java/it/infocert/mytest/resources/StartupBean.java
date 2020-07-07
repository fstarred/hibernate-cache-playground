package it.infocert.mytest.resources;

import org.hibernate.Session;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class StartupBean {

    @Inject
    Logger logger;

    @Inject
    EntityManager manager;

    @PostConstruct
    void postConstruct() {
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        logger.info("startup bean: initialized event");
        manager.getEntityManagerFactory().getCache().evictAll();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        logger.info("startup bean: destroyed even");
        manager.getEntityManagerFactory().getCache().evictAll();
    }
}
