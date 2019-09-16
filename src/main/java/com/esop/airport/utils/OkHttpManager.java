package com.esop.airport.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liliqiang on 2018/8/3.
 */
public class OkHttpManager {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static volatile OkHttpManager sOkHttpManager;

    private OkHttpClient mClient;

    public static OkHttpManager getInstance() {
        if (sOkHttpManager == null) {
            sOkHttpManager = new OkHttpManager();
        }
        return sOkHttpManager;
    }


    /**
     * 构造方法
     */
    private OkHttpManager() {
        mClient = new OkHttpClient();
        mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * 同步 GET 方法
     *
     * @param url
     * @return
     */
    public Response getSync(Map<String, String> header, String url) {
        Request.Builder builder = new Request.Builder();
        if (header != null) {
            header.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        Request request = builder.url(url).build();
        return sOkHttpManager.innerSync(request);
    }

    /**
     * 同步 POST 方法
     *
     * @param url
     * @param header
     * @param data
     * @return
     */
    public Response postSync(String url, Map<String, String> header , String data) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, data);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (header != null) {
            header.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        Request request = builder.build();
        return sOkHttpManager.innerSync(request);
    }

    /**
     * 同步 POST 方法
     *
     * @param url
     * @param data
     * @return
     */
    public Response postSync(String url, Map<String, String> header, Map<String, String> data) {

        FormBody.Builder formBody = new FormBody.Builder();
        if (data != null) {
            data.forEach((k, v) -> {
                formBody.add(k, v);
            });
        }
        Request.Builder builder = new Request.Builder().url(url).post(formBody.build());
        if (header != null) {
            header.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        Request request = builder.build();
        return sOkHttpManager.innerSync(request);
    }

    /**
     * 异步GET响应
     *
     * @param url
     * @param dataCallBack
     */
    public void getAsync(String url, IDataCallBack dataCallBack) {
        final Request request = new Request.Builder().url(url).build();
        innerAsync(request, dataCallBack);
    }

    /**
     * 异步POST
     *
     * @param url
     * @param data
     * @param dataCallBack
     */
    public void postAsync(Map<String, String> header, String url, String data, IDataCallBack dataCallBack) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, data);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        header.forEach((k, v) -> {
            builder.addHeader(k, v);
        });
        final Request request = builder.build();
        innerAsync(request, dataCallBack);
    }

