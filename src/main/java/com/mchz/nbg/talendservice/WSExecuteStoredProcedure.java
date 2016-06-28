
package com.mchz.nbg.talendservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WSExecuteStoredProcedure complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WSExecuteStoredProcedure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameters" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsDataClusterPK" type="{http://www.talend.com/mdm}WSDataClusterPK" minOccurs="0"/>
 *         &lt;element name="wsStoredProcedurePK" type="{http://www.talend.com/mdm}WSStoredProcedurePK" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSExecuteStoredProcedure", propOrder = {
    "parameters",
    "wsDataClusterPK",
    "wsStoredProcedurePK"
})
public class WSExecuteStoredProcedure {

    @XmlElement(nillable = true)
    protected List<String> parameters;
    protected WSDataClusterPK wsDataClusterPK;
    protected WSStoredProcedurePK wsStoredProcedurePK;

    /**
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<String>();
        }
        return this.parameters;
    }

    /**
     * ��ȡwsDataClusterPK���Ե�ֵ��
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
     * ����wsDataClusterPK���Ե�ֵ��
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
     * ��ȡwsStoredProcedurePK���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSStoredProcedurePK }
     *     
     */
    public WSStoredProcedurePK getWsStoredProcedurePK() {
        return wsStoredProcedurePK;
    }

    /**
     * ����wsStoredProcedurePK���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSStoredProcedurePK }
     *     
     */
    public void setWsStoredProcedurePK(WSStoredProcedurePK value) {
        this.wsStoredProcedurePK = value;
    }

}
