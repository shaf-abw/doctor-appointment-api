package com.abw.doctorAppointment.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "clinic")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Clinic extends AbstractMappedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "address1 must not be blank")
    @Size(min = 3, max = 100, message = "address1 must be between 3 and 100 characters")
    @Column(name = "address1")
    private String address1;

    @Size(min = 3, max = 100, message = "address2 must be between 3 and 100 characters")
    @Column(name = "address2", nullable = true)
    private String address2;

    @NotBlank(message = "city must not be blank")
    @Size(min = 3, max = 100, message = "city must be between 3 and 100 characters")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "state must not be blank")
    @Size(min = 3, max = 100, message = "state must be between 3 and 100 characters")
    @Column(name = "state")
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    private Set<AppointmentTime> appointmentTimes;

}
