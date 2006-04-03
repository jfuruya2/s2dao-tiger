/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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

import java.io.Serializable;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="DEPT2")
public class Department2 implements Serializable {

	private int deptno;
    private String dname;
    private boolean active;
        
	/**
	 * @return Returns the deptno.
	 */
	public int getDeptno() {
		return deptno;
	}
	/**
	 * @param deptno The deptno to set.
	 */
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	/**
	 * @return Returns the dname.
	 */
	public String getDname() {
		return dname;
	}
	/**
	 * @param dname The dname to set.
	 */
	public void setDname(String dname) {
		this.dname = dname;
	}
	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	buf.append(deptno).append(", ");
		buf.append(dname);
    	return buf.toString();
    }
}
