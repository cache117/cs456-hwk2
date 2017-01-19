package edu.byu.cstaheli.cs456.homework2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener
{
    public final static String EXTRA_MESSAGE = "edu.byu.cstaheli.cs456.homework2.MESSAGE";
    private final static String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                toast("Settings");
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                toast("Favorite");
                Snackbar snackbar = snackBar("Favorite clicked");
                snackbar.setAction(R.string.undo_string, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        toast("Undoing last action");
                    }
                });
                snackbar.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private Snackbar snackBar(CharSequence message)
    {
        return Snackbar.make(findViewById(R.id.coordinator_layout), message, Snackbar.LENGTH_LONG);
    }

    private void toast(CharSequence message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed" + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onDoubleTap" + e.toString());
        snackBar("onDoubleTap").show();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onDoubleTapEvent" + e.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onDown" + e.toString());
        snackBar("onDown").show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onShowPress" + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onSingleTapUp" + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        Log.d(DEBUG_TAG, "onScroll" + e1.toString() + e2.toString());
        snackBar("onScroll").show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
        Log.d(DEBUG_TAG, "onLongPress" + e.toString());
        snackBar("onLongPress").show();
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
    {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        snackBar("onFling").show();
        return true;
    }
}
