package elghoul.music;

public  abstract class Name {
  public   static  String SetName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }
}
