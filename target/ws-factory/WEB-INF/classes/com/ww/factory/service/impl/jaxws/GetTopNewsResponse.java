
package com.ww.factory.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getTopNewsResponse", namespace = "http://impl.service.factory.ww.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTopNewsResponse", namespace = "http://impl.service.factory.ww.com/")
public class GetTopNewsResponse {

    @XmlElement(name = "return", namespace = "")
    private com.ww.factory.model.TopNews _return;

    /**
     * 
     * @return
     *     returns TopNews
     */
    public com.ww.factory.model.TopNews getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.ww.factory.model.TopNews _return) {
        this._return = _return;
    }

}
