# 生成能量核心方块纹理（未激活和激活两种状态）
Add-Type -AssemblyName System.Drawing

$blockDir = "src\main\resources\assets\nutonmod\textures\block"
if (!(Test-Path $blockDir)) { New-Item -ItemType Directory -Force -Path $blockDir | Out-Null }

# === 未激活状态 - 暗蓝色 ===
$size = 32
$bmpOff = New-Object System.Drawing.Bitmap($size, $size)
$darkBlue = [System.Drawing.Color]::FromArgb(100, 30, 140)  # 暗蓝色

for ($x = 0; $x -lt $size; $x++) {
    for ($y = 0; $y -lt $size; $y++) {
        # 添加一些噪点让纹理更自然
        $noise = (Get-Random -Minimum -15 -Maximum 15)
        $r = [Math]::Max(0, [Math]::Min(255, 100 + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 30 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 140 + $noise))
        $color = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
        $bmpOff.SetPixel($x, $y, $color)
    }
}

# 添加中心装饰（暗色）
$centerX = [int]($size / 2)
$centerY = [int]($size / 2)
for ($dx = -3; $dx -le 3; $dx++) {
    for ($dy = -3; $dy -le 3; $dy++) {
        if ($dx * $dx + $dy * $dy -le 9) {
            $pixelColor = [System.Drawing.Color]::FromArgb(255, 80, 20, 100)
            $bmpOff.SetPixel($centerX + $dx, $centerY + $dy, $pixelColor)
        }
    }
}

$bmpOff.Save("$blockDir\energy_core_off.png", [System.Drawing.Imaging.ImageFormat]::Png)
Write-Host "Generated: energy_core_off.png (未激活 - 暗蓝色)" -ForegroundColor Gray

# === 激活状态 - 亮蓝色带发光 ===
$bmpOn = New-Object System.Drawing.Bitmap($size, $size)
$brightBlue = [System.Drawing.Color]::FromArgb(100, 150, 255)  # 亮蓝色

for ($x = 0; $x -lt $size; $x++) {
    for ($y = 0; $y -lt $size; $y++) {
        # 添加一些噪点让纹理更自然
        $noise = (Get-Random -Minimum -15 -Maximum 15)
        $r = [Math]::Max(0, [Math]::Min(255, 100 + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 150 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 255 + $noise))
        $color = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
        $bmpOn.SetPixel($x, $y, $color)
    }
}

# 添加中心发光效果（亮白色/浅蓝色）
for ($dx = -6; $dx -le 6; $dx++) {
    for ($dy = -6; $dy -le 6; $dy++) {
        $dist = [Math]::Sqrt($dx * $dx + $dy * $dy)
        if ($dist -le 6) {
            $brightness = 255 - [int]($dist * 20)
            $pixelColor = [System.Drawing.Color]::FromArgb(255, $brightness, [Math]::Min(255, $brightness + 50), 255)
            $bmpOn.SetPixel($centerX + $dx, $centerY + $dy, $pixelColor)
        }
    }
}

$bmpOn.Save("$blockDir\energy_core_on.png", [System.Drawing.Imaging.ImageFormat]::Png)
Write-Host "Generated: energy_core_on.png (激活 - 亮蓝色带发光)" -ForegroundColor Cyan

Write-Host "`nDone! Created two texture variants:" -ForegroundColor Green
Write-Host "  - energy_core_off.png (暗蓝色，未激活)" -ForegroundColor Gray
Write-Host "  - energy_core_on.png (亮蓝色带发光，激活)" -ForegroundColor Cyan
