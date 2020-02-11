package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaControllerIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private int qtdeCozinha;
	private Cozinha cozinhaJaponesa;
	private static final int ID_COZINHA_INEXISTENTE=100;
	
	@Before
	public void setUp() {		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port=port;
		RestAssured.basePath="/cozinhas";	
				
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarQtdeCozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(qtdeCozinha))
			.body("nome", hasItems("Tailandesa","Japonesa"))
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/correto/cadastroCozinhaItaliana.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("cozinhaId", cozinhaJaponesa.getId())
		.when()
			.get("/{cozinhaId}")
		.then()
			.body("nome", equalTo("Japonesa"))
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		Cozinha tailandesa= new Cozinha();
		tailandesa.setNome("Tailandesa");
		cozinhaRepository.save(tailandesa);
		
		cozinhaJaponesa= new Cozinha();
		cozinhaJaponesa.setNome("Japonesa");
		cozinhaRepository.save(cozinhaJaponesa);
		qtdeCozinha=(int) cozinhaRepository.count();
		
	}
	
}
