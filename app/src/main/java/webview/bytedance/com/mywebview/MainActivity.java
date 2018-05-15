package webview.bytedance.com.mywebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bytewebkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) this.findViewById(R.id.webView);
        webView.loadUrl("https://github.com/HomHomLin");
    }
}
