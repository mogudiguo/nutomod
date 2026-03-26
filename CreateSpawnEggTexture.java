import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateSpawnEggTexture {
    public static void main(String[] args) throws IOException {
        // Create 64x64 texture for spawn egg
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        
        // Fill with base color (cyan - main egg color)
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                // Create egg shape
                int centerX = 32;
                int centerY = 32;
                double dist = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                
                if (dist < 28) {
                    // Main body - cyan
                    img.setRGB(x, y, 0xFF00FFFF);
                } else {
                    // Transparent outside
                    img.setRGB(x, y, 0x00000000);
                }
            }
        }
        
        // Add white spots (speckles)
        for (int i = 0; i < 30; i++) {
            int px = 20 + (int)(Math.random() * 24);
            int py = 20 + (int)(Math.random() * 24);
            int size = 1 + (int)(Math.random() * 2);
            
            for (int dx = -size; dx <= size; dx++) {
                for (int dy = -size; dy <= size; dy++) {
                    if (dx*dx + dy*dy <= size*size) {
                        int spotX = px + dx;
                        int spotY = py + dy;
                        if (spotX >= 0 && spotX < 64 && spotY >= 0 && spotY < 64) {
                            img.setRGB(spotX, spotY, 0xFFFFFFFF);
                        }
                    }
                }
            }
        }
        
        // Add some shading to make it look more 3D
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                int pixel = img.getRGB(x, y);
                if (pixel != 0x00000000) {
                    // Darken edges slightly
                    if (x < 10 || x > 54 || y < 10 || y > 54) {
                        int r = (pixel >> 16) & 0xFF;
                        int g = (pixel >> 8) & 0xFF;
                        int b = pixel & 0xFF;
                        r = Math.max(0, r - 30);
                        g = Math.max(0, g - 30);
                        b = Math.max(0, b - 30);
                        img.setRGB(x, y, (0xFF << 24) | (r << 16) | (g << 8) | b);
                    }
                }
            }
        }
        
        // Save texture
        File outputDir = new File("src/main/resources/assets/nutonmod/textures/item");
        outputDir.mkdirs();
        ImageIO.write(img, "png", new File(outputDir, "energy_being_spawn_egg.png"));
        
        System.out.println("Spawn egg texture created successfully!");
    }
}
