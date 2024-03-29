package com.jos.dem.spring.webflux.jaxb;

import com.jos.dem.spring.webflux.jaxb.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@SpringBootTest(classes = DemoApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
  @DisplayName("Should Get Person")
  void shouldGetPerson() {
    webClient.get().uri("/")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(APPLICATION_XML_VALUE)
            .expectBody(Person.class)
            .value(person -> person.getFirstName(), equalTo("Jose"))
            .value(person -> person.getLastName(), equalTo("Morales"))
            .value(person -> person.getAddress(), equalTo("30 Frank Lloyd, Ann Arbor MI 48105"))
            .value(person -> person.getDevice().getName(), equalTo("Pixel 3"))
            .value(person -> person.getDevice().getOs(), equalTo("Android"))
            .value(person -> person.getDevice().getModel(), equalTo("9 Pie"));
	}

}
