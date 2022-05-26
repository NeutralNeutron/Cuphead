package com.example.cuphead;

import com.example.cuphead.Controller.PlayMedia;
import com.example.cuphead.Models.Animations.BossPhaseOne.BossPhaseOneFlying;
import com.example.cuphead.Models.Animations.MiniBoss.MiniBossFlying;
import com.example.cuphead.Models.Animations.Projectiles.BulletsAnimations;
import com.example.cuphead.Models.Animations.OverallChecker;
import com.example.cuphead.Models.BossPhaseOne;
import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.MiniBoss;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GameApplication extends Application {
    private Cuphead ourmostbelovedcuphead;
    private ArrayList<MiniBoss> minibosses = new ArrayList<>();
    private ColorAdjust scaler = new ColorAdjust();
    private Circle logo;
    private Rectangle healthNode;
    private BossPhaseOne bossPhaseOne;
    private Rectangle rocketChargeNode;

    @Override
    public void start(Stage stage) throws Exception {
        Pane gamepane = FXMLLoader.load(getClass().getResource("/com/example/cuphead/game.fxml"));
        createBackground(gamepane);
        ourmostbelovedcuphead = createCuphead(gamepane, stage);
        BossPhaseOne bossPhaseOnetemp = createBossPhaseOne(gamepane);
        bossPhaseOne = bossPhaseOnetemp;
        Label timer = createLabel(gamepane);
        createBossHealth(bossPhaseOne, gamepane);
        logo = createBulletLogo(gamepane);
        rocketChargeNode = rocketCharge(gamepane);
        OverallChecker overallChecker = new OverallChecker
                (gamepane, minibosses, bossPhaseOne, ourmostbelovedcuphead, rocketChargeNode);
        overallChecker.play();
        healthNode = createHealthLogo(gamepane);
        ourmostbelovedcuphead.setHealthNode(healthNode);
        Scene scene = new Scene(gamepane);
        stage.setScene(scene);
        stage.setResizable(false);
        gamepane.getChildren().get(2).requestFocus();
        stage.show();
    }

    private Cuphead createCuphead(Pane pane, Stage stage) {
        final long[] lastpressprocessed = {0};
        Cuphead hero = new Cuphead(pane);
        pane.getChildren().add(hero);
        hero.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                switch (keyName) {
                    case "Left":
                        hero.moveLeft();
                        break;
                    case "Right":
                        hero.moveRight();
                        break;
                    case "Up":
                        hero.moveUp();
                        break;
                    case "Down":
                        hero.moveDown();
                        break;
                    case "Esc":
                        //timeline.pause();
                        break;
                    case "B":
                        scaler.setSaturation(-scaler.getSaturation() - 1);
                        stage.getScene().getRoot().setEffect(scaler);
                        break;
                    case "M":
                        mediaPlayer.setVolume(-mediaPlayer.getVolume() + 0.35);
                        break;
                    case "P":
                        mediaPlayer.pause();
                        break;
                    case "C":
                        mediaPlayer.play();
                        break;
                    case "Space":
                        if (System.currentTimeMillis() - lastpressprocessed[0] > 250 && !hero.isRocket()) {
                            createBullet(hero, pane);
                            lastpressprocessed[0] = System.currentTimeMillis();
                        }
                        break;
                    case "Tab":
                        ImagePattern logoimage;
                        if (!hero.isShootingbombs()) {
                            logoimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BulletLogos/BombLogo.png").toExternalForm()));
                        } else {
                            logoimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BulletLogos/BulletLogo.png").toExternalForm()));
                        }
                        logo.setFill(logoimage);
                        hero.changeShootingbombs();
                        break;
                    case "R":
                        if (hero.getRocketcharge() == 6 && !hero.isRocket()) {
                            hero.changeToRocket();
                            rocketChargeNode.setFill(Color.CYAN);
                        }
                        break;
                }
            }
        });
        hero.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().getName().equals("Down")) {
                    hero.normalize();
                } else if (keyEvent.getCode().getName().equals("Up")) {
                    hero.normalize();
                }
            }
        });
        return hero;
    }

    private void createBullet(Cuphead hero, Pane pane) {
        Bullet bullet = hero.bulletAttack();
        pane.getChildren().add(bullet);
        BulletsAnimations bulletsAnimations = new BulletsAnimations(bullet, pane, minibosses, bossPhaseOne);
        bulletsAnimations.play();
        bullet.setBulletsAnimations(bulletsAnimations);
        if (!hero.isShootingbombs())
            PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/Click3.wav").toExternalForm()));
        else PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/Phew.wav").toExternalForm()));
    }

    MediaPlayer mediaPlayer;

    private void createBackground(Pane pane) {
        Media backgroundmudic = new Media(getClass().getResource("/NeededOnes/Sounds/BackgroundMusic.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(backgroundmudic);
        mediaPlayer.setVolume(0.35);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
            }
        });
        mediaPlayer.play();
        ImageView background1 = new ImageView(getClass().getResource("/NeededOnes/Backgrounds/MainBackground.png").toExternalForm());
        ImageView background2 = new ImageView(getClass().getResource("/NeededOnes/Backgrounds/MainBackground.png").toExternalForm());
        pane.getChildren().add(background1);
        pane.getChildren().add(background2);
        TranslateTransition trans1 = new TranslateTransition(Duration.seconds(14), background1);
        trans1.setFromX(0);
        trans1.setToX(-3111);
        trans1.setCycleCount(-1);
        trans1.setInterpolator(Interpolator.LINEAR);
        TranslateTransition trans2 = new TranslateTransition(Duration.seconds(14), background2);
        trans2.setFromX(3111);
        trans2.setToX(0);
        trans2.setCycleCount(-1);
        trans2.setInterpolator(Interpolator.LINEAR);
        ParallelTransition partrans = new ParallelTransition(trans1, trans2);
        partrans.play();
    }

    AudioClip announcerplayer;

    private BossPhaseOne createBossPhaseOne(Pane pane) {
        announcerplayer = PlayMedia.playMusic(new Media(getClass().getResource("/NeededOnes/Sounds/Announcer.wav").toExternalForm()));
        BossPhaseOne bossPhaseOne = new BossPhaseOne(970, 0, 2000);
        pane.getChildren().add(bossPhaseOne);
        BossPhaseOneFlying bossPhaseOneFlying = new BossPhaseOneFlying(bossPhaseOne, pane, ourmostbelovedcuphead);
        bossPhaseOneFlying.play();
        bossPhaseOne.setBossPhaseOneFlying(bossPhaseOneFlying);
        TranslateTransition trans1 = new TranslateTransition(Duration.seconds(7), bossPhaseOne);
        trans1.setFromY(0);
        trans1.setToY(420);
        trans1.setCycleCount(1);
        trans1.setInterpolator(Interpolator.LINEAR);
        TranslateTransition trans2 = new TranslateTransition(Duration.seconds(7), bossPhaseOne);
        trans2.setFromY(420);
        trans2.setToY(0);
        trans2.setCycleCount(1);
        trans2.setInterpolator(Interpolator.LINEAR);
        trans1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                trans2.play();
            }
        });
        trans2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                trans1.play();
            }
        });
        trans1.play();
        return bossPhaseOne;
    }

    public Circle createBulletLogo(Pane pane) {
        ImagePattern logoimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/BulletLogos/BulletLogo.png").toExternalForm()));
        Circle templogo = new Circle(25, 675, 25, logoimage);
        pane.getChildren().add(templogo);
        return templogo;
    }

    public Rectangle createHealthLogo(Pane pane) {
        ImagePattern logoImage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/HealthNodes/5.png").toExternalForm()));
        Rectangle healthNode = new Rectangle(0, 700, 50, 20);
        healthNode.setFill(logoImage);
        pane.getChildren().add(healthNode);
        return healthNode;
    }

    private Rectangle rocketCharge(Pane pane) {
        Rectangle Node = new Rectangle(50, 720, 25, 0);
        Node.setFill(Color.CYAN);
        pane.getChildren().add(Node);
        return Node;
    }

    private long endTime = System.currentTimeMillis();

    public Label createLabel(Pane pane) {
        Label timer = new Label();
        timer.setLayoutX(0);
        timer.setLayoutY(0);
        final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            long diff = (System.currentTimeMillis() - endTime) / 1000;
            timer.setText(String.format("%02d", diff / 60) + ":" + String.format("%02d", diff % 60));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        pane.getChildren().add(timer);
        return timer;
    }

    public void createBossHealth(BossPhaseOne bossPhaseOne, Pane pane) {
        Label health = new Label(Integer.toString(bossPhaseOne.getHealth()));
        health.setLayoutX(800);
        health.setLayoutY(0);
        pane.getChildren().add(health);
        Rectangle remaintHealth = new Rectangle(980, 0, 300, 20);
        Rectangle doneHealth = new Rectangle(980, 0, 0, 20);
        remaintHealth.setFill(Color.RED);
        doneHealth.setFill(Color.WHITE);
        pane.getChildren().add(remaintHealth);
        pane.getChildren().add(doneHealth);
        bossPhaseOne.setDoneHealth(doneHealth);
        bossPhaseOne.setHealthStr(health);
        bossPhaseOne.setRemaintHealth(remaintHealth);
    }

    public static void main(String[] args) {
        launch();
    }
}