package com.example.qr_codescan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker.OnDateChangedListener;

public class Hb1 extends Activity {

	private final static int SCANNIN_GREQUEST_CODE = 1;

	
	private EditText editKahao;
	private EditText editJth;
	private EditText editCzy;

	private EditText editJhsl;
	private EditText editLpsl;
	private EditText editBlpsl;

	private EditText editCpmc;
	private EditText editGgxh;

	private EditText editGxmc;
	private EditText editNote;


	//private ImageView mImageView;
	private String strBz;
	private String strDate;
	private String str2;
	private String strPh;
	private String strJth;
	private String strCzy;
	private String strJhsl;
	private String strLpsl;
	private String strBlpsl;
	private String strCpmc;
	private String strGgxh;
	private String strid;
	private String strScan;
	private String strStyle;
	private String strJthid;
	private String strScanPh;
	private String strnote="";
	private String strcus="";
	private DBUtil dbUtil;
	private Calendar calendar;  
    private int year0;  
    private int month0;  
    private int day0;
	private String strGx;
    private String strGxid;
    private String a1;
    private String[] a2;

	private ArrayList<String> arrayList1 = new ArrayList<String>();
	private ArrayList<String> arrayList2 = new ArrayList<String>();
	private ArrayList<String> arrayList3 = new ArrayList<String>();
	private ArrayList<String> arrayList4 = new ArrayList<String>();
    private ArrayList<String> arrayList5 = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hb1);

	
		editKahao = (EditText) findViewById(R.id.edit_kahao);
		editJth = (EditText) findViewById(R.id.edit_jth);
		editCzy = (EditText) findViewById(R.id.edit_czy);

	
		editJhsl = (EditText) findViewById(R.id.edit_jhsl);
		editLpsl = (EditText) findViewById(R.id.edit_lpsl);
		editBlpsl = (EditText) findViewById(R.id.edit_blpsl);

		editCpmc = (EditText) findViewById(R.id.edit_cpmc);
		editGgxh = (EditText) findViewById(R.id.edit_ggxh);

		editGxmc = (EditText) findViewById(R.id.edit_gxmc);
		editNote = (EditText) findViewById(R.id.edit_note);

		//mImageView = (ImageView) findViewById(R.id.qrcode_bitmap2);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		
		Button scan = (Button) findViewById(R.id.button_scan);
		Button buttonBack = (Button) findViewById(R.id.button_back);

		final Spinner spinnerBz = (Spinner) findViewById(R.id.spinner_bz);
		final Spinner spinnerStyle = (Spinner) findViewById(R.id.spinner_style);

		Button clearall = (Button) findViewById(R.id.clearall);
		Button send = (Button) findViewById(R.id.send);
		dbUtil = new DBUtil();

		//扫描按钮点击事件
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                try{
				Intent intent = new Intent();
				intent.setClass(Hb1.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
			catch(Exception e)
			{
				Toast.makeText(Hb1.this, "扫描连接失败", Toast.LENGTH_SHORT).show();
			}
			}
		});

		//返回按钮点击事件
		buttonBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Hb1.this.finish();
			}
		});
		
		

		//获取班组Spinner数据
		String[] mItems = getResources().getStringArray(R.array.bcchoose);
		
		ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_singlechoice, mItems);
		
		spinnerBz.setAdapter(_Adapter);
		spinnerBz.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				strBz = parent.getItemAtPosition(position).toString();
			
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				strBz = "";
			}
		});


		//获取生产类型Spinner数据
        String[] mItems2 = getResources().getStringArray(R.array.stylechoose);
		
		ArrayAdapter<String> _Adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_singlechoice, mItems2);
		
		spinnerStyle.setAdapter(_Adapter2);
		spinnerStyle.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				strStyle = parent.getItemAtPosition(position).toString();
			
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				strStyle = "";
			}
		});

		//获取作业时间
		//获取日历的一个对象  
	    calendar = Calendar.getInstance();  
	    //获取年月日时分的信息  
	    year0 = calendar.get(Calendar.YEAR);  
	    month0 = calendar.get(Calendar.MONTH);  
	    day0 = calendar.get(Calendar.DAY_OF_MONTH);  
	    strDate=String.valueOf(year0)+"/"+String.valueOf(month0+1)+"/"+String.valueOf(day0);
		datePicker.init(year0,month0,day0, new OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				strDate=String.valueOf(year)+"/"+String.valueOf(monthOfYear+1)+"/"+String.valueOf(dayOfMonth);
			}
		});


		//提交按钮点击事件
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//更新数据库		
				setAddDialog();
				datePicker.init(year0, month0,day0,null);
				//spinner.setSelection(0);
				spinnerStyle.setSelection(0);
				arrayList4.clear();
			}

		});

		//清空按钮点击事件
		clearall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				editKahao.setText("");
				editJth.setText("");
				editCzy.setText("");
				editJhsl.setText("");
				editLpsl.setText("");
				editBlpsl.setText("");
				editCpmc.setText("");
				editGgxh.setText("");
				editGxmc.setText("");
				editNote.setText("");


				editKahao.setEnabled(true);
				editJth.setEnabled(true);
				editCzy.setEnabled(true);
				editJhsl.setEnabled(true);
				editLpsl.setEnabled(true);
				editBlpsl.setEnabled(true);
				editCpmc.setEnabled(true);
				editGgxh.setEnabled(true);
				editGxmc.setEnabled(true);
				editNote.setEnabled(true);
				arrayList4.clear();

			}
		});
	}


	//扫描结果处理
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try{
			switch (requestCode) {
				case SCANNIN_GREQUEST_CODE:
					if (resultCode == RESULT_OK) {
						Bundle bundle = data.getExtras();
						// 通过扫描获得数据
						strScan=bundle.getString("result");
						if(strScan.length()>2)
						{
							str2=bundle.getString("result").substring(0,2);


							if (str2.equals("NO")) {
								strScanPh=strScan.substring(2);
								try
								{
									arrayList1.clear();

									arrayList1=dbUtil.inquireProductInfoph(strScanPh);
									//str3=arrayList1.get(5);
									strJhsl=arrayList1.get(4);
									DecimalFormat df = new DecimalFormat("#");
									//strcus=arrayList1.get(4);
									//strcus="";
									editKahao.setText(strScanPh);
									if(!strJhsl.equals(""))
									{
										editJhsl.setText(df.format(Double.parseDouble(strJhsl)));
									}
									editCpmc.setText(arrayList1.get(2));
									editGgxh.setText(arrayList1.get(1));

									strid=arrayList1.get(0);
								} catch(Exception e)
								{
									Toast.makeText(Hb1.this, "查询产品信息失败", Toast.LENGTH_SHORT).show();
								}

								editKahao.setEnabled(false);
								editCpmc.setEnabled(false);
								editGgxh.setEnabled(false);
							}else if (str2.equals("JR")||str2.equals("PJ")||str2.equals("FN")||str2.equals("ZP")||str2.equals("GJ")||str2.equals("HX")||str2.equals("CS")||str2.equals("BZ"))
							{
								try {
                                    strScanPh = editKahao.getText().toString();
                                    if(strScanPh.equals("")){
                                        Toast.makeText(Hb1.this, "请先扫描批号查询产品信息", Toast.LENGTH_SHORT).show();
                                    }else {


                                        arrayList2.clear();

                                        arrayList2 = dbUtil.inquireProductInfojth(strScan);
                                        editJth.setText(arrayList2.get(0));
                                        strJthid = arrayList2.get(1);

                                        if (str2.equals("JR")) {
                                            editGxmc.setText("卷绕");
                                            strGxid = "1";
                                        } else if (str2.equals("PJ")) {
                                            editGxmc.setText("喷金");
                                            strGxid = "2";
                                            arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);
                                            }
                                            if(arrayList5.indexOf("1")<0){
                                                Toast.makeText(Hb1.this, "请先进行卷绕工序汇报", Toast.LENGTH_SHORT).show();
                                            }
                                            arrayList5.clear();

                                        } else if (str2.equals("FN")) {
                                            editGxmc.setText("赋能");
                                            strGxid = "3";
                                            arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);

                                            }
                                            if(arrayList5.indexOf("2")<0){
                                                Toast.makeText(Hb1.this, "请先进行喷金工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        } else if (str2.equals("ZP")) {
                                            editGxmc.setText("装配");
                                            strGxid = "4";
                                            arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);


                                            }
                                            if(arrayList5.indexOf("3")<0){
                                                Toast.makeText(Hb1.this, "请先进行赋能工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        } else if (str2.equals("GJ")) {
                                            editGxmc.setText("灌胶");
                                            strGxid = "5";arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);


                                            }
                                            if(arrayList5.indexOf("4")<0){
                                                Toast.makeText(Hb1.this, "请先进行装配工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        } else if (str2.equals("HX")) {
                                            editGxmc.setText("热定型/6h");
                                            strGxid = "6";
                                            arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);

                                            }
                                            if(arrayList5.indexOf("5")<0){
                                                Toast.makeText(Hb1.this, "请先进行灌胶工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        } else if (str2.equals("CS")) {
                                            editGxmc.setText("测试");
                                            strGxid = "7";arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);

                                            }
                                            if(arrayList5.indexOf("6")<0){
                                                Toast.makeText(Hb1.this, "请先进行热定型工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        } else if (str2.equals("BZ")) {
                                            editGxmc.setText("包装");
                                            strGxid = "8";arrayList1.clear();
                                            arrayList1=dbUtil.inquireProductInfophhb(strScanPh);
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                a2 = null;
                                                a1 = arrayList1.get(i);
                                                a2 = a1.split(" ");
                                                arrayList5.add(a2[8]);

                                            }
                                            if(arrayList5.indexOf("7")<0){
                                                Toast.makeText(Hb1.this, "请先进行测试工序汇报", Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                            arrayList5.clear();
                                        }
                                    }
								} catch(Exception e)
								{
									Toast.makeText(Hb1.this, "机台信息查询失败", Toast.LENGTH_SHORT).show();
								}

								editJth.setEnabled(false);

							} else if (str2.equals("02"))
							{

								arrayList3.clear();
								arrayList3=dbUtil.inquireProductInfoczy(strScan);

								strGx=editGxmc.getText().toString();
								if(strGx.equals("")){
									Toast.makeText(Hb1.this, "请先扫描机台信息", Toast.LENGTH_SHORT).show();
								}else if (strGx.equals("装配")) {
									editCzy.setEnabled(false);
									try {
										//arrayList3.clear();
										//arrayList3=dbUtil.inquireProductInfoczy(strScan);
										strCzy = arrayList3.get(1);

										if(arrayList4.size() == 0){
											arrayList4.add(arrayList3.get(0));
											editNote.setText(arrayList4.get(0));
										}else if (arrayList4.size() == 1){
											if(arrayList4.indexOf(arrayList3.get(0)) >= 0){
												Toast.makeText(Hb1.this, "该操作员已存在", Toast.LENGTH_SHORT).show();
											}else {
												arrayList4.add(arrayList3.get(0));
												editNote.setText(arrayList4.get(0) + "," + arrayList4.get(1));
											}
										}else if(arrayList4.size() == 2){
											if(arrayList4.indexOf(arrayList3.get(0)) >= 0){
												Toast.makeText(Hb1.this, "该操作员已存在", Toast.LENGTH_SHORT).show();
											}else {
												arrayList4.add(arrayList3.get(0));
												editNote.setText(arrayList4.get(0) + "," + arrayList4.get(1) + "," + arrayList4.get(2));
											}
										}

										/*

										if (arrayList4.size() == 0) {
											arrayList4.add(arrayList3.get(0));
											editNote.setText(arrayList4.get(0));
											//strCzy=arrayList3.get(1);
										} else if (arrayList4.size() == 1) {
											if (arrayList4.contains(arrayList3.get(1))==false) {
												arrayList4.add(arrayList3.get(0));
												editNote.setText(arrayList4.get(0) + "," + arrayList4.get(1));
												//strCzy=arrayList3.get(1);
											} else {
												Toast.makeText(Hb1.this, "该操作员已存在", Toast.LENGTH_SHORT).show();
											}
										} else if (arrayList4.size() == 2) {
											if (arrayList4.contains(arrayList3.get(1))==false) {
												arrayList4.add(arrayList3.get(0));
												editNote.setText(arrayList4.get(0) + "," + arrayList4.get(1) + "," + arrayList4.get(2));
												//strCzy=arrayList3.get(1);
											} else {
												Toast.makeText(Hb1.this, "该操作员已存在", Toast.LENGTH_SHORT).show();
											}
										}

										*/


									} catch (Exception e) {
										Toast.makeText(Hb1.this, "操作员信息查询失败", Toast.LENGTH_SHORT).show();
									}
								}else {
									try {
										//arrayList3.clear();
										//arrayList3=dbUtil.inquireProductInfoczy(strScan);
										editCzy.setText(arrayList3.get(0));
										strCzy = arrayList3.get(1);

									} catch (Exception e) {
										Toast.makeText(Hb1.this, "操作员信息查询失败", Toast.LENGTH_SHORT).show();
									}
								}

								editCzy.setEnabled(false);
							}

							// 显示
							//mImageView.setImageBitmap((Bitmap) data
							//.getParcelableExtra("bitmap"));
						}
					}
					break;
			}
		}catch(Exception e)
		{
			Toast.makeText(Hb1.this, "连接失败", Toast.LENGTH_SHORT).show();
		}
	}




	//更新数据库
	private void setAddDialog() 
	{
		strPh = editKahao.getText().toString();
		strJth = editJth.getText().toString();
		//strCzy = editCzy.getText().toString();
		strLpsl = editLpsl.getText().toString();
		strBlpsl = editBlpsl.getText().toString();
		strJhsl = editJhsl.getText().toString();
		strCpmc = editCpmc.getText().toString();
		strGgxh = editGgxh.getText().toString();
		strcus = "0";
		strnote = editNote.getText().toString();




		if (strPh.equals("")  ) {
			Toast.makeText(Hb1.this, "批号不能为空", Toast.LENGTH_SHORT).show();
		}
		else if (strJth.equals("")  ) {
			Toast.makeText(Hb1.this, "机台号不能为空", Toast.LENGTH_SHORT).show();
		}	
		else if (strCzy.equals("")  ) {
			Toast.makeText(Hb1.this, "操作员不能为空", Toast.LENGTH_SHORT).show();
		}
		else if (strLpsl.equals("")  ) {
			Toast.makeText(Hb1.this, "良品数量不能为空", Toast.LENGTH_SHORT).show();
		}
		else if (strBlpsl.equals("")  ) {
			Toast.makeText(Hb1.this, "不良品数不能为空", Toast.LENGTH_SHORT).show();
		}
		else{
		try
		{
			//dbUtil.insertProductInfohb(editKahao.getText().toString(),str10,str11,strBz,strDate,editJhsl.getText().toString(),editWcsl.getText().toString(),editCpmc.getText().toString(),editGgxh.getText().toString(),strid,strStyle,strnote,strcus);
			//dbUtil.insertProductInfohb(strPh,strJthid,strCzy,strBz,strDate,strJhsl,strWcsl,strCpmc,strGgxh,strid,strStyle,strnote,strcus);
			//Toast.makeText(Hb1.this, "汇报成功", Toast.LENGTH_SHORT).show();

			if(dbUtil.insertProductInfohb(strPh,strJthid,strCzy,strBz,strDate,strJhsl,strLpsl,strBlpsl,strCpmc,strGgxh,strid,strStyle,strnote,strcus,strGxid)){
				Toast.makeText(Hb1.this, "汇报成功", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(Hb1.this, "汇报失败", Toast.LENGTH_SHORT).show();
			}
	
		}
		catch(Exception e)
		{
			Toast.makeText(Hb1.this, "汇报失败", Toast.LENGTH_SHORT).show();
		}


			editKahao.setText("");
			editJth.setText("");
			editCzy.setText("");
			editJhsl.setText("");
			editLpsl.setText("");
			editBlpsl.setText("");
			editCpmc.setText("");
			editGgxh.setText("");
			editGxmc.setText("");
			editNote.setText("");


			editKahao.setEnabled(true);
			editJth.setEnabled(true);
			editCzy.setEnabled(true);
			editJhsl.setEnabled(true);
			editLpsl.setEnabled(true);
			editBlpsl.setEnabled(true);
			editCpmc.setEnabled(true);
			editGgxh.setEnabled(true);
			editGxmc.setEnabled(true);
			editNote.setEnabled(true);
		
		
		}
		
	}


}

