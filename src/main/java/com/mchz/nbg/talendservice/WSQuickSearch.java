
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSQuickSearch complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WSQuickSearch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchAllWords" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maxItems" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skip" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="spellTreshold" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="wsDataClusterPK" type="{http://www.talend.com/mdm}WSDataClusterPK" minOccurs="0"/>
 *         &lt;element name="wsViewPK" type="{http://www.talend.com/mdm}WSViewPK" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSQuickSearch", propOrder = {
    "direction",
    "matchAllWords",
    "maxItems",
    "orderBy",
    "searchedValue",
    "skip",
    "spellTreshold",
    "wsDataClusterPK",
    "wsViewPK"
})
public class WSQuickSearch {

    protected String direction;
    protected boolean matchAllWords;
    protected int maxItems;
    protected String orderBy;
    protected String searchedValue;
    protected int skip;
    protected int spellTreshold;
    protected WSDataClusterPK wsDataClusterPK;
    protected WSViewPK wsViewPK;

    /**
     * 获取direction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置direction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * 获取matchAllWords属性的值。
     * 
     */
    public boolean isMatchAllWords() {
        return matchAllWords;
    }

    /**
     * 设置matchAllWords属性的值。
     * 
     */
    public void setMatchAllWords(boolean value) {
        this.matchAllWords = value;
    }

    /**
     * 获取maxItems属性的值。
     * 
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * 设置maxItems属性的值。
     * 
     */
    public void setMaxItems(int value) {
        this.maxItems = value;
    }

    /**
     * 获取orderBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置orderBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    /**
     * 获取searchedValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchedValue() {
        return searchedValue;
    }

    /**
     * 设置searchedValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchedValue(String value) {
        this.searchedValue = value;
    }

    /**
     * 获取skip属性的值。
     * 
     */
    public int getSkip() {
        return skip;
    }

    /**
     * 设置skip属性的值。
     * 
     */
    public void setSkip(int value) {
        this.skip = value;
    }

    /**
     * 获取spellTreshold属性的值。
     * 
     */
    public int getSpellTreshold() {
        return spellTreshold;
    }

    /**
     * 设置spellTreshold属性的值。
     * 
     */
    public void setSpellTreshold(int value) {
        this.spellTreshold = value;
    }

    /**
     * 获取wsDataClusterPK属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSDataClusterPK }
     *     
     */
    public WSDataClusterPK getWsDataClusterPK() {
        return wsDataClusterPK;
    }

    /**
     * 设置wsDataClusterPK属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSDataClusterPK }
     *     
     */
    public void setWsDataClusterPK(WSDataClusterPK value) {
        this.wsDataClusterPK = value;
    }

    /**
     * 获取wsViewPK属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSViewPK }
     *     
     */
    public WSViewPK getWsViewPK() {
        return wsViewPK;
    }

    /**
     * 设置wsViewPK属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSViewPK }
     *     
     */
    public void setWsViewPK(WSViewPK value) {
        this.wsViewPK = value;
    }

}
