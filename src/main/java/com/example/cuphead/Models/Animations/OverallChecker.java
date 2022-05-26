package com.example.cuphead.Models.Animations;

import com.example.cuphead.Controller.Mathematics;
import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.BossPhaseOne.BossPhaseOneShooting;
import com.example.cuphead.Models.Animations.MiniBoss.MiniBossFlying;
import com.example.cuphead.Models.Animations.Projectiles.EggProjectileAnimation;
import com.example.cuphead.Models.BossPhaseOne;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.Entities.EggProjectile;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class OverallChecker extends Transition {
    private final Pane pane;
    private final ArrayList<MiniBoss> miniBosses;
    private final BossPhaseOne bossPhaseOne;
    private final Cuphead cuphead;
    private final Rectangle rocketCharge;

    public OverallChecker(Pane pane, ArrayList<MiniBoss> minibosses, BossPhaseOne bossPhaseOne, Cuphead cuphead, Rectangle rocketCharge) {
        this.rocketCharge = rocketCharge;
        this.bossPhaseOne = bossPhaseOne;
        this.miniBosses = minibosses;
        this.pane = pane;
        this.cuphead = cuphead;
        this.setCycleDuration(Duration.millis(3000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (bossPhaseOne.getHealth() <= 0) System.exit(0);
        if (v == 0 || v == 0.5) {
            MiniBoss miniBoss = new MiniBoss(1300, (int) (Math.random() * 100) % 2 == 0 ? 540 : 120, 50, miniBosses);
            pane.getChildren().add(miniBoss);
            miniBosses.add(miniBoss);
            MiniBossFlying miniBossFlying = new MiniBossFlying(miniBoss, pane, cuphead);
            miniBossFlying.play();
            miniBoss.setMiniBossFlying(miniBossFlying);
        }
        if (v == 0) {
            if (cuphead.getRocketcharge() <= 5) cuphead.setRocketcharge(cuphead.getRocketcharge() + 1);
            rocketCharge.setY(720 - 10 * cuphead.getRocketcharge());
            rocketCharge.setHeight(10 * cuphead.getRocketcharge());
            if (cuphead.getRocketcharge() == 6) rocketCharge.setFill(Color.VIOLET);
            BossPhaseOneShooting bossPhaseOneShooting = new BossPhaseOneShooting(bossPhaseOne, pane);
            bossPhaseOneShooting.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/Spit.wav").toExternalForm()));
                    EggProjectile eggProjectile = bossPhaseOne.attack();
                    pane.getChildren().add(eggProjectile);
                    EggProjectileAnimation eggProjectileAnimation = new EggProjectileAnimation(eggProjectile, pane, cuphead);
                    eggProjectileAnimation.play();
                    eggProjectile.setEggProjectileAnimation(eggProjectileAnimation);
                }
            });
            bossPhaseOneShooting.play();
        }
    }
}
