/*
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

package com.arcbees.gaestudio.client.application.profiler.widget;

import com.arcbees.gaestudio.client.formatters.RecordFormatter;
import com.arcbees.gaestudio.client.resources.AppResources;
import com.arcbees.gaestudio.shared.dto.DbOperationRecordDto;
import com.arcbees.gaestudio.shared.stacktrace.StackTraceElementDto;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class DetailsView extends ViewImpl implements DetailsPresenter.MyView {
    interface Binder extends UiBinder<Widget, DetailsView> {
    }

    @UiField
    HTML statement;

    @UiField
    HTML callLocation;

    @UiField(provided = true)
    AppResources resources;

    private final RecordFormatter recordFormatter;

    @Inject
    DetailsView(Binder uiBinder,
                AppResources resources,
                RecordFormatter recordFormatter) {
        this.resources = resources;

        initWidget(uiBinder.createAndBindUi(this));

        this.recordFormatter = recordFormatter;
    }

    @Override
    public void displayStatementDetails(DbOperationRecordDto record) {
        statement.setHTML(recordFormatter.formatRecord(record));
        callLocation.setHTML(tempFormatCaller(record.getCallerStackTraceElement()));
    }

    @Override
    public void clear() {
        statement.setHTML("");
        callLocation.setHTML("");
    }

    // TODO this is just a quick hack
    private String tempFormatCaller(StackTraceElementDto caller) {
        StringBuilder builder = new StringBuilder();

        builder.append("Class:");
        builder.append(caller.getClassName());
        builder.append(" Method:");
        builder.append(caller.getMethodName());
        builder.append(" Filename:");
        builder.append(caller.getFileName());
        builder.append(" Line#:");
        builder.append(caller.getLineNumber());

        return builder.toString();
    }
}
