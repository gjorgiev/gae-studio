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

package com.arcbees.gae.querylogger.common.formatters;

import com.arcbees.gae.querylogger.common.dto.DbOperationRecord;
import com.arcbees.gae.querylogger.common.dto.DeleteRecord;
import com.arcbees.gae.querylogger.common.dto.GetRecord;
import com.arcbees.gae.querylogger.common.dto.PutRecord;
import com.arcbees.gae.querylogger.common.dto.QueryRecord;

public interface RecordFormatter {
    
    public String formatRecord(DeleteRecord record);
    
    public String formatRecord(GetRecord record);

    public String formatRecord(PutRecord record);

    public String formatRecord(QueryRecord record);
    
    public String formatRecord(DbOperationRecord record);

}