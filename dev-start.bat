@echo off
chcp 65001 >nul
title NutonMod Dev Helper
echo.
echo Starting in D:\\nutonmod-template-1.21
cd /d D:\\nutonmod-template-1.21
.\\gradlew runClient --no-daemon
