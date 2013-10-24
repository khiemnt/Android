package com.example.Fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity
{

    int mStackLevel = 1;
    static int count = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.btnNewFragment);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                count++;
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                if (count % 2 != 0)
//                {
//                    CountingFragment fragment1 = CountingFragment.newInstance();
//                    ft.replace(R.id.simple_fragment, fragment1);
//                    ft.commit();
//                }
//                else if (count % 2 == 0)
//                {
//                    TestFragment fragment2 = TestFragment.instance();
//                    ft.replace(R.id.simple_fragment, fragment2);
//                    ft.commit();
//                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                HoodFragment f = HoodFragment.instance();
                ft.replace(R.id.simple_fragment, f);
                ft.commit();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }


}
