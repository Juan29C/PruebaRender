# 🐳 Guía de Dockerización y Despliegue en Render

## 📋 Resumen del Proyecto

**Tecnologías:**
- Spring Boot 3.4.1
- Java 17
- Maven 3.9.9
- MySQL 8.0
- Spring Security + JWT
- MapStruct 1.5.5

---

## 🚀 Despliegue en Render

### Paso 1: Configurar la Base de Datos

Antes de desplegar, necesitas una base de datos MySQL. Opciones:

#### Opción A: Base de datos externa (recomendado)
Puedes usar servicios como:
- **Railway** (https://railway.app) - Gratis con límites
- **PlanetScale** (https://planetscale.com) - MySQL serverless
- **Clever Cloud** (https://www.clever-cloud.com)
- **Hosting tradicional** - Si tu proveedor de hosting incluye MySQL

#### Opción B: Base de datos en Render
Render no ofrece MySQL gratuito, solo PostgreSQL. Si quieres usar Render para la BD:
1. Crea una base de datos PostgreSQL (gratuita)
2. Tendrías que migrar de MySQL a PostgreSQL (cambiar driver y dependencia)

### Paso 2: Obtener Credenciales de la Base de Datos

De tu proveedor de hosting, necesitas:
- **Host:** `ejemplo.mysql.database.azure.com`
- **Puerto:** `3306` (generalmente)
- **Nombre de base de datos:** `web_mdnch_db`
- **Usuario:** `tu_usuario`
- **Contraseña:** `tu_contraseña`

Construye la URL de conexión:
```
jdbc:mysql://HOST:PUERTO/NOMBRE_BD?useSSL=true&serverTimezone=America/Lima
```

### Paso 3: Configurar Variables de Entorno en Render

1. Ve a tu proyecto en Render Dashboard
2. En la sección **Environment**, agrega estas variables:

```bash
# Base de datos (REEMPLAZAR con tus datos reales)
DATABASE_URL=jdbc:mysql://tu-host.com:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=tu_usuario
DATABASE_PASSWORD=tu_contraseña_segura

# JWT (GENERAR UN NUEVO SECRET para producción)
JWT_SECRET=GENERAR_UN_STRING_ALEATORIO_LARGO_Y_SEGURO_AQUI
JWT_EXPIRATION_MS=86400000

# Base URL (actualizar con tu dominio de Render)
BASE_URL=https://tu-app.onrender.com

# Profile de Spring
SPRING_PROFILES_ACTIVE=prod

# Puerto (Render lo asigna automáticamente, pero por defecto usar 8080)
PORT=8080

# Opciones de JVM (ajustar según plan de Render)
JAVA_OPTS=-Xmx512m -Xms256m
```

### Paso 4: Configurar el Repositorio

1. **Subir cambios a GitHub:**
```bash
git add .
git commit -m "Add Docker support and production configuration"
git push origin docker
```

2. **Conectar Render con GitHub:**
   - Ve a Render Dashboard
   - Click en "New +" → "Web Service"
   - Conecta tu repositorio: `DaniSScript/Back-End-Web-MDNCH`
   - Selecciona la rama: `docker`
   - Render detectará automáticamente el Dockerfile

### Paso 5: Configuración de Render

**Build Configuration:**
- **Environment:** Docker
- **Dockerfile Path:** `./Dockerfile`
- **Docker Build Context:** `./` (raíz del proyecto)

**Health Check:**
- **Path:** `/actuator/health` (necesitas agregar Spring Boot Actuator)

**Instancias:**
- **Tipo:** Free (para empezar) o Starter (más recursos)
- **Auto-Deploy:** Activado (se redesplega con cada push)

### Paso 6: Generar JWT Secret Seguro

Para producción, genera un JWT secret fuerte:

```bash
# En PowerShell
[Convert]::ToBase64String((1..64 | ForEach-Object { Get-Random -Maximum 256 } ))
```

O usa un generador online: https://generate-secret.vercel.app/64

---

## 🔧 Configuración Adicional Recomendada

### 1. Agregar Spring Boot Actuator (para health checks)

Agrega en `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configura en `application-prod.properties`:

```properties
# Actuator endpoints
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=when-authorized
management.health.db.enabled=true
```

### 2. Manejo de Archivos Estáticos

⚠️ **IMPORTANTE:** Los archivos subidos (imágenes/documentos) NO persisten en el plan Free de Render.

**Soluciones:**

#### Opción A: Usar almacenamiento externo (RECOMENDADO)
- **AWS S3** - Pago por uso
- **Cloudinary** - Gratis hasta 25 GB
- **Backblaze B2** - Más económico que S3
- **Supabase Storage** - Gratis hasta 1 GB

#### Opción B: Disco persistente en Render
- Requiere plan Starter o superior ($7/mes)
- Configurar en `render.yaml` descomentando la sección `disk`

### 3. Optimizar Dockerfile para Render

El Dockerfile ya está optimizado con:
- ✅ Multi-stage build (reduce tamaño de imagen)
- ✅ Usuario no-root (seguridad)
- ✅ Zona horaria configurada (America/Lima)
- ✅ Cache de dependencias Maven

---

## 🧪 Pruebas Locales

### Probar con Docker Compose (desarrollo local)

```bash
# Construir y levantar servicios
docker-compose up --build

# La aplicación estará en http://localhost:8080
# MySQL estará en localhost:3307
```

### Probar solo la aplicación (sin DB local)

```bash
# Construir imagen
docker build -t webmdnch-backend .

# Ejecutar con variables de entorno
docker run -p 8080:8080 \
  -e DATABASE_URL="jdbc:mysql://TU_HOST:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima" \
  -e DATABASE_USERNAME="tu_usuario" \
  -e DATABASE_PASSWORD="tu_password" \
  -e JWT_SECRET="tu_jwt_secret" \
  -e BASE_URL="http://localhost:8080" \
  -e SPRING_PROFILES_ACTIVE="prod" \
  webmdnch-backend
```

---

## 📝 Checklist antes de Desplegar

- [ ] Base de datos MySQL configurada y accesible
- [ ] Credenciales de BD obtenidas (host, puerto, usuario, contraseña)
- [ ] JWT secret generado (fuerte y aleatorio)
- [ ] Variables de entorno configuradas en Render
- [ ] Código subido a GitHub (rama docker)
- [ ] Spring Boot Actuator agregado (opcional pero recomendado)
- [ ] Plan de almacenamiento de archivos definido
- [ ] BASE_URL actualizado con dominio de Render

---

## 🐛 Troubleshooting

### Error: "Connection refused" o "Unknown host"

**Causa:** No se puede conectar a la base de datos.

**Solución:**
1. Verifica que la URL de conexión sea correcta
2. Asegúrate que el host de BD permita conexiones externas
3. Revisa que las credenciales sean correctas
4. Verifica que el firewall/whitelist permita la IP de Render

### Error: "Table doesn't exist"

**Causa:** La base de datos está vacía.

**Solución:**
- `spring.jpa.hibernate.ddl-auto=update` creará las tablas automáticamente
- O ejecuta el script SQL de tu esquema manualmente

### La aplicación se reinicia constantemente

**Causa:** Health check falla.

**Solución:**
1. Verifica que `/actuator/health` responda correctamente
2. Aumenta el timeout del health check en Render
3. Revisa los logs en Render Dashboard

### Archivos subidos desaparecen

**Causa:** Plan Free no tiene disco persistente.

**Solución:**
- Migra a plan Starter con disco persistente, O
- Implementa almacenamiento externo (S3, Cloudinary, etc.)

---

## 📚 Recursos Adicionales

- [Documentación de Render](https://render.com/docs)
- [Spring Boot Docker Guide](https://spring.io/guides/topicals/spring-boot-docker)
- [Maven Docker Build](https://docs.docker.com/language/java/build-images/)

---

## 🔐 Seguridad

**Nunca commits estos valores en Git:**
- ❌ Contraseñas de base de datos
- ❌ JWT secrets de producción
- ❌ API keys

**Usa siempre variables de entorno para valores sensibles.**
