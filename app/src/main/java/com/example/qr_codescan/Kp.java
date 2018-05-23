package com.example.qr_codescan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Kp extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * 锟斤拷示扫锟斤拷锟斤拷
	 */

	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;
	private EditText editText6;
	private EditText editText7;
	private EditText editText8;
	private EditText editText9;
	private EditText editText10;

	private EditText editText13;
	private EditText editText14;
	private EditText editText15;
	private EditText editText16;
	private EditText editText17;
	private EditText editText18;
	private EditText editText19;
	private EditText editText20;
	private EditText editText21;
	private EditText editText22;
	private EditText editTextkh;
	private String str2;
	private String str4;
	private String strl;
	private String strll;
	private String strk;
	private TextView textView1;
	private String res;
	private String strcp;

	private DBUtil dbUtil;
	private int i=0;
	private int year0;
	private int month0;
	private int day0;
	private String strrq;
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayList<String> arrayList1 = new ArrayList<String>();
	private ArrayList<String> arrayList2 = new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kp);

		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		editText5 = (EditText) findViewById(R.id.editText5);
		editText6 = (EditText) findViewById(R.id.editText6);
		editText7 = (EditText) findViewById(R.id.editText7);
		editText8 = (EditText) findViewById(R.id.editText8);
		editText9 = (EditText) findViewById(R.id.editText9);
		editText10 = (EditText) findViewById(R.id.editText10);
		editText13 = (EditText) findViewById(R.id.editText13);
		editText14 = (EditText) findViewById(R.id.editText14);
		editText15 = (EditText) findViewById(R.id.editText15);
		editText16 = (EditText) findViewById(R.id.editText16);
		editText17 = (EditText) findViewById(R.id.editText17);
		editText18 = (EditText) findViewById(R.id.editText18);
		editText19 = (EditText) findViewById(R.id.editText19);
		editText20 = (EditText) findViewById(R.id.editText20);
		editText21 = (EditText) findViewById(R.id.editText21);
		editText22 = (EditText) findViewById(R.id.editText22);
		editTextkh = (EditText) findViewById(R.id.editTextkh);
		textView1=(TextView) findViewById(R.id.textView1);
		Button send = (Button) findViewById(R.id.send);
		Button scan = (Button) findViewById(R.id.scan);
		Button clear = (Button) findViewById(R.id.clear);
		Button jisuan = (Button) findViewById(R.id.jisuan);
		Button tianjia = (Button) findViewById(R.id.tianjia);
		Button shanchu = (Button) findViewById(R.id.shanchu);
		Button chaxun = (Button) findViewById(R.id.chaxun);
		dbUtil = new DBUtil();
		editText19.setText("0");
		editText20.setText("0");
		editText21.setText("0");
		editText22.setText("0");

		scan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
					Intent intent = new Intent();
					intent.setClass(Kp.this, MipcaActivityCapture.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
				}
				catch(Exception e)
				{
					Toast.makeText(Kp.this, "连接失败", Toast.LENGTH_SHORT).show();
				}
			}
		});

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				try
				{
					Calendar calendar = Calendar.getInstance();
					year0 = calendar.get(Calendar.YEAR);
					month0 = calendar.get(Calendar.MONTH);
					day0 = calendar.get(Calendar.DAY_OF_MONTH);
					strrq=String.valueOf(year0)+"/"+String.valueOf(month0+1)+"/"+String.valueOf(day0);
					str4 = editText17.getText().toString();
					if (str4.equals("")  ) {
						Toast.makeText(Kp.this, "输入批号不能为空", Toast.LENGTH_SHORT).show();
					}else {
						dbUtil.insertProductInfokp(strcp, editText6.getText().toString(), editText7.getText().toString(), editText17.getText().toString(), editText1.getText().toString(), strrq, editTextkh.getText().toString());
						Toast.makeText(Kp.this, "汇报成功", Toast.LENGTH_SHORT).show();
					}
				}
				catch(Exception e)
				{
					Toast.makeText(Kp.this, "汇报失败", Toast.LENGTH_SHORT).show();
				}

			}

		});
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				editText4.setText("");
				editText5.setText("");
				editText6.setText("");
				editText7.setText("");
				editText1.setText("");
				editText17.setText("");
				editText2.setText("");

			}

		});
		jisuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
					DecimalFormat df = new DecimalFormat("#.00");
					double a=Double.parseDouble(editText6.getText().toString());//目标容量
					double b=Double.parseDouble(editText7.getText().toString());//裁切系数
					double c=Double.parseDouble(editText14.getText().toString());//宽度
					double h=Double.parseDouble(editText13.getText().toString());//并数
					double d=Double.parseDouble(editText9.getText().toString());//最小比容
					double f=Double.parseDouble(editText10.getText().toString());//长度
					double e=100*a/(b*c*d);//L
					int ee=(int) Math.round(e);//L四舍五入
					String stre=String.valueOf(ee);
					int x=(int) Math.round(1000*f/ee);
					int g=(int) ((x-1)*h);//总数量
					String strn=String.valueOf(g);
					String s=df.format(f*c*h/1000);//总面积
					editText15.setText(stre);
					editText16.setText(strn);
					editText18.setText(s);
					//textView1.append(" ");

				}
				catch(Exception e)
				{
					Toast.makeText(Kp.this, "计算错误", Toast.LENGTH_SHORT).show();
				}
			}

		});
		tianjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try{
					str4 = editText17.getText().toString();
					if (str4.equals("")  ) {
						Toast.makeText(Kp.this, "输入批号不能为空", Toast.LENGTH_SHORT).show();
					}else {
					if(editText3.getText().toString()!=""){
						dbUtil.insertProductInfokpt(arrayList2.get(1).toString(),editText3.getText().toString(),arrayList2.get(5).toString(),arrayList2.get(15).toString(),strll,editText13.getText().toString(),editText14.getText().toString(),editText15.getText().toString(),editText16.getText().toString(),editText18.getText().toString(),arrayList2.get(2).toString(),editText17.getText().toString(),editText19.getText().toString(),editText20.getText().toString(),editText21.getText().toString(),editText22.getText().toString());
						editText3.setText("");
						editText8.setText("");
						editText9.setText("");
						editText10.setText("");
						editText13.setText("");
						editText14.setText("");
						editText15.setText("");
						editText16.setText("");
						editText18.setText("");
						editText19.setText("0");
						editText20.setText("0");
						editText21.setText("0");
						editText22.setText("0");
					}
					Toast.makeText(Kp.this, "添加成功", Toast.LENGTH_SHORT).show();
				  }
				}
				catch(Exception e)
				{
					Toast.makeText(Kp.this, "添加错误", Toast.LENGTH_SHORT).show();
				}
			}

		});
		shanchu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				editText3.setText("");
				editText8.setText("");
				editText9.setText("");
				editText10.setText("");
				editText13.setText("");
				editText14.setText("");
				editText15.setText("");
				editText16.setText("");
				editText18.setText("");
				editText19.setText("0");
				editText20.setText("0");
				editText21.setText("0");
				editText22.setText("0");
			}

		});
		chaxun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				arrayList.clear();
				textView1.setText("");
				str4 = editText17.getText().toString();
				if (str4.equals("")  ) {
					Toast.makeText(Kp.this, "输入批号不能为空", Toast.LENGTH_SHORT).show();
				}else {
					arrayList = dbUtil.inquireProductInfokp(editText17.getText().toString());

					for (i = 0; i < arrayList.size(); i++) {
						textView1.setText(textView1.getText().toString() + "\n" + arrayList.get(i));
					}
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
							if (str2.equals("J1")) {

								try
								{
									arrayList1.clear();
									arrayList1=dbUtil.inquireProductInfozzt(res);
									String js1=arrayList1.get(0);
									if(js1.equals("1"))
									{
										DecimalFormat df = new DecimalFormat("0.00");
										strcp=arrayList1.get(2);//产品id

										if(!arrayList1.get(3).equals(""))
										{
											strk=df.format(Double.parseDouble(arrayList1.get(3)));
											editText7.setText(strk);//K值
										}
										editText1.setText(arrayList1.get(1));//图号
										editText4.setText(arrayList1.get(6));//产品名称
										editText5.setText(arrayList1.get(7));//规格型号
										editText6.setText(arrayList1.get(4));//目标容量

										editText1.setEnabled(false);
										editText4.setEnabled(false);
										editText5.setEnabled(false);
										//editText6.setEnabled(false);
										editText7.setEnabled(false);
									}
									else if(js1.equals(""))
									{
										Toast.makeText(Kp.this, "图号不存在！", Toast.LENGTH_SHORT).show();
									}
									else
									{
										Toast.makeText(Kp.this, "图号重复！", Toast.LENGTH_SHORT).show();
									}
								}
								catch(Exception e)
								{
									Toast.makeText(Kp.this, "连接失败！", Toast.LENGTH_SHORT).show();
								}

							}
							else if (str2.equals("LB")) {
								try
								{
									arrayList2.clear();
									if(res.length()>8)
									{
										arrayList2=dbUtil.inquireProductInfojybg(res.substring(2));
										String js2=arrayList2.get(0);
										if(js2.equals("1"))
										{
											DecimalFormat df = new DecimalFormat("#");
											strl=arrayList2.get(14);//铝箔长度
											editText3.setText(arrayList2.get(6));
											editText8.setText(arrayList2.get(4));//铝箔名称
											//editText3.setText(arrayList2.get(5));//检验批号
											editText9.setText(arrayList2.get(15));//最小比容
											if(!strl.equals(""))
											{
												strll=df.format(Double.parseDouble(strl));
												editText10.setText(strll);//铝箔长度
											}


											editText3.setEnabled(false);
											editText8.setEnabled(false);
											editText9.setEnabled(false);
											editText10.setEnabled(false);
										}
										else if(js2.equals(""))
										{
											Toast.makeText(Kp.this, "批号不存在！", Toast.LENGTH_SHORT).show();
										}
										else
										{
											Toast.makeText(Kp.this, "批号重复！", Toast.LENGTH_SHORT).show();
										}
									}
								}

								catch(Exception e)
								{
									Toast.makeText(Kp.this, "连接失败!!", Toast.LENGTH_SHORT).show();
								}

							}
						}
					}
					break;
			}
		}catch(Exception e)
		{
			Toast.makeText(Kp.this, "连接失败", Toast.LENGTH_SHORT).show();
		}
	}

}
