
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSGetItemPKsByFullCriteria complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WSGetItemPKsByFullCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="useFTSearch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wsGetItemPKsByCriteria" type="{http://www.talend.com/mdm}WSGetItemPKsByCriteria" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSGetItemPKsByFullCriteria", propOrder = {
    "useFTSearch",
    "wsGetItemPKsByCriteria"
})
public class WSGetItemPKsByFullCriteria {

    protected boolean useFTSearch;
    protected WSGetItemPKsByCriteria wsGetItemPKsByCriteria;

    /**
     * 获取useFTSearch属性的值。
     * 
     */
    public boolean isUseFTSearch() {
        return useFTSearch;
    }

    /**
     * 设置useFTSearch属性的值。
     * 
     */
    public void setUseFTSearch(boolean value) {
        this.useFTSearch = value;
    }

    /**
     * 获取wsGetItemPKsByCriteria属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSGetItemPKsByCriteria }
     *     
     */
    public WSGetItemPKsByCriteria getWsGetItemPKsByCriteria() {
        return wsGetItemPKsByCriteria;
    }

    /**
     * 设置wsGetItemPKsByCriteria属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSGetItemPKsByCriteria }
     *     
     */
    public void setWsGetItemPKsByCriteria(WSGetItemPKsByCriteria value) {
        this.wsGetItemPKsByCriteria = value;
    }

}
