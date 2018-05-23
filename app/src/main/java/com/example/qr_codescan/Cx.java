package com.example.qr_codescan;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Cx extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
	private String str;
	private String str2;
	private String strScan;
	private String strScanPh;
    private String a1;
    private String[] a2;
	private int i=0;
	private EditText editTextph;
	private DBUtil dbUtil;
    private ListView listView;

    private ArrayList<String> arrayList1 = new ArrayList<String>();
    private ArrayList<String> arrayList2 = new ArrayList<String>();
    private List<Hbinfo> hbinfoList = new ArrayList<Hbinfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cx);


        final ListAdapter adapter = new ListAdapter(this,hbinfoList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

		Button buttonScan = (Button) findViewById(R.id.button_scan);
        Button buttonBack = (Button) findViewById(R.id.button_back);
		editTextph = (EditText) findViewById(R.id.edit_ph);
		final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        dbUtil = new DBUtil();
        buttonScan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                try{
                    hbinfoList.clear();
                	Intent intent = new Intent();
    				intent.setClass(Cx.this, MipcaActivityCapture.class);
    				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                    editTextph.setText("");
                    editTextph.setEnabled(true);
                } catch(Exception e)
                {
                    Toast.makeText(Cx.this, "扫描连接失败", Toast.LENGTH_SHORT).show();
                }
			}
		});







        //返回按钮点击事件
        buttonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cx.this.finish();
            }
        });


        //获取Spinner数据
        String[] mItems = getResources().getStringArray(R.array.riqichoose);
  		
  		ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mItems);
  		
  		spinner3.setAdapter(_Adapter);
  		spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
  			@Override
  			public void onItemSelected(AdapterView<?> parent, View view,
  					int position, long id) {
  				str = parent.getItemAtPosition(position).toString();
  			
  			}
  			@Override
  			public void onNothingSelected(AdapterView<?> parent) {
  				// TODO Auto-generated method stub
  				str = "";
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
                    strScan=bundle.getString("result");
                    final ListAdapter adapter = new ListAdapter(this,hbinfoList);
                    listView.setAdapter(adapter);
                    if(strScan.length()>2)
                    {
                        str2=bundle.getString("result").substring(0,2);
				
                        if (str2.equals("NO")) {
                            strScanPh=strScan.substring(2);
                            try
                            {
                                arrayList1.clear();
                                arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                editTextph.setText(strScanPh);

                                for (i = 0; i < arrayList1.size(); i++) {
                                    a2 = null;
                                    a1 = arrayList1.get(i);
                                    a2 = a1.split(" ");

                                    hbinfoList.add(new Hbinfo(i + 1, a2[0], a2[1] + " " + a2[2], a2[3], a2[4], a2[5], a2[6], a2[7]));
                                }


                            }
                            catch(Exception e)
                            {
                                Toast.makeText(Cx.this, "按批号查询汇报信息失败", Toast.LENGTH_SHORT).show();
                            }
                            editTextph.setEnabled(false);
                        }
                        if (str2.equals("02")) {
                            try
                            {
                                arrayList1.clear();
                                arrayList2.clear();
                                arrayList2=dbUtil.inquireProductInfoczy(strScan);
					            editTextph.setText(arrayList2.get(0));


                                if (str.equals("今天")) {
                                    arrayList1 = dbUtil.inquireProductInfojthb(arrayList2.get(1));
                                } else if (str.equals("本月")) {
                                    arrayList1 = dbUtil.inquireProductInfobyhb(arrayList2.get(1));
                                } else {
                                    arrayList1.clear();
                                }

                                for (i = 0; i < arrayList1.size(); i++) {
                                    a2 = null;
                                    a1 = arrayList1.get(i);
                                    a2 = a1.split(" ");
                                    String s1 = a2[0];

                                    hbinfoList.add(new Hbinfo(i + 1, a2[0], a2[1] + " " + a2[2], a2[3], a2[4], a2[5], a2[6], a2[7]));
                                }

                            } catch(Exception e)
                            {
                                Toast.makeText(Cx.this, "汇报信息查询失败", Toast.LENGTH_SHORT).show();
                            }
                            editTextph.setEnabled(false);
                        }
                    }
                }
                break;
        }
        }catch(Exception e)
		{
			Toast.makeText(Cx.this, "连接失败", Toast.LENGTH_SHORT).show();
		}	
	}

}
