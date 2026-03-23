@echo off
chcp 65001 >nul
echo.
echo ========================================
echo   移动项目到无中文路径
echo ========================================
echo.

:: 目标路径
set TARGET_PATH=D:\nutonmod-template-1.21

:: 检查目标路径是否存在
if exist "%TARGET_PATH%" (
    echo [错误] 目标路径已存在：%TARGET_PATH%
    echo 请先删除或重命名该目录
    pause
    exit /b 1
)

:: 获取当前脚本所在目录
set CURRENT_DIR=%~dp0

echo [信息] 源路径：%CURRENT_DIR%
echo [信息] 目标路径：%TARGET_PATH%
echo.
echo 即将移动项目...
echo.

:: 创建目标目录
mkdir "%TARGET_PATH%"

:: 复制文件（保留原文件）
echo [正在复制] 正在复制项目文件...
xcopy /E /I /H /Y "%CURRENT_DIR%*" "%TARGET_PATH%" >nul

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo   移动成功！
    echo ========================================
    echo.
    echo 新位置：%TARGET_PATH%
    echo.
    echo 下一步操作：
    echo 1. 打开新位置中的 IDEA 或 VSCode
    echo 2. 运行以下命令构建项目:
    echo    .\gradlew build
    echo.
    pause
) else (
    echo.
    echo [错误] 复制失败！
    pause
)
