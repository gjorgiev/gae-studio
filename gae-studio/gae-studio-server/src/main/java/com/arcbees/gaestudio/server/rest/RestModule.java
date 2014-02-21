/**
 * Copyright (c) 2014 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.rest;

import com.arcbees.gaestudio.server.rest.auth.AuthResource;
import com.arcbees.gaestudio.server.rest.profiler.OperationsResource;
import com.arcbees.gaestudio.server.rest.profiler.RecordResource;
import com.arcbees.gaestudio.server.rest.visualizer.BlobsResource;
import com.arcbees.gaestudio.server.rest.visualizer.EntitiesResource;
import com.arcbees.gaestudio.server.rest.visualizer.EntityResource;
import com.arcbees.gaestudio.server.rest.visualizer.ExportResource;
import com.arcbees.gaestudio.server.rest.visualizer.ImportResource;
import com.arcbees.gaestudio.server.rest.visualizer.KindsResource;
import com.arcbees.gaestudio.server.rest.visualizer.NamespacesResource;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class RestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(SubresourceFactory.class));

        bind(BlobsResource.class);
        bind(NamespacesResource.class);
        bind(KindsResource.class);
        bind(EntitiesResource.class);
        bind(OperationsResource.class);
        bind(RecordResource.class);
        bind(AuthResource.class);
        bind(EntityResource.class);
        bind(ExportResource.class);
        bind(ImportResource.class);
    }
}
