package com.example.pedometer;

import java.util.ArrayList;
import java.util.List;

import com.example.pedometer.db.PedometerDB;
import com.example.pedometer.fragment.FragmentAdapter;
import com.example.pedometer.fragment.FragmentAnalysis;
import com.example.pedometer.fragment.FragmentHistory;
import com.example.pedometer.fragment.FragmentPK;
import com.example.pedometer.fragment.FragmentPedometer;
import com.example.pedometer.fragment.FragmentSet;
import com.example.pedometer.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.Fragment;

/**
 * 这是pedometer的主页面
 * @author 李垭超
 *
 */
public class MainActivity extends FragmentActivity {
	private RadioGroup rgs;//用来切换各个页面
	private RadioButton btn1;//如果是用户第一次进入这个app没有进行注册，则会跳转到注册页面
	public List<Fragment> fragments = new ArrayList<Fragment>();//将5个fragment添加到这个list里

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.page_mian);
		btn1 = (RadioButton) findViewById(R.id.btn5);
		//判断用户是否进行注册过，如果没有进行注册则选中注册页面的radiobutton
		if (PedometerDB.getInstance(this).loadUser(1) == null) {
			btn1.setChecked(true);
		}
		
		rgs = (RadioGroup) findViewById(R.id.radioGroup);//实例化RadioGroup
		fragments.add(new FragmentHistory());
		fragments.add(new FragmentAnalysis());
		fragments.add(new FragmentPedometer());
		fragments.add(new FragmentPK());
		fragments.add(new FragmentSet());
		//自己写的一个fragment的适配器，进行几个页面的逻辑跳转
		new FragmentAdapter(MainActivity.this, fragments, R.id.Fragment, rgs,
				this);

	}

}
