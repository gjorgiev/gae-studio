/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.recorder;

import javax.inject.Singleton;

import com.arcbees.gaestudio.server.guice.RequestIdProvider;
import com.arcbees.gaestudio.server.recorder.authentication.Listener;
import com.arcbees.gaestudio.server.recorder.authentication.ListenerProvider;
import com.arcbees.gaestudio.shared.util.SimpleStackInspector;
import com.arcbees.gaestudio.shared.util.StackInspector;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.google.inject.servlet.RequestScoped;

// TODO externalize magic strings
public class GaeStudioRecorderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Long.class).annotatedWith(Names.named("requestId")).toProvider(RequestIdProvider.class)
                .in(RequestScoped.class);

        bind(StackInspector.class).to(SimpleStackInspector.class);
        bind(DbOperationRecorder.class).to(MemcacheDbOperationRecorder.class);

        install(new FactoryModuleBuilder()
                .implement(DbOperationRecorderHook.class, DbOperationRecorderHook.class)
                .build(DbOperationRecorderHookFactory.class));

        bind(HookRegistrar.class).asEagerSingleton();
        bind(ListenerProvider.class).in(Singleton.class);
        bind(Listener.class).toProvider(ListenerProvider.class).in(RequestScoped.class);
    }

    @Provides
    @Singleton
    private MemcacheService memcacheServiceProvider() {
        return MemcacheServiceFactory.getMemcacheService("gae.visualizer");
    }
}