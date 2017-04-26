package hy.labelimageview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button xmlBarBtn = (Button)findViewById(R.id.xml_bar);
        Button codeBarBtn = (Button)findViewById(R.id.code_bar);
        Button xmlTriangle = (Button)findViewById(R.id.xml_triangle);
        Button codeTriangle = (Button)findViewById(R.id.code_triangle);

        xmlBarBtn.setOnClickListener(clickListener);
        codeBarBtn.setOnClickListener(clickListener);
        xmlTriangle.setOnClickListener(clickListener);
        codeTriangle.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.xml_bar:
                    startActivity(new Intent(MainActivity.this,XmlBarActivity.class));
                    break;
                case R.id.code_bar:
                    startActivity(new Intent(MainActivity.this,CodeBarActivity.class));
                    break;
                case R.id.xml_triangle:
                    startActivity(new Intent(MainActivity.this,XmlTriangleActivity.class));
                    break;
                case R.id.code_triangle:
                    startActivity(new Intent(MainActivity.this,CodeTriangleActivity.class));
                    break;
            }
        }
    };
}
