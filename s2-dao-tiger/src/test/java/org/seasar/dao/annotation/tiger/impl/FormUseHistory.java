package org.seasar.dao.annotation.tiger.impl;

import java.io.Serializable;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

/**
 * WEB ��ʗ��p�����N���X<br>
 * <br>
 * File Information :<br>
 * 	$Header: /cvsroot/seasar/s2-dao/src/test/org/seasar/dao/impl/FormUseHistory.java,v 1.1 2005/01/18 10:42:18 higa Exp $<br>
 * @author ARGO21
 * @version 1.0
 */
@Bean(table="CWEB_FORM_HIST")
public class FormUseHistory implements Serializable {
	//
	// �萔
	//
	
	
	
	
	
	
	//
	// �C���X�^���X�t�B�[���h
	//
	
	/** WEB���[�U�R�[�h */
	private String webUserCode;
	
	/** WEB���ID */
	private String webFormId;
	
	/** �Q�ƃ^�C���X�^���v */
	private java.sql.Timestamp referenceTimestamp;
	
	/** �Q�ƃz�X�gIP */
	private String referenceHostIp;
	
	//
	// �C���X�^���X���\�b�h
	//
	
	/**
	 * WEB���[�U�R�[�h�擾
	 * @return String
	 */
	@Column("W_USER_CD")
	public String getWebUserCode() {
		return this.webUserCode;
	}
	/**
	 * WEB���[�U�R�[�h�ݒ�
	 * @param webUserCode
	 */
	public void setWebUserCode(String webUserCode) {
		this.webUserCode = webUserCode;
	}
	/**
	 * WEB���ID�擾
	 * @return String
	 */
	public String getWebFormId() {
		return this.webFormId;
	}

	/**
	 * WEB���ID�ݒ�
	 * @param webFormId
	 */
	@Column("W_FORM_ID")
	public void setWebFormId(String webFormId) {
		this.webFormId = webFormId;
	}
	/**
	 * �Q�ƃ^�C���X�^���v�擾
	 * @return java.sql.Timestamp
	 */
	public java.sql.Timestamp getReferenceTimestamp() {
		return this.referenceTimestamp;
	}

	/**
	 * �Q�ƃ^�C���X�^���v�ݒ�
	 * @param referenceTimestamp
	 */
	@Column("REF_TIMESTAMP")
	public void setReferenceTimestamp(java.sql.Timestamp referenceTimestamp) {
		this.referenceTimestamp = referenceTimestamp;
	}
	/**
	 * �Q�ƃz�X�gIP�擾
	 * @return String
	 */
	public String getReferenceHostIp() {
		return this.referenceHostIp;
	}

	/**
	 * �Q�ƃz�X�gIP�ݒ�
	 * @param referenceHostIp
	 */
	@Column("REF_HOST_IP")
	public void setReferenceHostIp(String referenceHostIp) {
		this.referenceHostIp = referenceHostIp;
	}
	/**
	 * ������
	 * @return ������
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("webUserCode[").append(this.webUserCode).append("]");
		buffer.append("webFormId[").append(this.webFormId).append("]");
		buffer.append("referenceTimestamp[").append(this.referenceTimestamp).append("]");
		buffer.append("referenceHostIp[").append(this.referenceHostIp).append("]");
		return buffer.toString();
	}
}

