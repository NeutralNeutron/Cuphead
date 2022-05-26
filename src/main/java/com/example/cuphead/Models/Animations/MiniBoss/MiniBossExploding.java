package com.example.cuphead.Models.Animations.MiniBoss;

import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBossExploding extends Transition {
    private final MiniBoss miniBoss;
    public MiniBossExploding(MiniBoss miniBoss) {
        this.miniBoss=miniBoss;
        this.setCycleDuration(Duration.millis(150));
        this.setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        ImagePattern minibossexplosion = new ImagePattern(new Image(getClass().getResource("/NeededOnes/MiniBossExploding.png").toExternalForm()));
        this.miniBoss.setFill(minibossexplosion);
    }
}
