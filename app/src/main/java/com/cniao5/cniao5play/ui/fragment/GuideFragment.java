package com.cniao5.cniao5play.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.cniao5play.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {


    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.rootView)
    LinearLayout mRootView;
    Unbinder unbinder;

    public GuideFragment() {
        // Required empty public constructor
    }

    public static final String IMG_ID = "img_id";
    public static final String COLOR_ID = "color_id";
    public static final String TEXT_ID = "text_id";

    private View mView;


    public static GuideFragment newInstance(int imgResId, int bgColorResId, int textResId) {
        GuideFragment guideFragment = new GuideFragment();
        Bundle bundle = new Bundle();

        bundle.putInt(IMG_ID, imgResId);
        bundle.putInt(COLOR_ID, bgColorResId);
        bundle.putInt(TEXT_ID, textResId);


        guideFragment.setArguments(bundle);
        return guideFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide, container, false);

        unbinder = ButterKnife.bind(this, mView);

        initData();
        return mView;

    }

    private void initData() {
        Bundle args = getArguments();
        int imgId = args.getInt(IMG_ID);
        int colorId = args.getInt(COLOR_ID);
        int textId = args.getInt(TEXT_ID);

        mRootView.setBackgroundColor(getResources().getColor(colorId));
        mImgView.setImageResource(imgId);
        mText.setText(textId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
