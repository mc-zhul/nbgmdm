
package com.mchz.nbg.talendservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>processBytesUsingTransformerAsBackgroundJob complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="processBytesUsingTransformerAsBackgroundJob">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.talend.com/mdm}WSProcessBytesUsingTransformerAsBackgroundJob" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processBytesUsingTransformerAsBackgroundJob", propOrder = {
    "arg0"
})
public class ProcessBytesUsingTransformerAsBackgroundJob {

    protected WSProcessBytesUsingTransformerAsBackgroundJob arg0;

    /**
     * ��ȡarg0���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WSProcessBytesUsingTransformerAsBackgroundJob }
     *     
     */
    public WSProcessBytesUsingTransformerAsBackgroundJob getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WSProcessBytesUsingTransformerAsBackgroundJob }
     *     
     */
    public void setArg0(WSProcessBytesUsingTransformerAsBackgroundJob value) {
        this.arg0 = value;
    }

}
