package xyz.bhent.production.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.bhent.production.R;

public class Reservations extends AppCompatActivity {

    private static int  REQUEST_CODE = 0001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservations);
        Bundle bundle = getIntent().getExtras();

        Button test = (Button)findViewById(R.id.button);
        TextView header = (TextView)findViewById(R.id.selected_item);
        header.setText(bundle.getString("selectedItem"));
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("From Reservation", "Checked");
                overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_left_to_right);
                setResult(REQUEST_CODE, intent);
                finish();
            }
        });
 if(getSupportActionBar() != null){
     getSupportActionBar().setTitle(bundle.getString("selectedItem"));
 }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
//            case R.id.setting:
//                break;
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_to_left, android.R.anim.fade_out);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
