package GiaiDeThi;

//hoac xai Protected o getter setter cua CanBo
//bai hoc ve Entity - Bound - Control
//bai hoc ve MVC for design UI
//ke thua thi xai lai method cua lop base voi datatype la childClass duoc
//bai hoc using instanceof: You could but should not use the instanceof
//6. bai hoc ve instanceof la 1 bad idea from qoura -> polymorphism
//7. constructor chaining, superclass constructor will be call automatically, no need to call. You can Avoid it by using boolean flag

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class cau20_dechitiet1 {
    public static void main(String[] args){
        QLCB newMachine = new QLCB();

        CongNhan cn = new CongNhan("h", "h", "nam", "khanhhoa", 5);
        KySu ks = new KySu("h", "h", "nam", "khanhhoa", "CNTT", "chinhquy");

        newMachine.add(cn);
        newMachine.add(ks);

        newMachine.hienThi(newMachine.listCN.get(0));
        newMachine.hienThi(newMachine.listKS.get(0));

        newMachine.timKiemTheoTen("h");

    }
}

class QLCB{
    public ArrayList<CongNhan> listCN;
    public ArrayList<KySu> listKS;
    public ArrayList<NhanVien> listNV;

    QLCB(){
        listCN = new ArrayList<>();
        listKS = new ArrayList<>();
        listNV = new ArrayList<>();
        System.out.println("QLCB duoc khoi tao");
    }

    //Cach 1: should use
    public void add(CongNhan cb){
        listCN.add(cb);
    }

    public void add(KySu cb){
        listKS.add(cb);
    }

    public void add(NhanVien cb){
        listNV.add(cb);
    }

    //Cach 2: khong nen lam
//    public void add(CanBo cb){
//        if(cb instanceof CongNhan)
//            listCN.add((CongNhan)cb);
//        else if (cb instanceof NhanVien)
//            listNV.add((NhanVien)cb);
//        else
//            listKS.add((KySu)cb);
//    }

    //Polymorphism
    public void hienThi(CanBo cb){
        cb.hienThi();
    }

    public void nhapThongTin() {
        System.out.println("Giao dien nhap thong tin");
        System.out.println("1. cn, 2. ks, 3. nv");

        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));

        String res = null;
        try {
            res = din.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(res.equals("1"))
            System.out.println("cong nhan");
        else if(res.equals("2"))
            System.out.println("ky su");
        else
            System.out.println("nhan vien");
    }

    public void timKiemTheoTen(String name){
        for(int i = 0; i < listCN.size(); i++) {
            if (listCN.get(i).getHoTen().equals(name))
                System.out.println("Co 1 cong nhan trung ten ne");
        }

        for(int i = 0; i < listKS.size(); i++) {
            if (listKS.get(i).getHoTen().equals(name))
                System.out.println("Co 1 ky su trung ten ne");
        }

        for(int i = 0; i < listNV.size(); i++) {
            if (listNV.get(i).getHoTen().equals(name))
                System.out.println("Co 1 nhan vien trung ten ne");
        }

    }
}



class CanBo{
    private String hoTen;
    private String namSinh;
    private String gioiTinh;
    private String diaChi;

    CanBo() {
        System.out.println("Inside the parent class");
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void hienThi()
    {
        System.out.println("Ho va ten: "+this.hoTen);
        System.out.println("Gioi tinh: "+this.gioiTinh);
        System.out.println("Nam sinh: "+this.namSinh);
        System.out.println("Dia chi: "+this.diaChi);
    }
}

class CongNhan extends CanBo{
    private int bac;
    CongNhan(String hoTen, String namSinh, String gioiTinh, String diaChi, int bac)
    {
        this.setHoTen(hoTen);
        this.setNamSinh(namSinh);
        this.setGioiTinh(gioiTinh);
        this.setDiaChi(diaChi);
        this.setBac(bac);

        System.out.println("Mot CongNhan duoc tao");
    }

    public void hienThi()
    {
        super.hienThi();
        System.out.println("Bac: "+this.bac);
        System.out.println("----------------------- ");
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }
}

class KySu extends CanBo {
    private String nganhDaoTao;
    private String loaiBang;

    KySu(String hoTen, String namSinh, String gioiTinh, String diaChi, String nganhDT, String loaiBang) {
        this.setHoTen(hoTen);
        this.setNamSinh(namSinh);
        this.setGioiTinh(gioiTinh);
        this.setDiaChi(diaChi);
        this.setNganhDaoTao(nganhDT);
        this.setLoaiBang(loaiBang);

        System.out.println("Mot KySu duoc tao");
    }

    public void hienThi()
    {
        super.hienThi();
        System.out.println("Nghanh dao tao: "+this.nganhDaoTao);
        System.out.println("-----------------------");
    }

    public void setNganhDaoTao(String nganhDaoTao) {
        this.nganhDaoTao = nganhDaoTao;
    }

    public String getNganhDaoTao() {
        return nganhDaoTao;
    }

    public void setLoaiBang(String loaiBang) {
        this.loaiBang = loaiBang;
    }

    public String getLoaiBang() {
        return loaiBang;
    }
}

class NhanVien extends CanBo{
    private String congViec;
    NhanVien(String hoTen, String namSinh, String gioiTinh, String diaChi, String congViec)
    {
        this.setHoTen(hoTen);
        this.setNamSinh(namSinh);
        this.setGioiTinh(gioiTinh);
        this.setDiaChi(diaChi);
        this.setCongViec(congViec);

        System.out.println("Mot NhanVien duoc tao");

    }

