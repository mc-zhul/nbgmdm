
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSItem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WSItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conceptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataModelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ids" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="insertionTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsDataClusterPK" type="{http://www.talend.com/mdm}WSDataClusterPK" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSItem", propOrder = {
    "conceptName",
    "content",
    "dataModelName",
    "ids",
    "insertionTime",
    "taskId",
    "wsDataClusterPK"
})
public class WSItem {

    protected String conceptName;
    protected String content;
    protected String dataModelName;
    @XmlElement(nillable = true)
    protected List<String> ids;
    protected long insertionTime;
    protected String taskId;
    protected WSDataClusterPK wsDataClusterPK;

    /**
     * 获取conceptName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptName() {
        return conceptName;
    }

    /**
     * 设置conceptName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptName(String value) {
        this.conceptName = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获取dataModelName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelName() {
        return dataModelName;
    }

    /**
     * 设置dataModelName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelName(String value) {
        this.dataModelName = value;
    }

    /**
     * Gets the value of the ids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIds() {
        if (ids == null) {
            ids = new ArrayList<String>();
        }
        return this.ids;
    }

    /**
     * 获取insertionTime属性的值。
     * 
     */
    public long getInsertionTime() {
        return insertionTime;
    }

    /**
     * 设置insertionTime属性的值。
     * 
     */
    public void setInsertionTime(long value) {
        this.insertionTime = value;
    }

    /**
     * 获取taskId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置taskId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskId(String value) {
        this.taskId = value;
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

}
