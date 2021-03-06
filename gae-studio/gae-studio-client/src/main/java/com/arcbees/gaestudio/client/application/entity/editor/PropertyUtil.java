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

import com.arcbees.gaestudio.shared.PropertyType;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import static com.arcbees.gaestudio.shared.PropertyName.GAE_PROPERTY_TYPE;
import static com.arcbees.gaestudio.shared.PropertyName.INDEXED;
import static com.arcbees.gaestudio.shared.PropertyName.VALUE;

public class PropertyUtil {
    public static JSONValue getPropertyValue(JSONValue property) {
        JSONObject object = property.isObject();

        if (object != null && object.containsKey(VALUE)) {
            return object.get(VALUE);
        }

        return property;
    }

    public static Boolean isPropertyIndexed(JSONValue property) {
        JSONObject embed = property.isObject();

        if (embed != null && embed.containsKey(INDEXED)) {
            JSONBoolean indexed = embed.get(INDEXED).isBoolean();

            return indexed == null || indexed.booleanValue();
        }

        return true;
    }

    public static JSONValue cleanUpMetadata(JSONValue property) {
        JSONValue result = property;

        JSONObject object = property.isObject();
        if (object != null) {
            result = cleanUpObjectMetadata(object);
        }

        JSONArray array = property.isArray();
        if (array != null) {
            result = cleanUpArrayMetadata(array);
        }

        return result;
    }

    public static JSONValue parseJsonValueWithMetadata(JSONValue value, PropertyType type, Boolean indexed) {
        JSONObject wrapper = null;

        if (!indexed || type != PropertyType.NULL) {
            wrapper = new JSONObject();
        }

        if (wrapper != null) {
            wrapper.put(VALUE, value);

            if (type != PropertyType.NULL) {
                wrapper.put(GAE_PROPERTY_TYPE, new JSONString(type.name()));
            }

            if (!indexed) {
                wrapper.put(INDEXED, JSONBoolean.getInstance(false));
            }

            return wrapper;
        }

        return value;
    }

    public static PropertyType getPropertyType(JSONValue jsonValue) {
        PropertyType type = PropertyType.NULL;

        JSONObject asObject = jsonValue.isObject();
        JSONValue subValue = jsonValue;
        if (asObject != null) {
            if (asObject.containsKey(GAE_PROPERTY_TYPE)) {
                JSONString typeName = asObject.get(GAE_PROPERTY_TYPE).isString();
                type = PropertyType.valueOf(typeName.stringValue());

                subValue = getPropertyValue(jsonValue);
            } else if (asObject.containsKey(INDEXED)) {
                subValue = getPropertyValue(jsonValue);
            }
        }

        if (type == PropertyType.NULL) {
            type = guessPropertyType(subValue);
        }

        return type;
    }

    public static String getPropertyAsString(JSONObject object, String propertyName) {
        JSONValue property = object.get(propertyName);
        if (propertyIsNotNull(property)) {
            return property.isString().stringValue();
        }

        return "";
    }

    public static JSONNumber getPropertyAsNumber(JSONObject object, String propertyName) {
        JSONValue property = object.get(propertyName);
        if (propertyIsNotNull(property)) {
            return property.isNumber();
        }

        return null;
    }

    public static JSONObject getPropertyAsObject(JSONObject object, String propertyName) {
        JSONValue property = object.get(propertyName);
        if (propertyIsNotNull(property)) {
            return property.isObject();
        }

        return null;
    }

    private static boolean propertyIsNotNull(JSONValue property) {
        return property != null && property.isNull() == null;
    }

    private static PropertyType guessPropertyType(JSONValue jsonValue) {
        PropertyType type;
        if (jsonValue.isString() != null) {
            type = PropertyType.STRING;
        } else if (jsonValue.isNumber() != null) {
            type = PropertyType.NUMERIC;
        } else if (jsonValue.isBoolean() != null) {
            type = PropertyType.BOOLEAN;
        } else {
            type = PropertyType.NULL;
        }
        return type;
    }

    private static JSONValue cleanUpObjectMetadata(JSONObject object) {
        JSONValue result;

        if (object.containsKey(VALUE)) {
            result = cleanUpMetadata(object.get(VALUE));
        } else {
            JSONObject cleanedUpObject = new JSONObject();

            for (String key : object.keySet()) {
                JSONValue cleanedUpElement = cleanUpMetadata(object.get(key));
                cleanedUpObject.put(key, cleanedUpElement);
            }

            result = cleanedUpObject;
        }

        return result;
    }

    private static JSONValue cleanUpArrayMetadata(JSONArray array) {
        JSONArray cleanedUpArray = new JSONArray();
        int size = array.size();

        for (int i = 0; i < size; ++i) {
            JSONValue cleanedUpElement = cleanUpMetadata(array.get(i));
            cleanedUpArray.set(i, cleanedUpElement);
        }

        return cleanedUpArray;
    }
}
