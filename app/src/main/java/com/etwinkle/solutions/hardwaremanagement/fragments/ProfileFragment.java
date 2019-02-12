package com.etwinkle.solutions.hardwaremanagement.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.activities.LoginActivity;
import com.etwinkle.solutions.hardwaremanagement.models.UserObject;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Profile");

        UserObject user = ((CustomApplication)getActivity().getApplication()).getLoginUser();
        if(user == null){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
            Glide.with(view.getContext()).load(getResources().getDrawable(R.drawable.dummy)).into(circleImageView);
            TextView fullnames = (TextView)view.findViewById(R.id.profile_name);
            fullnames.setText(user.getRepName());
            TextView address = (TextView)view.findViewById(R.id.rep_id);
            address.setText(user.getEmployeeTypeName());
            TextView phone = (TextView)view.findViewById(R.id.phone);
//            phone.setText(user.getEmployeeId());
            TextView email = (TextView)view.findViewById(R.id.email);
            email.setText(user.getEmail());
            Button signOut = (Button) view.findViewById(R.id.sign_out);
            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            signOut.setVisibility(View.GONE);
        }
        return view;
    }

}
