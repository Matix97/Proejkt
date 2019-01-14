package com.example.projekt.car;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.Others.StringUtils;
import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;

import static android.app.PendingIntent.getActivity;


/**
 * Activity for reading data from an NFC Tag.
 */


public class NfcActivity extends AppCompatActivity {

    public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String TAG = "NfcDemo";
    public static final String UID_BLACK = "0420d06ac52481";
    public static final String UID_RED = "04d0dd6ac52480";

    public TextView mTextView;
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        mTextView = (TextView) findViewById(R.id.textView_explanation);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (!mNfcAdapter.isEnabled()) {
            mTextView.setText("NFC is disabled.");
        } else {
            mTextView.setText(R.string.explanation);
        }

        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * It's important, that the activity is in the foreground (resumed). Otherwise
         * an IllegalStateException is thrown.
         */
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        /**
         * Call this before onPause, otherwise an IllegalArgumentException is thrown as well.
         */
        stopForegroundDispatch(this, mNfcAdapter);

        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /**
         * This method gets called, when a new Intent gets associated with the current activity instance.
         * Instead of creating a new activity, onNewIntent will be called. For more information have a look
         * at the documentation.
         *
         * In our case this method gets called, when the user attaches a Tag to the device.
         */
        handleIntent(intent);
    }

    /**
     * @param activity The corresponding {@link Activity} requesting the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("Check your mime type.");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    /**
     * @param activity The corresponding {@link BaseActivity} requesting to stop the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }


    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            String type = intent.getType();
            if (MIME_TEXT_PLAIN.equals(type)) {
                //Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                String tagUID = getTagUID(intent);
                if (tagUID != null) {
                    // uruchom metode zajecie auta

                    // test:
                    mTextView.setText("Tag UID: " + tagUID);
                    if(tagUID.equals(UID_BLACK)) {
                        mTextView.setText(mTextView.getText() + "\n-> Tag CZARNY");
                    }
                    else if(tagUID.equals(UID_RED)) {
                        mTextView.setText(mTextView.getText() + "\n-> Tag CZERWONY");
                    }
                    else {
                        mTextView.setText(mTextView.getText() + "\n-> Tag nieznany");
                    }

                }

            } else {
                Log.d(TAG, "Wrong mime type: " + type);
            }

        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            // In case we would still use the Tech Discovered Intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                    String tagUID = getTagUID(intent);
                    if (tagUID != null) {
                        // uruchom metode zajecie auta
                        if(tagUID.equals(UID_BLACK)) {
                            newActivity("Jeep", UID_BLACK, "ELE N2543");
                        }
                        else if(tagUID.equals(UID_RED)) {
                            newActivity("Audi", UID_RED, "EL 32115");
                        }
                        else {
                            mTextView.setText(mTextView.getText() + "\n-> Tag nieznany");
                        }

                        // test:
//                        mTextView.setText("Tag UID: " + tagUID);
//                        if(tagUID.equals(UID_BLACK)) {
//                            mTextView.setText(mTextView.getText() + "\n-> Tag CZARNY");
//                        }
//                        else if(tagUID.equals(UID_RED)) {
//                            mTextView.setText(mTextView.getText() + "\n-> Tag CZERWONY");
//                        }
//                        else {
//                            mTextView.setText(mTextView.getText() + "\n-> Tag nieznany");
//                        }

                    }
                    break;
                }
            }
        }
    }

    private String getTagUID(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String prefix = "android.nfc.tech.";
        //String[] info = new String[2];
        byte[] uid = tag.getId();

            /*
            // Tech List
            String[] techList = tag.getTechList();
            String techListConcat = "Technologies: ";
            for(int i = 0; i < techList.length; i++) {
                techListConcat += techList[i].substring(prefix.length()) + ",";
            }
            info[0] += techListConcat.substring(0, techListConcat.length() - 1) + "\n\n";
            */

        return StringUtils.toHexString(uid);
    }

    private void newActivity(String model, String id, String registrationNumber) {
        Intent intent = new Intent(this, RentedCarActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("carID", 000001 /*Integer.parseInt(id)*/);
        bundle.putString("model", model);
        bundle.putString("registrationNumber", registrationNumber);
        intent.putExtras(bundle);

//        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

}
