/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaremanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import healthcaremanagement.ResourceNotFound;
import healthcaremanagement.entity.Patient;
import healthcaremanagement.repository.PatientRepository;

/**
 *
 * @author fatem
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class PatientController {

    @Autowired
    private PatientRepository pr;

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return pr.findAll();
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return pr.save(patient);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Optional<Patient>> getStudentById(@PathVariable int id) {
        Optional<Patient> patient = pr.findById(id);
        return ResponseEntity.ok(patient);

    }
    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient (@PathVariable int id, @RequestBody Patient patientDetails){
    Patient patient = pr.findById(id).orElseThrow(()-> new ResourceNotFound("id not available: " + id ));
   patient.setPatientName(patientDetails.getPatientName());
    patient.setGender(patientDetails.getGender());
    patient.setDob(patientDetails.getDob());
    patient.setPhoneNumber(patientDetails.getPhoneNumber());
    patient.setEmail(patientDetails.getEmail());
    patient.setAddress(patientDetails.getAddress());
    Patient updatedPatient = pr.save(patient);
    return ResponseEntity.ok(updatedPatient);
    }
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient (@PathVariable int id){
    	Patient patient = pr.findById(id).orElseThrow();
    	pr.delete(patient);
    	Map<String,Boolean> response = new HashMap<>();
    	response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
    	
    	
    }
}
