$outputDir = "src\main\resources\assets\nutonmod\textures"
New-Item -ItemType Directory -Force -Path "$outputDir\item" | Out-Null
New-Item -ItemType Directory -Force -Path "$outputDir\block" | Out-Null

Add-Type -AssemblyName System.Drawing

# === 创建能量核心方块纹理 (32x32) ===
$bitmap = New-Object System.Drawing.Bitmap(32, 32)
for ($x = 0; $x -lt 32; $x++) {
    for ($y = 0; $y -lt 32; $y++) {
        # 蓝色基底
        $c = [System.Drawing.Color]::FromArgb(255, 40, 100, 200)
        
        # 添加一些噪点效果
        $noise = Get-Random -Minimum -30 -Maximum 30
        $r = [Math]::Max(0, [Math]::Min(255, 40 + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 100 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 200 + $noise))
        $c = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
        
        # 中心发光区域
        $dist = [Math]::Sqrt([Math]::Pow($x - 16, 2) + [Math]::Pow($y - 16, 2))
        if ($dist -lt 8) {
            $r = [Math]::Min(255, $r + 80)
            $g = [Math]::Min(255, $g + 80)
            $b = [Math]::Min(255, $b + 80)
            $c = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
        }
        
        $bitmap.SetPixel($x, $y, $c)
    }
}
$bitmap.Save("$outputDir\block\energy_core.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bitmap.Dispose()
Write-Host "Generated: energy_core.png (block)"

# === 创建能量剑纹理 (16x16) ===
$bitmap = New-Object System.Drawing.Bitmap(16, 16)
for ($x = 0; $x -lt 16; $x++) {
    for ($y = 0; $y -lt 16; $y++) {
        $transparent = $true
        
        # 剑刃部分（斜向）
        if ($x -ge 3 -and $x -le 12 -and $y -ge 2 -and $y -le 14) {
            $slope = $y - $x
            if ($slope -ge -2 -and $slope -le 4) {
                $transparent = $false
                
                # 剑刃渐变效果
                $gradient = (($x + $y) % 4) * 20
                $r = 100 + $gradient
                $g = 200 + $gradient
                $b = 255
                
                # 边缘高亮
                if ($slope -eq -2 -or $slope -eq 4) {
                    $r = 200
                    $g = 255
                    $b = 255
                }
                
                $c = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
            }
        }
        
        # 剑柄
        if ($x -ge 6 -and $x -le 9 -and $y -ge 12 -and $y -le 15) {
            $transparent = $false
            $c = [System.Drawing.Color]::FromArgb(255, 80, 80, 100)
        }
        
        if (-not $transparent) {
            $bitmap.SetPixel($x, $y, $c)
        } else {
            $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bitmap.Save("$outputDir\item\energy_sword.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bitmap.Dispose()
Write-Host "Generated: energy_sword.png"

# === 创建能量胸甲纹理 (16x16) ===
$bitmap = New-Object System.Drawing.Bitmap(16, 16)
for ($x = 0; $x -lt 16; $x++) {
    for ($y = 0; $y -lt 16; $y++) {
        $transparent = $true
        
        # 胸甲主体
        if ($x -ge 3 -and $x -le 12 -and $y -ge 4 -and $y -le 14) {
            $transparent = $false
            
            # 紫色渐变
            $gradient = $y * 8
            $r = 100 + $gradient
            $g = 50
            $b = 150 + $gradient
            
            # 边缘暗化
            if ($x -eq 3 -or $x -eq 12 -or $y -eq 4 -or $y -eq 14) {
                $r = [Math]::Max(0, $r - 40)
                $g = [Math]::Max(0, $g - 20)
                $b = [Math]::Max(0, $b - 60)
            }
            
            # 中心能量核心图案
            $centerDist = [Math]::Sqrt([Math]::Pow($x - 7.5, 2) + [Math]::Pow($y - 9, 2))
            if ($centerDist -lt 3) {
                $r = 150
                $g = 200
                $b = 255
            }
            
            $c = [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
        }
        
        if (-not $transparent) {
            $bitmap.SetPixel($x, $y, $c)
        } else {
            $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bitmap.Save("$outputDir\item\energy_chestplate.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bitmap.Dispose()
Write-Host "Generated: energy_chestplate.png"

Write-Host "`nDone! All textures generated."
Write-Host "Block textures: $outputDir\block\"
Write-Host "Item textures: $outputDir\item\"
