/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.guice;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.inject.Inject;
import com.google.inject.Provider;

// TODO externalize magic strings
public class RequestIdProvider implements Provider<Long> {
    private final MemcacheService memcacheService;

    @Inject
    RequestIdProvider(MemcacheService memcacheService) {
        this.memcacheService = memcacheService;
    }

    @Override
    public Long get() {
        return memcacheService.increment("request.counter", 1L, 0L);
    }
}