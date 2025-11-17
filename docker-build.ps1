# Script para construir y probar la imagen Docker localmente
# Uso: .\docker-build.ps1

Write-Host "🐳 Construyendo imagen Docker para WebMDNCH..." -ForegroundColor Cyan

# Construir la imagen
Write-Host "`n📦 Paso 1: Construyendo imagen..." -ForegroundColor Yellow
docker build -t webmdnch-backend:latest .

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Imagen construida exitosamente" -ForegroundColor Green
    
    # Mostrar información de la imagen
    Write-Host "`n📊 Información de la imagen:" -ForegroundColor Yellow
    docker images webmdnch-backend:latest
    
    Write-Host "`n✨ Para ejecutar el contenedor, usa uno de estos comandos:" -ForegroundColor Cyan
    Write-Host "`n🔹 Con variables de entorno inline:" -ForegroundColor White
    Write-Host @"
docker run -p 8080:8080 \
  -e DATABASE_URL="jdbc:mysql://HOST:3306/DB?useSSL=true&serverTimezone=America/Lima" \
  -e DATABASE_USERNAME="tu_usuario" \
  -e DATABASE_PASSWORD="tu_password" \
  -e JWT_SECRET="tu_jwt_secret" \
  -e BASE_URL="http://localhost:8080" \
  -e SPRING_PROFILES_ACTIVE="prod" \
  webmdnch-backend:latest
"@ -ForegroundColor Gray
    
    Write-Host "`n🔹 O usa Docker Compose:" -ForegroundColor White
    Write-Host "docker-compose up" -ForegroundColor Gray
    
} else {
    Write-Host "❌ Error al construir la imagen" -ForegroundColor Red
    exit 1
}
