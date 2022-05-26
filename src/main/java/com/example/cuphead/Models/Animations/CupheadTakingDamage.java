package com.example.cuphead.Models.Animations;

import com.example.cuphead.Models.Cuphead;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class CupheadTakingDamage extends Transition {
    private Cuphead cuphead;
    public CupheadTakingDamage(Cuphead cuphead)
    {
        this.cuphead=cuphead;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1600));
    }
    @Override
    protected void interpolate(double v) {
        int frame =(int)Math.floor(v*10);
        if(frame%2==0)
        {
            ImagePattern cupheadimage = new ImagePattern(new Image(getClass().getResource("/NeededOnes/NormalPlane.png").toExternalForm()));
            this.cuphead.setFill(cupheadimage);
        }
       else if(frame%2==1)
        {
              this.cuphead.setFill(Color.TRANSPARENT);
        }
    }
}
