package org.example.api;

import lombok.Data;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Указывает, что поля с null значениями не будут включены в JSON
public class User {
    private String email;
    private String password;
    private String name;
}