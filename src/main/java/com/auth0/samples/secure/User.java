package com.auth0.samples.secure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String name;
    private String phone;
}
