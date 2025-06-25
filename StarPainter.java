import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;


public abstract class StarPainter {
    protected Point[] centroids;
    protected Color starColor;

    public StarPainter(Color color, Point[] centroids) {
        this.starColor = color;
        this.centroids = centroids;
    }

    // Draw all stars on the given processor
    public void drawStars(ImageProcessor ip, int radius) {
        ip.setColor(starColor);
        for (Point center : centroids) {
            drawStar(ip, center.x, center.y, radius);
        }
    }

    // Draw a 5-pointed star at (cx, cy)
    private void drawStar(ImageProcessor ip, int cx, int cy, int outerRadius) {
    int innerRadius = (int)(outerRadius * 0.382); // golden ratio approximation

    int[] x = new int[10];
    int[] y = new int[10];
    double angle = -Math.PI / 2; // Start at top

    for (int i = 0; i < 10; i++) {
        double radius = (i % 2 == 0) ? outerRadius : innerRadius;
        x[i] = (int) Math.round(cx + radius * Math.cos(angle));
        y[i] = (int) Math.round(cy + radius * Math.sin(angle));
        angle += Math.PI / 5; // 36 degrees
    }

    Polygon star = new Polygon(x, y, 10);
    ip.fillPolygon(star);
}

}
