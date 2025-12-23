# PowerShell script to start the databases
Write-Host "Starting TheGame databases with Docker Compose..." -ForegroundColor Green

# Check if Docker is running
try {
    docker version | Out-Null
    Write-Host "Docker is running" -ForegroundColor Green
} catch {
    Write-Host "Docker is not running. Please start Docker Desktop first." -ForegroundColor Red
    exit 1
}

# Start the databases
Write-Host "Starting PostgreSQL databases..." -ForegroundColor Yellow
docker-compose up -d

# Wait for databases to be ready
Write-Host "Waiting for databases to be ready..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

# Check database status
Write-Host "Database Status:" -ForegroundColor Green
docker-compose ps

Write-Host ""
Write-Host "Databases are ready!" -ForegroundColor Green
Write-Host "Account DB: localhost:5432" -ForegroundColor Cyan
Write-Host "Game DB: localhost:5433" -ForegroundColor Cyan
Write-Host "Redis: localhost:6379" -ForegroundColor Cyan
Write-Host ""
Write-Host "To stop databases: docker-compose down" -ForegroundColor Yellow


