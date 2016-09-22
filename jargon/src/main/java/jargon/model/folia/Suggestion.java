//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.21 at 12:15:47 PM CEST 
//


package jargon.model.folia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;element ref="{http://ilk.uvt.nl/folia}relation"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}coreferencechain"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}observation"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}semrole"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}chunk"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}dep"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}entity"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}timesegment"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}sentiment"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}target"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}hd"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}predicate"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}source"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}su"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}statement"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}dependency"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}coreferencelink"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}head"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}div"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}note"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}row"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}tablehead"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}def"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}ref"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}ex"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}part"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}whitespace"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}w"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}event"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}utt"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}label"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}item"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}listitem"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}term"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}entry"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}morpheme"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}quote"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}phoneme"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}speech"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}table"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}p"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}figure"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}list"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}cell"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}text"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}caption"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}s"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}br"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}lemma"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}sense"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}subjectivity"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}lang"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}domain"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}errordetection"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}pos"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}comment"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}correction"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}desc"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}foreign-data"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}metric"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}ph"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}str"/>
 *         &lt;element ref="{http://ilk.uvt.nl/folia}t"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://ilk.uvt.nl/folia}allow_foreign_attributes"/>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *       &lt;attribute name="annotator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="annotatortype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="n" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="auth" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="split" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="merge" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "relationOrCoreferencechainOrObservation"
})
@XmlRootElement(name = "suggestion")
public class Suggestion {

