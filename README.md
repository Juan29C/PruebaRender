# 🏛️ WebMDNCH - Sistema Back-End Municipal

Sistema Back-End de la página oficial de la Municipalidad Distrital de Nuevo Chimbote.

## 🚀 Tecnologías

- **Spring Boot** 3.4.1
- **Java** 17
- **Maven** 3.9.9
- **MySQL** 8.0
- **Spring Security** + JWT
- **MapStruct** 1.5.5
- **Lombok**
- **Spring Boot Actuator**

## 📋 Características

- ✅ Autenticación y autorización con JWT
- ✅ API RESTful
- ✅ Gestión de contenido municipal
- ✅ Manejo de archivos (imágenes y documentos)
- ✅ Auditoría de datos
- ✅ Validación de datos
- ✅ Health checks

## 🐳 Dockerización y Despliegue

Este proyecto está completamente dockerizado y listo para desplegar en **Render**.

### 📚 Documentación Disponible

- **[📖 README-DOCKER.md](./README-DOCKER.md)** - Guía completa de dockerización y configuración
- **[🚀 DESPLIEGUE-RENDER.md](./DESPLIEGUE-RENDER.md)** - Pasos rápidos para desplegar en Render
- **[🗄️ CONFIGURACION-BD.md](./CONFIGURACION-BD.md)** - Dónde y cómo configurar la base de datos
- **[✅ CHECKLIST.md](./CHECKLIST.md)** - Lista de verificación para despliegue

### Inicio Rápido

```powershell
# Construir la imagen Docker
docker build -t webmdnch-backend .

# O usar Docker Compose para desarrollo local
docker-compose up --build
```

## 🔧 Configuración

### Variables de Entorno

Copia `.env.example` y configura según tu entorno:

```bash
DATABASE_URL=jdbc:mysql://localhost:3306/web_mdnch_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=root
JWT_SECRET=tu_jwt_secret_aqui
BASE_URL=http://localhost:8080
SPRING_PROFILES_ACTIVE=prod
```

### Desarrollo Local

```powershell
# Instalar dependencias
.\mvnw clean install

# Ejecutar aplicación
.\mvnw spring-boot:run
```

## 📦 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/mdnch/webmdnch/
│   │   ├── audit/          # Auditoría de entidades
│   │   ├── config/         # Configuraciones
│   │   ├── controller/     # Controladores REST
│   │   ├── dto/            # DTOs y mappers
│   │   ├── entity/         # Entidades JPA
│   │   ├── exception/      # Manejo de excepciones
│   │   ├── mapper/         # MapStruct mappers
│   │   ├── repository/     # Repositorios JPA
│   │   ├── security/       # Seguridad y JWT
│   │   ├── service/        # Lógica de negocio
│   │   └── util/           # Utilidades
│   └── resources/
│       ├── application.properties           # Config desarrollo
│       └── application-prod.properties      # Config producción
├── Dockerfile              # Configuración Docker
├── docker-compose.yml      # Compose para desarrollo
└── render.yaml            # Configuración de Render
```

## 🌐 Endpoints Principales

### Autenticación
- `POST /api/authentication/login` - Login de usuarios

### Health Check
- `GET /actuator/health` - Estado de la aplicación

### Recursos Estáticos
- `/imagenes/**` - Archivos de imágenes
- `/documentos/**` - Archivos de documentos

### Módulos
- Agenda
- Convocatorias
- Banner
- Consejo Municipal
- Contacto
- Control Interno
- Defensa Civil
- Y más...

## 🔒 Seguridad

- Autenticación basada en JWT
- Roles: ADMINISTRADOR, ALCALDIA, etc.
- Endpoints protegidos por rol
- CORS configurado
- Variables de entorno para credenciales sensibles

## 🧪 Testing

```powershell
# Ejecutar tests
.\mvnw test

# Con cobertura
.\mvnw test jacoco:report
```

## 📝 Desarrollo

### Agregar nuevas dependencias

Edita `pom.xml` y ejecuta:
```powershell
.\mvnw clean install
```

### Generar JAR

```powershell
.\mvnw clean package -DskipTests
```

El JAR se generará en `target/webmdnch-0.0.1-SNAPSHOT.jar`

## 🐛 Troubleshooting

### Puerto 8080 en uso
```powershell
# Cambiar puerto en application.properties
server.port=8081
```

### Problemas de conexión a BD
- Verifica que MySQL esté corriendo
- Revisa credenciales en variables de entorno
- Asegúrate que la BD `web_mdnch_db` exista

### Errores de compilación
```powershell
.\mvnw clean install -U
```

## 📄 Licencia

Este proyecto es propiedad de la Municipalidad Distrital de Nuevo Chimbote.

## 👥 Equipo de Desarrollo

Desarrollado para la Municipalidad Distrital de Nuevo Chimbote.

## 🔗 Enlaces Útiles

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Render Docs](https://render.com/docs)
- [Docker Docs](https://docs.docker.com)

---

**Para desplegar en producción, sigue la [Guía de Despliegue](./DESPLIEGUE-RENDER.md)** 🚀
