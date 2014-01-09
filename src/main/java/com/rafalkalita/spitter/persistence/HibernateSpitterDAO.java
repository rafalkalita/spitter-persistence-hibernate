package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 22:21
 */
@Repository(value = "spitterDAO")
public class HibernateSpitterDAO implements SpitterDAO {

    @Inject
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addSpitter(Spitter spitter) {
        currentSession().save(spitter);
    }
    
    public Spitter getSpitterById(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }
}
