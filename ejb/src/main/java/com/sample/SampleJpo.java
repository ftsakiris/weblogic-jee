package com.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "ENS")
@Table(name = "ENS")
public class SampleJpo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 18)
    @Column(name = "MRN", length = 18)
    private String mrn;

    @Column(name = "INSTANTIATION_DATE")
    private LocalDateTime instantiationDate;

    @Column(name = "DESCRIPTION", length = 18)
    private String description;

    public SampleJpo() {
    }

    public SampleJpo(String mrn, LocalDateTime instantiationDate, String description) {
        this.mrn = mrn;
        this.instantiationDate = instantiationDate;
        this.description = description;
    }

    public String getMrn() {
        return mrn;
    }

    public LocalDateTime getInstantiationDate() {
        return instantiationDate;
    }

    public String getDescription() {
        return description;
    }
}
