//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.21 at 12:15:47 PM CEST 
//


package jargon.model.folia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://ilk.uvt.nl/folia}sentiments"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}entities"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}morphology"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}chunking"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}phonology"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}statements"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}semroles"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}complexalignments"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}timing"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}observations"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}syntax"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}dependencies"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}coreferences"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}alignment"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}alt"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}altlayers"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}comment"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}correction"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}desc"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}feat"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}foreign-data"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}metric"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}part"/>
 *       &lt;/choice>
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
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}type"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}role"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}title"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}label"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}show"/>
 *       &lt;attribute name="auth" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="pagenr" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="linenr" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="newpage" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "content"
})
@XmlRootElement(name = "br")
public class Br {

    @XmlElementRefs({
        @XmlElementRef(name = "feat", namespace = "http://ilk.uvt.nl/folia", type = Feat.class, required = false),
        @XmlElementRef(name = "correction", namespace = "http://ilk.uvt.nl/folia", type = Correction.class, required = false),
        @XmlElementRef(name = "coreferences", namespace = "http://ilk.uvt.nl/folia", type = Coreferences.class, required = false),
        @XmlElementRef(name = "altlayers", namespace = "http://ilk.uvt.nl/folia", type = Altlayers.class, required = false),
        @XmlElementRef(name = "chunking", namespace = "http://ilk.uvt.nl/folia", type = Chunking.class, required = false),
        @XmlElementRef(name = "desc", namespace = "http://ilk.uvt.nl/folia", type = Desc.class, required = false),
        @XmlElementRef(name = "entities", namespace = "http://ilk.uvt.nl/folia", type = Entities.class, required = false),
        @XmlElementRef(name = "dependencies", namespace = "http://ilk.uvt.nl/folia", type = Dependencies.class, required = false),
        @XmlElementRef(name = "alignment", namespace = "http://ilk.uvt.nl/folia", type = Alignment.class, required = false),
        @XmlElementRef(name = "timing", namespace = "http://ilk.uvt.nl/folia", type = Timing.class, required = false),
        @XmlElementRef(name = "foreign-data", namespace = "http://ilk.uvt.nl/folia", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "semroles", namespace = "http://ilk.uvt.nl/folia", type = Semroles.class, required = false),
        @XmlElementRef(name = "part", namespace = "http://ilk.uvt.nl/folia", type = Part.class, required = false),
        @XmlElementRef(name = "morphology", namespace = "http://ilk.uvt.nl/folia", type = Morphology.class, required = false),
        @XmlElementRef(name = "sentiments", namespace = "http://ilk.uvt.nl/folia", type = Sentiments.class, required = false),
        @XmlElementRef(name = "phonology", namespace = "http://ilk.uvt.nl/folia", type = Phonology.class, required = false),
        @XmlElementRef(name = "alt", namespace = "http://ilk.uvt.nl/folia", type = Alt.class, required = false),
        @XmlElementRef(name = "metric", namespace = "http://ilk.uvt.nl/folia", type = Metric.class, required = false),
        @XmlElementRef(name = "statements", namespace = "http://ilk.uvt.nl/folia", type = Statements.class, required = false),
        @XmlElementRef(name = "complexalignments", namespace = "http://ilk.uvt.nl/folia", type = Complexalignments.class, required = false),
        @XmlElementRef(name = "comment", namespace = "http://ilk.uvt.nl/folia", type = Comment.class, required = false),
        @XmlElementRef(name = "observations", namespace = "http://ilk.uvt.nl/folia", type = Observations.class, required = false),
        @XmlElementRef(name = "syntax", namespace = "http://ilk.uvt.nl/folia", type = Syntax.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
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
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String href;
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String type;
    @XmlAttribute(name = "role", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String role;
    @XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String title;
    @XmlAttribute(name = "label", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String label;
    @XmlAttribute(name = "show", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anySimpleType")
    protected String show;
    @XmlAttribute(name = "auth")
    @XmlSchemaType(name = "anySimpleType")
    protected String auth;
    @XmlAttribute(name = "pagenr")
    @XmlSchemaType(name = "anySimpleType")
    protected String pagenr;
    @XmlAttribute(name = "linenr")
    @XmlSchemaType(name = "anySimpleType")
    protected String linenr;
    @XmlAttribute(name = "newpage")
    @XmlSchemaType(name = "anySimpleType")
    protected String newpage;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    protected String idAttribute;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Feat }
     * {@link Correction }
     * {@link Coreferences }
     * {@link Altlayers }
     * {@link Chunking }
     * {@link Desc }
     * {@link Entities }
     * {@link Dependencies }
     * {@link Alignment }
     * {@link Timing }
     * {@link JAXBElement }{@code <}{@link AnyContent }{@code >}
     * {@link Semroles }
     * {@link Part }
     * {@link Morphology }
     * {@link String }
     * {@link Sentiments }
     * {@link Phonology }
     * {@link Alt }
     * {@link Metric }
     * {@link Statements }
     * {@link Complexalignments }
     * {@link Comment }
     * {@link Observations }
     * {@link Syntax }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
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
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShow(String value) {
        this.show = value;
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
     * Gets the value of the pagenr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagenr() {
        return pagenr;
    }

    /**
     * Sets the value of the pagenr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagenr(String value) {
        this.pagenr = value;
    }

    /**
     * Gets the value of the linenr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinenr() {
        return linenr;
    }

    /**
     * Sets the value of the linenr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinenr(String value) {
        this.linenr = value;
    }

    /**
     * Gets the value of the newpage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewpage() {
        return newpage;
    }

    /**
     * Sets the value of the newpage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewpage(String value) {
        this.newpage = value;
    }

    /**
     * Gets the value of the idAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAttribute() {
        return idAttribute;
    }

    /**
     * Sets the value of the idAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAttribute(String value) {
        this.idAttribute = value;
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