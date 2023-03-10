package br.com.vygos.springkeycloak.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
  private Integer id;
  private String name;
  private Integer age;
  private String email;

}
