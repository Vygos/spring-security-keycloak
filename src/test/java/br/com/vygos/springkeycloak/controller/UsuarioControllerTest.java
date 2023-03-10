package br.com.vygos.springkeycloak.controller;

import br.com.vygos.springkeycloak.SpringKeycloakApplication;
import br.com.vygos.springkeycloak.model.User;
import br.com.vygos.springkeycloak.utils.json.JsonParser;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = SpringKeycloakApplication.class
)
@TestPropertySource(locations = "classpath:application.yml")
public class UsuarioControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void beforeEach() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @WithMockUser
  @Test
  public void getUsersFromID_then_shouldReturn200ok() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .content(MediaType.APPLICATION_JSON.getType()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("id", Matchers.equalTo(1))).andReturn();

    User user = JsonParser.parseString(
        result.getResponse().getContentAsString(StandardCharsets.UTF_8),
        User.class
    );

    Assert.assertEquals("José dos Santos", user.getName());
  }
}
