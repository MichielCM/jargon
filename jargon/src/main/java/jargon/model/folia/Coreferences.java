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
 *         &lt;element ref="{http://ilk.uvt.nl/folia}comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}coreferencechain" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}correction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}desc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}foreign-data" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://ilk.uvt.nl/folia}allow_foreign_attributes"/>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *       &lt;attribute name="annotator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="annotatortype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="n" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="auth" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="set" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "comment",
    "coreferencechain",
    "correction",
    "desc",
    "foreignData"
})
@XmlRootElement(name = "coreferences")
public class Coreferences {

    protected List<Comment> comment;
    protected List<Coreferencechain> coreferencechain;
    protected List<Correction> correction;
    protected List<Desc> desc;
    @XmlElement(name = "foreign-data")
    protected List<AnyContent> foreignData;
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;
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
    @XmlAttribute(name = "auth")
    @XmlSchemaType(name = "anySimpleType")
    protected String auth;
    @XmlAttribute(name = "set")
    @XmlSchemaType(name = "anySimpleType")
    protected String set;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the coreferencechain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coreferencechain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoreferencechain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Coreferencechain }
     * 
     * 
     */
    public List<Coreferencechain> getCoreferencechain() {
        if (coreferencechain == null) {
            coreferencechain = new ArrayList<Coreferencechain>();
        }
        return this.coreferencechain;
    }

    /**
     * Gets the value of the correction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorrection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Correction }
     * 
     * 
     */
    public List<Correction> getCorrection() {
        if (correction == null) {
            correction = new ArrayList<Correction>();
        }
        return this.correction;
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
