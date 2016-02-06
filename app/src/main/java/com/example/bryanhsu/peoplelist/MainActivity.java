package com.example.bryanhsu.peoplelist;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.reconinstruments.ui.dialog.BaseDialog;
import com.reconinstruments.ui.dialog.DialogBuilder;
import com.reconinstruments.ui.list.SimpleListActivity;
import com.reconinstruments.ui.list.StandardListItem;


public class MainActivity extends SimpleListActivity {

    final static String TAG = "com.bryanhsu";
    NotificationManager mNotificationManager;
    private String name_pass;



    public class ListItem extends StandardListItem {
        String subtitle;
        OnClickCallback callback;

        public ListItem(String text, OnClickCallback callback) {
            super(text);
            this.callback = callback;
        }

        public void onClick(Context context) {
            callback.onClick(this);
        }

//        public void setSubtitle(String subtitle) {
//            this.subtitle = subtitle;
//            TextView subtitleView = (TextView) getView().findViewById(R.id.subtitle);
//            subtitleView.setVisibility(View.VISIBLE);
//            subtitleView.setText(subtitle);
//        }

        public String getSubtitle() {
            return subtitle;
        }
    }

    public interface OnClickCallback {
        void onClick(ListItem item);
    }

    public void intent_init(String name) {
        Intent i = new Intent(MainActivity.this, DetailedActivity.class);
        String strName = "You have clicked " + name;
        i.putExtra("STRING_I_NEED", strName);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_standard_layout);
        setContents(
                new ListItem("Bryan", new OnClickCallback() {
                    public void onClick(ListItem item) {
                        Log.d(TAG, "Bryan Pressed");
                        //createWarningDialog();
                        createReminderDialog();
                        getName("Bryan");
                    }
                }),
                new ListItem("Ali", new OnClickCallback() {
                    public void onClick(ListItem item) {

                        createWarningDialog();
                        getName("Ali");
                    }
                }),
                new ListItem("Jin", new OnClickCallback() {
                    public void onClick(ListItem item) {
                        createWarningDialog();
                        getName("Jin");
                    }
                }),
                new ListItem("Henry", new OnClickCallback() {
                    public void onClick(ListItem item) {
                        createWarningDialog();
                        getName("Henry");
                    }
                }),
                new ListItem("Ming", new OnClickCallback() {
                    public void onClick(ListItem item) {
                        createWarningDialog();
                        getName("Ming");
                    }
                })

        );


        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


    }

    private void getName(String C_name) {
        name_pass = C_name;
    }


    private void createWarningDialog() {

        new DialogBuilder(this).setTitle("Warning").setSubtitle("Are you sure?").setLayout(R.layout.activity_main).setOnKeyListener(new BaseDialog.OnKeyListener() {
            @Override
            public boolean onKey(BaseDialog var1, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_UP) return false;
                if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                    //MainActivity.this.intent_init(name_pass);
                    MainActivity.this.SelectClicked();
                    return true;
                }
                return false;

            }

        }).createDialog().show();


    }

   //This part is a experiment for ticket one
    private void createReminderDialog() {
       // new DialogBuilder(this).setTitle("Movement Detected").setSubtitle("Press to start Tracking").setLayout(R.layout.activity_main).setOnKeyListener(new BaseDialog.OnKeyListener()
        new DialogBuilder(this).setTitle("Movement Detected").setLayout(R.layout.activity_main).setOnKeyListener(new BaseDialog.OnKeyListener() {
            @Override
            public boolean onKey(BaseDialog var1, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_UP) return false;
                if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                    //MainActivity.this.intent_init(name_pass);
                    MainActivity.this.SelectClicked();
                    return true;
                }
                return false;

            }

        }).createDialog().show();


    }


    private void SelectClicked() {
        //Log.d(TAG, "Inside method");
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Selected")
                .setSmallIcon(R.drawable.ic_launcher_share)
                .setContentText("You have clicked " + name_pass)
                .build();
        mNotificationManager.notify(0, notification);
    }

}
