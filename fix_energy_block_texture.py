"""
修复能量方块贴图：去除白底，确保边缘实心
"""
from PIL import Image

# 打开贴图
img = Image.open('src/main/resources/assets/nutonmod/textures/block/energy_block.png')
img = img.convert('RGBA')

# 调整为标准 16x16 尺寸
img = img.resize((16, 16), Image.Resampling.LANCZOS)

# 获取像素
pixels = img.load()

# 修复：将所有接近白色的像素设为透明，确保边缘实心
for x in range(16):
    for y in range(16):
        r, g, b, a = pixels[x, y]
        # 如果是白色或接近白色（RGB 都大于 200），设为完全透明
        if r > 200 and g > 200 and b > 200:
            pixels[x, y] = (0, 0, 0, 0)
        # 如果是半透明像素，设为完全不透明（保持颜色）
        elif a < 255 and a > 0:
            pixels[x, y] = (r, g, b, 255)

# 保存
img.save('src/main/resources/assets/nutonmod/textures/block/energy_block_fixed.png')
print("✓ 已修复能量方块贴图：")
print("  - 去除白色背景")
print("  - 调整为 16x16 标准尺寸")
print("  - 边缘已填充为实心")
print("\n请将 energy_block_fixed.png 重命名为 energy_block.png 替换原文件")
