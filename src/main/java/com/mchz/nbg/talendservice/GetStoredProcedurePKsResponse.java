
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getStoredProcedurePKsResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getStoredProcedurePKsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.talend.com/mdm}WSStoredProcedurePKArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStoredProcedurePKsResponse", propOrder = {
    "_return"
})
public class GetStoredProcedurePKsResponse {

    @XmlElement(name = "return")
    protected WSStoredProcedurePKArray _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSStoredProcedurePKArray }
     *     
     */
    public WSStoredProcedurePKArray getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSStoredProcedurePKArray }
     *     
     */
    public void setReturn(WSStoredProcedurePKArray value) {
        this._return = value;
    }

}
