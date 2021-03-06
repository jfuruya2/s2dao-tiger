/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
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

import org.seasar.dao.impl.AbstractObjectResultSetHandler;
import org.seasar.dao.tiger.FetchHandler;
import org.seasar.extension.jdbc.ValueType;

/**
 * @author jundu
 * 
 */
public class FetchObjectResultSetHandler extends AbstractObjectResultSetHandler {

    @SuppressWarnings("unchecked")
    protected FetchHandler fetchHandler;

    /**
     * @param clazz
     */
    public FetchObjectResultSetHandler(Class<?> clazz,
            FetchHandler<?> fetchHandler) {
        super(clazz);
        this.fetchHandler = fetchHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.seasar.extension.jdbc.ResultSetHandler#handle(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public Object handle(ResultSet rs) throws SQLException {
        ValueType valueType = getValueType(rs);
        int count = 0;
        while (rs.next()) {
            count++;
            if (!fetchHandler.execute(valueType.getValue(rs, 1))) {
                break;
            }
        }
        return Integer.valueOf(count);
    }

}
