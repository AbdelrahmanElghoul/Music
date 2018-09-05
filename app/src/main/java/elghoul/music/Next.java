package elghoul.music;

 abstract class Next {

    private int index;

    abstract void Index(int Current, int Length);

     void setIndex(int index) {
         this.index = index;
     }

     int getIndex() {

         return index;
     }
}
