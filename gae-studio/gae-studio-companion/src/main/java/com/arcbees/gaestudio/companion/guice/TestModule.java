/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.companion.guice;

import com.arcbees.gaestudio.server.guice.CommonModule;
import com.arcbees.gaestudio.server.guice.EmbeddedPathModule;
import com.arcbees.gaestudio.server.service.auth.UnsecureAuthModule;
import com.google.inject.servlet.ServletModule;

public class TestModule extends ServletModule {
    @Override
    protected void configureServlets() {
        install(new EmbeddedPathModule());
        install(new CommonModule(getServletContext()));
        install(new UnsecureLicenseModule());
        install(new UnsecureAuthModule());
    }
}
