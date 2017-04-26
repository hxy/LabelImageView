package hy.labelimageview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hy.labelimageview.view.LabelImageView;

/**
 * Created by huangyue on 2017/4/26.
 */

public class CodeBarActivity extends Activity {
    private LabelImageView labelIV;
    private EditText editBarWidth;
    private EditText editBarHeight;
    private EditText editBarTopMargin;
    private EditText editBarRadius;
    private EditText editTextSize;
    private EditText editTextText;
    private Button labelColor1Btn;
    private Button labelColor2Btn;
    private Button textColor1Btn;
    private Button textColor2Btn;
    private Button okBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codebar);
        labelIV = (LabelImageView)findViewById(R.id.labelIV);
        editBarWidth = (EditText)findViewById(R.id.edit_width);
        editBarHeight = (EditText)findViewById(R.id.edit_height);
        editBarTopMargin = (EditText)findViewById(R.id.edit_topMargin);
        editBarRadius = (EditText)findViewById(R.id.edit_radius);
        labelColor1Btn = (Button) findViewById(R.id.labelColor1);
        labelColor2Btn = (Button) findViewById(R.id.labelColor2);
        textColor1Btn = (Button) findViewById(R.id.textColor1);
        textColor2Btn = (Button) findViewById(R.id.textColor2);
        editTextSize = (EditText)findViewById(R.id.edit_textSize);
        editTextText = (EditText)findViewById(R.id.edit_text);
        okBtn = (Button)findViewById(R.id.okBtn);

        labelIV.setLabelType(LabelImageView.TYPE_BAR);
        labelColor1Btn.setOnClickListener(clickListener);
        labelColor2Btn.setOnClickListener(clickListener);
        textColor1Btn.setOnClickListener(clickListener);
        textColor2Btn.setOnClickListener(clickListener);
        okBtn.setOnClickListener(clickListener);
        okBtn.performClick();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.labelColor1:
                    labelIV.setLabelColor(0xffBFEFFF);
                    labelIV.invalidate();
                    break;
                case R.id.labelColor2:
                    labelIV.setLabelColor(0xffB3EE3A);
                    labelIV.invalidate();
                    break;
                case R.id.textColor1:
                    labelIV.setTextColor(0xffbbbbbb);
                    labelIV.invalidate();
                    break;
                case R.id.textColor2:
                    labelIV.setTextColor(0xffCD2626);
                    labelIV.invalidate();;
                    break;
                case R.id.okBtn:
                    int barWidth = Integer.parseInt(editBarWidth.getText().toString());
                    int barHeight = Integer.parseInt(editBarHeight.getText().toString());
                    int barTopMargin = Integer.parseInt(editBarTopMargin.getText().toString());
                    int barRadius = Integer.parseInt(editBarRadius.getText().toString());
                    int barTextSize = Integer.parseInt(editTextSize.getText().toString());
                    String barText = editTextText.getText().toString();

                    labelIV.setBarWidth(barWidth);
                    labelIV.setBarHeight(barHeight);
                    labelIV.setBarTopMargin(barTopMargin);
                    labelIV.setBarRadius(barRadius);
                    labelIV.setTextSize(barTextSize);
                    labelIV.setText(barText);
                    labelIV.invalidate();
                    break;
            }
        }
    };
}
