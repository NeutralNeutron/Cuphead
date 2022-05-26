package com.example.cuphead.Models.Entities;

import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.Projectiles.BulletsExplosionAnimation;
import com.example.cuphead.Models.Animations.Projectiles.EggExplosion;
import com.example.cuphead.Models.Animations.Projectiles.EggProjectileAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EggProjectile extends Rectangle {
    private final int spead=5;
    private EggProjectileAnimation eggProjectileAnimation;
    public EggProjectile(int x, int y) {
        super(x,y,70,70);
        ImagePattern egg = new ImagePattern(new Image(getClass().getResource("/NeededOnes/images/egg.png").toExternalForm()));
        this.setFill(egg);
    }

    public void setEggProjectileAnimation(EggProjectileAnimation eggProjectileAnimation) {
        this.eggProjectileAnimation = eggProjectileAnimation;
    }

    public EggProjectileAnimation getEggProjectileAnimation() {
        return eggProjectileAnimation;
    }
    public void explode(Pane pane) {
        EggProjectile.this.getEggProjectileAnimation().stop();
        PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/EggBoom.wav").toExternalForm()));
        EggExplosion eggExplosion = new EggExplosion(this, pane);
        eggExplosion.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(EggProjectile.this);
            }
        });
        eggExplosion.play();
    }
    public void move() {
        setX(getX()-spead);
    }
}
