/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.dao.annotation.tiger.impl;

import org.seasar.dao.DaoMetaDataFactory;
import org.seasar.dao.impl.AbstractDao;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * 
 * @author azusa
 * 
 */
public class DaoAnnotationReaderImplMixTest extends
        AbstractDaoAnnotationReaderImplTest {

    @Override
    protected void setUp() throws Exception {
        clazz = AbstractAaaDaoImpl.class;
        BeanDesc daoDesc = BeanDescFactory.getBeanDesc(clazz);
        annotationReader = new DaoAnnotationReaderImpl(daoDesc);
        aaaClazz = Aaa.class;
        daoClazz = AaaDao.class;
    }

    public static interface AaaDao {

        public static final Class BEAN = Aaa.class;

        public static final String getAaaById1_ARGS = "aaa1,aaa2";

        public Aaa getAaaById1(int id);

        public static final String getAaaById2_QUERY = "A > B";

        public Aaa getAaaById2(int id);

        public static final String getAaaById3_SQL = "SELECT * FROM AAA";

        public Aaa getAaaById3(int id);

        public static final String createAaa1_NO_PERSISTENT_PROPS = "abc";

        public Aaa createAaa1(Aaa aaa);

        public static final String createAaa2_PERSISTENT_PROPS = "def";

        public Aaa createAaa2(Aaa aaa);

    }

    public static interface Aaa2Dao extends AaaDao {
    }

    public static abstract class AbstractAaaDaoImpl extends AbstractDao
            implements Aaa2Dao {

        public AbstractAaaDaoImpl(DaoMetaDataFactory daoMetaDataFactory) {
            super(daoMetaDataFactory);
        }

    }

    public static class Aaa {
    }

}