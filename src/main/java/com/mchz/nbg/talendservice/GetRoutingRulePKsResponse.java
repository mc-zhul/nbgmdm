
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getRoutingRulePKsResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getRoutingRulePKsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.talend.com/mdm}WSRoutingRulePKArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRoutingRulePKsResponse", propOrder = {
    "_return"
})
public class GetRoutingRulePKsResponse {

    @XmlElement(name = "return")
    protected WSRoutingRulePKArray _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSRoutingRulePKArray }
     *     
     */
    public WSRoutingRulePKArray getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSRoutingRulePKArray }
     *     
     */
    public void setReturn(WSRoutingRulePKArray value) {
        this._return = value;
    }

}
