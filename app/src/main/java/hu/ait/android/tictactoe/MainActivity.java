package hu.ait.android.tictactoe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        layoutContent = findViewById(R.id.layoutcontent);

        final TicTacToeView ticTacView = findViewById(R.id.ticTacToeView);
        Button clearButton = findViewById(R.id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacView.clearBoard();
                setNextPlayer("O");

            }
        });

        ShimmerFrameLayout shimmerView = findViewById(R.id.shimmer_view);
        shimmerView.startShimmerAnimation();

    }

    public void setNextPlayer(String nextPlayer) {
        String text = getString(R.string.text_next_player, nextPlayer);
        tvStatus.setText(text);

        Snackbar.make(layoutContent, "Next turn: " + text, Snackbar.LENGTH_LONG).setAction(
                "Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                }
        ).show();

    }

    public void showWinner(String winner) {
        String winnerText = getString(R.string.winner_text, winner);
        tvStatus.setText(winnerText);
        Snackbar.make(layoutContent, winnerText, Snackbar.LENGTH_LONG).show();

    }

}
