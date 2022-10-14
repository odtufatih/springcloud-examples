package com.fatihk.examples.springcloud.foa.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //this is important, this makes id field to be visible and used only in get responses, we hide it for create/update(post/put) endpoints in swagger api documentation
    private long id;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String customerId;

}
