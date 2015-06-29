package com.example.buddy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainScreen extends ActionBarActivity {

	private ListView list;
	private Toolbar toolbar;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerListener;
	

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Fragment fragment = new EventFragment();

		
		list = (ListView) findViewById(R.id.drawerListView);
		list.setAdapter(new CustomAdapter(createItems()));
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("");
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		toolbar.setNavigationIcon(R.drawable.ic_action_menu_50);
		toolbar.setLogo(R.drawable.rsz_walkielogo);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_action_menu_50, R.string.drawer_opened,
				R.string.drawer_closed) {
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}
		};
		drawerLayout.setDrawerListener(drawerListener);
		
		FragmentManager fm = getSupportFragmentManager();
		
		fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
				
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public DrawerItems[] createItems() {
		DrawerItems[] items = { new DrawerItems(R.drawable.ic_action_user_50,
				"Мој Профил") };
		return items;
	}

	public class CustomAdapter extends ArrayAdapter<DrawerItems> {

		public CustomAdapter(DrawerItems[] objects) {
			super(MainScreen.this, R.layout.custom_list_rows_drawer, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(
						R.layout.custom_list_rows_drawer, null);
			}

			TextView txt = (TextView) convertView
					.findViewById(R.id.textViewListDrawerCustom);
			txt.setText(getItem(position).title);
			txt.setCompoundDrawablesWithIntrinsicBounds(getItem(position).icon,
					0, 0, 0);

			return convertView;
		}
	}

}
