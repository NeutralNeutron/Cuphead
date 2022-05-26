package com.example.cuphead.Models.Animations.MiniBoss;

import com.example.cuphead.Controller.Mathematics;
import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.Rocket.Boom;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBossFlying extends Transition {
    private final Pane pane;
    private final MiniBoss miniBoss;
    private Cuphead cuphead;

    public MiniBossFlying(MiniBoss miniBoss, Pane pane, Cuphead cuphead) {
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        this.pane = pane;
        this.miniBoss = miniBoss;
        this.cuphead = cuphead;
    }

    public Pane getPane() {
        return pane;
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3.9);
        ImagePattern MiniBoss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/Miniboss/" + (frame + 1) + ".png").toExternalForm()));
        this.miniBoss.setFill(MiniBoss);
        miniBoss.move();
        if (Mathematics.isRectHitting(cuphead, miniBoss) && !cuphead.isTakingDamage()) {
            cuphead.setTakingDamage(true);
            if (cuphead.isRocket()) {
                cuphead.setHeight(110);
                cuphead.setWidth(110);
                Boom boom = new Boom(cuphead);
                boom.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cuphead.setHeight(65);
                        cuphead.setWidth(65);
                        cuphead.setRocket(false);
                        cuphead.getCupheadTakingDamage().play();
                    }
                });
                PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/BigHit.wav").toExternalForm()));
                boom.play();
            } else {
                cuphead.takeDamage();
            }
            miniBoss.explode(miniBoss.getMiniBosses());
        }
        if (miniBoss.getX() <= 0) {
            this.miniBoss.getMiniBossFlying().stop();
            pane.getChildren().remove(miniBoss);
        }
    }
}
