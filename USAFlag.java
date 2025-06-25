import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.Point;

public class USAFlag extends StarPainter implements PlugIn {

    public USAFlag() {
        super(Color.WHITE, generateStarPositions());
    }

    @Override
    public void run(String arg) {
        int width = 950;
        int height = 500;
        ImageProcessor ip = new ColorProcessor(width, height);

        // Draw flag background (red stripes and blue union)
        drawFlag(ip);

        // Draw stars in the blue union
        drawStars(ip, 12);  // Star radius
        new ImagePlus("USA Flag", ip).show();
    }

    private void drawFlag(ImageProcessor ip) {
        int stripeHeight = 38;
        for (int i = 0; i < 13; i++) {
            ip.setColor((i % 2 == 0) ? new Color(191, 10, 48) : Color.WHITE);
            ip.fillRect(0, i * stripeHeight, 950, stripeHeight);
        }

        ip.setColor(new Color(0, 33, 71)); // Navy blue for union
        ip.fillRect(0, 0, 380, stripeHeight * 7);
    }

    private static Point[] generateStarPositions() {
        Point[] stars = new Point[50];
        int count = 0;

        // Constants from US flag spec
        double H = 500.0;
        double starDiameter = 0.0616 * H; // ~30.8
        double starRadius = starDiameter / 2;
        double gapY = 0.054 * H;
        double gapX = 0.063 * H;

        double offsetY = gapY;
        double offsetX = gapX;

        for (int row = 0; row < 9; row++) {
            int starsInRow = (row % 2 == 0) ? 6 : 5;
            for (int col = 0; col < starsInRow; col++) {
                double x = offsetX + col * 2 * gapX;
                if (row % 2 != 0) x += gapX; // indent odd rows
                double y = offsetY + row * gapY;

                stars[count++] = new Point((int) x, (int) y);
            }
        }

        return stars;
    }


}
