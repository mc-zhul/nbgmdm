
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSPutView complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WSPutView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsView" type="{http://www.talend.com/mdm}WSView" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSPutView", propOrder = {
    "wsView"
})
public class WSPutView {

    protected WSView wsView;

    /**
     * 获取wsView属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSView }
     *     
     */
    public WSView getWsView() {
        return wsView;
    }

    /**
     * 设置wsView属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSView }
     *     
     */
    public void setWsView(WSView value) {
        this.wsView = value;
    }

}
