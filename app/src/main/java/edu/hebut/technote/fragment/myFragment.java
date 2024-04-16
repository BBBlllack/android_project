package edu.hebut.technote.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.hebut.technote.MainActivity;
import edu.hebut.technote.R;
import edu.hebut.technote.activity.CollectActivity;
import edu.hebut.technote.activity.LishiActivity;
import edu.hebut.technote.activity.MyApplication;
import edu.hebut.technote.activity.PersonActivity;
import edu.hebut.technote.activity.ShoucangActivity;
import edu.hebut.technote.activity.UpdatePasswordActivity;

public class myFragment extends Fragment implements View.OnClickListener{

    TextView my_name_hou;
    TextView my_name1_hou;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view=null;
        if (view==null){
            view=inflater.inflate(R.layout.fragment_my,container,false);
            RelativeLayout button1=(RelativeLayout) view.findViewById(R.id.my_re1);
            RelativeLayout button2=(RelativeLayout) view.findViewById(R.id.my_re2);
            RelativeLayout button3=(RelativeLayout) view.findViewById(R.id.my_re3);
            RelativeLayout button4=(RelativeLayout) view.findViewById(R.id.my_re4);
            RelativeLayout button5=(RelativeLayout) view.findViewById(R.id.my_re5);
            View langChenge = view.findViewById(R.id.langChenge);
            my_name_hou=(TextView)view.findViewById(R.id.my_name_hou);
            my_name1_hou=(TextView)view.findViewById(R.id.my_name1_hou);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
            button4.setOnClickListener(this);
            button5.setOnClickListener(this);
            langChenge.setOnClickListener(this);
            my_name_hou.setText(MyApplication.getInstance().getUser_local().getZhanghao());
            my_name1_hou.setText(MyApplication.getInstance().getUser_local().getName());
        }
        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.my_re1:
                Log.w("DEBUG0", "BEFORE");
                Intent intent =new Intent(getActivity(), PersonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.my_re2:
                Intent intent2 =new Intent(getActivity(), UpdatePasswordActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.my_re3:
                Intent intent3 =new Intent(getActivity(), LishiActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
            case R.id.my_re4:
                Intent intent4 =new Intent(getActivity(), CollectActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;
            case R.id.my_re5:
                Intent intent5 =new Intent(getActivity(), MainActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
                break;
            case R.id.langChenge:
                MyApplication.getInstance().setLanguage(!MyApplication.getInstance().isLanguage());
//                if(MyApplication.getInstance().isLanguage()){
//                    MyApplication.getInstance().setLanguage(false);
//                }else{
//                    MyApplication.getInstance().setLanguage(true);
//                }
                Toast.makeText(getActivity(), "您已成功切换语言!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}