from PIL import Image
import os

# 设置输出目录
output_dir = "src/main/resources/assets/nutonmod/textures/item"
os.makedirs(output_dir, exist_ok=True)

def create_simple_texture(filename, color):
    """创建简单的 16x16 像素纹理"""
    img = Image.new('RGBA', (16, 16), (0, 0, 0, 0))
    pixels = img.load()
    
    # 绘制一个圆形图案
    center_x, center_y = 8, 8
    radius = 6
    
    for x in range(16):
        for y in range(16):
            # 计算到中心的距离
            dist = ((x - center_x) ** 2 + (y - center_y) ** 2) ** 0.5
            if dist <= radius:
                pixels[x, y] = color
            elif dist <= radius + 1:
                # 边缘高光
                pixels[x, y] = (min(color[0] + 50, 255), min(color[1] + 50, 255), min(color[2] + 50, 255), 200)
    
    img.save(f"{output_dir}/{filename}.png")
    print(f"已生成：{filename}.png")

# 生成三个物品纹理
create_simple_texture("nuton_item", (64, 128, 255, 255))      # 蓝色 - 基础物品
create_simple_texture("rare_nuton_item", (178, 64, 255, 255))  # 紫色 - 稀有物品
create_simple_texture("epic_nuton_item", (255, 178, 64, 255))  # 橙色 - 史诗物品

print("\n✓ 纹理贴图生成完成！")
print(f"保存位置：{output_dir}/")
