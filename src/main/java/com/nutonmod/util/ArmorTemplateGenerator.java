package com.nutonmod.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 生成 Minecraft 盔甲贴图空白模板
 */
public class ArmorTemplateGenerator {
    
    public static void main(String[] args) throws IOException {
        // 创建 64x32 的透明图像
        BufferedImage template = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = template.createGraphics();
        
        // 启用抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 设置透明背景
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, 64, 32);
        
        // 绘制网格线（辅助线，半透明）
        g2d.setColor(new Color(128, 128, 128, 50));
        g2d.setStroke(new BasicStroke(1));
        
        // 水平线（16 像素处分界线）
        g2d.drawLine(0, 16, 64, 16);
        
        // 垂直辅助线
        for (int x = 0; x <= 64; x += 16) {
            g2d.drawLine(x, 0, x, 32);
        }
        
        // 绘制区域标签（灰色文字）
        g2d.setColor(new Color(200, 200, 200, 150));
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        
        // 上半部分 - 头盔区域 (0-16 行)
        g2d.drawString("HELMET - Head", 20, 10);
        
        // 下半部分 - 胸甲 + 手臂区域 (16-32 行)
        g2d.drawString("CHEST - Body", 24, 24);
        g2d.drawString("L-Arm", 4, 24);
        g2d.drawString("R-Arm", 52, 24);
        
        // 绘制边框（白色，半透明）
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(1, 1, 62, 30);
        
        g2d.dispose();
        
        // 保存为 PNG
        File outputFile = new File("armor_template_layer1.png");
        ImageIO.write(template, "png", outputFile);
        System.out.println("模板已生成：armor_template_layer1.png");
        
        // 同时生成 layer_2 模板（只有胸甲外层）
        BufferedImage template2 = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d_2 = template2.createGraphics();
        
        // 透明背景
        g2d_2.setColor(new Color(0, 0, 0, 0));
        g2d_2.fillRect(0, 0, 64, 32);
        
        // 只在下半部分中间绘制胸甲外层区域
        g2d_2.setColor(new Color(255, 200, 200, 80));
        g2d_2.fillRect(16, 16, 32, 16);
        
        g2d_2.setColor(new Color(200, 200, 200, 150));
        g2d_2.setFont(new Font("Arial", Font.BOLD, 9));
        g2d_2.drawString("CHEST Overlay", 22, 26);
        
        g2d_2.dispose();
        
        File outputFile2 = new File("armor_template_layer2.png");
        ImageIO.write(template2, "png", outputFile2);
        System.out.println("模板已生成：armor_template_layer2.png");
    }
}
