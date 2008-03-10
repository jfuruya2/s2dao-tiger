/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.dao.tiger.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.seasar.dao.BeanMetaData;
import org.seasar.dao.RelationPropertyType;
import org.seasar.dao.RelationRowCreator;
import org.seasar.dao.RowCreator;
import org.seasar.dao.impl.AbstractBeanMetaDataResultSetHandler;
import org.seasar.dao.impl.RelationKey;
import org.seasar.dao.impl.RelationRowCache;
import org.seasar.dao.tiger.FetchHandler;
import org.seasar.extension.jdbc.PropertyType;
import org.seasar.extension.jdbc.ValueType;
import org.seasar.framework.beans.PropertyDesc;

/**
 * @author jundu
 * 
 */
public class FetchBeanMetaDataResultSetHandler extends
        AbstractBeanMetaDataResultSetHandler {

    @SuppressWarnings("unchecked")
    protected FetchHandler fetchHandler;

    public FetchBeanMetaDataResultSetHandler(BeanMetaData beanMetaData,
            RowCreator rowCreator, RelationRowCreator relationRowCreator,
            FetchHandler<?> fetchHandler) {
        super(beanMetaData, rowCreator, relationRowCreator);
        this.fetchHandler = fetchHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.seasar.extension.jdbc.ResultSetHandler#handle(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public Object handle(ResultSet rs) throws SQLException {
        // Set<String(columnName)>
        final Set columnNames = createColumnNames(rs.getMetaData());

        // Map<String(columnName), PropertyType>
        Map propertyCache = null;// [DAO-118] (2007/08/26)

        // Map<String(relationNoSuffix), Map<String(columnName), PropertyType>>
        Map relationPropertyCache = null;// [DAO-118] (2007/08/25)

        final int relSize = getBeanMetaData().getRelationPropertyTypeSize();
        final RelationRowCache relRowCache = new RelationRowCache(relSize);

        int count = 0;
        while (rs.next()) {
            // Lazy initialization because if the result is zero, the cache is
            // unused.
            if (propertyCache == null) {
                propertyCache = createPropertyCache(columnNames);
            }
            if (relationPropertyCache == null) {
                relationPropertyCache = createRelationPropertyCache(columnNames);
            }

            // Create row instance of base table by row property cache.
            final Object row = createRow(rs, propertyCache);

            for (int i = 0; i < relSize; ++i) {
                RelationPropertyType rpt = getBeanMetaData()
                        .getRelationPropertyType(i);
                if (rpt == null) {
                    continue;
                }
                Object relationRow = null;
                Map relKeyValues = new HashMap();
                // TODO 1レコード目でnullが返るなら、2レコード目以降は不要では?
                RelationKey relKey = createRelationKey(rs, rpt, columnNames,
                        relKeyValues);
                if (relKey != null) {
                    relationRow = relRowCache.getRelationRow(i, relKey);
                    if (relationRow == null) {
                        relationRow = createRelationRow(rs, rpt, columnNames,
                                relKeyValues, relationPropertyCache);
                        relRowCache.addRelationRow(i, relKey, relationRow);
                    }
                }
                if (relationRow != null) {
                    PropertyDesc pd = rpt.getPropertyDesc();
                    pd.setValue(row, relationRow);
                    postCreateRow(relationRow);
                }
            }
            postCreateRow(row);
            count++;
            if (!fetchHandler.execute(row)) {
                break;
            }
        }
        return Integer.valueOf(count);
    }

    @SuppressWarnings("unchecked")
    protected RelationKey createRelationKey(ResultSet rs,
            RelationPropertyType rpt, Set columnNames, Map relKeyValues)
            throws SQLException {

        List keyList = new ArrayList();
        BeanMetaData bmd = rpt.getBeanMetaData();
        for (int i = 0; i < rpt.getKeySize(); ++i) {
            /*
             * PropertyType pt = bmd
             * .getPropertyTypeByColumnName(rpt.getYourKey(i)); ValueType
             * valueType = pt.getValueType(); String columnName =
             * pt.getColumnName() + "_" + rpt.getRelationNo();
             */
            ValueType valueType = null;
            String columnName = rpt.getMyKey(i);
            if (columnNames.contains(columnName)) {
                PropertyType pt = getBeanMetaData()
                        .getPropertyTypeByColumnName(columnName);
                valueType = pt.getValueType();
            } else {
                PropertyType pt = bmd.getPropertyTypeByColumnName(rpt
                        .getYourKey(i));
                columnName = pt.getColumnName() + "_" + rpt.getRelationNo();
                if (columnNames.contains(columnName)) {
                    valueType = pt.getValueType();
                } else {
                    return null;
                }
            }
            Object value = valueType.getValue(rs, columnName);
            if (value == null) {
                return null;
            }
            relKeyValues.put(columnName, value);
            keyList.add(value);
        }
        if (keyList.size() > 0) {
            Object[] keys = keyList.toArray();
            return new RelationKey(keys);
        } else {
            return null;
        }
    }

}
