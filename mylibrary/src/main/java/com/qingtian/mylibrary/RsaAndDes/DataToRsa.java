package com.qingtian.mylibrary.RsaAndDes;

import android.app.Dialog;
import android.content.Context;

import org.json.JSONObject;


/** 
 * Author: lkp
 * Date: 2017/3/10 13:09 
 * QQ: 425116228
 * 功能描述: 
*/
public class DataToRsa {

    private JSONObject json;
    private Dialog mdialog;

    public  synchronized void GoRsa(Context context, final JSONObject jsonObject, String url,
                                    final OnResponseListener dataListener) {
        /**
         * 数据传输前加密
         */
        JSONObject  json=SetSecretKey(jsonObject);
        mdialog.show();
//            OkGo.post(url)
//                     .headers("application/json;charset=utf-8", "Content-Type")
//                     .upJson(json)
//                     .execute(new StringCallback() {
//                         @Override
//                         public void onSuccess(String s, Call call, Response response) {
//                             String treasureDESKey = "";
//                             String  treasureData="";
//                             try {
//                                 JSONObject jsonObj = new JSONObject(s);
//                                 treasureDESKey = jsonObj.getString("treasureDESKey");
//                                 treasureData = jsonObj.getString("treasureData");
//                             } catch (JSONException e) {
//                                 e.printStackTrace();
//                             }
//
//                             /**
//                              *  利用私钥进行解密key
//                              */
//                             GetSecretKey(treasureDESKey,treasureData,dataListener);
//
//                         }
//
//                         @Override
//                         public void onError(Call call, Response response, Exception e) {
//                             super.onError(call, response, e);
//                         }
//                     });

    }

    /**
     * 数据传输前加密
     * @param jsonObject
     *
     */
    private  JSONObject SetSecretKey(JSONObject jsonObject) {
        //1.生成key
        String key = ThreeDes.generatorSecretKey();
        //2.把传进来的Json转换为String
        String InData=jsonObject.toString();

        try {
            //3.把key和json进行3des加密
            ThreeDes.encode(InData, key);
            //4.把key进行RSA加密
            String cryptData = MyRSA.cryptData(key);
            json = new JSONObject();
            //根据需求是否增加 treasureID
            json.put("treasureID", 402216352L);
            json.put("treasureData", ThreeDes.encode(InData, key));
            json.put("treasureDESKey", cryptData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    /**
     * 解密请求的json数据并回调
     */
    private void GetSecretKey(String treasureDESKey, String treasureData,
                              OnResponseListener dataListener) {
        //RSA解密服务器端返回的key
        String desKey = MyRSA.decryptData(treasureDESKey);
        try {
            //3Des
            String result=ThreeDes.decode(treasureData, desKey);
            JSONObject Resultjson=new JSONObject(result);
            //{"errCode":"200401","errMsg":"用户不存在","responseTime":"1482952581"}
            //根据服务器的返回字段判断是否成功
            String Code=Resultjson.getString("errCode");
            String errinfon=Resultjson.getString("errMsg");
            //解析json
            String ResultData= String.valueOf(Resultjson.get("result"));
            if(Code!=null){
                String errCode = Code.substring(Code.length()-2);
                if(!errCode.equals("88")){
                    mdialog.dismiss();
                    dataListener.error("网络请求失败",errCode);
                }else {
                    mdialog.dismiss();
                    dataListener.success(result);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 定义回调的接口
     */
    public interface OnResponseListener {
        public void success(String str);
        public void error(String msg, String code);
    }

}
