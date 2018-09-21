package com.example.android.cricketscorecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int batsman1Runs = 0;
    int batsman2Runs = 0;
    int batsman1Balls = 0;
    int batsman2Balls = 0;
    int batsman1Fours = 0;
    int batsman2Fours = 0;
    int batsman1Sixes = 0;
    int batsman2Sixes = 0;
    double batsman1SR = 0.0;
    double batsman2SR = 0.0;
    int totalRuns = 0;
    int totalWickets = 0;
    int bowler1Runs = 0;
    int bower2Runs = 0;
    int bower1Wickets = 0;
    int bowler2Wickets = 0;
    double bowler1Overs = 0.0;
    double bowler2Overs = 0.0;
    double bowler1ER = 0.0;
    double bowler2ER = 0.0;
    int batsmanOnStrike = 1;
    int batsmanOnNotStrike = 2;
    String batsmanOnStrikeId = "batsman_text_view_" + batsmanOnStrike;
    String runsOnStrikeId = "runs_text_view_" + batsmanOnStrike;
    String foursOnStrikeId = "fours_text_view_" + batsmanOnStrike;
    String sixesOnStrikeId = "sixes_text_view_" + batsmanOnStrike;
    String strikeRateOnStrikeId = "strikeRate_text_view_" + batsmanOnStrike;
    String batsmanOnNotStrikeId = "batsman_text_view_" + batsmanOnNotStrike;
    String totalRunsId = "totalRuns_text_view";
    String runRateId = "runRate_text_view";
    double totalOvers = 0.0;
    double runRate = 0.0;
    String activeBatsman = "batsman1";

    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addStrikeSign(batsmanOnStrikeId);
    }

    /**
     * This method is to change batsman on strike for runs taken or at the end of the over
     */
    public void setBatsmenOnStrike() {
        batsmanOnStrikeId = "batsman_text_view_" + batsmanOnStrike;
        runsOnStrikeId = "runs_text_view_" + batsmanOnStrike;
        foursOnStrikeId = "fours_text_view_" + batsmanOnStrike;
        sixesOnStrikeId = "sixes_text_view_" + batsmanOnStrike;
        strikeRateOnStrikeId = "strikeRate_text_view_" + batsmanOnStrike;
        batsmanOnNotStrikeId = "batsman_text_view_" + batsmanOnNotStrike;
    }
    /**
     * This method is to reset batsman1's stat if the wicket falls
     */
    public void resetBatsman1(){
        batsman1Runs = 0;
        batsman1Balls = 0;
        batsman1Fours = 0;
        batsman1Sixes = 0;
        batsman1SR = 0.0;
    }
    /**
     * This method is to reset batsman2's stat if the wicket falls
     */
    public void resetBatsman2(){
        batsman2Runs = 0;
        batsman2Balls = 0;
        batsman2Fours = 0;
        batsman2Sixes = 0;
        batsman2SR = 0.0;
    }
    /**
     * This method is to set a value to a view
     */
    public void setValueToView(String idName, String value) {
        int resID = getResources().getIdentifier(idName, "id", getPackageName());
        TextView view = (TextView) findViewById(resID);
        view.setText(value);
    }
    /**
     * This method is to add strike sign for the batsman on strike
     */
    public void addStrikeSign(String batsmanId){
        int resID = getResources().getIdentifier(batsmanId, "id", getPackageName());
        TextView batsman1 = (TextView) findViewById(resID);
        String batsmanName = batsman1.getText().toString();
        batsmanName = batsmanName + "*";
        batsman1.setText(batsmanName);
    }
    /**
     * This method is to remove strike sign for the batsman not on strike
     */
    public void removeStrikeSign(String batsmanId){
        int resID = getResources().getIdentifier(batsmanId, "id", getPackageName());
        TextView batsman2 = (TextView) findViewById(resID);
        String batsmanName = batsman2.getText().toString();
        batsmanName = batsmanName.substring(0, batsmanName.length() - 1);
        batsman2.setText(batsmanName);
    }

    /**
     * This method is to flip the batsman for runs taken, end of the over or wickets and change the star(*) suffix
     */
    public void flipBatsmanOnStrike(boolean flip) {
        if (flip == true) {
            int temp = batsmanOnStrike;
            batsmanOnStrike = batsmanOnNotStrike;
            batsmanOnNotStrike = temp;
            setBatsmenOnStrike();

            addStrikeSign(batsmanOnStrikeId);
            removeStrikeSign(batsmanOnNotStrikeId);

            if (activeBatsman == "batsman1")
                activeBatsman = "batsman2";
            else
                activeBatsman = "batsman1";
        }
    }

    /**
     * This method is to add runs to a batsman
     */
    public void addRuns(int runs, int balls) {
        if (activeBatsman == "batsman1") {
            batsman1Runs = batsman1Runs + runs;
            batsman1Balls = batsman1Balls + balls;
            if (runs == 4)
                batsman1Fours = batsman1Fours + 1;
            else if (runs == 6)
                batsman1Sixes = batsman1Sixes + 1;
            if(batsman1Runs!=0){
                batsman1SR = ((double) batsman1Runs / (double) batsman1Balls) * 100;
            }else{
                batsman1SR = 0.0;
            }

            setValueToView(runsOnStrikeId, batsman1Runs + "(" + batsman1Balls + ")");
            setValueToView(foursOnStrikeId, batsman1Fours + "");
            setValueToView(sixesOnStrikeId, batsman1Sixes + "");
            setValueToView(strikeRateOnStrikeId, df.format(batsman1SR));

        } else if (activeBatsman == "batsman2") {
            batsman2Runs = batsman2Runs + runs;
            batsman2Balls = batsman2Balls + balls;
            if (runs == 4)
                batsman2Fours = batsman2Fours + 1;
            else if (runs == 6)
                batsman2Sixes = batsman2Sixes + 1;
            if(batsman2Runs!=0){
                batsman2SR = ((double) batsman2Runs / (double) batsman2Balls) * 100;
            }else{
                batsman2SR=0.0;
            }

            setValueToView(runsOnStrikeId, batsman2Runs + "(" + batsman2Balls + ")");
            setValueToView(foursOnStrikeId, batsman2Fours + "");
            setValueToView(sixesOnStrikeId, batsman2Sixes + "");
            setValueToView(strikeRateOnStrikeId, df.format(batsman2SR));
        }

        addTotal(runs, balls, 0);

        if (runs % 2 == 1) {
            flipBatsmanOnStrike(true);
        }
    }

    /**
     * This method is to add runs to the total
     */
    public void addTotal(int runs, int balls, int wickets) {
        totalRuns = totalRuns + runs;
        totalOvers = totalOvers + (double) balls / 10;
        totalWickets = totalWickets+wickets;
        if (String.valueOf(totalOvers).contains(".6")) {
            totalOvers = ((int) totalOvers) + 1;
        }
        runRate = totalRuns / ((int) totalOvers + ((totalOvers % 1) * 10 / 6));

        setValueToView(totalRunsId, totalRuns + "-" + totalWickets + " (" + df.format(totalOvers) + ")");
        setValueToView(runRateId, df.format(runRate));
    }

    /**
     * This method is to add one run
     */
    public void oneRun(View view) {
        addRuns(1,1);
    }

    /**
     * This method is to add two runs
     */
    public void twoRun(View view) {
        addRuns(2,1);
    }

    /**
     * This method is to add three runs
     */
    public void threeRun(View view) {
        addRuns(3,1);
    }

    /**
     * This method is to add four runs
     */
    public void fourRun(View view) {
        addRuns(4,1);
    }

    /**
     * This method is to add six runs
     */
    public void sixRun(View view) {
        addRuns(6,1);
    }
    /**
     * This method is called if it is a wide ball
     */
    public void wideBall(View view) {
        addTotal(1, 0, 0);
    }
    /**
     * This method is called if it is a no ball
     */
    public void noBall(View view) {
        addTotal(1, 0, 0);
    }
    /**
     * This method is called if a wicket falls
     */
    public void wicketBall(View view) {
        addTotal(0, 1, 1);

        addRuns(0,1);
        removeStrikeSign(batsmanOnStrikeId);
        batsmanOnStrike = Math.max(batsmanOnStrike, batsmanOnNotStrike) + 1;
        if (activeBatsman == "batsman1"){
            resetBatsman1();
        }else{
            resetBatsman2();
        }
        setBatsmenOnStrike();
        addStrikeSign(batsmanOnStrikeId);
        addRuns(0,0);
    }
}