    public void hienThi()
    {
        super.hienThi();
        System.out.println("Cong Viec: "+this.congViec);
        System.out.println("----------------------- ");
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }
}




/* tao 3 lop data thoi, roi 1 lop quan li rieng
--- Entity - Control - Boundary
Robustness diagrams are written after use cases and before class diagrams. They help to identify the roles of use case steps. You can use them to ensure your use cases are sufficiently robust to represent usage requirements for the system you're building.

They involve:

Actors
Use Cases
Entities
Boundaries
Controls
Whereas the Model-View-Controller pattern is used for user interfaces, the Entity-Control-Boundary Pattern (ECB) is used for systems. The following aspects of ECB can be likened to an abstract version of MVC, if that's helpful:

UML notation

Entities (model)
Objects representing system data, often from the domain model.

Boundaries (view/service collaborator)
Objects that interface with system actors (e.g. a user or external service). Windows, screens and menus are examples of boundaries that interface with users.

Controls (controller)
Objects that mediate between boundaries and entities. These serve as the glue between boundary elements and entity elements, implementing the logic required to manage the various elements and their interactions. It is important to understand that you may decide to implement controllers within your design as something other than objects – many controllers are simple enough to be implemented as a method of an entity or boundary class for example.

Four rules apply to their communication:

Actors can only talk to boundary objects.
Boundary objects can only talk to controllers and actors.
Entity objects can only talk to controllers.
Controllers can talk to boundary objects and entity objects, and to other controllers, but not to actors
*/

/*
Model–view–controller (usually known as MVC) is a software design pattern[1]
commonly used for developing user interfaces that divide the related program logic
into three interconnected elements.

Model
The central component of the pattern. It is the application's dynamic data structure, independent of the user interface.[5] It directly manages the data, logic and rules of the application.
View
Any representation of information such as a chart, diagram or table. Multiple views of the same information are possible, such as a bar chart for management and a tabular view for accountants.
Controller
Accepts input and converts it to commands for the model or view.[6]
In addition to dividing the application into these components, the model–view–controller design defines the interactions between them.[7]

The model is responsible for managing the data of the application. It receives user input from the controller.
The view renders presentation of the model in a particular format.
The controller responds to the user input and performs interactions on the data model objects. The controller receives the input, optionally validates it and then passes the input to the model.
As with other software patterns, MVC expresses the "core of the solution" to a problem while allowing it to be adapted for each system.[8] Particular MVC designs can vary significantly from the traditional description here.[9]

Some web MVC frameworks take a 'thin client' approach that places almost
the entire model, view and controller logic on the server.
In this approach, the client sends either hyperlink requests or form submissions to the controller
and then receives a complete and updated web page (or other document) from the view;
the model exists entirely on the server.[15]
*/

/* instanceof You could but should not use the instanceof
 for(Iterator i = results.iterator(); i.hasNext();) {

    Object row = i.next();

    if (row instanceof User) {
       User u = (User) row; // this is safe
       // ...
    } else if (row instanceof Stuff) {
       Stuff u = (Stuff) row; // this is safe
       // ...
    }

    instead, you can apply polimorphysim -> override the base method
     The techniques can be applied more generally to avoid “instanceof” and
     replace if/else with polymorphism.
     If you want specific behavior depends on the type of object
     then that behavior should be encapsulated on the object itself.

    https://armedia.com/blog/instanceof-avoid-in-code/
 */

/*
6. bai hoc bad idea instance of

Its fairly easy to do this in Java but is a bad idea. Take the following Java 7 code.

List<Object> heterogenousList = new ArrayList<>;
hererogenousList.add(new Dog("fido"));
heterogenousList.add("Cat");
This is actually an ArrayList<Object> not an ArrayList<Dog> because Object is the only common parent type of String and Dog. Let's try getting objects out now.

Dog fido = (Dog) hList.get(1);
What's the problem here? First of all you need explicitly cast the returned object to the expected type.
Secondly, you need to know which specific field in a list is of what particular type and remember that every time this list gets used. This makes it harder to understand.
Third, if you goof up on one of the above two steps, ( which I did here deliberately) you will get a ClassCastException at Runtime. If this a piece of code that gets rarely executed, this error will not show up when compiling, but only after it gets deployed.
It is much much harder to fix bugs once your code is in the hands of consumers and deployed on their systems, than it is to fix it before your code is released.
My personal recommendation is that if you need to keep information on a number of dogs, all the dog toys available, etc , you create a separate class to hold this information, e.g.

class DoggyDayCare  {
List<Dog> dogs;
List<Toy> toys;
// ...
}
of course, you'd want to properly encapsulate it, handle synchronization correctly, etc...

////// another

There's nothing stopping you creating an ArrayList of Object and adding whatever types of objects you like to it. But usually this is not a good idea.

There are two situations where it's normal to add objects of different types to ArrayLists.

One is where they all share the same base class. So if you have an Animal class and various types of animal classes that extends it, like Dog, Cat, etc, all inheriting from Animal, you can create an ArrayList of type Animal and add all your animal objects to it.

ArrayList<Animal> list = new ArrayList<>();
list.add(new Dog());
list.add(new Cat());
etc.

The other is situation is where all your objects implement the same interface. Then you can add these objects to an ArrayList of the interface type.

If you have an ArrayList of Object, either it’ll be largely useless, or else you will end up with code that tests for the type of an object with instanceof. If you ever find yourself doing that, it’s usually a sign you’ve done something that’s not a good idea …. except in certain cases, like if you are just double-checking that an object is an instance of the class that you expect it to be (e.g. in a .equals() method).
* */