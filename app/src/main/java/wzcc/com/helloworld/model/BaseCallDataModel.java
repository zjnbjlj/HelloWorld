package wzcc.com.helloworld.model;

import java.util.List;

/**
 * Created by jlj on 2017/7/11.
 */

public class BaseCallDataModel<T>{

    public List<T> data;
    public String extra;
    public String total;
    public String page;
    public String pageSize;
}
