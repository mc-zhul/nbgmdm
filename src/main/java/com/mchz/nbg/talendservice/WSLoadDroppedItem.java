
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSLoadDroppedItem complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSLoadDroppedItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsDroppedItemPK" type="{http://www.talend.com/mdm}WSDroppedItemPK" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSLoadDroppedItem", propOrder = {
    "wsDroppedItemPK"
})
public class WSLoadDroppedItem {

    protected WSDroppedItemPK wsDroppedItemPK;

    /**
     * ��ȡwsDroppedItemPK���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSDroppedItemPK }
     *     
     */
    public WSDroppedItemPK getWsDroppedItemPK() {
        return wsDroppedItemPK;
    }

    /**
     * ����wsDroppedItemPK���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSDroppedItemPK }
     *     
     */
    public void setWsDroppedItemPK(WSDroppedItemPK value) {
        this.wsDroppedItemPK = value;
    }

}
