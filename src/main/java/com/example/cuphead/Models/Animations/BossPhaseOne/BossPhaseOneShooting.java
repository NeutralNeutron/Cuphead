package com.example.cuphead.Models.Animations.BossPhaseOne;

import com.example.cuphead.Models.BossPhaseOne;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BossPhaseOneShooting extends Transition {
    private final Pane pane;
    private final BossPhaseOne bossPhaseOne;
    public BossPhaseOneShooting(BossPhaseOne bossPhaseOne,Pane pane){
     this.setCycleDuration(Duration.millis(900));
        this.setCycleCount(1);
        this.pane = pane;
        this.bossPhaseOne=bossPhaseOne;
}
    @Override
    protected void interpolate(double v) {
        int frame =(int)Math.floor(v*11.9);
        ImagePattern Boss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BossShoot/"+(frame+1)+".png").toExternalForm()));
        this.bossPhaseOne.setFill(Boss);
    }
}
