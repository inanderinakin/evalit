$ErrorActionPreference = "Stop"
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition

Write-Host "Compiling shared module..."
Set-Location $scriptDir
& "$scriptDir\mvnw.cmd" -pl shared clean install
if ($LASTEXITCODE -ne 0) {
    Write-Error "Shared module compilation failed."
    exit 1
}

Write-Host "Starting server and client..."
Start-Process cmd -ArgumentList "/k cd /d `"$scriptDir`" && call `"$scriptDir\mvnw.cmd`" -pl server clean spring-boot:run" -WindowStyle Normal
Start-Process cmd -ArgumentList "/k cd /d `"$scriptDir`" && call `"$scriptDir\mvnw.cmd`" -pl client clean javafx:run" -WindowStyle Normal
