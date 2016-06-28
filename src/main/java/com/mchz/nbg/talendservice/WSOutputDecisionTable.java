
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSOutputDecisionTable complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSOutputDecisionTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="decisions" type="{http://www.talend.com/mdm}WSProcessBytesUsingTransformerWsOutputDecisionTableDecisions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSOutputDecisionTable", propOrder = {
    "decisions"
})
public class WSOutputDecisionTable {

    @XmlElement(nillable = true)
    protected List<WSProcessBytesUsingTransformerWsOutputDecisionTableDecisions> decisions;

    /**
     * Gets the value of the decisions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the decisions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDecisions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSProcessBytesUsingTransformerWsOutputDecisionTableDecisions }
     * 
     * 
     */
    public List<WSProcessBytesUsingTransformerWsOutputDecisionTableDecisions> getDecisions() {
        if (decisions == null) {
            decisions = new ArrayList<WSProcessBytesUsingTransformerWsOutputDecisionTableDecisions>();
        }
        return this.decisions;
    }

}
