
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSDataClusterPK complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WSDataClusterPK">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDataClusterPK", propOrder = {
    "pk"
})
public class WSDataClusterPK {

    protected String pk;

    /**
     * 获取pk属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPk() {
        return pk;
    }

    /**
     * 设置pk属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPk(String value) {
        this.pk = value;
    }

}
