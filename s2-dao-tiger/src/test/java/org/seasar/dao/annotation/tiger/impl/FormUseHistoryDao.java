package org.seasar.dao.annotation.tiger.impl;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

/**
 * WEB ��ʗ��p����Dao�N���X<br>
 * <br>
 * File Information :<br>
 * 	$Header: /cvsroot/seasar/s2-dao/src/test/org/seasar/dao/impl/FormUseHistoryDao.java,v 1.1 2005/01/18 10:42:18 higa Exp $<br>
 *
 * @author ARGO21
 * @version 1.0
 */
@S2Dao(bean=FormUseHistory.class)
public interface FormUseHistoryDao {
	
	//
	// �C���X�^���X���\�b�h
	//
	
	/**
	 * �C���T�[�g 
	 * @param formUseHistory WEB ��ʗ��p����
	 * @return �o�^������
	 */
	int insert(FormUseHistory formUseHistory);
	
	/**
	 * �G���e�B�e�B�擾
	 * @param webUserCode
	 * @param webFormId
	 * @return WEB ��ʗ��p����
	 */
	@Arguments({"W_USER_CD","W_FORM_ID"})
	FormUseHistory getEntity(String webUserCode,String webFormId);
	
	/**
	 * ���X�g�擾
	 * @return WEB ��ʗ��p�����̃��X�g
	 */
	List getList();
	
	//
	// �ǉ����\�b�h
	//
	
}

