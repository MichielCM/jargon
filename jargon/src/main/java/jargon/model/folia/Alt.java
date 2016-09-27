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
 *         &lt;element ref="{http://ilk.uvt.nl/folia}lemma" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}sense" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}subjectivity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}lang" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}domain" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}errordetection" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}pos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}correction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}desc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}foreign-data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}morphology" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}phonology" maxOccurs="unbounded" minOccurs="0"/>
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
    "lemma",
    "sense",
    "subjectivity",
    "lang",
    "domain",
    "errordetection",
    "pos",
    "comment",
    "correction",
    "desc",
    "foreignData",
    "morphology",
    "phonology"
})
@XmlRootElement(name = "alt")
public class Alt {

    protected List<Lemma> lemma;
    protected List<Sense> sense;
    protected List<Subjectivity> subjectivity;
    protected List<Lang> lang;
    protected List<Domain> domain;
    protected List<Errordetection> errordetection;
    protected List<Pos> pos;
    protected List<Comment> comment;
    protected List<Correction> correction;
    protected List<Desc> desc;
    @XmlElement(name = "foreign-data")
    protected List<AnyContent> foreignData;
    protected List<Morphology> morphology;
    protected List<Phonology> phonology;
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
     * Gets the value of the lemma property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lemma property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLemma().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lemma }
     * 
     * 
     */
    public List<Lemma> getLemma() {
        if (lemma == null) {
            lemma = new ArrayList<Lemma>();
        }
        return this.lemma;
    }

    /**
     * Gets the value of the sense property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sense property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSense().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sense }
     * 
     * 
     */
    public List<Sense> getSense() {
        if (sense == null) {
            sense = new ArrayList<Sense>();
        }
        return this.sense;
    }

    /**
     * Gets the value of the subjectivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subjectivity }
     * 
     * 
     */
    public List<Subjectivity> getSubjectivity() {
        if (subjectivity == null) {
            subjectivity = new ArrayList<Subjectivity>();
        }
        return this.subjectivity;
    }

    /**
     * Gets the value of the lang property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lang property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLang().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lang }
     * 
     * 
     */
    public List<Lang> getLang() {
        if (lang == null) {
            lang = new ArrayList<Lang>();
        }
        return this.lang;
    }

    /**
     * Gets the value of the domain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Domain }
     * 
     * 
     */
    public List<Domain> getDomain() {
        if (domain == null) {
            domain = new ArrayList<Domain>();
        }
        return this.domain;
    }

    /**
     * Gets the value of the errordetection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errordetection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrordetection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Errordetection }
     * 
     * 
     */
    public List<Errordetection> getErrordetection() {
        if (errordetection == null) {
            errordetection = new ArrayList<Errordetection>();
        }
        return this.errordetection;
    }

    /**
     * Gets the value of the pos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pos }
     * 
     * 
     */
    public List<Pos> getPos() {
        if (pos == null) {
            pos = new ArrayList<Pos>();
        }
        return this.pos;
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
     * Gets the value of the morphology property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the morphology property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMorphology().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Morphology }
     * 
     * 
     */
    public List<Morphology> getMorphology() {
        if (morphology == null) {
            morphology = new ArrayList<Morphology>();
        }
        return this.morphology;
    }

    /**
     * Gets the value of the phonology property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phonology property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhonology().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Phonology }
     * 
     * 
     */
    public List<Phonology> getPhonology() {
        if (phonology == null) {
            phonology = new ArrayList<Phonology>();
        }
        return this.phonology;
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
