/**
 * Copyright (c) 2014 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.client.resources;

import com.google.gwt.resources.client.GssResource;
import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableResource extends CellTable.Resources {
    interface TableStyle extends GssResource, CellTable.Style {
    }

    @Override
    @Source({CellTable.Style.DEFAULT_CSS, "css/mixins.css", "css/colors.css", "css/cellTableStyles.css"})
    TableStyle cellTableStyle();
}
