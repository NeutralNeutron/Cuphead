package com.example.cuphead.Models.Animations.Projectiles;

import com.example.cuphead.Controller.Mathematics;
import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.BossPhaseOne;
import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

public class BulletsAnimations extends Transition {
    private final Bullet bullet;
    private final Pane pane;
    private ArrayList<MiniBoss> minibosses=new ArrayList<>();
    private BossPhaseOne bossPhaseOne;
    public BulletsAnimations(Bullet bullet, Pane pane,ArrayList<MiniBoss>minibosses,BossPhaseOne bossPhaseOne) {
        this.minibosses=minibosses;
        this.bullet = bullet;
        this.bossPhaseOne=bossPhaseOne;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        this.pane = pane;
    }

    @Override
    protected void interpolate(double v) {
        bullet.move();
        boolean ishittinganything=false;
        for(int i=0;i<minibosses.size();i++)
            if(Mathematics.isRectHitting(minibosses.get(i),bullet))
            {
                ishittinganything= true;
                this.stop();
                if(minibosses.get(i).takeDamage(bullet.getDamage()))
                    minibosses.get(i).explode(minibosses);
                break;
            }
        if (Mathematics.isRectHitting(bullet,bossPhaseOne)){
             bossPhaseOne.takeDamage(bullet.getDamage());
            ishittinganything=true;
            this.stop();
        }
        if(ishittinganything)
            if(!bullet.getIsbomb())
            PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/SmallHit.wav").toExternalForm()));
       else
                PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/BigHit.wav").toExternalForm()));
        if (this.bullet.getX() > 1280||this.bullet.getY() > 720||ishittinganything) {
            this.stop();
            bullet.explode(pane);
        }
    }
}
