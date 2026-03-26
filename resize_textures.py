"""
调整图片尺寸到 64x64
"""
from PIL import Image

def resize_image(input_path, output_path, size=(64, 64)):
    """
    调整图片尺寸并保存
    """
    try:
        img = Image.open(input_path)
        # 调整为 64x64
        img = img.resize(size, Image.Resampling.LANCZOS)
        # 保存
        img.save(output_path, 'PNG')
        print(f"✓ 已调整尺寸：{output_path}")
        print(f"  原尺寸：{img.size}")
        print(f"  新尺寸：{size}")
    except Exception as e:
        print(f"✗ 处理失败：{e}")

# 调整胸甲尺寸
resize_image(
    'src/main/resources/assets/nutonmod/textures/item/energy_chestplate.png',
    'src/main/resources/assets/nutonmod/textures/item/energy_chestplate_64x64.png'
)

# 调整头盔尺寸（如果存在）
import os
if os.path.exists('src/main/resources/assets/nutonmod/textures/item/energy_helmet.png'):
    resize_image(
        'src/main/resources/assets/nutonmod/textures/item/energy_helmet.png',
        'src/main/resources/assets/nutonmod/textures/item/energy_helmet_64x64.png'
    )

print("\n完成！将 _64x64.png 文件重命名为原文件名替换即可。")
