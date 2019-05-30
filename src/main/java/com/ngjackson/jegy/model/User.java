package com.ngjackson.jegy.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;

}
