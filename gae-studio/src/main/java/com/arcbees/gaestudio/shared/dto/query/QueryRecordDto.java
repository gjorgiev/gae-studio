/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.shared.dto.query;

import com.arcbees.gaestudio.shared.dto.DbOperationRecordDto;
import com.arcbees.gaestudio.shared.stacktrace.StackTraceElementDto;

public class QueryRecordDto extends DbOperationRecordDto {
    private static final long serialVersionUID = -5801060359687948701L;

    private QueryDto query;
    private QueryResultDto queryResult;

    @SuppressWarnings("unused")
    protected QueryRecordDto() {
    }

    public QueryRecordDto(QueryDto query, QueryResultDto queryResult,
                          StackTraceElementDto callerStackTraceElement,
                          Long requestId,
                          Long statementId,
                          Integer executionTimeMs) {
        super(callerStackTraceElement, requestId, statementId, executionTimeMs);
        this.query = query;
        this.queryResult = queryResult;
    }


    public QueryDto getQuery() {
        return query;
    }

    public QueryResultDto getQueryResult() {
        return queryResult;
    }
}