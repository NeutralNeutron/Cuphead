package com.example.cuphead.Models.Entities;

import com.example.cuphead.Models.Animations.Projectiles.BulletsAnimations;
import com.example.cuphead.Models.Animations.Projectiles.BulletsExplosionAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    private final int speed = 10;
    private final boolean isbomb;
    private final int damage;
    private BulletsAnimations bulletsAnimations;

    public Bullet(int x, int y, boolean isbomb) {
        super(x + 65, y + 32, isbomb ? 38 : 15, isbomb ? 38 : 15);
        this.isbomb = isbomb;
        if (isbomb) this.damage = 50;
        else this.damage = 25;
        ImagePattern bulletimage = new ImagePattern(new Image(getClass().getResource(isbomb ? "/NeededOnes/Bomb.png" : "/NeededOnes/Bullet.png").toExternalForm()));
        this.setFill(bulletimage);
    }

    public int getDamage() {
        return damage;
    }

    public boolean getIsbomb() {
        return isbomb;
    }

    public void move() {
        if (!isbomb) setX(getX() + speed);
        else setY(getY() + speed);
    }

    public void setBulletsAnimations(BulletsAnimations bulletsAnimations) {
        this.bulletsAnimations = bulletsAnimations;
    }

    public BulletsAnimations getBulletsAnimations() {
        return bulletsAnimations;
    }

    public void explode(Pane pane) {
        BulletsExplosionAnimation bulletsExplosionAnimation = new BulletsExplosionAnimation(this, pane);
        bulletsExplosionAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Bullet.this.getBulletsAnimations().stop();
                pane.getChildren().remove(Bullet.this);
            }
        });
        bulletsExplosionAnimation.play();
    }
}