# Proyecto Backend - Half Media 🚀

## Tabla de contenidos 📚
- [Descripcion](#descripcion)
- [Tecnologias](#tecnologias)
- [Instalacion](#instalacion)
- [Screenshots](#screenshots)
- [Ejemplo](#ejemplo)
- [Enlace](#enlace)
- [Autor](#autor)
- [Contacto](#contacto)

<h2 id="descripcion"> Descripción del Proyecto 📖 <h2>

Este es el backend de una de la red social Half Media construida con **Spring Boot 3.4.2**. Utiliza **PostgreSQL** para almacenar datos y **JWT** para la autenticación y la seguridad de las rutas protegidas. El backend se comunica con un frontend desarrollado en **React.js** y **Vite.js**.

<h2 id="tecnologias"> Tecnologías Utilizadas 💻 <h2> 

- **Spring Boot 3.4.2** - Framework principal para el desarrollo del backend.
- **Java 17** - Lenguaje de programación utilizado.
- **PostgreSQL** - Base de datos para la persistencia de datos.
- **JWT** - Implementación de autenticación basada en JSON Web Tokens.
- **Maven** - Herramienta para la gestión de dependencias y la ejecución del proyecto.

<h2 id="instalacion"> Instrucciones de Instalación y Configuración 💾 <h2>

Requisitos
- **Java 17**
- **PostgreSQL** (versión 13 o superior)
- **Maven**

### Pasos para ejecutar el backend:
1. Clona este repositorio:
   ```bash
    git clone https://github.com/JavierEAcevedoN/My_Social_Media_Backend.git
    ```
2. Configura la base de datos SQL (postgresql) en ``aplication.properties``:
    ```bash
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```
3. Ejecuta el archivo java principal:
    [Main Java](./msmb/src/main/java/c3/msmb/MsmbApplication.java)
4. (Opcional) si no ejecuta ve al ``aplication.properties`` y pon un perto que no este ocupado:
    ```bash
    server.port=9000
    ```

<h3 id="screenshots">Screenshots 📷</h3>

![Half Media E-R](./Half%20Media.jpg)
Diegrama entidad relacion de Half media

### Tabla ``user``

Esta tabla almacena la información de los usuarios de la red social.

- ``username`` (varchar(128), PK, UNIQUE, NOT NULL): Identificador único del usuario.
- ``email`` (varchar(256), UNIQUE, NOT NULL): Correo electrónico unico del usuario.
- ``full_name`` (varchar(512), NOT NULL): Nombre completo del usuario.
- ``password`` (text, NOT NULL): Contraseña del usuario cifrada.
- ``phone`` (varchar(20), NOT NULL): Número de teléfono del usuario.
- ``birth_date`` (date, NOT NULL): Fecha de nacimiento del usuario.
- ``created`` (timestamp, NOT NULL): Fecha y hora en que se creó la cuenta.
- ``updated`` (timestamp): Fecha y hora de la última actualización del perfil.
- ``biography`` (text): Biografía del usuario (opcional).
- ``profile_photo`` (text): URL de la foto de perfil del usuario (opcional).

#### Relaciones

- Un usuario puede hacer publicaciones (``publication``).
- Un usuario puede dar "me gusta" a publicaciones (``like``).
- Un usuario puede hacer comentarios (``comment``).
- Un usuario puede seguir a otros usuarios (``follow``).
- Un usuario puede recibir notificaciones (``notification``).

### Tabla ``publication``

Almacena las publicaciones realizadas por los usuarios.

- ``id`` (long, PK, UNIQUE, NOT NULL): Identificador único de la publicación.
- ``content`` (varchar(500), NOT NULL): Contenido del texto de la publicación.
- ``img_src`` (text): URL o referencia de la imagen de la publicación (opcional).
- ``created`` (timestamp, NOT NULL): Fecha y hora en que se creó la publicación.
- ``updated`` (timestamp): Fecha y hora de la última edición de la publicación.
- ``tags`` (varchar(24)[]): Lista de etiquetas o categorías asociadas a la publicación (opcional).
- ``username`` (varchar(128), FK, NOT NULL): Usuario que realizó la publicación.

#### Relaciones

- Cada publicación pertenece a un usuario (``username``).
- Una publicación puede tener varios comentarios (``comment``).
- Una publicación puede recibir "me gusta" (``like``).

### Tabla ``comment``

Registra los comentarios realizados en las publicaciones.

- ``id`` (long, PK, UNIQUE, NOT NULL): Identificador único del comentario.
- ``content`` (varchar(200), NOT NULL): Texto del comentario.
- ``created`` (timestamp, NOT NULL): Fecha y hora en que se creó el comentario.
- ``updated`` (timestamp): Fecha y hora de la última actualización del comentario.
- ``tagged`` (varchar(128)): Usuario etiquetado en el comentario (opcional).
- ``id_publication`` (long, FK, NOT NULL): Publicación en la que se hizo el comentario.
- ``username`` (varchar(128), FK, NOT NULL): Usuario que hizo el comentario.

#### Relaciones

- Cada comentario pertenece a un usuario (``username``).
- Cada comentario pertenece a una publicación (``id_publication``).

### Tabla ``like``

Registra los "me gusta" dados por los usuarios en las publicaciones.

- ``id`` (long, PK, UNIQUE, NOT NULL): Identificador único del "me gusta".
- ``liked_date`` (timestamp, NOT NULL): Fecha y hora en que se dio el "me gusta".
- ``username`` (varchar(128), FK, NOT NULL): Usuario que dio el "me gusta".
- ``id_publication`` (long, FK, NOT NULL): Publicación que recibió el "me gusta".

### Restricciones

- Un usuario solo puede dar "me gusta" una vez por publicación (la combinación ``username`` + ``id_publication`` debe ser única).

#### Relaciones

- Cada "me gusta" pertenece a un usuario (``username``).
- Cada "me gusta" pertenece a una publicación (``id_publication``).

### Tabla ``follow``

Almacena las relaciones de seguidores entre los usuarios.

- ``id`` (long, PK, UNIQUE, NOT NULL): Identificador único del seguimiento.
- ``followed_timestamp`` (timestamp, NOT NULL): Fecha y hora en que se hizo el seguimiento.
- ``follow_from`` (varchar(128), FK, NOT NULL): Usuario que sigue a otro.
- ``follow_to`` (varchar(128), FK, NOT NULL): Usuario que es seguido.

### Restricciones

- Un usuario no puede seguirse a sí mismo (``follow_from`` ≠ ``follow_to``).
- Un usuario puede seguir a otro solo una vez (la combinación ``follow_from`` + ``follow_to`` debe ser única).

#### Relaciones

- Un usuario puede seguir a múltiples usuarios y ser seguido por otros usuarios.

### Tabla ``notification``

Registra las notificaciones enviadas a los usuarios.

- ``id`` (long, PK, UNIQUE, NOT NULL): Identificador único de la notificación.
- ``content`` (text, NOT NULL): Contenido de la notificación.
- ``sended`` (timestamp, NOT NULL): Fecha y hora en que se envió la notificación.
- ``readed`` (boolean, NOT NULL): Indica si el usuario ya leyó la notificación.
- ``username`` (varchar(128), FK, NOT NULL): Usuario que recibe la notificación.

#### Relaciones

- Cada notificación pertenece a un usuario (``username``).

<h3 id="ejemplo">Lista de endpoints y ejemplos de uso</h3>

### Ejemplo de endpoint login

Se ejecuta el endpoint en el enlace ``http://localhost:{puerto que eligio}/auth/login``

Se utiliza el metodo post con su content body para la peticion http:

```json
{
"username": "jean",
"password": "46hd721awqw"
}
```

Y el backend devuelve la siguiente respuesta:
```json
{
"token": "dasd34rwe233r.dasdASDSADwada3r43242dsadDASD2343.DASDaweasd3242sada$586799708786"
}
```

Los demas endpoints se pueden ver aqui: [Half Media API.pdf](./Half%20Media%20API.pdf)  
O aqui: [Index.html](./html2-client-generated/index.html)

<h3 id="enlace">Enlace 📎</h3>

Puedes encontrar el repositorio del frontend aqui:
- [Repositorio Frontend](https://github.com/JavierEAcevedoN/My_Social_Media_Frontend)  
Ultimo Hash: 

<h3 id="autor">Autor 👤</h3>

- [JavierEAcevedoN](https://github.com/JavierEAcevedoN)

<h3 id="contacto">Contacto 📱</h3>

- Linkedin: [Javier Eduardo Acevedo Noguera](https://www.linkedin.com/in/javier-eduardo-acevedo-noguera)