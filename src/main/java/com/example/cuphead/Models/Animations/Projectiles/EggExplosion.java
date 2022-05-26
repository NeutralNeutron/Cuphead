package com.example.cuphead.Models.Animations.Projectiles;

import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.Entities.EggProjectile;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class EggExplosion extends Transition {
    private final EggProjectile eggProjectile;
    private final Pane pane;

    public EggExplosion(EggProjectile eggProjectile, Pane pane) {
        this.eggProjectile=eggProjectile;
        this.setCycleDuration(Duration.millis(450));
        this.setCycleCount(1);
        this.pane = pane;
    }

    @Override
    protected void interpolate(double v) {
        int frame =(int)Math.floor(v*3.9);
        ImagePattern eggexplosion = new ImagePattern(new Image(getClass().getResource("/NeededOnes/EggExplosionAssets/"+frame+".png").toExternalForm()));
        this.eggProjectile.setFill(eggexplosion);
    }
}
