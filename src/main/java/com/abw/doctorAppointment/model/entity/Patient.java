package com.abw.doctorAppointment.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Patient extends AbstractMappedEntity implements Serializable {

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

    @NotBlank(message = "Gender must not be blank")
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotNull(message = "Age must not be blank")
    @Column(name = "age", nullable = false)
    private int age;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email(message = "Input must be in Email format")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^\\+84[0-9]{9,10}$|^0[0-9]{9,10}$", message = "The mobile number is not in the correct format")
    @Size(min = 10, max = 11, message = "mobile number must be between 10 and 11 characters")
    @Column(name = "mobile", unique = true)
    private String mobile;

}
