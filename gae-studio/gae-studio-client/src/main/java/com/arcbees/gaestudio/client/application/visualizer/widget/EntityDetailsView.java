/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.client.application.visualizer.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;


public class EntityDetailsView extends PopupViewWithUiHandlers<EntityDetailsUiHandlers>
        implements EntityDetailsPresenter.MyView {
    interface Binder extends UiBinder<Widget, EntityDetailsView> {
    }

    @UiField
    TextArea entityDetails;
    @UiField
    Button save;
    @UiField
    Button cancel;
    @UiField
    Label error;

    @Inject
    EntityDetailsView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void displayEntityDetails(String json) {
        asPopupPanel().center();
        error.setVisible(false);
        entityDetails.setText(json);
    }

    @Override
    public void showError(String message) {
        error.setVisible(true);
        error.setText(message);
    }

    @UiHandler("save")
    void onEditClicked(ClickEvent event) {
        getUiHandlers().saveEntity(entityDetails.getValue());
    }

    @UiHandler("cancel")
    void onCancelClicked(ClickEvent event) {
        asPopupPanel().hide();
    }
}