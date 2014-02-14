/**
 * Copyright (c) 2014 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.client.application.auth.activation;

import com.arcbees.gaestudio.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class ActivationPresenter extends Presenter<ActivationPresenter.MyView, ActivationPresenter.MyProxy> {
    interface MyView extends View {
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.activation)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<ActivationPresenter> {
    }

    @Inject
    ActivationPresenter(EventBus eventBus,
                        MyView view,
                        MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }
}