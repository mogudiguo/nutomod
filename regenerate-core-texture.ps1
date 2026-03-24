# 生成能量核心方块纹理 (32x32) - 蓝色带发光效果

Add-Type -AssemblyName System.Drawing

# 创建 32x32 的位图
$bitmap = New-Object System.Drawing.Bitmap(32, 32)

# 生成蓝色方块的像素
for ($y = 0; $y -lt 32; $y++) {
    for ($x = 0; $x -lt 32; $x++) {
        # 基础蓝色
        $r = 50
        $g = 100
        $b = 200
        
        # 添加一些噪点让纹理更自然
        $noise = Get-Random -Minimum -20 -Maximum 20
        $r = [Math]::Max(0, [Math]::Min(255, $r + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, $g + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, $b + $noise))
        
        # 中心区域更亮（模拟能量核心）
        $dist = [Math]::Sqrt([Math]::Pow($x - 16, 2) + [Math]::Pow($y - 16, 2))
        if ($dist -lt 8) {
            $r = [Math]::Min(255, $r + 60)
            $g = [Math]::Min(255, $g + 100)
            $b = [Math]::Min(255, $b + 55)
        }
        
        $color = [System.Drawing.Color]::FromArgb($r, $g, $b)
        $bitmap.SetPixel($x, $y, $color)
    }
}

# 保存为 PNG 文件
$outputDir = "src\main\resources\assets\nutonmod\textures\block"
if (!(Test-Path $outputDir)) {
    New-Item -ItemType Directory -Force -Path $outputDir | Out-Null
}
$bitmap.Save("$outputDir\energy_core.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bitmap.Dispose()

Write-Host "✓ 能量核心方块纹理已生成: energy_core.png (32x32)" -ForegroundColor Cyan
