/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.guice;

import javax.inject.Singleton;
import javax.servlet.ServletContext;

import com.arcbees.gaestudio.server.ConnectFilter;
import com.arcbees.gaestudio.server.analytic.AnalyticModule;
import com.arcbees.gaestudio.server.dto.mapper.MapperModule;
import com.arcbees.gaestudio.server.exception.ExceptionModule;
import com.arcbees.gaestudio.server.recorder.GaeStudioRecorderModule;
import com.arcbees.gaestudio.server.rest.RestModule;
import com.arcbees.gaestudio.server.service.ServiceModule;
import com.arcbees.gaestudio.server.velocity.VelocityModule;
import com.arcbees.gaestudio.shared.BaseRestPath;
import com.arcbees.gaestudio.shared.rest.EndPoints;
import com.google.appengine.api.datastore.GsonModule;
import com.google.common.base.Strings;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

import static org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX;

public class CommonModule extends ServletModule {
    private final String restPath;

    public CommonModule(ServletContext servletContext) {
        String restEasyPrefix = servletContext.getInitParameter(RESTEASY_SERVLET_MAPPING_PREFIX);

        if (Strings.isNullOrEmpty(restEasyPrefix) || "/*".equals(restEasyPrefix)) {
            restPath = "";
        } else {
            restPath = (restEasyPrefix + "/").replace("//", "/");
        }
    }

    CommonModule(String restPath) {
        this.restPath = restPath.replace("//", "/");
    }

    @Override
    protected void configureServlets() {
        // RestModule needs to be loaded before AnalyticsModule, since it binds interceptors in the rest package
        install(new RestModule());
        install(new AnalyticModule());
        install(new ExceptionModule());
        install(new GaeStudioRecorderModule());
        install(new GsonModule());
        install(new MapperModule());
        install(new ServiceModule());
        install(new VelocityModule());

        bindConstant().annotatedWith(BaseRestPath.class).to(restPath);

        String fullRestPath = parseFullRestPath();

        filter(fullRestPath + "*").through(GuiceRestEasyFilterDispatcher.class);

        bind(ConnectFilter.class).in(Singleton.class);
        filter("/_ah/channel/connected/", "/_ah/channel/disconnected/").through(ConnectFilter.class);
    }

    @Provides
    private String parseFullRestPath() {
        String baseRestPath = restPath == null ? "/" : "/" + restPath + "/";
        return (baseRestPath + EndPoints.REST_PATH).replace("//", "/");
    }
}
