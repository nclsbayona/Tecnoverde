//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.18 at 06:44:09 PM COT 
//


package co.edu.javeriana.ms.calificacion.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proveedor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proveedor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="twitter" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="pagina_web" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="facebook" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="instagram" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proveedor", propOrder = {
    "descripcion",
    "twitter",
    "foto",
    "paginaWeb",
    "facebook",
    "instagram",
    "telefono",
    "nombre",
    "edad"
})
public class Proveedor {

    protected String descripcion;
    @XmlSchemaType(name = "anyURI")
    protected String twitter;
    @XmlSchemaType(name = "anyURI")
    protected String foto;
    @XmlElement(name = "pagina_web")
    @XmlSchemaType(name = "anyURI")
    protected String paginaWeb;
    @XmlSchemaType(name = "anyURI")
    protected String facebook;
    @XmlSchemaType(name = "anyURI")
    protected String instagram;
    protected String telefono;
    protected String nombre;
    protected Integer edad;

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the twitter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * Sets the value of the twitter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwitter(String value) {
        this.twitter = value;
    }

    /**
     * Gets the value of the foto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Sets the value of the foto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoto(String value) {
        this.foto = value;
    }

    /**
     * Gets the value of the paginaWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * Sets the value of the paginaWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaginaWeb(String value) {
        this.paginaWeb = value;
    }

    /**
     * Gets the value of the facebook property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * Sets the value of the facebook property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacebook(String value) {
        this.facebook = value;
    }

    /**
     * Gets the value of the instagram property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * Sets the value of the instagram property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstagram(String value) {
        this.instagram = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the edad property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Sets the value of the edad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEdad(Integer value) {
        this.edad = value;
    }

}
