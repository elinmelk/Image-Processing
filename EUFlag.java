import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.Point;

public class EUFlag extends StarPainter implements PlugIn {

    public EUFlag() {
        super(new Color(255, 204, 0), generateCircleStars());
    }

    @Override
    public void run(String arg) {
        int width = 540, height = 360;
        ImageProcessor ip = new ColorProcessor(width, height);

        // Blue background
        ip.setColor(new Color(0, 51, 153));
        ip.fillRect(0, 0, width, height);

        // Draw 12 stars in circle
        drawStars(ip, 20);
        new ImagePlus("EU Flag", ip).show();
    }

    private static Point[] generateCircleStars() {
        Point[] stars = new Point[12];
        int cx = 270, cy = 180, radius = 120;

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(-90 + i * 30);
            int x = cx + (int) (Math.cos(angle) * radius);
            int y = cy + (int) (Math.sin(angle) * radius);
            stars[i] = new Point(x, y);
        }
        return stars;
    }
}
