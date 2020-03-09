package vo;

public class AirplaneTicketVO {
   private String userid;
   private String username;
   private String StartAp;
   private String ArriveAp;
   private String SitNum;
   private String gate;
   private String Startdate;   
   private String Starttime;   
   private String Sitclass;
   private String AirCompany;
   
   public String getAirCompany() {
      return AirCompany;
   }
   public void setAirCompany(String airCompany) {
      AirCompany = airCompany;
   }
   public String getStartdate() {
      return Startdate;
   }
   public void setStartdate(String startdate) {
      Startdate = startdate;
   }   
   public String getStarttime() {
      return Starttime;
   }
   public void setStarttime(String starttime) {
      Starttime = starttime;
   }   
   public String getSitclass() {
      return Sitclass;
   }
   public void setSitclass(String sitclass) {
      Sitclass = sitclass;
   }
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getStartAp() {
      return StartAp;
   }
   public void setStartAp(String startAp) {
      StartAp = startAp;
   }
   public String getArriveAp() {
      return ArriveAp;
   }
   public void setArriveAp(String arriveAp) {
      ArriveAp = arriveAp;
   }
   public String getSitNum() {
      return SitNum;
   }
   public void setSitNum(String sitNum) {
      SitNum = sitNum;
   }
   public String getGate() {
      return gate;
   }
   public void setGate(String gate) {
      this.gate = gate;
   }
}