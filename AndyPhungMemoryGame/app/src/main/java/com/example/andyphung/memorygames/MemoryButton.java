package com.example.andyphung.memorygames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by andyphung on 9/17/16.
 */
public class MemoryButton extends Button{
    int row;
    int column;
    int frontDrawableId;
    boolean isFlipped = false;
    boolean isMatched = false;

    Drawable front;
    Drawable back;

    public MemoryButton(Context context, int r, int c, int frontImageDrawableID)
    {
        super(context);
        row = r;
        column = c;
        frontDrawableId = frontImageDrawableID;

        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableID);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.picbg);

        setBackground(back);

        GridLayout.LayoutParams tempParams =
                new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));

        tempParams.width = (int) getResources().getDisplayMetrics().density * 10;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 10;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId()
    {
        return frontDrawableId;
    }

    public void flip()
    {
        if(isMatched)
        {
            return;
        }
        if(isFlipped)
        {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped = true;
        }
    }
}
