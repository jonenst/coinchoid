package net.homelinux.paubox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class NewGameActivity extends BaseMenuActivity {

	/*******************
	 **** CONSTANTS ****
	 *******************/

	/************************
	 **** CLASS VARIABLE ****
	 ************************/
	EditText text_players[] = new EditText[4];
	private static final int text_players_ids[] = {
		R.id.name_player1, R.id.name_player2, R.id.name_player3, R.id.name_player4
	};
	CheckBox shuffle_game;
	
	/**************************
	 **** PRIVATE METHODS *****
	 **************************/

	/****************************
	 **** PROTECTED METHODDS ****
	 ****************************/
	// Call the waiting activity
	protected void launchAnnouceActivity() {
		Intent annouce_intent = new Intent(this, AnnounceActivity.class);
		annouce_intent.setAction(Intent.ACTION_DELETE);
		annouce_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		annouce_intent.putExtra("net.homelinux.paubox.Game", current_game);
		startActivity(annouce_intent);
	}


	/*************************
	 **** PUBLIC METHODDS ****
	 *************************/
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game_layout);

		current_game = new Game();

		final Button button_start = (Button) findViewById(R.id.button_new_game_go);
		button_start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i<Game.players_cnt; i++) {
					if ((text_players[i].getText().toString() != null) && (text_players[i].getText().toString().trim().length()>0))
						current_game.setPlayer_name(text_players[i].getText().toString().trim(), Game.players[i]);
					else
						current_game.setPlayer_name(text_players[i].getHint().toString(), Game.players[i]);
				}
				current_game.currentDeal().setShuffleDeal(shuffle_game.isChecked());
				launchAnnouceActivity();
			}
		});

		for (int i = 0; i < Game.players_cnt; i++) {
			text_players[i] = (EditText) findViewById(text_players_ids[i]);
		}
		shuffle_game = (CheckBox) findViewById(R.id.shuffle_game);
	}

}
