package amir.demo.openhtmltopdfdemo.service;

import amir.demo.openhtmltopdfdemo.model.dto.Medication;
import amir.demo.openhtmltopdfdemo.model.dto.Prescription;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PrescriptionGenerationService {
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    public byte[] generatePrescriptionPdf() {
        // 1. Generate Dummy Data for a large multi-page prescription
        Prescription prescription = createLargeDummyPrescription();

        // 2. Process Thymeleaf template to get final HTML string
        Context context = new Context();
        context.setVariable("prescription", prescription);
        String htmlContent = templateEngine.process("prescription", context);

        // 3. Use OpenHtmlToPdf to convert the HTML string to a PDF
        try (OutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, "");
            builder.useFastMode(); // Optional: improves performance
            builder.toStream(os);
            builder.run();
            return ((ByteArrayOutputStream) os).toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    private Prescription createLargeDummyPrescription() {
        // Create a large prescription with many medications to span multiple pages
        
        // Hospital Information
        String hospitalName = "City General Hospital";
        String hospitalAddress = "123 Medical Center Blvd, Healthcare City, HC 12345";
        String hospitalPhone = "+1-555-0123-4567";
        String hospitalEmail = "contact@citygeneralhospital.com";
        String hospitalLicense = "MED-2025-12345";
        
        // Doctor Information
        String doctorName = "Dr. Sarah Johnson";
        String doctorSpecialization = "Internal Medicine Specialist";
        String doctorLicense = "MD-123456";
        
        // Patient Information
        String patientName = "John Michael Doe";
        String patientId = "PAT-2025-9876";
        int patientAge = 45;
        String patientGender = "Male";
        String patientContact = "+1-555-987-6543";
        String patientAddress = "456 Oak Street, Apartment 3B, Healthville, HV 54321";
        
        // Vital Signs
        String bloodPressure = "120/80 mmHg";
        String heartRate = "72 bpm";
        String temperature = "98.6°F (37°C)";
        String weight = "75 kg (165 lbs)";
        
        // Prescription Date
        LocalDate prescriptionDate = LocalDate.now();
        LocalDate followUpDate = LocalDate.now().plusDays(14);
        
        // Diagnoses - Multiple conditions
        List<String> diagnoses = Arrays.asList(
            "Upper Respiratory Tract Infection (URTI) - Acute",
            "Hypertension - Stage 1 (Controlled)",
            "Type 2 Diabetes Mellitus - Well controlled",
            "Seasonal Allergic Rhinitis",
            "Vitamin D Deficiency"
        );
        
        // Create a large list of medications to force multiple pages
        List<Medication> medications = new ArrayList<>();
        
        // Medication 1
        medications.add(new Medication(
            "Amoxicillin (Antibiotic)",
            "500mg Capsules",
            "3 times daily",
            "Take one capsule three times daily after meals with a full glass of water. Do not skip doses even if you feel better.",
            "7 days (Complete the full course)"
        ));
        
        // Medication 2
        medications.add(new Medication(
            "Paracetamol (Acetaminophen)",
            "500mg Tablets",
            "As needed (Max 4 times daily)",
            "Take one tablet when needed for fever or pain. Do not exceed 4 tablets in 24 hours. Take after meals to avoid stomach upset.",
            "5-7 days or until symptoms subside"
        ));
        
        // Medication 3
        medications.add(new Medication(
            "Cetirizine (Antihistamine)",
            "10mg Tablets",
            "Once daily at bedtime",
            "Take one tablet at bedtime for allergy symptoms. May cause drowsiness - avoid driving or operating heavy machinery after taking.",
            "14 days"
        ));
        
        // Medication 4
        medications.add(new Medication(
            "Amlodipine (Blood Pressure)",
            "5mg Tablets",
            "Once daily in the morning",
            "Take one tablet every morning at the same time, with or without food. This is for blood pressure control. Do not stop suddenly without consulting doctor.",
            "Ongoing (Continue as prescribed)"
        ));
        
        // Medication 5
        medications.add(new Medication(
            "Metformin (Diabetes)",
            "500mg Tablets",
            "Twice daily with meals",
            "Take one tablet with breakfast and one with dinner. This helps control blood sugar. Monitor blood glucose regularly and maintain diet.",
            "Ongoing (Continue as prescribed)"
        ));
        
        // Medication 6
        medications.add(new Medication(
            "Omeprazole (Proton Pump Inhibitor)",
            "20mg Capsules",
            "Once daily before breakfast",
            "Take one capsule 30 minutes before breakfast on an empty stomach. Helps reduce stomach acid and protect stomach lining.",
            "30 days"
        ));
        
        // Medication 7
        medications.add(new Medication(
            "Vitamin D3 (Supplement)",
            "60,000 IU Sachets",
            "Once weekly",
            "Mix the entire sachet content in a glass of milk or water and consume immediately after a meal. Take on the same day each week.",
            "8 weeks (8 sachets total)"
        ));
        
        // Medication 8
        medications.add(new Medication(
            "Aspirin (Antiplatelet)",
            "75mg Tablets",
            "Once daily after dinner",
            "Take one tablet daily after dinner. This helps prevent blood clots. Do not take on empty stomach. Inform doctor if you notice any unusual bleeding.",
            "Ongoing (Continue as prescribed)"
        ));
        
        // Medication 9
        medications.add(new Medication(
            "Salbutamol Inhaler (Bronchodilator)",
            "100 mcg/puff",
            "2 puffs twice daily or as needed",
            "Shake well before use. Breathe out fully, place inhaler in mouth, press and breathe in deeply. Hold breath for 10 seconds. Use spacer if provided.",
            "Ongoing (Refill as needed)"
        ));
        
        // Medication 10
        medications.add(new Medication(
            "Atorvastatin (Cholesterol)",
            "10mg Tablets",
            "Once daily at bedtime",
            "Take one tablet at bedtime. This helps lower cholesterol. Maintain low-fat diet. Report muscle pain or weakness immediately.",
            "Ongoing (Continue as prescribed)"
        ));
        
        // Medication 11 - Additional for multi-page
        medications.add(new Medication(
            "Levothyroxine (Thyroid)",
            "50 mcg Tablets",
            "Once daily on empty stomach",
            "Take one tablet first thing in the morning, 30 minutes before breakfast. Do not take with iron or calcium supplements.",
            "Ongoing (Continue as prescribed)"
        ));
        
        // Medication 12
        medications.add(new Medication(
            "Multivitamin with Minerals",
            "One tablet daily",
            "Once daily with breakfast",
            "Take one tablet with breakfast to support overall health. Contains essential vitamins and minerals for daily nutrition.",
            "Ongoing (Daily supplement)"
        ));
        
        // Medication 13
        medications.add(new Medication(
            "Probiotic Capsules",
            "10 Billion CFU",
            "Once daily",
            "Take one capsule daily with food. Helps maintain healthy gut bacteria, especially important while taking antibiotics.",
            "30 days"
        ));
        
        // Medication 14
        medications.add(new Medication(
            "Zinc Sulfate",
            "20mg Tablets",
            "Once daily after meals",
            "Take one tablet after any main meal. Supports immune system function and wound healing. May cause nausea if taken on empty stomach.",
            "30 days"
        ));
        
        // Medication 15
        medications.add(new Medication(
            "Vitamin C (Ascorbic Acid)",
            "500mg Tablets",
            "Once daily",
            "Take one tablet daily with or after meals. Supports immune system and acts as an antioxidant. Especially helpful during infection recovery.",
            "30 days"
        ));
        
        // Important Notes
        List<String> notes = Arrays.asList(
            "• Take all medications as prescribed, even if you start feeling better. Stopping antibiotics early can lead to antibiotic resistance.",
            "• Monitor your blood pressure daily in the morning and evening. Keep a log to bring to your follow-up appointment.",
            "• Check your blood sugar levels before breakfast and 2 hours after dinner. Target range: 80-130 mg/dL fasting.",
            "• Maintain adequate hydration - drink at least 8 glasses of water per day, especially while taking antibiotics.",
            "• Avoid alcohol consumption while taking antibiotics and during the entire treatment period.",
            "• If you experience severe side effects such as difficulty breathing, severe rash, or swelling, seek immediate medical attention.",
            "• Store all medications in a cool, dry place away from direct sunlight and out of reach of children.",
            "• Do not share your medications with others, even if they have similar symptoms.",
            "• Follow a balanced diet rich in fruits, vegetables, whole grains, and lean proteins. Limit salt, sugar, and saturated fats.",
            "• Engage in moderate physical activity for at least 30 minutes daily, such as brisk walking, as tolerated.",
            "• Get adequate sleep (7-8 hours per night) to support recovery and overall health.",
            "• Practice good hand hygiene to prevent spread of infection to others and avoid crowded places until symptoms resolve.",
            "• Keep all follow-up appointments for monitoring of chronic conditions and medication effectiveness.",
            "• Report any new symptoms or concerns to your healthcare provider promptly. Do not wait until the follow-up appointment.",
            "• If you miss a dose, take it as soon as you remember unless it's almost time for the next dose. Never double the dose."
        );

        return new Prescription(
            hospitalName, hospitalAddress, hospitalPhone, hospitalEmail, hospitalLicense,
            doctorName, doctorSpecialization, doctorLicense,
            patientName, patientId, patientAge, patientGender, patientContact, patientAddress,
            bloodPressure, heartRate, temperature, weight,
            prescriptionDate, diagnoses, medications, notes, followUpDate
        );
    }
}