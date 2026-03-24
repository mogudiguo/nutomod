$outputDir = "src\main\resources\assets\nutonmod\textures\item"
New-Item -ItemType Directory -Force -Path $outputDir | Out-Null

Add-Type -AssemblyName System.Drawing

$colors = @(
    @{Name="nuton_item"; R=64; G=128; B=255},
    @{Name="rare_nuton_item"; R=178; G=64; B=255},
    @{Name="epic_nuton_item"; R=255; G=178; B=64}
)

foreach ($color in $colors) {
    $bitmap = New-Object System.Drawing.Bitmap(16, 16)
    for ($x = 0; $x -lt 16; $x++) {
        for ($y = 0; $y -lt 16; $y++) {
            $dist = [Math]::Sqrt([Math]::Pow($x - 8, 2) + [Math]::Pow($y - 8, 2))
            if ($dist -le 6) {
                $c = [System.Drawing.Color]::FromArgb(255, $color.R, $color.G, $color.B)
                $bitmap.SetPixel($x, $y, $c)
            } elseif ($dist -le 7) {
                $c = [System.Drawing.Color]::FromArgb(255, [Math]::Min($color.R+50,255), [Math]::Min($color.G+50,255), [Math]::Min($color.B+50,255))
                $bitmap.SetPixel($x, $y, $c)
            }
        }
    }
    $bitmap.Save("$outputDir\$($color.Name).png", [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
    Write-Host "Generated: $($color.Name).png"
}

Write-Host "`nDone! Textures saved to: $outputDir"
