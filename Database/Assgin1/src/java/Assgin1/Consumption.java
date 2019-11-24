/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhy
 */
@Entity
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByConsumptionId", query = "SELECT c FROM Consumption c WHERE c.consumptionId = :consumptionId")
    , @NamedQuery(name = "Consumption.findByConsumptionDate", query = "SELECT c FROM Consumption c WHERE c.consumptionDate = :consumptionDate")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")
    , @NamedQuery(name = "Consumption.findByFoodId", query = "SELECT c FROM Consumption c WHERE c.foodId.foodId = :foodId")
    , @NamedQuery(name = "Consumption.findByUserId", query = "SELECT c FROM Consumption c WHERE c.userId.userId = :userId")
    , @NamedQuery(name = "Consumption.findByUserIdANDConsumptionDate", query = "SELECT c FROM Consumption c WHERE c.userId.userId = :userId AND c.consumptionDate = :consumptionDate")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONSUMPTION_ID")
    private Integer consumptionId;
    @Column(name = "CONSUMPTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date consumptionDate;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "FOOD_ID", referencedColumnName = "FOOD_ID")
    @ManyToOne
    private Food foodId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable userId;

    public Consumption() {
    }

    public Consumption(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public UserTable getUserId() {
        return userId;
    }

    public void setUserId(UserTable userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumptionId != null ? consumptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.consumptionId == null && other.consumptionId != null) || (this.consumptionId != null && !this.consumptionId.equals(other.consumptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assgin1.Consumption[ consumptionId=" + consumptionId + " ]";
    }
    
}
