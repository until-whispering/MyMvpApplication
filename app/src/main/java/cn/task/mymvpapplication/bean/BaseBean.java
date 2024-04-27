/**
  * Copyright 2024 bejson.com 
  */
package cn.task.mymvpapplication.bean;

/**
 * Auto-generated: 2024-04-20 11:58:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BaseBean <T>{

    private int code;
    private String message;
    private T data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setData(T data) {
         this.data = data;
     }
     public T getData() {
         return data;
     }


    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}