from PIL import Image

# 打开基础贴图
img = Image.open('src/main/resources/assets/nutonmod/textures/block/energy_block.png')

# 保存为门的上下两张贴图
img.save('src/main/resources/assets/nutonmod/textures/block/energy_door_bottom.png')
img.save('src/main/resources/assets/nutonmod/textures/block/energy_door_top.png')

print("✓ 已创建门贴图:")
print("  - energy_door_bottom.png (使用 energy_block.png)")
print("  - energy_door_top.png (使用 energy_block.png)")
