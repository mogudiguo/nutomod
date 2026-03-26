"""
生成能量护甲纹理模板
尺寸：64x32 像素
"""
from PIL import Image, ImageDraw

def create_armor_texture(output_path, texture_type="helmet"):
    """
    创建护甲纹理
    """
    # 创建 64x32 的透明图像
    img = Image.new('RGBA', (64, 32), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    if texture_type == "helmet":
        # 头盔纹理（绘制在左上角）
        # 主色 - 深蓝色
        draw.rectangle([16, 0, 48, 16], fill=(0, 51, 102, 255))
        # 高光 - 亮青色边框
        draw.rectangle([16, 0, 48, 2], fill=(0, 255, 255, 255))
        draw.rectangle([16, 0, 18, 16], fill=(0, 255, 255, 255))
        draw.rectangle([46, 0, 48, 16], fill=(0, 255, 255, 255))
        # 能量核心 - 紫色
        draw.rectangle([28, 6, 36, 12], fill=(153, 51, 255, 255))
        
    elif texture_type == "chestplate":
        # 胸甲纹理（绘制在右侧）
        # 主色 - 深蓝色
        draw.rectangle([32, 0, 60, 24], fill=(0, 51, 102, 255))
        # 肩部区域
        draw.rectangle([32, 0, 40, 8], fill=(0, 61, 122, 255))
        draw.rectangle([52, 0, 60, 8], fill=(0, 61, 122, 255))
        # 高光 - 亮青色装饰
        draw.line([(32, 8), (60, 8)], fill=(0, 255, 255, 255), width=2)
        draw.line([(44, 0), (44, 24)], fill=(0, 255, 255, 255), width=2)
        # 能量核心 - 紫色
        draw.rectangle([40, 10, 52, 18], fill=(153, 51, 255, 255))
        draw.rectangle([44, 12, 48, 16], fill=(255, 255, 255, 200))
    
    # 保存图像
    img.save(output_path)
    print(f"✓ 已生成：{output_path}")

# 生成头盔纹理
create_armor_texture(
    'src/main/resources/assets/nutonmod/textures/models/armor/energy_helmet_layer_1.png',
    'helmet'
)

# 生成胸甲纹理
create_armor_texture(
    'src/main/resources/assets/nutonmod/textures/models/armor/energy_chestplate_layer_1.png',
    'chestplate'
)

print("\n护甲纹理模板已生成！")
print("你可以在这些模板基础上进行精细调整。")
