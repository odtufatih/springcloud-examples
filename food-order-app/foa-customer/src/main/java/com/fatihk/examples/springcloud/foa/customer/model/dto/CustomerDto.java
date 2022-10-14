package com.fatihk.examples.springcloud.foa.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatihk.examples.springcloud.foa.customer.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //this is important, this makes id field to be visible and used only in get responses, we hide it for create/update(post/put) endpoints in swagger api documentation
    private String id;

    @Email
    private String username;

    @ValidPassword
    private String password;

    @Size(min = 1, max = 50)
    private String firstName;

    @Size(min = 1, max = 50)
    private String lastName;

    private LocalDate birthDay;

    @Size(min=5, max=10)
    private String mobilePhoneNo;

}
