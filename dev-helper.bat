@echo off
chcp 65001 >nul
title NutonMod 开发助手

:menu
cls
echo.
echo ========================================
echo   NutonMod 开发助手
echo ========================================
echo.
echo 项目路径：%~dp0
echo.
echo 请选择操作:
echo.
echo 1. 构建项目 (build)
echo 2. 运行客户端 (runClient)
echo 3. 运行服务器 (runServer)
echo 4. 清理项目 (clean)
echo 5. 运行数据生成器 (runDatagen)
echo 6. 完整构建 (clean + build)
echo 7. 退出
echo.
set /p choice=请输入选项 (1-7): 

if "%choice%"=="1" goto build
if "%choice%"=="2" goto runClient
if "%choice%"=="3" goto runServer
if "%choice%"=="4" goto clean
if "%choice%"=="5" goto datagen
if "%choice%"=="6" goto fullBuild
if "%choice%"=="7" goto end
goto menu

:build
echo.
echo ========================================
echo   正在构建项目...
echo ========================================
.\gradlew build --no-daemon
pause
goto menu

:runClient
echo.
echo ========================================
echo   正在启动 Minecraft 客户端...
echo ========================================
.\gradlew runClient --no-daemon
pause
goto menu

:runServer
echo.
echo ========================================
echo   正在启动 Minecraft 服务器...
echo ========================================
.\gradlew runServer --no-daemon
pause
goto menu

:clean
echo.
echo ========================================
echo   正在清理项目...
echo ========================================
.\gradlew clean --no-daemon
pause
goto menu

:datagen
echo.
echo ========================================
echo   正在运行数据生成器...
echo ========================================
.\gradlew runDatagen --no-daemon
pause
goto menu

:fullBuild
echo.
echo ========================================
echo   正在执行完整构建...
echo ========================================
.\gradlew clean build --no-daemon
pause
goto menu

:end
exit
