package com.example.cuphead.Controller;

import com.example.cuphead.Models.BossPhaseOne;
import com.example.cuphead.Models.Cuphead;
import com.example.cuphead.Models.Entities.Bullet;
import com.example.cuphead.Models.MiniBoss;
import javafx.scene.shape.Rectangle;

public class Mathematics {
    public static boolean isRectHitting(Rectangle rectangle1, Rectangle rectangle2) {
        if (
                ((rectangle2.getTranslateX()+rectangle2.getX()) + rectangle2.getWidth()/2 - (rectangle1.getTranslateX()+rectangle1.getX()) - rectangle1.getWidth()/2) *
                ((rectangle2.getTranslateX()+rectangle2.getX()) + rectangle2.getWidth()/2 -  (rectangle1.getTranslateX()+rectangle1.getX()) - rectangle1.getWidth()/2)
                + ((rectangle1.getTranslateY()+rectangle1.getY()) + rectangle1.getHeight()/2 - (rectangle2.getTranslateY()+rectangle2.getY()) - rectangle2.getHeight()/2)
                * ((rectangle1.getTranslateY()+rectangle1.getY()) + rectangle1.getHeight()/2 - (rectangle2.getTranslateY()+rectangle2.getY()) - rectangle2.getHeight()/2)
                < (rectangle1.getHeight()/2+rectangle2.getWidth()/2)*
                (rectangle1.getHeight()/2+rectangle2.getWidth()/2)
        )
            return true;
        else return false;
    }
}
