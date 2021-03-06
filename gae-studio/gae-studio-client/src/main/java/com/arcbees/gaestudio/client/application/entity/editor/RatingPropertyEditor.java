/**
 * Copyright 2015 ArcBees Inc.
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

package com.arcbees.gaestudio.client.application.entity.editor;

import javax.inject.Inject;

import com.arcbees.gaestudio.client.resources.AppConstants;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.assistedinject.Assisted;

public class RatingPropertyEditor extends LongPropertyEditor {
    private final AppConstants appConstants;
    private final RatingValidator ratingValidator;

    @Inject
    RatingPropertyEditor(
            AppConstants appConstants,
            RatingValidator ratingValidator,
            @Assisted String key,
            @Assisted JSONValue property) {
        super(key, property);

        this.appConstants = appConstants;
        this.ratingValidator = ratingValidator;
    }

    @Override
    protected void showErrors() {
        showError(appConstants.invalidRating());
    }

    @Override
    protected boolean validate() {
        return ratingValidator.isRatingValid(getValue());
    }
}
