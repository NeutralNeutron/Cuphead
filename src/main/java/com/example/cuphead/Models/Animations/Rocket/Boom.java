package com.example.cuphead.Models.Animations.Rocket;

import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Cuphead;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class Boom extends Transition {
    private Cuphead cuphead;
    public Boom(Cuphead cuphead)
    {
        this.cuphead=cuphead;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(250));
    }
    @Override
    protected void interpolate(double v) {
        ImagePattern Img = new ImagePattern(new Image(getClass().getResource("/NeededOnes/RocketChange/Expo.png").toExternalForm()));
        this.cuphead.setFill(Img);
    }
}
