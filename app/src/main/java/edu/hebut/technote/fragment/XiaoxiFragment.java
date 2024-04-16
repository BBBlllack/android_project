package edu.hebut.technote.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.activity.MailXiangqingActivity;
import edu.hebut.technote.activity.MyApplication;
import edu.hebut.technote.dao.EmailDao;
import edu.hebut.technote.entity.Email;


public class XiaoxiFragment extends Fragment{

    ListView listView;
    private LiveData<List<Email>> allEmails;
    public EmailDao emailDao;
    private ArrayAdapter<Email> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        if (view==null){
            view=inflater.inflate(R.layout.fragment_xiaoxi,container,false);
            listView=(ListView)view.findViewById(R.id.lv_my_collection);
            emailDao= MyApplication.getInstance().getmyDataBase().emailDao();
            int id=MyApplication.getInstance().getUser_local().getId();
            String yx=MyApplication.getInstance().getUser_local().getYouxiang();
            allEmails=emailDao.chabyuseridall(id,yx);
            allEmails.observe(getActivity(), new Observer<List<Email>>() {
                @Override
                public void onChanged(List<Email> emails) {
                    adapter = new ArrayAdapter<>(getActivity(), R.layout.items, emails);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Email clickedEmail = (Email) parent.getItemAtPosition(position);
                            MyApplication.getInstance().setEmail(clickedEmail);
                            Intent intent = new Intent(getActivity(), MailXiangqingActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
        return  view;
    }

}