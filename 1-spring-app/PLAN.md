# Plan de Implementación: Creación de Proyecto Spring Boot con Spring Initializr

Crear e inicializar el proyecto Spring Boot en el workspace actual (`D:\HERRAMIENTAS_DESARROLLO\curso_spring_ai\1-spring-app`) utilizando la API de **Spring Initializr** (`https://start.spring.io`), cumpliendo con todas las especificaciones solicitadas.

---

## Descripción del Objetivo

Generar un proyecto Spring Boot con las siguientes características:
- **Origen:** Generado a través de `https://start.spring.io/starter.zip`
- **Versión de Java / JDK:** 21
- **Versión de Spring Boot:** 4.0.6
- **Nombre del proyecto:** `1-spring-app` (nombre del workspace actual)
- **Group ID y Package:** `com.jorge.course.antigravity.springboot`
- **Artifact ID:** `1-spring-app`
- **Dependencias Maven:** `web` (Spring MVC), `devtools` (Spring Boot DevTools) y test starter
- **Configuración:** Archivo de configuración en formato properties (`application.properties`)

---

## User Review Required

> [!IMPORTANT]
> - El archivo existente `install.cmd` en el directorio raíz se conservará intacto.
> - La versión de Java configurada en el proyecto es **Java 21**. Para compilar y ejecutar con el wrapper `mvnw`, se utilizará el JDK 21 instalado en `D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10` configurando `JAVA_HOME`.

---

## Estructura Propuesta de Archivos

```
1-spring-app/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/com/jorge/course/antigravity/springboot/
│   │   │   ├── Application.java
│   │   │   ├── controllers/
│   │   │   │   └── IndexController.java
│   │   │   └── models/
│   │   │       └── User.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/jorge/course/antigravity/springboot/
│           ├── ApplicationTests.java
│           └── controllers/
│               └── IndexControllerTest.java
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── PLAN.md
└── AGENTS.md


```

---

## Cambios Propuestos

### 1. Inicialización y Extracción del Proyecto

#### [NEW] `pom.xml`
Definición del build de Maven con Spring Boot 4.0.6, Java 21 y dependencias `webmvc` y `devtools`:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.0.6</version>
		<relativePath/>
	</parent>
	<groupId>com.jorge.course.antigravity.springboot</groupId>
	<artifactId>1-spring-app</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>1-spring-app</name>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

#### [NEW] `src/main/resources/application.properties`
Archivo de propiedades estándar para configuración de la aplicación:
```properties
spring.application.name=1-spring-app
server.port=8080
```

#### [NEW] `src/main/java/com/jorge/course/antigravity/springboot/Application.java`
Clase principal con anotación `@SpringBootApplication`:
```java
package com.jorge.course.antigravity.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

#### [NEW] `src/main/java/com/jorge/course/antigravity/springboot/controllers/IndexController.java`
Controlador REST en el paquete `controllers` con prefijo `/api` y endpoints para retornar mensajes y el modelo `User` con `ResponseEntity`:
```java
package com.jorge.course.antigravity.springboot.controllers;

