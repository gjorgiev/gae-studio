/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.gaestudio.client.application.widget;

import com.arcbees.gaestudio.client.resources.AppResources;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements HeaderPresenter.MyView {
    interface Binder extends UiBinder<Widget, HeaderView> {
    }

    @UiField(provided = true)
    AppResources resources;

    private final String activeStyleName;

    @Inject
    HeaderView(Binder uiBinder,
               AppResources resources) {
        this.resources = resources;

        initWidget(uiBinder.createAndBindUi(this));

        activeStyleName = resources.sprites().topmenuBgActive();
    }

    @Override
    public void activateCurrentLink(String nameTokens) {
        $("." + activeStyleName).removeClass(activeStyleName);
        $("." + nameTokens).addClass(activeStyleName);
    }
}
