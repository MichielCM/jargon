//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.27 at 11:47:51 AM CEST 
//


package jargon.model.folia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}aref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}alignment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}dep" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}desc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}feat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}foreign-data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}hd" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}metric" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://ilk.uvt.nl/folia}allow_foreign_attributes"/>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *       &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="set" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="annotator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="annotatortype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="n" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="begintime" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="endtime" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="speaker" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="auth" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;anyAttribute processContents='skip'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "aref",
    "alignment",
    "comment",
    "dep",
    "desc",
    "feat",
    "foreignData",
    "hd",
    "metric"
})
@XmlRootElement(name = "dependency")
public class Dependency {

    protected List<Aref> aref;
    protected List<Alignment> alignment;
    protected List<Comment> comment;
    protected List<Dep> dep;
    protected List<Desc> desc;
    protected List<Feat> feat;
    @XmlElement(name = "foreign-data")
    protected List<AnyContent> foreignData;
    protected List<Hd> hd;
    protected List<Metric> metric;
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;
    @XmlAttribute(name = "class")
    @XmlSchemaType(name = "anySimpleType")
    protected String clazz;
    @XmlAttribute(name = "set")
    @XmlSchemaType(name = "anySimpleType")
    protected String set;
    @XmlAttribute(name = "annotator")
    @XmlSchemaType(name = "anySimpleType")
    protected String annotator;
    @XmlAttribute(name = "annotatortype")
    @XmlSchemaType(name = "anySimpleType")
    protected String annotatortype;
    @XmlAttribute(name = "confidence")
    protected Double confidence;
    @XmlAttribute(name = "n")
    @XmlSchemaType(name = "anySimpleType")
    protected String n;
    @XmlAttribute(name = "datetime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datetime;
    @XmlAttribute(name = "begintime")
    @XmlSchemaType(name = "anySimpleType")
    protected String begintime;
    @XmlAttribute(name = "endtime")
    @XmlSchemaType(name = "anySimpleType")
    protected String endtime;
    @XmlAttribute(name = "src")
    @XmlSchemaType(name = "anySimpleType")
    protected String src;
    @XmlAttribute(name = "speaker")
    @XmlSchemaType(name = "anySimpleType")
    protected String speaker;
    @XmlAttribute(name = "auth")
    @XmlSchemaType(name = "anySimpleType")
    protected String auth;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the aref property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aref property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAref().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aref }
     * 
     * 
     */
    public List<Aref> getAref() {
        if (aref == null) {
            aref = new ArrayList<Aref>();
        }
        return this.aref;
    }

    /**
     * Gets the value of the alignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alignment }
     * 
     * 
     */
    public List<Alignment> getAlignment() {
        if (alignment == null) {
            alignment = new ArrayList<Alignment>();
        }
        return this.alignment;
    }

    /**
     * Gets the value of the comment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comment }
     * 
     * 
     */
    public List<Comment> getComment() {
        if (comment == null) {
            comment = new ArrayList<Comment>();
        }
        return this.comment;
    }

    /**
     * Gets the value of the dep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dep }
     * 
     * 
     */
    public List<Dep> getDep() {
        if (dep == null) {
            dep = new ArrayList<Dep>();
        }
        return this.dep;
    }

    /**
     * Gets the value of the desc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the desc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Desc }
     * 
     * 
     */
    public List<Desc> getDesc() {
        if (desc == null) {
            desc = new ArrayList<Desc>();
        }
        return this.desc;
    }

    /**
     * Gets the value of the feat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the feat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Feat }
     * 
     * 
     */
    public List<Feat> getFeat() {
        if (feat == null) {
            feat = new ArrayList<Feat>();
        }
        return this.feat;
    }

    /**
     * Gets the value of the foreignData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the foreignData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForeignData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyContent }
     * 
     * 
     */
    public List<AnyContent> getForeignData() {
        if (foreignData == null) {
            foreignData = new ArrayList<AnyContent>();
        }
        return this.foreignData;
    }

    /**
     * Gets the value of the hd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hd }
     * 
     * 
     */
    public List<Hd> getHd() {
        if (hd == null) {
            hd = new ArrayList<Hd>();
        }
        return this.hd;
    }

    /**
     * Gets the value of the metric property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metric property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetric().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Metric }
     * 
     * 
     */
    public List<Metric> getMetric() {
        if (metric == null) {
            metric = new ArrayList<Metric>();
        }
        return this.metric;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the set property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSet() {
        return set;
    }

    /**
     * Sets the value of the set property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSet(String value) {
        this.set = value;
    }

    /**
     * Gets the value of the annotator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnotator() {
        return annotator;
    }

    /**
     * Sets the value of the annotator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnotator(String value) {
        this.annotator = value;
    }

    /**
     * Gets the value of the annotatortype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnotatortype() {
        return annotatortype;
    }

    /**
     * Sets the value of the annotatortype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnotatortype(String value) {
        this.annotatortype = value;
    }

    /**
     * Gets the value of the confidence property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * Sets the value of the confidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setConfidence(Double value) {
        this.confidence = value;
    }

    /**
     * Gets the value of the n property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getN() {
        return n;
    }

    /**
     * Sets the value of the n property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setN(String value) {
        this.n = value;
    }

    /**
     * Gets the value of the datetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatetime() {
        return datetime;
    }

    /**
     * Sets the value of the datetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatetime(XMLGregorianCalendar value) {
        this.datetime = value;
    }

    /**
     * Gets the value of the begintime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegintime() {
        return begintime;
    }

    /**
     * Sets the value of the begintime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegintime(String value) {
        this.begintime = value;
    }

    /**
     * Gets the value of the endtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * Sets the value of the endtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndtime(String value) {
        this.endtime = value;
    }

    /**
     * Gets the value of the src property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Gets the value of the speaker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Sets the value of the speaker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpeaker(String value) {
        this.speaker = value;
    }

    /**
     * Gets the value of the auth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuth() {
        return auth;
    }

    /**
     * Sets the value of the auth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuth(String value) {
        this.auth = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
