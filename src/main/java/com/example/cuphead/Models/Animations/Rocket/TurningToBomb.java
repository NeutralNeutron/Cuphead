package com.example.cuphead.Models.Animations.Rocket;

import com.example.cuphead.Models.Cuphead;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class TurningToBomb extends Transition {
    private Cuphead cuphead;
    public TurningToBomb(Cuphead cuphead)
    {
        this.cuphead=cuphead;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(500));
    }
    @Override
    protected void interpolate(double v) {
        int frame =(int)Math.floor(v*2.9);
        ImagePattern MiniBoss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/RocketChange/"+(frame+1)+".png").toExternalForm()));
        this.cuphead.setFill(MiniBoss);
    }
}
