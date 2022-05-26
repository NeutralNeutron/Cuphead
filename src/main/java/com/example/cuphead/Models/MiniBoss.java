package com.example.cuphead.Models;

import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.MiniBoss.MiniBossExploding;
import com.example.cuphead.Models.Animations.MiniBoss.MiniBossFlying;
import com.example.cuphead.Models.Entities.EggProjectile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniBoss extends Rectangle {
    private ArrayList<MiniBoss> miniBosses = new ArrayList<>();
    private int health;
    private final int speed = 3;
    private MiniBossFlying miniBossFlying;

    public MiniBoss(int x, int y, int health, ArrayList<MiniBoss> miniBosses) {
        super(x, y, 68, 68);
        this.miniBosses = miniBosses;
        this.health = health;
        ImagePattern MiniBoss = new ImagePattern(new Image(getClass().getResource("/NeededOnes/Miniboss/1.png").toExternalForm()));
        this.setFill(MiniBoss);
    }

    public MiniBossFlying getMiniBossFlying() {
        return miniBossFlying;
    }

    public void setMiniBossFlying(MiniBossFlying miniBossFlying) {
        this.miniBossFlying = miniBossFlying;
    }

    public int getHealth() {
        return health;
    }

    public void explode(ArrayList<MiniBoss> minibosses) {
        MiniBoss.this.getMiniBossFlying().stop();
        MiniBossExploding miniBossExploding = new MiniBossExploding(this);
        miniBossExploding.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                minibosses.remove(MiniBoss.this);
                MiniBoss.this.getMiniBossFlying().getPane().getChildren().remove(MiniBoss.this);
            }
        });
        PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/MiniBossBoom.wav").toExternalForm()));
        minibosses.remove(this);
        miniBossExploding.play();
    }

    public ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public void move() {
        this.setX(getX() - speed);
    }

    public boolean takeDamage(int amount) {
        health -= amount;
        return health <= 0;
    }
}
