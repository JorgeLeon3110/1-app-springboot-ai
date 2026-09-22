# AGENTS.md - Guía y Reglas para Agentes de IA

Este documento define las reglas, convenciones, arquitectura y comandos esenciales para cualquier agente de IA que opere en este repositorio.

---

## 1. Descripción del Proyecto

- **Nombre del Proyecto:** `1-spring-app`
- **Contexto:** Módulo inicial del curso `curso_spring_ai`.
- **Propósito:** Aplicación base Spring Boot preparada para la incorporación de servicios web y capacidades de Spring AI (ChatClient, ChatModel, integraciones LLM).
- **Paquete Base:** `com.jorge.course.antigravity.springboot`
- **Group ID:** `com.jorge.course.antigravity.springboot`
- **Artifact ID:** `1-spring-app`

---

## 2. Stack Tecnológico

| Componente | Versión / Detalle |
| :--- | :--- |
| **Lenguaje** | Java 21 (JDK 21) |
| **Framework** | Spring Boot 4.0.6 |
| **Gestor de Construcción** | Apache Maven con Maven Wrapper (`mvnw` / `mvnw.cmd`) |
| **Módulos Principales** | `spring-boot-starter-webmvc`, `spring-boot-devtools` |
| **Pruebas** | JUnit 5 (`spring-boot-starter-webmvc-test`) |
| **Configuración** | `src/main/resources/application.properties` |

---

## 3. Entorno de Ejecución y JDK (CRÍTICO)

> [!WARNING]
> En la máquina anfitriona, el comando `java` en el PATH global apunta a Java 1.8 por defecto.
> Para ejecutar cualquier comando de Maven o compilación, el agente **DEBE** establecer explícitamente `JAVA_HOME` apuntando al JDK 21 instalado:
>
> **Ruta JDK 21:** `D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10`

### Comandos de Terminal Estándar (Windows CMD / PowerShell)

- **Compilar el proyecto:**
  ```cmd
  cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd clean compile"
  ```

- **Ejecutar pruebas unitarias y de integración:**
  ```cmd
  cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd test"
  ```

- **Iniciar la aplicación:**
  ```cmd
  cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd spring-boot:run"
  ```

- **Empaquetar JAR:**
  ```cmd
  cmd /c "set JAVA_HOME=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10&& set PATH=D:\HERRAMIENTAS_DESARROLLO\java64\jdk-21.0.10\bin;%PATH%&& mvnw.cmd clean package -DskipTests"
  ```

---

## 4. Estructura del Código

```text
1-spring-app/
├── .mvn/wrapper/                  # Maven Wrapper runtime y propiedades
├── src/
│   ├── main/
│   │   ├── java/com/jorge/course/antigravity/springboot/
│   │   │   ├── Application.java   # Clase principal con @SpringBootApplication
│   │   │   ├── controllers/       # Controladores REST (@RestController)
│   │   │   ├── services/          # Lógica de negocio y clientes Spring AI
│   │   │   └── models/            # DTOs, records y entidades
│   │   └── resources/
│   │       └── application.properties # Parámetros de configuración
│   └── test/
│       └── java/com/jorge/course/antigravity/springboot/
│           └── ApplicationTests.java  # Pruebas de contexto y componentes
├── mvnw / mvnw.cmd                # Scripts del wrapper
├── pom.xml                        # Definición de dependencias y plugins Maven
├── PLAN.md                        # Registro del plan de implementación
└── AGENTS.md                      # Reglas e instrucciones para agentes
```

---

## 5. Convenciones de Desarrollo y Código

1. **Inyección de Dependencias:**
   - Usar inyección por constructor (constructor injection) en lugar de `@Autowired` sobre campos.
   - Declarar las dependencias como `final`.

2. **Uso de Características Modernas de Java 21:**
   - Preferir `record` para DTOs y objetos inmutables de transferencia de datos.
   - Utilizar pattern matching para `switch` y `instanceof` cuando simplifique la lógica.
   - Usar `var` únicamente cuando el tipo resultante sea evidente en la asignación.

3. **Arquitectura y Responsabilidades:**
   - **Controladores (`controllers/`):** Manejar exclusivamente peticiones HTTP, validación de entrada y mapeo de respuestas (`ResponseEntity`).
   - **Servicios (`services/`):** Encapsular lógica de negocio, integración con APIs externas y modelos de IA.
   - **Configuración:** Mantener parámetros configurables en `application.properties` con valores por defecto o variables de entorno (ej. `api-key=${OPENAI_API_KEY:}`).

4. **Pruebas:**
   - Cada nuevo endpoint o servicio debe contar con su correspondiente prueba unitaria o de integración usando `@WebMvcTest` o `@SpringBootTest`.

---

## 6. Pautas para Integraciones con Spring AI

Cuando se incorporen capacidades de Spring AI:
- Gestionar dependencias mediante el BOM oficial de Spring AI (`spring-ai-bom`) en `<dependencyManagement>`.
- Inyectar `ChatClient.Builder` o `ChatModel` en los servicios de IA correspondientes.
- Nunca incluir claves de API (OpenAI, Gemini, Anthropic, etc.) en código fuente ni en `application.properties` en texto plano; usar variables de entorno.

---

## 7. Reglas de Planificación y Gestión de Planes (CRÍTICO)

- **Persistencia en la Raíz:** Cada vez que se genere o actualice un plan (ya sea por `/plan` o por requerimiento de diseño), el agente **DEBE** guardarlo siempre en la raíz del repositorio como [`PLAN.md`](file:///D:/HERRAMIENTAS_DESARROLLO/curso_spring_ai/1-spring-app/PLAN.md).
- **Visualización Directa en el Chat:** Además de guardar el archivo, el agente **SIEMPRE** debe desplegar el contenido del plan de forma visible y completa dentro de la respuesta del chat para facilitar su lectura y revisión inmediata.

---

## Avaliable Skills

| Skill | Ubicación | Propósito |
| :--- | :--- | :--- |
| **`saludar`** | [`.agents/skills/saludar/SKILL.md`](file:///D:/HERRAMIENTAS_DESARROLLO/curso_spring_ai/1-spring-app/.agents/skills/saludar/SKILL.md) | Saludo cordial y enriquecido que incluye bienvenida, día actual, tiempo/clima de la ciudad del usuario (Bogotá) y un pensamiento célebre o proverbio chino. |
| **`skill-creator`** | [`.agents/skills/skill-creator/SKILL.md`](file:///D:/HERRAMIENTAS_DESARROLLO/curso_spring_ai/1-spring-app/.agents/skills/skill-creator/SKILL.md) | Creación, prueba y optimización de habilidades y flujos de trabajo personalizados para el agente. |

---

## Skill Trigger Rules

1. **Regla de Activación para `saludar`:**
   - **Condición de Disparo:** Se activa obligatoriamente cuando el usuario inicie su mensaje con un saludo (`"hola"`, `"buenas"`, `"buenos días"`, `"buenas tardes"`, `"buenas noches"`, `"qué tal"`, etc.).
   - **Estructura Obligatoria de Respuesta:**
     1. **Bienvenida:** Saludo cordial, respetuoso y amable.
     2. **Día y Tiempo Actual:** Indicar el día de la semana y fecha actual, junto con el tiempo/clima de la ciudad del usuario (Bogotá, Colombia).
     3. **Pensamiento o Proverbio:** Citar un pensamiento célebre o proverbio chino inspirador sobre constancia, aprendizaje o sabiduría.
     4. **Asistencia:** Preguntar en qué avanzar hoy en el proyecto `1-spring-app`.


