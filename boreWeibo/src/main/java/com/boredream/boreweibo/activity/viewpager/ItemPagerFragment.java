package com.boredream.boreweibo.activity.viewpager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.boredream.boreweibo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemPagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    public RecyclerView mRecyclerView;

    public ListView mListView;
    private OnFragmentInteractionListener mListener;
    private int headerHeight;
    private int mLastScrolly;
    private LinearLayout topheader;

    public ItemPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemPagerFragment newInstance(int param1, String param2) {
        ItemPagerFragment fragment = new ItemPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_layout_height);
        View view = inflater.inflate(R.layout.fragment_item_listview, container, false);
        mListView = (ListView) view;
        mListView.setAdapter(new ListViewAdapter(getContext()));
        topheader = (LinearLayout) inflater.inflate(R.layout.top_header_in_vp, mListView, false);
        mListView.addHeaderView(topheader);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == 0) {
                    ScrollStateHelper.getInstance().getBgImg().layout(0, topheader.getTop(), topheader.getWidth(), topheader.getBottom());
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mParam1 != ScrollStateHelper.getInstance().getCurFragmentIndex())
                    return;
                Log.e("tag", "onScroll: --- " + mParam1);
                int curScrolly = getScrollY();
                if (mLastScrolly != curScrolly) {
                    Log.e("tag", "onScroll: onlayout img!");
                    ScrollStateHelper.getInstance().getBgImg().layout(0, topheader.getTop(), topheader.getWidth(), topheader.getBottom());
                    mLastScrolly = curScrolly;
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public int getScrollY() {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();
        if (firstVisiblePosition == 0) {
            return -top;
        }
        return headerHeight - top + (firstVisiblePosition - 1) * c.getHeight();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
