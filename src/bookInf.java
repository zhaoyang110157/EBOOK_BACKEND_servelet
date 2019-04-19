public class bookInf {
    String title;
    String image;
    String ISBN;
    String writer;
    float price;
    int inventory;
    String tranch;
    String introduction;
    public bookInf(){
        inventory = 0;
        price = 0.0f;
    }
    public bookInf(String a,String b, String c, String d, float e, int f, String g,String h){
        title = a;
        image = b;
        ISBN = c;
        writer = d;
        price = e;
        inventory = f;
        tranch = g;
        introduction = h;
    }
}
