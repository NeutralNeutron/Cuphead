package com.example.cuphead.Models.Animations.Projectiles;

import com.example.cuphead.Controller.Mathematics;
import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.Rocket.Boom;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.Entities.EggProjectile;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class EggProjectileAnimation extends Transition {
    private final EggProjectile eggProjectile;
    private final Pane pane;
    private final Cuphead cuphead;

    public EggProjectileAnimation(EggProjectile eggProjectile, Pane pane, Cuphead cuphead) {
        this.pane = pane;
        this.eggProjectile = eggProjectile;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        this.cuphead = cuphead;
    }

    @Override
    protected void interpolate(double v) {
        eggProjectile.move();
        boolean ishitting = false;
        if (Mathematics.isRectHitting(cuphead, this.eggProjectile) && !cuphead.isTakingDamage()) {
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
            ishitting = true;
            this.eggProjectile.setWidth(108);
            this.eggProjectile.setHeight(108);
        }
        if (eggProjectile.getX() <= 0 || ishitting) {
            this.stop();
            eggProjectile.explode(pane);
        }
    }
}
