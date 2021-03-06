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
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportId", query = "SELECT r FROM Report r WHERE r.reportId = :reportId")
    , @NamedQuery(name = "Report.findByReportDate", query = "SELECT r FROM Report r WHERE r.reportDate = :reportDate")
    , @NamedQuery(name = "Report.findByTotalCaloriesConsumed", query = "SELECT r FROM Report r WHERE r.totalCaloriesConsumed = :totalCaloriesConsumed")
    , @NamedQuery(name = "Report.findByTotalCaloriesBurned", query = "SELECT r FROM Report r WHERE r.totalCaloriesBurned = :totalCaloriesBurned")
    , @NamedQuery(name = "Report.findByTotalStepsTaken", query = "SELECT r FROM Report r WHERE r.totalStepsTaken = :totalStepsTaken")
    , @NamedQuery(name = "Report.findBySetCalorieGoal", query = "SELECT r FROM Report r WHERE r.setCalorieGoal = :setCalorieGoal")
    , @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.userId.userId = :userId")
    , @NamedQuery(name = "Report.findByUserNameANDReportDate", 
            query = "SELECT r FROM Report r WHERE r.userId.name = :name AND r.reportDate = :reportDate")
    , @NamedQuery(name = "Report.findByUserIdANDReportDate", 
            query = "SELECT r FROM Report r WHERE r.userId.userId = :userId AND r.reportDate = :reportDate")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Column(name = "REPORT_DATE")
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    @Column(name = "TOTAL_CALORIES_CONSUMED")
    private Integer totalCaloriesConsumed;
    @Column(name = "TOTAL_CALORIES_BURNED")
    private Integer totalCaloriesBurned;
    @Column(name = "TOTAL_STEPS_TAKEN")
    private Integer totalStepsTaken;
    @Column(name = "SET_CALORIE_GOAL")
    private Integer setCalorieGoal;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable userId;

    public Report() {
    }

    public Report(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getTotalCaloriesConsumed() {
        return totalCaloriesConsumed;
    }

    public void setTotalCaloriesConsumed(Integer totalCaloriesConsumed) {
        this.totalCaloriesConsumed = totalCaloriesConsumed;
    }

    public Integer getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public Integer getTotalStepsTaken() {
        return totalStepsTaken;
    }

    public void setTotalStepsTaken(Integer totalStepsTaken) {
        this.totalStepsTaken = totalStepsTaken;
    }

    public Integer getSetCalorieGoal() {
        return setCalorieGoal;
    }

    public void setSetCalorieGoal(Integer setCalorieGoal) {
        this.setCalorieGoal = setCalorieGoal;
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
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }
    
    //public boolean betweenStartAndEnd(Date date,Date startingDate,Date endingDate) {
    //    if (date.compareTo(endingDate) > 0) {
    //       return false;
    //   } else if (date.compareTo(startingDate) < 0) {
    //        return false;
    //    } else {
    //        return true;
    //    }
    //}

    @Override
    public String toString() {
        return "Assgin1.Report[ reportId=" + reportId + " ]";
    }
    
}
