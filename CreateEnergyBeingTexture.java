import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateEnergyBeingTexture {
    public static void main(String[] args) throws IOException {
        // Create 64x64 texture for Minecraft entity
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        
        // Fill transparent background
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                img.setRGB(x, y, 0x00000000);
            }
        }
        
        // Draw head (8x8 pixels in model, use 32x32 area for visibility)
        // Head: x=20-44, y=4-20
        for (int x = 20; x < 44; x++) {
            for (int y = 4; y < 20; y++) {
                int distFromCenter = Math.abs(x - 32) + Math.abs(y - 12);
                if (distFromCenter < 10) {
                    if (distFromCenter < 5) {
                        img.setRGB(x, y, 0xFFFFFFFF); // White center
                    } else {
                        img.setRGB(x, y, 0xFF00FFFF); // Cyan edge
                    }
                }
            }
        }
        
        // Draw body (torso)
        // Body: x=22-42, y=20-40
        for (int x = 22; x < 42; x++) {
            for (int y = 20; y < 40; y++) {
                int distFromCenter = Math.abs(x - 32) + Math.abs(y - 30);
                if (distFromCenter < 9) {
                    if (distFromCenter < 4) {
                        img.setRGB(x, y, 0xFFFFFFFF); // White core
                    } else {
                        img.setRGB(x, y, 0xFF00FFFF); // Cyan body
                    }
                }
            }
        }
        
        // Draw left arm
        for (int x = 12; x < 22; x++) {
            for (int y = 20; y < 36; y++) {
                img.setRGB(x, y, 0xFF00FFFF);
            }
        }
        
        // Draw right arm
        for (int x = 42; x < 52; x++) {
            for (int y = 20; y < 36; y++) {
                img.setRGB(x, y, 0xFF00FFFF);
            }
        }
        
        // Draw left leg
        for (int x = 24; x < 32; x++) {
            for (int y = 40; y < 60; y++) {
                img.setRGB(x, y, 0xFF00FFFF);
            }
        }
        
        // Draw right leg
        for (int x = 32; x < 40; x++) {
            for (int y = 40; y < 60; y++) {
                img.setRGB(x, y, 0xFF00FFFF);
            }
        }
        
        // Add glow particles around the body
        for (int i = 0; i < 50; i++) {
            int px = 16 + (int)(Math.random() * 32);
            int py = 8 + (int)(Math.random() * 48);
            img.setRGB(px, py, 0x60FFFFFF);
        }
        
        // Save texture
        File outputDir = new File("src/main/resources/assets/nutonmod/textures/entity");
        outputDir.mkdirs();
        ImageIO.write(img, "png", new File(outputDir, "energy_being.png"));
        
        System.out.println("Energy Being texture created successfully!");
    }
}
