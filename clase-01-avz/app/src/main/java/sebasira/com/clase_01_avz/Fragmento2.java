package sebasira.com.clase_01_avz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sebas on 9/6/16.
 */
public class Fragmento2 extends Fragment {
    TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag2,container,false);
        tv = (TextView) rootView.findViewById(R.id.tv);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).clickFragmento2();
            }
        });
    }

    public void fragmentoSeleccionado(boolean state){
        if (state){
            tv.setText("Seleccionado");
        }else{
            tv.setText("No Seleccionado");
        }
    }
}