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

package com.arcbees.gaestudio.shared.dispatch;

import com.arcbees.gaestudio.shared.dispatch.util.GaeStudioActionImpl;

public class GetEmptyKindEntityAction extends GaeStudioActionImpl<GetEmptyKindEntityResult> {
    private String kind;

    protected GetEmptyKindEntityAction() {
        // Possibly for serialization.
    }

    public GetEmptyKindEntityAction(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
}
