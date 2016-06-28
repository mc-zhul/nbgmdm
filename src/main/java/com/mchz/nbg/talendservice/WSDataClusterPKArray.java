
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSDataClusterPKArray complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSDataClusterPKArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsDataClusterPKs" type="{http://www.talend.com/mdm}WSDataClusterPK" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDataClusterPKArray", propOrder = {
    "wsDataClusterPKs"
})
public class WSDataClusterPKArray {

    @XmlElement(nillable = true)
    protected List<WSDataClusterPK> wsDataClusterPKs;

    /**
     * Gets the value of the wsDataClusterPKs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsDataClusterPKs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsDataClusterPKs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSDataClusterPK }
     * 
     * 
     */
    public List<WSDataClusterPK> getWsDataClusterPKs() {
        if (wsDataClusterPKs == null) {
            wsDataClusterPKs = new ArrayList<WSDataClusterPK>();
        }
        return this.wsDataClusterPKs;
    }

}
