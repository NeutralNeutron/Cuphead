package com.example.cuphead.Models.Animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HealthAnimation extends Transition {
    private final Pane pane;
    private final Rectangle healthnode;

    public HealthAnimation(Pane pane, Rectangle healthnode) {
        this.healthnode = healthnode;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(INDEFINITE);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 2.9);
        ImagePattern warnPicture = null;
        if (frame == 0)
            warnPicture = new ImagePattern(new Image(getClass().getResource("/NeededOnes/HealthNodes/1.png").toExternalForm()));
        else if (frame == 1)
            warnPicture = new ImagePattern(new Image(getClass().getResource("/NeededOnes/HealthNodes/1W.png").toExternalForm()));
        else if (frame == 2)
            warnPicture = new ImagePattern(new Image(getClass().getResource("/NeededOnes/HealthNodes/1R.png").toExternalForm()));
        this.healthnode.setFill(warnPicture);
    }
}
