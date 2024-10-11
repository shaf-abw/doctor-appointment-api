package com.abw.doctorAppointment.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    Booked,
    Pending,
    Cancelled
}
