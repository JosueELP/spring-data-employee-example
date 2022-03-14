package com.spring.data.springdata.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewEmployeeRequest {
    String firstName;
    String lastName;
    Double salary;
}
