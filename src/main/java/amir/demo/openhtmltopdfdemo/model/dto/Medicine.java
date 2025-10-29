package amir.demo.openhtmltopdfdemo.model.dto;

// Simple data class
public class Medicine {
    public String name;
    public String dosage;
    public String instructions;
    // Constructor, getters, etc.


    public Medicine(String name, String dosage, String instructions) {
        this.name = name;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
