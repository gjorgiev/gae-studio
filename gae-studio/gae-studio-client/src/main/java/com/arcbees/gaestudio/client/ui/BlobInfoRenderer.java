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

package com.arcbees.gaestudio.client.ui;

import com.arcbees.gaestudio.shared.dto.entity.BlobInfoDto;
import com.google.common.base.Strings;
import com.google.gwt.text.shared.AbstractRenderer;

public class BlobInfoRenderer extends AbstractRenderer<BlobInfoDto> {
    @Override
    public String render(BlobInfoDto blobInfo) {
        if (blobInfo == null) {
            return "<null>";
        }

        String string;

        if (!Strings.isNullOrEmpty(blobInfo.getName())) {
            string = blobInfo.getName();
        } else {
            string = "<no name>";
        }

        if (!Strings.isNullOrEmpty(blobInfo.getKey())) {
            string += " <" + blobInfo.getKey() + ">";
        }

        return string;
    }
}
