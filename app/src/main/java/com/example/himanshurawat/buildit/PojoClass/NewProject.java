package com.example.himanshurawat.buildit.PojoClass;

import java.io.Serializable;

/**
 * Created by himanshurawat on 15/10/17.
 */

public class NewProject implements Serializable {

    private String id;
    private String name;
    private String date;
    private String contractorsKey;
    private String equipmentsKey;
    private String labourersKey;

    public NewProject(){

    }

    public NewProject(String id, String name, String date, String contractorsKey, String equipmentsKey, String labourersKey) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.contractorsKey = contractorsKey;
        this.equipmentsKey = equipmentsKey;
        this.labourersKey = labourersKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContractorsKey() {
        return contractorsKey;
    }

    public void setContractorsKey(String contractorsKey) {
        this.contractorsKey = contractorsKey;
    }

    public String getEquipmentsKey() {
        return equipmentsKey;
    }

    public void setEquipmentsKey(String equipmentsKey) {
        this.equipmentsKey = equipmentsKey;
    }

    public String getLabourersKey() {
        return labourersKey;
    }

    public void setLabourersKey(String labourersKey) {
        this.labourersKey = labourersKey;
    }
}
