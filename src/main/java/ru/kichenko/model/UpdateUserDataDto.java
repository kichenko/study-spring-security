package ru.kichenko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDataDto {

    private String firstName;

    private String secondName;

    private String email;

}
