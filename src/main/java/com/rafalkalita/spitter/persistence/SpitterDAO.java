package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 21:44
 */
public interface SpitterDAO {

    void addSpitter(Spitter spitter);

    Spitter getSpitterById(long id);
}
