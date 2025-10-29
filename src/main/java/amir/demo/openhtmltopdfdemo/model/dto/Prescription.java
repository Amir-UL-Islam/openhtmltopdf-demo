package amir.demo.openhtmltopdfdemo.model.dto;

import java.time.LocalDate;
import java.util.List;

public class Prescription {
    // Hospital Information
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalPhone;
    private String hospitalEmail;
    private String hospitalLicense;
    
    // Doctor Information
    private String doctorName;
    private String doctorSpecialization;
    private String doctorLicense;
    
    // Patient Information
    private String patientName;
    private String patientId;
    private int patientAge;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    
    // Vital Signs
    private String bloodPressure;
    private String heartRate;
    private String temperature;
    private String weight;
    
    // Prescription Details
    private LocalDate prescriptionDate;
    private List<String> diagnoses;
    private List<Medication> medications;
    private List<String> notes;
    private LocalDate followUpDate;

    // Constructors
    public Prescription() {
    }

    public Prescription(String hospitalName, String hospitalAddress, String hospitalPhone, 
                       String hospitalEmail, String hospitalLicense, String doctorName, 
                       String doctorSpecialization, String doctorLicense, String patientName, 
                       String patientId, int patientAge, String patientGender, 
                       String patientContact, String patientAddress, String bloodPressure, 
                       String heartRate, String temperature, String weight, 
                       LocalDate prescriptionDate, List<String> diagnoses, 
                       List<Medication> medications, List<String> notes, LocalDate followUpDate) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalPhone = hospitalPhone;
        this.hospitalEmail = hospitalEmail;
        this.hospitalLicense = hospitalLicense;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorLicense = doctorLicense;
        this.patientName = patientName;
        this.patientId = patientId;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientContact = patientContact;
        this.patientAddress = patientAddress;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.weight = weight;
        this.prescriptionDate = prescriptionDate;
        this.diagnoses = diagnoses;
        this.medications = medications;
        this.notes = notes;
        this.followUpDate = followUpDate;
    }

    // Getters and Setters
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getHospitalEmail() {
        return hospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.hospitalEmail = hospitalEmail;
    }

    public String getHospitalLicense() {
        return hospitalLicense;
    }

    public void setHospitalLicense(String hospitalLicense) {
        this.hospitalLicense = hospitalLicense;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorLicense() {
        return doctorLicense;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }
}