/**
 * Copyright (c) 2014 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.service;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BlobsService.class).to(BlobsServiceImpl.class).in(Singleton.class);
        bind(EntitiesService.class).to(EntitiesServiceImpl.class).in(Singleton.class);
        bind(EntityService.class).to(EntityServiceImpl.class).in(Singleton.class);
        bind(KindsService.class).to(KindsServiceImpl.class).in(Singleton.class);
        bind(NamespacesService.class).to(NamespacesServiceImpl.class).in(Singleton.class);
        bind(OperationService.class).to(OperationServiceImpl.class).in(Singleton.class);
        bind(RecordService.class).to(RecordServiceImpl.class).in(Singleton.class);
        bind(ImportService.class).to(ImportServiceImpl.class).in(Singleton.class);
        bind(ExportService.class).to(ExportServiceImpl.class).in(Singleton.class);
    }
}
