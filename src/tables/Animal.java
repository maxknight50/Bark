/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

/**
 *
 * @author wkowa
 */
public class Animal {
    public int animalID;
    public String name;
    public String species;
    public int age;
    public String medicalHistory;
    public String feedingNeeds;
    public String vetHistory;
    public int eventID;
    public int volID;
    
    public Animal(int id, String name, String species, int age, String medical, String feeding, String vet, int eventid, int volid) {
        this.animalID = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.medicalHistory = medical;
        this.feedingNeeds = feeding;
        this.vetHistory = vet;
        this.eventID = eventid;
        this.volID = volid;
    }
    
    public int getAnimalID() {
        return animalID;
    }
    
    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getMedicalHistory() {
        return medicalHistory;
    }
    
    public void setMedicalHistory(String medical) {
        this.medicalHistory = medical;
    }
    
    public String getFeedingNeeds() {
        return feedingNeeds;
    }
    
    public void setFeedingNeeds(String feeding) {
        this.feedingNeeds = feeding;
    }
    
    public String getVetHistory() {
        return vetHistory;
    }
    
    public void setVetHistory(String vet) {
        this.vetHistory = vet;
    }
    
    public int getEventID() {
        return eventID;
    }
    
    public void setEventID(int event) {
        this.eventID = event;
    }
    
    public int getVolID() {
        return volID;
    }
    
    public void setVolID(int vol) {
        this.volID = vol;
    }
}
    
