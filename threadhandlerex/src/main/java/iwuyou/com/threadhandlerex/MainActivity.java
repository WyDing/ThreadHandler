package iwuyou.com.threadhandlerex;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String MESSAGE = "您发送了%d条消息";
    Handler mineHandler;
    int count = 0;
    @butterknife.Bind(R.id.message_count)
    TextView messageCount;
    @butterknife.Bind(R.id.send_message)
    Button sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);

        messageCount.setText(String.format(MESSAGE,count));
        HandlerThread handlerThread = new HandlerThread("minemessage");
        handlerThread.start();


        mineHandler = new Handler(handlerThread.getLooper())
        {
            @Override
            public void handleMessage(Message msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        count ++;
                        messageCount.setText(String.format(MESSAGE,count));
                    }
                });
            }
        };
    }

    @butterknife.OnClick(R.id.send_message)
    public void onClick() {
        mineHandler.sendEmptyMessage(1);
    }
}
