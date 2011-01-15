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

import java.util.List;
import java.util.Map;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.impl.Employee;
import org.seasar.dao.tiger.FetchHandler;

/**
 * @author jundu
 * 
 */
@S2Dao(bean = Employee.class)
public interface EmployeeDao {

    public List<Employee> selectAll();

    @Arguments("empno")
    public Employee selectByEmpno(long empno);

    public int fetchAll(FetchHandler<Employee> handler);

    @Arguments("deptno")
    public int fetchByDeptno(int deptno);

    public int fetchEmployeesBySearchCondition(EmployeeSearchCondition dto,
            FetchHandler<Employee> handler);

    public int fetchAllToMap(FetchHandler<Map> handler);

    public int fetchAllEmpno(FetchHandler<Integer> handler);
}
