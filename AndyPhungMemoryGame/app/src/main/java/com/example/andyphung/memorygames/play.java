package com.example.andyphung.memorygames;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class play extends AppCompatActivity implements View.OnClickListener{

    int numberOfElements;
    int point = 0;
    private MemoryButton [] buttons;
    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;

    boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.playLayout);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];

        buttonGraphics = new int[numberOfElements/2];

        buttonGraphics[0] = R.drawable.pic1;
        buttonGraphics[1] = R.drawable.pic2;
        buttonGraphics[2] = R.drawable.pic3;
        buttonGraphics[3] = R.drawable.pic4;
        buttonGraphics[4] = R.drawable.pic5;
        buttonGraphics[5] = R.drawable.pic6;
        buttonGraphics[6] = R.drawable.pic7;
        buttonGraphics[7] = R.drawable.pic8;
        buttonGraphics[8] = R.drawable.pic9;
        buttonGraphics[9] = R.drawable.pic10;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtons();

        for(int r = 0; r < numRows; r++){
            for (int c = 0; c < numColumns; c++){
                MemoryButton tempButton =
                        new MemoryButton(this,r,c,buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }
        displayPoints();
    }

    private void shuffleButtons() {

        Random rand = new Random();

        for(int i = 0; i < numberOfElements; i++)
        {
            buttonGraphicLocations[i] = i % (numberOfElements /2);
        }

        for(int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(20);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }
    }

    public void displayPoints(){
        TextView pointsText = (TextView) findViewById(R.id.point);
        pointsText.setText("Points: " + point);
    }
    @Override
    public void onClick(View v)
    {

        MemoryButton button = (MemoryButton) v;
        if(isBusy){
            return;
        }
        if(button.isMatched)
        {
            return;
        }
        if(selectedButton1 == null)
        {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }
        if(selectedButton1.getId() == button.getId())
        {
            return;
        }

        if(selectedButton1.getFrontDrawableId() == button.getFrontDrawableId())
        {
            point++;
            button.flip();

            button.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1 = null;
            displayPoints();
            return;
        }
        else
        {
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;
                }
            }, 500);
        }
    }


}
