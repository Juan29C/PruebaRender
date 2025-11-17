# 🗄️ CONFIGURACIÓN DE BASE DE DATOS PARA PRODUCCIÓN

## 📍 Dónde Configurar la Conexión de Base de Datos

### Para Render (Despliegue en la Nube)

**NO edites archivos del código fuente.** La configuración se hace mediante **Variables de Entorno** en Render Dashboard.

#### Pasos:

1. Ve a tu proyecto en Render: https://dashboard.render.com
2. Selecciona tu Web Service: `webmdnch-backend`
3. Ve a la pestaña **"Environment"**
4. Agrega estas variables:

```
DATABASE_URL=jdbc:mysql://[HOST]:[PUERTO]/[NOMBRE_BD]?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=[USUARIO]
DATABASE_PASSWORD=[CONTRASEÑA]
```

#### Ejemplo con datos reales:

Si tu hosting te proporciona:
- **Host:** `mysql.ejemplo.com`
- **Puerto:** `3306`
- **Base de datos:** `web_mdnch_db`
- **Usuario:** `mdnch_user`
- **Contraseña:** `MiPassword123!`

Configurarías:

```
DATABASE_URL=jdbc:mysql://mysql.ejemplo.com:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=mdnch_user
DATABASE_PASSWORD=MiPassword123!
```

---

## 🔍 Cómo Obtener Credenciales de tu Hosting

### Si usas un hosting tradicional (cPanel, Plesk, etc.):

1. **Accede al panel de control** de tu hosting
2. Busca la sección **"Bases de Datos MySQL"** o **"phpMyAdmin"**
3. Encuentra o crea una base de datos
4. Anota:
   - Host/Servidor (puede ser `localhost`, una IP, o un dominio)
   - Puerto (usualmente `3306`)
   - Nombre de la base de datos
   - Usuario
   - Contraseña

### Ejemplo de cPanel:

```
Host: localhost o mysql.tudominio.com
Puerto: 3306
Base de datos: tuusuario_webmdnch
Usuario: tuusuario_admin
Contraseña: (la que creaste)
```

### Si usas Railway (alternativa a Render para MySQL):

1. Crea una base de datos MySQL en Railway
2. Railway te proporcionará automáticamente:
   - `DATABASE_URL` completa (en formato especial)
   - Puedes construirla con los valores individuales

Railway proporciona algo como:
```
MYSQL_URL=mysql://root:password@containers-us-west-xxx.railway.app:1234/railway
```

Conviértela a formato JDBC:
```
DATABASE_URL=jdbc:mysql://containers-us-west-xxx.railway.app:1234/railway?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=root
DATABASE_PASSWORD=password
```

---

## 🔒 Seguridad: IMPORTANTE

### ❌ NUNCA hagas esto:

```java
// ❌ NO pongas credenciales directamente en el código
spring.datasource.url=jdbc:mysql://mysql.ejemplo.com:3306/web_mdnch_db
spring.datasource.username=usuario_real
spring.datasource.password=password_real
```

### ✅ SIEMPRE usa variables de entorno:

```java
// ✅ Correcto - usa variables de entorno
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

**Ya está configurado así en:** `src/main/resources/application-prod.properties`

---

## 🧪 Probar Conexión Localmente (Opcional)

Si quieres probar la conexión antes de desplegar:

### Opción 1: Usando variables de entorno en PowerShell

```powershell
$env:DATABASE_URL="jdbc:mysql://HOST:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima"
$env:DATABASE_USERNAME="tu_usuario"
$env:DATABASE_PASSWORD="tu_password"
$env:JWT_SECRET="tu_jwt_secret"
$env:SPRING_PROFILES_ACTIVE="prod"

.\mvnw spring-boot:run
```

### Opción 2: Crear un archivo de configuración temporal

Crea `src/main/resources/application-local.properties`:

```properties
spring.datasource.url=jdbc:mysql://HOST:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

Ejecuta con:
```powershell
.\mvnw spring-boot:run -Dspring.profiles.active=local
```

**⚠️ NO COMITTEES este archivo al repositorio** (ya está en `.gitignore`)

---

## 📋 Checklist de Configuración

Antes de desplegar, asegúrate de tener:

- [ ] **Host** de la base de datos (IP o dominio)
- [ ] **Puerto** (usualmente 3306)
- [ ] **Nombre** de la base de datos
- [ ] **Usuario** con permisos en la BD
- [ ] **Contraseña** del usuario
- [ ] Verificado que el hosting **permite conexiones remotas**
- [ ] Configurado **whitelist de IPs** si es necesario (consulta con tu proveedor)

---

## 🔗 Formato de DATABASE_URL

### Estructura general:

```
jdbc:mysql://[HOST]:[PUERTO]/[NOMBRE_BD]?[PARAMETROS]
```

### Componentes:

- **jdbc:mysql://** - Protocolo (siempre igual para MySQL)
- **HOST** - Dirección del servidor (IP o dominio)
- **PUERTO** - Puerto MySQL (usualmente 3306)
- **NOMBRE_BD** - Nombre de tu base de datos
- **PARAMETROS** - Configuraciones adicionales:
  - `useSSL=true` - Usar conexión segura (recomendado)
  - `serverTimezone=America/Lima` - Zona horaria
  - `allowPublicKeyRetrieval=true` - Solo si es necesario

### Ejemplos:

**Hosting local:**
```
jdbc:mysql://localhost:3306/web_mdnch_db?useSSL=false&serverTimezone=America/Lima
```

**Hosting remoto con SSL:**
```
jdbc:mysql://mysql.ejemplo.com:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
```

**IP pública:**
```
jdbc:mysql://123.45.67.89:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
```

**Puerto no estándar:**
```
jdbc:mysql://mysql.ejemplo.com:3307/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
```

---

## ❓ Preguntas Frecuentes

### ¿Dónde encuentro el host de mi BD?

En tu panel de hosting, busca secciones como:
- "MySQL Databases"
- "phpMyAdmin"
- "Database Management"
- "Remote MySQL"

### ¿Qué pasa si mi hosting no permite conexiones remotas?

Algunas opciones:
1. Contacta a tu proveedor para habilitarlo
2. Configura whitelist de IPs
3. Usa una BD alternativa (Railway, PlanetScale, etc.)
4. Usa un túnel SSH (más complejo)

### ¿Puedo usar la misma BD para desarrollo y producción?

**No recomendado.** Es mejor tener:
- BD local para desarrollo
- BD de producción separada

### ¿Las tablas se crean automáticamente?

Sí, con la configuración:
```properties
spring.jpa.hibernate.ddl-auto=update
```

Hibernate creará las tablas automáticamente. O puedes:
1. Cambiar a `ddl-auto=none`
2. Ejecutar tu script SQL manualmente

---

## 📞 Soporte

Si tienes problemas de conexión:
1. Verifica que los datos sean correctos
2. Prueba conectarte con un cliente MySQL (MySQL Workbench, DBeaver)
3. Revisa logs en Render Dashboard
4. Consulta con tu proveedor de hosting sobre:
   - Permisos de acceso remoto
   - Whitelist de IPs
   - Configuración de firewall

---

**Archivo de configuración:** `src/main/resources/application-prod.properties`  
**Configuración de Render:** Panel de Environment Variables  
**Documentación completa:** Ver `README-DOCKER.md`
