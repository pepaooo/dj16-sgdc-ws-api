## Descripción
Este proyecto es una API REST desarrollada con **Spring Boot** que gestiona entidades relacionadas con un sistema de membresías, instalaciones, beneficios y miembros. Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas entidades.

## Tecnologías utilizadas
- **Java** (JDK 17+)
- **Spring Boot** (3.x)
- **Maven** (gestión de dependencias)
- **SQL** (base de datos relacional)
- **Jakarta Validation** (validación de datos)
- **SLF4J** (logging)

## Requisitos previos
1. Tener instalado:
   - **Java 17** o superior.
   - **Maven**.
   - Un servidor de base de datos MariaDB o MySQL.
2. Configurar las credenciales de la base de datos en el archivo `application.properties` o `application.yml`.

## Configuración
1. Clona este repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd dj16-sgdc-ws-api
   ```
2. Configura la conexión a la base de datos en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mariadb://localhost:3306/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Compila y ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints disponibles
### Miembros
#### Obtener todos los miembros
- **GET** `/api/v1/miembros`
- **Parámetros opcionales**: `q` (búsqueda por palabra clave).
- **Respuesta**: Lista de miembros.

#### Obtener un miembro por ID
- **GET** `/api/v1/miembros/{id}`
- **Respuesta**: Detalles del miembro.

#### Crear un nuevo miembro
- **POST** `/api/v1/miembros`
- **Cuerpo de la solicitud**:
  ```json
  {
    "nombre": "Juan",
    "apellidoPaterno": "Pérez",
    "apellidoMaterno": "López",
    "direccion": "Calle Falsa 123",
    "telefono": "123456789",
    "correoElectronico": "juan.perez@example.com",
    "fechaNacimiento": "1990-01-01",
    "genero": "M",
    "fechaInscripcion": "2025-04-08T00:53:02",
    "membresia": {
      "id": 1
    }
  }
  ```
- **Respuesta**: Miembro creado.

#### Actualizar un miembro
- **PUT** `/api/v1/miembros/{id}`
- **Cuerpo de la solicitud**: Igual al de creación.
- **Respuesta**: Miembro actualizado.

#### Eliminar un miembro
- **DELETE** `/api/v1/miembros/{id}`
- **Respuesta**: Código de estado 204 (No Content).

### Membresías
#### Obtener todas las membresías
- **GET** `/api/v1/membresias`
- **Respuesta**: Lista de membresías.

#### Crear una nueva membresía
- **POST** `/api/v1/membresias`
- **Cuerpo de la solicitud**:
  ```json
  {
    "nombre": "Estandar",
    "tarifa": 500.00,
    "duracionDias": 30,
    "estatus": "Activo",
    "descripcion": "Membresía con descuento en tienda y clases de yoga"
  }
  ```
- **Respuesta**: Membresía creada.

### Instalaciones
#### Obtener todas las instalaciones
- **GET** `/api/v1/instalaciones`
- **Respuesta**: Lista de instalaciones.

#### Crear una nueva instalación
- **POST** `/api/v1/instalaciones`
- **Cuerpo de la solicitud**:
  ```json
  {
    "nombre": "Instalación de prueba",
    "descripcion": "Descripción de la instalación",
    "estado": "Disponible"
  }
  ```
- **Respuesta**: Instalación creada.

### Beneficios
#### Actualizar un beneficio
- **PUT** `/api/v1/beneficios/{id}`
- **Cuerpo de la solicitud**:
  ```json
  {
    "nombre": "Beneficio actualizado",
    "descripcion": "Descripción del beneficio actualizado"
  }
  ```
- **Respuesta**: Beneficio actualizado.

#### Eliminar un beneficio
- **DELETE** `/api/v1/beneficios/{id}`
- **Respuesta**: Código de estado 204 (No Content).

## Ejemplo de consumo
Puedes consumir esta API utilizando herramientas como **Postman**, **cURL** o integrándola en tu aplicación cliente.

### Ejemplo con cURL
```bash
curl -X POST http://localhost:8080/api/v1/miembros \
-H "Content-Type: application/json" \
-d '{
  "nombre": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "López",
  "direccion": "Calle Falsa 123",
  "telefono": "123456789",
  "correoElectronico": "juan.perez@example.com",
  "fechaNacimiento": "1990-01-01",
  "genero": "M",
  "fechaInscripcion": "2025-04-08T00:53:02",
  "membresia": {
    "id": 1
  }
}'
```

## Notas adicionales
- Asegúrate de que el servidor esté corriendo en `http://localhost:8080` o ajusta la URL según sea necesario.
- Los datos enviados deben cumplir con las validaciones definidas en el modelo.

## Autor
Proyecto desarrollado por el equipo de **José Angulo**.
```