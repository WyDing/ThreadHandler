package iwuyou.com.intentserviceex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @butterknife.Bind(R.id.message_count)
    TextView messageCount;
    @butterknife.Bind(R.id.send_message)
    Button sendMessage;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
//        IntentService;
//        ResultReceiver;
        messageCount.setText(count + "");

    }

    @butterknife.OnClick(R.id.send_message)
    public void onClick() {
        Intent intent = new Intent(this,MineIntentService.class);
        intent.setAction("count+").
                putExtra(MineIntentService.RESULT_RECEIVER,new CountResultReceiver(new Handler())).
                putExtra(MineIntentService.COUNT,count);
        startService(intent);
    }

    private final class CountResultReceiver extends ResultReceiver {
        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public CountResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            count = resultData.getInt(MineIntentService.RESULT_COUNT);
            messageCount.setText(count + "");
        }
    }
}
