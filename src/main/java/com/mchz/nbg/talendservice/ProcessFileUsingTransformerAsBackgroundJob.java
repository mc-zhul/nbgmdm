
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>processFileUsingTransformerAsBackgroundJob complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="processFileUsingTransformerAsBackgroundJob">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.talend.com/mdm}WSProcessFileUsingTransformerAsBackgroundJob" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processFileUsingTransformerAsBackgroundJob", propOrder = {
    "arg0"
})
public class ProcessFileUsingTransformerAsBackgroundJob {

    protected WSProcessFileUsingTransformerAsBackgroundJob arg0;

    /**
     * ��ȡarg0���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSProcessFileUsingTransformerAsBackgroundJob }
     *     
     */
    public WSProcessFileUsingTransformerAsBackgroundJob getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSProcessFileUsingTransformerAsBackgroundJob }
     *     
     */
    public void setArg0(WSProcessFileUsingTransformerAsBackgroundJob value) {
        this.arg0 = value;
    }

}
