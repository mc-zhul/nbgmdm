
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getTransformerPluginV2DetailsResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getTransformerPluginV2DetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.talend.com/mdm}WSTransformerPluginV2Details" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTransformerPluginV2DetailsResponse", propOrder = {
    "_return"
})
public class GetTransformerPluginV2DetailsResponse {

    @XmlElement(name = "return")
    protected WSTransformerPluginV2Details _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSTransformerPluginV2Details }
     *     
     */
    public WSTransformerPluginV2Details getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSTransformerPluginV2Details }
     *     
     */
    public void setReturn(WSTransformerPluginV2Details value) {
        this._return = value;
    }

}
