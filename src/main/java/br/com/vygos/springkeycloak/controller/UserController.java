package br.com.vygos.springkeycloak.controller;

import br.com.vygos.springkeycloak.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

  @PreAuthorize("hasRole('USER')")
  @GetMapping("{id}")
  public ResponseEntity<User> get(@PathVariable("id") Integer id) {
    User user = User.builder().
        name("José dos Santos")
        .id(id)
        .age(20).build();

    return ResponseEntity.ok(user);
  }
}
