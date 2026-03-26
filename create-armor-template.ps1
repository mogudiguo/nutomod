# 创建护甲纹理模板的 PowerShell 脚本
# 由于 PowerShell 图像处理能力有限，我们创建一个简单的单色模板

$armorDir = "src\main\resources\assets\nutonmod\textures\models\armor"

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "能量护甲纹理模板制作指南" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "步骤 1: 打开画图工具" -ForegroundColor Yellow
Write-Host "  - 按 Win+R，输入 'mspaint'，回车" -ForegroundColor White
Write-Host ""

Write-Host "步骤 2: 创建头盔纹理 (64x32 像素)" -ForegroundColor Yellow
Write-Host "  1. 在画图中点击'文件' -> '新建'" -ForegroundColor White
Write-Host "  2. 点击'重新调整大小'" -ForegroundColor White
Write-Host "  3. 选择'像素'，输入：水平 64，垂直 32" -ForegroundColor White
Write-Host "  4. 取消勾选'保持纵横比'" -ForegroundColor White
Write-Host "  5. 绘制你的能量头盔图案（建议画在左上角区域）" -ForegroundColor White
Write-Host "  6. 保存为：energy_helmet_layer_1.png" -ForegroundColor Green
Write-Host "  7. 保存到：$armorDir" -ForegroundColor Green
Write-Host ""

Write-Host "步骤 3: 创建胸甲纹理 (64x32 像素)" -ForegroundColor Yellow
Write-Host "  1. 同样方法创建 64x32 的新文件" -ForegroundColor White
Write-Host "  2. 绘制能量胸甲图案（建议画在右半部分）" -ForegroundColor White
Write-Host "  3. 保存为：energy_chestplate_layer_1.png" -ForegroundColor Green
Write-Host "  4. 保存到相同目录" -ForegroundColor Green
Write-Host ""

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "颜色推荐：" -ForegroundColor Yellow
Write-Host "  - 主色：深蓝色 (RGB: 0, 51, 102)" -ForegroundColor White
Write-Host "  - 高光：亮青色 (RGB: 0, 255, 255)" -ForegroundColor White
Write-Host "  - 能量核心：紫色 (RGB: 153, 51, 255)" -ForegroundColor White
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "提示：你可以参考原版下界合金护甲的纹理布局" -ForegroundColor Magenta
Write-Host "      或者在网上搜索 'minecraft armor texture template' 获取更专业的模板" -ForegroundColor Magenta
Write-Host ""
