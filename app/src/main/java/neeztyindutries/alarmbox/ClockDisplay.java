package neeztyindutries.alarmbox;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.text.SimpleDateFormat;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import java.util.Date;
import android.content.IntentFilter;



public class ClockDisplay extends ActionBarActivity {

    BroadcastReceiver _broadcastReceiver;
    private final SimpleDateFormat _sdfWatchTime = new SimpleDateFormat("h:mm a");
    private TextView _clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_display);
        _clock = (TextView)findViewById(R.id._clock);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clock_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
         super.onStart();
        _broadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                    _clock.setText(_sdfWatchTime.format(new Date()));
            }
        };
        _clock.setText(_sdfWatchTime.format(new Date()));
        registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

    }
    //Greg was here
    @Override
    public void onStop(){
        super.onStop();
        if (_broadcastReceiver != null)
            unregisterReceiver(_broadcastReceiver);
    }
}
