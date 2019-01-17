package com.integralads.nemo.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportMessageDTO implements Serializable {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ReportProperties implements Serializable {
        private String prop1;
        private String prop2;

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }
    }

    private Long id;
    private String createdOn;
    private String startDate;
    private String endDate;
    private String reportName;
    private String reportPropertiesVersion;
    private ReportProperties reportProperties;
    private Integer limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportPropertiesVersion() {
        return reportPropertiesVersion;
    }

    public void setReportPropertiesVersion(String reportPropertiesVersion) {
        this.reportPropertiesVersion = reportPropertiesVersion;
    }

    public ReportProperties getReportProperties() {
        return reportProperties;
    }

    public void setReportProperties(ReportProperties reportProperties) {
        this.reportProperties = reportProperties;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
