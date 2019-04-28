package net.app.lblpack.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.app.lblpack.common.app.Application;
import net.app.lblpack.factory.utils.DBFlowExclusionStrategy;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Factory {
    private static final String TAG = Factory.class.getSimpleName();
    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;
    // 全局的Gson
    private final Gson gson;


    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // 设置一个过滤器，数据库级别的Model不进行Json转换
                .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    public static Application app(){
        return Application.getInstance();
    }

    /**
     * 异步运行的方法
     *
     * @param runnable
     */
    public  static void runOnAsync(Runnable runnable){
        // 拿到单例，拿到线程池
        instance.executor.execute(runnable);
    }


}
