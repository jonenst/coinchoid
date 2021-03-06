package net.homelinux.paubox;

import net.homelinux.paubox.Deal.Player;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewGameActivity extends BaseMenuActivity {

	/*******************
	 **** CONSTANTS ****
	 *******************/

	/************************
	 **** CLASS VARIABLE ****
	 ************************/
	EditText text_players[] = new EditText[4];
	RadioButton first_distribution[] = new RadioButton[4];
	private static final int text_players_ids[] = {
		R.id.name_player1, R.id.name_player2, R.id.name_player3, R.id.name_player4
	};
	private static final int text_players_defaults[] = {
		R.string.name_player1, R.string.name_player2, R.string.name_player3, R.string.name_player4
	};
	private static final int first_distribution_ids[] = {
		R.id.first_distribution1, R.id.first_distribution2, R.id.first_distribution3, R.id.first_distribution4
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
		putIntentGame(annouce_intent, current_game);
		startActivity(annouce_intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.newgame_menu, menu);
	    return true;
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
		forcePreferencesCounting(current_game);

		final Button button_start = (Button) findViewById(R.id.button_new_game_go);
		button_start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i<Player.values().length; i++) {
					if ((text_players[i].getText().toString() != null) && (text_players[i].getText().toString().trim().length()>0))
						current_game.setPlayer_name(text_players[i].getText().toString().trim(), Player.values()[i]);
					else
						current_game.setPlayer_name(text_players[i].getHint().toString(), Player.values()[i]);
					if (first_distribution[i].isChecked())
						current_game.setPlayer(Player.values()[i]);
				}
				current_game.currentDeal().setShuffleDeal(shuffle_game.isChecked());
				launchAnnouceActivity();
			}
		});

		for (int i = 0; i < Player.values().length; i++) {
			text_players[i] = (EditText) findViewById(text_players_ids[i]);
			first_distribution[i] = (RadioButton) findViewById(first_distribution_ids[i]);
		}

		Game previous_game = getIntentGame();
		if (previous_game!=null){
			for (int i = 0; i < Player.values().length; i++) {
				text_players[i].setHint(previous_game.getPlayer_name(Player.values()[i]));
			}
		}
		((Button) (findViewById(R.id.clear_names))).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < Player.values().length; i++) {
					text_players[i].setHint(text_players_defaults[i]);
				}
			}
		});
		shuffle_game = (CheckBox) findViewById(R.id.shuffle_game);
	}

}
