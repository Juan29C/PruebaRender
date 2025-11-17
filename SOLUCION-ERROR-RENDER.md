# 🚨 SOLUCIÓN AL ERROR - CONFIGURAR VARIABLES DE ENTORNO EN RENDER

## ❌ Error Actual

```
Could not resolve placeholder 'JWT_SECRET' in value "${JWT_SECRET}"
```

**Causa:** No has configurado las variables de entorno en Render Dashboard.

---

## ✅ SOLUCIÓN: Configurar Variables en Render

### 📍 Paso a Paso

1. **Ve a Render Dashboard:** https://dashboard.render.com
2. **Selecciona tu servicio:** `webmdnch-backend` (o como lo hayas nombrado)
3. **Click en la pestaña "Environment"** (en el menú lateral izquierdo)
4. **Click en "Add Environment Variable"**
5. **Agrega TODAS estas variables una por una:**

---

## 🔑 VARIABLES DE ENTORNO A CONFIGURAR

### 1. Base de Datos MySQL

```
Key: DATABASE_URL
Value: jdbc:mysql://158.69.104.108:3306/wwwgobch_web_mdnch?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true
```

```
Key: DATABASE_USERNAME
Value: wwwgobch_user_web_mdnch
```

```
Key: DATABASE_PASSWORD
Value: MDNCH*2025
```

### 2. JWT Secret

```
Key: JWT_SECRET
Value: OYDDIeIjzB7ifqcxzfri/11CwOTk6eQwhpElw88mKPWfV84gWjw/lqzFgqPbPAn7d3gjIoVQFGA67pHGqzr4JQ==
```

**Nota:** Este es el secret de desarrollo. Para mayor seguridad, deberías generar uno nuevo, pero este funcionará.

### 3. JWT Expiration

```
Key: JWT_EXPIRATION_MS
Value: 86400000
```

### 4. Base URL

```
Key: BASE_URL
Value: https://tu-app.onrender.com
```

**⚠️ IMPORTANTE:** Reemplaza `tu-app` con el nombre real de tu aplicación en Render.

Por ejemplo, si tu URL es `https://webmdnch-backend.onrender.com`, usa eso.

### 5. Spring Profile

```
Key: SPRING_PROFILES_ACTIVE
Value: prod
```

### 6. Puerto (opcional, Render lo configura automáticamente)

```
Key: PORT
Value: 8080
```

### 7. Opciones JVM

```
Key: JAVA_OPTS
Value: -Xmx512m -Xms256m
```

---

## 📋 RESUMEN - Copiar y pegar cada línea

Configura estas 8 variables en Render:

| Key | Value |
|-----|-------|
| `DATABASE_URL` | `jdbc:mysql://158.69.104.108:3306/wwwgobch_web_mdnch?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true` |
| `DATABASE_USERNAME` | `wwwgobch_user_web_mdnch` |
| `DATABASE_PASSWORD` | `MDNCH*2025` |
| `JWT_SECRET` | `OYDDIeIjzB7ifqcxzfri/11CwOTk6eQwhpElw88mKPWfV84gWjw/lqzFgqPbPAn7d3gjIoVQFGA67pHGqzr4JQ==` |
| `JWT_EXPIRATION_MS` | `86400000` |
| `BASE_URL` | `https://tu-app.onrender.com` *(cambiar por tu URL real)* |
| `SPRING_PROFILES_ACTIVE` | `prod` |
| `JAVA_OPTS` | `-Xmx512m -Xms256m` |

---

## 🎬 Después de Configurar

1. **Guarda los cambios** (click en "Save Changes" en Render)
2. Render **automáticamente redespleará** tu aplicación
3. **Espera 5-10 minutos** para que compile y arranque
4. **Verifica los logs** en tiempo real

---

## ✅ Verificar que Funciona

Una vez desplegado, visita:

```
https://tu-app.onrender.com/actuator/health
```

Deberías ver:
```json
{
  "status": "UP"
}
```

---

## 🔒 Seguridad - Recomendaciones

### ⚠️ Tu contraseña de BD contiene caracteres especiales

`MDNCH*2025` - El asterisco `*` podría causar problemas en algunas configuraciones.

Si tienes problemas de conexión, considera:
1. Cambiar la contraseña de la BD a algo sin caracteres especiales
2. O URL-encodear el asterisco: `MDNCH%2A2025`

### 🔐 Whitelist de IP

Tu servidor MySQL (`158.69.104.108`) debe permitir conexiones desde las IPs de Render.

**IPs de Render para whitelist:**

Consulta: https://render.com/docs/static-outbound-ip-addresses

O configura el firewall de tu hosting para permitir conexiones desde cualquier IP (menos seguro pero más simple).

---

## 🐛 Si Aún No Funciona

### Error de conexión a BD:

```bash
# Prueba la conexión desde tu máquina local
mysql -h 158.69.104.108 -u wwwgobch_user_web_mdnch -p wwwgobch_web_mdnch
# Contraseña: MDNCH*2025
```

Si no conecta desde tu máquina, tampoco conectará desde Render.

**Solución:** Contacta a tu proveedor de hosting para:
1. Habilitar acceso remoto a MySQL
2. Agregar las IPs de Render al whitelist
3. Verificar que el puerto 3306 esté abierto

---

## 📸 Guía Visual

### Paso 1: Ir a Environment
![Render Dashboard → Environment](https://render.com/docs/images/env-vars.png)

### Paso 2: Add Environment Variable
Click en "Add Environment Variable" y llena:
- **Key:** `DATABASE_URL`
- **Value:** `jdbc:mysql://158.69.104.108:3306/...`

### Paso 3: Repetir para todas las variables

### Paso 4: Save Changes
Render redespleará automáticamente.

---

## 📞 Soporte

Si después de configurar todo sigue fallando:

1. **Revisa los logs completos en Render**
2. **Verifica que todas las variables estén bien escritas** (sin espacios extra)
3. **Confirma que la BD permita acceso remoto**
4. **Prueba conectarte a la BD desde una herramienta externa** (MySQL Workbench, DBeaver)

---

**¡NO SE CONFIGURA EN DOCKER!** Todo se configura en **Render Dashboard → Environment Variables**.

El Dockerfile ya está perfecto. Solo falta la configuración de Render.
