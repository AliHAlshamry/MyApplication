package com.example.hp.myapplication;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends Activity {
    public final String SOAP_ACTION = "http://tempuri.org/Add";

    public  final String OPERATION_NAME = "Add";

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public  final String SOAP_ADDRESS = "http://grasshoppernetwork.com/NewFile.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tx=(TextView) findViewById(R.id.textView2);
        Button btn =(Button) findViewById(R.id.button);
        EditText x=(EditText) findViewById(R.id.editText);
        EditText y=(EditText) findViewById(R.id.editText2);
        final int a= Integer.parseInt(x.toString());
        final int b=Integer.parseInt(y.toString());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
                PropertyInfo pi=new PropertyInfo();
                pi.setName("a");
                pi.setValue(a);
                pi.setType(Integer.class);
                request.addProperty(pi);
                pi=new PropertyInfo();
                pi.setName("b");
                pi.setValue(b);
                pi.setType(Integer.class);
                request.addProperty(pi);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
                Object response=null;
                try
                {
                    httpTransport.call(SOAP_ACTION, envelope);
                    response = envelope.getResponse();

                    tx.setText(response.toString());
                }
                catch (Exception exception)
                {
                    response=exception.toString();
                }


            }
        });
    }
}
