# PowerShell script to stop the databases
Write-Host "Stopping TheGame databases..." -ForegroundColor Yellow

docker-compose down

Write-Host "Databases stopped!" -ForegroundColor Green


