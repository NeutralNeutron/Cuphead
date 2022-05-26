package com.example.cuphead.Models;

import com.example.cuphead.Models.Animations.BossPhaseOne.BossPhaseOneFlying;
import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.Entities.EggProjectile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BossPhaseOne extends Rectangle {
    private int health;
    private BossPhaseOneFlying bossPhaseOneFlying;
    private Rectangle remaintHealth;
    private Rectangle doneHealth;
    private Label healthStr;
    public BossPhaseOne(int x, int y, int health) {
        super(x, y, 300, 300);
        this.health = health;
        ImagePattern Boss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BossFly/1.png").toExternalForm()));
        this.setFill(Boss);
    }

    public int getHealth() {
        return health;
    }
    public void takeDamage(int x)
    {
        health-=x;
        remaintHealth.setWidth((int)((health*300)/2000));
        remaintHealth.setX(980+(int)(((2000-health)*300)/2000));
        doneHealth.setWidth((int)(((2000-health)*300)/2000));
        healthStr.setText(Integer.toString(this.getHealth()));
    }
    public void setDoneHealth(Rectangle doneHealth) {
        this.doneHealth = doneHealth;
    }

    public void setRemaintHealth(Rectangle remaintHealth) {
        this.remaintHealth = remaintHealth;
    }

    public void setHealthStr(Label healthStr) {
        this.healthStr = healthStr;
    }

    public void setBossPhaseOneFlying(BossPhaseOneFlying bossPhaseOneFlying) {
        this.bossPhaseOneFlying = bossPhaseOneFlying;
    }

    public BossPhaseOneFlying getBossPhaseOneFlying() {
        return bossPhaseOneFlying;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public  EggProjectile attack(){
    return new EggProjectile((int)(this.getX()+this.getTranslateX()),
             (int)(this.getTranslateY()+this.getY()+150));
    }
}
