package hr.matvidako.ideamachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import hr.matvidako.ideamachine.drawer.DrawerItemAdapter;

public abstract class BaseActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    ListView menuList;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    DrawerItemAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupToolbar();
        setupMenuDrawer();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(toolbar);
    }

    protected void setupMenuDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.menu_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.add, R.string.cancel);
        drawerLayout.setDrawerListener(drawerToggle);

        menuList = (ListView) findViewById(R.id.menu_list);
        View header = getLayoutInflater().inflate(R.layout.header_menu, null, false);
        menuList.addHeaderView(header, null, false);
        menuAdapter = new DrawerItemAdapter(this);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new DrawerItemClickListener());
    }

    abstract protected int getLayoutId();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0) return;
            position--;
            drawerLayout.closeDrawer(menuList);
            Toast.makeText(BaseActivity.this, menuAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            Class activityClass = menuAdapter.getItem(position).activityClass;
            Intent i = new Intent(BaseActivity.this, activityClass);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        }
    }


}