public class Address implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    String add1;
    String add2;
    int cityID;
    int stateID;
    public Address(String add1, String add2, int cityID, int stateID) {

        this.add1 = add1;
        this.add2 = add2;
        this.cityID = cityID;
        this.stateID = stateID;
    }
    public String getAdd1() {
        return add1;
    }
    public void setAdd1(String add1) {
        this.add1 = add1;
    }
    @Override
    public String toString() {
        return "Address [add1=" + add1 + ", add2=" + add2 + ", cityID=" + cityID + ", stateID=" + stateID + "]";
    }
    public String getAdd2() {
        return add2;
    }
    public void setAdd2(String add2) {
        this.add2 = add2;
    }
    public int getCityID() {
        return cityID;
    }
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
    public int getStateID() {
        return stateID;
    }
    public void setStateID(int stateID) {
        this.stateID = stateID;
    }
}
