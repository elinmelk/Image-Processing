import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.Polygon;

public class OldArmFlag implements PlugIn {

    public void run(String arg) {
        int w = 30;
        int width = 18 * w;
        int height = 9 * w;
        ImageProcessor ip = new ColorProcessor(width, height);

        // Armenian tricolor
        ip.setColor(new Color(160, 0, 24)); // Red
        ip.fillRect(0, 0, width, height / 3);
        ip.setColor(new Color(31, 26, 65)); // Blue
        ip.fillRect(0, height / 3, width, height / 3);
        ip.setColor(new Color(255, 112, 45)); // Orange
        ip.fillRect(0, 2 * height / 3, width, height / 3);

        // Center coordinates for shields
        int centerX = width / 2;
        int bottomY = height - 50;

        // Draw large shield (with Masis only)
        drawShieldWithMountain(ip, centerX - 5 * w, bottomY - 5 * w, 4 * w, 5 * w, true);

        // Draw small shield (with Sis only)
        drawShieldWithMountain(ip, centerX + w, bottomY - 3 * w, 2 * w, 3 * w, false);

        new ImagePlus("Armenian Tricolor + Shields", ip).show();
    }

    private void drawShieldWithMountain(ImageProcessor ip, int x, int y, int shieldW, int shieldH, boolean isLarge) {
        // Shield shape (simple medieval style)
        Polygon shield = new Polygon();
        shield.addPoint(x, y);
        shield.addPoint(x + shieldW, y);
        shield.addPoint(x + shieldW, y + shieldH - 10);
        shield.addPoint(x + shieldW / 2, y + shieldH);
        shield.addPoint(x, y + shieldH - 10);

        ip.setColor(new Color(245, 235, 220));
        ip.fillPolygon(shield);
        ip.setColor(Color.BLACK);
        ip.drawPolygon(shield);

        // Coordinates for mountain
        int baseX = x + shieldW / 2;
        int baseY = y + shieldH - 12;

        Color mountainColor = new Color(173, 216, 230); // Light blue

        if (isLarge) {
            // Masis only
            int masisSize = shieldW / 2;
            Polygon masis = new Polygon();
            masis.addPoint(baseX - masisSize, baseY);
            masis.addPoint(baseX + masisSize, baseY);
            masis.addPoint(baseX, baseY - masisSize);
            ip.setColor(mountainColor);
            ip.fillPolygon(masis);
        } else {
            // Sis only
            int sisSize = shieldW / 2;
            Polygon sis = new Polygon();
            sis.addPoint(baseX - sisSize / 2, baseY);
            sis.addPoint(baseX + sisSize / 2, baseY);
            sis.addPoint(baseX, baseY - sisSize);
            ip.setColor(mountainColor);
            ip.fillPolygon(sis);
        }
    }
}
