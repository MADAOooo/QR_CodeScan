package com.example.qr_codescan;


import android.widget.Toast;

import java.util.ArrayList;


public class DBUtil 
{
	private ArrayList<String> arrayList1 = new ArrayList<String>();
	private ArrayList<String> arrayList2 = new ArrayList<String>();
	private ArrayList<String> arrayList3 = new ArrayList<String>();
	
	private HttpConnSoap Soap = new HttpConnSoap();
	private ssoap ssoap = new ssoap();
	String s;
	String s2;
	String s3;
	//String ss[]= new String [20];


	//工序汇报
	//按批号查询一条信息
	public ArrayList<String>  inquireProductInfoph(final String kahao)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("kahao");
		arrayList2.add(kahao);
		//arrayList1.add("jth");
		//arrayList2.add(jth);
		//arrayList1.add("czy");
		//arrayList2.add(czy);

		//new Thread()
		//{

		//public void run(){

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfoph",kahao);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		//}

		//}.start();


		return arrayList3;

	}

	//查询机台号信息
	public ArrayList<String>  inquireProductInfojth(final String jth)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("jth");
		arrayList2.add(jth);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfojth",jth);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	//查询操作员信息
	public ArrayList<String>  inquireProductInfoczy(final String czy)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("czy");
		arrayList2.add(czy);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfoczy",czy);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	//增加一条工序汇报信息
	//public void insertProductInfohb(String kahao,String jth,String czy,String bz,String zysj,String jhsl,String wcsl,String cpmc,String ggxh,String wlid,String style,String note,String cus)
    public boolean insertProductInfohb(String kahao,String jth,String czy,String bz,String zysj,String jhsl,String lpsl,String blpsl,String cpmc,String ggxh,String wlid,String style,String note,String cus,String gxid)
	{
        try {
            arrayList1.clear();
            arrayList2.clear();

            arrayList1.add("kahao");
            arrayList1.add("jth");
            arrayList1.add("czy");
            arrayList1.add("bz");
            arrayList1.add("zysj");
			arrayList1.add("jhsl");
            arrayList1.add("lpsl");
			arrayList1.add("blpsl");
            arrayList1.add("cpmc");
            arrayList1.add("ggxh");
            arrayList1.add("wlid");
            arrayList1.add("style");
            arrayList1.add("note");
            arrayList1.add("cus");
			arrayList1.add("gxid");
            arrayList2.add(kahao);
            arrayList2.add(jth);
            arrayList2.add(czy);
            arrayList2.add(bz);
            arrayList2.add(zysj);
            arrayList2.add(jhsl);
            arrayList2.add(lpsl);
			arrayList2.add(blpsl);
            arrayList2.add(cpmc);
            arrayList2.add(ggxh);
            arrayList2.add(wlid);
            arrayList2.add(style);
            arrayList2.add(note);
            arrayList2.add(cus);
			arrayList2.add(gxid);

            /*
            new Thread() {
                public void run() {
                    try {
                        Soap.GetWebServer("insertProductInfohb", arrayList1, arrayList2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            */

            if(Soap.GetWebServer("insertProductInfohb", arrayList1, arrayList2).equals("false")){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return false;
        }

	}

	//工序汇报查询
	//查询批号相关作业汇报信息
	public ArrayList<String>  inquireProductInfophhb(final String ph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("ph");
		arrayList2.add(ph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfophhb",ph);

		} catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}

	//查询操作员今天作业汇报信息
	public ArrayList<String>  inquireProductInfojthb(final String ph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("ph");
		arrayList2.add(ph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfojthb",ph);

		} catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	//查询操作员本月作业汇报信息
	public ArrayList<String>  inquireProductInfobyhb(final String ph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("ph");
		arrayList2.add(ph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfobyhb",ph);

		} catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}

	//开片
	//查询开片相关信息
	public ArrayList<String>  inquireProductInfokp(final String ph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("ph");
		arrayList2.add(ph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfokp",ph);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	//增加开片表头信息
	public void insertProductInfokp(String cpid,String cs,String k,String inph,String tuhao,String kprq,String kh) 
	{
		arrayList1.clear();
		arrayList2.clear();
		
		arrayList1.add("cpid");
		arrayList1.add("cs");
		arrayList1.add("k");
		arrayList1.add("inph");
		arrayList1.add("tuhao");
		arrayList1.add("kprq");
		arrayList1.add("kh");
		arrayList2.add(cpid);
		arrayList2.add(cs);
		arrayList2.add(k);
		arrayList2.add(inph);
		arrayList2.add(tuhao);
		arrayList2.add(kprq);
		arrayList2.add(kh);
				try
				{
					Soap.GetWebServer("insertProductInfokp", arrayList1, arrayList2);
				}
				catch(Exception e)
				{
					
				}
			
	}
	//增加开片表体信息
	public void insertProductInfokpt(String lbid,String lbsgph,String lbjyph,String cbmin,String lbcd,String bs,String kd,String l,String sl,String mj,String fnh,String inph,String dq1,String dqb1,String dq2,String dqb2) 
	{
		arrayList1.clear();
		arrayList2.clear();
		
		arrayList1.add("lbid");
		arrayList1.add("lbsgph");
		arrayList1.add("lbjyph");
		arrayList1.add("cbmin");
		arrayList1.add("lbcd");
		arrayList1.add("bs");
		arrayList1.add("kd");
		arrayList1.add("l");
		arrayList1.add("sl");
		arrayList1.add("mj");
		arrayList1.add("fnh");
		arrayList1.add("inph");
		arrayList1.add("dq1");
		arrayList1.add("dqb1");
		arrayList1.add("dq2");
		arrayList1.add("dqb2");
		
		arrayList2.add(lbid);
		arrayList2.add(lbsgph);
		arrayList2.add(lbjyph);
		arrayList2.add(cbmin);
		arrayList2.add(lbcd);
		arrayList2.add(bs);
		arrayList2.add(kd);
		arrayList2.add(l);
		arrayList2.add(sl);
		arrayList2.add(mj);
		arrayList2.add(fnh);
		arrayList2.add(inph);
		arrayList2.add(dq1);
		arrayList2.add(dqb1);
		arrayList2.add(dq2);
		arrayList2.add(dqb2);
				try
				{
					Soap.GetWebServer("insertProductInfokpt", arrayList1, arrayList2);
				}
				catch(Exception e)
				{
					
				}
		
		
	}


	//负极箔/电解纸裁切
	//查询负极箔/电解纸检验信息
	public ArrayList<String>  inquireProductInfofdfq(final String ph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("ph");
		arrayList2.add(ph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfofdfq",ph);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	//增加负极箔/电解纸裁切信息
	public void insertProductInfofdfq(String cqrq, String czy,String tm,String ph,String xh,String wlid,String yxkd,String sl,String srkd,String srbs,String sqzkd)
	{
		arrayList1.clear();
		arrayList2.clear();

		arrayList1.add("cqrq");
		arrayList1.add("czy");
		arrayList1.add("tm");
		arrayList1.add("ph");
		arrayList1.add("xh");
		arrayList1.add("wlid");
		arrayList1.add("yxkd");
		arrayList1.add("sl");
		arrayList1.add("srkd");
		arrayList1.add("srbs");
		arrayList1.add("sqzkd");
		arrayList2.add(cqrq);
		arrayList2.add(czy);
		arrayList2.add(tm);
		arrayList2.add(ph);
		arrayList2.add(xh);
		arrayList2.add(wlid);
		arrayList2.add(yxkd);
		arrayList2.add(sl);
		arrayList2.add(srkd);
		arrayList2.add(srbs);
		arrayList2.add(sqzkd);

		try
		{
			Soap.GetWebServer("insertProductInfofdfq", arrayList1, arrayList2);
		}
		catch(Exception e)
		{

		}


	}

	//增加负极箔/电解纸裁切表体信息
	public void insertProductInfofdfqbt(String srkd, String srbs,String xh)
	{
		arrayList1.clear();
		arrayList2.clear();
		arrayList1.add("srkd");
		arrayList1.add("srbs");
	    arrayList1.add("xh");

		arrayList2.add(srkd);
		arrayList2.add(srbs);
		arrayList2.add(xh);

		try
		{
			Soap.GetWebServer("insertProductInfofdfqbt", arrayList1, arrayList2);
		}
		catch(Exception e)
		{

		}

	}






	public ArrayList<String>  inquireProductInfozzt(final String tuhao)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("tuhao");
		arrayList2.add(tuhao);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfozzt", tuhao);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}
	public ArrayList<String>  inquireProductInfojybg(final String jyph)
	{

		arrayList1.clear();
		arrayList2.clear();
		arrayList3.clear();

		arrayList1.add("jyph");
		arrayList2.add( jyph);

		try
		{

			arrayList3=ssoap.GetWebServer("inquireProductInfojybg", jyph);

		}
		catch(Exception e)
		{
			//System.out.println("程序出错");
		}

		return arrayList3;

	}



}