import com.jorge.course.antigravity.springboot.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping({ "/index" })
    public ResponseEntity<Map<String, Object>> index() {
        User user = new User("Jorge", "Doe", "jorge@correo.com");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "hola mundo desde spring boot");
        response.put("user", user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping({ "/greeting" })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Object> index2() {
        User user = new User("Jorge", "Doe", "jorge@correo.com");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "hola mundo desde spring boot");
        response.put("user", user);
        return response;
    }

    // http://localhost:8080/api/details
    // http://localhost:8080/api/user
    @GetMapping(value = { "/details", "/user" }, produces = "application/json")
    public ResponseEntity<User> user() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Content-Type", "application/json")
                .header("X-Custom-Header", "mi header customizado")
                .header("Authorization", "Bearer token_123456")
                .body(user);
    }

    // http://localhost:8080/api/details-text
    // http://localhost:8080/api/user-text
    // http://localhost:8080/api/details (con header Accept: text/plain)
    @GetMapping(value = { "/details", "/user", "/details-text", "/user-text" }, produces = "text/plain")
    public ResponseEntity<String> userPlainText() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.ok(user.toString());
    }

    // http://localhost:8080/api/details-xml
    // http://localhost:8080/api/user-xml
    // http://localhost:8080/api/details (con cabecera Accept: application/xml)
    @GetMapping(value = { "/details", "/user", "/details-xml", "/user-xml" }, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> userXml() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(user);
    }
}
```

#### [NEW] `src/test/java/com/jorge/course/antigravity/springboot/controllers/IndexControllerTest.java`
Pruebas unitarias con `@WebMvcTest` y `MockMvc` para validar todos los endpoints (JSON, texto plano y XML):
```java
package com.jorge.course.antigravity.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHolaMundoAndUserOnRoot() throws Exception {
        mockMvc.perform(get("/api/index"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hola mundo desde spring boot"))
                .andExpect(jsonPath("$.user.name").value("Jorge"))
                .andExpect(jsonPath("$.user.lastname").value("Doe"));
    }

    @Test
    void shouldReturnUserOnGreeting() throws Exception {
        mockMvc.perform(get("/api/greeting"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("hola mundo desde spring boot"))
                .andExpect(jsonPath("$.user.name").value("Jorge"))
                .andExpect(jsonPath("$.user.lastname").value("Doe"));
    }

    @Test
    void shouldReturnUserDetails() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Jorge"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserEndpoint() throws Exception {
        mockMvc.perform(get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Jorge"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserAsPlainTextWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("User [name=Jorge, lastname=Doe, email=[EMAIL_ADDRESS]]"));
    }

    @Test
    void shouldReturnUserAsPlainTextOnTextEndpoint() throws Exception {
        mockMvc.perform(get("/api/user-text"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("User [name=Jorge, lastname=Doe, email=[EMAIL_ADDRESS]]"));
    }

    @Test
    void shouldReturnUserAsXmlOnXmlEndpoint() throws Exception {
        mockMvc.perform(get("/api/user-xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("/User/name").string("Jorge"))
                .andExpect(xpath("/User/lastname").string("Doe"))
                .andExpect(xpath("/User/email").string("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserAsXmlWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("/User/name").string("Jorge"))
                .andExpect(xpath("/User/lastname").string("Doe"))
                .andExpect(xpath("/User/email").string("[EMAIL_ADDRESS]"));
    }
}
```



#### [NEW] `src/main/java/com/jorge/course/antigravity/springboot/models/User.java`
Modelo de datos para encapsular los atributos del usuario (`name`, `lastname`, `email`) con constructores, getters y setters:
```java
package com.jorge.course.antigravity.springboot.models;

public class User {

    private String name;
    private String lastname;
    private String email;

    public User() {
    }

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public User(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
    }
}
```

#### [NEW] Maven Wrapper & Configuración
- `mvnw`, `mvnw.cmd` y `.mvn/wrapper/maven-wrapper.properties` para garantizar compilación autónoma e independiente de la versión global de Maven.


---

## Plan de Verificación

### Pruebas Automatizadas
1. **Validación de estructura:** Comprobar que todos los directorios (`src/main/java/...`, `src/main/resources/application.properties`) existen correctamente.
2. **Validación de compilación:**
   ```cmd
   cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd clean compile"
   ```
3. **Validación de pruebas (unitarias y contexto):**
   ```cmd
   cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd test"
   ```

### Verificación Manual
- Acceder a `http://localhost:8080/api/details-xml` o `http://localhost:8080/api/user-xml` (o con cabecera `Accept: application/xml`) y comprobar la respuesta XML:
  ```xml
  <User>
    <name>Jorge</name>
    <lastname>Doe</lastname>
    <email>[EMAIL_ADDRESS]</email>
  </User>
  ```
- Acceder a `http://localhost:8080/api/details-text` o `http://localhost:8080/api/user-text` (o con cabecera `Accept: text/plain`) y comprobar la respuesta en texto plano:
  ```text
  User [name=Jorge, lastname=Doe, email=[EMAIL_ADDRESS]]
  ```
- Acceder a `http://localhost:8080/api/details` o `http://localhost:8080/api/user` (con cabecera `Accept: application/json`) y comprobar la respuesta JSON:
  ```json
  {
    "name": "Jorge",
    "lastname": "Doe",
    "email": "[EMAIL_ADDRESS]"
  }
  ```
- Acceder a `http://localhost:8080/api/index` para comprobar la respuesta compuesta:
  ```json
  {
    "message": "hola mundo desde spring boot",
    "user": {
      "name": "Jorge",
      "lastname": "Doe",
      "email": "[EMAIL_ADDRESS]"
    }
  }
  ```




