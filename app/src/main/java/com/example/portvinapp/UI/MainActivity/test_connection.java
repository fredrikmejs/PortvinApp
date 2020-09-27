package com.example.portvinapp.UI.MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.portvinapp.R;

public class test_connection extends Fragment implements View.OnClickListener {
    private Button button_goBack, button_goToURL;
    private WebView webView;


    public test_connection() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_connection, container, false);

        button_goBack = view.findViewById(R.id.backToMain);
        button_goBack.setOnClickListener(this);

        button_goToURL = view.findViewById(R.id.Button_GoToweb);

        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://google.com");


        return view;
    }

    @Override
    public void onClick(View v) {


        if (v == button_goToURL) {
            EditText editText = getView().findViewById(R.id.web_page);
            String input = editText.getText().toString();
            webView.loadUrl("https://"+input);
        }
    }

}