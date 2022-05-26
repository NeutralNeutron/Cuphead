package com.example.cuphead.Models.Animations.Projectiles;

import com.example.cuphead.Models.Entities.Bullet;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BulletsExplosionAnimation extends Transition {
    private final Bullet bullet;
    private final Pane pane;

    public BulletsExplosionAnimation(Bullet bullet, Pane pane) {
        this.bullet = bullet;
        this.setCycleDuration(Duration.millis(150));
        this.setCycleCount(1);
        this.pane = pane;
    }

    @Override
    protected void interpolate(double v) {
        if(!this.bullet.getIsbomb()) {
            ImagePattern bulletexplosion = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BulletExplosion.png").toExternalForm()));
            this.bullet.setFill(bulletexplosion);
        }
        else{
            ImagePattern bombexplosion = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BombExplosion.png").toExternalForm()));
            this.bullet.setFill(bombexplosion);
        }
    }
}
