# ✅ CHECKLIST DE DOCKERIZACIÓN Y DESPLIEGUE

## 📋 Archivos Creados

- [x] `Dockerfile` - Configuración de la imagen Docker
- [x] `.dockerignore` - Archivos a excluir del contexto Docker
- [x] `docker-compose.yml` - Para desarrollo local con BD
- [x] `render.yaml` - Configuración de Render (opcional)
- [x] `application-prod.properties` - Configuración de producción
- [x] `.env.example` - Ejemplo de variables de entorno
- [x] `docker-build.ps1` - Script para construir imagen localmente
- [x] `README-DOCKER.md` - Documentación completa
- [x] `DESPLIEGUE-RENDER.md` - Guía rápida de despliegue
- [x] Actualizados: `pom.xml` (Actuator), `SecurityConfig.java`, `.gitignore`

## 🔍 Configuración Analizada

**Spring Boot:** 3.4.1  
**Java:** 17  
**Maven:** 3.9.9  
**Base de Datos:** MySQL 8.0  
**Dependencias principales:**
- Spring Security + JWT
- Spring Data JPA
- MapStruct 1.5.5
- Lombok
- Validation
- **Actuator** (agregado para health checks)

## 📝 TAREAS PENDIENTES

### Antes de Subir a GitHub

- [ ] Revisar que no haya credenciales en el código
- [ ] Verificar que `.gitignore` excluye archivos sensibles
- [ ] Commit y push a la rama `docker`

```powershell
git add .
git commit -m "Add Docker support and production configuration"
git push origin docker
```

### Configuración de Base de Datos

- [ ] Obtener credenciales del hosting MySQL
  - [ ] Host/IP
  - [ ] Puerto (usualmente 3306)
  - [ ] Nombre de base de datos
  - [ ] Usuario
  - [ ] Contraseña
- [ ] Verificar que el hosting permita conexiones remotas
- [ ] Configurar whitelist de IPs (si es necesario)

### Configuración de Seguridad

- [ ] Generar nuevo JWT secret para producción
  
```powershell
[Convert]::ToBase64String((1..64 | ForEach-Object { Get-Random -Maximum 256 } ))
```

- [ ] Guardar el JWT secret de forma segura
- [ ] NO committear el JWT secret al repositorio

### Configuración en Render

- [ ] Crear cuenta en Render (render.com)
- [ ] Conectar repositorio GitHub
- [ ] Configurar variables de entorno (ver abajo)
- [ ] Configurar health check: `/actuator/health`
- [ ] Elegir plan (Free o Starter)
- [ ] Iniciar despliegue

### Variables de Entorno en Render

Configurar en Environment:

```
DATABASE_URL=jdbc:mysql://[HOST]:[PUERTO]/[NOMBRE_BD]?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=[USUARIO]
DATABASE_PASSWORD=[CONTRASEÑA]
JWT_SECRET=[SECRET_GENERADO]
JWT_EXPIRATION_MS=86400000
BASE_URL=https://[TU-APP].onrender.com
SPRING_PROFILES_ACTIVE=prod
PORT=8080
JAVA_OPTS=-Xmx512m -Xms256m
```

### Después del Despliegue

- [ ] Verificar health check: `https://tu-app.onrender.com/actuator/health`
- [ ] Probar endpoint de login
- [ ] Verificar conexión a base de datos
- [ ] Revisar logs en Render Dashboard
- [ ] Probar subida de archivos (notar limitaciones en plan Free)

## 🧪 Pruebas Locales (Opcional)

### Probar con Docker Compose

```powershell
# Iniciar todos los servicios (app + MySQL)
docker-compose up --build

# Verificar en: http://localhost:8080/actuator/health
```

### Probar solo la imagen Docker

```powershell
# Construir imagen
.\docker-build.ps1

# O manualmente:
docker build -t webmdnch-backend .

# Ejecutar (necesitas una BD MySQL accesible)
docker run -p 8080:8080 `
  -e DATABASE_URL="jdbc:mysql://HOST:3306/DB?useSSL=true&serverTimezone=America/Lima" `
  -e DATABASE_USERNAME="usuario" `
  -e DATABASE_PASSWORD="password" `
  -e JWT_SECRET="tu_secret" `
  -e BASE_URL="http://localhost:8080" `
  -e SPRING_PROFILES_ACTIVE="prod" `
  webmdnch-backend
```

## ⚠️ CONSIDERACIONES IMPORTANTES

### Almacenamiento de Archivos

Tu aplicación maneja carpetas `imagenes/` y `documentos/`.

**Problema:** El plan Free de Render NO tiene almacenamiento persistente.

**Soluciones:**

1. **Plan Starter de Render** ($7/mes)
   - Incluye disco persistente
   - Descomentar sección `disk` en `render.yaml`

2. **Almacenamiento externo** (RECOMENDADO)
   - AWS S3
   - Cloudinary (gratis hasta 25GB)
   - Backblaze B2
   - Supabase Storage

### Conexión de Base de Datos

- Asegúrate que tu hosting MySQL permita conexiones externas
- Algunos hostings requieren whitelist de IPs
- Render usa IPs dinámicas (ver documentación oficial)
- Considera usar MySQL como servicio (Railway, PlanetScale, etc.)

### Rendimiento

- Plan Free de Render:
  - Se suspende después de 15 min de inactividad
  - Primera petición toma ~30-60 segundos (cold start)
  - 512 MB RAM
  - CPU compartida

- Plan Starter ($7/mes):
  - Sin suspensión
  - 512 MB RAM garantizados
  - Disco persistente
  - Mejor para producción

## 📚 Documentación de Referencia

- `README-DOCKER.md` - Guía completa con detalles técnicos
- `DESPLIEGUE-RENDER.md` - Pasos rápidos para desplegar
- `.env.example` - Variables de entorno necesarias

## 🆘 Soporte

### Si tienes errores de conexión a BD:
1. Verifica que el host sea correcto
2. Verifica usuario y contraseña
3. Asegura que el puerto sea 3306
4. Revisa que permita conexiones remotas
5. Consulta whitelist de IPs

### Si las tablas no existen:
- `ddl-auto=update` las creará automáticamente
- O ejecuta tu script SQL manualmente en la BD

### Si la aplicación se reinicia constantemente:
- Revisa los logs en Render
- Verifica que `/actuator/health` responda
- Asegura que todas las variables de entorno estén configuradas

---

## ✨ PRÓXIMOS PASOS

1. ✅ **Obtener credenciales de BD** de tu hosting
2. ✅ **Generar JWT secret** seguro
3. ✅ **Hacer push a GitHub** (rama docker)
4. ✅ **Configurar Render** con las variables
5. ✅ **Desplegar** y monitorear

**¡Buena suerte con el despliegue!** 🚀
