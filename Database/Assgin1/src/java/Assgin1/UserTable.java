/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhy
 */
@Entity
@Table(name = "USER_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTable.findAll", query = "SELECT u FROM UserTable u")
    , @NamedQuery(name = "UserTable.findByUserId", query = "SELECT u FROM UserTable u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserTable.findByName", query = "SELECT u FROM UserTable u WHERE u.name = :name")
    , @NamedQuery(name = "UserTable.findBySurname", query = "SELECT u FROM UserTable u WHERE u.surname = :surname")
    , @NamedQuery(name = "UserTable.findByEmail", query = "SELECT u FROM UserTable u WHERE u.email = :email")
    , @NamedQuery(name = "UserTable.findByDob", query = "SELECT u FROM UserTable u WHERE u.dob = :dob")
    , @NamedQuery(name = "UserTable.findByHeight", query = "SELECT u FROM UserTable u WHERE u.height = :height")
    , @NamedQuery(name = "UserTable.findByWeight", query = "SELECT u FROM UserTable u WHERE u.weight = :weight")
    , @NamedQuery(name = "UserTable.findByGender", query = "SELECT u FROM UserTable u WHERE u.gender = :gender")
    , @NamedQuery(name = "UserTable.findByAddress", query = "SELECT u FROM UserTable u WHERE u.address = :address")
    , @NamedQuery(name = "UserTable.findByPostcode", query = "SELECT u FROM UserTable u WHERE u.postcode = :postcode")
    , @NamedQuery(name = "UserTable.findByLevelOfActivity", query = "SELECT u FROM UserTable u WHERE u.levelOfActivity = :levelOfActivity")
    , @NamedQuery(name = "UserTable.findByStepPerMile", query = "SELECT u FROM UserTable u WHERE u.stepPerMile = :stepPerMile")})
public class UserTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "SURNAME")
    private String surname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Size(max = 6)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "POSTCODE")
    private Integer postcode;
    @Column(name = "LEVEL_OF_ACTIVITY")
    private Integer levelOfActivity;
    @Column(name = "STEP_PER_MILE")
    private Integer stepPerMile;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Credential credential;
    @OneToMany(mappedBy = "userId")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Consumption> consumptionCollection;

    public UserTable() {
    }

    public UserTable(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(Integer levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public Integer getStepPerMile() {
        return stepPerMile;
    }

    public void setStepPerMile(Integer stepPerMile) {
        this.stepPerMile = stepPerMile;
    }

    @XmlTransient
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assgin1.UserTable[ userId=" + userId + " ]";
    }
    
}
