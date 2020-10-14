package com.paygpayment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.paygpayment.api.Communicator;
import com.paygpayment.api.Constants;
import com.paygpayment.api.CustomResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import cz.msebera.android.httpclient.entity.StringEntity;


public class PayGPayment   {
    private static String AuthenticationKey = "dcb3ef4d84254929a0e81f14c3ddefbf";
    /** @var string AuthenticationToken For Payment Provided By Gateway */
    private static String  AuthenticationToken  = "cb11d695d23e4a78bef04b90519b4b30";
    /** @var  string SecureHashKey For Payment Provided By Gateway */
    private static String SecureHashKey  = "b29cd704083442e2ac2e73f903167da4";
    /** @var  string MerchantKeyId For Payment Provided By Gateway. */
    private static String UniqueRequestId = "";
    private static String Merchantkeyid = "7771";
    private static Activity Activity;


    public static String generateRandomString(){
        int length = 10;
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(length);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();

    }

    public static void  createOrder(){
        //progress bar show
        JSONObject jsonParams = new JSONObject();
        StringEntity entity = null;
        try {
            jsonParams.put("Merchantkeyid", Merchantkeyid);
            jsonParams.put("UniqueRequestId", UniqueRequestId);
            jsonParams.put("OrderAmount", 1000);
            jsonParams.put("OrderType", "");
            jsonParams.put("OrderStatus", "");
            jsonParams.put("OrderAmountData", "");
            jsonParams.put("ProductData", "");
            jsonParams.put("NextStepFlowData", "");

            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Params", jsonParams.toString());
        Communicator communicator = new Communicator();
        communicator.post(1, Activity, Constants.Apis.CREATE_ORDER, entity, new CustomResponseListener() {
            @Override
            public void onResponse(int requestCode, String response) {
             //progress bar hide

                /*   try {
                    LoginResponse loginResponse = (LoginResponse) Utils.getObject(response, LoginResponse.class);
                    if (loginResponse.getSuccess()) {
                        preference.putString("isLogin", "yes");
                        Toast.makeText(mActivity, "1", Toast.LENGTH_SHORT).show();
                        Utils.saveLoginUser(mActivity, loginResponse);
                        Utils.startActivityFinish(mActivity, HomeActivity.class);
                    } else if (loginResponse.getStatus().equals("400")){
                        Toast.makeText(mActivity, "2", Toast.LENGTH_SHORT).show();
                        Utils.showToastPopup(mActivity, loginResponse.getMessage());
                    }

                    else{
                        Toast.makeText(mActivity, "3", Toast.LENGTH_SHORT).show();
                        Utils.showToastPopup(mActivity, loginResponse.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.showToastPopup(mActivity, "Response format is not proper");
                }*/
            }

            @Override
            public void onFailure(int statusCode, Throwable error) {
                //progress bar hide
            }
        });

    }


}
