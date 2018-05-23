package com.example.qr_codescan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

	//定义图标数组
	private int[] imageRes = {
			R.drawable.hb,
			R.drawable.cx,
			R.drawable.zx,
			R.drawable.rk,
			R.drawable.ck,
			R.drawable.kp,
			R.drawable.fq
	};

	//定义图标下方的名称数组
	private String[] name1 = {
			"工序汇报",
			"工序汇报查询",
			"装箱",
			"产品入库",
			"销售出库",
			"开片",
			"分切"
	};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		int length = imageRes.length;

		//生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", imageRes[i]);//添加图像资源的ID
			map.put("ItemText", name1[i]);//按序号做ItemText
			lstImageItem.add(map);
		}
		//生成适配器的ImageItem 与动态数组的元素相对应
		SimpleAdapter saImageItems = new SimpleAdapter(this,
				lstImageItem,//数据来源
				R.layout.item,//item的XML实现

				//动态数组与ImageItem对应的子项
				new String[]{"ItemImage", "ItemText"},

				//ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[]{R.id.img_hb, R.id.txt_hb});
		//添加并且显示
		gridview.setAdapter(saImageItems);
		//添加消息处理
		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(MainActivity.this,name[position],Toast.LENGTH_LONG).show();
				int index = position + 1;
                try {
                    if (index == 1) {
                        Intent intent1 = new Intent(MainActivity.this, Hb1.class);
                        startActivity(intent1);
                    } else if (index == 2) {
                        Intent intent2 = new Intent(MainActivity.this, Cx.class);
                        startActivity(intent2);
                    } else if (index == 3) {
                        Intent intent3 = new Intent(MainActivity.this, Zx.class);
                        startActivity(intent3);
                    } else if (index == 4) {
                        Intent intent4 = new Intent(MainActivity.this, Hb1.class);
                        startActivity(intent4);
                    } else if (index == 5) {
                        Intent intent5 = new Intent(MainActivity.this, Hb1.class);
                        startActivity(intent5);
                    } else if (index == 6) {
                        Intent intent6 = new Intent(MainActivity.this, Kp.class);
                        startActivity(intent6);
                    } else if (index == 7) {
                        Intent intent7 = new Intent(MainActivity.this, Fdfq.class);
                        startActivity(intent7);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"9宫格错误",Toast.LENGTH_LONG).show();
                }
			}
		});

	}

		     
	 
}
