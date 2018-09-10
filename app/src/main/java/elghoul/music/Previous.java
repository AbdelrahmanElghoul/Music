package elghoul.music;

import java.util.Deque;

public class Previous{

    private int index;

    Previous(int CurrentIndex, Deque<Integer> History, int Length)  {
        index( CurrentIndex,History,Length);
    }

/**
*    if Deque() empty index --
* */
    private void index(int CurrentIndex, Deque<Integer> History, int Length) {
        if(History.isEmpty()){
        index=--CurrentIndex;
          if(index<0){
                index=Length-1;
          }
        }else{
            index=History.pop();
        }

    }

    public int getIndex() {
        return index;
    }
}
