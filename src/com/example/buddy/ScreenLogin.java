package com.example.buddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class ScreenLogin extends Activity {

	private Button skip;
	private LoginButton loginButton;
	private CallbackManager callbackManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getApplication());
		setContentView(R.layout.screen_login);
		skip = (Button) findViewById(R.id.skipLoginButton);
		skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(ScreenLogin.this, MainScreen.class);
				startActivity(i);

			}
		});
		loginButton = (LoginButton) findViewById(R.id.login_button);
		loginButton.setReadPermissions("user_friends");
		callbackManager = CallbackManager.Factory.create();

		
		loginButton.registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
					@Override
					public void onSuccess(LoginResult loginResult) {
						// App code
						Intent i = new Intent(ScreenLogin.this,
								MainScreen.class);
						startActivity(i);
					}

					@Override
					public void onCancel() {
						// App code
						Toast.makeText(getApplicationContext(), "Canceled..",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onError(FacebookException exception) {
						// App code
						Log.e("Tag",exception.getMessage());
					}
				});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

}
