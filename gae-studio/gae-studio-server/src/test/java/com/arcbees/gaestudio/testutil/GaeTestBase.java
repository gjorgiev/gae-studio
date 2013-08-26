/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.testutil;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalSearchServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class GaeTestBase {
    private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(new
            LocalDatastoreServiceTestConfig(), new LocalSearchServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    public Entity createEntityInDatastore(String kindName, String propertyName, String name) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Entity entity = new Entity(kindName);

        entity.setProperty(propertyName, name);
        datastoreService.put(entity);

        return entity;
    }

    public Entity createEntityInNamespace(String namespace,
                                          String kindName,
                                          String propertyName,
                                          String name) {
        String defaultNamespace = NamespaceManager.get();
        NamespaceManager.set(namespace);

        Entity entity = createEntityInDatastore(kindName, propertyName, name);

        NamespaceManager.set(defaultNamespace);

        return entity;
    }
}