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
Controlador REST básico en el paquete `controllers` con endpoint GET que retorna un mensaje `"hola mundo"`:
```java
package com.jorge.course.antigravity.springboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

    @GetMapping({"/", "/index", "/api/index"})
    public ResponseEntity<Map<String, String>> index() {
        return ResponseEntity.ok(Map.of("message", "hola mundo"));
    }
}
```

#### [NEW] `src/test/java/com/jorge/course/antigravity/springboot/controllers/IndexControllerTest.java`
Pruebas unitarias con `@WebMvcTest` y `MockMvc` para validar el endpoint GET:
```java
package com.jorge.course.antigravity.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHolaMundoMessage() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hola mundo"));
    }

    @Test
    void shouldReturnHolaMundoOnRoot() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hola mundo"));
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
- Acceder a `http://localhost:8080/index` o `http://localhost:8080/` y comprobar la respuesta JSON:
  ```json
  {
    "message": "hola mundo"
  }
  ```

