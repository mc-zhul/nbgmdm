
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSPutItemWithReportArray complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSPutItemWithReportArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsPutItem" type="{http://www.talend.com/mdm}WSPutItemWithReport" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSPutItemWithReportArray", propOrder = {
    "wsPutItem"
})
public class WSPutItemWithReportArray {

    @XmlElement(nillable = true)
    protected List<WSPutItemWithReport> wsPutItem;

    /**
     * Gets the value of the wsPutItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsPutItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsPutItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSPutItemWithReport }
     * 
     * 
     */
    public List<WSPutItemWithReport> getWsPutItem() {
        if (wsPutItem == null) {
            wsPutItem = new ArrayList<WSPutItemWithReport>();
        }
        return this.wsPutItem;
    }

}
