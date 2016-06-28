
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSDataModelPKArray complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSDataModelPKArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsDataModelPKs" type="{http://www.talend.com/mdm}WSDataModelPK" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDataModelPKArray", propOrder = {
    "wsDataModelPKs"
})
public class WSDataModelPKArray {

    @XmlElement(nillable = true)
    protected List<WSDataModelPK> wsDataModelPKs;

    /**
     * Gets the value of the wsDataModelPKs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsDataModelPKs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsDataModelPKs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSDataModelPK }
     * 
     * 
     */
    public List<WSDataModelPK> getWsDataModelPKs() {
        if (wsDataModelPKs == null) {
            wsDataModelPKs = new ArrayList<WSDataModelPK>();
        }
        return this.wsDataModelPKs;
    }

}
