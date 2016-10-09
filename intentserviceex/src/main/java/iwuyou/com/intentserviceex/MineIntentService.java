package iwuyou.com.intentserviceex;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

/**
 * Created by duanding on 16/10/9.
 */

public class MineIntentService extends IntentService {

    public static final String RESULT_RECEIVER = "RESULT_RECEIVER";
    public static final String RESULT_COUNT = "RESULT_COUNT";
    public static final String COUNT = "COUNT";

    public MineIntentService() {
        //必须实现父类的构造方法
        super("MineIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MineIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        int count = intent.getIntExtra(COUNT, 0);
        ResultReceiver resultReceiver = intent.getParcelableExtra(RESULT_RECEIVER);
        int i = count + 1;
        Bundle bundle = new Bundle();
        bundle.putInt(RESULT_COUNT,i);
        resultReceiver.send(1, bundle);
    }


}
