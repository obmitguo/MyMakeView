package com.king.Listview;

import java.util.HashMap;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ListView listview;
    private String[] MyData=new String[500000];
  //定义hashMap 用来存放之前创建的每一项item 
    HashMap<Integer, View> lmap = new HashMap<Integer, View>(); 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
    }
	private void initData() {
		for(int i=0;i<MyData.length;i++){
			MyData[i]=i+1+"";
		}
		listview.setAdapter(new Myadapter());
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
				System.out.println("arg0: "+arg0+"arg1: "+view+" arg2: "+position+" arg3: "+position);
				
				Myadapter adapter = (Myadapter) arg0.getAdapter();
				View boosView =adapter.getView(position, view, null);
				ViewHolder tag = (ViewHolder) boosView.getTag();
				tag.firstView.setVisibility(View.GONE);
				tag.secondView.setVisibility(View.VISIBLE);
				/*TextView firstView=(TextView)view.findViewById(R.id.tv_first);
				TextView secondView=(TextView)view.findViewById(R.id.tv_second);
				firstView.setVisibility(View.GONE);
				secondView.setVisibility(View.VISIBLE);*/
				/*View childAt = arg0.getChildAt(0);
				View childAt1 = arg0.getChildAt(1);
				childAt.setVisibility(View.GONE);
				childAt1.setVisibility(View.GONE);*/
				
				return true;
			}
			
		});
	}

	private void initUI() {
		listview = (ListView) findViewById(R.id.lv_list);
	}
	
	
	class Myadapter extends BaseAdapter{

		@Override
		public int getCount() {
			return MyData.length;
		}

		@Override
		public Object getItem(int position) {
			return MyData[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder=null;
			if(lmap.get(position)==null){
				convertView=View.inflate(getApplicationContext(), R.layout.item_list_layout, null);
				holder=new ViewHolder();
				holder.textView=(TextView) convertView.findViewById(R.id.tv_num);
				holder.firstView=(TextView)convertView.findViewById(R.id.tv_first);
				holder.secondView=(TextView)convertView.findViewById(R.id.tv_second);
				convertView.setTag(holder);
				lmap.put(position, convertView);
			}else{
				convertView=lmap.get(position);
				holder=(ViewHolder) convertView.getTag();
			}
			holder.textView.setText((String)getItem(position));
			return convertView;
		}
		
	}
	
	 class ViewHolder{
		TextView textView,firstView,secondView;
	}
}
