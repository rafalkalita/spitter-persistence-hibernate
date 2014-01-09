package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 22:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/config/beanLocations.xml",
		"classpath:dataSource-test-context.xml"
})

@TransactionConfiguration(transactionManager = "txMgr", defaultRollback = true)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class SpitterDAOTest {

    @Inject
    private SpitterDAO dao;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        deleteFromTables(jdbcTemplate, "spitter");
    }
    
    @After
    public void cleanup() {
        deleteFromTables(jdbcTemplate, "spitter");
    }

    @Test
    public void shouldCreateRowsAndSetIds() {

        assertEquals(0, countRowsInTable(jdbcTemplate, "spitter"));
        insertASpitter("username", "password", "fullname");

        assertEquals(1, countRowsInTable(jdbcTemplate, "spitter"));

        insertASpitter("username2", "password2", "fullname2");
        assertEquals(2, countRowsInTable(jdbcTemplate, "spitter"));
    }

    @Test
    public void shouldBeAbleToFindInsertedSpitter() {

        Spitter spitterIn = insertASpitter("username", "password", "fullname");

        Spitter spitterOut = dao.getSpitterById(spitterIn.getId());

        assertEquals(spitterIn, spitterOut);
    }

    private Spitter insertASpitter(String username, String password, String fullname) {

        Spitter spitter = new Spitter();

        spitter.setUsername(username);
        spitter.setPassword(password);
        spitter.setFullName(fullname);

        assertNull(spitter.getId());
        dao.addSpitter(spitter);
        assertNotNull(spitter.getId());

        return spitter;
    }
}
