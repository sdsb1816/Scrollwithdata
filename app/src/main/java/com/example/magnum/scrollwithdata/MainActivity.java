package com.example.magnum.scrollwithdata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ConnectionClass connectionClass;

    List<cData> ll = new LinkedList();

    class cData {
        public int cid;
        public String cname;
        public String ccomment;
    }


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionClass = new ConnectionClass();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        getColleges college = new getColleges();
        college.execute("");



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber,int cid,String name,String comment) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt("COLLEGEID",cid);
            args.putString("COLLEGENAME",name);
            args.putString("COLLEGECOMMENT",comment);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);


            TextView textView2 = (TextView) rootView.findViewById(R.id.cid);


            TextView textView3 = (TextView) rootView.findViewById(R.id.cname);


            TextView textView4 = (TextView) rootView.findViewById(R.id.ccomment);


            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            textView2.setText(getString(R.string.section_format, getArguments().getInt("COLLEGEID")));
            textView3.setText(getString(R.string.section_format2, getArguments().getString("COLLEGENAME")));
            textView4.setText(getString(R.string.section_format2,getArguments().getString("COLLEGECOMMENT")));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Log.d("Fragment Get Item","Called");



            cData coll=new cData();
            Log.d("hell", " "+position);
            /*
            coll.cid=ll.get(position+1).cid;
            coll.cname=ll.get(position+1).cname;
            coll.ccomment=ll.get(position+1).ccomment;

            */
            coll=ll.get(position + 1);

            Log.d("Getitem",coll.cname+ " "+coll.ccomment);

            return PlaceholderFragment.newInstance(position + 1,coll.cid, coll.cname,coll.ccomment);




        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;





            //
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }



public class getColleges extends AsyncTask<String, String, String> {
    String z = "";
    Boolean isSuccess = false;





    @Override
    protected void onPreExecute() {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Getting Data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected void onPostExecute(String r) {
        Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
        if (isSuccess) {
            /*
            Intent i = new Intent(MainActivity.this,User.class);
            i.putExtra("Name", name);
            startActivity(i);
            finish();
            */
            Log.d("OnPost",ll.get(0).ccomment+" "+ ll.get(0).cname);

            return;
        }
    }

    @Override
    protected String doInBackground(String... params) {
            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    z = "Error in connection with SQL server";
                } else {
                    String query = "select * from calls";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    Log.d("Before While","");
                    while (rs.next()) {

                        int cid=rs.getInt("cid");
                        String name=rs.getString("name");
                        String comment=rs.getString("comment");

                        cData college=new cData();
                        college.cid=cid;
                        college.cname=name;
                        college.ccomment=comment;

                        Log.d("Data server",name+"\t"+comment);

                        ll.add(college);

                        Log.d("List data 0 is",ll.get(0).ccomment+" "+ ll.get(0).cname);

                        /*
                        rs = st.executeQuery("SELECT COUNT(*) FROM survey");
                        // get the number of rows from the result set
                        rs.next();
                        int rowCount = rs.getInt(1);
                        */



                        z = "Successfull";
                        isSuccess = true;

                    }
                }
            } catch (Exception ex) {
                isSuccess = false;
                z = "Exceptions";
            }

        return z;
    }
}


}

