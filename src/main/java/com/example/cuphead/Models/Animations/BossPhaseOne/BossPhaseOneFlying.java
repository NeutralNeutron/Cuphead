package com.example.cuphead.Models.Animations.BossPhaseOne;

import com.example.cuphead.Controller.Mathematics;
import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.Rocket.Boom;
import com.example.cuphead.Models.BossPhaseOne;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BossPhaseOneFlying extends Transition {
    private final Pane pane;
    private final BossPhaseOne bossPhaseOne;
    private final Cuphead cuphead;

    public BossPhaseOneFlying(BossPhaseOne bossPhaseOne, Pane pane, Cuphead cuphead) {
        this.cuphead = cuphead;
        this.setCycleDuration(Duration.millis(700));
        this.setCycleCount(-1);
        this.pane = pane;
        this.bossPhaseOne = bossPhaseOne;
    }

    public Pane getPane() {
        return pane;
    }

    @Override
    protected void interpolate(double v) {
        if (Mathematics.isRectHitting(cuphead, bossPhaseOne) && !cuphead.isTakingDamage()) {
            cuphead.setTakingDamage(true);
            if (cuphead.isRocket()) {
                bossPhaseOne.takeDamage(cuphead.getRocketdamage());
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
        }
        int frame = (int) Math.floor(v * 5.9);
        ImagePattern Boss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BossFly/" + (frame + 1) + ".png").toExternalForm()));
        this.bossPhaseOne.setFill(Boss);
    }
}
