package net.homelinux.paubox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

public class EditActivity extends Activity {

	Deal d;
	public void configureEditView() {

		AnnounceActivity.configureDealView(this,d);
		final RadioButton radio_us = (RadioButton) findViewById(R.id.button_Us);
		final RadioButton radio_them = (RadioButton) findViewById(R.id.button_Them);
		final Spinner score_spinner = (Spinner) findViewById(R.id.bet_spinner);
		final Spinner coinche_spinner = (Spinner) findViewById(R.id.coinche_spinner);
		Button edit_ok = ((Button) findViewById(R.id.edit_button_ok));
		edit_ok.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						AnnounceActivity.saveDeal(EditActivity.this, d, radio_us, radio_them, score_spinner,
								coinche_spinner);
						setResult(BaseMenuActivity.REQUEST_CODE_EDIT, new Intent().putExtra("net.homelinux.paubox.edit",d));
						finish();
					}});
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		d = (Deal)getIntent().getSerializableExtra("net.homelinux.paubox.Editable");
		setContentView(R.layout.edit_layout);
		configureEditView();
	}
}