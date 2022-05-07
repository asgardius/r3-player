package asgardius.page.r3player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class R3news extends AppCompatActivity {

    private WebView npw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r3news);
        //This initializes webview object
        npw =(WebView)findViewById(R.id.webview);
        npw.setWebViewClient(new MyBrowser());
        npw.loadUrl(getResources().getString(R.string.newsportal));
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}