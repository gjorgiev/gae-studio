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

package com.arcbees.gaestudio.client.formatters;

import com.arcbees.gaestudio.shared.dto.DeleteRecordDTO;
import com.arcbees.gaestudio.shared.dto.GetRecordDto;
import com.arcbees.gaestudio.shared.dto.PutRecordDto;
import com.arcbees.gaestudio.shared.dto.query.QueryDto;
import com.arcbees.gaestudio.shared.dto.query.QueryFilterDto;
import com.arcbees.gaestudio.shared.dto.query.QueryFilterOperatorDto;
import com.arcbees.gaestudio.shared.dto.query.QueryOrderDto;
import com.arcbees.gaestudio.shared.dto.query.QueryOrderDirectionDto;
import com.arcbees.gaestudio.shared.dto.query.QueryRecordDto;

import java.util.HashMap;

public class ObjectifyRecordFormatter extends AbstractRecordFormatter {
    private static final HashMap<QueryFilterOperatorDto, String> FILTER_OP_SYMBOLS =
            new HashMap<QueryFilterOperatorDto, String>();

    static {
        FILTER_OP_SYMBOLS.put(QueryFilterOperatorDto.EQUAL, "=");
        FILTER_OP_SYMBOLS.put(QueryFilterOperatorDto.GREATER_THAN, ">");
        FILTER_OP_SYMBOLS.put(QueryFilterOperatorDto.GREATER_THAN_OR_EQUAL, ">=");
        FILTER_OP_SYMBOLS.put(QueryFilterOperatorDto.LESS_THAN, "<");
        FILTER_OP_SYMBOLS.put(QueryFilterOperatorDto.LESS_THAN_OR_EQUAL, "<=");
    }
    
    @Override
    public String formatRecord(DeleteRecordDTO record) {
        return "Delete record formatting not implemented yet";
    }

    @Override
    public String formatRecord(GetRecordDto record) {
        return "Get record formatting not implemented yet";
    }

    @Override
    public String formatRecord(PutRecordDto record) {
        return "Put record formatting not implemented yet";
    }

    @Override
    public String formatRecord(QueryRecordDto record) {
        final StringBuilder builder = new StringBuilder();
        final QueryDto query = record.getQuery();

        builder.append("query(");
        if (query.getKind() != null) {
            builder.append(query.getKind());
            builder.append(".class");
        }
        builder.append(")");

        if (query.getAncestor() != null) {
            builder.append(".ancestor(");
            builder.append(query.getAncestor());
            builder.append(")");
        }
        
        for (QueryFilterDto filter : query.getFilters()) {
            builder.append(".filter(\"");
            builder.append(filter.getProperty());
            builder.append(" ");
            builder.append(operatorToString(filter.getOperator()));
            builder.append("\", ");
            builder.append(filter.getValue());
            builder.append(")");
        }

        for (QueryOrderDto order : query.getOrders()) {
            builder.append(".order(\"");
            if (order.getDirection() == QueryOrderDirectionDto.DESCENDING) {
                builder.append("-");
            }
            builder.append(order.getProperty());
            builder.append("\")");
        }

        if (query.getOffset() != null) {
            builder.append(".offset(");
            builder.append(query.getOffset());
            builder.append(")");
        }

        if (query.getOffset() != null) {
            builder.append(".limit(");
            builder.append(query.getLimit());
            builder.append(")");
        }

        return builder.toString();

    }
    
    private String operatorToString(QueryFilterOperatorDto operator) {
        return FILTER_OP_SYMBOLS.containsKey(operator)
                ? FILTER_OP_SYMBOLS.get(operator)
                : operator.toString();
    }
}
