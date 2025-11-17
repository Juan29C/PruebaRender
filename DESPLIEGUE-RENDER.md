# INSTRUCCIONES RÁPIDAS DE DESPLIEGUE EN RENDER

## 🔧 PASOS PARA CONFIGURAR

### 1. **Preparar la Base de Datos**

Obtén de tu hosting MySQL:
- Host: `ejemplo.com` o `123.45.67.89`
- Puerto: `3306` (generalmente)
- Nombre BD: `web_mdnch_db`
- Usuario: `tu_usuario`
- Contraseña: `tu_contraseña`

### 2. **Generar JWT Secret**

Ejecuta en PowerShell:
```powershell
[Convert]::ToBase64String((1..64 | ForEach-Object { Get-Random -Maximum 256 } ))
```

Copia el resultado generado.

### 3. **Configurar Render**

1. Ve a https://render.com y haz login
2. Click en "New +" → "Web Service"
3. Conecta tu repositorio GitHub: `DaniSScript/Back-End-Web-MDNCH`
4. Configuración:
   - **Name:** `webmdnch-backend`
   - **Branch:** `docker`
   - **Environment:** Docker
   - **Dockerfile Path:** `./Dockerfile`

5. **Variables de Entorno** (click en "Advanced"):

```
DATABASE_URL=jdbc:mysql://TU_HOST:3306/web_mdnch_db?useSSL=true&serverTimezone=America/Lima
DATABASE_USERNAME=tu_usuario
DATABASE_PASSWORD=tu_contraseña_segura
JWT_SECRET=EL_SECRET_QUE_GENERASTE
JWT_EXPIRATION_MS=86400000
BASE_URL=https://webmdnch-backend.onrender.com
SPRING_PROFILES_ACTIVE=prod
PORT=8080
JAVA_OPTS=-Xmx512m -Xms256m
```

6. Click en "Create Web Service"

### 4. **Configuración de Conexión de Base de Datos**

Tu hosting debe proporcionar algo como:

```
Host: db.ejemplo.com
Puerto: 3306
Base de datos: web_mdnch_db
Usuario: mdnch_user
Contraseña: *************
```

**IMPORTANTE:** Asegúrate de que tu hosting MySQL permita conexiones remotas desde las IPs de Render.

Para whitelist IP, consulta: https://render.com/docs/static-outbound-ip-addresses

### 5. **Monitorear el Despliegue**

- Los logs aparecerán en tiempo real en Render
- El primer build toma ~5-10 minutos
- Una vez completado, tu app estará en: `https://webmdnch-backend.onrender.com`

### 6. **Verificar que Funciona**

Prueba el endpoint de health:
```
https://webmdnch-backend.onrender.com/actuator/health
```

Debería responder:
```json
{
  "status": "UP"
}
```

## ⚠️ PROBLEMAS COMUNES

### "Connection timeout" o "Unknown host"
- Verifica que el host de BD sea correcto
- Asegúrate que el firewall permita conexiones externas
- Revisa que las IPs de Render estén en whitelist

### "Access denied for user"
- Verifica usuario y contraseña
- Asegúrate que el usuario tenga permisos remotos

### "Table doesn't exist"
- La configuración `ddl-auto=update` creará las tablas
- Si prefieres, crea el esquema manualmente

### Archivos desaparecen después de reiniciar
- El plan Free no tiene almacenamiento persistente
- Usa almacenamiento externo (S3, Cloudinary) o plan Starter

## 📱 CONTACTO Y SOPORTE

Si tienes problemas:
1. Revisa los logs en Render Dashboard
2. Verifica la conectividad a la BD
3. Asegúrate que todas las variables de entorno estén configuradas

---

**¡Listo! Tu aplicación estará en producción** 🚀
