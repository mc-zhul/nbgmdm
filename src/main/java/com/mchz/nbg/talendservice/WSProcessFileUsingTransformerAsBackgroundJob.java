
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSProcessFileUsingTransformerAsBackgroundJob complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSProcessFileUsingTransformerAsBackgroundJob">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOutputDecisionTable" type="{http://www.talend.com/mdm}WSOutputDecisionTable" minOccurs="0"/>
 *         &lt;element name="wsTransformerPK" type="{http://www.talend.com/mdm}WSTransformerPK" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSProcessFileUsingTransformerAsBackgroundJob", propOrder = {
    "contentType",
    "fileName",
    "wsOutputDecisionTable",
    "wsTransformerPK"
})
public class WSProcessFileUsingTransformerAsBackgroundJob {

    protected String contentType;
    protected String fileName;
    protected WSOutputDecisionTable wsOutputDecisionTable;
    protected WSTransformerPK wsTransformerPK;

    /**
     * ��ȡcontentType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * ����contentType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * ��ȡfileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ����fileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * ��ȡwsOutputDecisionTable���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSOutputDecisionTable }
     *     
     */
    public WSOutputDecisionTable getWsOutputDecisionTable() {
        return wsOutputDecisionTable;
    }

    /**
     * ����wsOutputDecisionTable���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSOutputDecisionTable }
     *     
     */
    public void setWsOutputDecisionTable(WSOutputDecisionTable value) {
        this.wsOutputDecisionTable = value;
    }

    /**
     * ��ȡwsTransformerPK���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSTransformerPK }
     *     
     */
    public WSTransformerPK getWsTransformerPK() {
        return wsTransformerPK;
    }

    /**
     * ����wsTransformerPK���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSTransformerPK }
     *     
     */
    public void setWsTransformerPK(WSTransformerPK value) {
        this.wsTransformerPK = value;
    }

}
