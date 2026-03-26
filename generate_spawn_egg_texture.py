"""
生成能量生物刷怪蛋贴图的脚本
刷怪蛋标准尺寸：64x64 像素
"""
from PIL import Image, ImageDraw

# 创建 64x64 的透明图像
img = Image.new('RGBA', (64, 64), (0, 0, 0, 0))
draw = ImageDraw.Draw(img)

# 刷怪蛋的基础颜色（底层 - 深蓝色）
base_color = (0, 51, 102, 255)  # 深蓝色

# 刷怪蛋的斑点颜色（上层 - 亮蓝色/青色）
spot_color = (0, 255, 255, 255)  # 亮青色

# 绘制椭圆形（刷怪蛋形状）
# 底层颜色
draw.ellipse([8, 16, 56, 48], fill=base_color)

# 上层斑点（随机分布的小椭圆）
spots = [
    (18, 22, 26, 28),
    (35, 20, 42, 26),
    (28, 30, 36, 36),
    (42, 32, 48, 38),
    (22, 36, 28, 40),
]

for spot in spots:
    draw.ellipse(spot, fill=spot_color)

# 保存图像
img.save('src/main/resources/assets/nutonmod/textures/item/energy_being_spawn_egg.png')
print("刷怪蛋贴图已生成！")
