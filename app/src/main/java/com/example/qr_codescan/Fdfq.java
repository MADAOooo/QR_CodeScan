package com.example.qr_codescan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fdfq extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;


    private EditText scan_editText;
    private EditText ph1;
    private EditText xh1;
    private EditText gg1;
    private EditText yxkd1;
    private EditText sl1;
    private EditText srkd1;
    private EditText srbs1;
    private EditText dq11;
    private EditText dqb11;
    private EditText dq21;
    private EditText dqb21;
    private EditText sqzkd1;
    private EditText cqr1;
    private DBUtil dbUtil;
    private int year0;
    private int month0;
    private int day0;
    private String strrq;
    private String res;
    private String str2;
    private String czyid;
    private String wlid;

    private ArrayList<String> arrayList1 = new ArrayList<String>();
    private ArrayList<String> arrayList2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdfq);

        scan_editText = (EditText) findViewById(R.id.scan_editText);
        ph1 = (EditText) findViewById(R.id.ph1);
        xh1 = (EditText) findViewById(R.id.xh1);
        gg1 = (EditText) findViewById(R.id.gg1);
        yxkd1 = (EditText) findViewById(R.id.yxkd1);
        sl1 = (EditText) findViewById(R.id.sl1);
        srkd1 = (EditText) findViewById(R.id.srkd1);
        srbs1 = (EditText) findViewById(R.id.srbs1);
        dq11 = (EditText) findViewById(R.id.dq11);
        dqb11 = (EditText) findViewById(R.id.dqb11);
        dq21 = (EditText) findViewById(R.id.dq21);
        dqb21 = (EditText) findViewById(R.id.dqb21);
        sqzkd1 = (EditText) findViewById(R.id.sqzkd1);
        cqr1 = (EditText) findViewById(R.id.cqr1);
        Button send = (Button) findViewById(R.id.send);
        Button jisuan = (Button) findViewById(R.id.jisuan);
        Button clear1 = (Button) findViewById(R.id.clear1);
        Button clear2 = (Button) findViewById(R.id.clear2);
        Button scan = (Button) findViewById(R.id.scan);

        dbUtil = new DBUtil();
        dq11.setText("0");
        dqb11.setText("0");
        dq21.setText("0");
        dqb21.setText("0");
        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent();
                    intent.setClass(Fdfq.this, MipcaActivityCapture.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                }
                catch(Exception e)
                {
                    Toast.makeText(Fdfq.this, "扫描连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                scan_editText.setText("");
                ph1.setText("");
                xh1.setText("");
                gg1.setText("");
                yxkd1.setText("");
                sl1.setText("");
                srkd1.setEnabled(true);
                srbs1.setEnabled(true);
                dq11.setEnabled(true);
                dqb11.setEnabled(true);
                dq21.setEnabled(true);
                dqb21.setEnabled(true);

            }

        });

        clear2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                srkd1.setText("");
                srbs1.setText("");
                dq11.setText("0");
                dqb11.setText("0");
                dq21.setText("0");
                dqb21.setText("0");
                sqzkd1.setText("");


            }

        });

        jisuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    DecimalFormat df = new DecimalFormat("#.00");

                    double m=Double.parseDouble(yxkd1.getText().toString());

                    double a=Double.parseDouble(srkd1.getText().toString());
                    double b=Double.parseDouble(srbs1.getText().toString());

                    try{
                        double c=Double.parseDouble(dq11.getText().toString());
                        double d=Double.parseDouble(dqb11.getText().toString());
                    }catch(Exception e)
                    {
                        dq11.setText("0");
                        dqb11.setText("0");
                    }
                    double c=Double.parseDouble(dq11.getText().toString());
                    double d=Double.parseDouble(dqb11.getText().toString());
                    try{
                        double e=Double.parseDouble(dq21.getText().toString());
                        double f=Double.parseDouble(dqb21.getText().toString());
                    }catch(Exception e)
                    {
                        dq21.setText("0");
                        dqb21.setText("0");
                    }
                    double e=Double.parseDouble(dq21.getText().toString());
                    double f=Double.parseDouble(dqb21.getText().toString());

                    double g = a * b + c * d + e * f;

                    if(g>m){
                        Toast.makeText(Fdfq.this, "超出有效宽度！", Toast.LENGTH_SHORT).show();
                    }else {

                        String s = df.format(g);
                        sqzkd1.setText(s);
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(Fdfq.this, "计算错误", Toast.LENGTH_SHORT).show();
                }
            }

        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){


                    try {
                        Calendar calendar = Calendar.getInstance();
                        year0 = calendar.get(Calendar.YEAR);
                        month0 = calendar.get(Calendar.MONTH);
                        day0 = calendar.get(Calendar.DAY_OF_MONTH);
                        strrq = String.valueOf(year0) + "/" + String.valueOf(month0 + 1) + "/" + String.valueOf(day0);

                        dbUtil.insertProductInfofdfq(strrq,czyid,scan_editText.getText().toString(),ph1.getText().toString(),xh1.getText().toString(),wlid,yxkd1.getText().toString(),sl1.getText().toString(),srkd1.getText().toString(),srbs1.getText().toString(),sqzkd1.getText().toString());
                        //dbUtil.insertProductInfofdfqtest(scan_editText.getText().toString(),ph1.getText().toString(),xh1.getText().toString());
                        if(!(dq11.getText().toString().equals("0")||dq11.getText().toString().equals(""))){
                            dbUtil.insertProductInfofdfqbt(dq11.getText().toString(),dqb11.getText().toString(),xh1.getText().toString());
                            if(!(dq21.getText().toString().equals("0")||dq21.getText().toString().equals(""))){
                                dbUtil.insertProductInfofdfqbt(dq21.getText().toString(),dqb21.getText().toString(),xh1.getText().toString());
                            }
                        }
                        else {

                        }
                        Toast.makeText(Fdfq.this, "提交成功", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(Fdfq.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                }


        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            switch (requestCode) {
                case SCANNIN_GREQUEST_CODE:
                    if (resultCode == RESULT_OK) {
                        Bundle bundle = data.getExtras();
                        // 通过扫描获得数据
                        res=bundle.getString("result");
                        if(res.length()>2)
                        {
                            str2=bundle.getString("result").substring(0,2);
                            if (str2.equals("25")) {

                                try
                                {
                                    arrayList1.clear();
                                    arrayList1=dbUtil.inquireProductInfoczy(res);

                                    czyid=arrayList1.get(1);
                                        cqr1.setText(arrayList1.get(0));
                                        cqr1.setEnabled(false);
                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(Fdfq.this, "操作员扫描连接失败！", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else  {
                                try
                                {
                                    arrayList2.clear();

                                    arrayList2=dbUtil.inquireProductInfofdfq(res);

                                    DecimalFormat dff = new DecimalFormat("#.00");
                                    DecimalFormat dff2 = new DecimalFormat("#");
                                    String str3=dff2.format(Double.parseDouble(arrayList2.get(8)));
                                    String str4=dff.format(Double.parseDouble(arrayList2.get(7)));
                                    scan_editText.setText(res);
                                    ph1.setText(arrayList2.get(0));
                                    xh1.setText(arrayList2.get(6));
                                    gg1.setText(arrayList2.get(4));
                                    yxkd1.setText(str3);
                                    sl1.setText(str4);
                                    wlid=arrayList2.get(1);
                                    scan_editText.setEnabled(false);
                                    ph1.setEnabled(false);
                                    xh1.setEnabled(false);
                                    yxkd1.setEnabled(false);
                                    sl1.setEnabled(false);
                                    gg1.setEnabled(false);
                                }

                                catch(Exception e)
                                {
                                    Toast.makeText(Fdfq.this, "条码扫描连接失败!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                    break;
            }
        }catch(Exception e)
        {
            Toast.makeText(Fdfq.this, "连接失败", Toast.LENGTH_SHORT).show();
        }
    }

}
