"""
图像处理脚本：去除白色背景，调整尺寸到 64x64
"""
from PIL import Image

def process_image(input_path, output_path, size=(64, 64)):
    """
    打开图片，去除白色背景，调整尺寸并保存
    """
    try:
        # 打开图片
        img = Image.open(input_path)
        
        # 转换为 RGBA（支持透明通道）
        img = img.convert('RGBA')
        
        # 调整尺寸
        img = img.resize(size, Image.Resampling.LANCZOS)
        
        # 获取像素数据
        datas = img.getdata()
        
        newData = []
        for item in datas:
            # 如果像素接近白色（RGB 都大于 200），则设为透明
            if item[0] > 200 and item[1] > 200 and item[2] > 200:
                newData.append((255, 255, 255, 0))  # 完全透明
            else:
                newData.append(item)
        
        # 应用新的像素数据
        img.putdata(newData)
        
        # 保存图片
        img.save(output_path, 'PNG')
        print(f"✓ 已处理：{output_path}")
        
    except Exception as e:
        print(f"✗ 处理失败 {input_path}: {e}")

# 处理胸甲
process_image(
    'src/main/resources/assets/nutonmod/textures/item/energy_chestplate.png',
    'src/main/resources/assets/nutonmod/textures/item/energy_chestplate_fixed.png'
)

# 如果有头盔图片，也一起处理
import os
if os.path.exists('src/main/resources/assets/nutonmod/textures/item/energy_helmet.png'):
    process_image(
        'src/main/resources/assets/nutonmod/textures/item/energy_helmet.png',
        'src/main/resources/assets/nutonmod/textures/item/energy_helmet_fixed.png'
    )
else:
    print("⚠ 未找到 energy_helmet.png 文件")

print("\n处理完成！请将 _fixed.png 文件重命名为原文件名替换使用。")
