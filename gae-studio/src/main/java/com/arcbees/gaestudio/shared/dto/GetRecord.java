/*
 * Copyright 2012 ArcBees Inc.
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

package com.arcbees.gaestudio.shared.dto;

import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.GetResponse;

public class GetRecord extends DbOperationRecord {

    private static final long serialVersionUID = -7824011424844857721L;

    private final GetRequest getRequest;
    
    private final GetResponse getResponse;

    public GetRecord(GetRequest getRequest, GetResponse getResponse, StackTraceElement[] stackTrace, Long requestId,
                     int executionTimeMs) {
        super(stackTrace, requestId, executionTimeMs);
        this.getRequest = getRequest;
        this.getResponse = getResponse;
    }

    public GetRequest getGetRequest() {
        return getRequest;
    }

    public GetResponse getGetResponse() {
        return getResponse;
    }

}
