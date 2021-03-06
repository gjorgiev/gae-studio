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

package com.arcbees.gaestudio.shared;

public enum PropertyType {
    NUMERIC("java.lang.Long"),
    FLOATING("java.lang.Double"),
    BOOLEAN("java.lang.Boolean"),
    STRING("java.lang.String"),
    DATE("java.util.Date"),
    BLOB("com.google.appengine.api.datastore.Blob"),
    BLOB_KEY("com.google.appengine.api.blobstore.BlobKey"),
    CATEGORY("com.google.appengine.api.datastore.Category"),
    COLLECTION("java.util.Collection"),
    EMAIL("com.google.appengine.api.datastore.Email"),
    EMBEDDED("com.google.appengine.api.datastore.EmbeddedEntity"),
    GEO_PT("com.google.appengine.api.datastore.GeoPt"),
    IM_HANDLE("com.google.appengine.api.datastore.IMHandle"),
    KEY("com.google.appengine.api.datastore.Key"),
    LINK("com.google.appengine.api.datastore.Link"),
    PHONE_NUMBER("com.google.appengine.api.datastore.PhoneNumber"),
    POSTAL_ADDRESS("com.google.appengine.api.datastore.PostalAddress"),
    RATING("com.google.appengine.api.datastore.Rating"),
    SHORT_BLOB("com.google.appengine.api.datastore.ShortBlob"),
    TEXT("com.google.appengine.api.datastore.Text", STRING),
    USER("com.google.appengine.api.users.User"),
    NULL("java.lang.Object");

    private final String mappedClass;
    private final PropertyType representation;

    PropertyType(
            String mappedClass) {
        this(mappedClass, null);
    }

    PropertyType(
            String mappedClass,
            PropertyType representation) {
        this.mappedClass = mappedClass;
        this.representation = representation == null ? this : representation;
    }

    public String getMappedClass() {
        return mappedClass;
    }

    public PropertyType getRepresentation() {
        return representation;
    }
}
