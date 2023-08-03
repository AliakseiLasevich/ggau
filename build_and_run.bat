@echo off

REM Step 1: Run Maven clean package
echo Running Maven clean package...
call mvn clean package

REM Check if Maven build was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Maven build failed. Exiting.
    exit /b 1
)

REM Step 2: Build Docker image from Dockerfile
echo Building Docker image...
docker build -t ggau-backend .

REM Check if Docker image build was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Docker image build failed. Exiting.
    exit /b 1
)

REM Step 3: Stop and remove the existing application container
echo Stopping and removing the existing application container...
docker-compose stop app
docker-compose rm -f app

REM Check if Docker container removal was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Docker container removal failed. Exiting.
    exit /b 1
)

REM Step 4: Start the updated application container with the new image
echo Starting the updated application container...
docker-compose up -d

REM Check if Docker container start was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Docker container start failed. Exiting.
    exit /b 1
)

echo Build and run process completed successfully.
exit /b 0