    @XmlElements({
        @XmlElement(name = "relation", type = Relation.class),
        @XmlElement(name = "coreferencechain", type = Coreferencechain.class),
        @XmlElement(name = "observation", type = Observation.class),
        @XmlElement(name = "semrole", type = Semrole.class),
        @XmlElement(name = "chunk", type = Chunk.class),
        @XmlElement(name = "dep", type = Dep.class),
        @XmlElement(name = "entity", type = Entity.class),
        @XmlElement(name = "timesegment", type = Timesegment.class),
        @XmlElement(name = "sentiment", type = Sentiment.class),
        @XmlElement(name = "target", type = Target.class),
        @XmlElement(name = "hd", type = Hd.class),
        @XmlElement(name = "predicate", type = Predicate.class),
        @XmlElement(name = "source", type = Source.class),
        @XmlElement(name = "su", type = Su.class),
        @XmlElement(name = "statement", type = Statement.class),
        @XmlElement(name = "dependency", type = Dependency.class),
        @XmlElement(name = "coreferencelink", type = Coreferencelink.class),
        @XmlElement(name = "head", type = Head.class),
        @XmlElement(name = "div", type = Div.class),
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "row", type = Row.class),
        @XmlElement(name = "tablehead", type = Tablehead.class),
        @XmlElement(name = "def", type = Def.class),
        @XmlElement(name = "ref", type = Ref.class),
        @XmlElement(name = "ex", type = Ex.class),
        @XmlElement(name = "part", type = Part.class),
        @XmlElement(name = "whitespace", type = Whitespace.class),
        @XmlElement(name = "w", type = W.class),
        @XmlElement(name = "event", type = Event.class),
        @XmlElement(name = "utt", type = Utt.class),
        @XmlElement(name = "label", type = Label.class),
        @XmlElement(name = "item", type = Item.class),
        @XmlElement(name = "listitem", type = Listitem.class),
        @XmlElement(name = "term", type = Term.class),
        @XmlElement(name = "entry", type = Entry.class),
        @XmlElement(name = "morpheme", type = Morpheme.class),
        @XmlElement(name = "quote", type = Quote.class),
        @XmlElement(name = "phoneme", type = Phoneme.class),
        @XmlElement(name = "speech", type = Speech.class),
        @XmlElement(name = "table", type = Table.class),
        @XmlElement(name = "p", type = P.class),
        @XmlElement(name = "figure", type = Figure.class),
        @XmlElement(name = "list", type = jargon.model.folia.List.class),
        @XmlElement(name = "cell", type = Cell.class),
        @XmlElement(name = "text", type = Text.class),
        @XmlElement(name = "caption", type = Caption.class),
        @XmlElement(name = "s", type = S.class),
        @XmlElement(name = "br", type = Br.class),
        @XmlElement(name = "lemma", type = Lemma.class),
        @XmlElement(name = "sense", type = Sense.class),
        @XmlElement(name = "subjectivity", type = Subjectivity.class),
        @XmlElement(name = "lang", type = Lang.class),
        @XmlElement(name = "domain", type = Domain.class),
        @XmlElement(name = "errordetection", type = Errordetection.class),
        @XmlElement(name = "pos", type = Pos.class),
        @XmlElement(name = "comment", type = Comment.class),
        @XmlElement(name = "correction", type = Correction.class),
        @XmlElement(name = "desc", type = Desc.class),
        @XmlElement(name = "foreign-data", type = AnyContent.class),
        @XmlElement(name = "metric", type = Metric.class),
        @XmlElement(name = "ph", type = Ph.class),
        @XmlElement(name = "str", type = Str.class),
        @XmlElement(name = "t", type = T.class)
    })
    protected java.util.List<Object> relationOrCoreferencechainOrObservation;
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
    @XmlAttribute(name = "split")
    @XmlSchemaType(name = "anySimpleType")
    protected String split;
    @XmlAttribute(name = "merge")
    @XmlSchemaType(name = "anySimpleType")
    protected String merge;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the relationOrCoreferencechainOrObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationOrCoreferencechainOrObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationOrCoreferencechainOrObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Relation }
     * {@link Coreferencechain }
     * {@link Observation }
     * {@link Semrole }
     * {@link Chunk }
     * {@link Dep }
     * {@link Entity }
     * {@link Timesegment }
     * {@link Sentiment }
     * {@link Target }
     * {@link Hd }
     * {@link Predicate }
     * {@link Source }
     * {@link Su }
     * {@link Statement }
     * {@link Dependency }
     * {@link Coreferencelink }
     * {@link Head }
     * {@link Div }
     * {@link Note }
     * {@link Row }
     * {@link Tablehead }
     * {@link Def }
     * {@link Ref }
     * {@link Ex }
     * {@link Part }
     * {@link Whitespace }
     * {@link W }
     * {@link Event }
     * {@link Utt }
     * {@link Label }
     * {@link Item }
     * {@link Listitem }
     * {@link Term }
     * {@link Entry }
     * {@link Morpheme }
     * {@link Quote }
     * {@link Phoneme }
     * {@link Speech }
     * {@link Table }
     * {@link P }
     * {@link Figure }
     * {@link jargon.model.folia.List }
     * {@link Cell }
     * {@link Text }
     * {@link Caption }
     * {@link S }
     * {@link Br }
     * {@link Lemma }
     * {@link Sense }
     * {@link Subjectivity }
     * {@link Lang }
     * {@link Domain }
     * {@link Errordetection }
     * {@link Pos }
     * {@link Comment }
     * {@link Correction }
     * {@link Desc }
     * {@link AnyContent }
     * {@link Metric }
     * {@link Ph }
     * {@link Str }
     * {@link T }
     * 
     * 
     */
    public java.util.List<Object> getRelationOrCoreferencechainOrObservation() {
        if (relationOrCoreferencechainOrObservation == null) {
            relationOrCoreferencechainOrObservation = new ArrayList<Object>();
        }
        return this.relationOrCoreferencechainOrObservation;
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
     * Gets the value of the split property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplit() {
        return split;
    }

    /**
     * Sets the value of the split property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplit(String value) {
        this.split = value;
    }

    /**
     * Gets the value of the merge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerge() {
        return merge;
    }

    /**
     * Sets the value of the merge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerge(String value) {
        this.merge = value;
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
