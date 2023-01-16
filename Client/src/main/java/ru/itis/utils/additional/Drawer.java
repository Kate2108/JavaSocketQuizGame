package ru.itis.utils.additional;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Drawer {
    private final GraphicsContext g;

    public Drawer(Canvas canvas) {
        this.g = canvas.getGraphicsContext2D();
    }

    public void drawCrown() {
        g.setFill(Color.rgb(108, 61, 242));
        int height = 80;
        int width = 100;
        // right triangle
        double[] xCoordinatesRight = {0, 0, width / 2};
        double[] yCoordinatesRight = {0, height, height};
        g.fillPolygon(xCoordinatesRight, yCoordinatesRight, 3);
        // center triangle
        double[] xCoordinatesCenter = {width / 4, width / 2, 3 * width / 4};
        double[] yCoordinatesCenter = {height, 0, height};
        g.fillPolygon(xCoordinatesCenter, yCoordinatesCenter, 3);
        // left
        double[] xCoordinatesLeft = {width / 2, width, width};
        double[] yCoordinatesLeft = {height, 0, height};
        g.fillPolygon(xCoordinatesLeft, yCoordinatesLeft, 3);
        g.fillRect(0, height + 5, width, 10);
    }
}
