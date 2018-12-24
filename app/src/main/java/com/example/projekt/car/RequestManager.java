package com.example.projekt.car;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.StrictMode;
import android.widget.TextView;

import com.google.android.gms.common.api.Response;
/*więc będzie coś typu var token = request.authenticateWith("somelogin","somepasword")*/
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

public class    RequestManager extends AccountAuthenticatorActivity {
    private String token;
    private HttpsURLConnection httpsURLConnection;//http://104.214.72.121:8080/cars
    RequestManager() {
       // DownloadManager.Request
       // token=

            try {
              //  HttpClientExample hce = new HttpClientExample();
                String body = post("http://104.214.72.121:8080/cars", "data=test data");
                System.out.println(body);
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }

    }

    public String post(String postUrl, String data) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);

        this.sendData(con, data);

        return this.read(con.getInputStream());
    }

    protected void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }

    private String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            this.closeQuietly(in);
        }
    }

    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }
    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL =String.format("jdbc:jtds:sqlserver://adamserver2137.database.windows.net:1433/Users;" +
                    "user=adamserver2137@adamserver2137;password=#zbigniewstonoga1;encrypt=true;" +
                    "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException e) {
            e.printStackTrace();
            //     textView.setText("chuj "+e.getMessage().toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // textView.setText("chuj 2  "+e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            //textView.setText(" c h uj 434 "+e.getMessage());
        }
        //Toast.makeText(getBaseContext(),""+wiad,Toast.LENGTH_LONG).show();
        return conn;
    }

}
