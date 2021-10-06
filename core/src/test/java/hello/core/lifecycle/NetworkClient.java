package hello.core.lifecycle;

public class NetworkClient {


    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = "+url);
        connect();
        call("초기화 연결 메세지");
    }


    // url field setter
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: "+ url);
    }

    // 메세지
    public void call(String message){
        System.out.println("call " + url + "message = " + message);//어디로 보낼지, 무슨 메세지인지
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: "+url);
    }





}