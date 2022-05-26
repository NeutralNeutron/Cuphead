package com.example.cuphead.Models;

import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.CupheadTakingDamage;
import com.example.cuphead.Models.Animations.HealthAnimation;
import com.example.cuphead.Models.Animations.Rocket.TurningToBomb;
import com.example.cuphead.Models.Entities.Bullet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cuphead extends Rectangle {
    public Cuphead(Pane pane) {
        super(50, 360, 65, 65);
        this.pane=pane;
        ImagePattern cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/NormalPlane.png").toExternalForm()));
        this.setFill(cupheadimage);
        cupheadTakingDamage.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Cuphead.this.takingDamage=false;
            }
        });
    }
    private CupheadTakingDamage cupheadTakingDamage=new CupheadTakingDamage(this);
    private boolean takingDamage=false;
    private Rectangle healthNode;
    private int health=5;
    private final int speed = 23;
    private boolean rocket=false;
    private int rocketcharge=0;
    private int rocketdamage=100;
    private boolean shootingbombs;
    private final Pane pane;
    public boolean isShootingbombs() {
        return shootingbombs;
    }

    public int getHealth() {
        return health;
    }

    public CupheadTakingDamage getCupheadTakingDamage() {
        return cupheadTakingDamage;
    }

    public int getRocketcharge() {
        return rocketcharge;
    }

    public int getRocketdamage() {
        return rocketdamage;
    }

    public boolean isRocket() {
        return rocket;
    }

    public void setRocket(boolean rocket) {
        this.rocket = rocket;
    }

    public void setRocketcharge(int rocketcharge) {
        this.rocketcharge = rocketcharge;
    }

    public void setRocketdamage(int rocketdamage) {
        this.rocketdamage = rocketdamage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHealthNode(Rectangle healthNode) {
        this.healthNode = healthNode;
    }

    public void takeDamage()
    {
        if(health==1)
            System.exit(0);
        if(health>=2)health--;
        ImagePattern nodeImage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/HealthNodes/"+health+".png").toExternalForm()));
        PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/Hitted.wav").toExternalForm()));
        healthNode.setFill(nodeImage);
        if(health==1)
        new HealthAnimation(pane,healthNode).play();
        cupheadTakingDamage.play();
    }

    public void setTakingDamage(boolean takingDamage) {
        this.takingDamage = takingDamage;
    }

    public boolean isTakingDamage() {
        return takingDamage;
    }

    public void changeShootingbombs() {
        this.shootingbombs = !this.shootingbombs;
    }

    public void moveRight() {
        if (getX() + speed + 65 < 1280) setX(getX() + speed);
    }

    public void moveLeft() {
        if (getX() - speed > 0) setX(getX() - speed);
    }

    public void moveUp() {
        if (getY() - speed > 0) {
            setY(getY() - speed);
            ImagePattern cupheadimage;
            if(!this.isRocket()) {
                cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/Cupheadgoingup.png").toExternalForm()));
            }
            else {
                cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/RocketChange/TurningUp.png").toExternalForm()));
            }
            this.setFill(cupheadimage);
        }
    }

    public void moveDown() {
        if (getY() + speed + 65 < 720) {
            setY(getY() + speed);
            ImagePattern cupheadimage;
            if(!this.isRocket()) {
                cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/Cupheadgoingdown.png").toExternalForm()));
            }
            else {
                cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/RocketChange/TurningDown.png").toExternalForm()));
            }
            this.setFill(cupheadimage);
        }
    }
    public void changeToRocket() {
        rocketcharge=0;
        rocket=true;
        TurningToBomb turningToBomb=new TurningToBomb(this);
        turningToBomb.play();
    }
    public void normalize() {
        ImagePattern cupheadimage;
        if(!this.isRocket()) {
            cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/NormalPlane.png").toExternalForm()));
        }
        else {
            cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/RocketChange/3.png").toExternalForm()));
        }
        this.setFill(cupheadimage);
    }

    public Bullet bulletAttack() {
        return new Bullet((int) this.getX(), (int) this.getY(), shootingbombs);
    }

}
