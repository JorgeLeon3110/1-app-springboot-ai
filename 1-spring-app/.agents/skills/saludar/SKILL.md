---
name: saludar
description: Saludo cordial y enriquecido para el usuario. Se activa automáticamente cada vez que el usuario salude (por ejemplo con "hola", "buenas", "buenos días", "buenas tardes", "buenas noches", "qué tal", "saludos", etc.). Proporciona una bienvenida amable, el día con el tiempo/clima actual de la ciudad del usuario, y un pensamiento célebre o proverbio chino inspirador.
---

# Skill: Saludar

Este skill define el protocolo de respuesta cordial, enriquecida y personalizada cuando el usuario inicia una conversación o envía un saludo al asistente.

---

## 1. Disparadores (Triggers)

Este skill **DEBE** ejecutarse siempre que el usuario inicie la interacción con un saludo o mencione expresiones de bienvenida, tales como:
- `"Hola"`, `"hola"`, `"hola!"`, `"buenas"`, `"qué tal"`, `"saludos"`
- `"Buenos días"`, `"Buenas tardes"`, `"Buenas noches"`
- Saludos informales combinados con consultas generales.

---

## 2. Elementos Requeridos en la Respuesta

Toda respuesta a un saludo debe estructurarse con los siguientes 4 bloques:

1. **Bienvenida Amable y Personalizada:**
   - Responder con calidez, cortesía y energía positiva.
   - Ponerse a disposición para trabajar en el proyecto Spring Boot / Spring AI (`1-spring-app`).

2. **Día y Tiempo Actual de la Ciudad del Usuario:**
   - Especificar el día de la semana y fecha actual (ej. *Viernes, 18 de septiembre*).
   - Indicar el tiempo / clima actual de la ciudad del usuario (Bogotá, Colombia: ej. *21°C con cielo parcialmente nublado / llovizna suave 🌦️*) o el estado obtenido en tiempo real.

3. **Pensamiento Célebre o Proverbio Chino:**
   - Incluir una frase célebre motivacional o un proverbio chino tradicional enfocado en la perseverancia, el conocimiento, la creación o el aprendizaje continuo.
   - Destacar la cita en formato de cita en bloque (*blockquote* o cursiva) con su respectiva atribución.

4. **Llamado a la Acción / Asistencia:**
   - Preguntar en qué tarea, arquitectura o código de Spring Boot / Spring AI se desea avanzar en esta sesión.

---

## 3. Ejemplo de Saludo Esperado

```markdown
¡Hola! Qué gusto saludarte.

Hoy es **Viernes, 18 de septiembre de 2026** y en **Bogotá** tenemos un tiempo de **21°C con intervalos de llovizna 🌦️**. Espero que estés teniendo una excelente jornada.

> 🎋 *"El mejor momento para plantar un árbol fue hace veinte años. El segundo mejor momento es hoy."*  
> — **Proverbio chino**

Estoy listo para continuar con el desarrollo de nuestro proyecto **1-spring-app**. ¿Qué funcionalidad o componente te gustaría implementar o planificar a continuación?
```
