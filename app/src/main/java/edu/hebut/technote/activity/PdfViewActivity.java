package edu.hebut.technote.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.hebut.technote.R;

public class PdfViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        String pdfUrl = getIntent().getStringExtra("pdfUrl");
        Toast.makeText(this, pdfUrl, Toast.LENGTH_SHORT).show();
        WebView webView = findViewById(R.id.pdf_webview);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setSupportMultipleWindows(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setAllowFileAccess(true);
//        webSettings.setAllowFileAccessFromFileURLs(true);
//        webSettings.setLoadsImagesAutomatically(true);
//        webSettings.setAllowUniversalAccessFromFileURLs(true);
        pdfUrl=pdfUrl.replace("http://", "https://");
        Log.w("pdfUrl", pdfUrl);
//        webView.loadUrl("http://192.168.43.154:9091/flask/word/preimg?key=2131");
        webView.loadUrl("https://mozilla.github.io/pdf.js/web/viewer.html?file=https://arxiv.org/pdf/2404.06661v1.pdf");
//        webView.loadUrl("https://mozilla.github.io/pdf.js/web/viewer.html?file=" + pdfUrl);


//        webView.loadUrl(pdfUrl);
    }
}