    private Response innerSync(Request request) {
        Response response = null;
        try {
            //同步请求返回的是response对象
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    private void innerAsync(Request request, final IDataCallBack callBack) {
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                deliverDataSuccess(result, callBack);
            }
        });
    }

    private void deliverDataFailure(Request request, IOException e, IDataCallBack callBack) {
        ThreadPoolManager.getInstance().addCachedThreadTask(() -> {
            callBack.requestFailure(request, e);
        });
    }

    private void deliverDataSuccess(String result, IDataCallBack callBack) {
        ThreadPoolManager.getInstance().addCachedThreadTask(() -> {
            try {
                callBack.requestSuccess(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        String uuid = "faaec213ac214d2e91e92f6491310fbc,167c4d338a424ec3bc95b8310ad53b11,f654f27f5d3d46a687ceddc1c7f4e3cb,b514d28e7255423db86918247fe83c8b,11bbcdc8b5af4d249a33631105ba34af,875a06439309420aa57e88194ae4ca4e,be8fb6448a694da9855b283c423e07b8,15c95627ba964a24ba04c9a54c693750,48ab2f054b5644fba1a82f2c266a779e,1e3e04341e084863bd0dcd49ea4fc0ed,8b69f97907454b5a8b151c8228a6a2b7,965e1c61a90b402f9938915869de1839,132628c00e314f71b9c021913871e8ad,4fc55b59a7c74dd2ac7f2523dc6a682a,504d5d99809f49429e9af93a19963934,03d1a0ab51c044cca48bfa131a5eee45,0b2ba1e5462b430a82d972bee140aedf,1e3764713fd14f82bbc2472328750e4f,fbf550a345934426aaeb9da8c4e1c86e,5686a022bffd45a18ee9dceba6d30422,7503d58539fb480ba4e798b4ca8313de,fcb436bf0f1147df939062c090e686ff,dc1a3d78e20948378aa384bfaffb8f89,c78769bd252e4078b7c8c79937b72fb7,64a392517fbc49b1b1edbbcb839d4f1c,5008420a329143d08bd1640f894a3f01,16e98c4a13464830ad533aa06d613372,343db6e1ecdf4088abd6353cbbd36a09,470b066e57e549bca6e937dd0f18019d,4ce81d90a57f4e9b863e1260b32073c6,d0449959c84545efbb8a5d9a6900256f,1830db02c70c4e9b94f8c830c5dc6adc,574e82082f844344a3e5196bf9240569,15ecb1b2304a403d93081e0c64c27bab,3de46c016dc54a5fa60865faa28c6e23,4905e650d2c84f4daa401c1030c9fa82,5e129e5c8ccb4d1e9785c39c661f3826,cef0b9a5610144c48151b0c913e66470,d3040105474d4b32884f7e47ca9b0700,ace7f45d34ab488c9800cecc642debf0,f951c31d584543169ac45d95148fcf22,bf7d187842e24f4094e4e03bfde49f33,d4809d1f675544049d89de3d2dcdef09,9ae1653a8a1f41cab6473dc8108c8924,5b277fc2b9284fc594d11d48b8036a18,00fcd9ba8df5494480e772e80c6231e5,1df48561931242d0bdbc9ec41a7807f7,ec293c72098244229a7df2fdd3ff2aac,9677ea3ddea4446580afe67d8494119a,cc5ca4aa140943adb7e71582fa1decef,6db875b1822745609205be245cef7e0c,c9d1ab5097d9486285699c5afd8e0e03,c3af5fabe90e454396c56919f4062205,ffb1fd95e2e14dfab075ebe7866eea0d,4174549e3ee3417e8228d0de789bfe65,54835b10d10349419afc599315d79f10,95ac24c74ad4433daad5f22bbc347dd2,38d776389fdd4a89b215d4f7ccbc9bf7,180de20b22ba4017b98a02b89bdc8f8b,e549728aac9c49cfb2f7598d41c3c016,210f1bd351b44df79ac25ad97eb87d87,2a99f8227fc84e59bbf89cff8fb33f2c,b7b5010fb5264c37a1479416567b58a6,287795f15c8b4777b2c3aaf1000a110e,bddad181bb2d48ef9a740c9780470227,98052a2fb44f4de2afe178d883dcf0ab,9afffecb910641cca0dfeba1801aa6ce,69f267fa1ea04a81806170ce263a01fd,f314023ba3454f8ebae9bad7c9bfe290,893e6d1dd3474a41990d341f1e957dab,71867b806b47466dad9070e620d8d390,661e7a07be894ec58eb9571604b1821d,aa39e4036cfc4184a6ffad98310a9698,33dbb482c4e44da699d4bdf22a7785f6,9fd3c3ed2f3d4981aa8ac3058d74e972,a0c64dd7b9754107afddbfc972fc5b7d,03b06bd6fb8c4a1ea9ce12eed7a4638b,ef70057b970d47bfaeb6a54558d55e69,5e65d537d9c04f09b198316dea0bfe3d,3db44396da9642d5a64d0bb42f5249c6,87d0071e94394ce88bb36415a77be32f,052456a03c914e399a93fdbf3ed42457,4a4b5671d28d43198812112768ced8b8,39c86b238a2e4ccaab036bfb3f1c9665,101cc8b292e64a098f0cb092cbb2b00f,f69c0750721d4b3e9c25c1e3ead1c64c,847fe0b0724746fb970e19a036316474,451cf42337d0493f9e7266a3e04e6aa7,699c58ccece64968aa7f3ad2c92a8ebf,5da190b3ba294f16aa5f623b6032ac87,ad0c5d857c44442eaad551bf2576e83c,f4607506b50843779cf6bb2976de8dfa,64dd0553c04c4023bdb75e7badbfe980,cd1f6c6c986241be9d422b8055677e6a,0d342827697a48649f97d8702d31db36,7a3780580e3542a5b503698ca6c1b07d,fcbc616a90fa4bd0aaa49fc8c5013a7f";

        String[] uuids = uuid.split(",");

        for (String u : uuids) {

            Response response = OkHttpManager.getInstance().getSync(null, "https://hlwactapi.hulian120.com/open/getTaskData?uuid=" + u + "&pass=pass_daboge_ok&type=1");

            JSONObject resJson = JSON.parseObject(response.body().string());

            System.out.println("uuid >>> " + u + "-- " + resJson.getString("data"));

        }
    }

}
